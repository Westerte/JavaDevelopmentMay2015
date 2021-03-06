package edu.nesterenko.auction.auction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import edu.nesterenko.auction.exception.LogicalException;
import edu.nesterenko.auction.participant.Participant;

public class Auction extends Thread {
	private LinkedList<Lot> lots;
	private LinkedList<Lot> finishedLots;
	private Lot currentLot;
	private Map<Participant, Integer> blockedParticipants;
	private ReentrantLock lockForBlockedParticipant;
	private Condition condForBlockedParticipant;
	private Phaser phaser;
	private ReentrantLock sync;
	private Condition isBetted;	
	private Random random;	
	private volatile boolean closed;
	

	public Auction() {
		lots = new LinkedList<Lot>();
		finishedLots = new LinkedList<Lot>();
		blockedParticipants = new ConcurrentHashMap<Participant, Integer>();
		lockForBlockedParticipant = new ReentrantLock();		
		condForBlockedParticipant = lockForBlockedParticipant.newCondition();			
		phaser = new Phaser();
		sync = new ReentrantLock();
		isBetted = sync.newCondition();
		random = new Random();				
		phaser.register();
	}	
	
	public void run() {
		while(!isEmpty()) {
			System.out.println();
			currentLot = lots.removeFirst();
			phaser.arriveAndAwaitAdvance(); 
			phaser.arriveAndAwaitAdvance();
			phaser.arriveAndAwaitAdvance();			
			finishLot();			
			phaser.arriveAndAwaitAdvance();			
		}
		System.out.println(String.format("�����: %s ��������", this.getName()));
		phaser.arriveAndDeregister();
	}
	
	public void trade() {
		Participant participant = (Participant)Thread.currentThread();
		while(!isClosed()) {
			phaser.arriveAndAwaitAdvance();	
			if(!checkGuilty()) {
				betting(participant);
				phaser.arriveAndAwaitAdvance();
				pay(participant);
			}					
			phaser.arriveAndAwaitAdvance();			
		}
		System.out.println(String.format("�����: %s ��������", participant.getName()));
		phaser.arriveAndDeregister();
	}	
	
	private boolean checkGuilty() {
		boolean isBlocked = false;
		while(blockedParticipants.containsKey(Thread.currentThread()) && !isClosed()) {
			if(!isBlocked) {
				phaser.arriveAndDeregister();
				isBlocked = true;
				System.out.println(String.format("����� %s �������� ���������", 
						Thread.currentThread().getName()));
			}						
			lockForBlockedParticipant.lock();
			try {
				condForBlockedParticipant.await();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} finally {
				lockForBlockedParticipant.unlock();
			}			
		}	
		if(isBlocked) {			
			System.out.println(String.format("������ %s ����� �������� �������" , 
					Thread.currentThread().getName()));		
			return true;
		}
		return false;
	}
	
	private void betting(Participant participant) {
		currentLot.register();
		System.out.println(String.format("�����: %s ����������������� �� ���: %s",  
				participant.getName(), currentLot.getLotsName()));
		phaser.arriveAndAwaitAdvance();				
		boolean decigion = random.nextBoolean();
		while(decigion) {	
			sync.lock();
			try {
				int bet = currentLot.getFinishPrice();
				if(currentLot.getWinner() != null) {
					bet += random.nextInt(300);
					currentLot.setFinishPrice(bet);
				}				
				currentLot.setWinner(participant);
				System.out.println(String.format("�����: %s ������ ������: %d", 
						participant.getName(), bet));
				isBetted.signal();
				try {
					if(currentLot.getNumberOfParticipant() != 1) {
						isBetted.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				sync.unlock();		
			}
			decigion = (currentLot.getNumberOfParticipant() != 1)?random.nextBoolean():false;
		}			
		currentLot.deregister();
		if(currentLot.getNumberOfParticipant() == 1) {
			sync.lock();
			try {
				isBetted.signal();
			} finally {
				sync.unlock();
			}
		}
		System.out.println(String.format("�����: %s �������� ����������� ������ ���", participant.getName()));
	}
	
	private void pay(Participant participant) {
		if(currentLot.getWinner() == participant) {
			System.out.println(String.format("�����: %s �������", participant.getName()));	
			try {
				Thread.sleep(random.nextInt(500));
				currentLot.confirm();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	private void finishLot() { 
		if(currentLot.getWinner() != null) {
			try {
				Thread.sleep(random.nextInt(500));	
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			if(currentLot.isConfirmed()) {
				System.out.println(String.format("����� %s ��������, ��� �������� �����������", 
						currentLot.getWinner().getName()));
				finishedLots.add(currentLot);
				updateBlockedParticipants();
			} else {		
				updateBlockedParticipants();
				System.out.println(String.format("����� %s �� ��������, ��� ����� ���������", 
						currentLot.getWinner().getName()));						
				int numberOfLotsToWait = 1 + random.nextInt(2);
				System.out.println(String.format("����� %s ����� ����������� �� %d ����", 
						currentLot.getWinner().getName(), numberOfLotsToWait));
				blockedParticipants.put(currentLot.getWinner(), numberOfLotsToWait);
				try {						
					lots.add(new Lot(currentLot.getStartPrice(), currentLot.getLotsName()));
				} catch(LogicalException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println(String.format("��� �������� ������ �� ��������� � �����"));	
		}	
		if(isEmpty()) {
			closed = true;				
		}
	}	
	
	private void updateBlockedParticipants() {
		Set<Entry<Participant, Integer>> entrySet = blockedParticipants.entrySet();
		for(Entry<Participant, Integer> entry : entrySet) {
			int value = entry.getValue() - 1;
			if(value == 0) {				
				phaser.register();
				blockedParticipants.remove(entry.getKey());
			} else {
				blockedParticipants.replace(entry.getKey(), value);
			}
		}
		lockForBlockedParticipant.lock();
		try {
			condForBlockedParticipant.signalAll();
		} finally {
			lockForBlockedParticipant.unlock();
		}
	}	
	
	public void register() {
		phaser.register();
	}
	
	public void addLot(Lot lot) {
		lots.add(lot);
	}

	public Lot getCurrentLot() {		
		return currentLot;
	}	
	
	public List<Lot> getFinishedLots() {
		return Collections.unmodifiableList(finishedLots);
	}
	
	public boolean isEmpty() {
		return lots.isEmpty();
	}

	public boolean isClosed() {
		return closed;
	}
}
