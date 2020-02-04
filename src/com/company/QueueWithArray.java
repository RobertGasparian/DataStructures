package com.company;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class QueueWithArray<T> implements Iterable<T> {

    private T[] array;
    private int start = 0;
    private int end = 0;
    private int capacity = 0;
    private int len = 0;

    public QueueWithArray() {
        this(16);
    }

    public QueueWithArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Invalid capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(T element) {
        if (len >= array.length) {
            extendCapacity();
            enqueue(element);
        } else {
            if(end == array.length) {
                end = 0;
            }
            array[end] = element;
            len++;
            end++;
        }
    }

    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        T data = array[start];
        array[start] = null;
        start++;
        len--;
        return data;
    }

    private void extendCapacity() {
        capacity = capacity == 0 ? 1 : capacity * 2;
        T[] newArray = (T[]) new Object[capacity];
        int realIndex = start;
        for (int i = 0; i < len; i++) {
            if (realIndex == array.length) {
                realIndex = 0;
            }
            newArray[i] = array[realIndex];
            realIndex++;
        }
        start = 0;
        end = len;
        array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = start;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if (index + 1 == len) {
                    index = 0;
                } else {
                    index++;
                }
                return array[index];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(size()).append("[");
            int realIndex = start;
            for (int i = 0; i < size() - 1; i++) {
                if (realIndex == len) {
                    realIndex = 0;
                }
                sb.append(array[realIndex] + ", ");
                realIndex++;
            }
            return sb.append(array[realIndex] + "]").toString();
        }
    }
}
