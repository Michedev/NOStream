package operations;

import structures.Collection;

public interface Linker<T> {
	Collection<T> getInput();
    <R> Collection<R> getOutput();
}
