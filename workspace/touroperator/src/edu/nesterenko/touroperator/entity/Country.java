package edu.nesterenko.touroperator.entity;

public class Country extends BusinessEntity {
	private String name;
	
	public Country(int id, String name) {
		super(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
