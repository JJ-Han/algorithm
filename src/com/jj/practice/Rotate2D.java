package com.jj.practice;

// https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
// https://practice.geeksforgeeks.org/problems/rotate-by-90-degree0356/1

public class Rotate2D {
    public static <T> void rotate (T[][] mat) {
         //without extra space
         int n = mat.length;
         for (int i = 0; i < n/2; i++) {
             for (int j = i; j < n-i-1; j++) {
                 T temp = mat[i][j];
                 mat[i][j] = mat[j][n-i-1];
                 mat[j][n-i-1] = mat[n-i-1][n-j-1];
                 mat[n-i-1][n-j-1] = mat[n-j-1][i];
                 mat[n-j-1][i] = temp;
             }
         }
    }
    
    public static <T> void rotateExtraSpace(T[][] mat) {
        // with extra space
        int n = mat.length;
        T[][] ans = (T[][]) new Object[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = mat[j][n-i-1];
            }
        }
        // copy
        for (int i = 0; i < n; i++)
            System.arraycopy(ans[i], 0, mat[i], 0, n);
    }

    public static void main(String[] args) {
        Integer[][] mat = { {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9} };
        rotate(mat);
    }
}
