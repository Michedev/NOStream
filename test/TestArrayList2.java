/**
 * Created by mikedev on 01/08/16.
 */
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;

import static org.junit.Assert.*;



public class TestArrayList2 {

    private ArrayList2<Integer> l1;

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

}
