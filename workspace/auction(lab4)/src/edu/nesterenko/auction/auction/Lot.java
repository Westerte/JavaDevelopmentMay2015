package edu.nesterenko.auction.auction;

import edu.nesterenko.auction.exception.LogicalException;
import edu.nesterenko.auction.participant.Participant;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Lot {
	private final int START_PRICE;
	private int finishPrice;
	private String lotsName;
	private Participant winner;
	private AtomicBoolean confirmed;
	private AtomicInteger numberOfParticipant;
	
	public Lot(int startPrice, String lotsName) throws LogicalException {
		if(startPrice <= 0) { 
			throw new LogicalException("startPrice must be greater then 0");
		} 				
		this.START_PRICE = startPrice;
		this.finishPrice = startPrice;
		setLotsName(lotsName);
		confirmed = new AtomicBoolean();
		numberOfParticipant = new AtomicInteger();
	}
	
	public int getStartPrice() {
		return START_PRICE;
	}
	
	public int getFinishPrice() {
		return finishPrice;
	}
	
	public void setFinishPrice(int finishPrice) {
		this.finishPrice = finishPrice;
	}
	
	public Participant getWinner() {
		return winner;
	}
	
	public void setWinner(Participant winner) {
		this.winner = winner;
	}

	public String getLotsName() {
		return lotsName;
	}

	public void setLotsName(String lotsName) throws LogicalException {
		if(lotsName != null && !lotsName.isEmpty()) {
			this.lotsName = lotsName; 
		} else {
			throw new LogicalException("lotsName must be set");
		}
	}
	public boolean isConfirmed() {
		return confirmed.get();
	}

	public void confirm() {
		this.confirmed.set(true);
	}	
	
	public void register() {
		numberOfParticipant.incrementAndGet();
	}
	
	public void deregister() {
		numberOfParticipant.decrementAndGet();
	}

	public int getNumberOfParticipant() {
		return numberOfParticipant.get();
	}
	
	@Override
	public String toString() {
		return String.format("lotsName: %s startPrice: %d finishPrice: %d winner: %s", 
				lotsName, START_PRICE, finishPrice, winner.getName());
	}
}
