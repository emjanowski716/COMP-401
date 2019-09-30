package a4;

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


	@Override
	public String getName() {
		return name;
	}


	@Override
	public double getCaloriesPerDollar() {
		return (calories / price);
	}


	@Override
	public int getCaloriesPerOunce() {
		return calories;
	}


	@Override
	public double getPricePerOunce() {
		return price;
	}

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


	@Override
	public boolean getIsVegetarian() {
		return isVeg;
	}


	@Override
	public boolean getIsRice() {
		return isRice;
	}


	@Override
	public boolean getIsShellfish() {
		return isShellfish;
	}
}
