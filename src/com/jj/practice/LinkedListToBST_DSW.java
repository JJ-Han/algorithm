package com.jj.practice;

// convert linked list to BST with DSW algorithm
// linear algorithm with time complexity O(n) and constant space
// https://en.wikipedia.org/wiki/Day%E2%80%93Stout%E2%80%93Warren_algorithm

public class LinkedListToBST_DSW {
    private class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode() {}
        public TreeNode(int val) {
            this.val = val;
        }
    }

    private class ListNode {
        private int val;
        private ListNode next;
    }

    private int n = 0;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        TreeNode root = listToTree(head);
        int m = (int) Math.pow(2, (int) ( Math.log(n + 1) / Math.log(2) )) - 1;

        compress(root, n-m);
        while (m > 1)
            compress(root, m/=2);
        return root.right;
    }

    // convert linked list to skewed tree while counting size of linked list
    private TreeNode listToTree(ListNode head) {
        TreeNode treeH = new TreeNode(), curr = treeH;
        while (head != null) {
            curr = curr.right = new TreeNode(head.val);
            head = head.next;
            n++;      // count size of the list
        }
        return treeH;
    }

    private void compress(TreeNode x, int cnt) {
        while (cnt-- > 0) {
            TreeNode prev = x;
            x = x.right;
            TreeNode left = x;
            x = x.right;
            prev.right = x;
            left.right = x.left;
            x.left = left;
        }
    }
}
