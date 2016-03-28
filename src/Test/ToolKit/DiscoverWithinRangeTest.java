package Test.ToolKit;

import ToolKit.DiscoverWithinRange;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by yuwang on 2016-03-27.
 */
public class DiscoverWithinRangeTest {


    @Test
    public void testParseIPRange() throws Exception {
        DiscoverWithinRange discoverWithinRange = new DiscoverWithinRange("192.168.1.15", "192.168.1.25", 1, 1000);
        ArrayList<String> arrayList = discoverWithinRange.parseAddressList();
        Assert.assertEquals(11, arrayList.size());
        Assert.assertEquals("192.168.1.25", arrayList.get(arrayList.size() - 1));

    }

    @Test
    public void testParseIPRangeStartFromZero() throws Exception {
        DiscoverWithinRange discoverWithinRange = new DiscoverWithinRange("192.168.1.0", "192.168.1.25", 1, 1000);
        ArrayList<String> arrayList = discoverWithinRange.parseAddressList();
        Assert.assertEquals(26, arrayList.size());
        Assert.assertEquals("192.168.1.25", arrayList.get(arrayList.size() - 1));
        Assert.assertEquals("192.168.1.0", arrayList.get(0));

    }

    @Test
    public void testParseIPRangeStartFromZeroTo255() throws Exception {
        DiscoverWithinRange discoverWithinRange = new DiscoverWithinRange("192.168.1.0", "192.168.1.255", 1, 1000);
        ArrayList<String> arrayList = discoverWithinRange.parseAddressList();
        Assert.assertEquals(256, arrayList.size());
        Assert.assertEquals("192.168.1.255", arrayList.get(arrayList.size() - 1));
        Assert.assertEquals("192.168.1.0", arrayList.get(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseIPRangeWithLetter() throws Exception {
        DiscoverWithinRange discoverWithinRange = new DiscoverWithinRange("192.168.1.a", "192.168.1.10", 1, 1000);
        ArrayList<String> arrayList = discoverWithinRange.parseAddressList();

    }

    @Test
    public void testObtainReachableAddress() {
        DiscoverWithinRange discoverWithinRange = new DiscoverWithinRange("69.70.200.230", "69.70.200.235", 2, 1000);
        ArrayList<String> arrayList = discoverWithinRange.parseAddressList();
        for (String entry : arrayList) {
            System.out.println(entry);
        }
        ArrayList<String> arrayList2 = discoverWithinRange.obtainReachables();
        for (String entry : arrayList2) {
            System.out.println(entry);
        }
        Assert.assertEquals(4, arrayList2.size());

    }




}