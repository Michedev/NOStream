package structures;

import utils.Pair;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.*;

/**
 * Created by mikedev on 31/07/16.
 */
public class ArrayList<T> extends java.util.ArrayList<T> implements List<T> {
    @Override
    public ArrayList<T> distinct() {
        return ((ArrayList<T>) List.super.distinct());
    }

    @Override
    public ArrayList<T> filter(Predicate<T> predicate) {
        return ((ArrayList<T>) List.super.filter(predicate));
    }

    @Override
    public ArrayList<T> filterIndexed(BiPredicate<T, Integer> predicate) {
        return ((ArrayList<T>) List.super.filterIndexed(predicate));
    }

    @Override
    public ArrayList<T> filterNotNull() {
        return (ArrayList<T>) List.super.filterNotNull();
    }

    @Override
    public ArrayList<T> intersection(java.util.Collection<T> collection) {
        return ((ArrayList<T>) List.super.intersection(collection));
    }

    @Override
    public <S> ArrayList<S> makeCollection() {
        return new ArrayList<S>();
    }

    @Override
    public <R> ArrayList<R> map(Function<T, R> mapper) {
        return ((ArrayList<R>) List.super.map(mapper));
    }

    @Override
    public <R> ArrayList<R> mapIndexed(BiFunction<T, Integer, R> mapper) {
        return ((ArrayList<R>) List.super.mapIndexed(mapper));
    }

    @Override
    public ArrayList<T> orderBy(Comparator<T> comparator) {
        return ((ArrayList<T>) List.super.orderBy(comparator));
    }

    @Override
    public ArrayList<T> orderDecreasingBy(Comparator<T> comparator) {
        return ((ArrayList<T>) List.super.orderDecreasingBy(comparator));
    }

    @Override
    public ArrayList<T> reverse() {
        return ((ArrayList<T>) List.super.reverse());
    }

    @Override
    public ArrayList<T> take(int n) {
        return ((ArrayList<T>) List.super.take(n));
    }

    @Override
    public ArrayList<T> takeLast(int n) {
        return ((ArrayList<T>) List.super.takeLast(n));
    }

    @Override
    public ArrayList<T> union(java.util.Collection<T> collection) {
        return ((ArrayList<T>) List.super.union(collection));
    }

    @Override
    public ArrayList<Pair<T, Integer>> zipIndexed() {
        return (ArrayList<Pair<T, Integer>>) List.super.zipIndexed();
    }

    @Override
    public <X> ArrayList<Pair<T, X>> zipWith(Collection<X> other) {
        return (ArrayList<Pair<T, X>>) List.super.zipWith(other);
    }

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        String print = "[";
        while(it.hasNext())
        {
            print += it.next()+ (it.hasNext()? ", ": " ]");
        }
        return print;
    }
}
