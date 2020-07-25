package com.jj.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KLargest {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(kLargest(br.readLine(), n, k) );
        }
    }

    public static String kLargest(String str, int n, int k) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(str);
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);

        for (int i = 0; i < n; i++) {
            if (i < k) {
                pq.add(Integer.parseInt(st.nextToken()));
                if (i == k-1)   sb.append(pq.peek()).append(" ");
                else            sb.append("-1 ");
            }else {
                int elem = Integer.parseInt(st.nextToken());
                if (pq.peek() < elem) {
                    pq.remove();
                    pq.add(elem);
                }
                sb.append(pq.peek()).append(" ");
            }
        }
        return sb.toString();
    }
}
