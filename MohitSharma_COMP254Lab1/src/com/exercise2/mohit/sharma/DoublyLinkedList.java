package com.exercise2.mohit.sharma;

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

    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(element, predecessor, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != trailer) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static <E> DoublyLinkedList<E> concatenate(DoublyLinkedList<E> L1, DoublyLinkedList<E> L2) {
        if (L1.isEmpty()) return L2;
        if (L2.isEmpty()) return L1;

        // Connect the last node of L1 to the first node of L2
        Node<E> lastNodeL1 = L1.trailer.getPrev();
        Node<E> firstNodeL2 = L2.header.getNext();

        lastNodeL1.setNext(firstNodeL2);
        firstNodeL2.setPrev(lastNodeL1);

        // Update the trailer of L1 to point to L2's trailer
        L1.trailer = L2.trailer;

        // Update the size of the new list
        L1.size += L2.size;

        return L1;
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> list1 = new DoublyLinkedList<>();
        list1.addLast("MSP");
        list1.addLast("ATL");
        list1.addLast("BOS");

        DoublyLinkedList<String> list2 = new DoublyLinkedList<>();
        list2.addLast("YVZ");
        list2.addLast("BOS");
        list2.addLast("YYJ");

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        DoublyLinkedList<String> concatenatedList = concatenate(list1, list2);
        System.out.println("Concatenated List: " + concatenatedList);
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
    }
}
