package edu.nesterenko.auction.entity;

import edu.nesterenko.auction.exception.LogicalException;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Lot {
	private final int startPrice; // �������� ������ �������� � �� ������������	
	private int finishPrice;
	private String lotsName;
	private Participant winner = null;
	private AtomicBoolean confirmed = new AtomicBoolean();
	private AtomicInteger numberOfParticipant = new AtomicInteger();
	
	public Lot(int startPrice, String lotsName) throws LogicalException {
		if(startPrice > 0) { 
			this.startPrice = startPrice;
			this.finishPrice = startPrice;
		} else {
			throw new LogicalException("startPrice must be greater then 0");
		}
		setLotsName(lotsName);
	}
	
	public int getStartPrice() {
		return startPrice;
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
			throw new LogicalException("lotsName must be ");	//���������� ������� ����� ������
		}
	}

	public boolean isConfirmed() {
		return confirmed.get();
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed.set(confirmed);
	}
	
	@Override
	public String toString() {
		return String.format("lotsName: %s startPrice: %d finishPrice: %d winner: %s", lotsName, startPrice, finishPrice, winner.getName());
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
}