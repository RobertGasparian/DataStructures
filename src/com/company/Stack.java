package com.company;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {

    private DoublyLinkedList<T> list = new DoublyLinkedList<>();  //My linked list
//    private LinkedList<T> list = new LinkedList<>();   //Java linked list

    public Stack() {
    }

    public Stack(T firstElement) {
        push(firstElement);
    }

    //Returns the number of elements in stack
    public int size() {
        return list.size();
    }

    //checks if stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    //Adds value on the top of the stack
    public void push(T element) {
        list.add(element);
    }

    //Pop an element off the stack
    //Throws and error if the stack is empty
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    //Peek the top of stack without removing an element
    //Throws and error if the stack is empty
    public T peek() {
      if (isEmpty()) throw new EmptyStackException();
      return list.peekLast();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
