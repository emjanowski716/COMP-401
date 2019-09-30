package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates an EelPortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class EelPortion extends IngredientPortionImpl{

	private static Ingredient ingredient = new Eel();

    public EelPortion(double amount) {
        super(ingredient, amount);
    }
}
