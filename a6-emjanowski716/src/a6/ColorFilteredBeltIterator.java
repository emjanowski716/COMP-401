package a6;

import java.util.ArrayList;
import java.util.Iterator;

/* Creates a belt iterator that iterates over plates matching the specified 
 * color_filter value starting at the position specified as start_position.
 */

public class ColorFilteredBeltIterator implements Iterator<Plate>{

	private Belt _belt;
	private int _start;
	private Plate.Color _color;
	private ArrayList<Plate> maxplates = new ArrayList<Plate>();
	private ArrayList<Integer> maxposition = new ArrayList<Integer>();
	
	public ColorFilteredBeltIterator(Belt belt, int start_position, Plate.Color color_filter) {
		this._belt = belt;
		this._start = start_position;
		this._color = color_filter;
		
		for (int i = 0; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) != null) {
				if (belt.getPlateAtPosition(i).getColor() == color_filter) {
					this.maxplates.add(belt.getPlateAtPosition(i));
					this.maxposition.add(i);
				}
			}
		}
		
	}
	
	@Override
	public boolean hasNext() {
		if (this.maxplates.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Plate next() {
		if (this.maxplates.size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		Plate newPlate = this._belt.getPlateAtPosition(this._start);
		if (check(newPlate)) {
			this._start++;
			return next();
		} else {
			if (newPlate.getColor() == this._color) {
				this._start++;
				return newPlate;
			}
		}
		this._start++;
		return next();
	}
	
	public boolean check(Plate plate) {
		if (plate == null) {
			return true;
		} else {
			return false;
		}
	}
}
