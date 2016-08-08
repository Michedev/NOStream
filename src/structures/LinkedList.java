package structures;

import utils.Pair;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 08/08/16.
 */
public class LinkedList<T> extends java.util.LinkedList<T> implements List<T> {
    @Override
    public LinkedList<T> distinct() {
        return ((LinkedList<T>) List.super.distinct());
    }

    @Override
    public LinkedList<T> filter(Predicate<T> predicate) {
        return ((LinkedList<T>) List.super.filter(predicate));
    }

    @Override
    public LinkedList<T> filterIndexed(BiPredicate<T, Integer> predicate) {
        return ((LinkedList<T>) List.super.filterIndexed(predicate));
    }

    @Override
    public LinkedList<T> intersection(java.util.Collection<T> collection) {
        return ((LinkedList<T>) List.super.intersection(collection));
    }

    @Override
    public <S> LinkedList<S> makeCollection() {
        return new LinkedList<S>();
    }

    @Override
    public <R> LinkedList<R> map(Function<T, R> mapper) {
        return ((LinkedList<R>) List.super.map(mapper));
    }

    @Override
    public <R> LinkedList<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
        return ((LinkedList<R>) List.super.mapIndexed(mapper));
    }

    @Override
    public LinkedList<T> orderBy(Comparator<T> comparator) {
        return ((LinkedList<T>) List.super.orderBy(comparator));
    }

    @Override
    public LinkedList<T> orderDecrescentBy(Comparator<T> comparator) {
        return ((LinkedList<T>) List.super.orderDecrescentBy(comparator));
    }

    @Override
    public LinkedList<T> reverse() {
        return ((LinkedList<T>) List.super.reverse());
    }

    @Override
    public LinkedList<T> take(int n) {
        return ((LinkedList<T>) List.super.take(n));
    }

    @Override
    public LinkedList<T> takeLast(int n) {
        return ((LinkedList<T>) List.super.takeLast(n));
    }

    @Override
    public LinkedList<T> union(java.util.Collection<T> collection) {
        return ((LinkedList<T>) List.super.union(collection));
    }

    @Override
    public LinkedList<Pair<T, Integer>> zipIndexed() {
        return (LinkedList<Pair<T, Integer>>) List.super.zipIndexed();
    }

    @Override
    public <X> LinkedList<Pair<T, X>> zipWith(Collection<X> other) {
        return (LinkedList<Pair<T, X>>) List.super.zipWith(other);
    }

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        String print = "[";
        while(it.hasNext()) {
            print += it.next()+ (it.hasNext()? ", ": " ]");
        }
        return print;
    }
}