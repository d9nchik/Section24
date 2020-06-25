package com.d9nich.exercise2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    MyLinkedList<Integer> simpleList = new MyLinkedList<>();

    @BeforeEach
    public void setSimpleList(){
        simpleList.addAll(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    void getFirst() {
        assertEquals(1, simpleList.getFirst());
    }

    @Test
    void getLast() {
        assertEquals(5, simpleList.getLast());
    }

    @Test
    void addFirst() {
        simpleList.addFirst(0);
        assertEquals(0, simpleList.getFirst());
        assertEquals(6, simpleList.size());
    }

    @Test
    void addLast() {
        simpleList.addLast(6);
        assertEquals(6, simpleList.getLast());
        assertEquals(6, simpleList.size());
    }

    @Test
    void add() {
        simpleList.add(6);
        assertEquals(6, simpleList.size());
        assertEquals(6, simpleList.getLast());
    }

    @Test
    void testAdd() {
        simpleList.add(3, 7);
        assertEquals(6, simpleList.size());
        assertEquals(7, simpleList.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> simpleList.add(-1, 8));
        assertThrows(IndexOutOfBoundsException.class, () -> simpleList.add(10, 9));
    }

    @Test
    void removeFirst() {
        assertEquals(1, simpleList.removeFirst());
        assertEquals(4, simpleList.size());
        assertEquals(2, simpleList.removeFirst());
        assertEquals(3, simpleList.size());
    }

    @Test
    void removeLast() {
        assertEquals(5, simpleList.removeLast());
        assertEquals(4, simpleList.size());
        assertEquals(4, simpleList.removeLast());
        assertEquals(3, simpleList.size());
    }

    @Test
    void remove() {
        assertEquals(3, simpleList.remove(2));
        assertEquals(4, simpleList.size());
        assertEquals(4, simpleList.remove(2));
        assertEquals(3, simpleList.size());
    }

    @Test
    void testToString() {
        MyLinkedList<Integer> empty = new MyLinkedList<>();
        assertEquals("[]", empty.toString());
        empty.add(1);
        assertEquals("[1]", empty.toString());
        empty.add(2);
        assertEquals("[1, 2]", empty.toString());
    }

    @Test
    void clear() {
        simpleList.clear();
        assertEquals(0, simpleList.size());
        assertThrows(NoSuchElementException.class, () -> simpleList.getFirst());
    }

    @Test
    void contains() {
        assertTrue(simpleList.contains(5));
        assertFalse(simpleList.contains(-1));
    }

    @Test
    void get() {
        assertEquals(1, simpleList.get(0));
        assertEquals(3, simpleList.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> simpleList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> simpleList.get(8));
    }

    @Test
    void indexOf() {
        assertEquals(0, simpleList.indexOf(1));
        assertEquals(2, simpleList.indexOf(3));
        assertEquals(-1, simpleList.indexOf(-1));
    }

    @Test
    void lastIndexOf() {
        simpleList.addAll(Arrays.asList(5, 4, 3, 2, 1));
        assertEquals(9, simpleList.lastIndexOf(1));
        assertEquals(7, simpleList.lastIndexOf(3));
        assertEquals(-1, simpleList.lastIndexOf(-1));
    }

    @Test
    void set() {
        simpleList.set(2, 0);
        assertEquals(0, simpleList.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> simpleList.set(7, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> simpleList.set(-2, 6));
    }

    @Test
    void iterator() {
        simpleList.clear();
        simpleList.addAll(Arrays.asList(1, 2));
        Iterator<Integer> iterator = simpleList.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        iterator.remove();
        assertEquals(1, simpleList.size());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void size() {
        assertEquals(5, simpleList.size());
    }
}