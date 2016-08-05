package Operations;

import Functions.Modifier;
import Functions.ModifierIndexed;
import Structures.Collection;

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

    java.util.Collection filterIndexed(BiPredicate<Integer, T> predicate);

    default java.util.Collection filterNotNull(){
        return filter(x -> x!= null);
    }

    T first();

    T first(T defaultValue);

    T first(Predicate<T>  predicate, T defaultValue);

    T firstOrNull();

    T firstOrNull(Predicate<T> predicate);

    void forEach(Modifier<T> alterator);

    void forEachIndexed(ModifierIndexed<T> mI);

    void forEachReverse(Modifier<T> mod);

    <E> Map<E, Collection<T>> groupBy(Function<T, E> thisFuct);

    T lastOrNull();

    T lastOrNull(Predicate<T> predicate);

    T last(T defaultValue);

    T last();

    T last(Predicate<T> predicate, T defaultValue);

    <R> java.util.Collection map(Function<T, R> mapper);

    <R> java.util.Collection mapIndexed(BiFunction<Integer, T, R> mapper);

    T maxBy(Comparator<T> comparator);

    T minBy(Comparator<T> comparator);

    java.util.Collection orderBy(Comparator<T> comparator);

    java.util.Collection orderDecrescentBy(Comparator<T> comparator);

    T reduce(BinaryOperator<T> accumulator);

    T reduceReverse(BinaryOperator<T> accumulator);

    java.util.Collection take(int n);

    java.util.Collection takeLast(int n);

    <X> java.util.Collection zipWith(java.util.Collection other);

    java.util.Collection reverse();

}
