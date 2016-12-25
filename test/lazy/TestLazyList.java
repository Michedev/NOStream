package lazy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import structures.ArrayList;
import structures.List;
import structures.List;
import structures.lazy.LazyList;
import structures.lazy.LazyList;
import useful_class_for_test.Point;
import utils.Pair;

public abstract class TestLazyList {

	private LazyList<Integer> lazyList;

	@Before
	public void setUp() {
		lazyList = makeLazyList();
		Collections.addAll(lazyList, 1, 1, 14, 98, 5, 23);
	}

	public abstract LazyList<Integer> makeLazyList();

	@Test
	public void testNoActions() {
		List<Integer> result = lazyList.executeActions();
		assertEquals(6, result.size());
		assertArrayEquals(lazyList.toArray(), result.toArray());
	}

	@Test
	public void testBaseFilter() {
		List<Integer> result = lazyList.filter(x -> x > 3).executeActions();
		assertEquals(4, result.size());
	}

	@Test
	public void testConcatenateFilter() {
		LazyList<Integer> result = lazyList.filter(x -> x > 1).filter(x -> x > 5);
		assertEquals(6, result.size());
		assertEquals(3, result.executeActions().size());
	}

	@Test
	public void testIndipendentRunsWithMultipleFilter() {
		LazyList<Integer> result = lazyList.filter(x -> x > 1).filter(x -> x > 5);
		result.executeActions();
		result.executeActions();
		result.executeActions();
		assertEquals(3, result.executeActions().size());
	}

	@Test
	public void testBaseMap() {
		List<Double> result = lazyList.map(x -> x * 3.5).executeActions();
		assertEquals(3.5, result.get(0), 0.01);
	}

	@Test
	public void testMapFilter() {
		LazyList<Double> mapResult = lazyList.map(x -> x / 2.0);
		assertEquals(2, mapResult.filter(x -> x < 1).executeActions().size());
	}

	@Test
	public void testBaseReduce() {
		int result = lazyList.reduce((a, b) -> a + b);
		assertEquals(142, result);
	}

	@Test
	public void testMapFilterReduce() {
		Point result = lazyList.map(e -> new Point(e, e + 1)).filter(x -> x.getX() / x.getY() < 1)
				.reduce((p1, p2) -> new Point(p1.getX() + p1.getY(), p2.getX() + p2.getY()));
		assertEquals(290, result.getX() + result.getY(), 0.01);
	}

	@Test
	public void testCount() {
		assertEquals(2, lazyList.count(x -> x < 3));
	}

	@Test
	public void testFilterAndCount() {
		assertEquals(2, lazyList.filter(x -> x < 200).count(x -> x < 3));
	}

	@Test
	public void testDistinct() {
		List<Integer> strictList = lazyList.distinct().executeActions();
		assertEquals(strictList.toString(), 5, strictList.size());
	}

	@Test
	public void testZipMapGroupBy() {
		List<Double> secondList = new ArrayList<>();
		Collections.addAll(secondList, 4.6, 3.2, 5.3, 3.5, 4.5, 43.3);
		Map<Integer, List<Pair<Integer, Double>>> map = lazyList.zipWith(secondList)
				.map(pair -> new Pair<Integer, Double>(pair.first, pair.second - 3)).groupBy(pair -> pair.first);
		List<Pair<Integer, Double>> oneList = map.get(1);
		assertEquals(2, oneList.size());
		assertEquals(1.6, oneList.get(0).second, 0.01);
		assertEquals(0.2, oneList.get(1).second, 0.01);
	}


}