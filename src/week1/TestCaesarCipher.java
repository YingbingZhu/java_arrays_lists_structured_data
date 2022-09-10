package week1;

import edu.duke.*;

public class TestCaesarCipher {
    private int[] countLetters(String s) {
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

    private int maxIndex(int[] values) {
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[max]) {
                max = i;
            }
        }
        return max;
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
        String frs = new FileResource("week1/data/common.txt").asString();
        CaesarCipherOne cco = new CaesarCipherOne(18);
        String encrypted = cco.encrypt(frs);
        System.out.println(encrypted);

        String decrypted = cco.decrypt(encrypted);
        System.out.println(decrypted);

        String decrypted1 = breakCaesarCipher(frs);
        System.out.println(decrypted1);
    }

    public static void main(String[] args) {
        TestCaesarCipher tcc = new TestCaesarCipher();
        tcc.simpleTests();
    }
}
