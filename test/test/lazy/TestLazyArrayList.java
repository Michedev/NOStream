package test.lazy;

import static org.junit.Assert.*;

import structures.lazy.LazyArrayList;
import structures.lazy.LazyList;

public class TestLazyArrayList extends TestLazyList {

	@Override
	public LazyList<Integer> makeLazyList() {
		return new LazyArrayList<>();
	}

}
