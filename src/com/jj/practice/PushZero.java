package com.jj.practice;

// https://www.geeksforgeeks.org/move-zeroes-end-array/
// https://practice.geeksforgeeks.org/problems/move-all-zeroes-to-end-of-array/0

/****************************************************************************************************************
 * Given an array of random numbers, Push all the zero’s of a given array to the end of the array.              *
 * For example, if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0},                                       *
 * it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}.                                                   *
 * The order of all other elements should be same. Expected time complexity is O(n) and extra space is O(1).    *
 ****************************************************************************************************************/

public class PushZero {
    public static void main(String[] args) {
        int[] num = {0, 1, 2, 3};
        pushZero2(num);
        for (int i: num)
            System.out.print(i + " ");
    }

    public static void pushZero(int[] a) {
        int j = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] != 0) {
                if (i > j) exch(a, i, j);
                j++;
            }
    }
    public static void pushZero2(int[] a) {
        int i = -1, j = -1;
        while(i < a.length-1) {
            while (a[++i] == 0)
                if (i + 1 == a.length) return;
            if(i > ++j)     exch(a, i, j);
        }
    }

    public static void pushZerosToEnd(int[] a)      // geeks suggest
    {
        // Count of non-zero elements
        int count = 0;

        // Traverse the array. If element
        // encountered is non-zero, then
        // replace the element at index
        // 'count' with this element
        for (int i = 0; i < a.length; i++)
            if (a[i] != 0)

                // here count is incremented
                a[count++] = a[i];

        // Now all non-zero elements
        // have been shifted to front and
        // 'count' is set as index of first 0.
        // Make all elements 0 from count to end.
        while (count < a.length)
            a[count++] = 0;
    }


    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
