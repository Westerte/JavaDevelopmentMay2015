package edu.nesterenko.concurrentusage.run;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nesterenko.concurrentusage.thread.CollectionSorter;

public class Runner {
	public static void main(String... args) {
		CopyOnWriteArrayList<Integer> intList = new CopyOnWriteArrayList<Integer>();
		for(int i = 100; i >= 1 ; i--) {
			intList.add(i);
		}
		CollectionSorter collectionSorter1 = new CollectionSorter("Поток1", intList);
		CollectionSorter collectionSorter2 = new CollectionSorter("Поток2", intList);
		collectionSorter1.start();
		collectionSorter2.start();
		try {
			collectionSorter1.join();
			collectionSorter2.join();
		} catch (InterruptedException e) {
			System.err.print(e);
		}		
		List<Integer> list1 = collectionSorter1.getIntList();
		List<Integer> list2 = collectionSorter2.getIntList();
		System.out.println(list1 == list2);	
	}
}
