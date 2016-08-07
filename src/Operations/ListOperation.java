//package Operations;
////
//import Functions.ConsumerIndexed;
//import Structures.ArrayList;
//import Structures.Collection;
//import Structures.List;
//import utils.Pair;
//import utils.Sorting;
//
//import java.util.*;
//import java.util.function.*;
//
///**
// * Created by mikedev on 13/07/16.
// */
//public interface ListOperation<T> extends Operation<T> {
//
//    /*Collection<T> getCollection();
//
//    boolean isParallel();
//
//    Iterator<T> iterator();
//
//    <S> List<S> makeCollection();
//
//    void toggleParallel();
//
//    @Override
//    default boolean all(Predicate<T> predicate) {
//        Iterator<T> it = iterator();
//        while (it.hasNext()) {
//            if (!predicate.test(it.next())) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    default boolean any(Predicate<T> predicate) {
//        Iterator<T> it = iterator();
//        while (it.hasNext()) {
//            if (predicate.test(it.next())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /*@Override
//    default void forEach(Modifier<T> alterator)
//    {
//        if(!isParallel())
//        {
//            iterator().forEachRemaining(alterator::alter);
//        }
//        else
//        {
//            int procs = Runtime.getRuntime().availableProcessors();
//            Spliterator<T> sIt = getCollection().spliterator();
//            Thread[] threads = new Thread[procs];
//            for (Thread thread : threads)
//            {
//                thread = new Thread(() -> sIt.trySplit().forEachRemaining(alterator::alter));
//            }
//        }
//
//    }
//
//    @Override
//    default int count(Predicate<T> fCounter) {
//        final int[] c = {0};
//        forEach(e ->
//        {
//            c[0] = fCounter.test(e) ? c[0] + 1 : c[0];
//        });
//        return c[0];
//    }
//
//    @Override
//    default Collection<T> distinct() {
//        List<T> distinctList = makeCollection();
//        distinctList.addAll(getCollection());
//        for (int i = 0; i < distinctList.size(); i++) {
//            for (int j = i + 1; j < distinctList.size(); j++) {
//                if (i != j && distinctList.get(i).equals(distinctList.get(j))) {
//                    distinctList.remove(j);
//                    j--;
//                }
//            }
//        }
//        return distinctList;
//    }
//
//    @Override
//    default Collection<T> filter(Predicate<T> predicate) {
//        List<T> newCollection = makeCollection();
//        forEach(e ->
//        {
//            if (predicate.test(e)) {
//                newCollection.add(e);
//            }
//        });
//        return newCollection;
//    }
//
//    @Override
//    default Collection<T> filterIndexed(BiPredicate<Integer, T> predicate) {
//        List<T> newCollection = makeCollection();
//        forEachIndexed((i, e) ->
//        {
//            if (predicate.test(i, e)) {
//                newCollection.add(e);
//            }
//        });
//        return newCollection;
//    }
//
//    @Override
//    default T first() {
//        return iterator().next();
//    }
//
//    @Override
//    default T first(T defaultValue) {
//        T result = firstOrNull();
//        return result != null ? result : defaultValue;
//    }
//
//    @Override
//    default T first(Predicate<T> predicate, T defaultValue) {
//        T result = firstOrNull(predicate);
//        return result != null ? result : defaultValue;
//    }
//
//    @Override
//    default T firstOrNull() {
//        return getCollection().size() > 0 ? iterator().next() : null;
//    }
//
//    @Override
//    default T firstOrNull(Predicate<T> predicate) {
//        Iterator<T> it = iterator();
//        while (it.hasNext()) {
//            T el = it.next();
//            if (predicate.test(el)) {
//                return el;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    default void forEachIndexed(ConsumerIndexed<? super T> mI) {
//        Iterator<T> curr = getCollection().iterator();
//        int n = getCollection().size();
//        int i = 0;
//        while (i < n) {
//            if (!curr.hasNext() && i < n - 1) {
//                throw new IndexOutOfBoundsException("iterator not has next and index is less than lastOrNull element");
//            }
//            mI.alter(i, curr.next());
//            i++;
//        }
//
//    }
//
//    @Override
//    default void forEachReverse(Consumer<? super T> mod) {
//        reverse().forEach(mod);
//    }
//
//    @Override
//    default <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct) {
//        Map<E, Collection<T>> hashMap = new HashMap<E, Collection<T>>();
//        for (T e : getCollection()) {
//            E key = thisFuct.apply(e);
//            hashMap.putIfAbsent(key, new ArrayList<T>());
//            hashMap.get(key).add(e);
//        }
//        return hashMap;
//
//    }
//
//    @Override
//    default T last(T defaultValue) {
//        T lastEl = lastOrNull();
//        return lastEl != null ? lastEl : defaultValue;
//    }
//
//    @Override
//    default T last() {
//        T lastEl = lastOrNull();
//        Objects.requireNonNull(lastEl, "Empty list or last element null");
//        return lastEl;
//    }
//
//    @Override
//    default T last(Predicate<T> predicate, T defaultValue) {
//        T lastEl = lastOrNull(predicate);
//        return lastEl != null ? lastEl : defaultValue;
//    }
//
//    @Override
//    default T lastOrNull() {
//        int lastIndex = getCollection().size() - 1;
//        if (lastIndex == -1) {
//            throw new IndexOutOfBoundsException("Empty collection");
//        }
//        if (getCollection() instanceof List) {
//            return ((List<T>) getCollection()).get(lastIndex);
//        } else {
//            Iterator<T> it = getCollection().iterator();
//            T last = null;
//            while (it.hasNext()) {
//                last = it.next();
//            }
//            return last;
//        }
//    }
//
//    @Override
//    default T lastOrNull(Predicate<T> predicate) {
//        Iterator<T> it = iterator();
//        T last = null;
//        while (it.hasNext()) {
//            T el = it.next();
//            if (predicate.test(el)) {
//                last = el;
//            }
//        }
//        return last;
//    }
//
//    @Override
//    default <R> Collection<R> map(Function<T, R> mapper) {
//        List<R> newCollection = makeCollection();
//        forEach(e ->
//        {
//            newCollection.add(mapper.apply(e));
//        });
//        return newCollection;
//    }
//
//    @Override
//    default <R> Collection<R> mapIndexed(BiFunction<Integer, T, R> mapper) {
//        List<R> newCollection = makeCollection();
//        forEachIndexed((i, e) ->
//        {
//            newCollection.add(mapper.apply(i, e));
//        });
//        return newCollection;
//    }
//
//    @Override
//    default T maxBy(Comparator<T> comparator) {
//        final T[] max = (T[]) new Object[]{null};
//        forEach(e ->
//        {
//            if (max[0] == null) {
//                max[0] = e;
//            } else {
//                int cResult = comparator.compare(max[0], e);
//                if (cResult == -1) {
//                    max[0] = e;
//                }
//            }
//        });
//        return max[0];
//    }
//
//    /**
//     * Return the minimum element by the comparator in input.
//     * If two elements are equals doesn't change the min.
//     *
//     * @param comparator
//     * @return minimum value
//     */
//    @Override
//    default T minBy(Comparator<T> comparator) {
//        final T[] min = (T[]) new Object[]{null};
//        forEach(e ->
//        {
//            if (min[0] == null) {
//                min[0] = e;
//            } else {
//                int cResult = comparator.compare(min[0], e);
//                if (cResult == 1) {
//                    min[0] = e;
//                }
//            }
//        });
//        return min[0];
//    }
//
//    @Override
//    default Collection<T> orderBy(Comparator<T> comparator) {
//        List<T> orderedList = makeCollection();
//        // Esegue un mergeSort da java
//        if (getCollection() instanceof List) {
//            orderedList.addAll(getCollection());
//            orderedList.sort(comparator);
//        }
//        //bubble sort
//        else {
//            Sorting.bubbleSort(getCollection(), orderedList, comparator);
//        }
//        return orderedList;
//    }
//
//    @Override
//    default Collection<T> orderDecrescentBy(Comparator<T> comparator) {
//        List<T> crescentList = (List<T>) (orderBy(comparator));
//        int n = crescentList.size();
//        for (int i = 0, reverse = n - i - 1; i < n / 2; i++) {
//            T tmp = crescentList.get(i);
//            crescentList.set(i, crescentList.get(reverse));
//            crescentList.set(reverse, tmp);
//        }
//        return crescentList;
//    }
//
//    @Override
//    default T reduce(BinaryOperator<T> accumulator) {
//        final T[] result = (T[]) new Object[]{null};
//        final boolean[] first = {true};
//        forEach(e ->
//        {
//            result[0] = first[0] ? e : accumulator.apply(result[0], e);
//            first[0] = false;
//        });
//        return result[0];
//    }
//
//    @Override
//    default T reduceReverse(BinaryOperator<T> accumulator) {
//        final T[] result = (T[]) new Object[]{null};
//        final boolean[] first = {true};
//        forEach(e ->
//        {
//            result[0] = first[0] ? e : accumulator.apply(result[0], e);
//            first[0] = false;
//        });
//        return result[0];
//    }
//
//    @Override
//    default Collection<T> reverse() {
//        int n = getCollection().size();
//        List<T> reverseList = makeCollection();
//        for (int i = 0, reverse = n - i - 1; i < n / 2; i++) {
//            T tmp = reverseList.get(i);
//            reverseList.set(i, reverseList.get(reverse));
//            reverseList.set(reverse, tmp);
//        }
//        return reverseList;
//    }
//
//    @Override
//    default Collection<T> take(int n) {
//        Iterator<T> it = iterator();
//        List<T> result = makeCollection();
//        int i = 0;
//        while (it.hasNext() && i < n) {
//            result.add(it.next());
//        }
//        return result;
//    }
//
//    @Override
//    default Collection<T> takeLast(int n) {
//        return ((Collection<T>) reverse()).take(n);
//    }
//
//    /**
//     * Zip the current list with another making a collection of pair of the two list
//     * Note that if the dimension of input is less than of the invoker the size of the new collection
//     * is equals to the size of invoker so the remaining elements of input are ignored
//     *
//     * @param other : list to zip
//     * @param <X>   Type of elements of input list
//     * @return A collection containing all two the elements
//     */
//    @Override
//    default <X> Collection<Pair<T, X>> zipWith(Collection<X> other) {
//        if (other.size() > getCollection().size()) {
//            throw new InputMismatchException("Size of input collection must be less or equal " +
//                    "than this collection");
//        }
//        Iterator<X> otIt = other.iterator();
//        List<Pair<T, X>> pairCollection = makeCollection();
//        forEach((x) -> pairCollection.add(new Pair<T, X>(x, otIt.next())));
//        return pairCollection;
//    }*/
//}
