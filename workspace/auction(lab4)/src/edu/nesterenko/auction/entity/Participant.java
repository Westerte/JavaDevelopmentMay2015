package edu.nesterenko.auction.entity;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Participant extends Thread {
	
	private Auction auction;
	private Phaser phaser;
	private Random random = new Random();
	private ReentrantLock sync;
	private Condition isBetted;
	
	public Participant(Auction auction, Phaser phaser, ReentrantLock reentrantLock, Condition condition) {
		this.auction = auction;
		this.phaser = phaser;
		sync = reentrantLock;
		isBetted = condition;
		phaser.register();
	}
	
	public void run() {
		while(!auction.isClosed()) {
			phaser.arriveAndAwaitAdvance();			
			Lot currentLot = auction.getCurrentLot();
			if(currentLot != null) {
				currentLot.register();
				System.out.println(String.format("�����: %s ����������������� �� ���: %s", this.getName(), currentLot.getLotsName()));
				phaser.arriveAndAwaitAdvance();				
				trade(currentLot);
			}				
			pay();
			phaser.arriveAndAwaitAdvance();			
		}
		phaser.arriveAndDeregister();
	}
	
	private void trade(Lot currentLot) {
		boolean decigion = random.nextBoolean();
		while(decigion) {	
			sync.lock();
			try {
				int bet = currentLot.getFinishPrice();
				if(currentLot.getWinner() != null) {
					bet += random.nextInt(300);
					currentLot.setFinishPrice(bet);
				}				
				currentLot.setWinner(this);
				System.out.println(String.format("�����: %s ������ ������: %d", this.getName(), bet));
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
		System.out.println(String.format("�����: %s �������� ����������� ������ ���", this.getName()));
		phaser.arriveAndAwaitAdvance();			
	}
	
	private void pay() {
		Lot currentLot = auction.getCurrentLot();
		if(currentLot.getWinner() == this) {
			System.out.println(String.format("�����: %s �������", this.getName()));	
			try {
				Thread.sleep(random.nextInt(200));
				currentLot.setConfirmed(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
