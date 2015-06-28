package edu.nesterenko.cme.thread;

import java.util.Collections;
import java.util.List;

public class CollectionSorter extends Thread {
	private List<Integer> intList;
	
	public CollectionSorter(String name, List<Integer> intList) {
		super(name);
		this.intList = intList;
	}
	
	@Override
	public void run() {
		Collections.sort(intList);
		for(Integer number : intList) {
			System.out.println(this.getName() + ": " + number);
		}
	}
	
	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}	
}
