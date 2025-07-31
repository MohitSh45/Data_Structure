/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.exercise3.mohit.sharma;


import java.util.Arrays;
import java.util.Comparator;

/**
 * An implementation of a priority queue with a sorted array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    /** Default initial capacity of the array */
    private static final int DEFAULT_CAPACITY = 10;

    /** Primary storage for the priority queue */
    private Entry<K, V>[] array;

    /** Number of elements in the queue */
    private int size;

    /** Creates an empty priority queue based on the natural ordering of its keys. */
    public SortedPriorityQueue() {
        super();
        array = new PQEntry[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     * @param comp comparator defining the order of keys in the priority queue
     */
    public SortedPriorityQueue(Comparator<K> comp) {
        super(comp);
        array = new PQEntry[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     * @param key     the key of the new entry
     * @param value   the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)
        if (size == array.length) resize(2 * array.length); // Ensure capacity

        Entry<K, V> newest = new PQEntry<>(key, value);
        int i = size - 1;

        // Shift elements to maintain sorted order
        while (i >= 0 && compare(newest, array[i]) < 0) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = newest;
        size++;

        return newest;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        if (isEmpty()) return null;
        return array[0]; // First element is always the smallest
    }

    /**
     * Removes and returns an entry with minimal key.
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) return null;
        Entry<K, V> minEntry = array[0];
        System.arraycopy(array, 1, array, 0, --size); // Shift left
        return minEntry;
    }

    /**
     * Returns the number of items in the priority queue.
     * @return number of items
     */
    @Override
    public int size() {
        return size;
    }

    /** 
     * Resizes the internal array to a new capacity.
     * @param newCapacity the new capacity of the array
     */
    private void resize(int newCapacity) {
        array = Arrays.copyOf(array, newCapacity);
    }

    /**
     * Main method to test the priority queue.
     */
    public static void main(String[] args) {
        SortedPriorityQueue<Integer, String> pq = new SortedPriorityQueue<>();

        // Inserting elements
        pq.insert(15, "Fifteen");
        pq.insert(3, "Three");
        pq.insert(9, "Nine");
        pq.insert(2, "Two");
        pq.insert(7, "Seven");

        // Displaying the minimum element
        Entry<Integer, String> minEntry = pq.min();
        System.out.println("Smallest element -> " + minEntry.getKey() + ": " + minEntry.getValue());

        // Removing the minimum element
        Entry<Integer, String> removedEntry = pq.removeMin();
        System.out.println("Removed -> " + removedEntry.getKey() + ": " + removedEntry.getValue());

        // Displaying the new minimum element
        minEntry = pq.min();
        System.out.println("Now the smallest element is -> " + minEntry.getKey() + ": " + minEntry.getValue());

        // Displaying all remaining elements in priority order
        System.out.println("Remaining elements:");
        while (!pq.isEmpty()) {
            Entry<Integer, String> entry = pq.removeMin();
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
    }
}
