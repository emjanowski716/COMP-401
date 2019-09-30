package a7;

import comp401sushi.Plate;

public class PlateCounter implements BeltObserver{

	private Belt b;
    private int red;
    private int blue;
    private int green;
    private int gold;

	
	public PlateCounter(Belt b) {
		if (b == null) {
			throw new IllegalArgumentException("no");
		} else {
			this.b = b;
		}
		
        b.addBeltObserver(this);

        for (int i = 0; i < b.getSize(); i++) {
            if (b.getPlateAtPosition(i) != null) {
                if (b.getPlateAtPosition(i).getColor().equals(Plate.Color.RED)) {
                    red += 1;
                } else if (b.getPlateAtPosition(i).getColor().equals(Plate.Color.BLUE)) {
                    blue += 1;
                } else if (b.getPlateAtPosition(i).getColor().equals(Plate.Color.GREEN)) {
                    green += 1;
                } else if (b.getPlateAtPosition(i).getColor().equals(Plate.Color.GOLD)) {
                    gold += 1;
                }
            }

        }
	}
	@Override
	public void handlePlateEvent(PlateEvent e) {
        switch (e.getType()) {
        case PLATE_PLACED:
            if (e.getPlate().getColor().equals(Plate.Color.RED)) {
                red++;
            } else if (e.getPlate().getColor().equals(Plate.Color.BLUE)) {
                blue++;
            } else if (e.getPlate().getColor().equals(Plate.Color.GREEN)) {
                green++;
            } else if (e.getPlate().getColor().equals(Plate.Color.GOLD)) {
                gold++;
            }
            break;

        case PLATE_REMOVED:
            if (e.getPlate().getColor().equals(Plate.Color.RED)) {
                red--;
            } else if (e.getPlate().getColor().equals(Plate.Color.BLUE)) {
                blue--;
            } else if (e.getPlate().getColor().equals(Plate.Color.GREEN)) {
                green--;
            } else if (e.getPlate().getColor().equals(Plate.Color.GOLD)) {
                gold--;
            }
            break;
        }
		
	}
	
	public int getRedPlateCount() {
		return red;
	}
	public int getBluePlateCount() {
		return blue;
	}
	public int getGreenPlateCount() {
		return green;
	}
	public int getGoldPlateCount() {
		return gold;
	}
	

}
