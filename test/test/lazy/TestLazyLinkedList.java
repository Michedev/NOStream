package test.lazy;

import structures.lazy.LazyLinkedList;
import structures.lazy.LazyList;

public class TestLazyLinkedList extends TestLazyList {

	@Override
	public LazyList<Integer> makeLazyList() {
		return new LazyLinkedList<>();
	}

}
