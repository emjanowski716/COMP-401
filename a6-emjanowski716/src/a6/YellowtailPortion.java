package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates a YellowtailPortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class YellowtailPortion extends IngredientPortionImpl{

	private static Ingredient ingredient = new Yellowtail();

    public YellowtailPortion(double amount) {
        super(ingredient, amount);
    }
}
