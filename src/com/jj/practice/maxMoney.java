package com.jj.practice;

// https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
// https://practice.geeksforgeeks.org/problems/stickler-theif/0

/********************************************************************************************************
 * Given an array of positive numbers, find the maximum sum of a subsequence                            *
 * with the constraint that no 2 numbers in the sequence should be adjacent in the array.               *
 * So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).   *
 * Answer the question in most efficient way.                                                           *
 ********************************************************************************************************/

public class maxMoney {
    public static void main(String[] args) {
        int[] nums = { 5, 5, 10, 10, 100, 5, 2, 100 };      // 215
        System.out.println(getMaxMoney(nums));
    }

    public static int getMaxMoney (int[] a) {
        int n = a.length;
        int inc = a[0], exc = 0, temp;
        for (int i = 1; i < n; i++) {
            temp = Math.max(inc, exc);
            inc = exc + a[i];
            exc = temp;
        }
        return Math.max(inc, exc);
    }
}
