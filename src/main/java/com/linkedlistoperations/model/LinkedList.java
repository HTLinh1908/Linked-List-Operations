package com.linkedlistoperations.model;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
    private Node<T> head;
    private final List<ListChangeListener<T>> listeners = new ArrayList<>();

    public LinkedList() {}

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
            current = next;
        }

        head = sorted;
        notifyListeners();
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
