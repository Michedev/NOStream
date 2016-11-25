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
	
	Vector<Function2<Collection<?>, Collection<?>, Void>> getActions();
	
	default <R> Collection<R> executeTasks(){
		Collection<T> tmpInput = makeCollection();
		this.iterator().forEachRemaining(x -> tmpInput.add(x));
		Collection<?> input = tmpInput;
		Collection<?> output = makeCollection();
		
		for(Function2<Collection<?>, Collection<?>, Void> task : getActions())
		{
			task.apply(input, output);
			Collection<?> tmp = input;
			input = output;
			output = tmp;
			output.clear();
		}
		
		return (Collection<R>) output;
	}
	
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
    }


	
}
