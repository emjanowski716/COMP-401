package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates a SeaweedPortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class SeaweedPortion extends IngredientPortionImpl {
	
	private static Ingredient ingredient = new Seaweed();

    public SeaweedPortion(double amount) {
        super(ingredient, amount);
    }
}
