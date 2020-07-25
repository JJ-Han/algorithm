package com.jj.practice;

import java.util.*;

public class MergeSortedArr {
    public static ArrayList<Integer> mergeKArrays(int[][] arrays)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<ArrayRow> pq = new PriorityQueue<>(arrays.length);
        for (int i = 0; i < arrays.length; i++)
            pq.add(new ArrayRow(arrays[i], 0));

        while(!pq.isEmpty()) {
            ArrayRow root = pq.poll();
            ans.add(root.getValue());
            if(root.nextIDX() < root.size()) pq.add(root);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr= {  { 2, 6, 12 },
                        { 1, 9 },
                        { 23, 34, 90, 2000 } };

        for (int i : mergeKArrays(arr))
            System.out.print(i + " ");
    }
}

class ArrayRow implements Comparable<ArrayRow> {
    private int idx;
    private int[] arr;

    ArrayRow(int[] arr, int idx) {
        this.arr = arr;
        this.idx = idx;
    }

    public int size() {
        return arr.length;
    }

    public int nextIDX() {
        return ++idx;
    }

    public int getValue() {
        return arr[idx];
    }

    public int compareTo(ArrayRow arr) {
        return getValue() - arr.getValue();
    }
}

