package com.company;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int len = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> temp = head;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }

    //Internal node class to represent data
    private class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    //Empty linked list, O(n)
    public void clear() {
        Node<T> temp = head;
        while (temp != null) {
            Node<T> next = temp.next;
            temp.prev = temp.next = null;
            temp.data = null;
            temp = next;
        }
        head = tail = null;
        len = 0;
    }

    //Size of linked list
    public int size() {
        return len;
    }

    //is linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    //add element to the tail of the linked list, O(1)
    public void add(T element) {
        addLast(element);
    }

    //add element to the beginning of the linked list, O(1)
    public void addFirst(T element) {
        if (isEmpty()) {
            head = new Node(element, null, null);
            tail = head;
        } else {
            head.prev = new Node(element, null, head); //set old heads previous node to new one
            head = head.prev; //set new head
        }
        len++;
    }

    //add element to the end of the linked list, O(1)
    public void addLast(T element) {
        if (isEmpty()) {
            head = new Node(element, null, null);
            tail = head;
        } else {
            tail.next = new Node(element, tail, null);
            tail = tail.next;
        }
        len++;
    }

    //Check the value of the first node if exists, O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    //Check the value of the last node if exists, O(1)
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    //Remove the first value at the head of linked list, O(1)
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        //Extract data from the head and move pointer forward
        T data = head.data;
        head = head.next;
        len--;

        //if list becomes empty, clean the tail as well
        if (isEmpty()) {
            tail = null;
        } else {
            //else clean memory
            head.prev = null;
        }
        return data;
    }

    //Remove the last value at the tail of linked list, O(1)
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        //Extract data from the head and move pointer forward
        T data = tail.data;
        tail = tail.prev;
        len--;

        //if list becomes empty, clean the head as well
        if (isEmpty()) {
            head = null;
        } else {
            //else clean memory
            tail.next = null;
        }
        return data;
    }

    //Remove specific node from linked list, O(1)
    //this is for internal usage (private) because user don't know about 'Node's
    private T remove(Node<T> node) {
        //if if node is at the head or tail
        if (node.prev == null) {
            return removeFirst();
        }
        if (node.next == null) {
            return removeLast();
        }

        T data = node.data;

        //Memory cleanup
        node.data = null;
        node = node.prev = node.next = null;
        len--;
        return data;
    }

    //Remove node at particular index, O(n)
    public T removeAt(int index) {
        //Make sure there is valid index provided
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Out of bonds index: " + index);
        Node<T> temp;

        if (index < size() / 2) {
            //Search from the front of the list
            temp = head;
            for (int i = 0; i != index; i++) {
                temp = temp.next;
            }
        } else {

            //Search from the end of the list
            temp = tail;
            for (int i = 0; i != index; i++) {
                temp = temp.prev;
            }
        }
        return remove(temp);
    }

    // Remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> temp = head;

        //If we support searching null(optional)
        if (obj == null) {
            for (temp = head; temp != null; temp = temp.next) {
                remove(temp);
                return true;
            }
        } else {
            for (temp = head; temp != null; temp = temp.next) {
                if (obj.equals(temp.data)) {
                    remove(temp);
                    return true;
                }
            }
        }
        return false;
    }

    //Find the index of particular value in the linked list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> temp = head;

        //If we support searching null(optional)
        if (obj == null) {
            for (temp = head; temp != null; temp = temp.next, index++) {
                if (temp.data == null) {
                    return index;
                }
            }
        } else {
            for (temp = head; temp != null; temp = temp.next, index++) {
                if (obj.equals(temp.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    //Check if a value contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
        if (len == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(size()).append("[ ");
            Node<T> temp = head;
            while (temp != null) {
                sb.append(temp.data + ", ");
                temp = temp.next;
            }
            return sb.append(" ]").toString();
        }
    }
}
