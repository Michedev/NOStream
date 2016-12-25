package structures;

import utils.Pair;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import functions.ConsumerIndexed;
import operations.IOCollection;
import operations.Operation;
import operations.OperationImpl;

/**
 * Created by mikedev on 08/08/16.
 */
public class LinkedList<T> extends java.util.LinkedList<T> implements List<T> {
	
	private OperationImpl<T> operationLists = new OperationImpl<>(makeLinker());
	private IOCollection<T> makeLinker() {
		return new IOCollection<T>(){

			@Override
			public LinkedList<T> getInput() {
				return LinkedList.this;
			}

			@Override
			public <S> LinkedList<S> getOutput() {
				return new LinkedList<>();
			}
			
		};
	}
    
    
    @Override
    public boolean all(Predicate<T> predicate) {
        return getOperationsList().all(predicate);
    }

    @Override
    public boolean any(Predicate<T> predicate) {
        return getOperationsList().any(predicate);
    }

    @Override
    public int count(Predicate<T> fCounter) {
        return getOperationsList().count(fCounter);
    }

    @Override
    public LinkedList<T> distinct() {
    	return (LinkedList<T>) getOperationsList().distinct();
    }

    @Override
    public LinkedList<T> filter(Predicate<T> predicate) {
    	return (LinkedList<T>) getOperationsList().filter(predicate);
    }

    @Override
    public LinkedList<T> filterIndexed(BiPredicate<T, Integer> predicate) {
    	return (LinkedList<T>) getOperationsList().filterIndexed(predicate);
    }

    @Override
    public LinkedList<T> filterNotNull() {
    	return (LinkedList<T>) getOperationsList().filterNotNull();
    }

    @Override
    public T first() {
        return getOperationsList().first();
    }

    @Override
    public T first(T publicValue) {
    	return getOperationsList().first(publicValue);
    }

    @Override
    public T first(Predicate<T> predicate, T publicValue) {
    	return getOperationsList().first(predicate, publicValue);
    }

    @Override
    public T firstOrNull() {
    	return getOperationsList().firstOrNull();
    }

    @Override
    public T firstOrNull(Predicate<T> predicate) {
    	return getOperationsList().firstOrNull(predicate);
    }


    @Override
    public void forEachIndexed(ConsumerIndexed<? super T> mI) {
    	getOperationsList().forEachIndexed(mI);
    }

    @Override
    public void forEachReverse(Consumer<? super T> mod) {
    	getOperationsList().forEachReverse(mod);
    }

    @Override
    public <E> Map<E, List<T>> groupBy(Function<T, E> thisFuct) {
    	return getOperationsList().groupBy(thisFuct);

    }

    @Override
    public LinkedList<T> intersection(java.util.Collection<T> list) {
    	return (LinkedList<T>) getOperationsList().intersection(list);
    }

    @Override
    public T last(T publicValue) {
    	return getOperationsList().last(publicValue);
    }

    @Override
    public T last() {
    	return getOperationsList().last();
    }

    @Override
    public T last(Predicate<T> predicate, T publicValue) {
    	return getOperationsList().last(predicate, publicValue);
    }

    @Override
    public T lastOrNull() {
    	return getOperationsList().lastOrNull();
    }

    @Override
    public T lastOrNull(Predicate<T> predicate) {
    	return getOperationsList().lastOrNull(predicate);
    }

    @Override
    public <R> LinkedList<R> map(Function<T, R> mapper) {
    	return (LinkedList<R>) getOperationsList().map(mapper);
    }

    @Override
    public <R> LinkedList<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
    	return (LinkedList<R>) getOperationsList().mapIndexed(mapper);
    }

    @Override
    public T maxBy(Comparator<T> comparator) {
    	return getOperationsList().maxBy(comparator);
    }


    @Override
    public T minBy(Comparator<T> comparator) {
    	return getOperationsList().minBy(comparator);
    }

    @Override
    public LinkedList<T> orderBy(Comparator<T> comparator) {
    	return (LinkedList<T>) getOperationsList().orderBy(comparator);
    }

    @Override
    public LinkedList<T> orderDecreasingBy(Comparator<T> comparator) {
    	return (LinkedList<T>) getOperationsList().orderDecreasingBy(comparator);
    }

    @Override
    public T reduce(BinaryOperator<T> accumulator) {
    	return getOperationsList().reduce(accumulator);
    }

    @Override
    public T reduceReverse(BinaryOperator<T> accumulator) {
    	return getOperationsList().reduceReverse(accumulator);
    }

    @Override
    public LinkedList<T> reverse() {
    	return (LinkedList<T>) getOperationsList().reverse();
    }

    @Override
    public LinkedList<T> take(int n) {
    	return (LinkedList<T>) getOperationsList().take(n);
    }

    @Override
    public LinkedList<T> takeLast(int n) {
    	return (LinkedList<T>) getOperationsList().takeLast(n);
    }

    @Override
    public LinkedList<T> union(java.util.Collection<T> LinkedList){
    	return (structures.LinkedList<T>) getOperationsList().union(LinkedList);
    }

    @Override
    public LinkedList<Pair<T, Integer>> zipIndexed(){
    	return (LinkedList<Pair<T, Integer>>) getOperationsList().zipIndexed();
    }


    @Override
    public <X> LinkedList<Pair<T, X>> zipWith(java.util.Collection<X> other) {
    	return (LinkedList<Pair<T, X>>) getOperationsList().zipWith(other);
    }

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        String print = "[";
        while(it.hasNext())
        {
            print += it.next()+ (it.hasNext()? ", ": " ]");
        }
        return print;
    }

	private Operation<T> getOperationsList() {
		return operationLists ;
	}
}