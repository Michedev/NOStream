
import Functions.Modifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 13/07/16.
 */
interface ListOperation<T> {
    Collection<T> getCollection();
    Collection<T> makeCollection();

    default void forEach(Modifier<T> alterator)
    {
        getCollection().forEach(alterator::alter);
    }

    default <R> Collection<R> map(Function<T, R> mapper)
    {
        ArrayList<R> newCollection = new ArrayList<R>();
        for(T e : getCollection())
        {
            newCollection.add(mapper.apply(e));
        }
        return newCollection;
    }

    default Collection<T> filter(Predicate<T> predicate)
    {
        ArrayList<T> newCollection = new ArrayList<T>();
        for(T e: getCollection())
        {
            if(predicate.test(e))
            {
                newCollection.add(e);
            }
        }
        return newCollection;
    }

    default T maxBy(Comparator<T> comparator)
    {
        T max = null;
        for(T e :  getCollection())
        {
            if(max == null)
            {
                max = e;
            }
            else
            {
                int cResult = comparator.compare(max, e);
                if(cResult == -1)
                {
                    max = e;
                }
            }
        }
        return max;
    }

    /**
     * Return the minimum element by the comparator in input.
     * If two elements are equals doesn't change the min.
     * @param comparator
     * @return minimum value
     */
    default T minBy(Comparator<T> comparator)
    {
        T min = null;
        for(T e :  getCollection())
        {
            if(min == null)
            {
                min = e;
            }
            else
            {
                int cResult = comparator.compare(min, e);
                if(cResult == 1)
                {
                    min = e;
                }
            }
        }
        return min;
    }

    default <E> Map<E, T> groupBy(Predicate<E> grouper)
    {

    }

    default int count(Predicate<T> fCounter)
    {
        int c = 0;
        for(T e : getCollection())
        {
            c = fCounter.test(e) ? c + 1 : c;
        }
        return c;
    }

    default Collection<T> orderBy(Comparator<T> comparator)
    {
        ArrayList<T> orderedList = 
    }

}
