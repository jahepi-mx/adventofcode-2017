package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day5 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day5/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day5 day = new Day5();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int[] values = new int[list.size()];
        for (int a = 0 ; a < list.size(); a++) {
            values[a] = Integer.valueOf(list.get(a));
        }
        int cursor = 0, res = 0;
        while (cursor < values.length) {
            int tmp = cursor;
            cursor += values[cursor];
            values[tmp]++;
            res++;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int[] values = new int[list.size()];
        for (int a = 0 ; a < list.size(); a++) {
            values[a] = Integer.valueOf(list.get(a));
        }
        int cursor = 0, res = 0;
        while (cursor < values.length) {
            int tmp = cursor;
            cursor += values[cursor];
            values[tmp] += values[tmp] >= 3 ? -1 : 1;
            res++;
        }
        return res;
    }
}


