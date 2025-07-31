package com.exercise3.mohit.sharma;


public class DoublyLinkedList<E> {
	// Student Name: Mohit Sharma
    // Student Number: 301297059


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

    public void insertMiddle(E element) {
        if (isEmpty()) {
            addFirst(element);
            return;
        }
        
        Node<E> slow = header.getNext();
        Node<E> fast = header.getNext();
        
        while (fast != trailer && fast.getNext() != trailer) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        
        addBetween(element, slow.getPrev(), slow);
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
        
        System.out.println("Before inserting element in the middle: " + list);
        list.insertMiddle("YYJ");
        System.out.println("After inserting element in the middle: " + list);
    }
}

