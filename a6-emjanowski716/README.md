# Assignment 6

In this assignment, you'll implement an object that acts as a circular "Belt" of plates in our sushi restaurant. To complete this assignment, you will need to have gotten through A5. 

### Novice

Carefully read the comments documenting the Belt interface and BeltPlateException class in the a6 package.

Copy all interfaces and classes from the a5 package of your A5 solution into the a6 package in this repository. 

Create an implementation of the Belt interface called BeltImpl. The constructor for BeltImpl should have the following declaration:

```
public BeltImpl(int belt_size)
```

The size of the belt (i.e., the number of positions it has) is given by ```belt_size```. Be sure BeltImpl functions as described in the Belt interface comments.

Hint: to translate a position value greater than size-1 to its corresponding position between 0 and size-1, use the mod operator % as follows:
```
normalized_position = ((position % size) + size) % size;
```
In this formula, position is any value (negative or positive), size is the size of the belt, and normalized_position is the correct coresponding value between 0 and size-1 for the value of position whatever it might be.

### Adept

Add the following method to your Belt interface and your BeltImpl class:

```
public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException
```

This methods sets the provided plate at the specified position if possible. If not possible because the position is already occupied, attempts to set the plate at the next highest position. This continues until either the plate is successfully placed on the belt or all of the positions on the belt have been found to be occupied (remember as the value of position gets higher it will eventually wrap back around to the beginning of the belt). In the case that the belt is full and the plate can not be placed, throw a BeltFullException (described below). If successful, this method should return the position index where the plate ended up. This value should be in the range of 0 to size-1 where size is the size of the belt.

Create a BeltFullException class to support the setPlateNearestToPosition method. This class should be a checked exception. It should have the following constructor:

```
public BeltFullException(Belt belt)
```

A BeltFullException should encapsulate the reference to the belt object provided to the constructor and provide the following getter method:

```
public Belt getBelt()
```

Create a BeltIterator class that implements the Iterator design pattern for the plates on a belt as described below.

The BeltIterator class should implement the interface Iterator<Plate>. You will need to import Iterator from the java.util package. A BeltIterator will be used to iterate over all of the plates on a belt starting a particular position. When the end of the belt is reached, the iterator should go back to the beginning of the belt (remember, our belt is being treated as if it were circular). This means that as long as there is at least one plate on the belt, the iterator will never run out of plates to iterate over since it will keep going back to the beginning.

The BeltIterator should have the following constructor:

```
public BeltIterator(Belt belt, int start_position)
```

The parameter "belt" indicates which belt object is to be iterated over. The parameter "start_position" indicates which position index to start with. Any value should be valid for start_position as per the above description of circular indexing.

BeltIterator should implement the following methods:

* public boolean hasNext()

  Indicates that there is a next plate object to be iterated. This method should only return false if the belt is completely empty.

* public Plate next()

  Retrieve the next plate from the belt. Note that this should not remove the plate from the belt. If next is called on an empty belt, should throw a java.util.NoSuchElementException.

* public void remove()

  Always throws an UnsupportedOperationException.

Fourth, alter the Belt interface (and your implementation BeltImpl) in the following ways:

Declare Belt an extension of Iterable<Plate>.

Add the following two methods to your Belt interface (and your implementation BeltImpl):

* public Iterator<Plate> iterator()
  
  Returns a BeltIterator object for this belt starting at position 0.

* public Iterator<Plate> iteratorFromPosition(int position)
  
  Returns a BeltIterator object for this belt starting at the specified position.

Finally, add the following method to the Belt interface (and your implementation BeltImpl):

* public void rotate()
  
  This method "rotates" the belt such that a Plate object set at position p before the rotate method, is now found at position p+1 after the rotate method. Note that while one way to do this is to actually move the plates on the belt to their new locations, there is a much more efficient solution that does not require moving the plate objects at all. Either approach is fine and if you aren't sure how you would do it without moving the plates, I would suggest first doing it that way, get it working and turned in, and then seeing if you can figure out the more sophisticated and efficient way that does not require actually moving the plates.


NOTE: the rotate functionality is not part of or related to the iterator/iterable functionality. In fact, calling rotate on a belt object could cause an existing belt iterator to skip plates.

### Jedi

First, alter your BeltIterator class to support the remove operation as described in the official Java documentation for Iterator found at the link below. In this context, the remove method should remove the plate last retrieved by this iterator (i.e., whatever was returned from the last call to ```next()```) from the belt. Calling remove() before next() has ever been called or calling remove() a second time before calling next() again should result in throwing an IllegalStateException.

http://docs.oracle.com/javase/10/docs/api/java/util/Iterator.html

Finally, create two new implementations of Iterator<Plate> called PriceThresholdBeltIterator and ColorFilteredBeltIterator that support the ability to iterate only over plates on a belt that match either a maximum price criteria or are of a particular color. The constructors for these classes should be declared as follows:

* public PriceThresholdBeltIterator(Belt belt, int start_position, double max_price)
  
  Creates a belt iterator that iterates over plates with a price less than or equal to the specified max_price starting at the position specified as start_position.

* public ColorFilteredBeltIterator(Belt belt, int start_position, Plate.Color color_filter)
  
  Creates a belt iterator that iterates over plates matching the specified color_filter value starting at the position specified as start_position.

Note that these iterators may return false for hasNext even if the belt is not empty if there are no plates that satisfy the criteria on the belt.

Finally overload the iterator and iteratorFromPosition methods in your Belt interface and your implementation BeltImpl class so that it can be used to create these new kinds of iterators for the belt. The overloaded versions should have the following signatures:

```
public Iterator<Plate> iterator(double max_price)
public Iterator<Plate> iterator(Plate.Color color)
public Iterator<Plate> iteratorFromPosition(int position, double max_price)
public Iterator<Plate> iteratorFromPosition(int position, Plate.Color color)
```
