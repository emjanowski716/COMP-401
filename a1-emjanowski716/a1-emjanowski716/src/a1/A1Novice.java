package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String output = "";
		int customers = scan.nextInt();
		for (int i = 0; i < customers; i++) {
			
			char firstName = scan.next().charAt(0);
			String lastName = scan.next();
			int totalThings = scan.nextInt();
			double allPrices = 0;
			
			for (int j = 0; j < totalThings; j++ ) {
				
				int quantity = scan.nextInt();
				scan.next();
				allPrices = allPrices + scan.nextDouble() * quantity;
			}
			
			String  allPricesValue = String.format("%.2f", allPrices);
			output = firstName + ". " + lastName + ": " + allPricesValue;
		
			System.out.println(output);

		}

	}
			
	
}
