package a3;

public interface IngredientPortion {

	Ingredient getIngredient();
	double getAmount();
	String getName();
	boolean getIsVegetarian();
	double getCalories();
	double getCost();
	IngredientPortion combine(IngredientPortion other);

}
