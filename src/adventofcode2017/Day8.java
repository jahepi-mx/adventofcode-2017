package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day8 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day8/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day8 day = new Day8();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        HashMap<String, Integer> regs = new HashMap<>();
        for (String line : list) {
            String[] parts = line.split(" ");
            int value = Integer.valueOf(parts[2]) * (parts[1].equals("dec") ? -1 : 1);
            int value2 = regs.getOrDefault(parts[4], 0);
            int value3 = Integer.valueOf(parts[6]);
            String comp = parts[5];
            if ((comp.equals("==") && value2 == value3)
                    || (comp.equals(">=") && value2 >= value3)
                    || (comp.equals("<=") && value2 <= value3)
                    || (comp.equals("!=") && value2 != value3)
                    || (comp.equals(">") && value2 > value3)
                    || (comp.equals("<") && value2 < value3)) {
                int curVal = regs.containsKey(parts[0]) ? regs.get(parts[0]) + value : value;
                regs.put(parts[0], curVal);
                max2 = Math.max(curVal, max2);
            }    
        }
        for (String reg : regs.keySet()) {
            max = Math.max(regs.get(reg), max);
        }
        return part1 ? max : max2;
    }
}
