import java.util.Comparator;

/**
 * Created by koltsova on 19/09/17.
 */


public class OwnElement<T>{
    public int key;
    public T   val;

    public OwnElement(int key, T val) {
        this.key = key;
        this.val = val;
    }

    public OwnElement(int key) {
        this.key = key;
        this.val = null;
    }

    public int getKey  () { return key; }
    public T   getValue() { return val; }

    public T   setValue(T value) {
        this.val = value;
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof OwnElement)) {
            return false;
        }

        OwnElement<T> e = (OwnElement) o;
        return key == e.key && val == e.val;
    }

    @Override
    public int hashCode() {
        int h = 31;
        h = 31*h + key;
        h = 31*h + (val == null ? 0 : val.hashCode());
        return h;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("( ")
                .append(key)
                .append(" => ")
                .append(val)
                .append(" )")
                .toString();
    }

}


