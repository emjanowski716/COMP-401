package a4;

public class AvocadoPortion extends IngredientPortionImpl {

    private static Ingredient ingredients = new Avocado();

    public AvocadoPortion(double amount) {
        super(ingredients, amount);
    }
}
