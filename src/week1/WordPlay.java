package week1;

import java.util.HashSet;
import java.util.Set;

public class WordPlay {

    // initialize a vowel set
    Character[] vowels = {'a', 'e', 'o', 'u', 'i'};

    /**
     * check if a character is vowel
     * @param ch
     * @return
     */
    public boolean isVowel(Character ch) {
        for (char c: vowels) {
            if (c == Character.toLowerCase(ch)) return true;
        }
        return false;
    }

    /**
     *
     * @param phrase
     * @param ch
     * @return
     */
    public String replaceVowels(String phrase, Character ch) {
        String s = phrase;
        for (char c: phrase.toCharArray()) {
            if (isVowel(Character.toLowerCase(c)) || isVowel(Character.toUpperCase(c))) {
                s = s.replace(c, ch);
            }
        }
        return s;
    }

    /**
     * replaced by ‘*’ if it is in an odd number location in the string
     *  ‘+’ if it is in an even number location in the string
     * @param phrase
     * @param ch
     * @return
     */
    public String emphasize(String phrase, Character ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++) {
            if (Character.toLowerCase(sb.charAt(i)) == Character.toLowerCase(ch)) {
                if (i > 0 && (i + 1) % 2 == 0) {
                    sb.setCharAt(i, '+');
                } else {
                    sb.setCharAt(i, '*');
                }
            }
        }
        return sb.toString();
    }




}
