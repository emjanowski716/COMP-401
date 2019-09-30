package a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		HashMap<String, Double> items = new
		HashMap<String, Double>();
		
		HashMap<Double, String> people = new
		HashMap<Double, String>();
		
		int itemNumber = scan.nextInt();
		for (int k = 0; k < itemNumber; k++) {
			
			items.put(scan.next(), scan.nextDouble());
		}
		
		ArrayList<Double> arr = new
		ArrayList<Double>();
		
		int customers = scan.nextInt();
		for (int i = 0; i < customers; i++) {
			
			String firstName = scan.next();
			String lastName = scan.next();
			int totalThings = scan.nextInt();
			double allPrices = 0; 
			
			
			for (int j = 0; j < totalThings; j++ ) {
				
				int quantity = scan.nextInt();
				allPrices = allPrices + items.get(scan.next()) * quantity;
						
			}
			
			for (int s = 0; s < customers; s++) {
				people.put(allPrices, firstName + " " + lastName);
				arr.add(allPrices);
			}
			
		
			double max = getMax(arr);
			double min = getMin(arr);
			double avg = getAvg(arr);
			
		
			  if (i == customers - 1) {
				String maxValue = String.format("%.2f", max);
				String minValue = String.format("%.2f", min);
				String avgValue = String.format("%.2f", avg);
				System.out.println("Biggest: " + people.get(max) + " (" + maxValue + ")");
				
				System.out.println("Smallest: " + people.get(min) + " (" + minValue + ")");
				System.out.println("Average: " + avgValue);

			}
		}
		
	}
	public static double getMax(ArrayList<Double> arr) {
		
		double max = arr.get(0);
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) > max) {
				max = arr.get(i);
			}
		
		}
		
		return max;
	}
	
	public static double getAvg(ArrayList<Double> arr) {
		
		double total = 0;
		
		for (int i = 0; i < arr.size(); i++) {
			total = total + arr.get(i);
		}
		total = total / arr.size();
		return total;
	}
	
	public static double getMin(ArrayList<Double> arr) {
		
		double max = arr.get(0);
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) < max) {
				max = arr.get(i);
			}
		
		}
		
		return max;
	}
}
