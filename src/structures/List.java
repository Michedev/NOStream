package structures;

import utils.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import functions.ConsumerIndexed;

/**
 * Created by mikedev on 13/07/16.
 */
public interface List<T> extends java.util.List<T>, Collection<T> {

	@Override
	List<T> distinct();
	@Override
	List<T> filter(Predicate<T> predicate);
	@Override
	 List<T> filterIndexed(BiPredicate<T, Integer> predicate) ;
	@Override
	 <E> Map<E, ArrayList<T>> groupBy(Function<T, E> thisFuct) ;
	@Override
	 List<T> intersection(java.util.Collection<T> List) ;
	@Override
	 <R> List<R> map(Function<T, R> mapper) ;
	@Override
	 <R> List<R> mapIndexed(BiFunction<T, Integer, R> mapper) ;
	@Override
	 List<T> orderBy(Comparator<T> comparator) ;
	@Override
	 List<T> orderDecreasingBy(Comparator<T> comparator) ;

	@Override
	 List<T> reverse() ;

	@Override
	 List<T> take(int n) ;

	@Override
	 List<T> takeLast(int n) ;

	@Override
	 List<T> union(java.util.Collection<T> List) ;


	@Override
	 List<Pair<T, Integer>> zipIndexed() ;


	@Override
	 <X> List<Pair<T, X>> zipWith(java.util.Collection<X> other) ;

	@Override
	 List<T> filterNotNull() ;

	
}
