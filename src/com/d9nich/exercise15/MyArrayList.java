package com.d9nich.exercise15;

import com.d9nich.exercise1.MyList;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked", "unused"})
public class MyArrayList<E> implements MyList<E> {
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];
    private int size = 0; // Number of elements in the list

    /**
     * Create an empty list
     */
    public MyArrayList() {
    }

    /**
     * Create a list from an array of objects
     */
    public MyArrayList(E[] objects) {
        // Warning: don't use super(objects)!
        this.addAll(Arrays.asList(objects));
    }

    /**
     * Add a new element at the specified index
     */
    @Override
    public void add(int index, E e) {
        // Ensure the index is in the right range
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException
                    ("Index: " + index + ", Size: " + size);
        ensureCapacity();
        // Move the elements to the right after the specified index
        if (size - index >= 0) System.arraycopy(data, index, data, index + 1, size - index);
        // Insert new element to data[index]
        data[index] = e;
        // Increase size by 1
        size++;
    }

    /**
     * Create a new larger array, double the current size + 1
     */
    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) (new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    /**
     * Clear the list
     */
    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Return true if this list contains the element
     */
    @Override
    public boolean contains(Object e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return true;
        return false;
    }


    /**
     * Return the element at the specified index
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException
                    ("Index: " + index + ", Size: " + size);
    }


    /**
     * Return the index of the first matching element
     * in this list. Return −1 if no match.
     */
    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return i;
        return -1;
    }


    /**
     * Return the index of the last matching element
     * in this list. Return −1 if no match.
     */
    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--)
            if (e.equals(data[i])) return i;
        return -1;
    }


    /**
     * Remove the element at the specified position
     * in this list. Shift any subsequent elements to the left.
     * Return the element that was removed from the list.
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E e = data[index];
// Shift data to the left
        if (size - 1 - index >= 0) System.arraycopy(data, index + 1, data, index, size - 1 - index);
        data[size - 1] = null; // This element is now null
// Decrement size
        size--;
        return e;
    }


    /**
     * Replace the element at the specified position
     * in this list with the specified element.
     */
    @Override
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }
        return result.toString() + "]";
    }

    /**
     * Trims the capacity to current size
     */
    public void trimToSize() {
        if (size != data.length) {
            E[] newData = (E[]) (new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        } // If size == capacity, no need to trim
    }


    /**
     * Override iterator() defined in Iterable
     */
    @Override
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator
            implements java.util.Iterator<E> {
        private int current = 0; // Current index

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if(hasNext())
            return data[current++];
            throw new NoSuchElementException("MyArrayList has no next element");
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();
            MyArrayList.this.remove(--current);
        }
    }


    /**
     * Return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }
}
