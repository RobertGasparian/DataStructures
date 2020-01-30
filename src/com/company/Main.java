package com.company;

public class Main {

    public static void main(String[] args) {
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
}
