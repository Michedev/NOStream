import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.*;

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
    public Iterator<T> getIterator()
    {
        return iterator();
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
    public ArrayList2<T> filterIndexed(BiPredicate<Integer, T> predicate) {
        return ((ArrayList2<T>) List2.super.filterIndexed(predicate));
    }
    

    @Override
    public ArrayList2<T> orderBy(Comparator<T> comparator) {
        return ((ArrayList2<T>) List2.super.orderBy(comparator));
    }

    @Override
    public ArrayList2<T> orderDecrescentBy(Comparator<T> comparator) {
        return ((ArrayList2<T>) List2.super.orderDecrescentBy(comparator));
    }


    @Override
    public <R> ArrayList2<R> map(Function<T, R> mapper) {
        return ((ArrayList2<R>) List2.super.map(mapper));
    }

    @Override
    public <R> ArrayList2<R> mapIndexed(BiFunction<Integer, T, R> mapper) {
        return ((ArrayList2<R>) List2.super.mapIndexed(mapper));
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
