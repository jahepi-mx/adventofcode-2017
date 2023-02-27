package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day2/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day2 day = new Day2();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            String[] parts = line.split("\\s+");
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (String str : parts) {
                min = Math.min(min, Integer.valueOf(str));
                max = Math.max(max, Integer.valueOf(str));
            }
            res += max - min;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            String[] parts = line.split("\\s+");
            for (int a = 0; a < parts.length; a++) {
                int n1 = Integer.valueOf(parts[a]);
                for (int b = a + 1; b < parts.length; b++) {
                    int n2 = Integer.valueOf(parts[b]);
                    if (n1 % n2 == 0 || n2 % n1 == 0) {
                        res += n1 % n2 == 0 ? n1 / n2 : n2 / n1;
                    } 
                }
            }
        }
        return res;
    }
}

