package Test.Snmp;

import org.junit.Assert;
import snmp.SnmpBasicOperaton;

import java.util.HashMap;


/**
 * Created by yuwang on 12/12/15.
 * Unit test class for
 */
public class SnmpBasicOperationTest {

    @org.junit.Test
    public void testSnmpGet() throws Exception {
        System.out.println("Testing EG1550 device SNMPGET");
        SnmpBasicOperaton sc_1550 = new SnmpBasicOperaton("69.70.200.246", "public", 161, "1", 2, 2000);
        System.out.println(".1.3.6.1.2.1.1.1.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.1.0").toString());
        System.out.println(".1.3.6.1.2.1.1.2.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.2.0").toString());
        System.out.println(".1.3.6.1.2.1.1.3.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.3.0").toString());
        System.out.println(".1.3.6.1.2.1.1.4.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.4.0").toString());
        System.out.println(".1.3.6.1.2.1.1.5.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.5.0").toString());
        System.out.println(".1.3.6.1.2.1.1.6.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.6.0").toString());
        System.out.println(".1.3.6.1.2.1.1.7.0: " + sc_1550.snmpGet(".1.3.6.1.2.1.1.7.0").toString());
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.1.0"));
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.2.0"));
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.3.0"));
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.4.0"));
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.5.0"));
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.6.0"));
        Assert.assertNotNull("", sc_1550.snmpGet(".1.3.6.1.2.1.1.7.0"));

        System.out.println("Testing EGFA device SNMPGET");
        SnmpBasicOperaton sc_egfa = new SnmpBasicOperaton("69.70.200.232", "PUBLIC", 161, "1", 2, 2000);
        System.out.println(".1.3.6.1.2.1.1.1.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.1.0").toString());
        System.out.println(".1.3.6.1.2.1.1.2.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.2.0").toString());
        System.out.println(".1.3.6.1.2.1.1.3.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.3.0").toString());
        System.out.println(".1.3.6.1.2.1.1.4.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.4.0").toString());
        System.out.println(".1.3.6.1.2.1.1.5.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.5.0").toString());
        System.out.println(".1.3.6.1.2.1.1.6.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.6.0").toString());
        System.out.println(".1.3.6.1.2.1.1.7.0: " + sc_egfa.snmpGet(".1.3.6.1.2.1.1.7.0").toString());
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.1.0"));
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.2.0"));
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.3.0"));
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.4.0"));
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.5.0"));
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.6.0"));
        Assert.assertNotNull("", sc_egfa.snmpGet(".1.3.6.1.2.1.1.7.0"));

        System.out.println("Testing ELink1550 device SNMPGET");
        SnmpBasicOperaton sc_elink = new SnmpBasicOperaton("69.70.200.249", "public", 161, "2c", 2, 2000);
        System.out.println(".1.3.6.1.2.1.1.1.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.1.0").toString());
        System.out.println(".1.3.6.1.2.1.1.2.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.2.0").toString());
        System.out.println(".1.3.6.1.2.1.1.3.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.3.0").toString());
        System.out.println(".1.3.6.1.2.1.1.4.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.4.0").toString());
        System.out.println(".1.3.6.1.2.1.1.5.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.5.0").toString());
        System.out.println(".1.3.6.1.2.1.1.6.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.6.0").toString());
        System.out.println(".1.3.6.1.2.1.1.7.0: " + sc_elink.snmpGet(".1.3.6.1.2.1.1.7.0").toString());
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.1.0"));
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.2.0"));
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.3.0"));
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.4.0"));
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.5.0"));
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.6.0"));
        Assert.assertNotNull("", sc_elink.snmpGet(".1.3.6.1.2.1.1.7.0"));
    }


    @org.junit.Test
    public void testSnmpGetWithWrongOID() throws Exception {
        System.out.println("Testing EG1550 device SNMPGET with wrong OID");
        SnmpBasicOperaton sc_elink = new SnmpBasicOperaton("69.70.200.249", "public", 161, "1", 2, 2000);
        Assert.assertEquals("___NONE___", sc_elink.snmpGet(".1.3.6.1.2.1.1.9.0"));

    }


    @org.junit.Test
    public void testSnmpSet() throws Exception {
        System.out.println("Testing EG1550 device SNMPSET");
        SnmpBasicOperaton sc_1550_set = new SnmpBasicOperaton("69.70.200.246", "public", 161, "1", 2, 2000);
        sc_1550_set.snmpSet(".1.3.6.1.2.1.1.4.0", "testing_snmpset@electroline.com");
        Assert.assertEquals(sc_1550_set.snmpGet(".1.3.6.1.2.1.1.4.0"), "testing_snmpset@electroline.com");
        Assert.assertTrue(sc_1550_set.snmpSet(".1.3.6.1.2.1.1.4.0", "testing_snmpset@electroline.com"));

        System.out.println("Testing EGFA device SNMPSET");
        SnmpBasicOperaton sc_egfa_set = new SnmpBasicOperaton("69.70.200.232", "PUBLIC", 161, "1", 2, 2000);
        sc_egfa_set.snmpSet(".1.3.6.1.2.1.1.4.0", "testing_snmpset@electroline.com");
        Assert.assertEquals(sc_egfa_set.snmpGet(".1.3.6.1.2.1.1.4.0"), "testing_snmpset@electroline.com");
        Assert.assertTrue(sc_egfa_set.snmpSet(".1.3.6.1.2.1.1.4.0", "testing_snmpset@electroline.com"));


        System.out.println("Testing ELink device SNMPSET");
        SnmpBasicOperaton sc_elink_set = new SnmpBasicOperaton("69.70.200.249", "public", 161, "2c", 2, 2000);
        sc_elink_set.snmpSet(".1.3.6.1.2.1.1.4.0", "testing_snmpset@electroline.com");
        sc_elink_set.snmpSet(".1.3.6.1.2.1.1.5.0", "testing_snmpJava");
        Assert.assertEquals(sc_elink_set.snmpGet(".1.3.6.1.2.1.1.5.0"), "testing_snmpJava");
        Assert.assertTrue(sc_elink_set.snmpSet(".1.3.6.1.2.1.1.4.0", "testing_snmpset@electroline.com"));
    }

    @org.junit.Test
    public void testSnmpSetWithNoneSetableOID() throws Exception {
        System.out.println("Testing ELink device SNMPSET");
        SnmpBasicOperaton sc_elink_set = new SnmpBasicOperaton("69.70.200.249", "public", 161, "2c", 2, 2000);
        Assert.assertFalse(sc_elink_set.snmpSet(".1.3.6.1.2.1.1.8.0", "testing"));


    }


    @org.junit.Test
    public void testSnmpGetNext() throws Exception {
        System.out.println("Testing EG1550 device SNMPGETNEXT");
        SnmpBasicOperaton sc_1550_getNext = new SnmpBasicOperaton("69.70.200.246", "public", 161, "1", 2, 2000);
        Assert.assertEquals(sc_1550_getNext.snmpGet("1.3.6.1.4.1.33826.3.1.1.0"),
                sc_1550_getNext.snmpGetNext(".1.3.6.1.4.1.4885.2.1.3.1.1.1.0")[1]);

        System.out.println("Testing Elink device SNMPGETNEXT");
        SnmpBasicOperaton sc_elink_getNext = new SnmpBasicOperaton("69.70.200.249", "public", 161, "1", 2, 2000);

        Assert.assertEquals(".1.3.6.1.2.1.1.5.0", sc_elink_getNext.snmpGetNext(".1.3.6.1.2.1.1.4.0")[0]);
        Assert.assertEquals(sc_elink_getNext.snmpGet(".1.3.6.1.2.1.1.5.0"), sc_elink_getNext.snmpGetNext(".1.3.6.1.2.1.1.4.0")[1]);
        Assert.assertEquals(".1.3.6.1.4.1.4885.2.1.3.1.1.1.0", sc_elink_getNext.snmpGetNext(".1.3.6.1.2.1.1.8.0")[0]);
        Assert.assertEquals(sc_elink_getNext.snmpGet(".1.3.6.1.4.1.4885.2.1.3.1.1.1.0"), sc_elink_getNext.snmpGetNext(".1.3.6.1.2.1.1.8.0")[1]);
        Assert.assertEquals(".1.3.6.1.4.1.5591.1.1.1.1.2.6.0", sc_elink_getNext.snmpGetNext(".1.3.6.1.4.1.4885.2.1.12.2.0")[0]);
        Assert.assertEquals(sc_elink_getNext.snmpGet(".1.3.6.1.4.1.5591.1.1.1.1.2.6.0"), sc_elink_getNext.snmpGetNext(".1.3.6.1.4.1.4885.2.1.12.2.0")[1]);

    }


    @org.junit.Test
    public void testSnmpWalk() throws Exception {
        System.out.println("Testing Elink device SNMPWALK");
        SnmpBasicOperaton sc_elink_walk = new SnmpBasicOperaton("69.70.200.249", "public", 161, "2c", 1, 1000);
        HashMap<String, String> hashMap = sc_elink_walk.snmpWalk();
        Assert.assertEquals("1", hashMap.get(".1.3.6.1.4.1.5591.1.11.2.3.1.1.2.1.3.8.0"));

    }


}

