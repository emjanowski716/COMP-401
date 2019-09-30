package a4;

public class ShrimpPortion extends IngredientPortionImpl {

	private static Ingredient ingredient = new Shrimp();

    public ShrimpPortion(double amount) {
        super(ingredient, amount);
    }
}
