package a6;

public class PlateImpl implements Plate{

    private Sushi sushi;
    private double price;
    private Color color;

    public PlateImpl(Sushi sushi, double price, Color color) throws PlatePriceException {

        this.sushi = sushi;
        this.price = price;
        this.color = color;

    }

    /* Returns the contents of the plate. 
     * If the plate is empty returns null.
     */
	@Override
	public Sushi getContents() {
        if (sushi == null) {
            return null;
        } else {
            return sushi;
        }
	}

	/* If the plate is already empty, it returns null. 
	 * If not empty, the plate is made empty and the prior contents of the plate is returned.
	 */
	@Override
	public Sushi removeContents() {
        if (sushi == null) {
            return sushi;
        } else {
            Sushi food = sushi;
            sushi = null;
            return food;
        }
	}

	/* This method sets the contents of the plate as the Sushi object passed in as a parameter.
	 * If parameter is null, this method should throw an IllegalArgumentException.
	 * If the price of the plate is less than or equal to the cost of the sushi 
	 * being placed on it, then it throws a PlatePriceException.
	 */
	@Override
	public void setContents(Sushi food) throws PlatePriceException {
        if (food == null) {
            throw new IllegalArgumentException("Illegal");
        } else if (price <= food.getCost()) {
            throw new PlatePriceException();
        } else {
            sushi = food;
        }
		
	}

	// Returns true if the plate has sushi on it, false if not.
	@Override
	public boolean hasContents() {
        if (sushi != null) {
            return true;
        }
        return false;
	}

	// Returns the price associated with the plate.
	@Override
	public double getPrice() {
		return price;
	}

	/* Returns the color associated with the plate as 
	 * represented by one of the Plate.Color enumeration symbols.
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/*
	 * If the plate is empty, returns 0.0. Otherwise, returns the difference 
	 * between the price of the plate and the cost of the sushi on it.
	 */
	@Override
	public double getProfit() {
        if (sushi == null) {
            return 0.0;
        } else {
            return price - sushi.getCost();
        }
    }
}

