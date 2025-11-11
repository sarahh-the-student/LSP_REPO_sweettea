package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements a set of integers, a collection that holds no duplicate elements.
 * This implementation uses an {@link java.util.ArrayList} as the underlying
 * data structure.
 * * @author [Your Name Here] 
 */
public class IntegerSet {
    // The internal list to store the set elements.
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Default constructor.
     */
    public IntegerSet() {
    }

    /**
     * Clears the internal representation of the set, removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * * @return the int number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the 2 sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in ANY order.
     * This overrides the equals method from the Object class.
     * * @param o the object to be compared for equality with this set
     * @return true if the specified object is equal to this set
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IntegerSet otherSet = (IntegerSet) o;

        // If sizes are different, they can't be equal
        if (this.length() != otherSet.length()) {
            return false;
        }

        // Since we enforce no duplicates, if sizes are equal and
        // this set contains all elements of the other set, they are equal.
        return this.set.containsAll(otherSet.set);
    }

    /**
     * Returns true if the set contains the specified value, otherwise false.
     * * @param value the value to check for presence in the set
     * @return true if the set contains the value
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * * @return the int largest value
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot find largest element in an empty set.");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest item in the set.
     * * @return the int smallest value
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot find smallest element in an empty set.");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set. If the item is already present, the set
     * remains unchanged (duplicates are not allowed).
     * * @param item the int item to add
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set. If the item is not present, the set
     * remains unchanged.
     * * @param item the int item to remove
     */
    public void remove(int item) {
        // Use Integer.valueOf(item) to call remove(Object) instead of remove(index)
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union (this U other). Modifies this set to contain all unique
     * elements that are present in this set, the other set, or both.
     * * @param other the IntegerSet to perform union with
     */
    public void union(IntegerSet other) {
        for (int item : other.set) {
            this.add(item); // The add method already handles duplicate checks
        }
    }

    /**
     * Set intersection (this ∩ other). Modifies this set to contain only
     * elements that are present in both this set and the other set.
     * * @param other the IntegerSet to perform intersection with
     */
    public void intersect(IntegerSet other) {
        // RetainAll keeps only the elements that are also in the other set
        set.retainAll(other.set);
    }

    /**
     * Set difference (this \ other). Modifies this set to remove all
     * elements that are found in the other set.
     * * @param other the IntegerSet to perform difference with
     */
    public void diff(IntegerSet other) {
        // RemoveAll removes elements that are also in the other set
        set.removeAll(other.set);
    }

    /**
     * Set complement (other \ this). Modifies this set to become the result
     * of the difference of the other set and this set.
     * * @param other the IntegerSet to use as the base for the complement
     */
    public void complement(IntegerSet other) {
        // Create a temporary list of elements in 'other'
        List<Integer> tempOther = new ArrayList<>(other.set);
        
        // Remove elements that are also in 'this' (original state)
        tempOther.removeAll(this.set);
        
        // Set 'this' set to the resulting complement
        this.set = tempOther;
    }

    /**
     * Returns true if the set contains no elements, false otherwise.
     * * @return true if the set is empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a String representation of the set.
     * The format is a comma-separated list of elements enclosed in
     * square brackets, e.g., [1, 2, 3].
     * * @return the String representation of the set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}