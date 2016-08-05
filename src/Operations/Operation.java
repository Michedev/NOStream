package Operations;

import Functions.Modifier;
import Functions.ModifierIndexed;
import Structures.Collection2;
import utils.Pair;

import java.util.Collection;
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

    Collection<T> distinct();

    Collection<T> filter(Predicate<T> predicate);

    Collection<T> filterIndexed(BiPredicate<Integer, T> predicate);

    default Collection<T> filterNotNull(){
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

    <E> Map<E, Collection2<T>> groupBy(Function<T, E> thisFuct);

    T lastOrNull();

    T lastOrNull(Predicate<T> predicate);

    T last(T defaultValue);

    T last();

    T last(Predicate<T> predicate, T defaultValue);

    <R> Collection<R> map(Function<T, R> mapper);

    <R> Collection<R> mapIndexed(BiFunction<Integer, T, R> mapper);

    T maxBy(Comparator<T> comparator);

    T minBy(Comparator<T> comparator);

    Collection<T> orderBy(Comparator<T> comparator);

    Collection<T> orderDecrescentBy(Comparator<T> comparator);

    T reduce(BinaryOperator<T> accumulator);

    T reduceReverse(BinaryOperator<T> accumulator);

    Collection<T> take(int n);

    Collection<T> takeLast(int n);

    <X> Collection<Pair<T,X>> zipWith(Collection<X> other);

    Collection<T> reverse();

}
