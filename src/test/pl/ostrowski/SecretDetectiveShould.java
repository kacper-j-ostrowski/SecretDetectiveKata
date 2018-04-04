package test.pl.ostrowski;

import main.pl.ostrowski.SecretDetective;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecretDetectiveShould {

    private SecretDetective secretDetective;
    private char[][] triplets = {
            {'t','u','p'},
            {'w','h','i'},
            {'t','s','u'},
            {'a','t','s'},
            {'h','a','p'},
            {'t','i','s'},
            {'w','h','s'}
    };;

    @Before
    public void setUp() {
        secretDetective = new SecretDetective();
    }


    @Test
    public void test_recover_firstLetter() {
        String result = secretDetective.recoverSecret(triplets);
        result = result == null ? new String() : result;
        assertEquals("w", result.substring(0,1));
    }


    @Test
    public void test_recover_correct_string() {
        String result = secretDetective.recoverSecret(triplets);
        assertEquals("whatisup", result);
    }

}
