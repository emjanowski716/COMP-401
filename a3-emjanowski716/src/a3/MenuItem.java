package a3;

public interface MenuItem {
	
	String getName();
	IngredientPortion[] getIngredients();
	int getCalories();
	double getCost();
	boolean getIsVegetarian();
	
}
