package com.company;

public class UnionFind {

    //The number of elements in the unioin find
    private int size;

    //Used to track the sizes of each of the components
    private int[] compSizes;

    //parentIds[i] points to the parent of i, if parentIds[i] = i then i is a root node
    private int[] parentIds;

    //Tracks the number of components in the union find
    private int componentsNumber;

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        this .size = componentsNumber = size;
        compSizes = new int[size];
        parentIds = new int[size];

        for (int i = 0; i < size; i++) {
            parentIds[i] = i;
            compSizes[i] = 1;
        }
    }

    //Find which component/set 'p' belongs to, takes amortized constant time (A(n))
    public int find(int p) {
        //Find the root of the component/set
        int root = p;
        while(root != parentIds[root]) {
            root = parentIds[root];
        }
        //Compress the path leading back to the root
        //Doing this operation is called "path compression"
        //and is what gives us amortized constant time complexity
        while (p != root) {
            int next = parentIds[p];
            parentIds[p] = root;
            p = next;
        }

        return root;
    }

    //Return whether or not the elements 'p' and 'q'
    //are in the same component/set
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //Returns the size of the components/set 'p' belongs to
    public int componentSize(int p) {
        return compSizes[find(p)];
    }

    //Return the number of elements in this UnionFind/Disjoint set
    public int size() {
        return size;
    }

    //Returns the number of remaining components/sets
    public int components() {
        return componentsNumber;
    }

    //Unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        //Check if these elements are already in the same group
        if (root1 == root2) return;

        //Merge 2 components/sets together/
        //NOTE: Merge smaller component/set into the larger one.
        if (compSizes[root1] < compSizes[root2]) {
            compSizes[root2] += compSizes[root1];
            parentIds[root1] = parentIds[root2];
        } else {
            compSizes[root1] += compSizes[root2];
            parentIds[root2] = parentIds[root1];
        }

        //Since the roots found are different we know that the number
        //of components/sets has decreased by one
        componentsNumber--;
    }
 }
