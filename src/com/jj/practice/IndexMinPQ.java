package com.jj.practice;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private final int maxN;
    private int n;
    private int[] pq;       // 1-based indexing
    private int[] qp;       // inverse indexing to recover origin position
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        pq = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];        // safe to set the size to maxN?; generic array with casting
        qp = new int[maxN + 1];                         // safe to set the size to maxN?
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public int size() {
        return n;
    }

    public void add(int i, Key key) {
        validateIndex(i);
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        qp[i] = ++n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public Key minKey() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    // return index of min
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int minIdx = pq[1];
        exch(1, n--);
        sink(1);
        qp[minIdx] = -1;
        keys[minIdx] = null;
        return minIdx;
    }

    public Key keyOf(int i) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    public void changeKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    // change key to lower value
    public void decreaseKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) <= 0) throw new IllegalArgumentException("new key should be greater than old key");
        keys[i] = key;
        swim(qp[i]);
    }

    // change key to higher value
    public void increaseKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) >= 0) throw new IllegalArgumentException("new key should be lower than old key");
        keys[i] = key;
        sink(qp[i]);
    }

    public void remove(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int idx = qp[i];
        exch(idx, n--);
        swim(idx);
        sink(idx);
        keys[idx] = null;
        qp[i] = -1;
    }

    // helper functions
    private void validateIndex(int i) {
        if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
        if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
}
