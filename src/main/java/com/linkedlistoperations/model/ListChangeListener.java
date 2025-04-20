package com.linkedlistoperations.model;

public interface ListChangeListener<T> {
    void onVisit(Node<T> current);
    void onInsert(Node<T> newNode, Node<T> prevNode);
    void onDelete(Node<T> deletedNode, Node<T> prevNode);
    void onLinkChange(Node<T> from, Node<T> to);
    void onComplete();
}