package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day1/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day1 day = new Day1();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        char[] chars = list.get(0).toCharArray();
        for (int a = 0; a < chars.length; a++) {
            if (chars[a] == chars[(a + 1) % chars.length]) {
                res += chars[a] - '0';
            }
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        char[] chars = list.get(0).toCharArray();
        for (int a = 0; a < chars.length; a++) {
            if (chars[a] == chars[(a + chars.length / 2) % chars.length]) {
                res += chars[a] - '0';
            }
        }
        return res;
    }
}

