package week2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay(){
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    private void update(String person) {
        int index = characters.indexOf(person);
        // if character does not exist
        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

    public void findAllCharacters() {
        // clear before open file
        characters.clear();
        counts.clear();

        FileResource fs = new FileResource();
        for (String s: fs.lines()) {
            if (s.contains(".")) {
                String person = s.substring(0, s.indexOf("."));
                update(person);
            }
            
        }
    }

    public int findIndexOfMax() {
        int index = 0;
        for (int i = 1; i < counts.size(); i++) {
            if (counts.get(i) > counts.get(index)) {
                index = i;
            }
        }
        return index;
    }

    public void tester() {
       findAllCharacters();
       for (int i = 0; i < characters.size(); i++) {
           System.out.printf("%s:%d\n", characters.get(i), counts.get(i));
       }
       charactersWithNumParts(10,15);
       int maxIndex = findIndexOfMax();
        System.out.printf("%s:%d\n", characters.get(maxIndex), counts.get(maxIndex));
    }

    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters that have between "+ num1 + " and " + num2+ " lines:");
        for (int i=0; i < characters.size(); i++){
            if (counts.get(i) >= num1 && counts.get(i)<= num2){
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
