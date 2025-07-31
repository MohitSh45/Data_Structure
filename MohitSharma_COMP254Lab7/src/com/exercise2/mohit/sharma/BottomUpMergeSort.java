package com.exercise2.mohit.sharma;

import java.util.Comparator;

import com.exercise2.mohit.sharma.LinkedQueue;

public class BottomUpMergeSort {
    
    // Method to merge two queues
    public static <T> LinkedQueue<T> merge(LinkedQueue<T> q1, LinkedQueue<T> q2, Comparator<T> comp) {
        LinkedQueue<T> result = new LinkedQueue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (comp.compare(q1.first(), q2.first()) <= 0) {
                result.enqueue(q1.dequeue());
            } else {
                result.enqueue(q2.dequeue());
            }
        }
        // enqueue remaining elements
        while (!q1.isEmpty()) {
            result.enqueue(q1.dequeue());
        }
        while (!q2.isEmpty()) {
            result.enqueue(q2.dequeue());
        }
        return result;
    }

    // Bottom-up merge sort
    public static <T> LinkedQueue<T> bottomUpMergeSort(LinkedQueue<T> items,Comparator<T> comp) {
        // LinkedQueue of queues to manage merging
        LinkedQueue<LinkedQueue<T>> queueOfQueues = new LinkedQueue<>();
        
        // Initialize each item into its own LinkedQueue
        while (!items.isEmpty()) {
            LinkedQueue<T> singleItemQueue = new LinkedQueue<>();
            singleItemQueue.enqueue(items.dequeue());
            queueOfQueues.enqueue(singleItemQueue);
        }
        
        // Merge until only one LinkedQueue is left
        while (queueOfQueues.size() > 1) {
            LinkedQueue<T> q1 = queueOfQueues.dequeue();
            LinkedQueue<T> q2 = queueOfQueues.dequeue();
            LinkedQueue<T> mergedQueue = merge(q1, q2,comp);
            queueOfQueues.enqueue(mergedQueue);
        }
        
        return queueOfQueues.dequeue();
    }

    public static void main(String[] args) {
        // Sample data to sort
        LinkedQueue<Integer> items = new LinkedQueue<>();
        items.enqueue(5);
        items.enqueue(2);
        items.enqueue(9);
        items.enqueue(1);
        items.enqueue(6);
        items.enqueue(3);

        //create a Comparator object
  	  Comparator<Integer> comp = new Comparator<Integer>() {
  	      public int compare(Integer i1, Integer i2) {
  	        return i1.compareTo(i2);
  	      }
  	    };
        // Perform bottom-up merge sort
        LinkedQueue<Integer> sortedItems = bottomUpMergeSort(items,comp);

        // Print sorted items
        System.out.println("Sorted items: " + sortedItems);
    }
}