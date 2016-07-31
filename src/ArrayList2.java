import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mikedev on 31/07/16.
 */
public class ArrayList2<T> extends ArrayList<T> implements  List2<T>
{
    @Override
    public ArrayList2<T> getCollection() {
        return this;
    }

    @Override
    public <S> ArrayList2<S> makeCollection() {
        return new ArrayList2<S>();
    }


    @Override
    public ArrayList2<T> filter(Predicate<T> predicate) {
        return ((ArrayList2<T>) List2.super.filter(predicate));
    }

    @Override
    public <R> ArrayList2<R> map(Function<T, R> mapper) {
        return ((ArrayList2<R>) List2.super.map(mapper));
    }
}
