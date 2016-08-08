package Operations;

import Functions.ConsumerIndexed;
import Structures.Collection;
import utils.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.function.*;

/**
 * Created by mikedev on 05/08/16.
 */
public interface Operation<T> {

    boolean all(Predicate<T> predicate);

    boolean any(Predicate<T> predicate);

    int count(Predicate<T> fCounter);

    java.util.Collection distinct();

    java.util.Collection filter(Predicate<T> predicate);

    java.util.Collection filterIndexed(BiPredicate<T, Integer> predicate);

    T first();

    T first(T defaultValue);

    T first(Predicate<T>  predicate, T defaultValue);

    T firstOrNull();

    T firstOrNull(Predicate<T> predicate);

    void forEachIndexed(ConsumerIndexed<? super T> mI);

    //void forEach(Consumer<? super T> consumer);

    void forEachReverse(Consumer<? super T> mod);

    <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct);

    java.util.Collection<T> intersection(java.util.Collection<T> collection);

    T last(T defaultValue);

    T last();

    T last(Predicate<T> predicate, T defaultValue);

    T lastOrNull();

    T lastOrNull(Predicate<T> predicate);

    <R> java.util.Collection map(Function<T, R> mapper);

    <R> java.util.Collection mapIndexed(BiFunction<T, Integer, R> mapper);

    T maxBy(Comparator<T> comparator);

    T minBy(Comparator<T> comparator);

    java.util.Collection orderBy(Comparator<T> comparator);

    java.util.Collection orderDecrescentBy(Comparator<T> comparator);

    T reduce(BinaryOperator<T> accumulator);

    T reduceReverse(BinaryOperator<T> accumulator);

    java.util.Collection reverse();

    java.util.Collection take(int n);

    java.util.Collection takeLast(int n);

    java.util.Collection<T> union(java.util.Collection<T> collection);

    Collection<Pair<T, Integer>> zipIndexed();

    <X> java.util.Collection<Pair<T,X>> zipWith(Collection<X> other);

    default java.util.Collection<T> filterNotNull(){
        return filter(x -> x!= null);
    }

}
