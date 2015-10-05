package edu.nesterenko.bank.entity;

public class Disability extends Entity {
	private String name;

	public Disability(int id, String name) {
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
