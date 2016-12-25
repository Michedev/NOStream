package list;
import static org.junit.Assert.*;

import structures.ArrayList;
import structures.List;



public class TestArrayList extends TestList {

	@Override
	public List<Integer> makeList() {
		return new ArrayList<Integer>();
	}
}
