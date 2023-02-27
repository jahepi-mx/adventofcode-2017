package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day22 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day22/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day22 day = new Day22();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int[][] dirs = new int[][] {{0,-1},{1,0},{0,1},{-1,0}};
        int offset = 50;
        int x = list.get(0).length() / 2 + offset;
        int y = list.size() / 2 + offset;
        int pos = 0, width = 1000;
        HashSet<Integer> set = new HashSet<>();
        for (int yy = 0; yy < list.size(); yy++) {
            for (int xx = 0; xx < list.get(yy).length(); xx++) {
                if (list.get(yy).charAt(xx) == '#' ) {
                    set.add((yy + offset) * width + (xx + offset));
                }
            }
        }
        int count = 0;
        for (int a = 0; a < 10_000; a++) {
            boolean infected = set.contains(y * width + x);
            pos += infected ? 1 : -1;
            pos = (pos + 4) % 4;
            if (infected) {
                set.remove(y * width + x);
            } else {
                set.add(y * width + x);
                count++;
            }
            x += dirs[pos][0];
            y += dirs[pos][1];
        }
        return count;
    }

    private int part2(ArrayList<String> list) {
        int[][] dirs = new int[][] {{0,-1},{1,0},{0,1},{-1,0}};
        int offset = 50;
        int x = list.get(0).length() / 2 + offset;
        int y = list.size() / 2 + offset;
        int pos = 0, width = 1000;
        HashMap<Integer, Integer> set = new HashMap<>();
        for (int yy = 0; yy < list.size(); yy++) {
            for (int xx = 0; xx < list.get(yy).length(); xx++) {
                if (list.get(yy).charAt(xx) == '#' ) {
                    set.put((yy + offset) * width + (xx + offset), 2);
                }
            }
        }
        int count = 0;
        for (int a = 0; a < 10_000_000; a++) {
            int value = set.getOrDefault(y * width + x, 0);
            pos += value == 0 ? -1 : 0;
            pos += value == 2 ? 1 : 0;
            pos += value == 3 ? 2 : 0;
            pos = (pos + 4) % 4;
            value = (value + 1) % 4;
            count += value == 2 ? 1 : 0;
            set.put(y * width + x, value);
            x += dirs[pos][0];
            y += dirs[pos][1];
        }
        return count;
    }
}
