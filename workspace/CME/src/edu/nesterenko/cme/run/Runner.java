package edu.nesterenko.cme.run;

import java.util.ArrayList;
import edu.nesterenko.cme.thread.CollectionSorter;

public class Runner {
	
	public static void main(String...args) {
		ArrayList<Integer> intList = new ArrayList<Integer>(100);
		for(int i = 100; i >= 1 ; i--) {
			intList.add(i);
		}
		CollectionSorter collectionSorter1 = new CollectionSorter("Поток1", intList);
		CollectionSorter collectionSorter2 = new CollectionSorter("Поток2", intList);
		collectionSorter1.start();
		collectionSorter2.start();		
	}
}
