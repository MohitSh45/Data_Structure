// A basic LinkedQueue implementation
package com.exercise3.mohit.sharma;
class LinkedQueue<K> {
    private static class Node<K> {
        K element;
        Node<K> next;

        Node(K element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node<K> front;
    private Node<K> rear;
    private int size;

    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Add element to the queue
    public void enqueue(K element) {
        Node<K> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    // Remove and return the front element
    public K dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        K element = front.element;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return element;
    }

    // Return the first element without removing it
    public K first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.element;
    }

    // Return the size of the queue
    public int size() {
        return size;
    }
}
