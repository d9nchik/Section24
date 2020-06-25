package com.d9nich.exercise15;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private final MyArrayList<Integer> emptyArrayList = new MyArrayList<>();
    private final MyArrayList<Integer> fullArrayList = new MyArrayList<>();

    @BeforeEach
    public void setArrayList() {
        for (int i = 0; i < 500; i++)
            fullArrayList.add(i);
        for (int i = 500 - 1; i >= 0; i--)
            fullArrayList.add(i);
    }

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(1_000, fullArrayList.size());
        fullArrayList.add(1);
        assertEquals(1_001, fullArrayList.size());
        assertEquals(1, fullArrayList.get(1_000));
        fullArrayList.add(10, 20);
        assertEquals(1_002, fullArrayList.size());
        assertEquals(20, fullArrayList.get(10));

        assertThrows(IndexOutOfBoundsException.class, () -> fullArrayList.add(-1, 100));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyArrayList.add(1, 100));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        assertEquals(1_000, fullArrayList.size());
        fullArrayList.clear();
        assertEquals(0, fullArrayList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> fullArrayList.get(0));
    }

    @org.junit.jupiter.api.Test
    void contains() {
        assertTrue(fullArrayList.contains(30));
        assertFalse(fullArrayList.contains(-1));
    }

    @org.junit.jupiter.api.Test
    void get() {
        assertEquals(10, fullArrayList.get(10));
        assertThrows(IndexOutOfBoundsException.class, () -> fullArrayList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> fullArrayList.get(1_000));
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        assertEquals(10, fullArrayList.indexOf(10));
        assertEquals(-1, fullArrayList.indexOf(-1));
    }

    @org.junit.jupiter.api.Test
    void lastIndexOf() {
        assertEquals(-1, fullArrayList.lastIndexOf(-1));
        assertEquals(989, fullArrayList.lastIndexOf(10));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        assertEquals(2, fullArrayList.get(2));
        assertEquals(1_000, fullArrayList.size());
        fullArrayList.remove(2);
        assertNotEquals(2, fullArrayList.get(2));
        assertEquals(999, fullArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void set() {
        assertEquals(2, fullArrayList.get(2));
        assertEquals(1_000, fullArrayList.size());
        fullArrayList.set(2, 3);
        assertEquals(3, fullArrayList.get(2));
        assertEquals(1_000, fullArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("[]", emptyArrayList.toString());
        emptyArrayList.add(1);
        assertEquals("[1]", emptyArrayList.toString());
        emptyArrayList.add(2);
        assertEquals("[1, 2]", emptyArrayList.toString());
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        emptyArrayList.addAll(Arrays.asList(1, 2));
        Iterator<Integer> iterator = emptyArrayList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        iterator.remove();
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(0, emptyArrayList.size());
        emptyArrayList.add(2);
        assertEquals(1, emptyArrayList.size());
    }
}