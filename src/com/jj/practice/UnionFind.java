package com.jj.practice;

public class UnionFind {
    private final int n;
    private int[] parent;
    private byte[] rank;
    private int count;      // number of components

    public UnionFind(int n) {
        this.n = n;
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    // returns the canonical element of the set containing element
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];      // path compressing
            p = parent[p];
        }
        return p;
    }

    // return number of components
    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    private void validate(int p) {
        if (p < 0 || p >= n) throw new IllegalArgumentException("illegal index");
    }
}
