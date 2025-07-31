package com.exercise3.mohit.sharma;

import java.util.Comparator;

public class QuickSort {

    // QuickSort method for sorting the LinkedQueue
    public static <K> void quickSort(LinkedQueue<K> S, Comparator<K> comp) {
        if (S.size() < 2) return;  // Base case, no need to sort if size is less than 2

        K pivot = S.first();  // Use the first element as the pivot
        LinkedQueue<K> less = new LinkedQueue<>();
        LinkedQueue<K> equal = new LinkedQueue<>();
        LinkedQueue<K> greater = new LinkedQueue<>();

        // Partition the elements into less, equal, and greater based on pivot
        while (!S.isEmpty()) {
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if (c < 0) less.enqueue(element);
            else if (c == 0) equal.enqueue(element);
            else greater.enqueue(element);
        }

        // Recursively sort the less and greater parts
        quickSort(less, comp);
        quickSort(greater, comp);

        // Rebuild the queue in sorted order: less + equal + greater
        while (!less.isEmpty()) S.enqueue(less.dequeue());
        while (!equal.isEmpty()) S.enqueue(equal.dequeue());
        while (!greater.isEmpty()) S.enqueue(greater.dequeue());
    }

    public static void main(String[] args) {
        LinkedQueue<Account> accounts = new LinkedQueue<>();
        accounts.enqueue(new Account(101, "Alice", 500.0));
        accounts.enqueue(new Account(102, "Bob", 1200.5));
        accounts.enqueue(new Account(103, "Charlie", 800.0));
        accounts.enqueue(new Account(104, "Diana", 300.75));

        // Comparator to compare Account objects by their balance
        Comparator<Account> balanceComparator = new Comparator<Account>() {
            public int compare(Account a1, Account a2) {
                return Double.compare(a1.getAccountBalance(), a2.getAccountBalance());
            }
        };

        // Perform quicksort on the accounts queue
        quickSort(accounts, balanceComparator);

        // Dequeue all sorted accounts and print them
        System.out.println("Sorted accounts by balance:");
        while (!accounts.isEmpty()) {
            System.out.println(accounts.dequeue());
        }
    }
}

