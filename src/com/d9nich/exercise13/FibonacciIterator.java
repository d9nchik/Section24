package com.d9nich.exercise13;

import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {
    private final int MAX_NUMBER;
    private int first = 0;
    private int second = 1;

    public FibonacciIterator(int maxNumber) {
        MAX_NUMBER = maxNumber;
    }

    @Override
    public boolean hasNext() {
        return first <= MAX_NUMBER;
    }

    @Override
    public Integer next() {
        int current = first;
        int temp = first + second;
        first = second;
        second = temp;
        return current;
    }
}
