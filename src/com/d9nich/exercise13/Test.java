package com.d9nich.exercise13;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Iterator<Integer> iterator = new FibonacciIterator(120_000);
        iterator.forEachRemaining(e -> System.out.println(e + " "));
    }
}
