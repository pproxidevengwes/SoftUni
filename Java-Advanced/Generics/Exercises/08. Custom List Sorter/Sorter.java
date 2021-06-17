public class Sorter {
    public static <E extends Comparable<E>> void sort(CustomList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            E current = list.get(i);
            for (int j = 1 + i; j < list.size(); j++) {
                if (current.compareTo(list.get(j)) > 0) {
                    list.swap(i, j);
                }
            }
        }
    }
}
