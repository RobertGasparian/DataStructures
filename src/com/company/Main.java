package com.company;

public class Main {

    public static void main(String[] args) {
//        testArray();
//        testDoubledLinkedList();
        testArrayQueue();
    }

    private static void testArray() {
        Array<Integer> array = new Array<>();
        array.add(5);
        array.add(2);
        array.add(-5);
        array.add(4);
        array.add(-10);
        System.out.println(array);
        System.out.println("===========================");
        array.set(2, 7);
        System.out.println(array);
        System.out.println("===========================");
        System.out.println("index of 4 is: " + array.indexOf(4));
        System.out.println(array);
        System.out.println("4th item is: " + array.get(3));
        System.out.println(array);
        System.out.println("size is: " + array.size());
        System.out.println(array);
        System.out.println("contains -10: " + array.contains(-10));
        System.out.println(array);
        System.out.println("contains 'abc': " + array.contains("abc"));
        array.removeAt(3);
        System.out.println(array);
        System.out.println("===========================");
        array.remove(-10);
        System.out.println(array);
        System.out.println("===========================");
    }

    private static void testDoubledLinkedList() {
        DoublyLinkedList<DummyPojo> list = new DoublyLinkedList<>();
        DummyPojo dPojo = new DummyPojo(0, "name 0");
        list.add(dPojo);
        list.add(new DummyPojo(1, "name 1"));
        list.add(new DummyPojo(2, "name 2"));
        list.add(new DummyPojo(3, "name 3"));
        list.add(new DummyPojo(4, "name 4"));
        list.add(dPojo);
        System.out.println(list);
        System.out.println("===========================");
        System.out.println("index of dPojo: " + list.indexOf(dPojo));
    }

    private static void testArrayQueue() {
        QueueWithArray<Integer> queue = new QueueWithArray<>(5);
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        System.out.println("===========================");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
        System.out.println("===========================");
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        System.out.println(queue);
        System.out.println("===========================");
        queue.enqueue(8);
        queue.enqueue(9);
        System.out.println(queue);
        System.out.println("===========================");
    }
}
