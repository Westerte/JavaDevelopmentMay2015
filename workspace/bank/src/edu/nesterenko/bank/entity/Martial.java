package edu.nesterenko.bank.entity;

public class Martial extends Entity {
	private String name;

	public Martial(int id, String name) {
		super(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
