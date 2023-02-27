package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day17 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day17/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day17 day = new Day17();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }
    
    class Node {
        Node prev;
        Node next;
        int value;
    }
    
    private void insert(Node node, Node newNode) {
        Node next = node.next;
        node.next = newNode;
        next.prev = newNode;
        newNode.prev = node;
        newNode.next = next;
    }

    private int part1(ArrayList<String> list) {
        int value = 0;
        Node root = new Node();
        root.value = value;
        root.prev = root;
        root.next = root;
        Node tmp = root;
        int to  = Integer.valueOf(list.get(0));
        for (int a = 0; a < 2017; a++) {
            for (int i = 0; i < to; i++) {
                tmp = tmp.next;
            }
            Node newNode = new Node();
            newNode.value = ++value;
            insert(tmp, newNode);
            tmp = newNode;
        }
        return tmp.next.value;
    }

    private int part2(ArrayList<String> list) {
        // This takes a long ass time to finish, be warned, 
        // did not look for a better efficient solution.
        int value = 0;
        Node root = new Node();
        root.value = value;
        root.prev = root;
        root.next = root;
        Node tmp = root;
        int to  = Integer.valueOf(list.get(0));
        for (int a = 0; a < 50_000_000; a++) {
            for (int i = 0; i < to; i++) {
                tmp = tmp.next;
            }
            Node newNode = new Node();
            newNode.value = ++value;
            insert(tmp, newNode);
            tmp = newNode;
        }
        return root.next.value;
    }
}
