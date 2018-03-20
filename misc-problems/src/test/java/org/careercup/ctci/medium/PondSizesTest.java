package org.careercup.ctci.medium;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PatternMatchingTest extends TestCase {

    private PatternMatching matcher;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.matcher = new PatternMatching();
    }

    @Test
    public void testMatchHappyPath(){
        String value = "catcatgocatgo";

        assertTrue(matcher.match("a", value));
        assertTrue(matcher.match("b", value));
        assertTrue(matcher.match("ab", value));
        assertTrue(matcher.match("aabab", value));
    }


}