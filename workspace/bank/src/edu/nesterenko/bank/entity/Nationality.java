package edu.nesterenko.bank.entity;

public class Nationality extends Entity {
	private String Name;

	public Nationality(int id, String name) {
		super(id);
		Name = name;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
