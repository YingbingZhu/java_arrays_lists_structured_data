package week1;

public class CaesarCipherTwo {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int key1, int key2) {
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        alphabet = alphabet;
        key1 = key1;
        key2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder res = new StringBuilder(input);
        int key;
        for (int i = 0; i < input.length(); i++) {
            char currChar = res.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if (index != -1) {
                char newChar;
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(index);
                } else {
                    newChar = shiftedAlphabet1.charAt(index);
                }
                res.setCharAt(i, newChar);
            }
        }
        return res.toString();
    }

    public String decrypt(String input){
        CaesarCipherTwo a = new CaesarCipherTwo(26 - key1, 26 - key2);
        return a.encrypt(input);
    }
    
}
