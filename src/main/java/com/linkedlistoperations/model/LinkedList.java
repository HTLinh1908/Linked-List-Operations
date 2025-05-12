package com.linkedlistoperations.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class LinkedList<T> {
    private Node<T> head;
    private final List<ListChangeListener<T>> listeners = new ArrayList<>();

    public LinkedList() {}

    public boolean checkPalindrome() {
        if (head == null || head.next == null) return true;

        // Step 1: Find midpoint
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node<T> secondHalfStart = reverse_linkedlist(slow);
        Node<T> copySecondHalfStart = secondHalfStart; // Save reference for restoration

        // Step 3: Compare
        Node<T> firstHalfStart = head;
        boolean isPalindrome = true;
        while (secondHalfStart != null) {
            if (!firstHalfStart.data.equals(secondHalfStart.data)) {
                isPalindrome = false;
                break;
            }
            firstHalfStart = firstHalfStart.next;
            secondHalfStart = secondHalfStart.next;
        }

        // Step 4: Restore the list
        reverse_linkedlist(copySecondHalfStart);

        return isPalindrome;
    }

    // Helper to reverse a linked list
    private Node<T> reverse_linkedlist(Node<T> node) {
        Node<T> prev = null;
        while (node != null) {
            Node<T> next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }


    public void insertFront(T data) {
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
        notifyListeners();
    }

    public void insertEnd(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
        } else {
            Node<T> temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = node;
        }
        notifyListeners();
    }

    public void insertAt(int index, T data) {
        if (index <= 0 || head == null) {
            insertFront(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> temp = head;
        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp != null) {
            newNode.next = temp.next;
            temp.next = newNode;
            notifyListeners();
        }
    }

    public T removeAt(int index) {
        if (index < 0 || head == null) return null;
        if (index == 0) {
            T val = head.data;
            head = head.next;
            notifyListeners();
            return val;
        }
        Node<T> temp = head;
        for (int i = 0; temp.next != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp.next != null) {
            T val = temp.next.data;
            temp.next = temp.next.next;
            notifyListeners();
            return val;
        }
        return null;
    }

    public boolean removeValue(T value) {
        if (head == null) return false;
        if (head.data.equals(value)) {
            head = head.next;
            notifyListeners();
            return true;
        }
        Node<T> temp = head;
        while (temp.next != null && !temp.next.data.equals(value)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            notifyListeners();
            return true;
        }
        return false;
    }

    public boolean removeDuplicatedValues(T value) {
        if (head == null) return false;
        boolean removed = false;
        while (head != null && head.data.equals(value)) {
            head = head.next;
            removed = true;
        }
        Node<T> current = head;
        while (current != null && current.next != null) {
            if (current.next.data.equals(value)) {
                current.next = current.next.next;
                removed = true;
            } else {
                current = current.next;
            }
        }
        if (removed) notifyListeners();
        return removed;
    }

    public void reverse() {
        Node<T> prev = null, current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        notifyListeners();
    }

    public void sort() {
        if (head == null || head.next == null) return;

        Node<T> sorted = null;
        Node<T> current = head;

        // Node<T> temp = head;
        // while (temp != null) {
        //     Node<T> next = temp.next;
        //     temp.next = sorted;
        //     sorted = temp;
        //     temp = next;
        //     head = sorted;
        //     notifyListeners();
        //     delay(4000);
        // }

        while (current != null) {
            Node<T> next = current.next;

            if (sorted == null || ((Comparable<T>) current.data).compareTo(sorted.data) <= 0) {
                current.next = sorted;
                sorted = current;
            } else {
                Node<T> temp = sorted;
                while (temp.next != null && ((Comparable<T>) current.data).compareTo(temp.next.data) > 0) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }

            notifyListeners(); 
            delay(300);      

            current = next;
        }

        head = sorted;
        notifyListeners(); // final update
    }

    private Node<T> cloneList(Node<T> node) {
        if (node == null) return null;

        Node<T> newHead = new Node<>(node.data);
        newHead.isHighlighted = node.isHighlighted;

        Node<T> currentOld = node.next;
        Node<T> currentNew = newHead;

        while (currentOld != null) {
            currentNew.next = new Node<>(currentOld.data);
            currentNew.next.isHighlighted = currentOld.isHighlighted;

            currentOld = currentOld.next;
            currentNew = currentNew.next;
        }

        return newHead;
    }

    private List<Node<T>> captureBubbleSortSteps() {
        List<Node<T>> steps = new ArrayList<>();

        if (head == null || head.next == null) {
            steps.add(cloneList(head));
            return steps;
        }

        boolean swapped;
        Node<T> passEnd = null; // end of unsorted section (sorted grows backward)

        do {
            swapped = false;
            Node<T> dummy = new Node<>(null);
            dummy.next = head;

            Node<T> prev = dummy;
            Node<T> curr = head;

            while (curr.next != passEnd) {
                // Highlight curr and curr.next
                curr.isHighlighted = true;
                curr.next.isHighlighted = true;

                // Take snapshot BEFORE the actual swap
                steps.add(cloneList(dummy.next));

                if (((Comparable<T>) curr.data).compareTo(curr.next.data) > 0) {
                    // Swap curr and curr.next
                    Node<T> temp = curr.next;
                    curr.next = temp.next;
                    temp.next = curr;
                    prev.next = temp;

                    swapped = true;
                    curr = temp; // stay at new position for next comparison
                }

                // Unhighlight
                curr.isHighlighted = false;
                if (curr.next != null) curr.next.isHighlighted = false;

                prev = curr;
                curr = curr.next;
            }

            passEnd = curr;
            head = dummy.next;
        } while (swapped);

        Node<T> cleared = cloneList(head);
        clearHighlights(cleared);
        steps.add(cleared);
        return steps;
    }

    private void clearHighlights(Node<T> node) {
        while (node != null) {
            node.isHighlighted = false;
            node = node.next;
        }
    }

    public void bubbleSortAnimated() {
    List<Node<T>> steps = captureBubbleSortSteps();
    final int[] index = {0};

    Timer timer = new Timer(700, e -> {
        if (index[0] < steps.size()) {
            head = steps.get(index[0]++);
            notifyListeners(); // visualize full list at this step
        } else {
            ((Timer) e.getSource()).stop(); // done
        }
    });
    timer.setInitialDelay(0);
    timer.start();
}
   
    public List<Node<T>> toList() {
        List<Node<T>> result = new ArrayList<>();
        Node<T> temp = head;
        while (temp != null) {
            result.add(temp);
            temp = temp.next;
        }
        return result;
    }

    public void addListChangeListener(ListChangeListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListChangeListener(ListChangeListener<T> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (ListChangeListener<T> listener : listeners) {
            listener.onListChanged();
        }
    }

    // Helper method to introduce a delay
    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Node<T>> search(T value) {
        List<Node<T>> result = new ArrayList<>();
        Node<T> temp = head;
        while (temp != null) {
            if (temp.data.equals(value)) {
                result.add(temp);
            }
            temp = temp.next;
        }
        return result;
    }

    public boolean detectCycle() {
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public Node<T> findMiddle() {
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void clear() {
        head = null;
        notifyListeners();
    }
}
