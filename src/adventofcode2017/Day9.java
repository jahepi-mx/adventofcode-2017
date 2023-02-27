package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Day9 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day9/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day9 day = new Day9();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        char[] chars = list.get(0).toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean removingTrash = false;
        boolean cancelled = false;
        int res = 0, count = 0, nCancelled = 0;
        for (int a = 0; a < chars.length; a++) {
            char ch = chars[a];
            if (!removingTrash && ch == '{') {
                stack.push(ch);
            } else if (!removingTrash && ch == '}') {
                res += stack.size();
                stack.pop();
            } else if (!removingTrash && ch == '<') {
                removingTrash = true;
            } else {
                
                if (!cancelled && ch == '>') {
                    count -= nCancelled * 2;
                    nCancelled = 0;
                    removingTrash = false;
                }
                count += removingTrash ? 1 : 0;
                if (!cancelled && ch == '!') {
                    cancelled = true;
                    nCancelled++;
                } else {
                    cancelled = false;
                }
            }
        }
        return part1 ? res : count;
    }
}
