package a6;

public interface Sushi {

	// for method definitions look at classes that implement Sushi
	
	String getName();
	IngredientPortion[] getIngredients();
	int getCalories();
	double getCost();
	boolean getHasRice();
	boolean getHasShellfish();
	boolean getIsVegetarian();
	
}
