// Package structure as per naming rules
package com.exercise1.mohit.sharma;

public class DoublyLinkedList<E> {
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }

    public E removeLast() {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(element, predecessor, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public void swapNodes(E data1, E data2) {
        if (data1.equals(data2)) return;

        Node<E> node1 = header.getNext();
        Node<E> node2 = header.getNext();

        while (node1 != trailer && !node1.getElement().equals(data1)) {
            node1 = node1.getNext();
        }

        while (node2 != trailer && !node2.getElement().equals(data2)) {
            node2 = node2.getNext();
        }

        if (node1 == trailer || node2 == trailer) return;

        Node<E> prev1 = node1.getPrev();
        Node<E> next1 = node1.getNext();
        Node<E> prev2 = node2.getPrev();
        Node<E> next2 = node2.getNext();

        if (node1.getNext() == node2) {
            prev1.setNext(node2);
            node2.setPrev(prev1);
            node2.setNext(node1);
            node1.setPrev(node2);
            node1.setNext(next2);
            next2.setPrev(node1);
        } else if (node2.getNext() == node1) {
            prev2.setNext(node1);
            node1.setPrev(prev2);
            node1.setNext(node2);
            node2.setPrev(node1);
            node2.setNext(next1);
            next1.setPrev(node2);
        } else {
            prev1.setNext(node2);
            node2.setPrev(prev1);
            node2.setNext(next1);
            next1.setPrev(node2);

            prev2.setNext(node1);
            node1.setPrev(prev2);
            node1.setNext(next2);
            next2.setPrev(node1);
        }
    }

    public void reverse() {
        if (isEmpty()) return; // No need to reverse an empty list

        Node<E> current = header.getNext(); // Start with the first actual node
        Node<E> temp = null;

        // Swap the `next` and `prev` pointers for all nodes
        while (current != null) {
            temp = current.getNext();
            current.setNext(current.getPrev());
            current.setPrev(temp);
            current = temp; // Move to the next node (originally "next")
        }

        // Swap header and trailer nodes
        temp = header;
        header = trailer;
        trailer = temp;

        // Re-link the header and trailer pointers
        header.setPrev(null); // Header's `prev` is always null
        trailer.setNext(null); // Trailer's `next` is always null
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != null && walk != trailer) { // Additional null check
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != null && walk != trailer)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }


    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst("MSP");
        list.addLast("ATL");
        list.addLast("BOS");
        list.addFirst("LAX");

        System.out.println("Before swap: " + list);
        list.swapNodes("LAX", "MSP");
        System.out.println("After swap: " + list);

        System.out.println("Before reverse: " + list);
        list.reverse();
        System.out.println("After reverse: " + list);
    }
}
