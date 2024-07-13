import java.util.Iterator;
import java.util.List;

public class Lake implements Iterable<Integer> {
    private List<Integer> lake;

    public Lake(List<Integer> lake) {
        this.lake = lake;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;
            private int lastEvenIndex = lake.size() % 2 == 0 ?
                    lake.size() - 2 : lake.size() - 1;

            @Override
            public boolean hasNext() {
                return index < lake.size();
            }

            @Override
            public Integer next() {
                if (index == lastEvenIndex) {
                    index = 1;
                    return lake.get(lastEvenIndex);
                }
                int element = (int) lake.get(index);
                index += 2;
                return element;
            }
        };
    }
}
