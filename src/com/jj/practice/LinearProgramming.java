package com.jj.practice;

// linear programming based on simplex algorithm

public class LinearProgramming {
    private static final double EPSILON = 1.0E-10;
    private double[][] a;   // tableaux
    private int m;          // constraints
    private int n;          // variables

    private int[] basis;    // pivot history to track the optimal solution

    public LinearProgramming(double[][] A, double[] b, double[] c) {
        m = b.length;
        n = c.length;
        for (int i = 0; i < m; i++)
            if (!(b[i] >= 0)) throw new IllegalArgumentException("RHS must be non-negative");

        a = new double[m+1][n+m+1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = A[i][j];
        for (int i = 0; i < m; i++)
            a[i][n+i] = 1.0;

        // objective equations
        for (int j = 0; j < n; j++)
            a[m][j] = c[j];

        // RHS values
        for (int i = 0; i < m; i++)
            a[i][m+n] = b[i];

        basis = new int[m];
        for (int i = 0; i < m; i++)
            basis[i] = n + i;

        solve();
    }

    private void solve() {
        while (true) {
            int q = bland();            // col
            if (q == -1) break;         // optimal

            int p = minRatioRule(q);    // row
            if (p == -1) throw new ArithmeticException("Linear program is unbounded");

            pivot(p, q);
//            System.out.println(p + " " + q);
            basis[p] = q;               // save pivot value to track the solutions
        }
    }

    // to find positive coefficient column in objective equation
    private int bland() {
        for (int j = 0; j < m+n; j++)
            if (a[m][j] > 0) return j;
        return -1;          // optimal = objective equation has all negative coefficient
    }

    // to find min ratio row
    private int minRatioRule(int q) {
        int p = -1;
        for (int i = 0; i < m; i++) {
            if (a[i][q] <= EPSILON) continue;
            else if (p == -1) p = i;
            else if ((a[i][m+n] / a[i][q]) < (a[p][m+n] / a[p][q])) p = i;
        }
        return p;
    }

    // Gaussian elimination
    private void pivot(int p, int q) {
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= m+n; j++)
                if (i != p && j != q) a[i][j] -= a[p][j] * a[i][q] / a[p][q];

        for (int i = 0; i <= m; i++)
            if (i != p) a[i][q] = 0.0;

        for (int j = 0; j <= m+n; j++)
            if (j != q) a[p][j] /= a[p][q];
        a[p][q] = 1.0;
    }

    public double value() {
        return -a[m][m+n];
    }

    // RHS values result
    public double[] primal() {
        double[] x = new double[n];
        for (int i = 0; i < m; i++)
            if (basis[i] < n) x[basis[i]] = a[i][m+n];
        return x;
    }

    // slack variables result
    public double[] dual() {
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            y[i] = -a[m][n+i];
        return y;
    }

    private static void test(double[][] A, double[] b, double[] c) {
        LinearProgramming lp;
        try {
            lp = new LinearProgramming(A, b, c);
        }
        catch (ArithmeticException e) {
            System.out.println(e);
            return;
        }
        System.out.println("value = " + lp.value());
        double[] x = lp.primal();
        for (int i = 0; i < x.length; i++)
            System.out.println("x[" + i + "] = " + x[i]);
        double[] y = lp.dual();
        for (int j = 0; j < y.length; j++)
            System.out.println("y[" + j + "] = " + y[j]);
    }

    public static void main(String[] args) {
        double[] c = {13.0, 23.0};              // objective equation
        double[] b = {480, 160, 1190};          // right hand
        double[][] A = {                        // constraints
                {5, 15},
                {4, 4},
                {35, 20}
        };
        test(A, b, c);
    }
}

