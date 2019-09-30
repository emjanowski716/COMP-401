package a7;

public class ProfitCounter implements BeltObserver{

	private Belt b;
    private double profitNum = 0;
    private int plateNum = 0;
	
	public ProfitCounter(Belt b) {
		if (b == null) {
			throw new IllegalArgumentException("no");
		} else {
			this.b = b;
		}
		
        b.addBeltObserver(this);

        for (int i = 0; i < b.getSize(); i++) {
            if (b.getPlateAtPosition(i) != null) {
                plateNum++;
                profitNum += b.getPlateAtPosition(i).getProfit();

            }
        }
	}
	
	@Override
	public void handlePlateEvent(PlateEvent e) {
        switch (e.getType()) {
        case PLATE_PLACED:
            double placed = e.getPlate().getProfit();
            plateNum++;
            profitNum += placed;
            break;
        case PLATE_REMOVED:
            double removed = e.getPlate().getProfit();
            plateNum--;
            profitNum -= removed;
            break;
        }
		
	}
	
	public double getTotalBeltProfit() {
        double total = 0;

        for (int i = 0; i < b.getSize(); i++) {
            if (b.getPlateAtPosition(i) != null) {
                total += b.getPlateAtPosition(i).getProfit();
            }
        }

        return total;
	}
	
	public double getAverageBeltProfit() {
        int avg = 0;
        
        for (int i = 0; i < b.getSize(); i++) {
            if (b.getPlateAtPosition(i) != null) {
                avg++;
            }
        }

        if (avg == 0) {
            return 0.0;
        } else {
            return getTotalBeltProfit() / avg;
        }
	}

}
