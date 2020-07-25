package com.jj.practice;

//https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/

// O(n^2)
// https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence/0

// O(nlogn)
// https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-using-binary-indexed-tree/
// https://e2718281828459045.wordpress.com/2013/09/06/maximum-sum-increasing-subsequence/
// http://iamayushanand.blogspot.com/2016/02/maximum-sum-increasing-subsequenceo-n.html
public class MaxSumIncSubsequence {
    public static void main(String[] args) {
        int[] num = {1, 101, 2, 3, 100, 4, 5};
        int[] num2 = {1, 6, 5, 15, 10};
        System.out.println(msis(num2));
    }

    // O(n^2)
    public static int msis(int[] a) {
        int[] msis = new int[a.length];
        System.arraycopy(a, 0, msis, 0, a.length);

        for (int i = 1; i < a.length; i++)
            for (int j = 0; j < i; j++)
                if (a[i] > a[j] && msis[i] < msis[j] + a[i])
                    msis[i] = msis[j] + a[i];
        int max = 0;
        for (int i : msis)
            if (max < i)    max = i;

        return max;
    }
}
