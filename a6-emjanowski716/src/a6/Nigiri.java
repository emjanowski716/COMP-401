package a6;

public class Nigiri implements Sushi{
    
	private IngredientPortion seafood;
    private IngredientPortion rice;
    private double seafood_amount = 0.75;
    private double rice_amount = 0.5;
    private NigiriType type;


    public enum NigiriType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};

    // creates a different nigiri depending on the value of type
    
    public Nigiri(NigiriType type) {

        this.type = type;

        switch (type) {
            case TUNA:
                seafood = new TunaPortion(seafood_amount);
                break;
            case YELLOWTAIL:
                seafood = new YellowtailPortion(seafood_amount);
                break;
            case EEL:
                seafood = new EelPortion(seafood_amount);
                break;
            case CRAB:
                seafood = new CrabPortion(seafood_amount);
                break;
            case SHRIMP:
                seafood = new ShrimpPortion(seafood_amount);
                break;
        }

        rice = new RicePortion(rice_amount);


    }

    @Override
    public String getName() {
        return seafood.getName() + " nigiri";
    }
    @Override
    public IngredientPortion[] getIngredients() {

        IngredientPortion[] ingredients = new IngredientPortion[]{seafood, rice};
        return ingredients;
    }

    @Override
    public int getCalories() {

        return (int) (seafood.getCalories() + rice.getCalories() + 0.5);
    }

    @Override
    public double getCost() {

        return (int) (((seafood.getCost() + rice.getCost()) * 100 + 0.5)) / 100.0;


    }

    @Override
    public boolean getHasRice() {
        return true;
    }

    @Override
    public boolean getHasShellfish() {
        switch (type) {
            case TUNA:
                return true;
            case YELLOWTAIL:
                return false;
            case EEL:
                return false;
            case CRAB:
                return true;
            case SHRIMP:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean getIsVegetarian() {
        return false;
    }
}

