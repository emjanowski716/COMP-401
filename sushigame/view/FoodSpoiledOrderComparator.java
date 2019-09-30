package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class FoodSpoiledOrderComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef a, Chef b) {
		return (int) (b.totalSpoiledFood() - a.totalSpoiledFood());
	}			
}
