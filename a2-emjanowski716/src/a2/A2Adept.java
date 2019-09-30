package a2;

import java.util.HashMap;
import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int ingredientNum = scan.nextInt();
		
		HashMap<String, Double> priceMap = new HashMap<String, Double>();
		HashMap<String, Integer> calorieMap = new HashMap<String, Integer>();
		HashMap<String, String> veggieMap = new HashMap<String, String>();
		
		for (int i = 0; i < ingredientNum; i++) {
			String ingredientName = scan.next();
			priceMap.put(ingredientName, scan.nextDouble());
			veggieMap.put(ingredientName, scan.next());
			calorieMap.put(ingredientName, scan.nextInt());
		}
		
		int orderNum = scan.nextInt();
		double orderCalories = 0;
		double orderPrice = 0;
		String orderVeg = "";
		int orderVegNum = 0;
		
		for (int i = 0; i < orderNum; i++) {
			String orderName = scan.next();
			int orderIngredients = scan.nextInt();
			
			Double[] calories = new Double[orderIngredients];
			Double[] price = new Double[orderIngredients];
			String[] veggie = new String[orderIngredients];
			
			for (int j = 0; j < orderIngredients; j++) {
				String currentIng = scan.next();
				Double currentOunce = scan.nextDouble();
				orderCalories = getCals(calorieMap.get(currentIng), currentOunce);
				calories[j] = orderCalories;
				orderPrice = getPrice(priceMap.get(currentIng), currentOunce);
				price[j] = orderPrice;
				veggie[j] = veggieMap.get(currentIng);
			}
			
			for (int k = 0; k < calories.length - 1; k++) {
				orderCalories = orderCalories + calories[k];
			}
			
			for (int k = 0; k < price.length - 1; k++) {
				orderPrice = orderPrice + price[k];
			}
			
			for (int k = 0; k < veggie.length; k++) {
				if (veggie[k].equals("true")) {
					orderVegNum = 0;
				} else {
					orderVegNum++;
					break;
				}
			}
			
			if (orderVegNum == 0) {
				orderVeg = "Vegetarian";
			} else {
				orderVeg = "Non-Vegetarian";
			}
			
			String orderPrices = String.format("%.2f", orderPrice);
			int orderCalorie = (int) (orderCalories + 0.5);
			System.out.println(orderName + ":");
			System.out.println(orderCalorie + " calories");
			System.out.println("$" + orderPrices);
			System.out.println(orderVeg);
		
		}
		
	}
	
	public static double getCals(int calories, double ounces) {
		return calories * ounces;
		
	}
	
	public static double getPrice(double price, double ounces) {
		return price * ounces;
		
	}
}
