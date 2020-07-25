package com.jj.practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListExample<Item extends Comparable<Item>> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;

        Node() {
            item = null;
            next = null;
        }

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    public LinkedListExample() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public Item peekFirst() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        return first.item;
    }

    public Item peekLast() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        return last.item;
    }

    public void addFirst(Item item) {   // stack push
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (last == null)   last = first;
        n++;
    }

    public void addLast(Item item) {    // queue enqueue
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())  first = last;
        else    oldlast.next = last;
        n++;
    }

    public Item removeFirst() {     // queue deque
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    public void removeDuplicates() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        HashSet<Item> hs = new HashSet<>();
        Node curr = first, prev = null;
        while (curr != null) {
            if (!hs.contains(curr.item)) {
                hs.add(curr.item);
                prev = curr;
            }
            else {
                prev.next = curr.next;
                n--;    // need to check
            }
            curr = curr.next;
        }
        last = prev;
    }

    // remove Duplicate in sorted
    // https://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
    public void removeDuplicatesInSorted() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        if (!isSorted())    throw new UnsupportedOperationException
                ("removeDuplicateInSorted() supports only sorted list. Please use removeDuplicates() if the list is not sorted");
        Node curr = first;
        while(curr != null) {
            Node temp = curr;
            while(temp != null && temp.item == curr.item)
                temp = temp.next;
            curr.next = temp;
            if (curr.next == null)  last = curr;
            curr = curr.next;
        }
    }

    // https://www.geeksforgeeks.org/delete-middle-of-linked-list/
    public void removeMid() {
        if (first == null || first.next == null) {
            first = null;
            last = null;
            n = 0;
            return;
        }
        Node dummy = new Node(null);
        dummy.next = first;
        Node fast = first;
        while (fast != null && fast.next != null) {
            dummy = dummy.next;
            fast = fast.next.next;
        }
        dummy.next = dummy.next.next;
        n--;
        if (size() == 1)    last = first;
    }

    // recursive implement for removeDuplicates()
    // need to check
    public Node removeDuplicatesInSorted (Node node) {
        if (node == null) return null;
        if (!isSorted())    throw new UnsupportedOperationException
                ("removeDuplicateInSorted() supports only sorted list. Please use removeDuplicates() if the list is not sorted");

        first = last;
        if (node.next != null) {
            if (node.item == node.next.item) {
                node.next = node.next.next;
                n--;
                removeDuplicatesInSorted(node);
            }
            else    removeDuplicatesInSorted(node.next);
        }
        else    { last = node; }
        return node;
    }

    public Item printMiddle() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        Node refNode = first, mainNode = first;
        while (refNode != null && refNode.next != null) {
            mainNode = mainNode.next;
            refNode = refNode.next.next;
        }
        //System.out.println("The middle element is [" + mainNode.item + "] \n");
        return mainNode.item;
    }

    // https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
    public boolean isLoop() {       // loop detect with Implementation of Floyd’s Cycle-Finding Algorithm
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        Node slow = first, fast = first;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)   return true;
        }
        return false;
    }

    // https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
    // https://stackoverflow.com/questions/5607292/interview-remove-loop-in-linked-list-java
    public void removeLoop() {
        if (first == null || first.next == null) return;
        Node dummy = new Node(null), slow = dummy, fast = dummy;
        dummy.next = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)   break;
        }
        if (slow == fast) {
            slow = first;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
            last = fast;
        }
    }

    // https://www.geeksforgeeks.org/reverse-a-linked-list/
    public void reverseList() {             // reverse the entire list
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        if (size() == 1)    return;
        Node prev = null, curr = first;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        last = first;
        first = prev;
    }

    // https://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list-by-changing-links/
    // https://www.geeksforgeeks.org/pairwise-swap-adjacent-nodes-of-a-linked-list-by-changing-pointers-set-2/
    public void pairwiseSwap() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        Node curr = first, prev = first;
        first = first.next;
        while (curr != null && curr.next != null) {
            Node next = curr.next;
            prev.next = next;
            curr.next = next.next;
            next.next = curr;
            prev = curr;
            curr = curr.next;
        }
        last = (curr != null) ? curr : prev;
    }

    boolean isPalindrome() {        // O(n) TC, O(1) SC
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");
        Node slowCur = first;
        Node slowPre = null;
        Node fast = first;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node next = slowCur.next;
            slowCur.next = slowPre;
            slowPre = slowCur;
            slowCur = next;
        }

        boolean flag = true;

        /*******************************************************************
         * fast == null -> EVEN number of Nodes
         * 1  <-  2  <-  3  <-  4       5   ->  6  ->  7  ->  8 ->  null
         *                   slowPre  slowCur                       fast
         *                    halfL    halfR
         *
         * fast != null -> ODD number of Nodes
         * 1  <-  2  <-  3       4  ->   5   ->  6  ->  7  ->  null
         *            slowPre  slowCur                 fast
         *             halfL   slowPre  halfR
         ********************************************************************/
        Node halfR = (fast == null) ? slowCur : slowCur.next;

        Node halfL = slowPre;
        slowPre = slowCur;


        while (halfL != null) {
            if (halfL.item != halfR.item) flag = false;
            Node next = halfL.next;
            halfL.next = slowPre;
            slowPre = halfL;
            halfL = next;
            halfR = halfR.next;
        }
        return flag;
    }

    // to rotate the linked list counter-clockwise by k nodes
    // https://practice.geeksforgeeks.org/problems/rotate-a-linked-list/1
    public void rotate(int k) {
        if (k == 0 || k >= size()) return;
        Node cur = first;
        while (--k > 0) {
            cur = cur.next;
            // if (cur.next == null)   return;
            // when there is no method for size(), loop should be stopped when k is larger than list size.
        }

        Node oldfirst = first;
        first = cur.next;
        cur.next = null;

        last.next = oldfirst;
        last = cur;
    }

    public void arrEvenOdd1() {
        if(first == null || first.next == null) return;

        Node evenHead = new Node(null), even = evenHead, curr = first;
        while (curr.next != null) {
            even.next = curr.next;
            curr.next = curr.next.next;
            even = even.next;
            curr = (curr.next != null) ? curr.next : curr;
        }
        even.next = null;
        last = even;
        curr.next = evenHead.next;
    }

    // Bottom-up Mergesort for linked list
    // https://leetcode.com/problems/sort-list/discuss/46712/Bottom-to-up(not-recurring)-with-o(1)-space-complextity-and-o(nlgn)-time-complextity

    // my implement is on comment but only 4% faster than original
    public void sort() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");

        Node ans = new Node(first.item);
        Node prev = ans;
        ans.next = first;
        //int n = size();

        for (int len = 1; len < size(); len <<= 1) {
            prev = ans;
            Node curr = ans.next;
            //int count = 0, limit = n-len;
            while (curr != null) {      // count < limit
                Node left = curr;
                Node right = split(left, len);
                curr = split(right, len);
                prev = merge(left, right, prev);
                //count += (len<<1);
            }
            //if (curr != null) prev.next = curr;
        }
        last = prev;
        first = ans.next;
    }
    public void sort2() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list");

        Node ans = new Node(first.item);
        Node prev = ans;
        ans.next = first;

        for (int len = 1; len < size(); len <<= 1) {
            prev = ans;
            Node curr = ans.next;
            while (curr != null) {
                Node left = curr;
                Node right = split(left, len);
                curr = split(right, len);
                prev = merge(left, right, prev);
            }
        }
        last = prev;
        first = ans.next;
    }
    private Node split(Node head, int len) {
        if (head == null)    return null;

        for (int i = 1; head.next != null && i < len; i++)      // while (head.next != null && --len > 0)
            head = head.next;

        Node nextSub = head.next;
        head.next = null;

        return nextSub;
    }
    private Node merge(Node left, Node right, Node sort) {
        if (right == null) {
            sort.next = left;
            return null;
        }
        Node curr = sort;
        while (left != null && right != null) {
            if (left.item.compareTo(right.item) > 0) {
                curr.next = right;
                right = right.next;
            }
            else {
                curr.next = left;
                left = left.next;
            }
            curr = curr.next;
        }
        if (left != null)   curr.next = left;
        else    curr.next = right;

        while(curr.next != null)    curr = curr.next;
        return curr;
    }

    public boolean isSorted() {
        Node prev = first;
        Node curr = first.next;

        while(curr != null) {
            if (prev.item.compareTo(curr.item) > 0) return false;
            prev = prev.next;
            curr = curr.next;
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()    { return current != null; }
        public void remove()        { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int r = (int) ((1 + i) * Math.random());
            exch(a, i, r);
        }
    }

    public static void main(String[] args) {

        /*
        int[] input = new int[50000000]; // 50000000
        LinkedListExample<Integer> list = new LinkedListExample<>();
        LinkedListExample<Integer> list2 = new LinkedListExample<>();
        for (int i = 0; i < input.length; i++)
            input[i] = i+1;

        shuffle(input);
        for (int i : input) {
            list.addLast(i);
            //list2.addLast(i);
        }


*/
        LinkedListExample<Integer> list = new LinkedListExample<>();
        //LinkedListExample<Integer> list2 = new LinkedListExample<>();
        for (int i = 0; i < 100000000; i++) {
            list.addLast(i);
            //list2.addLast(i);
        }

        //System.out.println(list.isSorted());
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("My implement - Elapsed time in milliseconds: " + timeDiff );
        //System.out.println(list.isSorted());

        //System.out.println(list2.isSorted());
//        long startTime2 = System.currentTimeMillis();
//        list2.arrEvenOdd2();
//        long endTime2 = System.currentTimeMillis();
//        long timeDiff2 = endTime2 - startTime2;
//        System.out.println("Original implement - Elapsed time in milliseconds: " + timeDiff2 );
        //System.out.println(list2.isSorted());




    }
}
