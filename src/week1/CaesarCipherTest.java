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

    @Test
    public void testEncryptNew() {
        String s1 = "First Legion";
        Assert.assertEquals(
                cc.modifiedEncrypt(s1, 23),
                "Cfopq Ibdflk"
        );
        Assert.assertEquals(
                cc.modifiedEncrypt(s1, 17),
                "Wzijk Cvxzfe"
        );
    }

    @Test
    public void testEncryptTwoKeys() {
        String s1 = "First Legion";
        Assert.assertEquals(
                cc.encryptTwoKeys(s1, 23, 17),
                "Czojq Ivdzle"
        );
    }


}