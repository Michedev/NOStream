import Functions.Modifier;
import Functions.ModifierIndexed;

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

    void forEach(Modifier<T> alterator);

    void forEachIndexed(ModifierIndexed<T> mI);

    void forEachReverse(Modifier<T> mod);

    <R> Collection<R> map(Function<T, R> mapper);

    <R> Collection<R> mapIndexed(BiFunction<Integer, T, R> mapper);

    Collection<T> filter(Predicate<T> predicate);

    Collection<T> filterIndexed(BiPredicate<Integer, T> predicate);

    T reduce(BinaryOperator<T> accumulator);

    T reduceReverse(BinaryOperator<T> accumulator);

    T maxBy(Comparator<T> comparator);

    T minBy(Comparator<T> comparator);

    <E> Map<E, ArrayList2<T>> groupBy(Function<T, E> thisFuct);

    int count(Predicate<T> fCounter);

    Collection<T> orderBy(Comparator<T> comparator);

    Collection<T> orderDecrescentBy(Comparator<T> comparator);

    T last();

    <X> Collection<Pair<T,X>> zipWith(Collection<X> other);

    Collection<T> distinct();
}
