package com.jj.practice;

import java.util.HashMap;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/

public class LongestSubstringNoRepeat {
    public static void main(String[] args) {
        String[] tc = {"abcabcbb", "dvdf", "bbbbb", "pwwkew", "cdd", "abba", "wobgrovw", "ohomm"};
                // ans      3        3       1          3       2       2       6           3
//        for(String s : tc)
//            System.out.println(lengthOfLongestSubstring(s));
//        System.out.println(fast(tc[6]));
//        int i = tc[0].charAt(0);
        String st = "qwertyuiopasdfghjkl";
        System.out.println(fast(tc[6]));
    }

    // Using HashMap Class
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();
        HashMap<Character, Integer> hm = new HashMap<>(128);
        int ans = 0;
        for(int i = 0, pos = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(hm.containsKey(ch))  pos = Math.max(pos, hm.get(ch));
            hm.put(ch, i+1);
            ans = Math.max(ans, i-pos + 1);
        }
        return ans;
    }

    // This method updates max only when it is required
    // Also, array could be faster for hashing instead of HashMap
    // space required: 26 for letters for [a-z] or [A-Z], 128 for ASCII, 256 for Extended ASCII, ASCII: American Standard Code for Information Interchange
    public static int fast(String s) {
        if(s.length() < 2)  return s.length();
        char[] chars = s.toCharArray();
        int[] hs = new int[128];
        int pos = 0, ans = 0;
        for(int i = 0; i < chars.length; i++) {
            if(hs[chars[i]] >= pos) {
                ans = Math.max(ans, i - pos + 1);
                pos = hs[chars[i]] + 1;
                System.out.println(i + " " + pos);
            }
            hs[chars[i]] = i + 1;
        }
        return Math.max(ans, chars.length - pos + 1);
    }
}
