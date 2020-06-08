package com.d9nich.exercise14;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Iterator<Integer> iterator = new PrimeIterator(120_000);
        iterator.forEachRemaining(System.out::println);
    }
}
