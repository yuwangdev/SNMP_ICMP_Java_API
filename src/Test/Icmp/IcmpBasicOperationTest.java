package Test.Icmp;

import icmp.IcmpBasicOperation;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yuwang on 12/13/15.
 */
public class IcmpBasicOperationTest {

    @Test
    public void testIfReachableGood() throws Exception {


        Assert.assertTrue((new IcmpBasicOperation("8.8.8.8", 1000, 2)).ifReachable());


    }

    @Test
    public void testIfNotReachable() throws Exception {


        Assert.assertFalse((new IcmpBasicOperation("8.8.8.0", 1000, 2)).ifReachable());


    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfReachableWithZeroRetry() {

        (new IcmpBasicOperation("8.8.8.8", 1000, 0)).ifReachable();


    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfReachableWithWrongTimeOut() {

        (new IcmpBasicOperation("8.8.8.8", 100010, 1)).ifReachable();


    }

}