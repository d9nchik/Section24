package com.d9nich.exercise6;

import java.util.Comparator;

public class ComparatorPriorityQueue<E> {
    private final HeapWithComparator<E> heap;

    public ComparatorPriorityQueue(Comparator<? super E> comparator) {
        heap = new HeapWithComparator<>(comparator);
    }

    public void enqueue(E newObject) {
        heap.add(newObject);
    }

    public E dequeue() {
        return heap.remove();
    }

    public int getSize() {
        return heap.getSize();
    }
}
