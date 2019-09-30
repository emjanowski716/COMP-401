package a6;

public class IngredientImpl implements Ingredient{

    private String name;
    private double price;
    private int calories;
    private boolean isVeg;
    private boolean isRice;
    private boolean isShellfish;


    public IngredientImpl(String name, double price, int calories, boolean isVeg, boolean isRice, boolean isShellfish) {

        this.name = name;
        this.price = price;
        this.calories = calories;
        this.isVeg = isVeg;
        this.isRice = isRice;
        this.isShellfish = isShellfish;

    }

    // returns the name of the ingredient
	@Override
	public String getName() {
		return name;
	}

	// returns the calories per dollar
	@Override
	public double getCaloriesPerDollar() {
		return (calories / price);
	}

	// returns the calories of the ingredient
	@Override
	public int getCaloriesPerOunce() {
		return calories;
	}

	// returns the price of the ingredient
	@Override
	public double getPricePerOunce() {
		return price;
	}
	
	// checks if two ingredients are equal to each other
	public boolean equals(Ingredient other) {
		if (this.getName().equals(other.getName())) {
			if (this.getCaloriesPerOunce() == other.getCaloriesPerOunce()) {
				if (Math.abs(this.getPricePerOunce() - other.getPricePerOunce()) < 0.01) {
					if (this.getIsVegetarian() == other.getIsVegetarian()) {
						if (this.getIsRice() == other.getIsRice()) {
							if (this.getIsShellfish() == other.getIsShellfish()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	// checks if the ingredient is vegetarian
	@Override
	public boolean getIsVegetarian() {
		return isVeg;
	}

	// checks if the ingredient is rice
	@Override
	public boolean getIsRice() {
		return isRice;
	}

	// checks if the ingredient is a shellfish
	@Override
	public boolean getIsShellfish() {
		return isShellfish;
	}
}
