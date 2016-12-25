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
	
	
	<R> Collection<R> executeActions();
	

	
}
