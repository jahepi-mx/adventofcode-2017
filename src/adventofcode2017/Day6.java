package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day6 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day6/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day6 day = new Day6();
        System.out.println("Part 1: " + day.run(list.get(0), true));
        System.out.println("Part 2: " + day.run(list.get(0), false));
    }

    private int run(String str, boolean part1) {
        int res = 0;
        String[] nums = str.split("\\s+");
        int[] values = new int[nums.length];
        for (int a = 0 ; a < nums.length; a++) {
            values[a] = Integer.valueOf(nums[a]);
        }
        HashSet<String> set = new HashSet<>();
        while (true) {
            int index = 0; int max = 0;
            for (int a = 0; a < values.length; a++) {
                if (values[a] > max) {
                    max = values[a];
                    index = a;
                }
            }
            int tmp = values[index];
            values[index] = 0;
            for (int a = 0, b = index + 1; a < tmp; a++, b++) {
                values[b % values.length]++;
            }
            String key = "";
            for (int n : values) {
                key += n + " ";
            }
            if (set.contains(key)) {
                return part1 ? ++res : run(key, true) - 1;
            }
            set.add(key);
            res++;
        }
    }
}
