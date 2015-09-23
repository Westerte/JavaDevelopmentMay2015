package edu.nesterenko.touroperator.entity;

public abstract class BusinessEntity {
	private int id;
	
	public BusinessEntity(int id) {
		this.id = id;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
