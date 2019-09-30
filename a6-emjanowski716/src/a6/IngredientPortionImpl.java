package a6;

public class IngredientPortionImpl implements IngredientPortion {

    private Ingredient ingredient;
    private double amount;

    public IngredientPortionImpl(Ingredient ingredient, double amount) {
        	
    	if (amount < 0) {
            throw new RuntimeException("No");
        } else {
            this.amount = amount;
        }

        if (ingredient == null) {
            throw new RuntimeException("No");
        } else {
            this.ingredient = ingredient;
        }
    }
    
    // returns the ingredient
	@Override
	public Ingredient getIngredient() {
		return ingredient;
	}

	// returns the name of the ingredient
	@Override
	public String getName() {
		return ingredient.getName();
	}

	// returns the amount of the ingredient
	@Override
	public double getAmount() {
		return amount;
	}

	// returns the amount of calories of the ingredient
	@Override
	public double getCalories() {
		return ingredient.getCaloriesPerOunce() * amount;
	}

	// returns the cost of the ingredient
	@Override
	public double getCost() {
		return ingredient.getPricePerOunce() * amount;
	}

	// checks if the ingredient is vegetarian
	@Override
	public boolean getIsVegetarian() {
		return ingredient.getIsVegetarian();
	}

	// checks if the ingredient is rice
	@Override
	public boolean getIsRice() {
		return ingredient.getIsRice();
	}

	// checks if the ingredient is shellfish
	@Override
	public boolean getIsShellfish() {
		return ingredient.getIsShellfish();
	}

	// combines the amounts of the ingredients if they are equal to each other
	@Override
	public IngredientPortion combine(IngredientPortion other) {
        
		if (this.getIngredient().getName() != other.getName()) {
            throw new RuntimeException("No");
        }

        if (this.getIngredient().equals(other.getIngredient())) {
            IngredientPortion combine = new IngredientPortionImpl(this.ingredient, this.amount + other.getAmount());
            return combine;
        } else {
            return null;
        }
    }

}
