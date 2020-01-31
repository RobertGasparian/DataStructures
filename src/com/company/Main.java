package com.company;

public class Main {

    public static void main(String[] args) {
//        testArray();
        testDoubledLinkedList();
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
}
