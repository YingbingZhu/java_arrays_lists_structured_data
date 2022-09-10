package week1;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    public int[] countLetters(String s) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (char ch : s.toCharArray() ) {
            ch = Character.toLowerCase(ch);
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[max]) {
                max = i;
            }
        }
        return max;
    }

    /**
     * a new String that is every other character from message starting with the start position
     * @param message
     * @param start
     * @return
     */
    public String halfOfString(String message, int start) {
        String output = "";
        for (int i = start; i < message.length(); i+=2) {
            output += message.charAt(i);
        }
        return output;
    }

    private String breakCaesarCipher(String input) {
        int[] freq = countLetters(input);
        // e is 4th most frequent, which has index of 4
        int dkey =  maxIndex(freq) - 4;
        if (dkey < 0) dkey = 26 - (4 - maxIndex(freq));
        CaesarCipherOne cco = new CaesarCipherOne(dkey);
        String decrypted = cco.decrypt(input);
        return decrypted;
    }

    void simpleTests() {
        String frs = new FileResource("week1/data/mysteryTwoKeysQuiz.txt").asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 13);
        String encrypted = cct.encrypt(frs);
        System.out.println(encrypted);

        String decrypted = cct.decrypt(encrypted);
        System.out.println(decrypted);

        String decrypted1 = breakCaesarCipher(frs);
        System.out.println(decrypted1);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
        tcct.simpleTests();
    }
}
