package com.d9nich.exercise17;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityUsingSortedArrayList<E extends Comparable<E>> {
    private final ArrayList<E> arrayList = new ArrayList<>();
    private boolean sorted;

    public PriorityUsingSortedArrayList() {
        sorted = true;
    }

    public void enqueue(E newObject) {
        arrayList.add(newObject);
        sorted = false;
    }

    public E dequeue() {
        if (!sorted) {
            Collections.sort(arrayList);
            sorted = true;
        }
        return arrayList.remove(getSize() - 1);
    }

    public int getSize() {
        return arrayList.size();
    }
}
