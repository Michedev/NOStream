package structures;

import functions.ConsumerIndexed;
import operations.Linker;
import operations.Operation;
import operations.OperationImpl;
import utils.Pair;
import utils.Sorting;

import java.util.*;
import java.util.function.*;

/**
 * Created by mikedev on 01/08/16.
 */
public interface Collection<T> extends java.util.Collection<T>, Operation<T> {

    OperationImpl<T> getOperationsList();
    
    @Override
    default boolean all(Predicate<T> predicate) {
        return getOperationsList().all(predicate);
    }

    @Override
    default boolean any(Predicate<T> predicate) {
        return getOperationsList().any(predicate);
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
        return getOperationsList().count(fCounter);
    }

    @Override
    default Collection<T> distinct() {
    	return getOperationsList().distinct();
    }

    @Override
    default Collection<T> filter(Predicate<T> predicate) {
    	return getOperationsList().filter(predicate);
    }

    @Override
    default Collection<T> filterIndexed(BiPredicate<T, Integer> predicate) {
    	return getOperationsList().filterIndexed(predicate);
    }

    @Override
    default Collection<T> filterNotNull() {
    	return getOperationsList().filterNotNull();
    }

    @Override
    default T first() {
        return getOperationsList().first();
    }

    @Override
    default T first(T defaultValue) {
    	return getOperationsList().first(defaultValue);
    }

    @Override
    default T first(Predicate<T> predicate, T defaultValue) {
    	return getOperationsList().first(predicate, defaultValue);
    }

    @Override
    default T firstOrNull() {
    	return getOperationsList().firstOrNull();
    }

    @Override
    default T firstOrNull(Predicate<T> predicate) {
    	return getOperationsList().firstOrNull(predicate);
    }


    @Override
    default void forEachIndexed(ConsumerIndexed<? super T> mI) {
    	getOperationsList().forEachIndexed(mI);
    }

    @Override
    default void forEachReverse(Consumer<? super T> mod) {
    	getOperationsList().forEachReverse(mod);
    }

    @Override
    default <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct) {
    	return getOperationsList().groupBy(thisFuct);

    }

    @Override
    default Collection<T> intersection(java.util.Collection<T> collection) {
    	return getOperationsList().intersection(collection);
    }

    @Override
    default T last(T defaultValue) {
    	return getOperationsList().last(defaultValue);
    }

    @Override
    default T last() {
    	return getOperationsList().last();
    }

    @Override
    default T last(Predicate<T> predicate, T defaultValue) {
    	return getOperationsList().last(predicate, defaultValue);
    }

    @Override
    default T lastOrNull() {
    	return getOperationsList().lastOrNull();
    }

    @Override
    default T lastOrNull(Predicate<T> predicate) {
    	return getOperationsList().lastOrNull(predicate);
    }

    @Override
    default <R> Collection<R> map(Function<T, R> mapper) {
    	return getOperationsList().map(mapper);
    }

    @Override
    default <R> Collection<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
    	return getOperationsList().mapIndexed(mapper);
    }

    @Override
    default T maxBy(Comparator<T> comparator) {
    	return getOperationsList().maxBy(comparator);
    }


    @Override
    default T minBy(Comparator<T> comparator) {
    	return getOperationsList().minBy(comparator);
    }

    @Override
    default Collection<T> orderBy(Comparator<T> comparator) {
    	return getOperationsList().orderBy(comparator);
    }

    @Override
    default Collection<T> orderDecreasingBy(Comparator<T> comparator) {
    	return getOperationsList().orderDecreasingBy(comparator);
    }

    @Override
    default T reduce(BinaryOperator<T> accumulator) {
    	return getOperationsList().reduce(accumulator);
    }

    @Override
    default T reduceReverse(BinaryOperator<T> accumulator) {
    	return getOperationsList().reduceReverse(accumulator);
    }

    @Override
    default Collection<T> reverse() {
    	return getOperationsList().reverse();
    }

    @Override
    default Collection<T> take(int n) {
    	return getOperationsList().take(n);
    }

    @Override
    default Collection<T> takeLast(int n) {
    	return getOperationsList().takeLast(n);
    }

    @Override
    default Collection<T> union(java.util.Collection<T> collection){
    	return getOperationsList().union(collection);
    }

    @Override
    default Collection<Pair<T, Integer>> zipIndexed(){
    	return getOperationsList().zipIndexed();
    }


    @Override
    default <X> Collection<Pair<T, X>> zipWith(Collection<X> other) {
    	return getOperationsList().zipWith(other);
    }

}
