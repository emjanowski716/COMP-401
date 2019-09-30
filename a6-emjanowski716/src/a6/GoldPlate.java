package a6;

// child class of parent class PlateImpl

public class GoldPlate extends PlateImpl{

    public GoldPlate(Sushi food, double price) throws PlatePriceException {
        super(food, price, Color.GOLD);

        if (price < 5.0) {
            throw new IllegalArgumentException("Illegal");
        }

    }

}
