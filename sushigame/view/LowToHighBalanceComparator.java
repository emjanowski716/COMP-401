package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class LowToHighBalanceComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef a, Chef b) {
		return (int) (Math.round(a.getBalance() * 100.0) - Math.round(b.getBalance() * 100));
	}			
}
