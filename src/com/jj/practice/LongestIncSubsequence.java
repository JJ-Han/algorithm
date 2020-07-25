package com.jj.practice;

// https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
// https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence/0

// NlogN solution...
// https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/

// https://leetcode.com/problems/longest-increasing-subsequence/solution/

// Theorem. [Erdös-Szekeres 1935] Any sequence of n^2 + 1 distinct real
// numbers either has an increasing or decreasing subsequence of size n + 1.

import java.util.Arrays;

public class LongestIncSubsequence {
    static int ans;
    public static void main(String[] args) {
        int[] num = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int[] num2 = { 5, 8, 3, 7, 9, 1};
        int[] num3 = {6, 3, 5, 10, 11, 2, 9, 14, 13, 7, 4, 8, 12};
        int[] num4 = {15, 27, 14, 38, 63, 55, 46, 65, 85};

        System.out.println(lenLISMine(num4));
        /*
        int[] numR = new int[100000000];
        for (int i = 0; i < numR.length; i++)
            numR[i] = i;


        shuffle(numR);
        long startTime1 = System.currentTimeMillis();
        System.out.println(lenLIS(numR));
        long endTime1 = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        System.out.println(lenLISMine(numR));
        long endTime2 = System.currentTimeMillis();

        long timeDiff1 = endTime1 - startTime1;
        long timeDiff2 = endTime2 - startTime2;

        System.out.println("SelectionSort Elapsed time in milliseconds: " + timeDiff1 );
        System.out.println("InsertionSort Elapsed time in milliseconds: " + timeDiff2 );
*/
    }

    // my implement much faster
    public static int lenLISMine(int[] a) {
        int[] tails = new int[a.length];
        int idx = 0;
        tails[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            int lo = 0, hi = idx;
            while (lo <= hi) {
                int mid = lo + (hi-lo)/2;
                if (a[i] > tails[mid])  lo = mid + 1;
                else                    hi = mid - 1;
            }
            tails[lo] = a[i];
            if (lo > idx)  idx++;
        }
        return idx+1;       // return size = idx + 1
    }

    // nlogn
    public static int lenLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int lo = 0, hi = size;
            while (lo != hi) {
                int mid = lo + (hi-lo) / 2;
                if (tails[mid] < x) lo = mid + 1;
                else                hi = mid;
            }
            tails[lo] = x;
            if (lo == size) ++size;
        }
        return size;
    }


    // n space DP solution
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);       // i 까지 longest LIS 구하기
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        for (int i : dp)
            System.out.print(i + " ");
        System.out.println();
        return maxans;
    }

    // DP solution with n^2 extra space
    public static int lenLISMemo(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];
        for (int[] l : memo)
            Arrays.fill(l, -1);
        int ans = lenLISMemo(nums, -1, 0, memo);
        /*
        System.out.println("ARRAY final value: ");

        for (int[] l : memo) {
            for (int i : l)
                System.out.print(i + " ");

            System.out.println();
        }*/
        return ans;
    }
    private static int lenLISMemo(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            System.out.println("SAVED prev: " +(previndex) + " " + "curpos: " + curpos + " " +memo[previndex + 1][curpos]);
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lenLISMemo(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lenLISMemo(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        System.out.println("prev: " +(previndex) + " " + "curpos: " + curpos + " taken: " +taken + " notTaken: " + nottaken + " / MEMO= " +memo[previndex + 1][curpos]);
        return memo[previndex + 1][curpos];
    }



    // 2^n TC brute force method
    public static int lenLIS_BF(int[] a) {
        return lenLIS_BF(a, -1, 0);
    }

    private static int lenLIS_BF(int[] a, int prev, int curr) {
        if (curr == a.length)   return 0;

        int taken = 0;
        if (a[curr] > prev)
            taken = 1 + lenLIS_BF(a, a[curr], curr +1);
        int notTaken = lenLIS_BF(a, prev, curr+1);
        System.out.println("prev=" + prev + " curr=" +curr + " taken=" +taken + " notTaken=" +notTaken);
        return Math.max(taken, notTaken);
    }

    public static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int r = (int) (Math.random() * (i + 1));
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}