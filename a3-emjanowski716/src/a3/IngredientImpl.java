package a3;

public class IngredientImpl implements Ingredient {

    private String name;
    private double price;
    private int calories;
    private boolean is_vegetarian;
    
    public IngredientImpl(String name, double price, int calories, boolean is_vegetarian) {

        this.name = name;
        this.price = price;
        this.calories = calories;
        this.is_vegetarian = is_vegetarian;
        
        if (name == null) {
            throw new RuntimeException("Explanation string");
        }
        
        if (price < 0) {
            throw new RuntimeException("Explanation string");
        }
        
        if (calories < 0) {
            throw new RuntimeException("Explanation string");
        }

    }


    public String getName() {
    	return name;
    }


    public boolean getIsVegetarian() {
        return is_vegetarian;
    }


    public double getCaloriesPerDollar() {

        return (calories / price);

    }


    public double getPricePerOunce() {
        return price;
    }


    public int getCaloriesPerOunce() {
        return calories;
    }

}
