package edu.nesterenko.parcer.entity;

import java.util.Iterator;

import edu.nesterenko.parcer.exception.LogicalException;

public class Leaf extends Component {
	private String content;	
	
	public Leaf(String name, String content) {
		super(name);
		this.content = content;
	}
	
	@Override
	public Component getComponent(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addComponent(Component c) throws LogicalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeComponent(Component c) throws LogicalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Iterator<Component> iterator() {
		throw new UnsupportedOperationException();
	}

}
