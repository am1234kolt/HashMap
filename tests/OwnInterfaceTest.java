import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by koltsova on 11/02/2018.
 */
public abstract class OwnInterfaceTest {
    @Rule
    public final ExpectedException exp = ExpectedException.none();

    public OwnInterface<String> map;

    final OwnElement<String> e1 = new OwnElement<>(1337, "elite");
    final OwnElement<String> e2 = new OwnElement<>(  42, "answer");
    final OwnElement<String> e3 = new OwnElement<>(   2, "SOOOO");
    final OwnElement<String> e4 = new OwnElement<>(   3, "MAKE_SOME_OIL");
    final OwnElement<String> e5 = new OwnElement<>(  18, "MAKE INSTALL");
    final OwnElement<String> e6 = new OwnElement<>(  19, "OWNTESTVALUE");

    final OwnElement<String> eNull    = new OwnElement<>(1);


    private final int notUsedKey = -1;

    @Test
    public void addTest() {
        assertTrue(map.isEmpty());
        assertEquals(e1.getKey(), map.add(e1));
        assertEquals(e2.getKey(), map.add(e2));
        assertEquals(e3.getKey(), map.add(e3));
        assertEquals(e4.getKey(), map.add(e4));
        assertEquals(e5.getKey(), map.add(e5));
        assertEquals(e6.getKey(), map.add(e6));

        assertEquals(eNull.getKey(), map.add(eNull));
        assertEquals(7, map.size());

        ;
    }

    @Test
    public void removeTest() {
        assertTrue(map.isEmpty());
        assertNull(map.remove(e1.getKey()));
        map.add(e1);
        map.add(e2);
        map.add(e3);
        map.add(e4);
        map.add(e5);
        map.add(e6);
        assertEquals(6, map.size  ());
        assertEquals(e3, map.remove(e3.getKey()));
        assertEquals(5, map.size  ());
        assertNull(map.remove(notUsedKey));
    }

    @Test
    public void getTest() {
        assertTrue(map.isEmpty());
        assertNull(map.get(1337));
        map.add(e1);
        map.add(e2);
        map.add(e3);
        map.add(e4);
        map.add(e5);
        map.add(e6);
        assertEquals(6, map.size());
        assertEquals(e2, map.get(e2.getKey()));
        assertNull(map.get(notUsedKey));
    }

    @Test
    public void printTest() { //manual test, not automatic
        assertTrue(map.isEmpty());
        map.add(e1);
        map.add(e2);
        map.add(e3);
        map.add(e4);
        map.add(e5);
        map.add(e6);
        map.print();
    }

    @Test
    public void iteratorTest() {
        Set<OwnElement<String>> elementSet = new HashSet<>();
        assertTrue(map.isEmpty());
        map.add(e1); map.add(e2); elementSet.add(e1); elementSet.add(e2);
        map.add(e3); map.add(e4); elementSet.add(e3); elementSet.add(e4);
        map.add(e5); map.add(e6); elementSet.add(e5); elementSet.add(e6);
        assertEquals(6, map.size());

        for (OwnElement<String> e : map) {
            assertTrue(elementSet.contains(e));
            elementSet.remove(e);
        }

        assertTrue(elementSet.isEmpty());
    }
}
