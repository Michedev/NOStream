package structures;

import functions.ConsumerIndexed;
import operations.Operation;
import utils.Pair;
import utils.Sorting;

import java.util.*;
import java.util.function.*;

/**
 * Created by mikedev on 01/08/16.
 */
public interface Collection<T> extends java.util.Collection<T>, Operation<T> {

    <S> Collection<S> makeCollection();

    @Override
    default boolean all(Predicate<T> predicate) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (!predicate.test(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    default boolean any(Predicate<T> predicate) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                return true;
            }
        }
        return false;
    }

    /*@Override
    default void forEach(Modifier<T> alterator)
    {
        if(!isParallel())
        {
            iterator().forEachRemaining(alterator::alter);
        }
        else
        {
            int procs = Runtime.getRuntime().availableProcessors();
            Spliterator<T> sIt = this.spliterator();
            Thread[] threads = new Thread[procs];
            for (Thread thread : threads)
            {
                thread = new Thread(() -> sIt.trySplit().forEachRemaining(alterator::alter));
            }
        }

    }*/

    @Override
    default int count(Predicate<T> fCounter) {
        final int[] c = {0};
        forEach(e ->
        {
            c[0] = fCounter.test(e) ? c[0] + 1 : c[0];
        });
        return c[0];
    }

    @Override
    default Collection<T> distinct() {
        Collection<T> distinctList = makeCollection();
        distinctList.addAll(this);
        T[] array = (T[]) toArray();
        int n = distinctList.size();
        for (int i = 0; i < n; i++) {
            if(array[i] != null)
                for (int j = i + 1; j < n; j++) {
                    if (i != j && array[j]!=null && array[i].equals(array[j])) {
                        distinctList.remove(array[j]);
                        array[j] = null;
                    }
                }
        }
        return distinctList;
    }

    @Override
    default Collection<T> filter(Predicate<T> predicate) {
        Collection<T> newCollection = makeCollection();
        forEach(e ->
        {
            if (predicate.test(e)) {
                newCollection.add(e);
            }
        });
        return newCollection;
    }

    @Override
    default Collection<T> filterIndexed(BiPredicate<T, Integer> predicate) {
        Collection<T> newCollection = makeCollection();
        forEachIndexed((e, i) ->
        {
            if (predicate.test(e, i)) {
                newCollection.add(e);
            }
        });
        return newCollection;
    }

    @Override
    default Collection<T> filterNotNull() {
        return (Collection<T>) Operation.super.filterNotNull();
    }

    @Override
    default T first() {
        return iterator().next();
    }

    @Override
    default T first(T defaultValue) {
        T result = firstOrNull();
        return result != null ? result : defaultValue;
    }

    @Override
    default T first(Predicate<T> predicate, T defaultValue) {
        T result = firstOrNull(predicate);
        return result != null ? result : defaultValue;
    }

    @Override
    default T firstOrNull() {
        return this.size() > 0 ? iterator().next() : null;
    }

    @Override
    default T firstOrNull(Predicate<T> predicate) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T el = it.next();
            if (predicate.test(el)) {
                return el;
            }
        }
        return null;
    }

    @Override
    default void forEach(Consumer<? super T> consumer) {
        Iterator<T> it = iterator();
        while(it.hasNext()){
            consumer.accept(it.next());
        }
    }

    @Override
    default void forEachIndexed(ConsumerIndexed<? super T> mI) {
        Iterator<T> curr = this.iterator();
        int n = this.size();
        int i = 0;
        while (i < n) {
            if (!curr.hasNext() && i < n - 1) {
                throw new IndexOutOfBoundsException("iterator not has next and index is less than lastOrNull element");
            }
            mI.alter(curr.next(), i);
            i++;
        }

    }

    @Override
    default void forEachReverse(Consumer<? super T> mod) {
        reverse().forEach(mod);
    }

    @Override
    default <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct) {
        Map<E, Collection<T>> hashMap = new HashMap<E, Collection<T>>();
        for (T e : this) {
            E key = thisFuct.apply(e);
            hashMap.putIfAbsent(key, new ArrayList<T>());
            hashMap.get(key).add(e);
        }
        return hashMap;

    }

    @Override
    default Collection<T> intersection(java.util.Collection<T> collection) {
        Collection<T> intersectColl = makeCollection();
        java.util.Collection<T> firstCollection = size() > collection.size()? collection : this;
        java.util.Collection<T> secondCollection = firstCollection == this ? collection : this;
        for(T e : firstCollection)
        {
            if(secondCollection.contains(e)){
                intersectColl.add(e);
            }
        }
        return intersectColl;
    }

    @Override
    default T last(T defaultValue) {
        T lastEl = lastOrNull();
        return lastEl != null ? lastEl : defaultValue;
    }

    @Override
    default T last() {
        T lastEl = lastOrNull();
        Objects.requireNonNull(lastEl, "Empty list or last element null");
        return lastEl;
    }

    @Override
    default T last(Predicate<T> predicate, T defaultValue) {
        T lastEl = lastOrNull(predicate);
        return lastEl != null ? lastEl : defaultValue;
    }

    @Override
    default T lastOrNull() {
        int lastIndex = this.size() - 1;
        if (lastIndex == -1) {
            throw new IndexOutOfBoundsException("Empty collection");
        }
        if (this instanceof List) {
            return ((List<T>) this).get(lastIndex);
        } else {
            Iterator<T> it = this.iterator();
            T last = null;
            while (it.hasNext()) {
                last = it.next();
            }
            return last;
        }
    }

    @Override
    default T lastOrNull(Predicate<T> predicate) {
        Iterator<T> it = iterator();
        T last = null;
        while (it.hasNext()) {
            T el = it.next();
            if (predicate.test(el)) {
                last = el;
            }
        }
        return last;
    }

    @Override
    default <R> Collection<R> map(Function<T, R> mapper) {
        Collection<R> newCollection = makeCollection();
        forEach(e ->
        {
            newCollection.add(mapper.apply(e));
        });
        return newCollection;
    }

    @Override
    default <R> Collection<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
        List<R> newCollection = (List<R>) makeCollection();
        forEachIndexed((e, i) ->
        {
            newCollection.add(mapper.apply(e, i));
        });
        return newCollection;
    }

    @Override
    default T maxBy(Comparator<T> comparator) {
        final T[] max = (T[]) new Object[]{null};
        forEach(e ->
        {
            if (max[0] == null) {
                max[0] = e;
            } else {
                int cResult = comparator.compare(max[0], e);
                if (cResult == -1) {
                    max[0] = e;
                }
            }
        });
        return max[0];
    }


    @Override
    default T minBy(Comparator<T> comparator) {
        final T[] min = (T[]) new Object[]{null};
        forEach(e ->
        {
            if (min[0] == null) {
                min[0] = e;
            } else {
                int cResult = comparator.compare(min[0], e);
                if (cResult == 1) {
                    min[0] = e;
                }
            }
        });
        return min[0];
    }

    @Override
    default Collection<T> orderBy(Comparator<T> comparator) {
        Collection<T> orderedColl = makeCollection();
        // Esegue un mergeSort da java
        if (orderedColl instanceof List) {
            List<T> orderedList = (List<T>) orderedColl;
            orderedList.addAll(this);
            orderedList.sort(comparator);
        }
        //bubble sort
        else {
            Sorting.bubbleSort(this, orderedColl, comparator);
        }
        return orderedColl;
    }

    @Override
    default Collection<T> orderDecreasingBy(Comparator<T> comparator) {
        List<T> crescentList = (List<T>) (orderBy(comparator));
        int n = crescentList.size();
        for (int i = 0, reverse = n - i - 1; i < n / 2; i++) {
            T tmp = crescentList.get(i);
            crescentList.set(i, crescentList.get(reverse));
            crescentList.set(reverse, tmp);
        }
        return crescentList;
    }

    @Override
    default T reduce(BinaryOperator<T> accumulator) {
        final T[] result = (T[]) new Object[]{null};
        final boolean[] first = {true};
        forEach(e ->
        {
            result[0] = first[0] ? e : accumulator.apply(result[0], e);
            first[0] = false;
        });
        return result[0];
    }

    @Override
    default T reduceReverse(BinaryOperator<T> accumulator) {
        return reverse().reduce(accumulator);
    }

    @Override
    default Collection<T> reverse() {
        int n = this.size();
        Collection<T> reverseList = makeCollection();
        /*for (int i = 0, reverse = n - i - 1; i < n / 2; i++) {
            T tmp = reverseList.get(i);
            reverseList.set(i, reverseList.get(reverse));
            reverseList.set(reverse, tmp);
        }*/
        Object[] array = toArray();
        for(int i = n-1; i >=0; i--)
        {
            reverseList.add((T) array[i]);
        }
        return reverseList;
    }

    @Override
    default Collection<T> take(int n) {
        Iterator<T> it = iterator();
        Collection<T> result = makeCollection();
        int i = 0;
        while (it.hasNext() && i < n) {
            result.add(it.next());
        }
        return result;
    }

    @Override
    default Collection<T> takeLast(int n) {
        return reverse().take(n);
    }

    @Override
    default Collection<T> union(java.util.Collection<T> collection){
        Collection<T> unionColl = makeCollection();
        unionColl.addAll(this);
        unionColl.addAll(collection);
        return unionColl;
    }

    @Override
    default Collection<Pair<T, Integer>> zipIndexed(){
        Collection<Integer> indxCollection = makeCollection();
        for(int i = 0; i < size(); i++){
            indxCollection.add(i);
        }
        return zipWith(indxCollection);
    }


    @Override
    default <X> Collection<Pair<T, X>> zipWith(Collection<X> other) {
        if (other.size() < this.size()) {
            throw new InputMismatchException("Size of input collection must be more or equal " +
                    "of this collection");
        }
        Iterator<X> otIt = other.iterator();
        Collection<Pair<T, X>> pairCollection = makeCollection();
        forEach((x) -> pairCollection.add(new Pair<T, X>(x, otIt.next())));
        return pairCollection;
    }

}
