package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day7/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day7 day = new Day7();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    HashMap<String, ArrayList<String>> map = new HashMap<>();
    HashMap<String, Integer> weights = new HashMap<>();
    HashMap<String, Integer> sumWeights = new HashMap<>();
    private String run(ArrayList<String> list, boolean part1) {
        for (String line : list) {
            String[] parts = line.split(" ");
            map.put(parts[0], new ArrayList<>());
            weights.put(parts[0], Integer.valueOf(parts[1].substring(1, parts[1].length() - 1)));
            if (parts.length > 2) {
                for (int a = 3; a < parts.length; a++) {
                    map.get(parts[0]).add(parts[a].replace(",", ""));
                }
            }
        }
        int max = 0;
        String res = "";
        for (String key : map.keySet()) {
            sumWeights.put(key, sums(key));
            int tmp = max(key);
            if (tmp > max) {
                max = tmp;
                res = key;
            }
        }
        return part1 ? res : getWeight(res, 0) + "";
    }
    
    private int getWeight(String key, int diff) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        HashMap<Integer, String> hash2 = new HashMap<>();
        int[] hash3 = new int[2];
        for (String tmp : map.get(key)) {
            hash2.put(sumWeights.get(tmp), tmp);
            hash.put(sumWeights.get(tmp), hash.containsKey(sumWeights.get(tmp)) ? 1 : 0);
        }
        for (int pos : hash.keySet()) {
            hash3[hash.get(pos)] = pos;
        }
        return hash.size() == 2 ? getWeight(hash2.get(hash3[0]), hash3[0] - hash3[1]) : weights.get(key) - diff;
    }
    
    private int max(String key) {
        int max = 0;
        for (String tmp : map.get(key)) {
            max = Math.max(max(tmp) + 1, max);
        }
        return max;
    }
    
    private int sums(String key) {
        int sum = weights.get(key);
        for (String tmp : map.get(key)) {
            sum += sums(tmp);
        }
        return sum;
    }
}
