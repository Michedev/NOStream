package tests;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import structures.ArrayList;
import structures.lazy.LazyArrayList;

public class TestLazyArrayList {
	
	private LazyArrayList<Integer> testArrayList;
	
	@Before
	public void setUp() {
		testArrayList = new LazyArrayList<>();
		Collections.addAll(testArrayList, 1,1,14,98,5,23);
	}
	
	@Test
	public void testBaseFilter() {
		ArrayList<Integer> result = testArrayList.filter(x -> x > 3).executeTasks();
		assertEquals(4, result.size());
	}


	
	@Test
	public void testConcatenateFilter(){
		LazyArrayList<Integer> result = testArrayList.filter(x -> x > 1).filter(x -> x > 5);
		assertEquals(6, result.size());
		assertEquals(3, result.executeTasks().size());
	}
	
	@Test
	public void testBaseMap(){
		ArrayList<Double> result = testArrayList.map(x -> x * 3.5).executeTasks();
		assertEquals(3.5, result.get(0), 0.01);
	}
	
	@Test
	public void testMapFilter(){
		LazyArrayList<Double> mapResult = testArrayList.map(x -> x / 2.0);
		assertEquals(2, mapResult.filter(x -> x < 1).executeTasks().size());
	}

}
