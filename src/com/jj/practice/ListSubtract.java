package com.jj.practice;

// https://practice.geeksforgeeks.org/problems/subtraction-in-linked-list/1
public class ListSubtract {
    private static class Node{
        private Node next;
        private int data;
        public Node(int x) {
            data = x;
            next = null;
        }
    }

    public static void print(Node head)
    {
        Node temp = head;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.data).append(" ");
            temp = temp.next;
        }
        System.out.println(sb.toString());
    }

    public static Node sublinkedList(Node l1, Node l2)
    {
        if (l1 == null || l2 == null)   return null;            // edge cases
        if (l1 == l2)   return new Node(0);

        Node minuend = findMinuend(l1, l2);
        if(minuend == null)    return new Node(0);              // if findMiuend() returns null, return 0

        Node subtra = reverse( (minuend == l1) ? l2 : l1);      // reverse both minuend and sutra
        minuend = reverse(minuend);

        // subtract implement
        Node ans = null;
        boolean borrow = false;
        while (minuend != null) {
            int diff = (subtra == null) ? minuend.data : minuend.data - subtra.data;
            if (borrow)  diff--;
            if (diff < 0) {
                diff += 10;
                borrow = true;
            }else   borrow = false;

            if (subtra != null) subtra = subtra.next;
            minuend = minuend.next;

            Node temp = new Node(diff);             // update result
            temp.next = ans;
            ans = temp;
        }
        while (ans.data == 0)   ans = ans.next;     // delete front zeros
        return ans;
    }

    // utility method
    public static Node findMinuend(Node head1, Node head2) {
        int sizeL1 = size(head1), sizeL2 = size(head2);         // count number of nodes
        if (sizeL1 > sizeL2)    return head1;                   // compare counts
        else if (sizeL1 < sizeL2) return head2;
        else {                                                  // if counts are the same, compare inside
            Node curr1 = head1, curr2 = head2;
            while (curr1 != null && curr1.data == curr2.data) {
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            if (curr1 == null)  return null;                    // if they are the same, return null
            if (curr1.data > curr2.data)    return head1;
            else return head2;
        }
    }
    private static Node reverse(Node head) {
        Node curr = head, prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    private static int size(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(0);
        head.next.next = new Node(0);

        Node head2 = new Node(2);
        head2.next = new Node(0);
        head2.next.next = new Node(0);
        Node ans = sublinkedList(head, head2);
        print(ans);
    }
}
