package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class FoodSoldOrderComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef a, Chef b) {
		return (int) (b.totalFoodConsumed() - a.totalFoodConsumed());
	}			
}

