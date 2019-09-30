package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates a CrabPortion object
 * accepts a parameter of the ingredient amount in ounces
*/

public class CrabPortion extends IngredientPortionImpl{

	private static Ingredient crab = new Crab();

    public CrabPortion(double amount) {
        super(crab, amount);
    }
}
