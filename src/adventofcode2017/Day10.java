package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day10 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day10/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day10 day = new Day10();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int[] arr = new int[256];
        int pointer = 0;
        int skip = 0;
        for (int a = 0; a < arr.length; a++) {
            arr[a] = a;
        }
        for (String n : list.get(0).split(",")) {
            int len = Integer.valueOf(n.trim());
            int from = pointer % arr.length;
            int to = (from + len - 1) % arr.length;
            int counter = 0;
            while (counter < (len + skip - 1) / 2) {
                int tmp = arr[from];
                arr[from] = arr[to];
                arr[to] = tmp;
                from++;
                to--;
                from = from % arr.length;
                to = (to + arr.length) % arr.length;
                counter++;
            }
            pointer = (pointer + skip + len) % arr.length;
            skip++;
            
        }
        return arr[0] * arr[1];
    }

    private String part2(ArrayList<String> list) {
        int[] arr = new int[256];
        int pointer = 0;
        int skip = 0;
        for (int a = 0; a < arr.length; a++) {
            arr[a] = a;
        }
        String tmpStr = "";
        for (byte b : list.get(0).getBytes()) {
            tmpStr += b  + ",";
        }
        tmpStr += "17,31,73,47,23";
        for (int a = 0; a < 64; a++) {
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
        String out = "";
        for (int y = 0; y < 256; y += 16) {
            int xor = 0;
            for (int x = 0; x < 16; x++) {
                xor ^= arr[y + x];
            }
            String hex = Integer.toHexString(xor);
            out += (hex.length() == 1 ? "0" + hex : hex);
        }
        return out;
    }
}
