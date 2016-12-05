package structures;

import utils.Pair;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 13/07/16.
 */
public interface List<T> extends java.util.List<T>, Collection<T> {
/*

    @Override
    default List<T> distinct() {
        return ((List<T>) Collection.super.distinct());
    }

    @Override
    default List<T> filter(Predicate<T> predicate) {
        return ((List<T>) Collection.super.filter(predicate));
    }
    
    @Override
    default List<T> filterIndexed(BiPredicate<T, Integer> predicate) {
        return  ((List<T>) Collection.super.filterIndexed(predicate));
    }

    @Override
    default List<T> filterNotNull() {
        return (List<T>) Collection.super.filterNotNull();
    }

    @Override
    default List<T> intersection(java.util.Collection<T> collection) {
        return ((List<T>) Collection.super.intersection(collection));
    }

    @Override
    default  <R> List<R> map(Function<T, R> mapper) {
        return ((List<R>) Collection.super.map(mapper));
    }

    @Override
    default <R>  List<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
        return ((List<R>) Collection.super.mapIndexed(mapper));
    }

    @Override
    default List<T> orderBy(Comparator<T> comparator) {
        return ((List<T>) Collection.super.orderBy(comparator));
    }

    @Override
    default List<T> orderDecreasingBy(Comparator<T> comparator) {
        return ((List<T>) Collection.super.orderDecreasingBy(comparator));
    }

    @Override
    default List<T> reverse() {
        return ((List<T>) Collection.super.reverse());
    }

    @Override
    default List<T> take(int n) {
        return ((List<T>) Collection.super.take(n));
    }

    @Override
    default List<T> takeLast(int n) {
        return ((List<T>) Collection.super.takeLast(n));
    }

    @Override
    default List<T> union(java.util.Collection<T> collection) {
        return ((List<T>) Collection.super.union(collection));
    }

    @Override
    default List<Pair<T, Integer>> zipIndexed() {
        return ((List<Pair<T, Integer>>) Collection.super.zipIndexed());
    }

    @Override
    default <X> List<Pair<T, X>> zipWith(Collection<X> other) {
        return ((List<Pair<T, X>>) Collection.super.zipWith(other));
    }*/
}
