package TimeTest;

import org.apache.commons.lang3.time.StopWatch;
import structures.ArrayList;
import structures.Collection;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import structures.List;
import test_classes.Point;
import utils.Pair;
import utils.Sorting;

import static org.junit.Assert.*;

/**
 * Created by mikedev on 28/08/16.
 */
public class TimeTest {


    public final int times = 10000000;
    private List<Pair<Long, Long>> list;
    private ArrayList<Integer> testList;


    private static void NOStreamTest(ArrayList<Integer> list) {
        List<Integer> newList = list.filter(x -> x > 5);
        newList.map(x -> new Pair<Double, Double>(x * 3.5, x * 1.5));
    }

    private static void streamTest(ArrayList<Integer> list) {
        List<Integer> newList1 = list.stream().filter(x -> x > 5).collect(Collectors.toCollection(ArrayList::new));
        newList1.stream().map(x -> new Pair<Double, Double>(x * 3.5, x * 1.5)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Before
    public void before() {
        testList = new ArrayList<>();
        Collections.addAll(testList, 12, 4, 8, 2, 21, 36, 10, 1, 4);
    }


   @Test
   public void testStream()
   {
       for(int i = 0; i < times; i++)
       {
           streamTest(testList);
       }
   }


    @Test
    public void testNOStream()
    {
        for(int i = 0; i < times; i++)
        {
            NOStreamTest(testList);
        }
    }

}
