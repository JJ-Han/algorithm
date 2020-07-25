package com.jj.practice;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PQ<T> implements Iterable<T> {
    private T[] pq;
    private int n;
    private Comparator<T> comparator;

    // constructor
    public PQ(int initCapacity) {
        pq = (T[]) new Object[initCapacity + 1];
        n = 0;
    }
    public PQ() {
        this(1);
    }

    public PQ(int initCapacity, Comparator<T> comparator) {
        this.comparator = comparator;
        pq = (T[]) new Object[initCapacity + 1];
        n = 0;
    }
    public PQ(Comparator<T> comparator) {
        this(1, comparator);
    }

    public PQ(T[] keys) {
        n = keys.length;
        pq = (T[]) new Object[n + 1];
        System.arraycopy(keys, 0, pq, 1, n);
        for (int k = n/2; k >= 1; k--)
            sink(k);
    }
    public PQ(T[] keys, Comparator<T> comparator) {
        this.comparator = comparator;
        n = keys.length;
        pq = (T[]) new Object[n + 1];
        System.arraycopy(keys, 0, pq, 1, n);
        for (int k = n/2; k >= 1; k--)
            sink(k);
    }

    public boolean isEmpty()    { return n == 0; }
    //public int size()           { return n; }

    public T peek() {
        if (isEmpty())  throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(pq, 1, temp, 1, n);
        pq = temp;
    }

    public void add(T x) {
        if (n == pq.length -1)  resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    public T poll() {
        if (isEmpty())  throw new NoSuchElementException("Priority queue underflow");
        T root = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        if (n > 0 && n == (pq.length -1) / 4)   resize(pq.length / 2);
        return root;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j))    break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null)     return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
        else                        return comparator.compare(pq[i], pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public Iterator<T> iterator() {
        return new HeapIterator();
    }
    private class HeapIterator implements Iterator<T> {
        private PQ<T> copy;

        public HeapIterator() {
            if (comparator == null)     copy = new PQ<T>(n);
            else                        copy = new PQ<T>(n, comparator);
            for (int i = 1; i <= n; i++)
                copy.add(pq[i]);
        }

        public boolean hasNext() { return !copy.isEmpty(); }
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.poll();
        }
    }


    public static void main(String[] args) {
        Integer[] num = {-999, 1903, 1, 59, 170, -96, -5, 210, 999, -56};
        PQ<Integer> pq = new PQ<>(num, Comparator.naturalOrder());
        for (int i : pq)
            System.out.print(i + " ");
        System.out.println();

        pq = new PQ<>(num);
        for (int i : pq)
            System.out.print(i + " ");
    }
}