package com.jj.practice;

public class MergeArray {
    private static final int CUTOFF = 7;

    public static void main(String[] args) {
        /*
        int[] num = new int[100];     // 500000000
        for (int i = 0; i < num.length; i++)
            num[i] = i+1;

        shuffle(num);
        System.out.println(isSorted(num));
        //System.out.println(Arrays.toString(num));
        long startTime = System.currentTimeMillis();
        sortBU(num);
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + timeDiff );
        System.out.println(isSorted(num));
        //System.out.println(Arrays.toString(num));

         */
    }

    public static void sortBU(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        for (int len = 1; len < n; len <<= 1) {
            int count = 0;
            for (int lo = 0; lo < n - len; lo += len + len) {
                count++;
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                if(a[mid] > a[mid+1])   merge(a, aux, lo, mid, hi, true);
            }
        }
    }

    public static void sort(int[] a) {
        int[] aux = a.clone();
        sort(aux, a, 0, a.length-1);
    }
    private static void sort(int[] aux, int[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);

        if (aux[mid] <= aux[mid+1]) {
            System.arraycopy(aux, lo, a, lo, hi-lo+1);
            return;
        }
        merge(a, aux, lo, mid, hi, false); // WORKS NOW
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, boolean isBU) {
        if (isBU) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        /*
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
         */
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
        }
    }

    // utility
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i-1] > a[i])  return false;
        return true;
    }

    public static void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int r = (int) (Math.random() * (i+1));
            exch(a, i, r);
        }
    }

    public static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++)
            for (int j = i; j > lo && a[j-1] > a[j]; j--) {
                exch(a, j, j-1);
            }
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
