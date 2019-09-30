package a6;

/* supports the setPlateToNearestPosition method if the belt is full
 * checked exception
 */

public class BeltFullException extends Exception{
    
	private Belt belt;

    public BeltFullException(Belt belt) {
        super("Belt is full");

        this.belt = belt;
    }

    public Belt getBelt() {
        return belt;
    }
}
