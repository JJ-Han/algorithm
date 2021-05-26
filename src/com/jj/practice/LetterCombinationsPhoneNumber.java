package com.jj.practice;


import java.util.ArrayList;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

public class LetterCombinationsPhoneNumber {
    private static ArrayList<String> ans;
    private static final char[][] MAP = {
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'}
    };

    public static void main(String[] args) {
        ArrayList<String> test = letterCombinations("23");
        System.out.println(test);
    }

    public static ArrayList<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        if(digits == null || digits.length() == 0)  return ans;

        digitsToList(digits.toCharArray(), new char[digits.length()], 0);
        return ans;
    }

    // digits: input, letters: decoded letters, i: index
    private static void digitsToList(char[] digits, char[] letters, int i) {
        if(i == digits.length) {
            ans.add(new String(letters));
            return;
        }
        for(char c : MAP[digits[i] - 50]) {
            letters[i] = c;
            digitsToList(digits, letters, i+1);
        }
    }
}
