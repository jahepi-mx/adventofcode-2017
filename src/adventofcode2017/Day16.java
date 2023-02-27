package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day16 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day16/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day16 day = new Day16();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        HashSet<String> set = new HashSet<>();
        int len = 16;
        int[] arr = new int[len];
        int[] pos = new int[len];
        for (int a = 0; a < len; a++) {
            arr[a] = a;
            pos[a] = a;
        }
        
        long t = 16393442 * 61 + 39;
        System.out.println(t);
        for (int o = 1; o <100_000_000; o++) {
            
            String str = "";
            for (int n : arr) {
                str += (char )(n + 'a');
            }
            
            if (set.contains(str)) {
                System.out.println(o + " " + str);
            } else {
                set.add(str);
            }
            
            
            for (String line : list.get(0).split(",")) {
                if (line.charAt(0) == 's') {
                    int value = Integer.valueOf(line.substring(1, line.length()));
                    int[] tmp = new int[len];
                    for (int a = value, b = 0; a < len; a++) {
                        tmp[a] = arr[b++];
                        pos[tmp[a]] = a;
                    }
                    for (int a = 0, b = len - value; a < value; a++) {
                        tmp[a] = arr[b++];
                        pos[tmp[a]] = a;
                    }
                    arr = tmp;  
                } else if (line.charAt(0) == 'x') {
                    String[] parts = line.substring(1, line.length()).split("\\/");
                    int a = Integer.valueOf(parts[0]);
                    int b = Integer.valueOf(parts[1]);
                    int tmp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = tmp;
                    pos[arr[a]] = a;
                    pos[arr[b]] = b;
                } else {
                    int a = line.charAt(1) - 'a';
                    int b = line.charAt(3) - 'a';
                    int pos1 = pos[a];
                    int pos2 = pos[b];
                    int tmp = arr[pos1];
                    arr[pos1] = arr[pos2];
                    arr[pos2] = tmp;
                    pos[arr[pos1]] = pos1;
                    pos[arr[pos2]] = pos2;
                }
            }
        }
        String str = "";
        for (int n : arr) {
            str += (char )(n + 'a');
        }
        System.out.println(str);
        return 0;
    }

    private int part2(ArrayList<String> list) {
        return 0;
    }
}
