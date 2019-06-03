package org.cytoscape.view.model.spacial;

import org.cytoscape.view.model.CyNetworkViewSnapshot;

/**
 * Stores the location and rectangular boundary of objects 
 * (typically <code>View&lt;CyNode&gt;</code> objects)
 * in 2D space. Coordinates are stored as floats.
 *
 * @param <K> The key type.
 */
public interface SpacialIndex2D<K> {

	/**
	 * Constants for indexing into extents arrays.
	 */
	final int X_MIN = 0, Y_MIN = 1, X_MAX = 2, Y_MAX = 3;
	
	/**
	 * Returns the "Minimum Bounding Rectangle", the smallest rectangle that contains all the 
	 * objects stored in this SpacialIndex2D.
	 * 
	 * @param extents The first 4 elements of this array will be overwritten to contain the extents of the minimum bounding rectangle.
	 * @throws ArrayIndexOutOfBoundsException if extents.length is less than 4
	 * @throws NullPointerException if extents is null 
	 */
	void getMBR(float[] extents);
	
	/**
	 * Returns the "Minimum Bounding Rectangle", the smallest rectangle that contains all the 
	 * objects stored in this SpacialIndex2D.
	 * 
	 * This data structure stores floats, this is just a convenience method that takes a double[] instead of a float[].
	 * 
	 * @param extents The first 4 elements of this array will be overwritten to contain the extents of the minimum bounding rectangle.
	 * @throws ArrayIndexOutOfBoundsException if extents.length is less than 4
	 * @throws NullPointerException if extents is null 
	 */
	void getMBR(double[] extents);

	/**
	 * Returns true if an object with the given key is stored in this SpacialIndex2D.
	 */
	boolean exists(K key);
	
	/**
	 * Retrieves the extents of the rectangle with the given key.
	 * 
	 * @param extents The first 4 elements of this array will be overwritten to contain the extents of the minimum bounding rectangle.
	 * @throws ArrayIndexOutOfBoundsException if extents.length is less than 4
	 * @throws NullPointerException if extents is null 
	 */
	boolean get(K key, float[] extents);
	
	/**
	 * Retrieves the extents of the rectangle with the given key.
	 * This data structure stores floats, this is just a convenience method that takes a double[] instead of a float[].
	 * 
	 * @param extents The first 4 elements of this array will be overwritten to contain the extents of the minimum bounding rectangle.
	 * @throws ArrayIndexOutOfBoundsException if extents.length is less than 4
	 * @throws NullPointerException if extents is null 
	 */
	boolean get(K key, double[] extents);
	
	/**
	 * Returns the number of objects stored in this SpacialIndex2D.
	 */
	int size();
	
	/**
	 * Returns an enumerator of all the rectangular objects that are 
	 * contained within or intersecting the given rectangle. 
	 */
	SpacialIndex2DEnumerator<K> queryOverlap(float xMin, float yMin, float xMax, float yMax);
	
	/**
	 * Returns an enumerator of all the objects stored in this SpacialIndex2D.
	 */
	SpacialIndex2DEnumerator<K> queryAll();
	
	/**
	 * Adds a new rectangular object with the given key to this SpacialIndex2D. 
	 * <br><br>
	 * Note: The SpacialIndex2D returned by {@link CyNetworkViewSnapshot#getSpacialIndex2D()} is immutable
	 * and does not support this method.
	 */
	void put(K key, float xMin, float yMin, float xMax, float yMax);
	
	/**
	 * Deletes the new rectangular object with the given key from this SpacialIndex2D. If this
	 * SpacialIndex2D does not contain an object with the given key then nothing happens.
	 * <br><br>
	 * Note: The SpacialIndex2D returned by {@link CyNetworkViewSnapshot#getSpacialIndex2D()} is immutable
	 * and does not support this method.
	 */
	void delete(K key);
	
}
