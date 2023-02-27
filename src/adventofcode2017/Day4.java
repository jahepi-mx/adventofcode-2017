package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day4 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day4/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day4 day = new Day4();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            HashSet<String> set = new HashSet<>();
            boolean isValid = true;
            for (String word : line.split(" ")) {
                isValid = set.contains(word) ? false : isValid;
                set.add(word);
            }
            res += isValid ? 1 : 0;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            HashSet<String> set = new HashSet<>();
            boolean isValid = true;
            for (String word : line.split(" ")) {
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String newWord = new String(chars);
                isValid = set.contains(newWord) ? false : isValid;
                set.add(newWord);
            }
            res += isValid ? 1 : 0;
        }
        return res;
    }
}

