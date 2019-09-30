package a4;

public class SeaweedPortion extends IngredientPortionImpl {
	
	private static Ingredient ingredient = new Seaweed();

    public SeaweedPortion(double amount) {
        super(ingredient, amount);
    }
}
