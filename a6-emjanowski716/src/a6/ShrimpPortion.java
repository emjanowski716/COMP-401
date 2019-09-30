package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates a ShrimpPortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class ShrimpPortion extends IngredientPortionImpl {

	private static Ingredient ingredient = new Shrimp();

    public ShrimpPortion(double amount) {
        super(ingredient, amount);
    }
}
