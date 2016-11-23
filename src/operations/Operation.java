package operations;

import functions.ConsumerIndexed;
import structures.Collection;
import utils.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.function.*;

/**
 * Created by mikedev on 05/08/16.
 */
public interface Operation<T> {

    /**
     *
     * @param predicate Criteria that decide if an element of the list is true or false
     * @return true if all elements are positive by predicate else false
     */
    boolean all(Predicate<T> predicate);

    /**
     *
     * @param predicate Criteria that decide if an element of the list is true or false
     * @return true if exists one element that is positive in the predicate else false
     */
    boolean any(Predicate<T> predicate);

    /**
     *
     * @param fCounter Criteria that decide if an element of the list is true or false
     * @return the number of element that are true with this predicate
     */
    int count(Predicate<T> fCounter);

    /**
     * Remove the elements that are the same by the method equals()
     * @return a new list with all different elements
     */
    java.util.Collection distinct();

    /**
     * Remove the elements that are true in the predicate
     * @param predicate Criteria that decide if an element of the list is true or false
     * @return a new list except the elements true in the predicate
     */
    java.util.Collection filter(Predicate<T> predicate);

    /**
     * Same of filter but with the index of the element in input
     * @param predicate a normal predicate with the index next to the element
     * @return a new list except the elements true in the predicate
     */
    java.util.Collection filterIndexed(BiPredicate<T, Integer> predicate);

    /**
     *
     * @return the first element of the element or throw an exception if empty
     */
    T first();

    /**
     *
     * @param defaultValue value returned if the collection is empty
     * @return the first element of the element or defaultValue if empty
     */
    T first(T defaultValue);

    /**
     *
     * @param predicate Criteria that decide if an element of the list is true or false
     * @param defaultValue value returned if no one element of the collection satisfy the predicate
     * @return the first element that satisfy the predicate or the default value if there isn't
     */
    T first(Predicate<T>  predicate, T defaultValue);

    /**
     *
     * @return the first element of the element or null if empty
     */
    T firstOrNull();

    /**
     *
     * @param predicate Criteria that decide if an element of the list is true or false
     * @return the first element of the element that satisfy the predicate or null if there isn't
     */
    T firstOrNull(Predicate<T> predicate);

    /**
     * Alternative forEach with the index next to the element
     * @param mI lambda expression applied on each element of the collection where the first argument is an element and the second its index
     */
    void forEachIndexed(ConsumerIndexed<? super T> mI);

    /**
     * examine the list from right to left
     * @param mod lambda expression applied on each element of the collection
     */
    void forEachReverse(Consumer<? super T> mod);

    <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct);

    /**
     *
     * @param collection another collection
     * @return a new collection with the element equals between them
     */
    java.util.Collection<T> intersection(java.util.Collection<T> collection);

    /**
     *
     * @param defaultValue value returned if the collection is empty
     * @return the last element of the collection or the default value if empty
     */
    T last(T defaultValue);

    /**
     *
     * @return the last element of the collection or throw an exception if empty
     */
    T last();

    /**
     *
     * @param predicate Criteria that decide if an element of the list is true or false
     * @param defaultValue value returned if no one element satisfy the predicate
     * @return the last element that satisfy the predicate or the default value if there isn't
     */
    T last(Predicate<T> predicate, T defaultValue);

    /**
     *
     * @return the last element of the collection or null if empty
     */
    T lastOrNull();

    /**
     *
     * @param predicate Criteria that decide if an element of the list is true or false
     * @return the last element that satisfy the predicate or null if empty
     */
    T lastOrNull(Predicate<T> predicate);

    /**
     *
     * @param mapper function that transform the elements of the collection in another type
     * @param <R> the type of return value of mapper
     * @return a new collection that contains elements of type R
     */
    <R> java.util.Collection map(Function<T, R> mapper);

    /**
     * Same of map but with the index next to the input element
     * @param mapper lambda expression that map T elements with their index associated in R elements
     * @param <R> type returned from mapper
     * @return a new collection of type R
     */
    <R> java.util.Collection mapIndexed(BiFunction<T, Integer, R> mapper);

    /**
     *
     * @param comparator An object that compare 2 elements : return -1 if the first element is less than
     *                   the second, 0 if equals, 1 if is greater
     * @return the max by the comparator
     */
    T maxBy(Comparator<T> comparator);

    /**
     *
     * @param comparator An object that compare 2 elements : return -1 if the first element is less than
     *                   the second, 0 if equals, 1 if is greater
     * @return the min by the comparator
     */
    T minBy(Comparator<T> comparator);

    /**
     *
     * @param comparator An object that compare 2 elements : return -1 if the first element is less than
     *                   the second, 0 if equals, 1 if is greater
     * @return a new list ordered in crescent mode
     */
    java.util.Collection orderBy(Comparator<T> comparator);

    /**
     *
     * @param comparator An object that compare 2 elements : return -1 if the first element is less than
     *                   the second, 0 if equals, 1 if is greater
     * @return a new list ordered in decreasing mode
     */

    java.util.Collection orderDecreasingBy(Comparator<T> comparator);

    /**
     * Apply the BinaryOperator to all elements from left to right. At the first iteration the return value
     * is the first element. At the successive iterations
     * is used {accumulator.apply(return_value, next_element} until all the list is not finished
     * @param accumulator a function that from 2 elements return one of the same type
     * @return a element obtained from the accumulator
     */
    T reduce(BinaryOperator<T> accumulator);

    /**
     *
     * Apply the BinaryOperator to all elements from right to left. At the first iteration the return value
     * is the last element. At the successive iterations
     * is used {accumulator.apply(return_value, next_element} until all the list is not finished
     * @param accumulator a function that from 2 elements return one of the same type
     * @return a element obtained from the accumulator
     */
    T reduceReverse(BinaryOperator<T> accumulator);

    /**
     *
     * @return a new collection that is reverse [example : first element now = last element before reverse]
     */
    java.util.Collection reverse();

    /**
     *
     * @param n the number of elements to be take from the list
     * @return a new collection with only the first n elements
     */
    java.util.Collection take(int n);

    /**
     *
     * @param n the number of elements to be take from the list
     * @return a new collection with only the last n elements
     */
    java.util.Collection takeLast(int n);

    /**
     *
     * @param collection another collection
     * @return a new collection with different elements from the collections
     */
    java.util.Collection<T> union(java.util.Collection<T> collection);

    /**
     *
     * @return a new collection of Pair{Element, Index of element}
     */
    java.util.Collection<Pair<T, Integer>> zipIndexed();

    /**
     * Zip the current list with another making a collection of pair of the two list
     * Note that if the dimension of input is more than of the invoker the size of the new collection
     * is equals to the size of invoker so the remaining elements of input are ignored
     *
     * @param other : list to zip
     * @param <X>   Type of elements of input list
     * @return A collection containing all two the elements
     */
    <X> java.util.Collection<Pair<T,X>> zipWith(Collection<X> other);

    /**
     * this is equivalent to {@code filter(x -> x!= null)}
     * @return a new collection with not null elements
     */
    default java.util.Collection<T> filterNotNull(){
        return filter(x -> x!= null);
    }

}
