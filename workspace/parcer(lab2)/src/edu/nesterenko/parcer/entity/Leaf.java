package edu.nesterenko.parcer.entity;

import java.util.Iterator;

public class Leaf extends Component {
	private String content;	
	
	public Leaf(String name, String content) {
		super(name);
		this.content = content;
	}
	
	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public Component getComponent(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addComponent(Component component) {
		throw new UnsupportedOperationException();
	}	

	@Override
	public Iterator<Component> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setComponent(int index, Component component){
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeComponent(Component component) {
		throw new UnsupportedOperationException();
	}

}
