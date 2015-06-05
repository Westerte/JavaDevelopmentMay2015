package edu.nesterenko.parcer.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.exception.PhisicalException;

public class Composite extends Component {
	
	private List<Component> components = new ArrayList<Component>();

	public Composite(String name) {
		super(name);
	}
	
	@Override
	public Component getComponent(int index) throws PhisicalException {
		if(index < 0 || index > size()) {
			throw new PhisicalException("Value of index is out of bounds");
		}
		return components.get(index);
	}
	
	public void setComponent(int index, Component component) throws PhisicalException, LogicalException {
		if(index < 0 || index > size()) {
			throw new PhisicalException("Value of index is out of bounds");
		}
		if(component == null) {
			throw new LogicalException("Component must be not null");
		}
		components.set(index, component);
	}

	@Override
	public void addComponent(Component component) throws LogicalException {
		if(component == null) {
			throw new LogicalException("Component must not be null");
		}
		components.add(component);
	}
	
	@Override
	public int size() {
		return components.size();		
	}
	
	@Override
	public Iterator<Component> iterator() {
        return components.iterator();
    }

	@Override
	public String getContent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeComponent(Component component) throws LogicalException {
		if(component == null) {
			throw new LogicalException("Component must not be null");
		} 			
		components.remove(component);	
	}
}
