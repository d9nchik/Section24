package com.d9nich.exercise1;

import java.util.Collection;

public interface MyList<E> extends Collection<E> {
    /**
     * Add a new element at the specified index in this list
     */
    void add(int index, E e);

    /**
     * Return the element from this list at the specified index
     */
    E get(int index);

    /**
     * Return the index of the first matching element in this list.
     * Return −1 if no match.
     */
    int indexOf(Object e);

    /**
     * Return the index of the last matching element in this list
     * Return −1 if no match.
     */
    int lastIndexOf(E e);

    /**
     * Remove the element at the specified position in this list
     * Shift any subsequent elements to the left.
     * Return the element that was removed from the list.
     */
    E remove(int index);

    /**
     * Replace the element at the specified position in this list
     * with the specified element and returns the new set.
     */
    E set(int index, E e);


    /**
     * Add a new element at the end of this list
     */
    @Override
    default boolean add(E e) {
        add(size(), e);
        return true;
    }


    /**
     * Return true if this list contains no elements
     */
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }


    /**
     * Remove the first occurrence of the element e
     * from this list. Shift any subsequent elements to the left.
     * Return true if the element is removed.
     */
    @Override
    default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        } else
            return false;
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (indexOf(e) == -1)
                return false;
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        this.forEach(e -> {
            if (!c.contains(e))
                this.remove(e);
        });
        return true;
    }

    @Override
    default Object[] toArray() {
        Object[] objects = new Object[size()];
        int i = 0;
        for (E e : this)
            objects[i++] = e;
        return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <T> T[] toArray(T[] array) {
        if (array.length < size())
            array = (T[]) new Object[size()];
        int i = 0;
        for (E e : this)
            array[i++] = (T) e;
        return array;
    }
}
