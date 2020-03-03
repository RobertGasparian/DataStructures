package com.company;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> {

    //Tracks the number of nodes in this BST
    private int nodeCount = 0;

    //This BST is a rooted tree so we maintain a handle on the root node
    private Node root = null;

    //Internal node containing node references
    //and the actual node data
    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T elem) {
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    //Check if tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    //Get the number of nodes in this binary tree
    public int size() {
        return nodeCount;
    }

    //Add an element to this binary tree. Returns true
    //if we successfully perform an insertion
    public boolean add(T elem) {
        //Check if the value already exists in this
        //binary tree, if it does ignore adding it
        if (contains(elem)) {
            return false;
        //Otherwise add this element to the binary tree
        } else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    //Private method to recursively add a value in the binary tree
    private Node add(Node node, T elem) {
        //Base case: found a leaf node
        if (node == null) {
            node = new Node(null, null, elem);
        } else {
            //Place lower element values in the left subtree
            if (elem.compareTo(node.data) < 0) {
                node.left = add(node.left, elem);
            } else {
                node.right = add(node.right, elem);
            }
        }
        return node;
    }

    //Remove a value from this binary tree, if it exists
    public boolean remove(T elem) {
        //Make sure the node we want to remove
        //actually exists before we remove it
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    //Private method to recursively remove a value in the binary tree
    private Node remove(Node node, T elem) {
        //Base case: found a leaf node
        if(node == null) return null;

        int compering = elem.compareTo(node.data);

        //Dig into left subtree, the value we're looking
        //for is smaller than the current value
        if(compering < 0) {
            node.left = remove(node.left, elem);

        //Dig into right subtree, the value we're looking
        //is greater than the current value
        } else if (compering > 0) {
            node.right = remove(node.right, elem);

        //Found the needed node for removing
        } else {
            //This is the case with only a right subtree or
            //no subtree at all. In this situation just swap
            //the node we wish with to remove with its right child.
            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            //This is the case with only a left subtree or
            //no subtree at all. In this situation just swap
            //the node we wish with to remove with its left child.
            } else if (node.right == null) {
                Node leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;
            //This is the case when we have both left and right subtrees
            //When removing a node like this from binary tree
            //the successor of the node being removed can either be the largest
            //value in the left subtree or the smallest value int the right subtree.
            //In this implementation I have decided to find the smallest value in the right
            //which can be found by traversing(digging) as far left as possible in the right subtree.
            } else {
                //Find the leftmost node in the right subtree
                Node temp = digLeft(node.right);

                //Swap the data
                node.data = temp.data;

                //Go into the right subtree and remove the leftmost node we found
                //and swapped data with. This prevents us for having two nodes
                //in out tree with the same value.
                node.right = remove(node.right, temp.data);

                //If we want to find the largest node in the left subtree:
//                Node temp = digRight(node.left);
//                node.data = temp.data;
//                node.left = remove(node.left, temp.data);
            }
        }

        return node;
    }

    //Helper method to find leftmost node
    private Node digLeft(Node node) {
        Node cur = node;
        while (cur.left != null)
            cur = cur.left;
        return cur;
    }

    //Helper method to find rightmost node
    private Node digRight(Node node) {
        Node cur = node;
        while (cur.right != null)
            cur = cur.right;
        return cur;
    }

    //returns true if the element exists in the tree
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    //private recursive method to find an element in the tree
    private boolean contains(Node node, T elem) {
        //Base case: reached bottom, value not found
        if (node == null) return false;

        int cmp = elem.compareTo(node.data);

        //Dig into the left subtree because the value we're
        //looking for is smaller than the current value
        if (cmp < 0) return contains(node.right, elem);

        //Dig into the right subtree because the value we're
        //looking for is greater than the current value
        else if (cmp > 0) return contains(node.right, elem);

        //We fount the value we were looking for
        else return true;
    }

    //Computes the height of the tree, O(n)
    public int height() {
        return height(root);
    }

    //Recursive helper method to compute the height of the tree
    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}
