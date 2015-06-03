package edu.nesterenko.parcer.entity;


import edu.nesterenko.parcer.exception.LogicalException;

public abstract class Component implements Iterable<Component>{
	private String name;
	
	public Component(String name) {
		this.name = name;
	}
	
	public abstract Component getComponent(int index);
	public abstract void addComponent(Component c) throws LogicalException;
	public abstract boolean removeComponent(Component c) throws LogicalException;
	public abstract String getContent();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
