package week1;


import org.junit.Assert;
import org.junit.Test;

public class CaesarBreakerTest {

    CaesarBreaker cb = new CaesarBreaker();

    @Test
    public void halfOfString() {
        String s = "Qbkm Zgis";
        Assert.assertEquals(cb.halfOfString(s, 0), "Qk gs");
        Assert.assertEquals(cb.halfOfString(s, 1), "bmZi");
    }
}