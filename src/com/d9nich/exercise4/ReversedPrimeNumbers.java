package com.d9nich.exercise4;

import java.util.Arrays;

public class ReversedPrimeNumbers {
    public static void main(String[] args) {
        final int MAX_NUMBER = 100;
        boolean[] primes = new boolean[MAX_NUMBER + 1]; // Prime number sieve

        // Initialize primes1[i] to true
        Arrays.fill(primes, true);

        for (int k = 2; k <= MAX_NUMBER / k; k++)
            if (primes[k])
                for (int i = k; i <= MAX_NUMBER / k; i++)
                    primes[k * i] = false; // k * i is not prime

        GenericStack<Integer> primeNumbers = new GenericStack<>();
        for (int i = 2; i < primes.length; i++)
            if (primes[i])
                primeNumbers.push(i);

        int counter = 0;
        while (!primeNumbers.isEmpty()) {
            counter++;
            if (counter % 10 == 0)
                System.out.println(primeNumbers.pop());
            else
                System.out.print(primeNumbers.pop() + ", ");
        }
    }
}
