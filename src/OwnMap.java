/**
 * Created by koltsova on 19/09/17.
 */

import static java.util.stream.Collectors.*;
import java.util.*;

public class OwnMap<T> implements OwnInterface<T> {
    private final Vector<OwnElement<T>> storage;


    public OwnMap() {
        this.storage = new Vector<>();
    }

    public OwnMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException
                    ("Illegal initial capacity: " + initialCapacity);
        }

        this.storage = new Vector<>(initialCapacity);
    }

    public int add(OwnElement<T> element) {
        int key = element.getKey();
        for (OwnElement<T> e : storage) {
            if (e.getKey() == key) {
                throw new OwnDublicateException();
            }
        }

        storage.add(element);
        return key;
    }


    public void addAll(Collection<? extends OwnElement<T>> elements) {
        for (OwnElement<T> e : elements) {
            add(e);
        }
    }

    public OwnElement<T> get(int key) {
        for (OwnElement<T> e : storage) {
            if (e.getKey() == key) {
                return e;
            }
        }

        return null;
    }

    public OwnElement<T> remove(int key) {
        for (OwnElement e : storage) {
            if (e.getKey() == key) {
                storage.remove(e);
                return e;
            }
        }

        return null;
    }

    public int size() {
        return storage.size();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    public boolean contains(int key) {
        return get(key) != null;
    }

    public boolean contains(OwnElement<T> e) {
        return contains(e.getKey());
    }

    public void print() {
        for (OwnElement<T> e : storage) {
            System.out.print(e.toString());
            System.out.print(", ");
        }

        System.out.println();
    }

    public Iterator<OwnElement<T>> iterator() {
        return storage.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnMap<?> ownMap = (OwnMap<?>) o;

        return storage.equals(ownMap.storage);
    }

    public void sort(Comparator<? super OwnElement<T>> cmp) {
        quickSort(0, storage.size() - 1, cmp);
    }

    private void quickSort(int begin, int end, Comparator<? super OwnElement<T>> comp) {
        if (begin < end) {
            int l = begin, r = end;
            OwnElement<T> pivot = storage.get((l + r) / 2);
            do {
                while (comp.compare(storage.get(l), pivot) < 0) l++;
                while (comp.compare(storage.get(r), pivot) > 0) r--;
                if (l <= r) {
                    OwnElement<T> tmp = storage.get(l);
                    storage.set(l, storage.get(r));
                    storage.set(r, tmp);
                    l++;
                    r--;
                }
            } while (l <= r);
            quickSort(begin, r, comp);
            quickSort(l, end, comp);
        }
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }

    private class OwnMapIterator implements Iterator<OwnElement<T>> {

        private Iterator<OwnElement<T>> it;

        OwnMapIterator() {
            it = OwnMap.this.storage.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public OwnElement<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return it.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}