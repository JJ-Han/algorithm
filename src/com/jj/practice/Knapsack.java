package com.jj.practice;

// classic 0-1 knapsack problem solving with dynamic programming

import java.util.Arrays;

public class Knapsack {
    private final int n;
    private final int[] value;
    private final int[] weight;

    public Knapsack(int[] value, int[] weight) {
        if (value.length != weight.length) throw new IllegalArgumentException("different length of arrays");
        this.n = value.length;
        this.value = Arrays.copyOf(value, n);
        this.weight = Arrays.copyOf(weight, n);
    }

    public int getMaximumValue(int knapsack) {
        int[][] dp = new int[n + 1][knapsack + 1];
        for (int item = 1; item <= n; item++)
            for (int capacity = 1; capacity <= knapsack; capacity++)
                if (weight[item-1] > capacity) dp[item][capacity] = dp[item-1][capacity];
                else dp[item][capacity] = Math.max(dp[item-1][capacity], dp[item-1][capacity - weight[item-1]] + value[item-1]);
        return dp[n][knapsack];
    }

    public static void main(String[] args) {
        int[] v = {10, 15, 40};
        int[] w = {1, 2, 3};

        int[] v2 = {60, 100, 120};
        int[] w2 = {10, 20, 30};
        Knapsack test = new Knapsack(v, w);
        Knapsack test2 = new Knapsack(v2, w2);
        int ans = test.getMaximumValue(6);
        System.out.println(ans);

        int ans2 = test2.getMaximumValue(50);
        System.out.println(ans2);
    }
}
