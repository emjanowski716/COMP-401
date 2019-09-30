package a6;

/*
 * a child class of parent class IngredientImpl
 * creates an avocado ingredient object
*/

public class Avocado extends IngredientImpl {
	
	public Avocado() {
		super("avocado", 0.24, 42, true, false, false);
	}

}
