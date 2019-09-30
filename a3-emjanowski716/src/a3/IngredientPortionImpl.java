package a3;

public class IngredientPortionImpl implements IngredientPortion {

	private Ingredient ing;
	private double amount;
	
	public IngredientPortionImpl(Ingredient ing, double amount) {
		this.ing = ing;
		this.amount = amount;
		
		if (ing == null) {
			throw new RuntimeException("Explanation string");
		}
		
		if (amount < 0) {
			throw new RuntimeException("Explanation string");
		}
	}
	
    public Ingredient getIngredient() {
        return ing;
    }

    public double getCalories() {

        return this.ing.getCaloriesPerOunce() * amount;
    }

    public boolean getIsVegetarian() {

        return this.ing.getIsVegetarian();
    }


    public double getCost() {
        return (this.ing.getCaloriesPerDollar() * amount) / 100;
    }


    public IngredientPortion combine(IngredientPortion other) {

        if (other == null) {
            return new IngredientPortionImpl(this.ing, this.amount);
        }

        if (other.getIngredient() != this.getIngredient()) {
        	throw new RuntimeException("Explanation string");
        }

        return new IngredientPortionImpl(other.getIngredient(), this.amount + amount);
    }


    public String getName() {
        return ing.getName();
    }

    public double getAmount() {
        return amount;
    }

}
