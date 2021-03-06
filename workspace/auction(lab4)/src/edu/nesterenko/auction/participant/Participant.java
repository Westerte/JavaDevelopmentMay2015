package edu.nesterenko.auction.participant;

import edu.nesterenko.auction.auction.Auction;

public class Participant extends Thread {
	
	private Auction auction;
	
	public Participant(Auction auction) {
		this.auction = auction;
		auction.register();
	}
	
	public void run() {
		auction.trade();
	}
}
