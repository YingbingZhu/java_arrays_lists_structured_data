package week1;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

public class CaesarCipher {

    private String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * return encrypted string using Caesar Cipher, only uppercase
     * @param input
     * @param key
     * @return
     */
    public String encrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String shifted = alphabets.substring(key) + alphabets.substring(0, key);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            int index = alphabets.indexOf(currChar);
            if (index != -1) {
                char newChar = shifted.charAt(index);
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }

    /**
     * return encrypted string using Caesar Cipher, both uppercase and lowercase
     * @param input
     * @param key
     * @return
     */
    public String modifiedEncrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);
//        String shifted = alphabets.substring(key) + alphabets.substring(0, key);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            int index = alphabets.indexOf(Character.toUpperCase(currChar));
            if (index != -1) {
                char newChar = encryptNewChar(index, key);
//                char newChar = shifted.charAt(index);
                if (Character.isLowerCase(currChar)) {
                    sb.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    sb.setCharAt(i, newChar);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 
     * @param input
     * @param key1   encrypt every other character with the Caesar Cipher algorithm
     * @param key2   encrypt every other character, starting with the second character.
     * @return     encrypted
     */
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder res = new StringBuilder(input);
        int key;
        for (int i = 0; i < input.length(); i++) {
            char currChar = res.charAt(i);
            if (i % 2 == 0) {
                key = key1;
            } else {
                key = key2;
            }
            int index = alphabets.indexOf(Character.toUpperCase(currChar));
            if (index != -1) {
                char newChar = encryptNewChar(index, key);
                if (Character.isLowerCase(currChar)) {
                    newChar =  Character.toLowerCase(newChar);
                }
                res.setCharAt(i, newChar);
            }

        }
        return res.toString();
    }

    public char encryptNewChar(int index, int key) {
        return alphabets.charAt(Math.floorMod(index + key, 26));
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        String s = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String s1 = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(cc.encryptTwoKeys(s, 14,24));
        System.out.println(cc.modifiedEncrypt(s, 15));
    }
}
