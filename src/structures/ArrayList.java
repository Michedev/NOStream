package structures;

import utils.Pair;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.function.*;

import functions.ConsumerIndexed;
import operations.IOCollection;
import operations.Operation;
import operations.OperationImpl;

/**
 * Created by mikedev on 31/07/16.
 * 
 * There is some code duplication beetwen this class and LinkedList.
 * This choise is driver by design, because i want to respect at maximum the open closed principle.
 * I make private the instance of OperationImpl and IOCollection for avoid to the programmer that
 * use this library unuseful methods involving the implementation of the operations.
 * 
 * Another opportunity  is an abstract class: i can't do this because i want to keep the compatibility
 * with the respective java.util counterpart via inheritance and so the super class must be that.
 */
public class ArrayList<T> extends java.util.ArrayList<T> implements List<T> {
	
	private Operation<T> operationLists = new OperationImpl<>(makeLinker());
	
	public ArrayList(int size){
		super(size);
	}
	
	public ArrayList(){
		super();
	}

	private IOCollection<T> makeLinker() {
		return new IOCollection<T>(){

			@Override
			public ArrayList<T> getInput() {
				return ArrayList.this;
			}

			@Override
			public <S> ArrayList<S> getOutput() {
				return new ArrayList<>();
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
    public ArrayList<T> distinct() {
    	return (ArrayList<T>) getOperationsList().distinct();
    }

    @Override
    public ArrayList<T> filter(Predicate<T> predicate) {
    	return (ArrayList<T>) getOperationsList().filter(predicate);
    }

    @Override
    public ArrayList<T> filterIndexed(BiPredicate<T, Integer> predicate) {
    	return (ArrayList<T>) getOperationsList().filterIndexed(predicate);
    }

    @Override
    public ArrayList<T> filterNotNull() {
    	return (ArrayList<T>) getOperationsList().filterNotNull();
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
    public void forEach(Consumer<? super T> consumer){
    	getOperationsList().forEach(consumer);
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
    public ArrayList<T> intersection(java.util.Collection<T> list) {
    	return (ArrayList<T>) getOperationsList().intersection(list);
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
    public <R> ArrayList<R> map(Function<T, R> mapper) {
    	return (ArrayList<R>) getOperationsList().map(mapper);
    }

    @Override
    public <R> ArrayList<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
    	return (ArrayList<R>) getOperationsList().mapIndexed(mapper);
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
    public ArrayList<T> orderBy(Comparator<T> comparator) {
    	return (ArrayList<T>) getOperationsList().orderBy(comparator);
    }

    @Override
    public ArrayList<T> orderDecreasingBy(Comparator<T> comparator) {
    	return (ArrayList<T>) getOperationsList().orderDecreasingBy(comparator);
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
    public ArrayList<T> reverse() {
    	return (ArrayList<T>) getOperationsList().reverse();
    }

    @Override
    public ArrayList<T> take(int n) {
    	return (ArrayList<T>) getOperationsList().take(n);
    }

    @Override
    public ArrayList<T> takeLast(int n) {
    	return (ArrayList<T>) getOperationsList().takeLast(n);
    }

    @Override
    public ArrayList<T> union(java.util.Collection<T> ArrayList){
    	return (structures.ArrayList<T>) getOperationsList().union(ArrayList);
    }

    @Override
    public ArrayList<Pair<T, Integer>> zipIndexed(){
    	return (ArrayList<Pair<T, Integer>>) getOperationsList().zipIndexed();
    }


    @Override
    public <X> ArrayList<Pair<T, X>> zipWith(java.util.Collection<X> other) {
    	return (ArrayList<Pair<T, X>>) getOperationsList().zipWith(other);
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
