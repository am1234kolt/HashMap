import java.util.*;

public class OwnHashMap<T extends Comparable<T>> implements OwnInterface<T> {
    private static final int   MAXIMUM_CAPACITY = 1 << 30;
    private static final float LOAD_FACTOR      = 0.75f;

    private final OwnMap<OwnMap<T>> buckets;
    private int size;
    private int threshold;
    private int capacity;

    public OwnHashMap() { this(16); }
    public OwnHashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException
                    ("Illegal initial capacity: " + initialCapacity);
        }

        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }

        buckets = new OwnMap<>(initialCapacity);
        capacity = initialCapacity;
        threshold = (int) (initialCapacity*LOAD_FACTOR);
    }

    public int add(OwnElement<T> element) {
        final int address = element.getKey() % capacity;
        OwnElement<OwnMap<T>> chainElement = buckets.get(address);
        if (chainElement == null) {
            chainElement = new OwnElement<>(address, new OwnMap<>());
            buckets.add(chainElement);
        }

        OwnMap<T> chain = chainElement.getValue();
        chain.add(element);

        if (++size > threshold) {
            resize();
        }

        return element.getKey();
    }

    public void addAll(Collection<? extends OwnElement<T>> elements) {
        for (OwnElement<T> element : elements) {
            add(element);
        }
    }

    public OwnElement<T> get(int key) {
        final int address = key % capacity;
        final OwnElement<OwnMap<T>> chainElement = buckets.get(address);
        if (chainElement == null) { return null; }
        final OwnMap<T> chain = chainElement.getValue();
        return chain.get(key);
    }

    public OwnElement<T> remove(int key) {
        final int address = key % capacity;
        final OwnElement<OwnMap<T>> chainElement = buckets.get(address);
        if (chainElement == null) { return null; }

        final OwnMap<T>     chain = chainElement.getValue();
        final OwnElement<T>     e = chain.remove(key);

        if (e != null) { size--; }

        return e;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int key) {
        return get(key) != null;
    }

    public boolean contains(OwnElement<T> e) {
        return contains(e.getKey());
    }

    public void print() {
        System.out.print("[");
        for (OwnElement<OwnMap<T>> map : buckets) {
            OwnMap<T> chain = map.getValue();
            for (OwnElement<T> e : chain) {
                System.out.print(e);
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    int firstPartKey(int key) {
        if (get(key) != null) {
            return key % capacity;
        } else {
            throw new NoSuchElementException();
        }
    }

    int secondPartKey(int key) {
        if (get(key) != null) {
            return key;
        } else {
            throw new NoSuchElementException();
        }
    }

    private void resize() {
        capacity *= 2;
        if (capacity >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
        } else {
            threshold = (int) (capacity*LOAD_FACTOR);
        }

        List<OwnElement<T>> buffer = new Vector<>(size);
        for (OwnElement<OwnMap<T>> map : buckets) {
            Iterator<OwnElement<T>> it = map.getValue().iterator();
            while (it.hasNext()) {
                buffer.add(it.next());
                it.remove();
            }
        }

        size = 0;
        buffer.forEach(this::add);
    }

    @Override
    public Iterator<OwnElement<T>> iterator() {
        return new OwnHashMapIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnHashMap<?> that = (OwnHashMap<?>) o;

        return buckets.equals(that.buckets);
    }
    @Override
    public int hashCode() {
        return buckets.hashCode();
    }

    private class OwnHashMapIterator implements Iterator<OwnElement<T>> {
        private final Iterator<OwnElement<OwnMap<T>>> it;
        private Iterator<OwnElement<T>> currentIt;

        OwnHashMapIterator() {
            it = buckets.iterator();
        }

        @Override
        public boolean hasNext() {
            if (currentIt == null || !currentIt.hasNext()) {
                if (it.hasNext()) {
                    currentIt = it.next().getValue().iterator();
                    return hasNext();
                } else {
                    return false;
                }
            }

            return true;
        }

        @Override
        public OwnElement<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return currentIt.next();
        }
    }
}
