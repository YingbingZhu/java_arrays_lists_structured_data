package week1;

public class CaesarCipherOne {

    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipherOne(int key) {
        mainKey = key;
        alphabet = alphabet;
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    /**
     * return encrypted string using Caesar Cipher, both uppercase and lowercase
     * @param input
     * @return
     */
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                if (Character.isLowerCase(currChar)) {
                    sb.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    sb.setCharAt(i, newChar);
                }
            }
        }
        return sb.toString();
    }

    public String decrypt(String input) {
        CaesarCipherOne cc = new CaesarCipherOne(26 - mainKey);
        return cc.encrypt(input);
    }

}

