package com.d9nich.exercise14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PrimeIterator implements Iterator<Integer> {
    private final ArrayList<Integer> primes = new ArrayList<>();
    private int currentPosition = 0;

    public PrimeIterator(int maxNumber) {
        boolean[] primes = new boolean[maxNumber + 1]; // Prime number sieve

        // Initialize primes1[i] to true
        Arrays.fill(primes, true);

        for (int k = 2; k <= maxNumber / k; k++)
            if (primes[k])
                for (int i = k; i <= maxNumber / k; i++)
                    primes[k * i] = false; // k * i is not prime

        for (int i = 2; i < primes.length; i++)
            if (primes[i])
                this.primes.add(i);
    }

    @Override
    public boolean hasNext() {
        return currentPosition < primes.size();
    }

    @Override
    public Integer next() {
        return primes.get(currentPosition++);
    }
}
