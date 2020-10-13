import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Lab8Test
{  

    final String[] NAMES_SOME_EMPTY = { "", "Bo", "Joe", "Christy", "" };
    final String[] NAMES_SOME_EMPTY2 = { "", "", "Moses", null, "Jo" };

    final String[] NAMES = { "Joe", "Bo", "Christy" };
    final String[] NAMES2 = { "Moses", "Jo" };

    final Gopher[] GOPHERS = { new Gopher("brown", 2), new Gopher("grey", 4), new Gopher("purple", 6)};
    final Gopher[] GOPHERS2 = { new Gopher("brown", 4), new Gopher("grey", 12), new Gopher("purple", 8)};

    @Test
    public void testFindFirstEmpty()
    {
        assertEquals("Bo", Lab8.findFirstNonEmpty(Arrays.asList(NAMES_SOME_EMPTY)));
        assertEquals("Moses", Lab8.findFirstNonEmpty(Arrays.asList(NAMES_SOME_EMPTY2)));
    }

    @Test
    public void testCountNullOrEmpty() {
        assertEquals(2, Lab8.countNullOrEmpty(Arrays.asList(NAMES_SOME_EMPTY)));
        assertEquals(3, Lab8.countNullOrEmpty(Arrays.asList(NAMES_SOME_EMPTY2)));
    }

    @Test
    public void testFindShortest() {
        assertEquals("Bo", Lab8.findShortest(NAMES));
        assertEquals("Jo", Lab8.findShortest(NAMES2));
    }

    @Test
    public void testRemoveNullOrEmpty() {
        var names = new ArrayList<String>(Arrays.asList(NAMES_SOME_EMPTY));
        Lab8.removeNullOrEmpty(names);
        assertArrayEquals(new Object[] {"Bo", "Joe", "Christy"}, names.toArray());
        
    }

    @Test
    public void testComputeAverageWeight() {
        assertEquals(4, Lab8.computeAverageWeight(Arrays.asList(GOPHERS)), 0);
        assertEquals(4, Lab8.computeAverageWeight(Arrays.asList(GOPHERS2)), 8);
    }
}