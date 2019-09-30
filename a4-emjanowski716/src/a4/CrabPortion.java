package a4;

public class CrabPortion extends IngredientPortionImpl{

	private static Ingredient crab = new Crab();

    public CrabPortion(double amount) {
        super(crab, amount);
    }
}
