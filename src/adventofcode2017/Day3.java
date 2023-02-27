package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day3 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day3/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day3 day = new Day3();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        int step = 0;
        int x = 0;
        int y = 0;
        int num = 1;
        int[][] dirs = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}, {1,1}, {-1,1}, {-1,-1}, {1,-1}};
        HashMap<Integer, Integer> map = new HashMap<>();
        while (true) {
            if (!map.containsKey(y * 1000 + x)) {
                int sum = 0;
                for (int[] adj : dirs) {
                    sum += map.getOrDefault((y + adj[1]) * 1000 + (x + adj[0]), 0);
                }
                map.put(y * 1000 + x, sum == 0 ? 1 : sum);
                if (!part1 && sum > 312051) {
                    return sum;
                }
                if (part1 && num == 312051) {
                    return Math.abs(x) + Math.abs(y);
                }
                num++;
            } else {
                int tmp = ((step - 1) % 4 + 4) % 4;
                x += dirs[tmp][0] * -1;
                y += dirs[tmp][1] * -1;
                step = ((step - 2) % 4 + 4) % 4;
            }
            x += dirs[step % 4][0];
            y += dirs[step++ % 4][1];
        }
    }
}

