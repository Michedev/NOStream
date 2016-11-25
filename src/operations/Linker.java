package operations;

import structures.Collection;

public interface Linker<T> {
	Collection<T> getInput();
	<S> Collection<S> getOutput();
}
