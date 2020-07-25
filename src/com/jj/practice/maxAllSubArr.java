package com.jj.practice;

import java.util.ArrayDeque;

// https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
// https://practice.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k/0

/************************************************************************************************************
 * Given an array A and an integer K. Find the maximum for each and every contiguous subarray of size K.    *
 ************************************************************************************************************/

public class maxAllSubArr {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 1, 4, 5, 2, 3, 6};    // 3 3 4 5 5 5 6
        maxSubArr(num, 3);
    }



    public static void maxSubArr(int[] a, int k) {
        ArrayDeque<Integer> adq = new ArrayDeque<>(k);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            while(!adq.isEmpty() && a[i] >= a[adq.peekLast()])
                adq.removeLast();

            adq.addLast(i);
        }

        for (int i = k; i < a.length; i++) {
            sb.append(a[adq.peek()]).append(" ");

            if(!adq.isEmpty() && adq.peek() <= i-k)
                adq.removeFirst();

            while(!adq.isEmpty() && a[i] >= a[adq.peekLast()])
                adq.removeLast();

            adq.addLast(i);
        }
        sb.append(a[adq.peek()]);
        System.out.println(sb);
    }
}
