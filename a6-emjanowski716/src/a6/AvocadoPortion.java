package a6;

/*
 * a child class of parent class IngredientPortionImpl
 * creates an AvocadoPortion object
 * accepts a parameter of the ingredient amount in ounces
*/
public class AvocadoPortion extends IngredientPortionImpl {

    private static Ingredient ingredients = new Avocado();

    public AvocadoPortion(double amount) {
        super(ingredients, amount);
    }
}
