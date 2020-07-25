package com.jj.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class BST<Key extends Comparable <Key>, Value> {
    private Node root;

    private class Node  {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BST()    {}

    public boolean isEmpty()    {return size() == 0;}

    public int size()   {return size(root);}

    private int size(Node x)    {
        if (x == null)  return 0;
        else return x.size;
    }

    public boolean contains(Key key)    {
        if (key == null)    throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key)   {
        return get(root, key);
    }
    private Value get(Node x, Key key)  {
        if (key == null)    throw new IllegalArgumentException("calls  get() with a null key");
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return get(x.left, key);
        else if (cmp > 0)   return get(x.right, key);
        else                return x.val;
    }

    public void put(Key key, Value val) {
        if (key == null)    throw new IllegalArgumentException("calls put() with a null key");
        if (val == null)    {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val)    {
        if (x == null)  return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   x.left = put(x.left, key, val);
        else if (cmp > 0)   x.right = put(x.right, key, val);
        else                x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {
        if (isEmpty())  throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }
    private Node deleteMin(Node x)  {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        if(isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }
    private Node deleteMax(Node x)  {
        if (x.right == null)    return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key)    {
        if(key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }
    private Node delete(Node x, Key key)    {
        if (x == null)  return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   x.left = delete(x.left, key);
        else if (cmp > 0)   x.right = delete(x.right, key);
        else    {
            if (x.right == null)    return x.left;
            if (x.left  == null)    return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    /* delete nodes greater than or equal to given key
    public Node deleteGrt(Node x, Key key) {
        if (x == null)                  return null;
        if (x.data.compareTo(key) >= 0) return deleteNode(root.left, x);
        x.right = deleteNode(x.right, key);
        return root;
    }
     */

    public Key min()    {
        if (isEmpty())  throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x)    {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max()    {
        if (isEmpty())  throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x)    {
        if (x.right == null)    return x;
        else                    return max(x.right);
    }

    public Key floor(Key key)   {
        if (key == null)    throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty())      throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null)      throw new NoSuchElementException("argument to floor() is to small");
        else return x.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)   return x;
        if (cmp <  0)   return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)  return t;
        else            return x;
    }

    public Key ceiling(Key key) {
        if (key == null)    throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty())      throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null)      throw new NoSuchElementException("argument to ceiling() is too large");
        else return x.key;
    }
    private Node ceiling(Node x, Key key)   {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if      (leftSize > rank) return select(x.left,  rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else                      return x.key;
    }

    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/solution/
    // https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/
    // iterative with stack
    public Key kthLargest(int k) {
        Stack<Node> st = new Stack<>();
        Node curr = root;
        while(true) {
            while(curr != null) {
                st.push(curr);
                curr = curr.right;
            }
            curr = st.pop();
            if(--k == 0) return curr.key;
            curr = curr.left;
        }
    }
    // recursive
    public Key kthLargestRec(int k) {
        ArrayList<Key> al = new ArrayList<>(k);
        kthLargestRec(root, k, al);
        return al.get(k-1);
    }
    private void kthLargestRec(Node x, int k, ArrayList<Key> al) {
        if(x != null && al.size() < k) {
            kthLargestRec(x.right, k, al);
            al.add(x.key);
            if(--k == 0)    return;
            kthLargestRec(x.left, k, al);
        }
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
    public Iterable<Key> levelOrder()   {
        Queue<Key> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty())    {
            Node x = queue.dequeue();
            if (x == null)  continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    // a 1-node tree has height 0
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    public void inorder()   {inorder(root);}
    private void inorder(Node x)    {
        if (x != null)  {
            inorder(x.left);
            System.out.print(x.key + " " + x.val + "\n");
            inorder(x.right);
        }
    }

    // Morris Traversal
    public void printInOrder() {
        Node curr = root;
        StringBuilder sb = new StringBuilder();
        while(curr != null) {
            if(curr.left == null) {
                sb.append(curr.val).append(" ");
                curr = curr.right;
            }
            else {
                Node left = curr.left;
                while(left != null && left != curr) curr = curr.right;
                if(left.right == null) {
                    left.right = curr;
                    curr = curr.left;
                }
                else {
                    left.right = null;
                    sb.append(curr.val);
                    curr = curr.right;
                }
            }
        }
    }

    public void preOrder() { preOrder(root); }
    private void preOrder(Node x) {
        if (x != null) {
            System.out.print(x.key + " " + x.val + "\n");
            preOrder(x.left);
            preOrder(x.right);
        }
    }

    // https://www.geeksforgeeks.org/print-left-view-binary-tree/
    // https://leetcode.com/problems/binary-tree-right-side-view/solution/
    public Iterable<Key> leftOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            keys.enqueue(queue.peek().key);
            while (size-- > 0) {
                Node x = queue.dequeue();
                if (x.left != null) queue.enqueue(x.left);
                if (x.right != null) queue.enqueue(x.right);
            }
        }
        return keys;
    }

    /*  original method from https://www.techiedelight.com/print-left-view-of-binary-tree/
    public Iterable<Key> leftOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int i = 0;
            while (i++ < size) {
                Node x = queue.dequeue();
                if (i == 1) keys.enqueue(x.key);
                if (x.left != null) queue.enqueue(x.left);
                if (x.right != null) queue.enqueue(x.right);
            }
        }
        return keys;
    }
     */



    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        try (Scanner sc = new Scanner(new File("C:\\Users\\jjhan\\IdeaProjects\\Algorithm\\Data\\tinyST.txt"))) {
            for (int i = 0; sc.hasNext(); i++)  {
                String key = sc.next();
                st.put(key, i);
            }
            for (String s: st.levelOrder())
                System.out.println(s + " " + st.get(s));
            System.out.println();

            for (String s : st.leftOrder())
                System.out.println(s + " " + st.get(s));
            System.out.println();

            /*
            for (String s: st.keys())
                System.out.println(s + " " + st.get(s));
            System.out.println("test");
            st.inorder();
            System.out.println();
            System.out.println(st.isBST());*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
