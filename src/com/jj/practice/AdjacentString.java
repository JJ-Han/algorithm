package com.jj.practice;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AdjacentString {
    public static void main(String[] args) {
        String str = "bbbabaaacd";
//        System.out.println(noAdjStr(str));

        PriorityQueue<Integer> pq = new PriorityQueue<>(3, Collections.reverseOrder());
        pq.add(1);
        pq.add(2);
        pq.add(3);
        pq.add(4);
        for (int i : pq)
            System.out.println(i + " ");
    }

    static String noAdjStr(String str) {
        HashMap<Character, Integer> hm = new HashMap<>();
        PriorityQueue<CharKey> pq = new PriorityQueue<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!hm.containsKey(ch))    hm.put(ch, 1);
            else                        hm.put(ch, hm.get(ch) +1);
        }
        for (char key : hm.keySet())
            pq.add(new CharKey(key, hm.get(key)));

        StringBuilder ans = new StringBuilder();
        CharKey used = null;
        while (!pq.isEmpty()) {
            CharKey max = pq.poll();
            ans.append(max);
            max.used();
            if (used != null && max != used) {
                used.ready();
                pq.remove(used);
                pq.add(used);
            }
            if (max.getCount() != 0) {
                pq.add(max);
                used = max;
            }
            else used = null;
        }
        return ans.toString();
    }
}

class CharKey implements Comparable<CharKey> {
    private int count;
    private char ch;
    private boolean adjacent = false;

    CharKey(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
    public void ready()                { adjacent = false; }
    public int getCount()               { return count; }
    public void used() {
        count--;
        adjacent = true;
    }
    public String toString()            { return String.valueOf(ch); }
    public int compareTo(CharKey ck) {
        if (adjacent && !ck.adjacent)   return 1;
        else if (!adjacent && ck.adjacent)  return -1;
        return -(getCount() - ck.getCount());
    }
}

