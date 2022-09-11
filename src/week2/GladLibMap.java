package week2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    // maps a String representing a category to an ArrayList of words in that category
    private HashMap<String, ArrayList<String>> myMap;
    // keep track of words that have been seen
    private ArrayList<String> trackList;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "src/week2/data";

    public GladLibMap(){
        myRandom = new Random();
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        trackList = new ArrayList<>();
    }

//    public GladLibMap(String source){
//        initializeFromSource(source);
//        myRandom = new Random();
//        myMap = new HashMap<>();
//    }

    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String s: labels){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (true) {
            if (!trackList.contains(sub)) {
                trackList.add(sub);
                break;
            } else {
                sub = getSubstitute(w.substring(first+1,last));
            }
        }
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        trackList.clear();
        System.out.println("\n");
        String story = fromTemplate("src/week2/data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n" + "Number of words replaced: " + trackList.size());
        System.out.println("Total number of words to pick from " + totalWordsInMap());
        System.out.println("Total words considered " + totalWordsConsidered());
    }

    private int totalWordsInMap(){
        int i = 0;
        for (String s: myMap.keySet()){
            i += myMap.get(s).size();
        }
        return i;
    }

    private int totalWordsConsidered(){
        int i = 0;
        ArrayList<String> al = new ArrayList<String>();
        FileResource fr = new FileResource("src/week2/data/madtemplate2.txt");
        for (String s: fr.words()){
            if (s != processWord(s)){
                String a = s.substring(s.indexOf("<") + 1, s.indexOf(">"));
                if (a == "number"){
                    continue;
                }
                if (al.contains(a) == false){
                    al.add(a);
                }
            }
        }
        for (String b: al){
            i += myMap.get(b).size();
        }
        return i;
    }


    public static void main(String[] args) {
        GladLibMap glm = new GladLibMap();
        glm.makeStory();
    }



}
