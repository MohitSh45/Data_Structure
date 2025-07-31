package com.exercise3.mohit.sharma;

/**
 * An implementation of a circularly linked list.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class CircularlyLinkedList<E> {
  //---------------- nested Node class ----------------
  /**
   * Singly linked node, which stores a reference to its element and
   * to the subsequent node in the list.
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;     // an element stored at this node

    /** A reference to the subsequent node in the list */
    private Node<E> next;  // a reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the CircularlyLinkedList
  /** The designated cursor of the list */
  private Node<E> tail = null;                  // we store tail (but not head)

  /** Number of nodes in the list */
  private int size = 0;                         // number of nodes in the list

  /** Constructs an initially empty list. */
  public CircularlyLinkedList() { }             // constructs an initially empty list

  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return tail.getNext().getElement();         // the head is after the tail
  }

  /**
   * Returns (but does not remove) the last element of the list
   * @return element at the back of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Rotate the first element to the back of the list.
   */
  public void rotate() {         // rotate the first element to the back of the list
    if (tail != null)                // if empty, do nothing
      tail = tail.getNext();         // the old head becomes the new tail
  }

  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) {                // adds element e to the front of the list
    if (size == 0) {
      tail = new Node<>(e, null);
      tail.setNext(tail);                     // link to itself circularly
    } else {
      Node<E> newest = new Node<>(e, tail.getNext());
      tail.setNext(newest);
    }
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) { // adds element e to the end of the list
    addFirst(e);             // insert new element at front of list
    tail = tail.getNext();   // now new element becomes the tail
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {                   // removes and returns the first element
    if (isEmpty()) return null;              // nothing to remove
    Node<E> head = tail.getNext();
    if (head == tail) tail = null;           // must be the only node left
    else tail.setNext(head.getNext());       // removes "head" from the list
    size--;
    return head.getElement();
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    if (tail == null) return "()";
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = tail;
    do {
      walk = walk.getNext();
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
    } while (walk != tail);
    sb.append(")");
    return sb.toString();
  }

  /**
   * Returns true if two circularly linked lists store the same sequence of elements
   * (potentially starting at different points).
   */
  public boolean equals(CircularlyLinkedList<E> other) {
    if (this.size != other.size) return false; // Lists must have the same size.

    Node<E> current = this.tail.getNext();  // Start at the first node of this list.
    for (int i = 0; i < this.size; i++) {
      Node<E> otherNode = other.tail.getNext(); // Start at the first node of the other list.
      
      // Try all rotations of the other list
      for (int j = 0; j < other.size; j++) {
        if (current.getElement().equals(otherNode.getElement())) {
          // If elements match, compare the rest of the list.
          Node<E> tempCurrent = current;
          Node<E> tempOtherNode = otherNode;
          boolean match = true;
          
          for (int k = 1; k < this.size; k++) {
            tempCurrent = tempCurrent.getNext();
            tempOtherNode = tempOtherNode.getNext();
            if (!tempCurrent.getElement().equals(tempOtherNode.getElement())) {
              match = false;
              break;
            }
          }
          
          if (match) return true; // The lists are identical at this rotation.
        }
        otherNode = otherNode.getNext();
      }
      current = current.getNext();
    }
    return false; // No matching sequences found.
  }
  
// main method
  public static void main(String[] args)
  {
    // Create two circularly linked lists with the same elements, in different orders.
    CircularlyLinkedList<String> L1 = new CircularlyLinkedList<String>();
    CircularlyLinkedList<String> L2 = new CircularlyLinkedList<String>();
    
    L1.addFirst("LAX");
    L1.addLast("MSP");
    L1.addLast("ATL");
    L1.addLast("BOS");

    L2.addFirst("ATL");
    L2.addLast("BOS");
    L2.addLast("LAX");
    L2.addLast("MSP");

    System.out.println("List 1: " + L1);
    System.out.println("List 2: " + L2);
    System.out.println("Are both lists equal or not? " + L1.equals(L2)); // Should return true.
  }
}