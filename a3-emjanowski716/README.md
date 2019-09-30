# Our First Objects

In this assignment, you will create a few simple classes that implement interfaces that I specify. By themselves, these classes will not constitute a program that does anything specific. In order to test your classes, you will have to develop your own testing programs.  Another thing you can do is to use these classes to reimplement your A2 programs (in fact the Jedi portion below is exactly this).

NOTE: all of your code should be in a package called a3. You'll notice that this starter repository includes a "dummy" class in the a3 package. This dummy class was included just to make sure that the appropriate directory and package structure was created when you create your Eclipse project using this repo. You can ignore it or even delete it.

## Novice

Create an interface called ```Ingredient```. This interface should declare the following methods:

```
String getName()
boolean getIsVegetarian()
double getPricePerOunce()
int getCaloriesPerOunce()
double getCaloriesPerDollar()
```

These methods all do what their names suggest and are simply getters for the properties of an ```Ingredient```.

Now create a class that implements ```Ingredient``` called ```IngredientImpl```. 
The class should provide a constructor declared like this:

```
public IngredientImpl(String name, double price, int calories, boolean is_vegetarian)
```

If any of the arguments passed to the constructor are illegal, your constructor should "throw a RuntimeException". 
To do so, use a line of code that looks like this:

```
throw new RuntimeException("Explanation string");
```

where "Explanation string" should be a message that explains why this runtime exception has occurred.
You should check to make sure that name is not null and that price and calories are not negative.

A few things to remember:

* Be sure to include "implements Ingredient" in the class declaration.
* Declare appropriate instance fields to encapsulate the information passed to the constructor.
* Make all of your instance fields private.
* Be sure you declare the methods that implement the interface as public.

That's it for Novice. But you should be sure to write additional code to test your implementation. Also, you could write a program that does the same thing as A2 Novice but now using Ingredient and IngredientImpl in your solution.

## Adept

Create an interface called ```IngredientPortion```. This interface is an abstraction for a specific portion of an ingredient (for example, 0.42 ounces of Seaweed). It should have the following methods:

```
Ingredient getIngredient()
double getAmount()
String getName()
boolean getIsVegetarian()
double getCalories()
double getCost()
IngredientPortion combine(IngredientPortion other)
```

```getIngredient``` returns a reference to the Ingredient object that this is a portion of and ```getAmount``` should return the weight of the portion in ounces. The methods ```getName``` and ```getIsVegetarian``` returns the name and vegetarian status of the ingredient. The methods ```getCalories``` and ```getCost``` return the number of calories in this portion and the cost of the portion. These should be calculated by multiplying the amount of the portion with the calories/ounce and cost/ounce values associated with the ingredient. Do not round these values.

The method ```combine``` accepts an ```IngredientPortion``` parameter named *other*. The result of the method should be a new ```IngredientPortion``` object that is the combined weight of the current (i.e., *this*) object and *other*. If *other* is null, then the method should just return the current object. This kind of makes sense if you think about it since if you combine something with nothing, you just get what you started with. If *other* is a portion of a different ingredient, then a ```RuntimeException``` should be thrown.

Create a class called ```IngredientPortionImpl``` that implements the ```IngredientPortion``` interface. Your class should have the following constructor:

```
public IngredientPortionImpl(Ingredient ing, double amount)
```

Your constructor to check to make sure *ing* is not null and that *amount* is positive, throwing a RuntimeException if these constraints are not met.

A few hints:

* Be sure to include "implements IngredientPortion" in the class declaration.
* Encapsulate the values provided to the constructor in private instance fields.
* You don't need instance fields for name and vegetarian information. You can simply use the reference to the ingredient object you encapsulated to retrieve that information as needed.
* Similarly you don't need to store the calories and cost information in fields. You can calculate these derived properties when needed.
* The ```combine``` method should first check to see if *other* is null first and do the appropriate thing if so. Then you should check to see if *other* and the current object are portions of the same ```Ingredient```. If you don't check for null first, when you try to retrieve the ingredient associated with other, you'll cause a NullPointerException to be thrown.

Now create an interface called ```MenuItem```. This interface represents a menu item defined by a list of ingredient portions. It should have the following methods:

```
String getName()
IngredientPortion[] getIngredients()
int getCalories()
double getCost()
boolean getIsVegetarian()
```

```getName``` returns the menu item's name. ```getIngredients``` returns an array of ```IngredientPortion``` objects that represent the amounts of the various ingredients that go into the menu item. ```getCalories``` returns the sum of the calorie information from the menu item's ingredients rounded to the nearest integer. Similarly ```getCost``` returns the sum of the cost information rounded to the nearest cent. ```getIsVegetarian``` should return true if all of the ingredients in the menu item are vegetarian and false otherwise.

Create a class called ```MenuItemImpl``` that implements the ```MenuItem``` interface. Your class should have the following constructor:

```
public MenuItemImpl(String name, IngredientPortion[] ingredients)
```

Again, be sure to validate the parameter values. In this case, *name* should not be null, *ingredients* should not be null and have a length greater than zero. You also should check to ensure that none of the elements in *ingredients* are null.

A few hints (beyond the basic ones from adept and novice that I won't repeat):

* Be sure to clone the array of ingredient portions passed to the constructor so that you encapsulate a copy of the array rather than the original array. If you don't, then you can't prevent the elements from being changed by whatever code provided the array to the constructor.
* Similarly, don't return your encapsulated ingredient portion array directly as the result of ```getIngredients``` for the same reason. Be sure to return a clone.

That's it for Adept. Again, you'll need to write your own code to test these implementations.

## Jedi

Create a class called ```A3Jedi``` with a main method. Use the interfaces and classes you've developed to create a program that processes input as described in A2 Jedi.

### Submission Notes

In order to test your classes, you'll have to create little programs as the main method in other classes that you make up that use them in ways that test their functionality. It's OK to have these other classes in the repository you push to GitHub which you then tell Gradescope to grade. But if there are any compilation errors in any of these classes, it might cause problems. Either make sure that there are no compilation errors in any of your classes that you are turning in or create a branch that only has the interfaces and classes that are asked for by the assignment without any of the other classes you might have created to test your code.
