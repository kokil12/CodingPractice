import com.sun.tools.javac.util.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CharMaxOccurence {
    public static void main(String[] args) throws IOException {
        int temp;
        Map<Character, Integer> charOccurence = new HashMap<>();
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                return o1.snd - o2.snd;
            }
        });

        int k=5;
        FileReader fileReader = new FileReader("/Users/kokil.jain/code/CodingPractice/code/algorithm/src/test.txt");
        while ((temp = fileReader.read()) != -1) {
            Integer freq = charOccurence.get((char)temp);
            if (freq == null) {
                charOccurence.put((char)temp, 1);
            } else {
                charOccurence.put((char)temp, freq+1);
            }
            printTopK(pq, charOccurence, k);
            System.out.println("******************************************************************");
        }
        //System.out.println(charOccurence);

        //printTopK(pq, charOccurence, k);

    }

    private static void printTopK(PriorityQueue<Pair<Character, Integer>> pq, Map<Character, Integer> charOccurence, int k) {
        for (Map.Entry<Character, Integer> entry : charOccurence.entrySet()) {
            Pair<Character, Integer> p = new Pair<>(entry.getKey(), entry.getValue());
            pq.add(p);
            if (pq.size()>k)
                pq.poll();
        }

        //print result
        while (pq.size() != 0) {
            System.out.println(pq.poll());
        }
    }
}
