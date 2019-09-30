package a6;

// child class of parent class PlateImpl

public class GreenPlate extends PlateImpl{

    public GreenPlate(Sushi food) throws PlatePriceException {
        super(food, 2.0, Color.GREEN);
    }
}
