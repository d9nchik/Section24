package com.d9nich.exercise3;

import com.d9nich.exercise1.MyList;

import java.util.Arrays;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;

    private int size = 0; // Number of elements in the list

    /**
     * Create an empty list
     */
    public TwoWayLinkedList() {

    }

    /**
     * Create a list from an array of objects
     */
    public TwoWayLinkedList(E[] objects) {
        this.addAll(Arrays.asList(objects));
    }

    /**
     * Return the head element in the list
     */
    public E getFirst() {
        return size == 0 ? null : head.element;
    }

    /**
     * Return the last element in the list
     */
    public E getLast() {
        return size == 0 ? null : tail.element;
    }

    /**
     * Add an element to the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head.previous = newNode;
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // The new node is the only node in list
            tail = head;
    }

    /**
     * Add an element to the end of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node for e
        if (tail == null)
            head = tail = newNode; // The only node in list
        else {
            tail.next = newNode; // Link the new node with the last node
            newNode.previous = tail;
            tail = newNode; // tail now points to the last node
        }
        size++; // Increase size
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * Add a new element at the specified index
     * in this list. The index of the head element is 0
     */
    @Override
    public void add(int index, E e) {
        if (index == 0) addFirst(e); // Insert first
        else if (index >= size) addLast(e); // Insert last
        else { // Insert in the middle
            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            current.next.previous = current;
            temp.previous = current.next;
            (current.next).next = temp;
            size++;
        }
    }

    /**
     * Remove the head node and
     * return the object that is contained in the removed node.
     */
    public E removeFirst() {
        if (size == 0) return null; // Nothing to delete
        else {
            Node<E> temp = head; // Keep the first node temporarily
            head = head.next; // Move head to point to next node
            size--; // Reduce size by 1
            if (head == null) tail = null; // List becomes empty
            else
                head.previous = null;
            return temp.element; // Return the deleted element
        }
    }

    /**
     * Remove the last node and
     * return the object that is contained in the removed node.
     */
    public E removeLast() {
        if (size <= 1)
            return removeFirst();
        else {
            Node<E> current = tail.previous;
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }


    /**
     * Remove the element at the specified position in this
     * list. Return the element that was removed from the list.
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null; // Out of range
        else if (index == 0) return removeFirst(); // Remove first
        else if (index == size - 1) return removeLast(); // Remove last
        else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            current.next.previous = previous;
            size--;
            return current.element;
        }
    }


    /**
     * Override toString() to return elements in the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            assert current != null;
            result.append(current.element);
            current = current.next;
            result.append(current != null ? ", " : "]");
        }
        return result.toString();
    }


    /**
     * Clear the list
     */
    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }


    /**
     * Return true if this list contains the element e
     */
    @Override
    public boolean contains(Object e) {
        for (E element : this)
            if (element.equals(e))
                return true;
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * Return the element at the specified index
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.element;
    }


    /**
     * Return the index of the head matching element in
     * this list. Return −1 if no match.
     */
    @Override
    public int indexOf(Object e) {
        int counter = 0;
        for (E element : this) {
            if (e.equals(element))
                return counter;
            counter++;
        }
        return -1;
    }


    /**
     * Return the index of the last matching element in
     * this list. Return −1 if no match.
     */
    @Override
    public int lastIndexOf(E e) {
        int counter = size - 1;
        Node<E> current = tail;
        while (current != null) {
            if (current.element.equals(e))
                return counter;
            current = current.previous;
            counter--;
        }
        return -1;
    }


    /**
     * Replace the element at the specified position
     * in this list with the specified element.
     */
    @Override
    public E set(int index, E e) {
        checkIndex(index);
        if (index >= size) {
            addLast(e); // Insert last
            return null;
        } else { // Insert in the middle
            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp.next;
            return temp.element;
        }
    }


    /**
     * Override iterator() defined in Iterable
     */
    @Override
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public java.util.Iterator<E> iterator(int index) {
        checkIndex(index);
        java.util.Iterator<E> iterator = new LinkedListIterator();
        for (int i = 0; i < index; i++)
            iterator.next();
        return iterator;
    }

    /**
     * Return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    private class LinkedListIterator
            implements java.util.Iterator<E> {
        private Node<E> current = head; // Current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        public E previous() {
            E e = current.element;
            current = current.previous;
            return e;
        }

        @Override
        public void remove() {
            // Left as an exercise
        }
    }
}
