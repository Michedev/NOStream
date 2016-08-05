package Structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.*;

/**
 * Created by mikedev on 31/07/16.
 */
public class ArrayList<T> extends java.util.ArrayList<T> implements List<T>
{
    @Override
    public ArrayList<T> getCollection() {
        return this;
    }

    @Override
    public Iterator<T> iterator()
    {
        return super.iterator();
    }
    @Override
    public <S> ArrayList<S> makeCollection() {
        return new ArrayList<S>();
    }


    @Override
    public ArrayList<T> filter(Predicate<T> predicate) {
        return ((ArrayList<T>) List.super.filter(predicate));
    }

    @Override
    public ArrayList<T> filterIndexed(BiPredicate<Integer, T> predicate) {
        return ((ArrayList<T>) List.super.filterIndexed(predicate));
    }


    @Override
    public ArrayList<T> orderBy(Comparator<T> comparator) {
        return ((ArrayList<T>) List.super.orderBy(comparator));
    }

    @Override
    public ArrayList<T> orderDecrescentBy(Comparator<T> comparator) {
        return ((ArrayList<T>) List.super.orderDecrescentBy(comparator));
    }


    @Override
    public <R> ArrayList<R> map(Function<T, R> mapper) {
        return ((ArrayList<R>) List.super.map(mapper));
    }

    @Override
    public <R> ArrayList<R> mapIndexed(BiFunction<Integer, T, R> mapper) {
        return ((ArrayList<R>) List.super.mapIndexed(mapper));
    }

    @Override
    public boolean isParallel()
    {
        return false;
    }

    @Override
    public void toggleParallel()
    {
        throw new RuntimeException("Method not implemented");
    }


}
