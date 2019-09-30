package a6;

/*
 * a child class of parent class IngredientImpl
 * creates a rice ingredient object
*/

public class Rice extends IngredientImpl {

	public Rice() {
		super("rice", 0.13, 34, true, true, false);
	}
	
}
