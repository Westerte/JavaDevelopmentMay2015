package edu.nesterenko.auction.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import edu.nesterenko.auction.exception.LogicalException;

public class Auction extends Thread {
	private LinkedList<Lot> lots = new LinkedList<Lot>();
	private LinkedList<Lot> finishedLots = new LinkedList<Lot>();
	private Lot currentLot;
	private HashMap<Participant, Integer> blockedParticipants = new HashMap<Participant, Integer>();
	private Phaser phaser;
	private Random random = new Random();
	private ReentrantLock sync = new ReentrantLock();
	private Condition cond = sync.newCondition();
	private boolean closed = false;

	public Auction(Phaser phaser) {
		this.phaser = phaser;
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
			if(isEmpty()) {
				setClosed(true);
			}
			phaser.arriveAndAwaitAdvance();			
		}
		phaser.arriveAndDeregister();
	}
	
	private void finishLot() { 
		updateblockedParticipants();
		if(currentLot.getWinner() != null) {
			try {
				Thread.sleep(random.nextInt(500));				
				if(currentLot.isConfirmed()) {
					System.out.println(String.format("����� %s ��������, ��� �������� �����������", currentLot.getWinner().getName()));
					finishedLots.add(currentLot);
				} else {
					try {
						System.out.println(String.format("����� %s �� ��������, ��� ����� ���������", currentLot.getWinner().getName()));						
						int numberOfLotsToWait = 1 + random.nextInt(2);
						System.out.println(String.format("����� %s ����� ����������� �� %d ����", 
								currentLot.getWinner().getName(), numberOfLotsToWait));
						blockedParticipants.put(currentLot.getWinner(), numberOfLotsToWait);
						lots.add(new Lot(currentLot.getStartPrice(), currentLot.getLotsName()));
					} catch(LogicalException e) {
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		} else {
			System.out.println(String.format("��� �������� ������ �� ��������� � �����"));	
		}		
	}	
	
	private void updateblockedParticipants() {
		Set<Entry<Participant, Integer>> entrySet = blockedParticipants.entrySet();
		for(Entry<Participant, Integer> entry : entrySet) {
			int value = entry.getValue() - 1;
			if(value == 0) {
				blockedParticipants.remove(entry.getKey());
				phaser.register();				
			} else {
				blockedParticipants.replace(entry.getKey(), value);
			}
		}
		sync.lock();
		try {
			cond.signalAll();
		} finally {
			sync.unlock();
		}
	}
	
	public void addLot(Lot lot) {
		lots.add(lot);
	}

	public Lot getCurrentLot() {
		boolean isBlocked = false;
		while(blockedParticipants.containsKey(Thread.currentThread())) {
			if(!isBlocked) {
				phaser.arriveAndDeregister();
				isBlocked = true;
				System.out.println(String.format("����� %s �������� ���������", Thread.currentThread().getName()));
			}						
			sync.lock();
			try {
				cond.await();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} finally {
				sync.unlock();
			}			
		}	
		if(isBlocked) {			
			System.out.println(String.format("������ %s ����� �������� �������" , Thread.currentThread().getName()));		
			return null;
		}
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

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
}