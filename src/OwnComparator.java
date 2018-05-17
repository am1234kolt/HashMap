import java.util.Comparator;
import java.util.Date;


/**
 * Created by koltsova on 14/10/17.
 */
public class OwnComparator {
    public static <T> Comparator<OwnElement<T>> byValue(Comparator<? super T> valueComparator) {
        return (a, b) -> valueComparator.compare(a.getValue(), b.getValue());
    }
    public static Comparator<OwnElement> byKey = Comparator.comparingInt(OwnElement::getKey);

}


