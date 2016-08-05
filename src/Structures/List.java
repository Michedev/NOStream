package Structures;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 13/07/16.
 */
public interface List<T> extends java.util.List<T>, Collection<T> {


    @Override
    default  <R> List<R> map(Function<T, R> mapper) {
        return ((List<R>) Collection.super.map(mapper));
    }

    @Override
    default List<T> filter(Predicate<T> predicate) {
        return ((List<T>) Collection.super.filter(predicate));
    }
}
