package a2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int ingredientNum = scan.nextInt();
		
		HashMap<String, Double> ingredientCals = new HashMap<String, Double>();
		
		String[] vegetarian = new String[ingredientNum];
		
		for (int i = 0; i < ingredientNum; i++) {
			String ingredient = scan.next();
			double price = scan.nextDouble();
			String isVeg = scan.next();
			int calories = scan.nextInt();
			ingredientCals.put(ingredient, division(price, calories));
			vegetarian[i] = isVeg;
		}
		
		int numOfVeg = 0;
		
		for (int i = 0; i < vegetarian.length; i++) {
			if (vegetarian[i].equals("true")) {
				numOfVeg++;
			}
		}
		
		System.out.println("Number of vegetarian ingredients: " + numOfVeg);
		System.out.println("Highest cals/$: " + max(ingredientCals));
		System.out.println("Lowest cals/$: " + min(ingredientCals));
		
	}
	

	
	public static double division(double price, int calories) {
		double calsPerPrice = calories / price;
		return calsPerPrice;
	}
	
	public static String max(HashMap<String, Double> ingredientCals) {
		Map.Entry<String, Double> firstEntry = ingredientCals.entrySet().iterator().next();
		String largestKey = firstEntry.getKey();
		Double largestKeyValue = firstEntry.getValue();
	 
		for (Entry<String, Double> map : ingredientCals.entrySet()) {
		    Double value = map.getValue();
		    if (value > largestKeyValue) {
			largestKeyValue = value;
			largestKey = map.getKey();
		    }
		}
		return largestKey;
	}
	
	public static String min(HashMap<String, Double> ingredientCals) {
		Map.Entry<String, Double> firstEntry = ingredientCals.entrySet().iterator().next();
		String smallestKey = firstEntry.getKey();
		Double largestKeyValue = firstEntry.getValue();
	 
		for (Entry<String, Double> map : ingredientCals.entrySet()) {
		    Double value = map.getValue();
		    if (value < largestKeyValue) {
			largestKeyValue = value;
			smallestKey = map.getKey();
		    }
		}
		return smallestKey;
	}
	
	
}
