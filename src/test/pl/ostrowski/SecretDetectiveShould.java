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
    };

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
    public void test_is_before() {
        boolean result = false;
        result = secretDetective.isBefore('s', 'u', triplets);
        assertEquals(true, result);
        result = secretDetective.isBefore('i', 's', triplets);
        assertEquals(true, result);
        result = secretDetective.isBefore('u', 'p', triplets);
        assertEquals(true, result);
    }

    @Test
    public void test_first_in_order() {
        char result = 0;
        char[] letters = {'u', 's', 'i'};
        result = secretDetective.getFirstLetterInOrder(letters,triplets);
        assertEquals(result, 'i');

        char[] letters2 = {'h', 'w', 's'};
        result = secretDetective.getFirstLetterInOrder(letters2,triplets);
        assertEquals(result, 'w');
    }


    @Test
    public void test_get_candidates() {
        char[] result = secretDetective.getCandidates('t',triplets);
        assertEquals(3,result.length);

        result = secretDetective.getCandidates('w',triplets);
        assertEquals(1,result.length);
    }

    @Test
    public void test_word_length() {
        int l = secretDetective.resolveWordLength(triplets);
        assertEquals(8, l);
    }

    @Test
    public void test_recover_correct_string() {
        String result = secretDetective.recoverSecret(triplets);
        assertEquals("whatisup", result);
    }

}
