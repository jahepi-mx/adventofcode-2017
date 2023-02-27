package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Day12 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day12/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day12 day = new Day12();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String line : list) {
            String[] parts = line.split(" <-> ");
            map.put(parts[0], new ArrayList<>());
            for (String str : parts[1].split(",")) {
                map.get(parts[0]).add(str.trim());
            }
            
        }
        HashSet<String> visited = new HashSet<>();
        visited.add("0");
        Stack<String> stack = new Stack<>();
        stack.add("0");
        while (!stack.isEmpty()) {
            ArrayList<String> tmp = map.get(stack.pop());
            for (String node : tmp) {
                if (!visited.contains(node)) {
                    stack.add(node);
                    visited.add(node);
                }
            }
        }
        return visited.size();
    }

    private int part2(ArrayList<String> list) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String line : list) {
            String[] parts = line.split(" <-> ");
            map.put(parts[0], new ArrayList<>());
            for (String str : parts[1].split(",")) {
                map.get(parts[0]).add(str.trim());
            }
            
        }
        int res = 0;
        HashSet<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        for (String tmpNode : map.keySet()) {
            if (visited.contains(tmpNode)) {
                continue;
            }
            visited.add(tmpNode);
            stack.add(tmpNode);
            while (!stack.isEmpty()) {
                ArrayList<String> tmp = map.get(stack.pop());
                for (String node : tmp) {
                    if (!visited.contains(node)) {
                        stack.add(node);
                        visited.add(node);
                    }
                }
            }
            res++;
        }
        return res;
    }
}
