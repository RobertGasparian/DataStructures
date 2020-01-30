package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {

    private T[] array;
    private int len = 0;   // length user thinks array is
    private int capacity = 0;  //Actual array size

    public Array() {
        this(16);
    }

    public Array(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index > size() - 1 || index < 0) throw new IllegalArgumentException("Illegal index: " + index);
        return array[index];
    }

    public void set(int index, T element) {
        if (index > size() - 1 || index < 0) throw new IllegalArgumentException("Illegal index: " + index);
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            array[i] = null;
        }
        len = 0;
    }

    public void add(T element) {
        if (len + 1 >= capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2;  //double the size
            }
            T[] newArray = (T[]) new Object[capacity];
            for (int i = 0; i < size(); i++) {
                newArray[i] = array[i];
            }
            array = newArray;  // now array has extra nulls added
        }
        array[len++] = element;
    }

    public T removeAt(int index) {
        if (index > size() - 1 || index < 0) throw new IllegalArgumentException("Illegal index: " + index);
        T data = array[index];
        if (size() - 1 < (capacity/2) + 1) {
            capacity = (capacity / 2) + 1;  // reduce size by half if needed (optional)
        }
        T[] newArray = (T[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < size(); i++) {
            if (i != index) { //Skip removed element
                newArray[j++] = array[i];
            }
        }
        array = newArray;
        len--;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(size()).append("[");
            for (int i = 0; i < size() - 1; i++) {
                sb.append(array[i] + ", ");
            }
            return sb.append(array[len - 1] + "]").toString();
        }
    }
}
