package a1;

import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int ingredient = scan.nextInt();
		String[] allIngredients = new String[ingredient];
		
		for (int i = 0; i < ingredient; i++) {
			allIngredients[i] = scan.next();
			double price = scan.nextDouble();
		}
		
		int customerNum = scan.nextInt();
		int[] customerCount = new int[ingredient];
		int[] ingredientCount = new int[ingredient];
		
		for (int i = 0; i < customerNum; i++) {
			String first = scan.next();
			String last = scan.next();
			int amountNum = scan.nextInt();
			String[] customerList = new String[amountNum];
			
			for (int j = 0; j < amountNum; j++) {
				int quantity = scan.nextInt();
				customerList[j] = scan.next();
				int output = getIngredient(allIngredients, customerList[j]);
				
				if (output != -1) {
					ingredientCount[output] += quantity;
				}
			}
			
			for (int z = 0; z < ingredient; z++) {
				int output = getIngredient(customerList, allIngredients[z]);
				
				if (output != -1) {
					customerCount[z]++;
				}
			}
		}
		
		for (int i = 0; i < ingredient; i++) {
			if (customerCount[i] == 0) {
				System.out.println("No customers bought " + allIngredients[i]);
			} else {
				System.out.println(customerCount[i] + " customers bought " + ingredientCount[i] + " " + allIngredients[i]);
			}
		}
		
	}
	
	public static int getIngredient(String[] ingredients, String name) {
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
