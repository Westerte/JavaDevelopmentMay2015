package edu.nesterenko.touroperator.entity;

public class ResortHotel extends BusinessEntity {
	
	private String name;
	private String description;
	private Resort resort;
	private int stars;
	
	public ResortHotel(int id, String name, String description, Resort resort, int stars) {
		super(id);
		setName(name);
		setDescription(description);
		setResort(resort);
		setStars(stars);
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Resort getResort() {
		return resort;
	}

	public void setResort(Resort resort) {
		this.resort = resort;
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

	
}
