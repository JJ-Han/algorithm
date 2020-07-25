package com.jj.practice;

public class Heapsort {
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object temp = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = temp;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0)   return false;
        return true;
    }

    public static void shuffle(Object[] a) {
        for (int i = 1; i < a.length; i++) {
            int r = ((int) ((1 + i) * Math.random())) +1;
            exch(a, i, r);
        }
    }

    public static void main(String[] args) {
        Integer[] pq = new Integer[100];
        for (int i = 0; i < 100; i++)
            pq[i] = i+1;
        shuffle(pq);
        System.out.println(isSorted(pq));
        sort(pq);
        System.out.println(isSorted(pq));
    }
}
