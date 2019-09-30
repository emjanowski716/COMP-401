package a6;

public interface Plate {

	// for descriptions of the methods look at PlateImpl 
	
	public enum Color {RED, GREEN, BLUE, GOLD};
	Sushi getContents(); 
	Sushi removeContents(); 
	void setContents(Sushi s) throws PlatePriceException; 
	boolean hasContents(); 
	double getPrice(); 
	Plate.Color getColor(); 
	double getProfit();
	
}
