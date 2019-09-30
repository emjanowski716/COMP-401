package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* A BeltIterator will be used to iterate over all of the plates 
 * on a belt starting with a particular position.
 * 
 * When the end of the belt is reached, 
 * the iterator should go back to the beginning of the belt.
 */

public class BeltIterator implements Iterator<Plate> {

    private int start_position;
    private Belt belt;
    private boolean var = false;

    public BeltIterator(Belt belt, int start_position) {

        this.belt = belt;
        int position = 0;

        if (start_position < 0) {
            position = ((start_position % belt.getSize()) + belt.getSize()) % belt.getSize();
        } else if (start_position > 0) {
            position = start_position % belt.getSize();
        }
        this.start_position = position;

    }

    /* Indicates that there is a next plate object to be iterated. 
     * This method should only return false if the belt is completely empty.
     */
    public boolean hasNext() {

        for (int i = 0; i < belt.getSize() + start_position; i++) {
            if (belt.getPlateAtPosition(i) != null) {
                return true;
            }
        }
        return false;
    }

    /* Retrieves the next plate from the belt. 
     * If next is called on an empty belt, throws a java.util.NoSuchElementException.
     */
    public Plate next() {

        var = true;

        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            for (int i = this.start_position; true; i++) {

                if (i == this.belt.getSize()) {
                    i = 0;
                }

                this.start_position = i;

                if (this.belt.getPlateAtPosition(i) != null) {
                    this.start_position++;
                    return this.belt.getPlateAtPosition(i);
                }
            }
        }
    }

    /* Removes the plate last retrieved from the belt.
     * Throws an IllegalStateException if this method is called prematurely.
     */
    public void remove() {

        if (!var) {
            throw new IllegalStateException();
        }

        var = false;
        belt.removePlateAtPosition(start_position - 1);


    }
}
