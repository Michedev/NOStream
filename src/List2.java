import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 13/07/16.
 */
public interface List2<T> extends List<T>, ListOperation<T> {


    @Override
    default  <R> List2<R> map(Function<T, R> mapper) {
        return ((List2<R>) ListOperation.super.map(mapper));
    }

    @Override
    default List2<T> filter(Predicate<T> predicate) {
        return ((List2<T>) ListOperation.super.filter(predicate));
    }
}
