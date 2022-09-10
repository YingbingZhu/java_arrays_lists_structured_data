package week1;
import edu.duke.*;

import java.util.Arrays;
import java.util.Comparator;

public class WordLengths {
    /**
     * a FileResource named resource and an integer array named counts.
     * This method should read in the words from resource
     * and count the number of words of each length for all the words in resource, storing these counts in the array counts.
     * @param resource
     * @param counts
     */
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String s: resource.words()) {
            int sLength = 0;
            //  If a word has a non-letter as the first or last character, it should not be counted as part of the word length
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isLetter(ch) || (i != 0 && i != s.length() - 1 )) {
                    sLength += 1;
                }
            }
            if (sLength < counts.length ) {
                counts[sLength] += 1;
            } else {
                // For any words equal to or larger than the last index of the counts array, count them as the largest size represented in the counts array.
                counts[counts.length - 1] += 1;
            }
        }
        
    }

    void testCountWordLengths(){
        FileResource resource = new FileResource("week1/data/manywords.txt");
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for(int k=0; k < counts.length; k++){
            System.out.println(k + "\t" + counts[k]);
        }

        System.out.println(indexOfMax(counts));
        
    }

    /**
     * 
     * @param values
     * @return the index position of the largest element in values
     */
    public int indexOfMax(int[] values) {
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[max]) {
                max = i;
            }
        }
        return max;
    }
    

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
