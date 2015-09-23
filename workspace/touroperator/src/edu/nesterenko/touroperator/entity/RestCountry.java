package edu.nesterenko.touroperator.entity;

public class RestCountry extends BusinessEntity {
	
	private Country country;
	private RestType restType;
	private String description;

	public RestCountry(int id, Country country, RestType restType, String description) {
		super(id);
		setCountry(country);
		setRestType(restType);
		setDescription(description);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public RestType getRestType() {
		return restType;
	}

	public void setRestType(RestType restType) {
		this.restType = restType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
