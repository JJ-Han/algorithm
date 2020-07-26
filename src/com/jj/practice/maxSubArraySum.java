package com.jj.practice;

// https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
// https://practice.geeksforgeeks.org/problems/kadanes-algorithm/0

/**********************************************************
 * Find the contiguous sub-array with maximum sum.
 **********************************************************/

public class maxSubArraySum {
    public static void main(String[] args)  {
        int[] a = {-2, 5, -1};
        int[] b = {-1, 2, 3, 4, 5};
        int[] c = {-2, -3, 4, -1, -2, 1, 5, -3};
        int[] d = {-1, -2, -3, -4};
        int[] e = {1, 2, 3, -2, 5};
        System.out.println("ans: "+  maxContSum(e));
    }

    // Kadane's Algorithm
    static int maxContSum(int[] a)
    {
        int max_so_far = a[0];
        int curr_max = a[0];
        int n = a.length;

        for (int i = 1; i < n; i++)
        {
            curr_max = Math.max(a[i], curr_max+a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
        }
        return max_so_far;
    }

    // same approach with indices
    public static int maxContSumIDX(int[] a) {
        int max = a[0], currMax = a[0], s = 0, start = 0, end = 0;
        for(int i = 1; i < a.length; i++) {
            if(currMax < 0) {
                currMax = a[i];
                s = i;
            }
            else    currMax += a[i];
            if(max < currMax) {
                start = s;
                end = i;
                max = currMax;
            }
        }
        System.out.println(start + " " + end);
        return max;
    }
}
