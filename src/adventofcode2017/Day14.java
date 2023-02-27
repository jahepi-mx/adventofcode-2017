package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day14 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day14/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day14 day = new Day14();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    int[] map = new int[128 * 128];
    private int part1(ArrayList<String> list) {
        int res = 0;
        for (int i = 0; i < 128; i++) {
            int[] arr = new int[256];
            int pointer = 0;
            int skip = 0;
            for (int a = 0; a < arr.length; a++) {
                arr[a] = a;
            }
            String tmpStr = "";
            for (byte b : ("nbysizxe-" + i).getBytes()) {
                tmpStr += b  + ",";
            }
            tmpStr += "17,31,73,47,23";
            for (int e = 0; e < 64; e++) {
                for (String n : tmpStr.split(",")) {
                    int len = Integer.valueOf(n.trim());
                    int from = pointer % arr.length;
                    int to = (from + len - 1) % arr.length;
                    HashSet<Integer> set = new HashSet<>();
                    while (!set.contains(from)) {
                        set.add(from);
                        set.add(to);
                        int tmp = arr[from];
                        arr[from] = arr[to];
                        arr[to] = tmp;
                        from++;
                        to--;
                        from = from % arr.length;
                        to = (to + arr.length) % arr.length;
                    }
                    pointer = (pointer + skip + len) % arr.length;
                    skip++;
                }
            }
            for (int y = 0, c = 0; y < 256; y += 16) {
                int xor = 0;
                for (int x = 0; x < 16; x++) {
                    xor ^= arr[y + x];
                }
                for (int k = 0; k < 8; k++) {
                    int bit = (xor & (1 << (7 - k))) >> (7 - k);
                    res += bit;
                    map[i * 128 + c++] = bit;
                    
                }
            }
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (int a = 0; a < 128 * 128; a++) {
            res += dfs(a);
        }
        return res;
    }
    
    int dfs(int pos) {
        int res = 0;
        if (map[pos] == 1) {
            map[pos] = 0;
            res = 1;
            int x = pos % 128;
            int y = pos / 128;
            for (int[] dir : new int[][] {{1,0},{0,1},{-1,0},{0,-1}}) {
                if (x + dir[0] >= 0 && x + dir[0] < 128 && y + dir[1] >= 0 && y + dir[1] < 128) {
                     res = res | dfs((y + dir[1]) * 128 + x + dir[0]);
                }
            }
        }
        return res;
    }
}
