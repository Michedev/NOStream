package factory;

import structures.ArrayList;
import structures.List;

public class ArrayListFactory implements ListFactory{

	@Override
	public <R> List<R> buildList() {
		return new ArrayList<>();
	}

}
