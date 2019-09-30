package a6;

import java.util.Iterator;

/*
 * The Belt interface represents the belt in a sushi restaurant upon which
 * Plate objects are placed.
 * 
 * Positions on the belt are numbered from 0 to size-1 but any integer
 * can be interpreted as a valid position as follows:
 *
 * If a position value greater than size-1 is used, it should be 
 * understood as having wrapped around back to the beginning of the belt.
 * 
 * Negative values for positions are understood as having wrapped around
 * to the end of the belt.
 */

public interface Belt extends Iterable<Plate> {
	
	// for description of methods look at class BeltImpl

    public int getSize();

    public Plate getPlateAtPosition(int position);

    public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException;

    public void clearPlateAtPosition(int position);

    public Plate removePlateAtPosition(int position);

    public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException;

    public Iterator<Plate> iterator();

    public Iterator<Plate> iterator(double max_price);

    public Iterator<Plate> iterator(Plate.Color color);

    public Iterator<Plate> iteratorFromPosition(int position, double max_price);

    public Iterator<Plate> iteratorFromPosition(int position, Plate.Color color);

    public Iterator<Plate> iteratorFromPosition(int position);

    public void rotate();
    
}
