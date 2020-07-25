package com.jj.practice;

// https://leetcode.com/problems/unique-binary-search-trees/solution/
// https://practice.geeksforgeeks.org/problems/unique-bsts/0


public class UniqueBST {
    private static long[] DP = new long[51];

    public static void main(String[] args) {
        System.out.println(findNum(25));
        //System.out.println(findNumFast(25));
    }

    public static long findNum(int n) {
        if(DP[n] != 0)    return DP[n];
        int i = 0;
        while(DP[i] != 0)   i++;
        if(i < 2) {
            DP[0] = 1;
            DP[1] = 1;
            i = 2;
        }
        for(; i <= n; i++)
            for(int j = 1; j <= i; j++)
                DP[i] += DP[j-1] * DP[i-j];
        return DP[n];
    }
    // C(0) = 1;
    public static long findNumFast(int n) {
        if(DP[n] != 0)    return DP[n];
        int i = 0;
        while(DP[i] != 0)   i++;
        if(i == 0)  DP[i++] = 1;
        for (; i <= n; ++i)
            DP[i] = DP[i-1] * 2 * (2 * i - 1) / (i + 1);
        return DP[n];
    }

	/*  original formula
	long C = 1;
    for (int i = 0; i < n; ++i) {
      C = C * 2 * (2 * i + 1) / (i + 2);
    }
    return (int) C; */
}
