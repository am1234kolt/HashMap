import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by koltsova on 09/10/17.
 */
public class OwnSort<T> {

    Comparator<? super OwnElement<T>> comparator;
    public OwnSort(Comparator<? super OwnElement<T>>ownComparator){
        this.comparator=ownComparator;}

    public List<OwnElement<T>> bubbleSort(OwnInterface<T> toSort) {
        final List<OwnElement<T>> sorted = new ArrayList<>((toSort.size()));
        int numSorted = 0;
        int index;
        while (numSorted < sorted.size()) {
            for (index = 1; index < sorted.size() - numSorted; index++) {
                if (comparator.compare(sorted.get(index), sorted.get(index - 1)) < 0) {
                    Collections.swap(sorted, index - 1, index);
                }
            }
            numSorted++;
        }
        return sorted;

    }
}

