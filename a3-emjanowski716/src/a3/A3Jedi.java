package a3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class A3Jedi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int ingredientNum = scan.nextInt();
		
        String ingredientName;
        double price;
        boolean isVeg;
        int calories;

        Ingredient[] ingredientArray = new IngredientImpl[ingredientNum];
        HashMap<String, Ingredient> stuff = new HashMap<>();
        LinkedHashMap<String, Double> complete = new LinkedHashMap<>();
        String[] ingredients = new String[ingredientNum];

        for (int i = 0; i < ingredientNum; i++) {
            ingredientName = scan.next();
            price = scan.nextDouble();
            isVeg = scan.nextBoolean();
            calories = scan.nextInt();

            Ingredient ingredient = new IngredientImpl(ingredientName, price, calories, isVeg);
            ingredientArray[i] = ingredient;
            stuff.put(ingredientName, ingredient);
            complete.put(ingredientName, (double) 0);
            ingredients[i] = ingredientName;

        }
        
        int menuItemNum = scan.nextInt();
        String portionName;
        double amountNum;
        
        HashMap<String, IngredientPortion[]> menuItems = new HashMap<>();
        
        for (int i = 0; i < menuItemNum; i++) {
        	String itemName = scan.next();
        	int ingNum = scan.nextInt();
        	IngredientPortion[] portionArray = new IngredientPortionImpl[ingNum];
        	
        	for (int j = 0; j < ingNum; j++) {
        		portionName = scan.next();
        		amountNum = scan.nextDouble();
        		
        		IngredientPortion portions = new IngredientPortionImpl(stuff.get(portionName), amountNum);
        		portionArray[j] = portions;
        	}
        	
        	menuItems.put(itemName, portionArray);
        	
        }   
        	
        while (true) {
        	String recipe = scan.next();
        	
        	if (recipe.equals("EndOrder")) {
        		
        		break;
        	}
        	
        		IngredientPortion[] portionAnswer = new IngredientPortionImpl[menuItems.get(recipe).length];
        		portionAnswer = menuItems.get(recipe);
        		
        		for (int j = 0; j < portionAnswer.length; j++) {
        			double current = portionAnswer[j].getAmount();
        			double currentNum = complete.get(portionAnswer[j].getName());
        			complete.put(portionAnswer[j].getName(), currentNum + current);
       
        		
        	}
        }
        
        LinkedHashMap<String, String> completeReal = new LinkedHashMap<>();
        for (int i = 0; i < ingredientNum; i++) {
        	double doubleNum = complete.get(ingredients[i]);
        	String stringNum = String.format("%.2f", doubleNum);
        	completeReal.put(ingredients[i], stringNum);
        }
        
        System.out.println("The order will require:");
        
        for (String item : completeReal.keySet()) {

			System.out.println(completeReal.get(item) + " ounces of " + item);
	}
	}

}
