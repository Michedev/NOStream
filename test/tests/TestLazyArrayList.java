package tests;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import structures.ArrayList;
import structures.lazy.LazyArrayList;
import test_classes.Point;

public class TestLazyArrayList {
	
	private LazyArrayList<Integer> lazyArrayList;
	
	@Before
	public void setUp() {
		lazyArrayList = new LazyArrayList<>();
		Collections.addAll(lazyArrayList, 1,1,14,98,5,23);
	}
	
	@Test
	public void testBaseFilter() {
		ArrayList<Integer> result = lazyArrayList.filter(x -> x > 3).executeTasks();
		assertEquals(4, result.size());
	}


	
	@Test
	public void testConcatenateFilter(){
		LazyArrayList<Integer> result = lazyArrayList.filter(x -> x > 1).filter(x -> x > 5);
		assertEquals(6, result.size());
		assertEquals(3, result.executeTasks().size());
	}
	
	@Test
	public void testBaseMap(){
		ArrayList<Double> result = lazyArrayList.map(x -> x * 3.5).executeTasks();
		assertEquals(3.5, result.get(0), 0.01);
	}
	
	@Test
	public void testMapFilter(){
		LazyArrayList<Double> mapResult = lazyArrayList.map(x -> x / 2.0);
		assertEquals(2, mapResult.filter(x -> x < 1).executeTasks().size());
	}
	
	@Test
	public void testBaseReduce(){
		int result = lazyArrayList.reduce((a,b) -> a+b);
		assertEquals(142, result);
	}
	
	@Test
	public void testMapFilterReduce(){
		Point result = lazyArrayList.map(e -> new Point(e, e+1))
								.filter(x -> x.getX() / x.getY() < 1)
								.reduce((p1, p2) -> new Point(p1.getX() + p1.getY(), p2.getX() + p2.getY()));
		assertEquals(290, result.getX() + result.getY(), 0.01);
	}

}
