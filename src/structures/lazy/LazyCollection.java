package structures.lazy;

import java.util.Iterator;
import java.util.Vector;

import functions.Function2;
import structures.ArrayList;
import structures.Collection;

public interface LazyCollection<T> extends Collection<T> {
	
	Vector<Function2> getActions();
	
	default <R> Collection<R> executeTasks(){
		Collection<T> input = makeCollection();
		this.iterator().forEachRemaining(x -> input.add(x));
		
		return null;
	}
	
}
