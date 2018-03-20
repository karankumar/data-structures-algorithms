package org.careercup.ctci.medium;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PondSizesTest extends TestCase {

    private PondSizes classUnderTest;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.classUnderTest = new PondSizes();
    }

    @Test
    public void testMatchHappyPath(){
        int[][] land = {{0, 1, 1, 0}, {0, 2, 0, 1}, {1, 3, 0, 2}, {0, 1, 0, 4}};
        List<Integer> result = classUnderTest.computePondSizes(land);

        assertEquals(3, result.size());
        assertTrue(result.contains(2));
        assertTrue(result.contains(1));
        assertTrue(result.contains(4));
    }


}