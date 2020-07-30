package com.jj.practice;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
// https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
// https://practice.geeksforgeeks.org/problems/maximum-rectangular-area-in-a-histogram/0
// Algorithm to find maximum rectangle area in Histogram
// Time Complexity O(n), Space Complexity O(n)

import java.util.ArrayDeque;

public class MaxRectangleHistLinear {
    public static void main(String[] args) {
        int[] hist =  {6, 2, 5, 4, 5, 1, 6};
        System.out.println(getMaxRectangleLinear(hist));
    }

    public static int getMaxRectangleLinear(int[] hist) {
        if(hist == null)    return 0;
        int max = 0;
        ArrayDeque<Integer> st = new ArrayDeque<>(hist.length);
        for(int i = 0; i < hist.length; i++) {
            while(!st.isEmpty() && hist[i] <= hist[st.peek()]) {
                int h = hist[st.pop()];
                int w = st.isEmpty() ? i : i-st.peek()-1;
                max = Math.max(max, h*w);
            }
            st.push(i);
        }
        while(!st.isEmpty()) {
            int h = hist[st.pop()];
            int w = st.isEmpty() ? hist.length : hist.length-st.peek()-1;
            max = Math.max(max, h*w);
        }
        return max;
    }
}
