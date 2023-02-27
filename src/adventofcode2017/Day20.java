package adventofcode2017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day20 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day20/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day20 day = new Day20();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        long t = 1_000;
        long distance = Long.MAX_VALUE;
        int particle = 0;
        int closestParticle = 0;
        for (String line : list) {
            String[] parts = line.split("<");
            String[] pos = parts[1].substring(0, parts[1].indexOf('>')).split(",");
            long px = Long.valueOf(pos[0]);
            long py = Long.valueOf(pos[1]);
            long pz = Long.valueOf(pos[2]);
            String[] vel = parts[2].substring(0, parts[2].indexOf('>')).split(",");
            long vx = Long.valueOf(vel[0]);
            long vy = Long.valueOf(vel[1]);
            long vz = Long.valueOf(vel[2]);
            String[] acc = parts[3].substring(0, parts[3].indexOf('>')).split(",");
            long ax = Long.valueOf(acc[0]);
            long ay = Long.valueOf(acc[1]);
            long az = Long.valueOf(acc[2]);
            px += t * vx + (t * t + t) / 2 * ax;
            py += t * vy + (t * t + t) / 2 * ay;
            pz += t * vz + (t * t + t) / 2 * az;
            long tmpDist = Math.abs(px) + Math.abs(py) + Math.abs(pz);
            if (tmpDist < distance) {
                distance = tmpDist;
                closestParticle = particle;
            }
            particle++;
        }
        return closestParticle;
    }

    private int part2(ArrayList<String> list) {
        int[] collided = new int[list.size() + 1];
        int count = 0;
        for (int t = 1; t <= 500; t++) {
            HashMap<String, Integer> map = new HashMap<>();
            int particle = 0;
            for (String line : list) {
                if (collided[particle] == 0) {
                    String[] parts = line.split("<");
                    String[] pos = parts[1].substring(0, parts[1].indexOf('>')).split(",");
                    long px = Long.valueOf(pos[0]);
                    long py = Long.valueOf(pos[1]);
                    long pz = Long.valueOf(pos[2]);
                    String[] vel = parts[2].substring(0, parts[2].indexOf('>')).split(",");
                    long vx = Long.valueOf(vel[0]);
                    long vy = Long.valueOf(vel[1]);
                    long vz = Long.valueOf(vel[2]);
                    String[] acc = parts[3].substring(0, parts[3].indexOf('>')).split(",");
                    long ax = Long.valueOf(acc[0]);
                    long ay = Long.valueOf(acc[1]);
                    long az = Long.valueOf(acc[2]);
                    px += t * vx + (t * t + t) / 2 * ax;
                    py += t * vy + (t * t + t) / 2 * ay;
                    pz += t * vz + (t * t + t) / 2 * az;
                    String key = px + " " + py + " " + pz;
                    if (!map.containsKey(key)) {
                        map.put(key, particle);
                    } else {
                        count += collided[particle] == 0 ? 1 : 0;
                        count += collided[map.get(key)] == 0 ? 1 : 0;
                        collided[particle] = 1;
                        collided[map.get(key)] = 1;
                    }
                }
                particle++;
            }
        }
        return list.size() - count;
    }
}
