/**
 * Created by mikedev on 01/08/16.
 */
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executors;

import test_classes.Point;

import static org.junit.Assert.*;



public class TestArrayList2 {

    private ArrayList2<Integer> l1;
    private ArrayList2<Integer> l2;

    @Before
    public void inizialize()
    {
        l1 = new ArrayList2<>();
        l1.add(3);
        l1.add(4);
        l1.add(1);
        l1.add(43);
        l1.add(41);
        l1.add(39);

        l2 = new ArrayList2<>();
        Collections.addAll(l2,54,11,3,4,5,14,31,53,43,90,67);
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
    public void testBubbleSort1()
    {
        ArrayList2<Integer> ordList = Sorting.bubbleSort(l1, Integer::compare);
        assertEquals(ordList.last().intValue(), 43);
        assertEquals(ordList.get(0).intValue(), 1);
        assertEquals(ordList.get(1).intValue(), 3);
        assertEquals(ordList.get(2).intValue(), 4);
    }

    @Test
    public void testReverseBubbleSort1()
    {
        assertEquals(Sorting.bubbleSortDecrescent(l1, Integer::compare).get(0).intValue(), 43);
    }

    @Test
    public void testOrderBy1()
    {
        assertEquals(l2.orderBy(Integer::compare).get(0).intValue(), 3);
    }

    @Test
    public void testOrderDecrescentBy1()
    {
        assertEquals(l2.orderDecrescentBy(Integer::compare).get(0).intValue(), 90);
    }


}
