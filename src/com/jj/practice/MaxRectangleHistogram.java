package com.jj.practice;

// https://practice.geeksforgeeks.org/problems/maximum-rectangular-area-in-a-histogram/0
// https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
import java.util.Arrays;

public class MaxRectangleHistogram {
    public static void main(String[] args) {
        int[] hist = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(maxRectangle(hist));
    }

    public static int maxRectangle(int[] hist) {
        SegTree st = new SegTree(hist);
        return maxRectangle(st, 0, hist.length-1);
    }
    private static int maxRectangle(SegTree st, int from, int to) {
        if(from > to)   return 0;
        if(from == to)  return st.get(from);
        int mid = st.rmIDXq(from, to);
        return Math.max( (to-from+1) * st.get(mid),
                Math.max( maxRectangle(st, from, mid-1), maxRectangle(st, mid+1, to) ) );
    }

    static class SegTree{
        private int[] st;
        private int size;
        private int[] array;

        public SegTree(int[] a) {
            this.array = Arrays.copyOf(a, a.length);
            this.size = (int) (2 * Math.pow(2, Math.ceil(Math.log(a.length) / Math.log(2))));
            st = new int[size];
            build(1, 0, array.length);
        }
        private void build(int v, int from, int size) {
            if(size == 1)   st[v] = from;
            else {
                build(2*v, from, size/2);
                build(2*v + 1, from + size/2, size - size/2);
                st[v] = array[st[2*v]] < array[st[2*v + 1]] ? st[2*v] : st[2*v + 1];
            }
        }

        public int get(int idx) { return array[idx]; }
        public int getMinIDX(int i, int j) {
            if(i == -1) return j;
            if(j == -1) return i;
            return array[i] < array[j] ? i : j;
        }

        // range minimum Index query.
        public int rmIDXq(int from, int to) {
            if(from < 0 || to >= array.length || from > to)  throw new IllegalArgumentException("invalid argument");
            return rmIDXq(1, 0, array.length-1, from, to);
        }
        private int rmIDXq(int v, int lo, int hi, int from, int to) {
            if(from <= lo && hi <= to)  return st[v];
            if(from > hi || to < lo)    return -1;
            int mid = lo + (hi - lo - 1)/2;
            int leftMin = rmIDXq(2*v, lo, mid, from, to);
            int rightMin = rmIDXq(2*v + 1, mid+1, hi, from, to);
            return getMinIDX(leftMin, rightMin);
        }
    }
}
