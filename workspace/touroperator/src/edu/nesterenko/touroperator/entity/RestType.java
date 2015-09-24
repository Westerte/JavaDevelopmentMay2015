package edu.nesterenko.touroperator.entity;

public class RestType extends BusinessEntity {
	private String name;
	private String description;
	
	public RestType(int id, String name, 
			String description) {
		super(id);
		setName(name);
		setDescription(description);
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
}
