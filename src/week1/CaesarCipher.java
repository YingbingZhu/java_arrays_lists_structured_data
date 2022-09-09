package week1;

public class CaesarCipher {
    /**
     * retrun encrypted string using Caesar Cipher, only uppercase
     * @param input
     * @param key
     * @return
     */
    public String encrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
     * retrun encrypted string using Caesar Cipher, only uppercase
     * @param input
     * @param key
     * @return
     */
    public String modifiedEncrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
}
