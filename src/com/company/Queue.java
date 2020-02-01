package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {

    private DoublyLinkedList<T> list = new DoublyLinkedList<>();
//    private LinkedList<T> list = new LinkedList<>();

    public Queue() {}

    public Queue(T firstElement) {
        enqueue(firstElement);
    }

    //Returns size of the queue
    public int size() {
        return list.size();
    }

    //check if the queue is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    //Peek the element at the front of the queue
    //Throws error when the queue is empty
    public T peekFirst() {
        if(isEmpty()) throw new RuntimeException("Queue is empty");
        return list.peekFirst();
    }

    //Removes the element in the front of the queue
    //Throws error when the queue is empty
    public T dequeue() {
        if(isEmpty()) throw new RuntimeException("Queue is empty");
        return list.removeFirst();
    }

    //Adds the element at the back of the queue
    public void enqueue(T element) {
        list.add(element);
    }

    public T removeAt(int index) {
        if (index < 0 || index > size() - 1) throw new IllegalArgumentException("Out of bounds: " + index);
        return list.removeAt(index);
    }

    public boolean remove(Object obj) {
        return list.remove(obj);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
