package a3;

public class MenuItemImpl implements MenuItem {

	private String name;
	private IngredientPortion[] ingredients;
	
	public MenuItemImpl(String name, IngredientPortion[] ingredients) {
		this.name = name;
		this.ingredients = ingredients;
		
		if (name == null) {
            throw new RuntimeException("Explanation String");
        }
		
		for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i] == null) {
                throw new RuntimeException("Explanation String");
            }
        }
		
		if (ingredients.length == 0) {
            throw new RuntimeException("Explanation String");
        }
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public IngredientPortion[] getIngredients() {
		return ingredients.clone();
	}

	@Override
	public int getCalories() {
        double sum = 0;
        for (int i = 0; i < ingredients.length; i++) {
            sum += ingredients[i].getIngredient().getCaloriesPerOunce() * ingredients[i].getAmount();
        }
        return (int) (sum + 0.5);
	}

	@Override
	public double getCost() {
		 double cost = 0;
	     for (int i = 0; i < ingredients.length; i++) {
	         cost += ingredients[i].getIngredient().getPricePerOunce() * ingredients[i].getAmount();
	     }

	     return (double) Math.round(cost * 100) / 100;
	}

	@Override
	public boolean getIsVegetarian() {
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i].getIsVegetarian() == false) {
				return false;
			}
		}
		return true;
	}

}
