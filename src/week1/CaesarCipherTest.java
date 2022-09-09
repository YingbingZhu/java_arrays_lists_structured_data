package week1;

import org.junit.Assert;
import org.junit.Test;

public class CaesarCipherTest {
    CaesarCipher cc = new CaesarCipher();

    @Test
    public void testEncrypt() {
        String s1 = "FIRST LEGION ATTACK EAST FLANK!";
        // initialize a class
        // test isVowel
        Assert.assertEquals(
                cc.encrypt(s1, 23),
                "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!"
        );
    }

}