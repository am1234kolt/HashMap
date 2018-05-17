import org.junit.*;
import org.junit.rules.*;

import java.util.*;
import org.junit.Before;
import static org.junit.Assert.*;

public class OwnMapTest extends OwnInterfaceTest {
    @Before
    public void prepare() {
        map = new OwnMap<>();
    }

}
