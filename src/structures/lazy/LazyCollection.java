package structures.lazy;

import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import functions.Function2;
import structures.ArrayList;
import structures.Collection;

public interface LazyCollection<T> extends Collection<T> {
	
	
	<R> Collection<R> executeTasks();
/*	
	@Override
	default LazyCollection<T> filter(Predicate<T> predicate) {
		Collection<T> newCollection = makeCollection();
		forEach(e ->
		{
			if (predicate.test(e)) {
				newCollection.add(e);
			}
		});
		return newCollection;
	}
	
    @Override
    default <R> LazyCollection<R> map(Function<T, R> mapper) {
        getActions().add((i, o) -> {
        return (LazyCollection<R>) this;
    }
    
    

    @Override
    default T reduce(BinaryOperator<T> accumulator) {
        final T[] result = (T[]) new Object[]{null};
        final boolean[] first = {true};
        forEach(e ->
        {
            result[0] = first[0] ? e : accumulator.apply(result[0], e);
            first[0] = false;
        });
        return result[0];
    }*/


	
}
