/**
 * Created by mikedev on 01/08/16.
 */
import structures.ArrayList;
import structures.Collection;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.Map;
import structures.List;
import test_classes.Point;
import utils.Pair;
import utils.Sorting;

import static org.junit.Assert.*;



public class TestArrayList {

    private ArrayList<Integer> l1;
    private ArrayList<Integer> l2;

    @Before
    public void inizialize()
    {
        l1 = new ArrayList<>();
        l1.add(3);
        l1.add(4);
        l1.add(1);
        l1.add(43);
        l1.add(41);
        l1.add(39);

        l2 = new ArrayList<>();
        Collections.addAll(l2,54,11,3,4,5,14,31,53,43,90,67);
    }

    @Test
    public void testAll1()
    {
        assertTrue(l1.all(x -> x>0));
    }

    @Test
    public void testAll2()
    {
        assertFalse(l1.all(x -> x>15));
    }

    @Test
    public void testAny1()
    {
        assertTrue(l1.any(x -> x>15));
    }

    @Test
    public void testAny2()
    {
        assertFalse(l1.any(x -> x>165));
    }

    @Test
    public void testBubbleSort1()
    {
        ArrayList<Integer> ordList = Sorting.bubbleSort(l1, Integer::compare);
        assertEquals(ordList.lastOrNull().intValue(), 43);
        assertEquals(ordList.get(0).intValue(), 1);
        assertEquals(ordList.get(1).intValue(), 3);
        assertEquals(ordList.get(2).intValue(), 4);
    }

    @Test
    public void testCount1()
    {
        assertEquals(l1.count(x -> x%2 == 1), 5);
    }

    @Test
    public void testCount2()
    {
        assertEquals(l1.count(x -> x%2 == 0), 1);
    }

    @Test
    public void testCount3()
    {
        assertEquals(l2.count(x -> x%5 == 0),2);
    }

    @Test
    public void testDistinct1()
    {
        Point p1 = new Point(1,1);
        Point p2 = new Point(3,3);
        ArrayList<Point> a = new ArrayList<>();
        Collections.addAll(a, p1,p1,p2,p1,p2, new Point(1,1), p2, p1, new Point(3,3));
        assertEquals(a.distinct().size(), 2);
    }

    @Test
    public void testFilter1()
    {
        assertEquals(l1.filter(x -> x > 10).size(), 3);
    }

    @Test
    public void testFilter2()
    {
        assertEquals(l1.filter(x -> x > 10).filter(x -> x <= 10).size(), 0);
    }

    @Test
    public void testFilter3()
    {
        assertEquals(l1.filter(x -> true).get(0).intValue(), 3);
    }

    @Test
    public void testFilter4()
    {
        assertEquals(l1.filter(x -> x > 20).get(0).intValue(), 43);
    }

    @Test
    public void testGroupBy1()
    {
        Map<Integer, Collection<Integer>> map = l1.groupBy(x -> x % 5);
        assertEquals(map.get(1).size(), 2);
        assertTrue(map.get(1).contains(41));
    }

    @Test
    public void testGroupBy2()
    {
        Map<Point, Collection<Integer>> map = l1.groupBy(x -> new Point(x%3,x%3));
        assertEquals(map.get(new Point(0,0)).size(), 2);
        assertTrue(map.get(new Point(0,0)).contains(3) && map.get(new Point(0,0)).contains(39));
    }

    @Test
    public void testIntersection1()
    {
        Collection<Integer> intersection = l1.intersection(l2);
        assertEquals(intersection.size(), 3);
        assertTrue(intersection.contains(3));
        assertTrue(intersection.contains(43));
        assertTrue(intersection.contains(4));
    }

    @Test
    /**
     * test Identity function
     */
    public void testMap1()
    {
        assertEquals(l1.map(x -> x).get(0).intValue(), 3);
    }

    @Test
    public void testMap2()
    {
        assertEquals(l1.map(x -> x.floatValue() * 1.5).get(0).doubleValue(), 4.5, 0.001);
    }

    @Test
    public void testMap3()
    {
        assertEquals(l1.map(x -> new Point(x,x)).get(0), new Point(3,3));
    }

    @Test
    public void testMapIndexed1()
    {
        assertEquals(l1.mapIndexed((i,x) -> i+x).lastOrNull().intValue(), 44);
    }

    @Test
    public void testMaxBy1()
    {
        assertEquals(l1.maxBy(Integer::compare).intValue(), 43);
    }

    @Test
    public void testMinBy1()
    {
        assertEquals(l1.minBy(Integer::compare).intValue(), 1);
    }

    @Test
    public void testOrderBy1()
    {
        assertEquals(l2.orderBy(Integer::compare).get(0).intValue(), 3);
    }

    @Test
    public void testOrderDecrescentBy1()
    {
        assertEquals(l2.orderDecreasingBy(Integer::compare).get(0).intValue(), 90);
    }

    @Test
    public void testOrderDecrescentBy2()
    {
        assertEquals(l1.orderDecreasingBy(Integer::compare).get(0).intValue(), 43);
    }

    @Test
    public void testReduce1()
    {
        assertEquals(l1.reduce(Integer::sum).intValue(), 131);
    }

    @Test
    public void testReduce2()
    {
        assertEquals(l1.reduce((a,b) -> a - b).intValue(), -125);
    }

    @Test
    public void testReduceReverse1()
    {
        assertEquals(l1.reduceReverse(Integer::sum).intValue(), 131);
    }

    @Test
    public void testReduceReverse2()
    {
        assertEquals(l1.reduceReverse((a,b) -> a - b).intValue(), -53);
    }

    @Test
    public void testReverseBubbleSort1()
    {
        assertEquals(Sorting.bubbleSortDecrescent(l1, Integer::compare).get(0).intValue(), 43);
    }

    @Test
    public void testUnion1()
    {
        Collection<Integer> union = l1.union(l2);
        assertEquals(union.size(), l1.size()+l2.size());
        assertEquals(l1.first(), union.first());
        assertNotEquals(l1.last(), union.last());
        assertEquals(l2.last(), union.last());
    }

    @Test
    public void testZipWith1()
    {
        ArrayList<Pair<Integer, Integer>> res = l1.zipWith(l2);
        assertEquals(res.size(), l1.size());
    }

    @Test
    public void testMapFilterReverseReduce1(){

        List<Point> filteredList = l1
                                    .map(x -> new Point(x, x * 2))
                                    .filter(p -> p.getX() < 10);
                                        //X: 3,4,1
                                        //Y: 6,8,2
        Integer result = ((int) filteredList.reduce((p1, p2) -> new Point(p1.getY()+p2.getX(), p2.getX())).getX());
        assertEquals(result.intValue(), 5);
        int result2 = (int) filteredList.reduce((p1, p2) -> new Point(p1.getX()+p2.getX(), p2.getY())).getX();
        assertEquals(8, result2);
    }
}
