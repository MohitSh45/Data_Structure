package com.exercise2.mohit.sharma;

import java.util.ArrayList;
import java.util.List;

/**
 * A stack implementation using a list.
 *
 * @param <E> the type of elements stored in the stack
 */
public class ListStack<E> {
    private List<E> stack;

    public ListStack() {
        stack = new ArrayList<>();
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param e the element to push
     */
    public void push(E e) {
        stack.add(e);
    }

    /**
     * Pops an element from the stack.
     *
     * @return the popped element
     * @throws IllegalStateException if the stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    /**
     * Returns the top element of the stack.
     *
     * @return the top element
     * @throws IllegalStateException if the stack is empty
     */
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Removes all elements from the stack using recursion.
     */
    public void removeAll() {
        if (!isEmpty()) {
            pop();  // Remove the top element
            removeAll();  // Recurse until the stack is empty
        }
    }

    /**
     * Prints the elements of the stack.
     */
    public void printStack() {
        System.out.println(stack);
    }

public static void main(String[] args) {
    // Create a stack and add elements
    ListStack<Integer> stack = new ListStack<>();
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.push(40);
    
    System.out.println("Original Stack:");
    stack.printStack();  // Print the stack before removal

    // Remove all elements using the recursive method
    stack.removeAll();
    
    System.out.println("Stack after removing all elements:");
    stack.printStack();  // Print the stack after removal
}  
}


