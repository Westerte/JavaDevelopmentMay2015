package edu.nesterenko.parcer.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nesterenko.parcer.exception.LogicalException;

public class Composite extends Component {
	
	private List<Component> components = new ArrayList<Component>();

	public Composite(String name) {
		super(name);
	}
	
	@Override
	public Component getComponent(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComponent(Component c) throws LogicalException {
		if(null != c) {
			components.add(c);
		} else {
			throw new LogicalException("Component must not be null");
		}
	}

	@Override
	public boolean removeComponent(Component c) throws LogicalException {
		if(null != c) {
			components.add(c);
		} else {
			throw new LogicalException("Component must not be null");
		}
		return components.remove(c);
	}

	@Override
	public Iterator<Component> iterator() {
        return components.iterator();
    }

	@Override
	public String getContent() {
		throw new UnsupportedOperationException();
	}
}
