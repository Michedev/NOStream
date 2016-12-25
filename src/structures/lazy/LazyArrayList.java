package structures.lazy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import factory.ArrayListFactory;
import factory.IOCollectionFactory;
import functions.ConsumerIndexed;
import functions.Function2;
import operations.IOCollection;
import operations.OperationImpl;
import structures.ArrayList;
import structures.Collection;
import structures.List;
import utils.Pair;

public class LazyArrayList<T> extends java.util.ArrayList<T> implements LazyList<T> {

	private LazyExecuter<T> lazyExecuter = new LazyExecuter<>();
	private LazyArrayList<?> inner;
	private LazyActionManager<T> lazyActionManager = new LazyActionManager<>(new ArrayListFactory());

	private <U> LazyArrayList(LazyArrayList<U> lazyArrayList) {
		this.inner = lazyArrayList;
	}
	
	public LazyArrayList() {
	}
	
	@Override
	public <R> ArrayList<R> executeActions(){
		if(lazyActionManager.numActions() == 0 && inner == null){
			ArrayList<T> thisAsStrict = new ArrayList<T>();
			for(T value : this){
				thisAsStrict.add(value);
			}
			return (ArrayList<R>) thisAsStrict;
		}
		ArrayList<T> inputList = null;
		if(inner != null){
			inputList = inner.executeActions();
		}
		else {
			inputList = (ArrayList<T>) lazyActionManager.getInput();
			for(T value : this){
				inputList.add(value);
			}
		}
		lazyActionManager.setInput(inputList);
		return (ArrayList<R>) lazyExecuter.executeTasks(lazyActionManager);
	}

	private OperationImpl<T> getOperationList(){
		return lazyActionManager.getOperationList();
	}
	

	@Override
	public boolean all(Predicate<T> predicate) {
		ArrayList<T> strictList = executeActions();
		return strictList.all(predicate);
	}

	@Override
	public boolean any(Predicate<T> predicate) {
		ArrayList<T> strictList = executeActions();
		return strictList.any(predicate);
	}

	@Override
	public int count(Predicate<T> fCounter) {
		ArrayList<T> strictList = executeActions();
		return strictList.count(fCounter);
	}

	@Override
	public LazyArrayList<T> distinct() {
		lazyActionManager.addAction(() -> getOperationList().distinct());
		return this;
	}
	
	@Override
	public LazyArrayList<T> filter(Predicate<T> predicate) {
		lazyActionManager.addAction(() -> getOperationList().filter(predicate));
		return this;
	}

	@Override
	public LazyArrayList<T> filterIndexed(BiPredicate<T, Integer> predicate) {
		lazyActionManager.addAction(() -> getOperationList().filterIndexed(predicate));
		return this;
	}

	@Override
	public LazyArrayList<T> filterNotNull() {
		lazyActionManager.addAction(() -> getOperationList().filterNotNull());
		return this;
	}

	@Override
	public T first() {
		ArrayList<T> strictList = executeActions();
		return strictList.first();
	}

	@Override
	public T first(T defaultValue) {
		ArrayList<T> strictList = executeActions();
		return strictList.first(defaultValue);
	}

	@Override
	public T first(Predicate<T> predicate, T defaultValue) {
		ArrayList<T> strictList = executeActions();
		return strictList.first(predicate, defaultValue);
	}

	@Override
	public T firstOrNull() {
		ArrayList<T> strictList = executeActions();
		return strictList.firstOrNull();
	}

	@Override
	public T firstOrNull(Predicate<T> predicate) {
		ArrayList<T> strictList = executeActions();
		return strictList.firstOrNull(predicate);
	}
	

	@Override
	public void forEach(Consumer<? super T> consumer) {
		lazyActionManager.addAction(() -> getOperationList().forEach(consumer));
	}
	
	@Override
	public void forEachIndexed(ConsumerIndexed<? super T> consumerI) {
		lazyActionManager.addAction(() -> getOperationList().forEachIndexed(consumerI));
		
	}

	@Override
	public void forEachReverse(Consumer<? super T> consumer) {
		lazyActionManager.addAction(() -> getOperationList().forEachReverse(consumer));
		
	}


	@Override
	public <E> Map<E, List<T>> groupBy(Function<T, E> thisFuct) {
		ArrayList<T> strictList = executeActions();
		return strictList.groupBy(thisFuct);
	}

	@Override
	public LazyArrayList<T> intersection(java.util.Collection<T> collection) {
		lazyActionManager.addAction(() -> getOperationList().intersection(collection));
		return this;
	}

	@Override
	public T last(T defaultValue) {
		ArrayList<T> strictList = executeActions();
		return strictList.last(defaultValue);
	}

	@Override
	public T last() {
		ArrayList<T> strictList = executeActions();
		return strictList.last();
	}

	@Override
	public T last(Predicate<T> predicate, T defaultValue) {
		ArrayList<T> strictList = executeActions();
		return strictList.last(predicate, defaultValue);
	}

	@Override
	public T lastOrNull() {
		ArrayList<T> strictList = executeActions();
		return strictList.lastOrNull();
	}

	@Override
	public T lastOrNull(Predicate<T> predicate) {
		ArrayList<T> strictList = executeActions();
		return strictList.lastOrNull(predicate);
	}
	
	@Override
	public <R> LazyArrayList<R> map(Function<T, R> mapper) {
		LazyArrayList<R> mapOutput = new LazyArrayList<R>(this);
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().map(mapper));
		return mapOutput;
	}	

	@Override
	public <R> LazyArrayList<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().mapIndexed(mapper));
		return new LazyArrayList<>(this);
	}

	@Override
	public T maxBy(Comparator<T> comparator) {
		ArrayList<T> strictList = executeActions();
		return strictList.maxBy(comparator);
	}

	@Override
	public T minBy(Comparator<T> comparator) {
		ArrayList<T> strictList = executeActions();
		return strictList.minBy(comparator);
	}

	@Override
	public LazyArrayList<T> orderBy(Comparator<T> comparator) {
		lazyActionManager.addAction(() -> getOperationList().orderBy(comparator));
		return this;
	}

	@Override
	public LazyArrayList<T> orderDecreasingBy(Comparator<T> comparator) {
		lazyActionManager.addAction(() -> getOperationList().orderDecreasingBy(comparator));
		return this;
	}
	

	@Override
	public T reduce(BinaryOperator<T> accumulator) {
		ArrayList<T> strictList = executeActions();
		return strictList.reduce(accumulator);
	}

	@Override
	public T reduceReverse(BinaryOperator<T> accumulator) {
		ArrayList<T> strictList = executeActions();
		return strictList.reduceReverse(accumulator);
	}

	@Override
	public LazyArrayList<T> reverse() {
		lazyActionManager.addAction(() -> getOperationList().reverse());
		return this;
	}

	@Override
	public LazyArrayList<T> take(int n) {
		lazyActionManager.addAction(() -> getOperationList().take(n));
		return this;
	}

	@Override
	public LazyArrayList<T> takeLast(int n) {
		lazyActionManager.addAction(() -> getOperationList().takeLast(n));
		return this;
	}

	@Override
	public LazyArrayList<T> union(java.util.Collection<T> collection) {
		lazyActionManager.addAction(() -> getOperationList().union(collection));
		return this;
	}

	@Override
	public LazyArrayList<Pair<T, Integer>> zipIndexed() {
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().zipIndexed());
		return new LazyArrayList<>(this);
	}

	@Override
	public <X> LazyArrayList<Pair<T, X>> zipWith(java.util.Collection<X> other) {
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().zipWith(other));
		return new LazyArrayList<>(this);
	}

}
