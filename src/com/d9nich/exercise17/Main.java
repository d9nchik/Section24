package com.d9nich.exercise17;

import com.d9nich.exercise6.ComparatorPriorityQueue;

import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int MAX_NUMBER = 5_000_000;
        Random random = new Random();

        long start = System.currentTimeMillis();
        PriorityUsingSortedArrayList<Integer> priorityUsingSortedArrayList = new PriorityUsingSortedArrayList<>();
        for (int i = 0; i < MAX_NUMBER; i++)
            priorityUsingSortedArrayList.enqueue(random.nextInt());
        for (int i = 0; i < MAX_NUMBER; i++)
            priorityUsingSortedArrayList.dequeue();
        System.out.println("PriorityUsingSortedArrayList: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        ComparatorPriorityQueue<Integer> comparatorPriorityQueue =
                new ComparatorPriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < MAX_NUMBER; i++)
            comparatorPriorityQueue.enqueue(random.nextInt());
        for (int i = 0; i < MAX_NUMBER; i++)
            comparatorPriorityQueue.dequeue();
        System.out.println("PriorityHeap: " + (System.currentTimeMillis() - start));
    }
}
