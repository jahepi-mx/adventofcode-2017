package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Day15 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day15/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day15 day = new Day15();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        long a = 16807;
        long b = 48271;
        long v1 = 699;
        long v2 = 124;
        long mod = 2147483647;
        int res = 0;
        for (int c = 0; c < 40_000_000; c++) {
            v1 = (v1 * a) % mod;
            v2 = (v2 * b) % mod;
            res += (v1 & ((1<<16) - 1)) == (v2 & ((1<<16) - 1)) ? 1 : 0;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        long a = 16807;
        long b = 48271;
        long v1 = 699;
        long v2 = 124;
        long mod = 2147483647;
        int res = 0, count = 0;
        Queue<Long> queueA = new LinkedList<>();
        Queue<Long> queueB = new LinkedList<>();
        here: while (true) {
            v1 = (v1 * a) % mod;
            v2 = (v2 * b) % mod;
            if (v1 % 4 == 0) {
                queueA.add(v1);
            }
            if (v2 % 8 == 0) {
                queueB.add(v2);
            }
            while (!queueA.isEmpty() && !queueB.isEmpty()) {
                long tmp1 = queueA.remove();
                long tmp2 = queueB.remove();
                count++;
                res += (tmp1 & ((1<<16) - 1)) == (tmp2 & ((1<<16) - 1)) ? 1 : 0;
                if (count == 5_000_000) {
                    break here;
                }
            }
        }
        return res;
    }
}
