package com.jj.practice;

// https://www.geeksforgeeks.org/array-rotation/
// https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements/0
// https://leetcode.com/problems/rotate-array/solution/

import java.util.Arrays;

/************************************************************************************
 * Given an unsorted array arr[] of size N, rotate it by D elements (clockwise).    *
 ************************************************************************************/

public class RotateArray {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8};
//        //int[] after = rotate3x(nums, 2);
//        for (int i : nums)
//            System.out.print(i + " ");
//
//        System.out.println("\nLEFT: ");
//        rotateArr(nums, 3);
//        for (int i : nums)
//            System.out.print(i + " ");
//
//
//        System.out.println("\nRIGHT: ");
//        rotateArrRgt(nums2, 2);
//        for (int i : nums2)
//            System.out.print(i + " ");
        System.out.println(gcd(2, 7));
    }

    public static int[] rotateArr2(int[] x, int d) {
        int n = x.length;
        int gcd = gcd(n, d);
        int[] a = new int[n];
        System.arraycopy(x, 0, a, 0, n);

        for (int i = 0; i < gcd; i++) {
            int temp = a[i];
            int j = i;

            for (int k = j+d; k % n != i; k = (j+d)%n ) {
                a[j] = a[k];
                j = k;
                //count++;
            }
            a[j] = temp;        //a[n-d+i] = temp;
        }
        return a;
    }

    public static void rotateArr(int[] a, int d) {
        int n = a.length;
        int gcd = gcd(n, d);

        for (int i = 0; i < gcd; i++) {
            int temp = a[i];
            int j = i;

            for (int k = j+d; k % n != i; k = (j+d)%n ) {
                a[j] = a[k];
                j = k;
            }
            a[j] = temp;        //a[n-d+i] = temp;
        }
    }

    public static void rotateArrRgt(int[] a, int d) {
        int n = a.length;
        int gcd = gcd(n, d);

        for (int i = n-1; i >= n-gcd; i--) {
            int temp = a[i];
            int j = i;

            for (int k = j-d; k % n != i; k = (j-d+n)%n) {
                a[j] = a[k];
                j = k;
            }
            a[j] = temp;        //a[n-d+i] = temp;
        }
    }

    // utility function for reverseArr
    public static int gcd(int a, int b) {
        while (b!=0)    {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    // reverse 3 times to implement rotate array
    public static int[] rotate3x (int[] x, int k) {
        int[] a = new int[x.length];
        System.arraycopy(x, 0, a, 0, x.length);
        reverseArr(a, 0, a.length);
        reverseArr(a, 0, k);
        reverseArr(a, k, a.length-k);
        return a;
    }

    private static void reverseArr(int[] a, int idx, int len) {
        for (int i = idx, j = idx + len; i < idx + len/2; i++)
            exch(a, i, --j);
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}


