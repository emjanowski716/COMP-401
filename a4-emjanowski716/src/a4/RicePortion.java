package a4;

public class RicePortion extends IngredientPortionImpl{

	private static Ingredient ingredient = new Rice();

    public RicePortion(double amount) {
        super(ingredient, amount);
    }
}
