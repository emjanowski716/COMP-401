package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates a TunaPortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class TunaPortion extends IngredientPortionImpl {

	private static Ingredient ingredient = new Tuna();

    public TunaPortion(double amount) {
        super(ingredient, amount);
    }
}
