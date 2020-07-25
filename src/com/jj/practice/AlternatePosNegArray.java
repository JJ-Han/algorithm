package com.jj.practice;

// https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
// https://practice.geeksforgeeks.org/problems/array-of-alternate-ve-and-ve-nos/0

/*****************************************************************************************************
 * An array contains both positive and negative numbers in random order.
 * Rearrange the array elements so that positive and negative numbers are placed alternatively.
 * Number of positive and negative numbers need not be equal.
 * If there are more positive numbers they appear at the end of the array.
 * If there are more negative numbers, they too appear in the end of the array.
 * For example, if the input array is [-1, 2, -3, 4, 5, 6, -7, 8, 9],
 * then the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]
 *****************************************************************************************************/

public class AlternatePosNegArray {
    public static void altPosNeg (int[] a) {
        int t = -1, n = a.length;
        for (int i = 0; i < n; i++) {
            if (t >= 0)
                if (((a[i] >= 0) && (a[t] < 0))
                        || ((a[i] < 0) && (a[t] >= 0))) {
                    rotateArr(a, t, i);
                    if (i - t > 1)
                        t += 2;
                    else
                        t = -1;
                }
            if (t == -1)
                if (((a[i] >= 0) && ((i & 1)==1))
                        || ((a[i] < 0) && (i & 1)==0))
                    t = i;
        }
    }

    private static void rotateArr (int[] a, int i, int j) {
        int temp = a[j];
        while (i < j)
            a[j--] = a[j];
        a[i] = temp;
    }
}
