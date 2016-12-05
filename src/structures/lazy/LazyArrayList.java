package structures.lazy;

import java.util.Comparator;
import java.util.Map;
import java.util.Vector;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import functions.Function2;
import operations.IOCollection;
import operations.OperationImpl;
import structures.ArrayList;
import structures.Collection;
import utils.Pair;

public class LazyArrayList<T> extends java.util.ArrayList<T> implements LazyList<T> {

	private LazyExecuter data = new LazyExecuter();
	private java.util.ArrayList<Runnable> actions = new java.util.ArrayList<>();
	private boolean mapInvoked = false;

	private <U> LazyArrayList(LazyArrayList<U> lazyArrayList) {
		this.data.inner = lazyArrayList;
	}
	
	public LazyArrayList() {
	}
	
	@Override
	public <R> ArrayList<R> executeTasks(){
		return data.executeTasks(actions);
	}
	public OperationImpl<T> getOperationsList() {
		return data.operationImpl ;
	}

	@Override
	public LazyArrayList<T> filter(Predicate<T> predicate) {
		data.actions.add(() -> getOperationsList().filter(predicate));
		return this;
	}

	@Override
	public <R> LazyArrayList<R> map(Function<T, R> mapper) 
	{
		LazyArrayList<R> mapOutput = new LazyArrayList<R>(this);
		wrapLastAction(() -> getOperationsList().map(mapper));
		mapInvoked = true;
		return mapOutput;
	}

	private <R> void wrapLastAction(Runnable endingRunnable) {
		data.actions.add(() -> 
		{
			getOperationsList()
				.setIOCollection(new IOCollection<T>()
				{
					private ArrayList<R> output = new ArrayList<R>();
	
					@Override
					public ArrayList<T> getInput() {
						return data.input;
					}
	
					@Override
					public <S> ArrayList<S> getOutput() {
						return (ArrayList<S>) output;
					}
					
				});
			endingRunnable.run();
	
		});
	}
	
	

	@Override
	public boolean all(Predicate<T> predicate) {
		ArrayList<T> computedTask = executeTasks();
		return computedTask.all(predicate);
	}

	@Override
	public boolean any(Predicate<T> predicate) {
		ArrayList<T> computedTask = executeTasks();
		return computedTask.any(predicate);
	}

	@Override
	public int count(Predicate<T> fCounter) {
		ArrayList<T> computedTask = executeTasks();
		return computedTask.count(fCounter);
	}

	@Override
	public Collection<T> distinct() {
		// TODO Auto-generated method stub
		return LazyList.super.distinct();
	}

	@Override
	public Collection<T> filterIndexed(BiPredicate<T, Integer> predicate) {
		// TODO Auto-generated method stub
		return LazyList.super.filterIndexed(predicate);
	}

	@Override
	public Collection<T> filterNotNull() {
		// TODO Auto-generated method stub
		return LazyList.super.filterNotNull();
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return LazyList.super.first();
	}

	@Override
	public T first(T defaultValue) {
		// TODO Auto-generated method stub
		return LazyList.super.first(defaultValue);
	}

	@Override
	public T first(Predicate<T> predicate, T defaultValue) {
		// TODO Auto-generated method stub
		return LazyList.super.first(predicate, defaultValue);
	}

	@Override
	public T firstOrNull() {
		// TODO Auto-generated method stub
		return LazyList.super.firstOrNull();
	}

	@Override
	public T firstOrNull(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return LazyList.super.firstOrNull(predicate);
	}

	@Override
	public <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct) {
		// TODO Auto-generated method stub
		return LazyList.super.groupBy(thisFuct);
	}

	@Override
	public Collection<T> intersection(java.util.Collection<T> collection) {
		// TODO Auto-generated method stub
		return LazyList.super.intersection(collection);
	}

	@Override
	public T last(T defaultValue) {
		// TODO Auto-generated method stub
		return LazyList.super.last(defaultValue);
	}

	@Override
	public T last() {
		// TODO Auto-generated method stub
		return LazyList.super.last();
	}

	@Override
	public T last(Predicate<T> predicate, T defaultValue) {
		// TODO Auto-generated method stub
		return LazyList.super.last(predicate, defaultValue);
	}

	@Override
	public T lastOrNull() {
		// TODO Auto-generated method stub
		return LazyList.super.lastOrNull();
	}

	@Override
	public T lastOrNull(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return LazyList.super.lastOrNull(predicate);
	}

	@Override
	public <R> Collection<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
		// TODO Auto-generated method stub
		return LazyList.super.mapIndexed(mapper);
	}

	@Override
	public T maxBy(Comparator<T> comparator) {
		// TODO Auto-generated method stub
		return LazyList.super.maxBy(comparator);
	}

	@Override
	public T minBy(Comparator<T> comparator) {
		// TODO Auto-generated method stub
		return LazyList.super.minBy(comparator);
	}

	@Override
	public Collection<T> orderBy(Comparator<T> comparator) {
		// TODO Auto-generated method stub
		return LazyList.super.orderBy(comparator);
	}

	@Override
	public Collection<T> orderDecreasingBy(Comparator<T> comparator) {
		// TODO Auto-generated method stub
		return LazyList.super.orderDecreasingBy(comparator);
	}

	@Override
	public T reduceReverse(BinaryOperator<T> accumulator) {
		// TODO Auto-generated method stub
		return LazyList.super.reduceReverse(accumulator);
	}

	@Override
	public Collection<T> reverse() {
		// TODO Auto-generated method stub
		return LazyList.super.reverse();
	}

	@Override
	public Collection<T> take(int n) {
		// TODO Auto-generated method stub
		return LazyList.super.take(n);
	}

	@Override
	public Collection<T> takeLast(int n) {
		// TODO Auto-generated method stub
		return LazyList.super.takeLast(n);
	}

	@Override
	public Collection<T> union(java.util.Collection<T> collection) {
		// TODO Auto-generated method stub
		return LazyList.super.union(collection);
	}

	@Override
	public Collection<Pair<T, Integer>> zipIndexed() {
		// TODO Auto-generated method stub
		return LazyList.super.zipIndexed();
	}

	@Override
	public <X> Collection<Pair<T, X>> zipWith(Collection<X> other) {
		// TODO Auto-generated method stub
		return LazyList.super.zipWith(other);
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		super.forEach(action);
	}

	@Override
	public T reduce(BinaryOperator<T> accumulator) {
		ArrayList<T> result = executeTasks();
		return result.reduce(accumulator);
	}
	
	

}
