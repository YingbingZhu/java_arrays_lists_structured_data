package week2;

import edu.duke.FileResource;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    /**
     * constructor
     */
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique() {
        // clear both myWords and myFreqs
        myWords.clear();
        myFreqs.clear();

        // selects a file and then iterates over every word in the file, putting the unique words found into myWords
        FileResource fs = new FileResource();
        for (String word: fs.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index==-1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
        
    }

    /**
     * 
     * @return the index location of the largest value in myFreqs
     */
    public int findIndexOfMax() {
        int index = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(index)) {
                index = i;
            }
        }
        return index;
    }

    public void tester() {
        findUnique();
        for (String s: myWords) {
            System.out.println(s);
        }
        for (int i = 0; i < myWords.size(); i++) {
            System.out.printf("%s\tcount:\t%d\n", myWords.get(i), myFreqs.get(i));
        }
        
        int idx = findIndexOfMax();
        String word = myWords.get(idx);
        int freq = myFreqs.get(idx);
        System.out.printf("Number of unique words: %d\n", myWords.size());
        System.out.printf("The word that occurs most often: >%s<: %d\n", word, freq);
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }

    


}
