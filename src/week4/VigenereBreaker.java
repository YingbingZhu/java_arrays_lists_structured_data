package week4;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String s = "";
        for (int i = whichSlice; i < message.length(); i+= totalSlices) {
            s += message.charAt(i);
        }
        return s;
    }

    /**
     * 
     * @param encrypted encrypted message
     * @param klength the key length
     * @param mostCommon the most common character in the language of the message
     * @return
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cck = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String s =  sliceString(encrypted, i, klength);
            int num = cck.getKey(s);
            key[i] = num;

        }
        return key;
    }

    public void breakVigenere (int klength) {
        FileResource fr = new FileResource();
        String s = fr.asString();
        int[] key = tryKeyLength(s, klength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String msg = vc.decrypt(s);
        System.out.println(msg);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> set = new HashSet<>();
        for (String s: fr.lines()){
            set.add(s.toLowerCase());
        }
        return set;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] sa = message.split("\\W+");
        int i = 0;
        for (String s: sa){
            String slower = s.toLowerCase();
            if (dictionary.contains(slower)){
                i++;
            }
        }
        return i;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        char c = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++){
            int[] key = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            if (a > max){
                max = a;
            }
        }
        for (int j = 1; j <= 100; j++){
            int[] key = tryKeyLength(encrypted, j, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            if (a == max){
                return s;
            }
        }
        return null;
    }

    public void breakVigenere() {
        FileResource msgfr = new FileResource();
        String s = msgfr.asString();
        DirectoryResource dr = new DirectoryResource();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> hm = new HashMap<String, HashSet<String>>();
        for(int i = 0; i < languages.length; i++) {
                FileResource fr = new FileResource("week4/dictionaries/"+languages[i]);
            if(!hm.containsKey(languages[i])) {
                hm.put(languages[i], readDictionary(fr));
                System.out.println("Done reading " + languages[i] + " dictionary");
            }
        }
        System.out.println();
        breakForAllLanguages(s, hm);
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (String s: dictionary){
            String slower = s.toLowerCase();
            for (char c: slower.toCharArray()){
                if (hm.containsKey(c)){
                    hm.put(c, hm.get(c) + 1);
                }
                else{
                    hm.put(c, 1);
                }
            }
        }
        int max = 0;
        for (char c: hm.keySet()){
            if (hm.get(c) > max){
                max = hm.get(c);
            }
        }
        for (char c: hm.keySet()){
            if (hm.get(c) == max){
                return c;
            }
        }
        return 'n';
    }

    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        int max = 0;
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            if (i > max){
                max = i;
            }
        }
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            if (i == max){
                System.out.println(s + "\n" + language);
            }
        }
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.printf(vb.sliceString("abcdefghijklm", 0, 3));
        vb.breakVigenere();
    }
    
}
