package a6;

public interface IngredientPortion {

	// for method definitions look at IngredientPortionImpl
	
	Ingredient getIngredient();
	String getName();
	double getAmount();
	double getCalories();
	double getCost();
	boolean getIsVegetarian();
	boolean getIsRice();
	boolean getIsShellfish();
	IngredientPortion combine(IngredientPortion other);
	
}
