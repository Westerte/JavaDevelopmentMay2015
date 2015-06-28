package edu.nesterenko.auction.run;

import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import edu.nesterenko.auction.entity.*;
import edu.nesterenko.auction.exception.LogicalException;

public class AuctionDemo {

	public static void main(String[] args) {
		Phaser phaser = new Phaser();	//����� Phaser ����� �� �����
		ReentrantLock reentrantLock = new ReentrantLock();
		Condition condition = reentrantLock.newCondition();
		Auction auction = new Auction(phaser);
		try {
			auction.addLot(new Lot(300, "�������"));
			auction.addLot(new Lot(300, "�������"));
			auction.addLot(new Lot(300, "��������"));
			auction.addLot(new Lot(300, "��������� ���������"));
			auction.addLot(new Lot(300, "��������� ���������1"));
			auction.addLot(new Lot(300, "��������� ���������2"));
			auction.addLot(new Lot(300, "��������� ���������3"));
			auction.addLot(new Lot(300, "��������� ���������4"));
		} catch (LogicalException e) {
			e.printStackTrace();
		}
		Participant[] participants = new Participant[10];
		for(int i = 0; i < participants.length; i++) {
			participants[i] = new Participant(auction, phaser, reentrantLock, condition);
		}
		auction.start();
		for(Participant participant : participants) {
			participant.start();
		}
		try {		
			for(Participant participant : participants) {
				participant.join();
			}
			auction.join();
			System.out.println();
			System.out.println("���� ��������: ");
			List<Lot> finishedLots = auction.getFinishedLots();
			for(Lot lot : finishedLots) {
				System.out.println(lot);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
