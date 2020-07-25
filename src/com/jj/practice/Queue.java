package com.jj.practice;
// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Queue.java.html
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item>  next;
    }

    public Queue()  {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty()    {
        return first == null;
    }

    public int size()   {
        return n;
    }

    public Item peek()  {
        if (isEmpty())  throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public void enqueue(Item item)  {
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else    oldlast.next = last;
        n++;
    }

    public Item dequeue()   {
        if (isEmpty())  throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())  last = null;
        return item;
    }

    public Iterator<Item> iterator()    {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        try (Scanner sc = new Scanner(new File("C:\\Users\\jjhan\\IdeaProjects\\Algorithm\\Data\\tobe.txt"))) {
            while (sc.hasNext()) {
                String item = sc.next();
                if (!item.equals("-")) queue.enqueue(item);
                else if (!queue.isEmpty())
                    System.out.print(queue.dequeue() + " ");
            }
            System.out.println("(" + queue.size() + " left on queue)");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

