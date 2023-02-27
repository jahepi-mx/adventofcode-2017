package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day11 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day11/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day11 day = new Day11();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("n", 0);
        map.put("ne", 1);
        map.put("se", 2);
        map.put("s", 3);
        map.put("sw", 4);
        map.put("nw", 5);
        
        int[][] dirs = new int[][] {{0,1}, {1,0}, {1,-1}, {0,-1}, {-1,0}, {-1,1}};
        String[] cmds = list.get(0).split(",");
        int x = 0;
        int y = 0;
        int max = 0;
        for (String cmd : cmds) {
            x += dirs[map.get(cmd)][0];
            y += dirs[map.get(cmd)][1];
            max = Math.max(max, Math.abs(x) + Math.abs(y));
        }
        return part1 ? Math.abs(x) + Math.abs(y) : max;
    }
}
