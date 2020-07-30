package com.jj.practice;

// https://leetcode.com/problems/surrounded-regions/
// https://practice.geeksforgeeks.org/problems/replace-os-with-xs/0
// https://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/

import java.util.ArrayDeque;

public class ReplaceOX {
    private static final int[][] DIR = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},                       // {'X', 'X', 'X', 'X'}
                {'X', 'O', 'X', 'X'},                       // {'X', 'X', 'X', 'X'}
                {'X', 'O', 'O', 'X'},                       // {'X', 'X', 'X', 'X'}
                {'X', 'O', 'X', 'X'},                       // {'X', 'X', 'X', 'X'}
                {'X', 'X', 'O', 'O'}                        // {'X', 'X', 'O', 'O'}
        };

        char[][] board2 = {
                {'X', 'O', 'X', 'X', 'X', 'X'},             // {'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'O', 'X'},             // {'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'O', 'X', 'X'},             // {'X', 'X', 'X', 'X', 'X', 'X'},
                {'O', 'X', 'O', 'X', 'O', 'X'},             // {'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X', 'O'},             // {'X', 'X', 'X', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O'},             // {'O', 'O', 'X', 'O', 'O', 'O'},
        };

        dfs(board);
        printBoard(board);

        bfs(board2);
        printBoard(board2);
    }

    public static void dfs(char[][] board) {
        if(board == null || board.length <= 2 || board[0].length <= 2)  return;
        int r = board.length, c = board[0].length;
        boolean[][] marked = new boolean[r][c];

        // check boarder and mark it
        for(int j = 0; j < c; j++) {
            if(board[0][j] == 'O' && !marked[0][j])     dfs(board, marked, 0, j);
            if(board[r-1][j] == 'O' && !marked[r-1][j]) dfs(board, marked, r-1, j);
        }
        for(int i = 1; i < r-1; i++) {
            if(board[i][0] == 'O' && !marked[i][0])     dfs(board, marked, i, 0);
            if(board[i][c-1] == 'O' && !marked[i][c-1]) dfs(board, marked, i, c-1);
        }

        // fill 'O' to 'X' only if not marked
        fill(board, marked);
    }
    private static void dfs(char[][] board, boolean[][] marked, int i, int j) {
        marked[i][j] = true;
        for(int[] d : DIR) {
            int adjRow = i + d[0], adjCol = j + d[1];
            if(adjRow < 0 || adjRow >= board.length || adjCol < 0 || adjCol >= board[0].length) continue;
            if(board[adjRow][adjCol] == 'O' && !marked[adjRow][adjCol]) dfs(board, marked, adjRow, adjCol);
        }
    }

    public static void bfs(char[][] board) {
        if(board == null || board.length <= 2 || board[0].length <= 2)  return;
        int r = board.length, c = board[0].length;
        boolean[][] marked = new boolean[r][c];
        ArrayDeque<Pair> queue = new ArrayDeque<>(r*c);
        for(int j = 0; j < c; j++) {
            if(board[0][j] == 'O') {
                marked[0][j] = true;
                queue.offer(new Pair(0, j));
            }
            if(board[r-1][j] == 'O') {
                marked[r-1][j] = true;
                queue.offer(new Pair(r-1, j));
            }
        }
        for(int i = 1; i < r-1; i++) {
            if(board[i][0] == 'O') {
                marked[i][0] = true;
                queue.offer(new Pair(i, 0));
            }
            if(board[i][c-1] == 'O') {
                marked[i][c-1] = true;
                queue.offer(new Pair(i, c-1));
            }
        }
        while(!queue.isEmpty()) {
            Pair curr = queue.poll();
            for(int[] d : DIR) {
                int adjRow = curr.x + d[0], adjCol = curr.y + d[1];
                if(adjRow < 0 || adjRow >= board.length || adjCol < 0 || adjCol >= board[0].length) continue;
                if(!marked[adjRow][adjCol] && board[adjRow][adjCol] == 'O') {
                    marked[adjRow][adjCol] = true;
                    queue.offer(new Pair(adjRow, adjCol));
                }
            }
        }

        fill(board, marked);
    }

    // find unmarked 'O' and modify to 'X'
    private static void fill(char[][] board, boolean[][] marked) {
        for(int i = 1; i < board.length-1; i++)
            for(int j = 1; j < board[0].length-1; j++)
                if(!marked[i][j] && board[i][j] == 'O') board[i][j] = 'X';
    }
    public static void printBoard(char[][] board) {
        for(char[] row : board) {
            for(char ch : row)
                System.out.print(ch + " ");
            System.out.println();
        }
        System.out.println();
    }

    private static class Pair {
        private int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
