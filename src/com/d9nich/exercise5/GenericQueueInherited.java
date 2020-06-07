package com.d9nich.exercise5;

import java.util.LinkedList;

public class GenericQueueInherited<E> extends LinkedList<E> {
    public void enqueue(E e) {
        addLast(e);
    }

    public E dequeue() {
        return removeFirst();
    }

    public int getSize() {
        return size();
    }

    @Override
    public String toString() {
        return "GenericQueueInherited{} " + super.toString();
    }
}
