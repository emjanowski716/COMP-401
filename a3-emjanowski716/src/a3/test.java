package a3;

public class test {

	public static void main(String[] args) {
		IngredientPortion[] list = new IngredientPortion[3];
		Ingredient shrimp = new IngredientImpl("shrimp", 5.678, 4, true);
		Ingredient rice = new IngredientImpl("rice", 5.982, 4, true);
		Ingredient seaweed = new IngredientImpl("rice", 5, 4, true);
		
		list[0] = new IngredientPortionImpl(shrimp, 2);
		list[1] = new IngredientPortionImpl(rice, 2);
		list[2] = new IngredientPortionImpl(seaweed, 2);
		
		MenuItem item = new MenuItemImpl("item", list);
		
		System.out.println(item.getCost());
		
		

	}

}
