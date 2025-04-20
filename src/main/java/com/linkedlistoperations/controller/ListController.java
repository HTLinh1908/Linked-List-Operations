package com.linkedlistoperations.controller;

import com.linkedlistoperations.model.*;
import com.linkedlistoperations.view.ListPanel;
import java.util.List;

public class ListController<T> {
    private final LinkedList<T> model;
    private ListPanel<T> view;

    public ListController(LinkedList<T> model) { this.model = model; }
    public void setView(ListPanel<T> view) { this.view = view; }

    public void insertFront(T data) { }
    public void insertEnd(T data) { }
    public void insertAt(int index, T data) { }

    public void removeAt(int index) { }
    public void removeValue(T value) { }
    public void reverse() { }

    public void search(T value) { }
    public void detectCycle() { }
    public void findMiddle() { }
}

