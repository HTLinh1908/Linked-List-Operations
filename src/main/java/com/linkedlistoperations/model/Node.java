package com.linkedlistoperations.model;

public class Node<T> {
    T data;
    Node<T> next;
    boolean isHighlighted = false;;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
        this.isHighlighted = false;
    }
    public Node(T data) {
        this.data = data;
        System.out.println("Node created with data: " + data);
        this.next = null;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    
    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }
}
