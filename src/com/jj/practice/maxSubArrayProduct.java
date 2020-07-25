package com.jj.practice;

// https://www.geeksforgeeks.org/maximum-product-subarray/
// https://practice.geeksforgeeks.org/problems/maximum-product-subarray/0

/****************************************************************************
 * Given an array that contains both positive and negative integers,        *
 * find the product of the maximum product subarray.                        *
 * Expected Time complexity is O(n) and only O(1) extra space can be used.  *
 ****************************************************************************/

public class maxSubArrayProduct {
    public static void main(String[] args) {
        int[] nums = {0, -1, -1};
        int[] nums2 = { 2, 3, 4, 5, -1, 0 };
        int[] nums3 = { 9, 0, 8, -1, -2, 0, -2, 6, -2};
        int[] nums4 = {1, 2, 3, -10, -1, 3};
        System.out.println(maxProduct(nums4));
    }

    public static int maxProduct (int[] a) {        // easy to understand, same speed as my solution
        int ans = a[0], min = ans, max = ans;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > 0) {
                max = Math.max(a[i], max * a[i]);
                min = Math.min(a[i], min * a[i]);
            }
            else if (a[i] == 0) {
                max = min = 0;
                continue;
            }
            else {
                int tmp = max;
                max = Math.max(a[i], min * a[i]);
                min = Math.min(a[i], tmp * a[i]);
            }
            ans = Math.max(ans, max);
            System.out.println("MAX " + max + " MIN " + min + " ANS " + ans + " at " + a[i]);
        }
        return ans;
    }

    public static int maxProduct2 (int[] a) {   // my algorithm
        int ans = 1, temp = 1, div = 0;
        if (a.length == 1) return a[0];
        boolean flag = false;
        for (int i : a) {
            if (i > 0)   {
                flag = true;
                ans *= i;
            }
            else if (i == 0) {
                if (ans < 0)    ans /= div;
                temp = Math.max(ans, temp);
                ans = 1;
                div = 0;
            }
            else {
                if (ans < 0) {
                    flag = true;
                    ans *= i;
                    temp = Math.max(ans, temp);
                }
                else {
                    temp = Math.max(ans, temp);
                    ans *= i;
                    if (div == 0)    div = ans;
                }
            }
            System.out.println("ans " + ans + " tmp " + temp);
        }
        if (!flag && temp == 1)  return 0;
        return Math.max((ans > 0) ? ans : ans/div, temp);
    }
}
