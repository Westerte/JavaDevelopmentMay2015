package edu.nesterenko.airline.logic;

import java.util.Comparator;

import edu.nesterenko.airline.entity.Airplane;
public class MaxRangeComparator implements Comparator<Airplane> {

	@Override
	public int compare(Airplane arg0, Airplane arg1) {
		return arg0.getMaxRange() - arg1.getMaxRange();
	}	
}
