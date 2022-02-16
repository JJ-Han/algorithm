package com.jj.practice;
/*
Amazon OA Prep
A large cloud provider provides fast efficient server solutions.
The developers want to stress-test the quality of the server's channels. They must ensure the following:

Each of the packets must be sent via a single channel.
Each of the channels must transfer at least one packet.
The quality of the transfer for a channel is defined by the median of the sizes of all the data packets sent through that channel.

Note: The median of an array is the middle element if the array is sorted in non-decreasing order.
If the number of elements in the array is even, the median is the average of the two middle elements.

Find the maximum possible sum of the qualities of all channels. If the answer is a floating-point value, round it to the next higher integer.

Function Description
Complete the function maximum_quality.

maximum_quality has the following parameters:
int packets[n]L the packet sizes
int channels: the number of channels

Returns
long int: the maximum sum possible

Constraints
1 <= len(packets) <= 5 * 10^5
1 <= packets[i] <= 10^9
1 <= channels <= len(packets)
Sample Test Case 0
packets[] size n = 5
packets = [2, 2, 1, 5, 3]
channels = 2

Sample Output
7

Explanation
One solution is to send packet {5} through one channel and {2, 2, 1, 3} through the other. The sum of qquality is 5 + (2+2)/2 = 7

Sample Test Case 1
packets[] size n = 5
packets = [1, 2, 3, 4. 5]
channels = 2

Sample Output
8

Explanation
One maximal solution is to transfer packets [1,2,3,4] through channel 1 and packet [5] through channel 2.
The quality of transfer for channel 1 is (2+3)/2=2.5 and that of channel 2 is 5. Their sum is 2.5+5 =7.5 which is round up to 8.


Sample Test Case 2
packets[] size n = 3
packets = [89, 48, 14]
channels = 2

Sample Output
151

Explanation
There are 3 channels for each 3 packets. Each channel carries one, so the overall sum of quantity is 89+48+14=151

Sample Test Case 3
packets[] size n = 1
packets = [1]
channels = 1

Sample Output
1

Explanation
There is only 1 channel and only 1 packet so output is 1

 */
import java.util.*;
import java.util.Queue;

public class ChannelQuality {
    // benchmark
    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        List<Integer> test2 = new ArrayList<>();
        for (int i = 0; i < 500000; i++) {
            int x = (int) (Math.random() * 1000000000);
            test.add(x);
            test2.add(x);
        }
        long startTime = System.currentTimeMillis();
        System.out.println(maxQualitySorting(test, 50000));
        System.out.println(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        System.out.println(maxQualityQuickSelect(test2, 50000));
        System.out.println(System.currentTimeMillis() - startTime);
    }

    // implement with sorting, probably better solution for OA due to limited time
    // Time Complexity O(nlogn)
    public static long maxQualitySorting(List<Integer> packets, int channels) {
        int n = packets.size();
        if (n == channels) return sumOf(packets, 0, n);

        Collections.sort(packets);
        long sum = sumOf(packets, n-channels+1, n);
        int size = n - channels + 1;
        int k = size/2;
        if ((size & 1) == 1) return sum + packets.get(k);
        else return sum + (long) Math.ceil((double) (packets.get(k-1) + packets.get(k)) / 2);
    }

    // implement with priority queue and quickselect
    // Avg Time Complexity O(klogk) where k = channels size-1
    // caution: worst time complexity is O(n^2) due to quickselect on sorted array
    // When channel size is much smaller than packets size, it would be more benefit.
    // If channel size is usually bigger than the half of packet size, maxheap to filter low packets would be appropriate
    public static long maxQualityQuickSelect(List<Integer> packets, int channels) {
        int n = packets.size();
        if (n == channels) return sumOf(packets, 0, n);
        int k = channels-1;
        java.util.Queue<Integer> pq = new PriorityQueue<>(k, Comparator.comparingInt(packets::get));    // save index instead of packet value
        pq.offer(0);
        for (int i = 1; i < n; i++) {
            if (pq.size() < k) pq.offer(i);
            else if (packets.get(i) > packets.get(pq.peek())) {
                pq.poll();
                pq.offer(i);
            }
        }
        int shiftRight = pq.size();
        long sum = compute(packets, pq);
        return sum + getMedian(packets, shiftRight);
    }

    // get median using quickselect
    private static int getMedian(List<Integer> a, int shiftRight) {        // k = 0-indexed
        int size = a.size() - shiftRight;
        int lo = 0, hi = a.size()-1;

        boolean isOdd = (size & 1) == 1;
        int k = size / 2 + shiftRight;                  // shifted k value for median
        int median1 = isOdd ? 0 : -1, median2 = -1;
        while (lo < hi) {
            int i = partition(a, lo, hi);
            if (i == k-1) {
                median1 = a.get(i);
                if (median2 != -1) break;
            }
            else if (i == k) {
                median2 = a.get(i);
                if (median1 != -1) break;
            }
            if (i < k) lo = i + 1;
            else hi = i-1;
        }
        if (median2 < 0) median2 = a.get(lo);   // tricky case of when while loop can be terminated before finding i == k
        return isOdd ? median2 : (int) Math.ceil(((double) median1 + median2) /2);
    }

    private static int partition(List<Integer> a, int lo, int hi) {
        int i = lo, j = hi+1;
        Integer v = a.get(lo);
        while (true) {
            while (v > a.get(++i))
                if (i == hi) break;
            while (a.get(--j) > v)
                if (j == lo) break;

            if (i >= j) break;
            Collections.swap(a, i, j);
        }
        Collections.swap(a, lo, j);
        return j;
    }

    // get Sum from pq and change value to zero in the original list
    private static long compute(List<Integer> packets, Queue<Integer> pq) {
        long sum = 0;
        Integer[] a = pq.toArray(Integer[]::new);
        for (int x : a) {
            sum += packets.get(x);
            packets.set(x, 0);
        }
        return sum;
    }

    private static long sumOf(List<Integer> a, int lo, int hi) {
        long sum = 0;
        for (int i = lo; i < hi; i++)
            sum += a.get(i);
        return sum;
    }
}
