package week1;

import edu.duke.FileResource;

public class CaesarBreaker {

    CaesarCipher cc = new CaesarCipher();

    public String decrypt(String encrypted) {
        int[] freq = countLetters(encrypted);
        // e is 4th most frequent, which has index of 4
        int dkey =  maxIndex(freq) - 4;
        if (dkey < 0) dkey = 26 - (4 - maxIndex(freq));
        String message = cc.modifiedEncrypt(encrypted, 26 - dkey);
        return message;
    }

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

    /**
     * calculate the index of the largest letter frequency
     * @param s a String that was encrypted with the two key
     * @return
     */
    public int getKey(String s) {
        int[] freq = countLetters(s);
        return maxIndex(freq);
    }

    public String decryptTwoKeys(String encrypted) {
        // Calculate a String of every other character starting with the first character of the encrypted String by calling halfOfString.
        String s1 =  halfOfString(encrypted, 0);

        // Calculate a String of every other character starting with the second character of the encrypted String.
        String s2 =  halfOfString(encrypted, 1);

        // Then calculate the key used to encrypt each half String.
        int maxDex1 = getKey(s1);
        int maxDex2 = getKey(s2);

        int dkey1 = maxDex1 - 4;
        if(maxDex1 < 4)
            dkey1 = 26-(4-maxDex1);

        int dkey2 = maxDex2 - 4;
        if(maxDex2 < 4)
            dkey2 = 26-(4-maxDex2);

        System.out.println("key1: " + dkey1 + "; key2: " + dkey2);

        return cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);

    }

     void testDecrypt() {
         // FileResource resource = new FileResource("week1/data/wordsLotsOfEs.txt");
         String s = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
         String decrypted = decryptTwoKeys(s);
         System.out.println(decrypted);
     }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
//        cb.testDecrypt();
//        String s = "Top ncmy qkff vi vguv vbg ycpx";
//        CaesarCipher cc = new CaesarCipher();
//        System.out.println(cc.encryptTwoKeys(s, 24, 6));

        // q10
        FileResource resource = new FileResource("week1/data/mysteryTwoKeysQuiz.txt");
        cb.decryptTwoKeys(resource.asString());
        System.out.println(cb.decryptTwoKeys(resource.asString()));
    }
}
