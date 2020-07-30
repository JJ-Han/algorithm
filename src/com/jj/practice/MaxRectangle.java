package com.jj.practice;

// https://practice.geeksforgeeks.org/problems/maximum-sum-rectangle/0
// https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/

public class MaxRectangle {
    public static void main(String[] args) {
        int[][] test1 = {   {1, 2, -1, -4, -20},        // 29
                            {-8, -3, 4, 2, 1},
                            {3, 8, 10, 1, 3},
                            {-4, -1, 1, 7, -6} };
        int[][] test2 = {   {-10, -21, 5},              // 55
                            {-15, 4, 13},
                            {17, -16, -4},
                            {24, 0, -7},
                            {-4, -22, -6},
                            {-20, -12, -12},
                            {12, 24, 18},
                            {-21, 7, -9},
                            {-6, 24, -11} };
        int[][] test3 = {};                             // edge case
        System.out.println(getMaxRectangle(test1));
        System.out.println(getMaxRectangle(test3));
    }

    public static int getMaxRectangle(int[][] a) {
        if(a == null || a.length == 0 || a[0].length == 0) return 0;        // edge case
        int r = a.length, c = a[0].length, max = a[0][0];
        for(int i = 0; i < r; i++) {
            int[] sum = new int[c];
            for (int j = i; j < r; j++) {
                for (int k = 0; k < c; k++)
                    sum[k] += a[j][k];
                max = Math.max(max, getMaxSum(sum));
            }
        }
        return max;
    }
    private static int getMaxSum(int[] a) {
        int max = a[0], currMax = a[0];
        for(int i = 1; i < a.length; i++) {
            currMax = Math.max(a[i], a[i] + currMax);
            max = Math.max(max, currMax);
        }
        return max;
    }

// TODO: to implement the same method finding indices, left top and right bottom corners of rectangle.
}
