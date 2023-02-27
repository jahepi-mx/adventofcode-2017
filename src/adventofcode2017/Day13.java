package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day13 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day13/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day13 day = new Day13();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        HashMap<Integer, Integer> heights = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (String line : list) {
            String[] parts = line.split(":");
            int index = Integer.valueOf(parts[0].trim());
            heights.put(index, Integer.valueOf(parts[1].trim()));
            max = Math.max(max, index);
        }
        int[] firewall = new int[max + 1];
        int[] dirs = new int[max + 1];
        Arrays.fill(dirs, 1);
        int res = 0;
        for (int a = 0; a < firewall.length; a++) {
            int height = heights.getOrDefault(a, 0);
            res += height > 0 && firewall[a] == 0 ? height * a : 0;
            for (int b = 0; b < firewall.length; b++) {
                height = heights.getOrDefault(b, 0);
                if (height > 0) {
                    dirs[b] *= firewall[b] + dirs[b] >= height || firewall[b] + dirs[b] < 0 ? -1 : 1;
                    firewall[b] += dirs[b];
                }
            }
        }
        return res;
    }
    
    private int part2(ArrayList<String> list) {
        HashMap<Integer, Integer> heights = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (String line : list) {
            String[] parts = line.split(":");
            int index = Integer.valueOf(parts[0].trim());
            heights.put(index, Integer.valueOf(parts[1].trim()));
            max = Math.max(max, index);
        }
        int[] firewall = new int[max + 1];
        int[] firewallCopy = new int[max + 1];
        int[] dirs = new int[max + 1];
        int[] dirsCopy = new int[max + 1];
        Arrays.fill(dirs, 1);
        int res = 0;
        boolean isOk = false;
        while (!isOk) {
            res++;
            isOk = true;
            for (int b = 0; b < firewall.length; b++) {
                int height = heights.getOrDefault(b, 0);
                if (height > 0) {
                    dirs[b] *= firewall[b] + dirs[b] >= height || firewall[b] + dirs[b] < 0 ? -1 : 1;
                    firewall[b] += dirs[b];
                }
            }
            dirsCopy = dirs.clone();
            firewallCopy = firewall.clone();
            for (int a = 0; a < firewallCopy.length; a++) {
                if (heights.getOrDefault(a, 0) > 0 && firewallCopy[a] == 0) {
                    isOk = false;
                    break;
                }
                for (int b = 0; b < firewall.length; b++) {
                    int height = heights.getOrDefault(b, 0);
                    if (height > 0) {
                        dirsCopy[b] *= firewallCopy[b] + dirsCopy[b] >= height || firewallCopy[b] + dirsCopy[b] < 0 ? -1 : 1;
                        firewallCopy[b] += dirsCopy[b];
                    }
                }
            }
        }
        return res;
    }
}
