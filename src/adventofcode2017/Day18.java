package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Day18 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day18/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day18 day = new Day18();
        //System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        long[] registers = new long[26];
        long lastFeq = 0;
        int pointer = 0;
        while (pointer < list.size()) {
            String line = list.get(pointer);
            String[] parts = line.split(" ");
            if (line.indexOf("snd") >= 0) {
                lastFeq = registers[parts[1].charAt(0) - 'a'];
                pointer++;
            } else if (line.indexOf("set") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                registers[parts[1].charAt(0) - 'a'] = value;
                pointer++;
            } else if (line.indexOf("add") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                registers[parts[1].charAt(0) - 'a'] += value;
                pointer++;
            } else if (line.indexOf("mul") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                registers[parts[1].charAt(0) - 'a'] *= value;
                pointer++;
            } else if (line.indexOf("mod") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                registers[parts[1].charAt(0) - 'a'] %= value;
                pointer++;
            } else if (line.indexOf("rcv") >= 0) {
                if (registers[parts[1].charAt(0) - 'a'] != 0) {
                    System.out.println(lastFeq);
                }
                pointer++;
            } else {
                long value = parts[1].charAt(0) >= 'a' && parts[1].charAt(0) <= 'z' ? registers[parts[1].charAt(0) - 'a'] : Long.valueOf(parts[1]);
                long value2 = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                pointer += value > 0 ? value2 : 1;
            }
        }
        return 0;
    }

    private int part2(ArrayList<String> list) {
        Program p1 = new Program();
        Program p2 = new Program();
        p1.list = p2.list = list;
        p1.program = p2;
        p2.program = p1;
        p2.registers['p' - 'a'] = 1;
        
        while (true) {
             p1.run();
             p2.run();
             System.out.println(p2.count);
        }
    }
    
    
    class Program {
        Queue<Long> queue = new LinkedList<>();
        long[] registers = new long[26];
        int pointer = 0;
        ArrayList<String> list;
        Program program;
        int count;
        
        public void run() {
            while (pointer < list.size()) {
                String line = list.get(pointer);
                String[] parts = line.split(" ");
                if (line.indexOf("snd") >= 0) {
                    long value = parts[1].charAt(0) >= 'a' && parts[1].charAt(0) <= 'z' ? registers[parts[1].charAt(0) - 'a'] : Long.valueOf(parts[1]);
                    program.queue.add(value);
                    pointer++;
                    count++;
                } else if (line.indexOf("set") >= 0) {
                    long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                    registers[parts[1].charAt(0) - 'a'] = value;
                    pointer++;
                } else if (line.indexOf("add") >= 0) {
                    long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                    registers[parts[1].charAt(0) - 'a'] += value;
                    pointer++;
                } else if (line.indexOf("mul") >= 0) {
                    long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                    registers[parts[1].charAt(0) - 'a'] *= value;
                    pointer++;
                } else if (line.indexOf("mod") >= 0) {
                    long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                    registers[parts[1].charAt(0) - 'a'] %= value;
                    pointer++;
                } else if (line.indexOf("rcv") >= 0) {
                    if (!this.queue.isEmpty()) {
                        registers[parts[1].charAt(0) - 'a'] = this.queue.remove();
                    } else {
                        return;
                    }
                    pointer++;
                } else {
                    long value = parts[1].charAt(0) >= 'a' && parts[1].charAt(0) <= 'z' ? registers[parts[1].charAt(0) - 'a'] : Long.valueOf(parts[1]);
                    long value2 = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? registers[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                    pointer += value > 0 ? value2 : 1;
                }
            }
        }
    }
}
