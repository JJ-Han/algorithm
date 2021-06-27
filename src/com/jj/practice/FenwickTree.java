package com.jj.practice;

public class FenwickTree {
    private int[] array;
    private int[] ft;

    public FenwickTree(int[] a) {
        this.array = a;
        int n = array.length;
        ft = new int[array.length+1];
        for (int i = 0; i < n; i++)
            updateOffset(i, array[i]);
    }

    public void update(int idx, int value) {
        if (idx < 0 || idx >= array.length) throw new IllegalArgumentException();
        int offset = value - array[idx];
        updateOffset(idx, offset);
        array[idx] = value;
    }

    private void updateOffset(int idx, int offset) {
        idx++;
        while (idx < ft.length) {
            ft[idx] += offset;
            idx += idx & -idx;
        }
    }

    public int rsq (int idx) {
        if (idx < 0 || idx >= array.length) throw new IllegalArgumentException("here" +idx);
        int sum = 0;
        idx++;
        while (idx > 0) {
            sum += ft[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    public int rsq (int from, int to) {
        if (from > to) throw new IllegalArgumentException();
        int sumTo = rsq(to);
        int sumFrom = from > 0 ? rsq(from-1) : 0;
        return sumTo - sumFrom;
    }

    public static void main(String[] args) {
        int[] test = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
        int n = test.length;
        int[] prefix = new int[test.length+1];
        for (int i = 1; i <= test.length; i++)
            prefix[i] = prefix[i-1] + test[i-1];

        FenwickTree ft = new FenwickTree(test);
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                if (prefix[j+1] - prefix[i] != ft.rsq(i, j)) System.out.println("FAIL");

        ft.update(2, -3);
        ft.update(6, 1);
        for (int i = 1; i <= test.length; i++)
            prefix[i] = prefix[i-1] + test[i-1];

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                if (prefix[j+1] - prefix[i] != ft.rsq(i, j)) System.out.println("FAIL");

    }
}
