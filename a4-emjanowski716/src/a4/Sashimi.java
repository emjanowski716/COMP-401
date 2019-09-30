package a4;

public class Sashimi implements Sushi{
    private double amount = 0.75;
    private SashimiType type;
    private IngredientPortion[] ingredient = new IngredientPortion[1];

    public enum SashimiType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP}

    ;

    public Sashimi(SashimiType type) {

        this.type = type;

        if (type == null) {
            throw new RuntimeException("Illegal");
        }

        switch (type) {
            case TUNA:
                ingredient[0] = new TunaPortion(amount);
                break;
            case YELLOWTAIL:
                ingredient[0] = new YellowtailPortion(amount);
                break;
            case EEL:
                ingredient[0] = new EelPortion(amount);
                break;
            case CRAB:
                ingredient[0] = new CrabPortion(amount);
                break;
            case SHRIMP:
                ingredient[0] = new ShrimpPortion(amount);
        }


    }

    @Override
    public String getName() {
        return ingredient[0].getName() + " sashimi";
    }

    @Override
    public IngredientPortion[] getIngredients() {
        return ingredient;
    }

    @Override
    public int getCalories() {

        double cals = ingredient[0].getCalories();
        int cal = (int) (cals + 0.5);

        return cal;
    }

    @Override
    public double getCost() {

        double costs = ingredient[0].getCost();
        double ingCost = ((int) ((costs * 100.0) + 0.5)) / 100.0;
        return ingCost;
    }

    @Override
    public boolean getHasRice() {
        return false;
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

