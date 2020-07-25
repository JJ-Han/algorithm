package com.jj.practice;
// https://practice.geeksforgeeks.org/problems/sorted-matrix/0

public class Sort2D {
    public static void main(String[] args) {
        int n = 200;
        Integer[][] test1 = createInt2D(n);
        shuffle2D(test1);

        Integer[][] test2 = new Integer[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(test1[i], 0, test2[i], 0, n);
        System.out.println(isSorted(test2));

        long startTime1 = System.currentTimeMillis();
        selectionSort(test2);
        long endTime1 = System.currentTimeMillis();
        System.out.print(isSorted(test2) + " ");
        System.out.println(endTime1 - startTime1);

        for (int i = 0; i < n; i++)
            System.arraycopy(test1[i], 0, test2[i], 0, n);
        System.out.println(isSorted(test2));

        long startTime2 = System.currentTimeMillis();
        selectionSort2(test2);
        long endTime2 = System.currentTimeMillis();
        System.out.print(isSorted(test2) + " ");
        System.out.println(endTime2 - startTime2);

        for (int i = 0; i < n; i++)
            System.arraycopy(test1[i], 0, test2[i], 0, n);
        System.out.println(isSorted(test2));

        long startTime3 = System.currentTimeMillis();
        quickSort2D(test2);
        long endTime3 = System.currentTimeMillis();
        System.out.print(isSorted(test2) + " ");
        System.out.println(endTime3 - startTime3);
    }

    public static Integer[][] createInt2D(int n) {
        Integer[][] a = new Integer[n][n];
        int k = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = ++k;

        return a;
    }

    public static void quickSort2D(Comparable[][] a)  {
        shuffle2D(a);
        quickSort2D(a, 0, a.length * a.length -1);
    }

    private static void quickSort2D(Comparable[][] a, int lo, int hi)  {
        if (hi <= lo)   return;
        int j = partition2D(a, lo, hi);

        quickSort2D(a, lo, j-1);
        quickSort2D(a, j+1, hi);
    }

    private static int partition2D(Comparable[][] a, int lo, int hi)   {
        int i = lo, j = hi + 1;
        int n = a.length;
        Comparable v = a[lo / n][lo % n];

        while (true)    {
            while (a[++i / n][i % n].compareTo(v) < 0)
                if (i == hi)    break;
            while (a[--j / n][j % n].compareTo(v) > 0)
                if (j == lo)    break;

            if (i >= j) break;
            exch2D(a, i/n, i%n, j/n, j%n);
        }
        exch2D(a, lo/n, lo%n, j/n, j%n);
        return j;
    }

    public static void shuffle2D(Comparable[][] a)    {
        for (int i = 0; i < a.length; i++)  {
            for (int j = 0; j < a[i].length; j++)   {
                int ri = (int) (Math.random() * (i + 1));
                int rj = (int) (Math.random() * ((ri != i) ? a[ri].length : j + 1));

                exch2D(a, i, j, ri, rj);
            }
        }
    }

    // SelectionSort
    public static void selectionSort(Comparable[][] a) {
        for (int i = 0; i < a.length; i++)  {
            for (int j = 0; j < a[i].length; j++)   {
                int mi = i, mj = j;
                for (int i2 = ((j==a.length-1) ? i+1 : i); i2 < a.length; i2++) {
                    for (int j2 = ((i==i2) ? j+1 : 0); j2 < a[i2].length; j2++)   {
                        if(a[mi][mj].compareTo(a[i2][j2]) > 0)   {
                            mi = i2;
                            mj = j2;
                        }
                    }
                }
                exch2D(a, i, j, mi, mj);
            }
        }
    }

    // much slower than 1st implement
    public static void selectionSort2(Comparable[][] a) {
        int n = a.length;
        for (int i = 0; i < a.length; i ++)
            for (int j = 0; j < a[i].length; j++) {
                Comparable min = a[i][j];
                int i2 = i, j2 = j;
                for (int k = i*n + j + 1; k < n*n; k++) {
                    if (min.compareTo(a[k/n][k%n]) > 0) {
                        i2 = k/n;
                        j2 = k%n;
                        min = a[i2][j2];
                    }
                }
                exch2D(a, i, j, i2, j2);
            }
    }

    private static void exch2D(Comparable[][] a, int i, int j, int i2, int j2) {
        Comparable temp = a[i][j];
        a[i][j] = a[i2][j2];
        a[i2][j2] = temp;
    }

    public static void print2D(Comparable[][] a)  {
        for (Comparable[] row : a)
            for (Comparable i : row)
                System.out.print(i + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[][] a) {
        Comparable i = a[0][0];
        for (Comparable[] row : a)
            for (Comparable j : row) {
                if (i.compareTo(j) > 0) return false;
                i = j;
            }
        return true;
    }
}
