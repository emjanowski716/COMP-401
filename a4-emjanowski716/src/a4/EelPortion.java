package a4;

public class EelPortion extends IngredientPortionImpl{

	private static Ingredient ingredient = new Eel();

    public EelPortion(double amount) {
        super(ingredient, amount);
    }
}
