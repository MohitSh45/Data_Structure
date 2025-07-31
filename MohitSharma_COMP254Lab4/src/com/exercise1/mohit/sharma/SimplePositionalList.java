package com.exercise1.mohit.sharma;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A concrete implementation of the PositionalList interface.
 * 
 * @param <E> the type of elements stored in the list
 */
public class SimplePositionalList<E> implements PositionalList<E> {
    private List<Position<E>> list = new LinkedList<>();

    // Inner class for position
    private class SimplePosition implements Position<E> {
        private E element;
        SimplePosition(E e) { this.element = e; }
        public E getElement() { return element; }
    }

    public int size() { return list.size(); }
    public boolean isEmpty() { return list.isEmpty(); }
    public Position<E> first() { return isEmpty() ? null : list.get(0); }
    public Position<E> last() { return isEmpty() ? null : list.get(size() - 1); }
    
    public Position<E> before(Position<E> p) {
        int index = list.indexOf(p);
        return (index > 0) ? list.get(index - 1) : null;
    }

    public Position<E> after(Position<E> p) {
        int index = list.indexOf(p);
        return (index >= 0 && index < list.size() - 1) ? list.get(index + 1) : null;
    }

    public Position<E> addFirst(E e) {
        Position<E> p = new SimplePosition(e);
        list.add(0, p);
        return p;
    }

    public Position<E> addLast(E e) {
        Position<E> p = new SimplePosition(e);
        list.add(p);
        return p;
    }

    public Position<E> addBefore(Position<E> p, E e) {
        int index = list.indexOf(p);
        if (index == -1) throw new IllegalArgumentException("Invalid position");
        Position<E> newPos = new SimplePosition(e);
        list.add(index, newPos);
        return newPos;
    }

    public Position<E> addAfter(Position<E> p, E e) {
        int index = list.indexOf(p);
        if (index == -1) throw new IllegalArgumentException("Invalid position");
        Position<E> newPos = new SimplePosition(e);
        list.add(index + 1, newPos);
        return newPos;
    }

    public E set(Position<E> p, E e) {
        SimplePosition pos = (SimplePosition) p;
        E old = pos.getElement();
        pos.element = e;
        return old;
    }

    public E remove(Position<E> p) {
        if (!list.contains(p)) throw new IllegalArgumentException("Invalid position");
        list.remove(p);
        return p.getElement();
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Iterator<Position<E>> it = list.iterator();
            public boolean hasNext() { return it.hasNext(); }
            public E next() { return it.next().getElement(); }
        };
    }

    public Iterable<Position<E>> positions() { return list; }


    public static void main(String[] args) {
        PositionalList<String> list = new SimplePositionalList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");

        Position<String> pos = list.findPosition("A");
        System.out.println(pos != null ? "Found: " + pos.getElement() : "Not Found");

        Position<String> pos2 = list.findPosition("Z");
        System.out.println(pos2 != null ? "Found: " + pos2.getElement() : "Not Found");
    }
}

