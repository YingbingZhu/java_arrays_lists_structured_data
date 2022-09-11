package week2;

import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Map;

public class CodonCount {
    private HashMap<String, Integer> counts;

    public CodonCount() {
        counts = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna){
        counts.clear();
        for (int i = start; i < dna.length(); i += 3){
            String s = dna.substring(i, i+3);
            if (!counts.containsKey(s)) {
                counts.put(s, 1);
            } else {
                counts.put(s, counts.get(s) + 1);
            }
        }
    }

    public String getMostCommonCodon() {
        int high = 0;
        String mostCommonCodon = "";
        for (String s : counts.keySet()) {
            int currentCount = counts.get(s);
            if (currentCount > high) {
                mostCommonCodon = s;
                high = currentCount;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String s : counts.keySet()) {
            int count = counts.get(s);
            if (count >= start && count <= end) {
                System.out.println(s+"\t"+count);
            }

        }
    }

    public void testBuildCodonMap() {
        FileResource fileResource = new FileResource();
        String dna = fileResource.asString();
        dna = dna.toUpperCase();

        for (int index=0;index <= 2;index++) {
            System.out.println("\nTesting with start position "+index+":\n");
            buildCodonMap(index,dna);
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Total unique codons found: "+counts.size());
            System.out.println("\nMost common codon: "+mostCommonCodon
                    +"\t"+counts.get(mostCommonCodon));
            printCodonCounts(4,8);
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.testBuildCodonMap();
    }

    
}
