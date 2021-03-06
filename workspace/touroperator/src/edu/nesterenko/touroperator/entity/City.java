package edu.nesterenko.touroperator.entity;

public class City extends BusinessEntity {
	private String name;
	private String description;
	private Country country;
	
	public City(int id, String name, String description, Country country) {
		super(id);
		this.setName(name);
		this.setDescription(description);
		this.setCountry(country);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
