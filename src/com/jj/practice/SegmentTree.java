package com.jj.practice;

import java.util.Arrays;

// Segment Tree for Sum
// No Modification Method...
// TODO: to implement segment tree for min value

public class SegmentTree {
    private int size;
    private int[] array;
    private int[] st;

    SegmentTree(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        // max size = 2 * 2 ^ ceil[log2(n)] for 1-based index array
        // safest min value array.size x 4
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

    public int rsq(int from, int to) {
        if(from < 0 || to >= array.length || from > to)  throw new IllegalArgumentException("invalid argument");
        return rsq(1, 0, array.length-1, from, to);
    }
    private int rsq(int v, int lo, int hi, int from, int to) {
        if(from <= lo && hi <= to)  return st[v];
        if(from > hi || lo > to)    return 0;
        // reason of hi-lo-1 => when node size is odd, left node(2*v) is always smaller size than right node(2*v +1) since size/2 from build method
        int mid = lo + (hi-lo-1)/2;
        int leftSum = rsq(2*v, lo, mid, from, to);
        int rightSum = rsq(2*v + 1, mid+1, hi, from, to);
        return leftSum + rightSum;
    }

    // eager update implement which updates result immediately
    // TODO: laze update implement with node segment tree structure
    public void update(int idx, int val){
        int v = 1, lo = 0, hi = array.length-1, diff = val - array[idx];
        while (lo < hi) {
            st[v] += diff;
            int mid = lo + (hi - lo - 1)/2;
            v *= 2;
            if (idx <= mid) hi = mid;
            else {
                lo = mid + 1;
                v++;
            }
        }
        array[idx] = st[v] = val;
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
