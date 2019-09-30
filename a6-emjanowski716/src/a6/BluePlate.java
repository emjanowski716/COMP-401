package a6;

// child class of parent class PlateImpl

public class BluePlate extends PlateImpl{

    public BluePlate(Sushi food) throws PlatePriceException{
        super(food, 4.0, Color.BLUE);
    }
}
