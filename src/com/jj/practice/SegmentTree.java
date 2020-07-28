package com.jj.practice;

import java.util.Arrays;

// Segment Tree for Sum
// No Modification Method...
// TODO: to implement update method
// TODO: to implement segment tree for min value

public class SegmentTree {
    private int size;
    private int[] array;
    private int[] st;

    SegmentTree(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        //max size = 2 * 2 ^ ceil[log2(n)] for 1-based index array
        size = (int) (2 * Math.pow(2, (Math.ceil(Math.log(array.length) / Math.log(2)))));
        st = new int[size];
        build(1, 0, array.length);
    }

    public int size() {
        return array.length;
    }
    public int sizeST() { return size; }

    private void build(int v, int from, int size) {
        if(size == 1)   st[v] = array[from];
        else {
            build(2*v, from, size/2);
            build(2*v + 1, from + size/2, size - size/2);
            st[v] = st[2*v] + st[2*v + 1];
        }
    }

    /**
     * Range Sum Query
     * @param  from from index
     * @param  to to index
     */
    public int rsq(int from, int to) {
        if(from < 0 || to >= array.length || from > to)  throw new IllegalArgumentException("invalid argument");
        return rsq(1, 0, array.length-1, from, to);
    }
    private int rsq(int v, int lo, int hi, int from, int to) {
        if(from <= lo && hi <= to)  return st[v];
        if(from > hi || lo > to)    return 0;
        int mid = lo + (hi-lo-1)/2;
        int leftSum = rsq(2*v, lo, mid, from, to);
        int rightSum = rsq(2*v + 1, mid+1, hi, from, to);
        return leftSum + rightSum;
    }

    public void print() {
        for(int i = 1; i < size; i++)
            System.out.print(st[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int[] arr2 = {1, 3, 5, 7};
        SegmentTree st = new SegmentTree(arr);
        System.out.println(st.sizeST());
        st.print();
        for(int i = 0; i < st.size(); i++)
            for(int j = i; j < st.size(); j++)
                System.out.println(i + " " + j + ": " + st.rsq(i, j));
    }
}
