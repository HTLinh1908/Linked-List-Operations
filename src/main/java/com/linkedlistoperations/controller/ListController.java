package com.linkedlistoperations.controller;

import com.linkedlistoperations.model.*;
import com.linkedlistoperations.view.ListPanel;

import java.util.List;

public class ListController<T> implements ListChangeListener<T> {
    private final LinkedList<T> model;
    private ListPanel<T> view;

    public ListController(LinkedList<T> model) {
        this.model = model;
        model.addListChangeListener(this); // Register listener
        refreshView(); // Initialize the view
    }
    @Override
    public void onVisit(Node<T> current) {
        // Optional: Handle visit events if needed
    }

    @Override
    public void onInsert(Node<T> newNode, Node<T> prevNode) {
        refreshView();
    }

    @Override
    public void onDelete(Node<T> deletedNode, Node<T> prevNode) {
        refreshView();
    }

    @Override
    public void onLinkChange(Node<T> from, Node<T> to) {
        refreshView();
    }

    @Override
    public void onComplete() {
        refreshView();
    }
    @Override
    public void onListChanged() {
        refreshView();
    }

    public void setView(ListPanel<T> view) {
        this.view = view;
        refreshView(); // update GUI initially
    }

    private void refreshView() {
        if (view != null) {
            List<Node<T>> nodeList = model.toList();
//            view.updateList(nodeList);
//            view.showMessage(""); // clear message
            System.out.println("View received: " + nodeList);
            view.updateList(nodeList);
        }
    }

    public void insertFront(T data) {
        model.insertFront(data);
    }

    public void insertEnd(T data) {
        model.insertEnd(data);
    }

    public void removeFront() {
        T removed = model.removeFront();
        if (view != null) {
            if (removed != null) {
                view.showMessage("Removed item from front: " + removed);
            } else {
                view.showMessage("List is empty.");
            }
        }
    }

    public void removeEnd() {
        T removed = model.removeEnd();
        if (view != null) {
            if (removed != null) {
                view.showMessage("Removed item from end: " + removed);
            } else {
                view.showMessage("List is empty.");
            }
        }
    }

    public void insertAt(int index, T data) {
        model.insertAt(index, data);
    }

    public void removeAt(int index) {
        T removed = model.removeAt(index);
        if (view != null) {
            if (removed != null) {
                view.showMessage("Removed item at index " + index + ": " + removed);
            } else {
                view.showMessage("Invalid index: " + index);
            }
        }
    }

    public void removeValue(T value) {
        boolean success = model.removeValue(value);
        if (view != null) {
            view.showMessage(success
                    ? "Removed value: " + value
                    : "Value not found: " + value);
        }
    }

    public void removeDuplicatedValues(T value) {
        boolean success = model.removeDuplicatedValues(value);
        if (view != null) {
            view.showMessage(success
                    ? "Removed all value: " + value
                    : "Value not found: " + value);
        }
    }

    public void reverse() {
        model.reverse();
        if (view != null) view.showMessage("List reversed.");
    }

    public void sort() {
        view.clearHighlight();
        model.bubbleSortAnimated();
        if (view != null) view.showMessage("List sorted.");
    }

    public void search(T value) {
        List<Node<T>> found = model.search(value);
        if (view != null) {
            if (found.isEmpty()) {
                view.showMessage("Value not found: " + value);
            } else {
                view.showMessage("Found " + found.size() + " occurrence(s) of: " + value);
                view.highlightValue(value);
            }
        }
    }

    public void detectCycle() {
        boolean hasCycle = model.detectCycle();
        if (view != null) {
            view.showMessage(hasCycle ? "Cycle detected!" : "No cycle detected.");
        }
    }

    public void findMiddle() {
        Node<T> mid = model.findMiddle();
        view.clearHighlight();
        if (view != null) {
            if (mid != null) {
                view.highlightNode(mid);
                view.showMessage("Middle element: " + mid.getData());
            } else {
                view.showMessage("List is empty.");
            }
        }
    }

    public void clear() {
        model.clear();
        if (view != null) {
            view.updateList(model.toList());
            view.showMessage("List cleared.");
        }
    }

    public void checkPalindrome() {
        boolean isPalindrome = model.checkPalindrome();
        if (view != null) {
            view.showMessage(isPalindrome ? "List is a palindrome." : "List is not a palindrome.");
        }
    }
}
