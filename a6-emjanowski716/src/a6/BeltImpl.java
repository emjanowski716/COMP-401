package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BeltImpl implements Belt {

    private int belt_size;
    private Plate[] plates;

    public BeltImpl(int belt_size) {

        if (belt_size < 0) {
            throw new IllegalArgumentException();
        } else {
            this.belt_size = belt_size;
            plates = new Plate[belt_size];
        }

    }

    // A getter for the size of the belt.
    @Override
    public int getSize() {
        return belt_size;
    }

    /* 	Returns the plate at the specified position on the belt
     *  or null if there is no Plate object 
     */
    @Override
    public Plate getPlateAtPosition(int position) {

        if (position < 0) {
            position = ((position % belt_size) + belt_size) % belt_size;
        } else {
            position = position % belt_size;
        }

        if (plates[position] == null) {
            return null;
        } else {
            return plates[position];
        }
    }

    /* 	Sets a plate at the specified position on the belt. If the provided plate is null,
     *  then it throws an IllegalArgumentException.
     *  If there is already a plate at that position, throws a BeltPlateException.
     */
    @Override
    public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {

        if (position < 0) {
            position = ((position % belt_size) + belt_size) % belt_size;
        } else {
            position = position % belt_size;
        }

        if (plate == null) {
            throw new IllegalArgumentException();
        } else if (getPlateAtPosition(position) != null) {
            throw new BeltPlateException(position, plate, this);
        } else {
            plates[position] = plate;
        }
    }

    // Clears the specified position on the belt to null
    @Override
    public void clearPlateAtPosition(int position) {

        if (position < 0) {
            position = ((position % belt_size) + belt_size) % belt_size;
        }

        plates[position] = null;

    }

    /* This method sets the provided plate at the specified position. 
     * If the position is already occupied, it attempts to set the plate at the next highest position.
     * If the belt is full and the plate can not be placed, a BeltFullException is thrown.
     */
    @Override
    public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {

    	if (position < 0) {
            position = ((position % belt_size) + belt_size) % belt_size;
        } else {
            position = position % belt_size;
        }


        for (int i = 0; i < plates.length; i++) {

            int newPos = position + i;

            if (plates[newPos] == null) {
            	plates[newPos] = plate;
                return newPos;
            }
        }
        throw new BeltFullException(this);

    }

    /* Removes the plate at the specified position off the belt and returns it.
     * If there is no plate at the specified position, throws a java.util.NoSuchElementException.
     */
    @Override
    public Plate removePlateAtPosition(int position) {

        if (position < 0) {
            position = ((position % belt_size) + belt_size) % belt_size;
        } else {
            position = position % belt_size;
        }

        Plate newPlate = getPlateAtPosition(position);

        if (newPlate == null) {
            throw new NoSuchElementException();
        } else {
            clearPlateAtPosition(position);
            return newPlate;
        }
    }

    // Returns a BeltIterator object for this belt starting at position 0.
    @Override
    public Iterator<Plate> iterator() {
        return new BeltIterator(this, 0);
    }
    
    // Returns a BeltIterator object for this belt starting at the specified position.
    public Iterator<Plate> iteratorFromPosition(int position) {
        return new BeltIterator(this, position);
    }

    // overloaded method that returns a new iterator based on the max price.
    public Iterator<Plate> iterator(double max_price) {
        return new PriceThresholdBeltIterator(this, 0, max_price);
    }

    // overloaded method that returns a new iterator based on the specific color.
    public Iterator<Plate> iterator(Plate.Color color) {
        return new ColorFilteredBeltIterator(this, 0, color);
    }

    // overloaded method that returns a new iterator based on the max price and position.
    public Iterator<Plate> iteratorFromPosition(int position, double max_price) {
        return new PriceThresholdBeltIterator(this, position, max_price);
    }
    // overloaded method that returns a new iterator based on the specific color and position.
    public Iterator<Plate> iteratorFromPosition(int position, Plate.Color color) {
        return new ColorFilteredBeltIterator(this, position, color);
    }

    /* This method rotates the belt so a Plate object set at 
     * position p is now found at position p+1.
     */
    @Override
    public void rotate() {

        Plate[] newPlates = new PlateImpl[this.getSize()];

        for (int i = 0; i < getSize() - 1; i++) {

            newPlates[i + 1] = plates[i];
        }

        plates = newPlates;

    }
}
