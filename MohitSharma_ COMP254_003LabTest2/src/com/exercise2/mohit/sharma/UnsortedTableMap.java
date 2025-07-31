package com.exercise2.mohit.sharma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */
public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    /** Underlying storage for the map of entries. */
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /** Constructs an initially empty map. */
    public UnsortedTableMap() {
    }

    /** Returns the index of an entry with equal key, or -1 if none found. */
    private int findIndex(K key) {
        int n = table.size();
        for (int j = 0; j < n; j++)
            if (table.get(j).getKey().equals(key))
                return j;
        return -1; // special value denotes that key was not found
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1)
            return null;
        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else
            return table.get(j).setValue(value);
    }

    @Override
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if (j == -1)
            return null;
        V answer = table.get(j).getValue();
        if (j != n - 1)
            table.set(j, table.get(n - 1));
        table.remove(n - 1);
        return answer;
    }

    public boolean containsKey(K key) {
        return findIndex(key) != -1;
    }

    // ---------- New Method: removeOnlyIfNull ----------
    public V removeOnlyIfNull(K key) {
        int j = findIndex(key);
        if (j == -1) {
            System.out.println("Key not found: " + key);
            return null;
        }
        V value = table.get(j).getValue();
        if (value == null) {
            if (j != table.size() - 1)
                table.set(j, table.get(table.size() - 1));
            table.remove(table.size() - 1);
            System.out.println("Entry with key '" + key + "' was null and has been removed.");
            return null;
        } else {
            System.out.println("Value for key '" + key + "' is not null. No removal performed.");
            return value;
        }
    }

    // ---------- EntryIterator ----------
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if (j == table.size())
                throw new NoSuchElementException("No further entries");
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    // ---------- EntryIterable ----------
    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    // ---------- MAIN method ----------
    public static void main(String[] args) {
        // Instantiate UnsortedTableMap
        UnsortedTableMap<String, Integer> dataMap = new UnsortedTableMap<>();

        // Add entries
        dataMap.put("apple", null);
        dataMap.put("banana", 10);
        dataMap.put("cherry", null);

        // Test removeOnlyIfNull
        dataMap.removeOnlyIfNull("apple");    // Should remove
        dataMap.removeOnlyIfNull("banana");   // Should not remove (value is not null)
        dataMap.removeOnlyIfNull("cherry");   // Should remove
        dataMap.removeOnlyIfNull("durian");   // Key not found

        // Final map entries
        System.out.println("\nRemaining entries in the map:");
        for (Entry<String, Integer> e : dataMap.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
}
