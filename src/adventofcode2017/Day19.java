package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day19 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day19/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day19 day = new Day19();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private String run(ArrayList<String> list, boolean part1) {
        int[][] dirs = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int curr = 0;
        int x = 0;
        int y = 0;
        int steps = 0;
        char[] chs = list.get(0).toCharArray();
        for (int a = 0; a < chs.length; a++) {
            x = chs[a] == '|' ? a : x;
        }
        HashSet<Character> visited = new HashSet<>();
        boolean isRunning = true;
        String res = "";
        int finalSteps = 0;
        while (isRunning) {
            char ch = list.get(y).charAt(x);
            if (ch == '+') {
                int tmp = curr;
                for (int a = 0; a < dirs.length; a++) {
                    int tx = x + dirs[curr][0];
                    int ty = y + dirs[curr][1]; 
                    if (ty >= 0 && ty < list.size() && tx >= 0 && tx < list.get(ty).length()
                        && list.get(ty).charAt(tx) != ' '
                        && (dirs[tmp][0] != dirs[curr][0] * -1 && dirs[tmp][1] != dirs[curr][1] * -1)) {
                        break;
                    } else {
                        curr = ++curr % 4;
                    }
                }   
            }
            x += dirs[curr][0];
            y += dirs[curr][1]; 
            steps++;
            isRunning = y >= 0 && y < list.size() && x >= 0 && x < list.get(y).length();
            boolean isOk = isRunning && list.get(y).charAt(x) >= 'A' && list.get(y).charAt(x) <= 'Z';
            res += isOk && !visited.contains(list.get(y).charAt(x)) ? list.get(y).charAt(x) : "";
            if (isOk && visited.contains(list.get(y).charAt(x))) {
                isRunning = false;
            } else if (isOk) {
                visited.add(list.get(y).charAt(x));
                finalSteps = steps + 1;
            }
        }
        return part1 ? res : finalSteps + "";
    }
}
