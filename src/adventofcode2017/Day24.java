package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day24 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day24/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day24 day = new Day24();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    private int run(ArrayList<String> list, boolean part1) {
        map = new HashMap<>();
        for (String line : list) {
            String[] parts = line.split("/");
            int v1 = Integer.valueOf(parts[0]);
            int v2 = Integer.valueOf(parts[1]);
            if (!map.containsKey(v1)) {
                map.put(v1, new ArrayList<>());
            }
            if (!map.containsKey(v2)) {
                map.put(v2, new ArrayList<>());
            }
            map.get(v1).add(v2);
            map.get(v2).add(v1);
        }
        int res = getMax(0, 0, 0);
        return part1 ? res : maxLenghts[maxLen];
    }
    
    int[][] visited = new int[100][100];
    int[] maxLenghts = new int[100];
    int maxLen = 0;
    private int getMax(int node, int sum, int len) {
        int max  = sum;
        maxLenghts[len] = Math.max(maxLenghts[len], max);
        maxLen = Math.max(maxLen, len);
        for (int nodeTmp : map.get(node)) {
            if (visited[node][nodeTmp] == 0 && visited[nodeTmp][node] == 0) {
                visited[node][nodeTmp] = visited[nodeTmp][node] = 1;
                max = Math.max(max, getMax(nodeTmp, sum + node + nodeTmp, len + 1));
                visited[node][nodeTmp] = visited[nodeTmp][node] = 0;
            }
        }
        return max;
    }
}
