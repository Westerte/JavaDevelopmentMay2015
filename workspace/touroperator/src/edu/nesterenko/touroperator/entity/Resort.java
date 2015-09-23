package edu.nesterenko.touroperator.entity;

public class Resort extends BusinessEntity {
	
	private String name;
	private String description;
	private City city;
	
	public Resort(int id, String name, String description, City city) {
		super(id);
		this.setName(name);
		this.setDescription(description);
		this.setCity(city);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
