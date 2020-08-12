package com.jj.practice;

import java.util.ArrayList;

// https://leetcode.com/problems/generate-parentheses/
// Possible numbers of Parentheses is n Catalan number
// which is bound asymptotically by n power of 4 / n sqrt(n)
// Time Complexity O( pow(4,n) / sqrt(n) )

public class GenerateParentheses {
    public static void main(String[] args) {
        ArrayList<String> test = generate(3);
        System.out.println(test);
    }

    // n pairs of parentheses
    public static ArrayList<String> generate(int n) {
        if(n == 0)  return new ArrayList<>();
        ArrayList<String> al = new ArrayList<>();
        resultToList(0, 0, n, new char[n*2], al);
        return al;
    }
    private static void resultToList(int open, int close, int n, char[] result, ArrayList<String> ans) {
        int curr = open+close;
        if(curr == result.length) {
            ans.add(new String(result));
            return;
        }
        if(open < n) {
            result[curr] = '(';
            resultToList(open+1, close, n, result, ans);
        }
        if(close < open) {
            result[curr] = ')';
            resultToList(open, close+1, n, result, ans);
        }
    }
}
