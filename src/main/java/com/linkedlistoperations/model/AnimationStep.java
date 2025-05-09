package com.linkedlistoperations.model;

public record AnimationStep<T>(AnimationType type, Node<T> node, Node<T> otherNode) {
    public T getValue() {
        return node.getData();  // Get the data from the node
    }
    
     public boolean hasNext() {
        return node.getNext() != null;  // Returns true if the node has a next node
    }
}
