package structures.lazy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import factory.LinkedListFactory;
import functions.ConsumerIndexed;
import operations.OperationImpl;
import structures.ArrayList;
import structures.LinkedList;
import structures.LinkedList;
import structures.List;
import utils.Pair;

public class LazyLinkedList<T> extends java.util.LinkedList<T> implements LazyList<T> {

	private LazyExecuter<T> lazyExecuter = new LazyExecuter<>();
	private LazyLinkedList<?> inner;
	private LazyActionManager<T> lazyActionManager = new LazyActionManager<>(new LinkedListFactory());

	private <U> LazyLinkedList(LazyLinkedList<U> LazyLinkedList) {
		this.inner = LazyLinkedList;
	}
	
	public LazyLinkedList() {
	}
	
	@Override
	public <R> LinkedList<R> executeActions(){
		if(lazyActionManager.numActions() == 0 && inner == null){
			LinkedList<T> thisAsStrict = new LinkedList<T>();
			for(T value : this){
				thisAsStrict.add(value);
			}
			return (LinkedList<R>) thisAsStrict;
		}
		LinkedList<T> inputList = null;
		if(inner != null){
			inputList = inner.executeActions();
		}
		else {
			inputList = (LinkedList<T>) lazyActionManager.getInput();
			for(T value : this){
				inputList.add(value);
			}
		}
		lazyActionManager.setInput(inputList);
		return (LinkedList<R>) lazyExecuter.executeTasks(lazyActionManager);
	}

	private OperationImpl<T> getOperationList(){
		return lazyActionManager.getOperationList();
	}
	

	@Override
	public boolean all(Predicate<T> predicate) {
		LinkedList<T> strictList = executeActions();
		return strictList.all(predicate);
	}

	@Override
	public boolean any(Predicate<T> predicate) {
		LinkedList<T> strictList = executeActions();
		return strictList.any(predicate);
	}

	@Override
	public int count(Predicate<T> fCounter) {
		LinkedList<T> strictList = executeActions();
		return strictList.count(fCounter);
	}

	@Override
	public LazyLinkedList<T> distinct() {
		lazyActionManager.addAction(() -> getOperationList().distinct());
		return this;
	}
	
	@Override
	public LazyLinkedList<T> filter(Predicate<T> predicate) {
		lazyActionManager.addAction(() -> getOperationList().filter(predicate));
		return this;
	}

	@Override
	public LazyLinkedList<T> filterIndexed(BiPredicate<T, Integer> predicate) {
		lazyActionManager.addAction(() -> getOperationList().filterIndexed(predicate));
		return this;
	}

	@Override
	public LazyLinkedList<T> filterNotNull() {
		lazyActionManager.addAction(() -> getOperationList().filterNotNull());
		return this;
	}

	@Override
	public T first() {
		LinkedList<T> strictList = executeActions();
		return strictList.first();
	}

	@Override
	public T first(T defaultValue) {
		LinkedList<T> strictList = executeActions();
		return strictList.first(defaultValue);
	}

	@Override
	public T first(Predicate<T> predicate, T defaultValue) {
		LinkedList<T> strictList = executeActions();
		return strictList.first(predicate, defaultValue);
	}

	@Override
	public T firstOrNull() {
		LinkedList<T> strictList = executeActions();
		return strictList.firstOrNull();
	}

	@Override
	public T firstOrNull(Predicate<T> predicate) {
		LinkedList<T> strictList = executeActions();
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
		LinkedList<T> strictList = executeActions();
		return strictList.groupBy(thisFuct);
	}

	@Override
	public LazyLinkedList<T> intersection(java.util.Collection<T> collection) {
		lazyActionManager.addAction(() -> getOperationList().intersection(collection));
		return this;
	}

	@Override
	public T last(T defaultValue) {
		LinkedList<T> strictList = executeActions();
		return strictList.last(defaultValue);
	}

	@Override
	public T last() {
		LinkedList<T> strictList = executeActions();
		return strictList.last();
	}

	@Override
	public T last(Predicate<T> predicate, T defaultValue) {
		LinkedList<T> strictList = executeActions();
		return strictList.last(predicate, defaultValue);
	}

	@Override
	public T lastOrNull() {
		LinkedList<T> strictList = executeActions();
		return strictList.lastOrNull();
	}

	@Override
	public T lastOrNull(Predicate<T> predicate) {
		LinkedList<T> strictList = executeActions();
		return strictList.lastOrNull(predicate);
	}
	
	@Override
	public <R> LazyLinkedList<R> map(Function<T, R> mapper) {
		LazyLinkedList<R> mapOutput = new LazyLinkedList<R>(this);
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().map(mapper));
		return mapOutput;
	}	

	@Override
	public <R> LazyLinkedList<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().mapIndexed(mapper));
		return new LazyLinkedList<>(this);
	}

	@Override
	public T maxBy(Comparator<T> comparator) {
		LinkedList<T> strictList = executeActions();
		return strictList.maxBy(comparator);
	}

	@Override
	public T minBy(Comparator<T> comparator) {
		LinkedList<T> strictList = executeActions();
		return strictList.minBy(comparator);
	}

	@Override
	public LazyLinkedList<T> orderBy(Comparator<T> comparator) {
		lazyActionManager.addAction(() -> getOperationList().orderBy(comparator));
		return this;
	}

	@Override
	public LazyLinkedList<T> orderDecreasingBy(Comparator<T> comparator) {
		lazyActionManager.addAction(() -> getOperationList().orderDecreasingBy(comparator));
		return this;
	}
	

	@Override
	public T reduce(BinaryOperator<T> accumulator) {
		LinkedList<T> strictList = executeActions();
		return strictList.reduce(accumulator);
	}

	@Override
	public T reduceReverse(BinaryOperator<T> accumulator) {
		LinkedList<T> strictList = executeActions();
		return strictList.reduceReverse(accumulator);
	}

	@Override
	public LazyLinkedList<T> reverse() {
		lazyActionManager.addAction(() -> getOperationList().reverse());
		return this;
	}

	@Override
	public LazyLinkedList<T> take(int n) {
		lazyActionManager.addAction(() -> getOperationList().take(n));
		return this;
	}

	@Override
	public LazyLinkedList<T> takeLast(int n) {
		lazyActionManager.addAction(() -> getOperationList().takeLast(n));
		return this;
	}

	@Override
	public LazyLinkedList<T> union(java.util.Collection<T> collection) {
		lazyActionManager.addAction(() -> getOperationList().union(collection));
		return this;
	}

	@Override
	public LazyLinkedList<Pair<T, Integer>> zipIndexed() {
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().zipIndexed());
		return new LazyLinkedList<>(this);
	}

	@Override
	public <X> LazyLinkedList<Pair<T, X>> zipWith(java.util.Collection<X> other) {
		lazyActionManager.addTrasformingTypeAction(() -> getOperationList().zipWith(other));
		return new LazyLinkedList<>(this);
	}
}
