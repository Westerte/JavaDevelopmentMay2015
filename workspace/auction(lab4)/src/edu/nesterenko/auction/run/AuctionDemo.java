package edu.nesterenko.auction.run;

import java.util.List;
import edu.nesterenko.auction.auction.Auction;
import edu.nesterenko.auction.auction.Lot;
import edu.nesterenko.auction.exception.LogicalException;
import edu.nesterenko.auction.participant.Participant;

public class AuctionDemo {

	public static void main(String[] args) {
		Auction auction = new Auction();
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
		Participant[] participants = new Participant[15];
		for(int i = 0; i < participants.length; i++) {
			participants[i] = new Participant(auction);
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
