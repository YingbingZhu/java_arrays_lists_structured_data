package week1;

import org.junit.Assert;
import org.junit.Test;


public class WordPlayTest {

    WordPlay wp = new WordPlay();

    @Test
    public void testIsVowel() {
        Character testCh1 = 'a';
        Character testCh2 = 'E';
        Character testCh3 = 'F';
        // initialize a class
        // test isVowel
        Assert.assertTrue(wp.isVowel(testCh1));
        Assert.assertTrue(wp.isVowel(testCh2));
        Assert.assertFalse(wp.isVowel(testCh3));
    }

    @Test
    public void testReplaceVowels() {
        String testS1 = "Hello World";
        String testS2 = "HEllo World";
        // test isVowel
        Assert.assertEquals(wp.replaceVowels(testS1, '*'), "H*ll* W*rld");
        Assert.assertEquals(wp.replaceVowels(testS2, '*'), "H*ll* W*rld");
    }

    @Test
    public void testEmphasize() {
        String testS1 = "dna ctgaaactga";
        String testS2 = "Mary Bella Abracadabra";
        // test isVowel
        Assert.assertEquals(wp.emphasize(testS1, 'a'), "dn* ctg+*+ctg+");
        Assert.assertEquals(wp.emphasize(testS2, 'a'), "M+ry Bell+ +br*c*d*br+");
    }

}
