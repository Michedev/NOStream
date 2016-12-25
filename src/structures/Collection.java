package structures;

import java.util.function.Consumer;

import operations.Operation;

/**
 * Created by mikedev on 01/08/16.
 */
public interface Collection<T> extends java.util.Collection<T>, Operation<T> {
	@Override
	void forEach(Consumer<? super T> consumer);
}
