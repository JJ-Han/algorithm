package com.jj.practice;

public class QuickSort {
    private QuickSort() {

    }

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // k index (0 <= k < a.length)
    private static Comparable select(Comparable[] a, int k) {
        int lo = 0, hi = a.length-1;
        while (lo < hi) {
            int i = partition(a, lo, hi);
            if (i > k) lo = i + 1;
            else if (i < k) hi = i - 1;
            else return a[i];
        }
        return a[lo];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable v = a[lo];       // pivot
        while (true) {
            while (a[++i].compareTo(v) < 0)
                if (i == hi) break;

            while (v.compareTo(a[--j]) < 0)
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void shuffle(Comparable[] a) {
        for (int i = a.length-1; i > 0; i--) {
            int r = (int) (Math.random() * i);
            exch(a, i, r);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        for (int t = 0; t < 100; t++) {
            Integer[] test = new Integer[100000];
            for (int i = 0; i < test.length; i++) {
                test[i] = (int) (Math.random() * 100000);
            }
            sort(test);
            if (!isSorted(test)) System.out.println("FAIL");
        }
        System.out.println("PASS");
    }
}
