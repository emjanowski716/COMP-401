package a6;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Roll implements Sushi{
    private String name;
    private IngredientPortion[] rollIng;
    private List<IngredientPortion> ingredients = new ArrayList<IngredientPortion>();


    public Roll(String name, IngredientPortion[] roll_ingredients) {

        this.name = name;
        this.rollIng = roll_ingredients.clone();

        if (name.equals(null)) {
            throw new RuntimeException("Illegal");
        }

        if (roll_ingredients.equals(null)) {
            throw new RuntimeException("Illegal");
        }

        for (int i = 0; i < roll_ingredients.length; i++) {
            if (roll_ingredients[i].equals(null)) {
                throw new RuntimeException("Illegal");
            }
        }
        
        // checks if ingredients are equal to one another and combines them

        IngredientPortion[] ingredients1 = roll_ingredients.clone();
        Hashtable<String, IngredientPortion> ingredientHash = new Hashtable<String, IngredientPortion>();
        for (int i = 0; i < ingredients1.length; i++) {
            if (!ingredientHash.containsKey(ingredients1[i].getName())) {
                ingredientHash.put(ingredients1[i].getName(), ingredients1[i]);
            } else {
                ingredientHash.replace(ingredients1[i].getName(), ingredients1[i].combine(ingredientHash.get(ingredients1[i].getName())));
            }
        }
        
        if (ingredientHash.get("seaweed") != null) {
            if (ingredientHash.get("seaweed").getAmount() < .1) {
                ingredientHash.replace("seaweed", new SeaweedPortion(.1));
            } else {
            ingredientHash.put("seaweed", new SeaweedPortion(.1));
        }

        this.rollIng = ingredientHash.values().toArray(new IngredientPortion[0]);
        }
  }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public IngredientPortion[] getIngredients() {
        return rollIng.clone();
    }

    @Override
    public int getCalories() {

        double cals = 0;


        for (int i = 0; i < rollIng.length; i++) {
            cals += rollIng[i].getCalories();
        }

        int calories = (int) (cals + 0.5);

        return calories;

    }

    @Override
    public double getCost() {

        double costs = 0;
        double cost;

        for (int i = 0; i < rollIng.length; i++) {
            costs += rollIng[i].getCost();
        }

        cost = (((int) ((costs * 100.0) + 0.5)) / 100.0);
        return cost;
    }

    @Override
    public boolean getHasRice() {

        for (int i = 0; i < rollIng.length; i++) {
            if (rollIng[i].getIsRice()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean getHasShellfish() {

        for (int i = 0; i < rollIng.length; i++) {
            if (rollIng[i].getIsShellfish()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean getIsVegetarian() {

        for (int i = 0; i < rollIng.length; i++) {
            if (rollIng[i].getIsVegetarian()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
