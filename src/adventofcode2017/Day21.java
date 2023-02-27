package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day21 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day21/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day21 day = new Day21();
        System.out.println("Part 1: " + day.run(list, 5));
        System.out.println("Part 2: " + day.run(list, 18));
    }

    private int run(ArrayList<String> list, int iterations) {
        String[] rot3x3 = new String[] {"210543876", "678345012", "258147036"};
        String[] rot2x2 = new String[] {"1032", "2301", "1302"};
        HashMap<String, String> values = new HashMap<>();
        HashSet<Integer> pos = new HashSet<>();
        pos.add(1);
        pos.add(5);
        pos.add(6);
        pos.add(7);
        pos.add(8);
        int width = 3, count = 0;
        
        for (String line: list) {
            String[] parts = line.split(" => ");
            String pattern = parts[0].replaceAll("/", "");
            for (int a = 0; a < 4; a++) {
                String tmpKey = "";
                for (String moves : (pattern.length() % 3 == 0 ? rot3x3 : rot2x2)) {
                    tmpKey = "";
                    for (char ch : moves.toCharArray()) {
                        tmpKey += pattern.charAt(ch - '0');
                    }
                    values.put(tmpKey, parts[1].replaceAll("/", ""));
                }
                pattern = tmpKey;
            }
        }
        
        for (int i = 0; i < iterations; i++) {
            int sWidth = width % 2 == 0 ? 2 : 3;
            int segs = width % 2 == 0 ? width / 2 : width / 3;
            HashSet<Integer> newPos = new HashSet<>();
            for (int x = 0; x < segs; x++) {
                for (int y = 0; y < segs; y++) {
                    String key = "";
                    for (int a = 0; a < sWidth * sWidth; a++) {
                        int nx = x * sWidth + a % sWidth;
                        int ny = y * sWidth + a / sWidth;
                        key += pos.contains(ny * width + nx) ? "#" : ".";
                    }
                    if (values.containsKey(key)) {
                        String newKey = values.get(key);
                        int sWidth2 = sWidth + 1, tmpPos = 0;
                        for (char ch : newKey.toCharArray()) {
                            if (ch == '#') {
                                int ox = x * sWidth2 + tmpPos % sWidth2;
                                int oy = y * sWidth2 + tmpPos / sWidth2;
                                newPos.add(oy * sWidth2 * segs + ox);
                            }
                            tmpPos++;
                        }
                    }
                }
            }
            pos = newPos;
            width = (sWidth + 1) * segs;
            count = pos.size();
        }
        return count;
    }
}
