package com.jj.practice;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ() {
        pq = (Key[]) new Object[2];
        n = 0;
    }

    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[2];
        n = 0;
    }

    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length+1];
        for (int i = 0; i < n; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key max() {
        if (isEmpty())  throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Object[capacity];
        for(int i = 1; i <= n; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    public void insert(Key x) {
        if (n == pq.length-1)   resize(2*pq.length);
        pq[++n] = x;
        swim(n);
    }

    public Key delMax() {
        if(isEmpty())   throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        if(n > 0 && n == (pq.length-1)/4)   resize(pq.length/2);
        return max;
    }

    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while(2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j))    break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if(comparator == null)  return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        else return comparator.compare(pq[i], pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    // is pq[1..n] a max heap?
    private boolean isMaxHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && less(k, left))  return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    // need to implement
    public boolean isSorted() {
        for (int i = 2; i <= n; i++)
            if (less(i-1, i))    return false;
        return true;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            sb.append(pq[i] + " ");
        System.out.println(sb.toString());
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        // need to copy the pq array because iterate will call delMax() which deletes data
        private MaxPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<Key>(size());
            else                    copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()    { return !copy.isEmpty(); }
        public void remove()        { throw new UnsupportedOperationException(); }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    public static void exch(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shuffle(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            int r = (int) ((1 + i) * Math.random());
            exch(a, i, r);
        }
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[10];
        for (int i = 0; i < test.length; i++)
            test[i] = i;
        shuffle(test);
        MaxPQ<Integer> que = new MaxPQ(test);
        System.out.println(que.isSorted());

        que.print();
/*        for (int i : que)
            System.out.print(i + " ");
        System.out.println(que.delMax());*/
    }

}
