package com.exercise3.mohit.sharma;

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

import java.util.NoSuchElementException;

/**
 * A basic singly linked list implementation.
 * 
 * @param <E> the type of elements held in this collection
 */
class SinglyLinkedList<E> implements Cloneable {
  //---------------- nested Node class ----------------
  /**
   * Node of a singly linked list, which stores a reference to its
   * element and to the subsequent node in the list (or null if this
   * is the last node).
   */
  private static class Node<E> {
    private E element;   // The element stored at this node
    private Node<E> next; // Reference to the subsequent node in the list

    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    public E getElement() { return element; }
    public Node<E> getNext() { return next; }
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the SinglyLinkedList
  private Node<E> head = null;  // head node of the list (or null if empty)
  private Node<E> tail = null;  // last node of the list (or null if empty)
  private int size = 0;         // number of nodes in the list

  public SinglyLinkedList() { } // constructs an initially empty list

  public int size() { return size; }
  public boolean isEmpty() { return size == 0; }

  public E first() { // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return head.getElement();
  }

  public E last() { // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  public void addFirst(E e) { // adds element e to the front of the list
    head = new Node<>(e, head);
    if (size == 0)
      tail = head;
    size++;
  }

  public void addLast(E e) { // adds element e to the end of the list
    Node<E> newest = new Node<>(e, null);
    if (isEmpty())
      head = newest;
    else
      tail.setNext(newest);
    tail = newest;
    size++;
  }

  public E removeFirst() { // removes and returns the first element
    if (isEmpty()) return null;
    E answer = head.getElement();
    head = head.getNext();
    size--;
    if (size == 0)
      tail = null;
    return answer;
  }

  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) o;
    if (size != other.size) return false;
    Node<E> walkA = head;
    Node<E> walkB = other.head;
    while (walkA != null) {
      if (!walkA.getElement().equals(walkB.getElement())) return false;
      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
    if (size > 0) {
      other.head = new Node<>(head.getElement(), null);
      Node<E> walk = head.getNext();
      Node<E> otherTail = other.head;
      while (walk != null) {
        Node<E> newest = new Node<>(walk.getElement(), null);
        otherTail.setNext(newest);
        otherTail = newest;
        walk = walk.getNext();
      }
    }
    return other;
  }

  public int hashCode() {
    int h = 0;
    for (Node<E> walk = head; walk != null; walk = walk.getNext()) {
      h ^= walk.getElement().hashCode();
      h = (h << 5) | (h >>> 27);
    }
    return h;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * Concatenates another singly linked list to the end of this list.
   * The other list becomes empty after concatenation.
   *
   * @param other the other singly linked list to concatenate
   */
  public void concatenate(SinglyLinkedList<E> other) {
    if (other.isEmpty()) return;
    if (this.isEmpty()) {
      this.head = other.head;
      this.tail = other.tail;
    } else {
      this.tail.setNext(other.head);
      this.tail = other.tail;
    }
    this.size += other.size;
    other.head = null;
    other.tail = null;
    other.size = 0;
  }
}

public class LinkedQueue<E> {
  private SinglyLinkedList<E> list; // Singly linked list to store queue elements

  public LinkedQueue() {
    list = new SinglyLinkedList<>();
  }

  /**
   * Adds an element to the end of the queue.
   *
   * @param element the element to be added to the queue
   */
  public void enqueue(E element) {
    list.addLast(element);
  }

  /**
   * Removes and returns the first element of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public E dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return list.removeFirst();
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return list.isEmpty();
  }

  /**
   * Concatenates another queue to the end of this queue.
   * The other queue becomes empty after concatenation.
   *
   * @param otherQueue the other queue to concatenate
   */
  public void concatenate(LinkedQueue<E> otherQueue) {
    list.concatenate(otherQueue.list);
  }

  /**
   * Main method to test the concatenate method.
   *
   * @param args command line arguments
   */

  public static void main(String[] args) {
    LinkedQueue<Integer> queue1 = new LinkedQueue<>();
    LinkedQueue<Integer> queue2 = new LinkedQueue<>();

    // Add elements to queue1
    queue1.enqueue(1);
    queue1.enqueue(2);
    queue1.enqueue(3);

    // Add elements to queue2
    queue2.enqueue(4);
    queue2.enqueue(5);
    queue2.enqueue(6);

    // Concatenate queue2 to queue1
    queue1.concatenate(queue2);

    // queue2 should now be empty
    System.out.println("Is queue2 empty? " + queue2.isEmpty()); // Should print true

    // Print elements of queue1 after concatenation
    System.out.print("Elements of queue1 after concatenation: ");
    while (!queue1.isEmpty()) {
      System.out.print(queue1.dequeue() + " ");
    }
    System.out.println();
  }
}

