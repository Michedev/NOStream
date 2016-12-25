package structures.lazy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import structures.ArrayList;
import structures.Collection;
import structures.List;
import utils.Pair;

public interface LazyList<T> extends LazyCollection<T>, List<T> {
	
	@Override
	<R> List<R> executeActions();
	
	@Override
	LazyList<T> distinct();

	@Override
	LazyList<T> filter(Predicate<T> predicate);

	@Override
	LazyList<T> filterIndexed(BiPredicate<T, Integer> predicate);

	@Override
	<E> Map<E, ArrayList<T>> groupBy(Function<T, E> thisFuct);

	@Override
	LazyList<T> intersection(java.util.Collection<T> LazyList);

	@Override
	<R> LazyList<R> map(Function<T, R> mapper);

	@Override
	<R> LazyList<R> mapIndexed(BiFunction<T, Integer, R> mapper);

	@Override
	LazyList<T> orderBy(Comparator<T> comparator);

	@Override
	LazyList<T> orderDecreasingBy(Comparator<T> comparator);

	@Override
	LazyList<T> reverse();

	@Override
	LazyList<T> take(int n);

	@Override
	LazyList<T> takeLast(int n);

	@Override
	LazyList<T> union(java.util.Collection<T> LazyList);

	@Override
	LazyList<Pair<T, Integer>> zipIndexed();

	@Override
	<X> LazyList<Pair<T, X>> zipWith(java.util.Collection<X> other);

	@Override
	LazyList<T> filterNotNull();

}
