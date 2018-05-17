/**
 * Created by koltsova on 19/09/17.
 */
import java.util.Collection;
public interface OwnInterface<T> extends Iterable<OwnElement<T >> {

    int           add     (OwnElement<T> element);
    void          addAll  (Collection<? extends OwnElement<T>> c);
    OwnElement<T> get     (int key);
    OwnElement<T> remove  (int key);
    int           size    ();
    boolean       isEmpty ();
    boolean       contains(int key);
    boolean       contains(OwnElement<T> e);
    void          print   ();
}

