package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day23 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day23/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day23 day = new Day23();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private long part1(ArrayList<String> list) {
        long[] mem = new long[8];
        int cursor = 0, count = 0;
        while (cursor < list.size()) {
            String[] parts = list.get(cursor).split(" ");
            if (parts[0].indexOf("set") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? mem[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                mem[parts[1].charAt(0) - 'a'] = value;
                cursor++;
            } else if (parts[0].indexOf("sub") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? mem[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                mem[parts[1].charAt(0) - 'a'] -= value;
                cursor++;
            } else if (parts[0].indexOf("mul") >= 0) {
                long value = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? mem[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                mem[parts[1].charAt(0) - 'a'] *= value;
                cursor++;
                count++;
            } else if (parts[0].indexOf("jnz") >= 0) {
                long value1 = parts[1].charAt(0) >= 'a' && parts[1].charAt(0) <= 'z' ? mem[parts[1].charAt(0) - 'a'] : Long.valueOf(parts[1]);
                long value2 = parts[2].charAt(0) >= 'a' && parts[2].charAt(0) <= 'z' ? mem[parts[2].charAt(0) - 'a'] : Long.valueOf(parts[2]);
                cursor += value1 != 0 ? value2 : 1;
            }
        }
        return count;
    }

private long part2(ArrayList<String> list) {
        long a = 1;
        long b = 93;
        long c = b;
        long d = 0;
        long e = 0;
        long f = 0;
        long g = 0;
        long h = 0;

        if (a != 0) {
            b *= 100;
            b += 100000;
            c = b;
            c += 17000;
        }
        
        for (;;) { // jnz -23 
            
            f = 1;
            d = 2;
            for (;;) { // jnz -13
                e = 109300;
                d += 1;
                g = d;
                g -= b;
                if (g == 0) {
                    break;
                }
            }
            f = 1;
            for (int r = 2; r < d; r++) {
                f = d % r == 0 ? 0 : f;
            }
            
            if (f == 0) {
                h += 1;
            }
            g = b;
            g -= c;
            if (g == 0) {
                break;
            }
            b += 17;
        }
        return h;
    }

    private long part3(ArrayList<String> list) {
        
        /*
         set b 93
        set c b
        jnz a 2
        jnz 1 5
        mul b 100
        sub b -100000
        set c b
        sub c -17000   
        set f 1
        set d 2
        set e 2
        set g d
        mul g e
        sub g b
        jnz g 2
        set f 0
        sub e -1
        set g e
        sub g b
        jnz g -8
        sub d -1
        set g d
        sub g b
        jnz g -13
        jnz f 2
        sub h -1
        set g b
        sub g c
        jnz g 2
        jnz 1 3
        sub b -17
        jnz 1 -23
         */
        
        int count = 0;
        long a = 1;
        long b = 93;
        long c = b;
        long d = 0;
        long e = 0;
        long f = 0;
        long g = 0;
        long h = 0;
        
        if (a != 0) {
            b *= 100; count++;
            b += 100000;
            c = b;
            c += 17000;
        }
        
        for (;;) { // jnz -23 
            
            f = 1;
            d = 2;
            
            for (;;) { // jnz -13
                
                e = 2;
                
                for (;;) { // jnz -8
                    g = d;
                    g *= e; count++;
                    g -=b;
                    if (g == 0) {
                        f = 0;
                    }
                    e += 1;
                    g = e;
                    g -= b;
                    
                    if (g == 0) {
                        break;
                    }
                }
                
                d += 1;
                g = d;
                g -= b;
                
                if (g == 0) {
                    break;
                }
            }
            
            if (f == 0) {
                h += 1;
                System.out.println(h);
            }
            g = b;
            g -= c;
            if (g == 0) {
                break;
            }
            b += 17;
        }
        
        return h;
    }
}
