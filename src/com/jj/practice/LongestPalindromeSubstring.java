package com.jj.practice;

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        String s = "abccbaaa";
        String s2 = "abbaabba";
        String s3 = "abc";
//        System.out.println(longestPalindrome(s));
        System.out.println(manacher(s3));
    }

    // check every 2n-1 centers to find palindrome
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int lo = 0, hi = 0;
        for(int i = 0; i < chars.length && hi-lo < (chars.length-1-i) << 1 ; i++) {
            // to find both odd and even palindromes
            int len = Math.max(isPalindrome(chars, i, i), isPalindrome(chars, i, i+1));
            if(len > hi-lo) {       // to store index of the found longer palindrome
                lo = i - (len-1)/2;
                hi = i + len/2;
            }
        }
        return s.substring(lo, hi+1);       // substring(inc, exc)
    }

    private static int isPalindrome(char[] s, int lo, int hi) {
        while(lo >= 0 && hi < s.length && s[lo] == s[hi]) {
            lo--;
            hi++;
        }
        return hi-lo-1;     // palindrome between lo+1 and hi-1
    }

    // Manacher Algorithm with Time Complexity O(n)
    // https://algs4.cs.princeton.edu/53substring/Manacher.java.html
    // https://web.archive.org/web/20181208031518/https://articles.leetcode.com/longest-palindromic-substring-part-ii/
    public static String manacher(String s) {
        if(s == null || s.length() < 1) return "";
        char[] t = transform(s);
        int[] p = new int[t.length];

        int center = 0, right = 0;
        int max = 0;
        for(int i = 1; i < t.length-1; i++) {
            int mirror = 2*center - i;

            if(right > i)
                p[i] = Math.min(right - i, p[mirror]);

            while(t[i + (1 + p[i])] == t[i - (1 + p[i])])
                p[i]++;

            if(i + p[i] > right) {
                center = i;
                right = i + p[i];
                if(p[i] > p[max]) max = i;
            }
        }
        return s.substring((max - 1 - p[max]) / 2, (max - 1 + p[max]) / 2);
    }

    // https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
    // geeksforgeeks implement for Manacher's algorithm
    // LPS size should be 2n + 1 to check both odd and even palindrome
    // when LPS[i] = d, LPS[i-d] ~ LPS[i+d] in LPS array
    // in terms of index in String, (i-d)/2 ~ (i+d)/2 - 1

    // * this implement takes care of all four cases, whereas ALGS simplifies 'continue' case
    // * ALGS is more efficient since some cases seems redundant
    public static String manacher2(String s) {
        if(s == null || s.length() < 1) return "";
        char[] t = transform(s);
        int[] p = new int[t.length];
        int center = 0, right = 0, max = 0;
        for(int i = 1; i < t.length-1; i++) {
            int mirror = center*2 - i;
            if(i < right) {
                int diff = right - i;
                if(p[mirror] < diff) {
                    p[i] = p[mirror];
                    continue;
                }else if (p[mirror] == diff) {
                    if(right == t.length-2) {
                        p[i] = p[mirror];
                        continue;
                    }
                    else {
                        System.out.println(i);
                        p[i] = p[mirror];
                    }
                }else {
                    System.out.println(i);
                    p[i] = diff;
                }
            }
            if(i == right)  System.out.println("SAME " + i);

            while(t[i + (1 + p[i])] == t[i - (1 + p[i])])
                p[i]++;

            if(i + p[i] > right) {
                center = i;
                right = i + p[i];
                if(p[i] > p[max]) max = i;
            }
//            System.out.println("center: " + center + " right: " + right + " @ " + i + " " + p[i]);
        }
        return s.substring((max - 1 - p[max]) / 2, (max - 1 + p[max]) / 2);
    }

    // modified exit condition for 'for loop'
    // When the possible longest palindrome substring is found, for loop will break
    // simplified the code a bit more
    public static String manacherTweak(String s) {
        if(s == null || s.length() < 1) return "";
        char[] t = transform(s);
        int[] p = new int[t.length];
        int center = 0, right = 0, pos = 0, edge = t.length-2;
        for(int i = 1; i < t.length-1; i++) {
            if(i < right) {
                int mirror = p[center*2 - i];
                int diff = right - i;
                if(mirror > diff)    p[i] = diff;
                else if (mirror < diff) {
                    p[i] = mirror;
                    continue;
                }
                else    p[i] = mirror;
            }

            while(t[i + (1 + p[i])] == t[i - (1 + p[i])])
                p[i]++;

            if(i + p[i] > right) {
                center = i;
                right = i + p[i];
                if(p[i] > p[pos]) pos = i;
                // when right and edge are the same, possible longest palindrome substring is already found
                if(right == edge)   break;
            }
        }
        return s.substring((pos - 1 - p[pos]) / 2, (pos - 1 + p[pos]) / 2);
    }

    // transform to char array from original string
    // insert '#' between character and '$', '@' are prepended and appended to each end to avoid bounds checking
    private static char[] transform(String s) {
        char[] t = new char[s.length()*2 + 3];
        t[0] = '$';
        t[s.length()*2 + 1] = '#';
        t[s.length()*2 + 2] = '@';
        for(int i = 0; i < s.length(); i++) {
            t[i*2 + 1] = '#';
            t[i*2 + 2] = s.charAt(i);
        }
        return t;
    }
}
