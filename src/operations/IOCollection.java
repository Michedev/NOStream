package operations;

import structures.Collection;

public interface IOCollection<T> {
	Collection<T> getInput();
    <R> Collection<R> getOutput();
}
