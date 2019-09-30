package a2;


import java.util.Hashtable;
import java.util.Scanner;

public class A2Jedi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Hashtable<String, Hashtable> totalOrder = new Hashtable<String, Hashtable>();

        int ingredientNum = scan.nextInt();

        String[] ingredientName = new String[ingredientNum];
        double ingredientPrice = 0;
        String isVeg;
        int ingredientCalories = 0;

        for (int i = 0; i < ingredientNum; i++) {
            ingredientName[i] = scan.next(); 
            ingredientPrice = scan.nextDouble(); 
            isVeg = scan.next();
            ingredientCalories = scan.nextInt(); 
        }

        int recipeNum = scan.nextInt();

        String recipeName = "";
        int recipe_ing_num = 0;
        String recipe_ing_name = "";
        double ing_ounce = 0;


        for (int j = 0; j < recipeNum; j++) {
            recipeName = scan.next();
            recipe_ing_num = scan.nextInt(); 

            Hashtable list = new Hashtable();

            for (int k = 0; k < recipe_ing_num; k++) {
                recipe_ing_name = scan.next();
                ing_ounce = scan.nextDouble(); 

                list.put(recipe_ing_name, ing_ounce); 
                totalOrder.put(recipeName, list);
            }
        }

        double[] ingredient = new double[ingredientNum];

        while (true) {

            String items = scan.next();

            if (items.equals("EndOrder")) {
                break;
            } else {
                Hashtable menu = totalOrder.get(items);

                for (int x = 0; x < ingredientNum; x++) {
                    if (menu.containsKey(ingredientName[x])) { 
                        double tempOunce = (double) menu.get(ingredientName[x]);
                        ingredient[x] += tempOunce;

                    }
                }


            }
        }
        System.out.println("The order will require:");

        for (int y = 0; y < ingredientNum; y++) {

            String ounce = String.format("%.2f", ingredient[y]);
            System.out.println(ounce + " ounces of " + ingredientName[y]);
        }
	}
	
	// You can define helper methods here if needed.
	
}
