
import Functions.Modifier;
import Functions.ModifierIndexed;

import org.omg.SendingContext.RunTime;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
/**
 * Created by mikedev on 13/07/16.
 */
interface ListOperation<T>
{
    Collection<T> getCollection();
    Iterator<T> getIterator();
    <S> List<S> makeCollection();
    boolean isParallel();
    void toggleParallel();

    default void forEach(Modifier<T> alterator)
    {
        if(!isParallel())
        {
            getIterator().forEachRemaining(alterator::alter);
        }
        else
        {
            int procs = Runtime.getRuntime().availableProcessors();
            Spliterator<T> sIt = getCollection().spliterator();
            Thread[] threads = new Thread[procs];
            for (Thread thread : threads)
            {
                thread = new Thread(() -> sIt.trySplit().forEachRemaining(alterator::alter));
            }
        }

    }

    default void forEachIndexed(ModifierIndexed<T> mI)
    {
        Iterator<T> curr = getCollection().iterator();
        int n = getCollection().size();
        int i = 0;
        while (i < n)
        {
            if(!curr.hasNext() && i < n-1){
                throw new IndexOutOfBoundsException("iterator not has next and index is less than last element");
            }
            mI.alter(i, curr.next());
            i++;
        }

    }


    default void forEachReverse(Modifier<T> mod)
    {
        //TODO Vedere IterTools per invertire iteratore;
        Iterator<T> it;
        for(int i = getCollection().size() - 1; i>= 0; i++)
        {

        }
    }



    default <R> Collection<R> map(Function<T, R> mapper)
    {
        List<R> newCollection = makeCollection();
        forEach(e ->
            {
                newCollection.add(mapper.apply(e));
            });
        return newCollection;
    }

    default <R> Collection<R> mapIndexed(BiFunction<Integer, T, R> mapper)
    {
        List<R> newCollection = makeCollection();
        forEachIndexed((i, e) ->
        {
            newCollection.add(mapper.apply(i, e));
        });
        return newCollection;
    }

    default Collection<T> filter(Predicate<T> predicate)
    {
        List<T> newCollection = makeCollection();
        forEach(e ->
        {
            if(predicate.test(e))
            {
                newCollection.add(e);
            }
        });
        return newCollection;
    }

    default Collection<T> filterIndexed(BiPredicate<Integer, T> predicate)
    {
        List<T> newCollection = makeCollection();
        forEachIndexed((i, e) ->
        {
            if(predicate.test(i, e)) {
                newCollection.add(e);
            }
        });
        return newCollection;
    }

    default T reduce(BinaryOperator<T> accumulator)
    {
        final T[] result = (T[]) new Object[]{null};
        final boolean[] first = {true};
        forEach(e ->
        {
            result[0] = first[0] ? e : accumulator.apply(result[0], e);
            first[0] = false;
        });
        return result[0];
    }

    default T reduceReverse(BinaryOperator<T> accumulator)
    {
        final T[] result = (T[]) new Object[]{null};
        final boolean[] first = {true};

        forEach(e ->
        {
            result[0] = first[0] ? e : accumulator.apply(result[0], e);
            first[0] = false;
        });
        return result[0];
    }

    default T maxBy(Comparator<T> comparator)
    {
        final T[] max = (T[])new Object[]{null};
        forEach(e ->
        {
            if(max[0] == null)
            {
                max[0] = e;
            }
            else
            {
                int cResult = comparator.compare(max[0], e);
                if(cResult == -1)
                {
                    max[0] = e;
                }
            }
        });
        return max[0];
    }

    /**
     * Return the minimum element by the comparator in input.
     * If two elements are equals doesn't change the min.
     * @param comparator
     * @return minimum value
     */
    default T minBy(Comparator<T> comparator)
    {
        final T[] min = (T[]) new Object[]{null};
        forEach(e ->
        {
            if(min[0] == null)
            {
                min[0] = e;
            }
            else
            {
                int cResult = comparator.compare(min[0], e);
                if(cResult == 1)
                {
                    min[0] = e;
                }
            }
        });
        return min[0];
    }

    default <E> Map<E, ArrayList2<T>> groupBy(Function<T, E> thisFuct)
    {
        Map<E,ArrayList2<T>> hashMap = new HashMap<E, ArrayList2<T>>();
        for(T e: getCollection())
        {
            E key = thisFuct.apply(e);
            hashMap.putIfAbsent(key, new ArrayList2<T>());
            hashMap.get(key).add(e);
        }
        return hashMap;

    }

    default int count(Predicate<T> fCounter)
    {
        final int[] c = {0};
        forEach(e ->
        {
            c[0] = fCounter.test(e) ? c[0] + 1 : c[0];
        });
        return c[0];
    }

    default Collection<T> orderBy(Comparator<T> comparator)
    {
        List<T> orderedList = makeCollection();
        // Esegue un mergeSort da java
        if(getCollection() instanceof List)
        {
            orderedList.addAll(getCollection());
            orderedList.sort(comparator);
        }
        //bubble sort
        else
        {
            Sorting.bubbleSort(getCollection(), orderedList, comparator);
        }
        return orderedList;
    }

    default Collection<T> orderDecrescentBy(Comparator<T> comparator)
    {
        ArrayList2<T> crescentList = (ArrayList2) (orderBy(comparator));
        int n = crescentList.size();
        for(int i = 0, reverse = n-i-1; i < n/2; i++)
        {
            T tmp = crescentList.get(i);
            crescentList.set(i, crescentList.get(reverse));
            crescentList.set(reverse, tmp);
        }
        return crescentList;
    }

    default T last()
    {
        int lastIndex = getCollection().size() - 1;
        if(getCollection() instanceof List)
        {
            return ((List<T>) getCollection()).get(lastIndex);
        }
        else
        {
            Iterator<T> it = getCollection().iterator();
            T last = null;
            while(it.hasNext()){
                last = it.next();
            }
            return last;
        }
    }

    /**
     * Zip the current list with another making a collection of pair of the two list
     * Note that if the dimension of input is less than of the invoker the size of the new collection
     * is equals to the size of invoker so the remaining elements of input are ignored
     * @param other : list to zip
     * @param <X> Type of elements of input list
     * @return A collection containing all two the elements
     */
    default <X> Collection<Pair<T,X>> zipWith(Collection<X> other)
    {
        if(other.size() > getCollection().size()) {
            throw new InputMismatchException("Size of input collection must be less or equal " +
                    "than this collection");
        }
        Iterator<X> otIt = other.iterator();
        List<Pair<T,X>> pairCollection = makeCollection();
        forEach((x) -> pairCollection.add(new Pair<T,X>(x, otIt.next())));
        return pairCollection;
    }

}
