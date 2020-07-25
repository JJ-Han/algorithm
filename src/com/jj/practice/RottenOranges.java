package com.jj.practice;

// https://practice.geeksforgeeks.org/problems/rotten-oranges/0
// https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
// https://leetcode.com/problems/rotting-oranges/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class RottenOranges {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[][] field = new int[m][n];
            for(int i = 0; i < m; i++)
                for(int j = 0; j < n; j++)
                    field[i][j] = Integer.parseInt(st.nextToken());
            System.out.println(getRottenTime(field));
        }
    }

    private static class Cord {
        private int row, col;
        public Cord(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int getRottenTime(int[][] a) {
        int r = a.length, c = a[0].length, time = 0, fresh = 0;
        int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        ArrayDeque<Cord> queue = new ArrayDeque<>();
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(a[i][j] == 1)    fresh++;
                else if(a[i][j] == 2)   queue.offer(new Cord(i, j));
        queue.offer(new Cord(-1, -1));
        while(!queue.isEmpty()) {
            Cord curr = queue.poll();
            int row = curr.row, col = curr.col;
            if(row == -1) {
                if(!queue.isEmpty()) {
                    time++;
                    queue.offer(new Cord(-1, -1));
                }
            }else {
                for(int[] d : dir) {
                    int adjRow = row + d[0], adjCol = col + d[1];
                    if(adjRow < r && adjRow >= 0 && adjCol < c && adjCol >= 0 && a[adjRow][adjCol] == 1) {
                        a[adjRow][adjCol] = 2;
                        fresh--;
                        queue.offer(new Cord(adjRow, adjCol));
                    }
                }

            }
        }
        return fresh == 0 ? time : -1;
    }
}
