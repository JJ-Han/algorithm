package com.jj.practice;

// https://www.geeksforgeeks.org/sort-given-matrix/

public class Sort2DArray {
    public static void main(String[] args)  {
        int[][] a = {   {2, 8, 5},
                        {9, 1, 4},
                        {6, 7, 3}   };

        print2D(a);
        quickSort2D(a);
        System.out.println("Sorted");
        print2D(a);
        shuffle2D(a);
        System.out.println("Shuffle");
        print2D(a);
        quickSort2D(a);
        System.out.println("Sorted");
        print2D(a);

    }
    static void exch2D(int[][] a, int i, int j, int i1, int j1)   {
        int temp = a[i][j];
        a[i][j] = a[i1][j1];
        a[i1][j1] = temp;
    }

    static void print2D(int[][] a)  {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void quickSort2D(int[][] a)  {
        shuffle2D(a);
        quickSort2D(a, 0, a.length * a.length -1);
    }

    private static void quickSort2D(int[][] a, int lo, int hi)  {
        if (hi <= lo)   return;
        int p = partition2D(a, lo, hi);

        quickSort2D(a, lo, p-1);
        quickSort2D(a, p+1, hi);
    }


    static int partition2D(int[][] a, int lo, int hi)   {
        int i = lo, j = hi + 1;
        int n = a.length;
        int v = a[lo / n][lo % n];

        while (true)    {
            while (a[++i / n][i % n] < v)
                if (i == hi)    break;
            while (a[--j / n][j % n] > v)
                if (j == lo)    break;

            if (i >= j) break;
            exch2D(a, i/n, i%n, j/n, j%n);
        }
        exch2D(a, lo/n, lo%n, j/n, j%n);
        return j;
    }

    static void shuffle2D(int[][] a)    {
        for (int i = 0; i < a.length; i++)  {
            for (int j = 0; j < a[i].length; j++)   {
                int ri = (int) (Math.random() * (i + 1));
                int rj = (int) (Math.random() * ((ri != i) ? a[ri].length : j + 1));
                /*if(ri != i) rj = (int) (Math.random() * a[ri].length);
                else        rj = (int) (Math.random() * (j + 1));*/

                exch2D(a, i, j, ri, rj);
            }
        }
    }

    static void selectSort2D(int[][] a) {
        for (int i = 0; i < a.length; i++)  {
            for (int j = 0; j < a[i].length; j++)   {
                int mi = i, mj = j;
                for (int i2 = ((j==a.length-1) ? i+1 : i); i2 < a.length; i2++) {
                    for (int j2 = ((i==i2) ? j+1 : 0); j2 < a[i2].length; j2++)   {
                        if(a[mi][mj] > a[i2][j2])   {
                            mi = i2;
                            mj = j2;
                        }
                    }
                }
                exch2D(a, i, j, mi, mj);
                //print2D(a);
            }
        }
    }
}
