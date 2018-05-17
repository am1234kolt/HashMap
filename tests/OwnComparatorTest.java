import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by koltsova on 11/02/2018.
 */
public class OwnComparatorTest {
    @Test
    public void byKeyTest() {
        Comparator<OwnElement> cmp = OwnComparator.byKey;

        OwnElement<String> a1 = new OwnElement<>( 0, null);
        OwnElement<String> a2 = new OwnElement<>( 5, null);
        OwnElement<String> a3 = new OwnElement<>(10, null);

        OwnElement<String> a4 = new OwnElement<>(  -5, null);
        OwnElement<String> a5 = new OwnElement<>( -10, null);
        OwnElement<String> a6 = new OwnElement<>(-100, null);

        assertEquals(0, cmp.compare(a1, a1));
        assertEquals(0, cmp.compare(a2, a2));
        assertEquals(0, cmp.compare(a3, a3));
        assertEquals(0, cmp.compare(a4, a4));
        assertEquals(0, cmp.compare(a5, a5));
        assertEquals(0, cmp.compare(a6, a6));

        assertTrue(cmp.compare(a1, a6) > 0);
        assertTrue(cmp.compare(a2, a1) > 0);
        assertTrue(cmp.compare(a3, a1) > 0);
        assertTrue(cmp.compare(a3, a2) > 0);
        assertTrue(cmp.compare(a5, a6) > 0);

        assertTrue(cmp.compare(a6, a1) < 0);
        assertTrue(cmp.compare(a1, a2) < 0);
        assertTrue(cmp.compare(a1, a3) < 0);
        assertTrue(cmp.compare(a2, a3) < 0);
        assertTrue(cmp.compare(a6, a4) < 0);
        assertTrue(cmp.compare(a5, a4) < 0);

        assertTrue(
                cmp.compare(a2, a1) > 0 &&
                        cmp.compare(a3, a2) > 0 &&
                        cmp.compare(a3, a1) > 0
        );
    }
}