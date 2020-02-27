package com.company;

import java.util.*;

public class PQueue<T extends Comparable<T>> {
    //The number of elements currently inside the heap
    private int heapSize = 0;

    //The internal capacity of the heap
    private int heapCapacity = 0;

    //A dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // This map keeps track of the possible indices a particular
    // node value is found in the heap. Having this mapping lets us have
    // O(log(n)) removal and O(1) element containment check
    // at the cost of some additional space and minor overhead
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    //Construct and initially empty queue
    public PQueue() {
        this(1);
    }

    //Construct a priority queue with an initial capacity
    public PQueue(int size) {
        heap = new ArrayList<>(size);
    }

    public PQueue(T... elements) {
        heapSize = heapCapacity = elements.length;
        heap = new ArrayList<>(heapCapacity);

        //Place all elements in heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elements[i], i);
            heap.add(elements[i]);
        }

        //Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    //Priority queue construction, O(nlog(n))
    public PQueue(Collection<T> elements) {
        this(elements.size());
        for (T elem : elements) {
            add(elem);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    //Clears everything inside heap, O(n)
    public void clear() {
        for (int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    public int size() {
        return heapSize;
    }

    // Returns the value of element with the lowest
    // priority in this priority queue. If the priority
    // queue is empty null is returned.
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    //Removes the root of the heap, O(log(n))
    public T poll() {
        return removeAt(0);
    }

    //Test if an element is in heap, O(1)
    public boolean contains(T element) {
        //Map lookup to check containment, O(1)
        if (element == null) return false;
        return map.containsKey(element);

        //Linear scan to check containment, O(n)
//        for (int i = 0; i < heapSize; i++) {
//            if (heap.get(i).equals(element)) {
//                return true;
//            }
//        }
//        return false;
    }

    // Adds an element to the priority queue, the
    // element must not be null, O(log(n))
    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException();
        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        } else {
            heap.add(elem);
            heapCapacity++;
        }

        mapAdd(elem, heapSize);
        swim(heapSize);
        heapSize++;
    }

    // Tests if the value if node i <= node j
    // this method assumes i & j are valid indices, O(1)
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    //Bottom up node swim, O(log(n))
    private void swim(int k) {
        //Take the index of the next parent node WRT to k
        // NOTE: heap start index is 0. If it is 1 then (k/2)
        int parent = (k - 1) / 2;

        while (k > 0 && less(k, parent)) {
            //change k with parent
            swap(parent, k);
            k = parent;

            //Grab the index of the next parent node WRT to k
            parent = (k - 1) / 2;
        }
    }

    //Top down node sink, O(log(n))
    private void sink(int k) {
        while (true) {
            int left = (2 * k) + 1;  // Left node
            int right = (2 * k) + 2;  // Right node
            int smallest = left;  // Assume left is the smallest node of the two children

            //Find which is smaller left or right
            //If right is smaller set smaller to be right
            if (right < heapSize && less(right, left)) {
                smallest = right;
            }

            //Stop if we are outside the bounds of the tree
            //or stop early if we cannot sink k anymore
            if (left >= heapSize || less(k, smallest)) {
                break;
            }

            //Move down the tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    //Swap two nodes. Assumes i & j are valid, O(1)
    private void swap(int i, int j) {
        T iItem = heap.get(i);
        T jItem = heap.get(j);

        heap.set(i, jItem);
        heap.set(j, iItem);

        mapSwap(iItem, jItem, i, j);
    }

    //Removes a particular element in the heap, O(log(n))
    public boolean remove(T element) {
        if (element == null) return false;
        //Logarithmic removal with map, O(log(n))
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        //Linear removal
//        for (int i = 0; i < heapSize; i++) {
//            if (element.equals(heap.get(i))) {
//                removeAt(i);
//                return true;
//            }
//        }
        return false;
    }

    private T removeAt(int i) {
        if (isEmpty()) return null;

        heapSize--;
        T removeData = heap.get(i);
        swap(i, heapSize);

        //Obliterate the value
        heap.set(heapSize, null);
        mapRemove(removeData, heapSize);

        //Remove last element
        if (i == heapSize) return removeData;

        T elem = heap.get(i);

        int parent = (i - 1) / 2; //Parent node

        //Check if we need to swim() or sink()
        if (i > 0 && less(i, parent)) {
            swim(i);
        } else {
            sink(i);
        }

        return removeData;
    }

    //Recursively checks if this heap is a min heap
    //This method is just for testing purposes to make sure
    //that heap invariant is still being maintained
    //Call with k = 0 to start at the root
    public boolean isMinHeap(int k) {
        //if we are outside of the bounds of the heap return true
        if (k >= heapSize) return true;

        int left = (2 * k) + 1;
        int right = (2 * k) + 2;

        //Make sure that the current node k is less than
        //both of its children if they exist, otherwise return false to indicate
        //an invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        //Recurse on both children to make sure they are also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    //Add a node value and its index to the map
    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);

        //Check if we need to insert new value or add to existing set
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
        } else {
            set.add(index);
        }
    }

    //Remove the index at a given value, O(log(n))
    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) {
            set.remove(index); //TreeSet take O(log(n)) removal time
            if (set.size() == 0) map.remove(value);
        }
    }

    //Extract an index position for given value
    //NOTE: If a value exists multiple times in the heap the highest
    //index is returned (this has arbitrarily been chosen)
    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null)  return set.last();
        return null;
    }

    //Exchange the index of two nodes internally within the map
    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
