package operations;

import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import functions.ConsumerIndexed;
import structures.ArrayList;
import structures.Collection;
import structures.List;
import utils.Pair;
import utils.Sorting;

public class OperationImpl<T> implements Operation<T> {
	
	private IOCollection<T> linker;


	public OperationImpl(IOCollection<T> linker){
		this.linker = linker;
	}
	
	public OperationImpl(){
		
	}
	
	public void setIOCollection(IOCollection<T> linker){
		this.linker = linker;
	}
	
	public Collection<T> getInputCollection(){
		return linker.getInput();
	}
	
	public <S> Collection<S> getOutputCollection(){
		return linker.getOutput();
	}
	
    @Override
    public boolean all(Predicate<T> predicate) {
        Iterator<T> it = getInputCollection().iterator();
        while (it.hasNext()) {
            if (!predicate.test(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean any(Predicate<T> predicate) {
        Iterator<T> it = getInputCollection().iterator();
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                return true;
            }
        }
        return false;
    }

    /*@Override
    public void forEach(Modifier<T> alterator)
    {
        if(!isParallel())
        {
            iterator().forEachRemaining(alterator::alter);
        }
        else
        {
            int procs = Runtime.getRuntime().availableProcessors();
            Spliterator<T> sIt = getInputCollection().spliterator();
            Thread[] threads = new Thread[procs];
            for (Thread thread : threads)
            {
                thread = new Thread(() -> sIt.trySplit().forEachRemaining(alterator::alter));
            }
        }

    }*/

    @Override
    public int count(Predicate<T> fCounter) {
        final int[] c = {0};
        getInputCollection().forEach(e ->{
            c[0] = fCounter.test(e) ? c[0] + 1 : c[0];
        });
        return c[0];
    }

    @Override
    public Collection<T> distinct() 
    {
        Collection<T> distinctCollection = getOutputCollection();
        distinctCollection.addAll(getInputCollection());
        /*iterator().forEachRemaining(x -> {
        	for(int i = 0; i < distinctCollection.size(); i++){
        		
        	}
        });*/
        T[] array = (T[]) getInputCollection().toArray();
        int n = distinctCollection.size();
        for (int i = 0; i < n; i++) {
            if(array[i] != null)
                for (int j = i + 1; j < n; j++) {
                    if (i != j && array[j]!=null && array[i].equals(array[j])) {
                        distinctCollection.remove(array[j]);
                        array[j] = null;
                    }
                }
        }
        return distinctCollection;
    }

    @Override
    public Collection<T> filter(Predicate<T> predicate) {
        Collection<T> newCollection = getOutputCollection();
        forEach(e ->
        {
            if (predicate.test(e)) {
                newCollection.add(e);
            }
        });
        return newCollection;
    }

    @Override
    public Collection<T> filterIndexed(BiPredicate<T, Integer> predicate) {
        Collection<T> newCollection = getOutputCollection();
        forEachIndexed((e, i) ->
        {
            if (predicate.test(e, i)) {
                newCollection.add(e);
            }
        });
        return newCollection;
    }

    @Override
    public Collection<T> filterNotNull() {
        return (Collection<T>) Operation.super.filterNotNull();
    }

    @Override
    public T first() {
        return getInputCollection().iterator().next();
    }

    @Override
    public T first(T publicValue) {
        T result = firstOrNull();
        return result != null ? result : publicValue;
    }

    @Override
    public T first(Predicate<T> predicate, T publicValue) {
        T result = firstOrNull(predicate);
        return result != null ? result : publicValue;
    }

    @Override
    public T firstOrNull() {
        return getInputCollection().size() > 0 ? getInputCollection().iterator().next() : null;
    }

    @Override
    public T firstOrNull(Predicate<T> predicate) {
        Iterator<T> it = getInputCollection().iterator();
        while (it.hasNext()) {
            T el = it.next();
            if (predicate.test(el)) {
                return el;
            }
        }
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        Iterator<T> it = getInputCollection().iterator();
        while(it.hasNext()){
            consumer.accept(it.next());
        }
    }

    @Override
    public void forEachIndexed(ConsumerIndexed<? super T> mI) {
        Iterator<T> curr = getInputCollection().iterator();
        int n = getInputCollection().size();
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
    public void forEachReverse(Consumer<? super T> mod) {
        reverse().forEach(mod);
    }

    @Override
    public <E> Map<E, ArrayList<T>> groupBy(Function<T, E> gatherer) {
        Map<E, ArrayList<T>> hashMap = new HashMap<>();
        for (T e : getInputCollection()) {
            E key = gatherer.apply(e);
            hashMap.putIfAbsent(key, new ArrayList<T>());
            hashMap.get(key).add(e);
        }
        return hashMap;

    }

    @Override
    public Collection<T> intersection(java.util.Collection<T> collection) {
        Collection<T> intersectColl = getOutputCollection();
        java.util.Collection<T> firstCollection = getInputCollection().size() > collection.size()? collection : getInputCollection();
        java.util.Collection<T> secondCollection = firstCollection == getInputCollection() ? collection : getInputCollection();
        for(T e : firstCollection)
        {
            if(secondCollection.contains(e)){
                intersectColl.add(e);
            }
        }
        return intersectColl;
    }

    @Override
    public T last(T publicValue) {
        T lastEl = lastOrNull();
        return lastEl != null ? lastEl : publicValue;
    }

    @Override
    public T last() {
        T lastEl = lastOrNull();
        Objects.requireNonNull(lastEl, "Empty list or last element null");
        return lastEl;
    }

    @Override
    public T last(Predicate<T> predicate, T publicValue) {
        T lastEl = lastOrNull(predicate);
        return lastEl != null ? lastEl : publicValue;
    }

    @Override
    public T lastOrNull() {
        int lastIndex = getInputCollection().size() - 1;
        if (lastIndex == -1) {
            throw new IndexOutOfBoundsException("Empty collection");
        }
        if (getInputCollection() instanceof List) {
            return ((List<T>) getInputCollection()).get(lastIndex);
        } else {
            Iterator<T> it = getInputCollection().iterator();
            T last = null;
            while (it.hasNext()) {
                last = it.next();
            }
            return last;
        }
    }

    @Override
    public T lastOrNull(Predicate<T> predicate) {
        Iterator<T> it = getInputCollection().iterator();
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
    public <R> Collection<R> map(Function<T, R> mapper) {
        Collection<R> newCollection = getOutputCollection();
        forEach(e ->
        {
            newCollection.add(mapper.apply(e));
        });
        return newCollection;
    }

    @Override
    public <R> Collection<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
        List<R> newCollection = (List<R>) getOutputCollection();
        forEachIndexed((e, i) ->
        {
            newCollection.add(mapper.apply(e, i));
        });
        return newCollection;
    }

    @Override
    public T maxBy(Comparator<T> comparator) {
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
    public T minBy(Comparator<T> comparator) {
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
    public Collection<T> orderBy(Comparator<T> comparator) {
        Collection<T> orderedColl = getOutputCollection();
        // Esegue un mergeSort da java
        if (orderedColl instanceof List) {
            List<T> orderedList = (List<T>) orderedColl;
            orderedList.addAll(getInputCollection());
            orderedList.sort(comparator);
        }
        //bubble sort
        else {
            Sorting.bubbleSort(getInputCollection(), orderedColl, comparator);
        }
        return orderedColl;
    }

    @Override
    public Collection<T> orderDecreasingBy(Comparator<T> comparator) {
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
    public T reduce(BinaryOperator<T> accumulator) {
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
    public T reduceReverse(BinaryOperator<T> accumulator) {
        return reverse().reduce(accumulator);
    }

    @Override
    public Collection<T> reverse() {
        int n = getInputCollection().size();
        Collection<T> reverseList = getOutputCollection();
        /*for (int i = 0, reverse = n - i - 1; i < n / 2; i++) {
            T tmp = reverseList.get(i);
            reverseList.set(i, reverseList.get(reverse));
            reverseList.set(reverse, tmp);
        }*/
        Object[] array = getInputCollection().toArray();
        for(int i = n-1; i >=0; i--)
        {
            reverseList.add((T) array[i]);
        }
        return reverseList;
    }

    @Override
    public Collection<T> take(int n) {
        Iterator<T> it = getInputCollection().iterator();
        Collection<T> result = getOutputCollection();
        int i = 0;
        while (it.hasNext() && i < n) {
            result.add(it.next());
        }
        return result;
    }

    @Override
    public Collection<T> takeLast(int n) {
        Collection<T> reversed = reverse();
        return reversed.take(n);
    }

    @Override
    public Collection<T> union(java.util.Collection<T> collection){
        Collection<T> unionColl = getOutputCollection();
        unionColl.addAll(getInputCollection());
        unionColl.addAll(collection);
        return unionColl;
    }

    @Override
    public Collection<Pair<T, Integer>> zipIndexed(){
        Collection<Integer> indxCollection = getOutputCollection();
        for(int i = 0; i < getInputCollection().size(); i++){
            indxCollection.add(i);
        }
        return zipWith(indxCollection);
    }


    @Override
    public <X> Collection<Pair<T, X>> zipWith(java.util.Collection<X> other) {
        if (other.size() < getInputCollection().size()) {
            throw new InputMismatchException("Size of input collection must be more or equal " +
                    "of getInputCollection() collection");
        }
        Iterator<X> otIt = other.iterator();
        Collection<Pair<T, X>> pairCollection = getOutputCollection();
        forEach((x) -> pairCollection.add(new Pair<T, X>(x, otIt.next())));
        return pairCollection;
    }
}
