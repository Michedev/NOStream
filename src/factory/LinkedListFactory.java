package factory;

import structures.LinkedList;
import structures.List;

public class LinkedListFactory implements ListFactory {

	@Override
	public <R> List<R> buildList() {
		return new LinkedList<>();
	}

}
