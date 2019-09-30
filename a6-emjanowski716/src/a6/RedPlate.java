package a6;

// child class of parent class PlateImpl

public class RedPlate extends PlateImpl{

    public RedPlate(Sushi food) throws PlatePriceException{
        super(food, 1.0, Color.RED);
    }
}
