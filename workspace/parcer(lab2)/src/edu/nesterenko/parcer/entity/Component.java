package edu.nesterenko.parcer.entity;


import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.exception.PhisicalException;

public abstract class Component implements Iterable<Component>{
	private String name;
	
	public Component(String name) {
		this.name = name;
	}
	
	public abstract Component getComponent(int index) throws PhisicalException;
	public abstract void setComponent(int index, Component component) throws PhisicalException, LogicalException;
	public abstract void addComponent(Component component) throws LogicalException;
	public abstract void removeComponent(Component component) throws PhisicalException, LogicalException;
	public abstract int size();
	public abstract String getContent();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
