package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates a RicePortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class RicePortion extends IngredientPortionImpl{

	private static Ingredient ingredient = new Rice();

    public RicePortion(double amount) {
        super(ingredient, amount);
    }
}
