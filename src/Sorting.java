import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by mikedev on 01/08/16.
 */
public class Sorting {

    private static <T> ArrayList2<T> bubbleSort(Collection<T> collection, Comparator<T> comparator, int order)
    {
        if(!(order == -1 || order == 0 || order == 1)){
            throw new IllegalArgumentException("Order value can be only -1,0,1");
        }
        ArrayList2<T> orderedList = new ArrayList2<T>();
        T[] unorderedArray = (T[]) new Object[collection.size()];
        collection.toArray(unorderedArray);
        boolean flagStop = true;
        for(int orderedElements = 0; orderedElements < unorderedArray.length; orderedElements++)
        {
            flagStop = true;
            for(int j = 1 ; j < unorderedArray.length; j++)
            {
                int first = j - 1;
                int second = j;
                int cResult = comparator.compare(unorderedArray[first], unorderedArray[second]);
                if(cResult == order)
                {
                    flagStop = false;
                    //swap
                    T tmp = unorderedArray[first];
                    unorderedArray[first] =  unorderedArray[second];
                    unorderedArray[second] = tmp;
                }
            }
            if(flagStop){
                break;
            }
        }

        for(T e: unorderedArray){
            orderedList.add(e);
        }
        return orderedList;
    }

    public static <T> ArrayList2<T> bubbleSortDecrescent(Collection<T> collection, Comparator<T> comparator)
    {
       return bubbleSort(collection, comparator, -1);
    }

    public static <T> ArrayList2<T> bubbleSort(Collection<T> collection, Comparator<T> comparator)
    {
        return bubbleSort(collection, comparator, 1);
    }

    private static <T> void bubbleSort(Collection<T> collection, Collection<T> dest, Comparator<T> comparator, int order)
    {
        if(!(order == -1 || order == 0 || order == 1)){
            throw new IllegalArgumentException("Order value can be only -1,0,1");
        }
        ArrayList2<T> orderedList = new ArrayList2<T>();
        T[] unorderedArray = (T[]) new Object[collection.size()];
        collection.toArray(unorderedArray);
        boolean flagStop = true;
        for(int orderedElements = 0; orderedElements < unorderedArray.length; orderedElements++)
        {
            flagStop = true;
            for(int j = 1 ; j < unorderedArray.length; j++)
            {
                int first = j - 1;
                int second = j;
                int cResult = comparator.compare(unorderedArray[first], unorderedArray[second]);
                if(cResult == order)
                {
                    flagStop = false;
                    //swap
                    T tmp = unorderedArray[first];
                    unorderedArray[first] =  unorderedArray[second];
                    unorderedArray[second] = tmp;
                }
            }
            if(flagStop){
                break;
            }
        }

        //dest.clear();
        //Collections.addAll(dest, unorderedArray);
        dest.addAll(Arrays.asList(unorderedArray));
    }

    public static <T> void bubbleSortDecrescent(Collection<T> collection,Collection<T> dest, Comparator<T> comparator)
    {
        bubbleSort(collection,dest, comparator, -1);
    }

    public static <T> void bubbleSort(Collection<T> collection, Collection<T> dest, Comparator<T> comparator)
    {
        bubbleSort(collection, dest, comparator, 1);
    }
}
