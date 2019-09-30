package a7;

import java.util.ArrayList;
import java.util.List;

import comp401sushi.Plate;


public class BeltImpl implements Belt {

	private Plate[] _belt;
	private List<BeltObserver> observers = new ArrayList<BeltObserver>();
	private Customer[] customers;
	
	public BeltImpl(int belt_size) {
		if (belt_size < 1) {
			throw new IllegalArgumentException("Illegal belt size");
		}
		
		_belt = new Plate[belt_size];
		customers = new Customer[belt_size];
	}

	@Override
	public int getSize() {
		return _belt.length;
	}

	@Override
	public Plate getPlateAtPosition(int position) {
		position = normalize_position(position);

		return _belt[normalize_position(position)];
	}

	@Override
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == null) {
			throw new IllegalArgumentException();
		}

		position = normalize_position(position);

		if (getPlateAtPosition(position) != null) {
			throw new BeltPlateException(position, plate, this);
		}
		PlateEvent plates = new PlateEvent(PlateEvent.EventType.PLATE_PLACED, plate, position);
		handlePlateEvent(plates);
		_belt[position] = plate;
		
	}


	@Override
	public void clearPlateAtPosition(int position) {
		position = normalize_position(position);
		PlateEvent plates = new PlateEvent(PlateEvent.EventType.PLATE_REMOVED, _belt[position], position);
        handlePlateEvent(plates);
		_belt[position] = null;		
	}

	private int normalize_position(int position) {
		int size = getSize();
		return (((position % size) + size) % size);
	}
	
	
	@Override
	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {
		int offset = 0;
		position = normalize_position(position);

		while (offset < getSize()) {
			try {
				setPlateAtPosition(plate, position+offset);

				return normalize_position(position+offset);
			}
			catch (BeltPlateException e) {
				offset += 1;
			}
		}
		throw new BeltFullException(this);
	}

	@Override
	public void rotate() {
		Plate last_plate = _belt[getSize()-1];
		
		for (int i=getSize()-1; i>0; i--) {
			_belt[i] = _belt[i-1];
		}
		_belt[0] = last_plate;
		
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                customers[i].observePlateOnBelt(this, _belt[i], i);
            }
        }
	}

	@Override
	public void handlePlateEvent(PlateEvent e) {
		 for (BeltObserver b : observers) {
	            b.handlePlateEvent(e);
	        }
	}

	@Override
	public void addBeltObserver(BeltObserver o) {
		observers.add(o);
		
	}

	@Override
	public void removeBeltObserver(BeltObserver o) {
		observers.remove(o);
		
	}

	@Override
	public void registerCustomerAtPosition(Customer c, int position) {
        int position1 = normalize_position(position);

        if (c == null) {
            throw new IllegalArgumentException();
        }

        if (customers[position1] != null) {
            throw new RuntimeException();
        }

        for (int i = 0; i < customers.length; i++) {
            if (c == customers[i]) {
                throw new RuntimeException();
            }
        }

        customers[position1] = c;
    }

	@Override
	public Customer unregisterCustomerAtPosition(int position) {
        Customer c = customers[normalize_position(position)];
        customers[normalize_position(position)] = null;
        return c;
	}
	
	
}
