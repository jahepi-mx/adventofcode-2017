package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day25 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day25/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day25 day = new Day25();
        System.out.println("Part 1: " + day.part1(list));
    }

    private int part1(ArrayList<String> list) {
        int[][] states = new int[26][2];
        int[][] to = new int[26][2];
        for (int a = 0; a < list.size(); a++) {
            String line = list.get(a);
            if (line.indexOf("In state") >= 0) {
                int pos = line.charAt(9) - 'A';
                int currVal1 = list.get(a + 1).charAt(26) - '0';
                int write1 = list.get(a + 2).charAt(22) - '0';
                int dir1 = list.get(a + 3).indexOf("left") >= 0 ? 1 : 2;
                int to1 = list.get(a + 4).charAt(26) - 'A';
                int currVal2 = list.get(a + 5).charAt(26) - '0';
                int write2 = list.get(a + 6).charAt(22) - '0';
                int dir2 = list.get(a + 7).indexOf("left") >= 0 ? 1 : 2;
                int to2 = list.get(a + 8).charAt(26) - 'A';
                
                states[pos][currVal1] = dir1 * 10 + write1;
                states[pos][currVal2] = dir2 * 10 + write2;
                to[pos][currVal1] = to1;
                to[pos][currVal2] = to2;
            }
        }
        int len = 1_000_000;
        int[] bits = new int[len];
        int cursor = len / 2;
        int ins = 0;
        for (int a = 0; a < 12_919_244; a++) {
            int val = bits[cursor];
            int data = states[ins][val];
            int write = data % 10;
            int dir = data / 10 % 10;
            bits[cursor] = write;
            cursor += dir == 1 ? -1 : 1;
            ins = to[ins][val];
        }
        int count = 0;
        for (int bit : bits) {
            count += bit;
        }
        return count;
    }
}
