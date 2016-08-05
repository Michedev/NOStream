package Structures;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 13/07/16.
 */
public interface List2<T> extends List<T>, Collection2<T> {


    @Override
    default  <R> List2<R> map(Function<T, R> mapper) {
        return ((List2<R>) Collection2.super.map(mapper));
    }

    @Override
    default List2<T> filter(Predicate<T> predicate) {
        return ((List2<T>) Collection2.super.filter(predicate));
    }
}
