package factory;

import structures.List;

public interface ListFactory{
	<R> List<R> buildList();
}


