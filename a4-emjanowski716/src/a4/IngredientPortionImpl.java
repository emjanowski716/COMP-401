package a4;

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
    
	@Override
	public Ingredient getIngredient() {
		return ingredient;
	}

	@Override
	public String getName() {
		return ingredient.getName();
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public double getCalories() {
		return ingredient.getCaloriesPerOunce() * amount;
	}

	@Override
	public double getCost() {
		return ingredient.getPricePerOunce() * amount;
	}

	@Override
	public boolean getIsVegetarian() {
		return ingredient.getIsVegetarian();
	}

	@Override
	public boolean getIsRice() {
		return ingredient.getIsRice();
	}

	@Override
	public boolean getIsShellfish() {
		return ingredient.getIsShellfish();
	}

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
