package snmp;

/**
 * Created by yuwang on 12/12/15.
 * the snmpget/walk/set basic operation api class
 * Support v1, v2c, and v3
 */

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.HashMap;


public class SnmpBasicOperaton {

    private String ipAddress;
    private String community;
    private String port;
    private int version; // should be 1, 2c, or 3
    private int retires;
    private int timeout;

    public SnmpBasicOperaton(String ip, String communityString, int port, String versionString, int retries, int timeout) throws IllegalArgumentException {
        this.ipAddress = ip;
        this.community = communityString;
        this.port = Integer.toString(port);

        if (!versionString.equalsIgnoreCase("1") && !versionString.equalsIgnoreCase("2c") && !versionString.equalsIgnoreCase("3")) {
            throw new IllegalArgumentException("The version string should be 1, 2c, or 3 in string type");
        }

        if (timeout >= 100000 || timeout < 500) {
            throw new IllegalArgumentException("Timeout is too small or too long");

        }
        switch (versionString) {
            case "1":
                this.version = SnmpConstants.version1;
                break;
            case "2c":
                this.version = SnmpConstants.version2c;
                break;
            case "3":
                this.version = SnmpConstants.version3;
                break;
            default:
                this.version = SnmpConstants.version1;
                break;
        }
        this.retires = retries;
        this.timeout = timeout;
    }

    public String snmpGet(String oid) throws IOException {

        String result = "___NONE___";

        // Create TransportMapping and Listen
        TransportMapping transport = new DefaultUdpTransportMapping();
        transport.listen();

        // Create Target Address object
        CommunityTarget comtarget = new CommunityTarget();
        comtarget.setCommunity(new OctetString(this.community));
        comtarget.setVersion(this.version);
        comtarget.setAddress(new UdpAddress(this.ipAddress + "/" + this.port));
        comtarget.setRetries(this.retires);
        comtarget.setTimeout(this.timeout);

        // Create the PDU object
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));

        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(transport);

        //System.out.println("Sending Request to Agent...");
        ResponseEvent response = snmp.get(pdu, comtarget);
        // Process Agent Response
        if (response != null) {
            //  System.out.println("Got Response from Agent");
            PDU responsePDU = response.getResponse();

            if (responsePDU != null) {
                int errorStatus = responsePDU.getErrorStatus();
                int errorIndex = responsePDU.getErrorIndex();
                String errorStatusText = responsePDU.getErrorStatusText();

                if (errorStatus == PDU.noError) {
                    result = responsePDU.getVariableBindings().firstElement().toString();
                } else {
                    System.out.println("Error: Request Failed");
                    System.out.println("Error Status = " + errorStatus);
                    System.out.println("Error Index = " + errorIndex);
                    System.out.println("Error Status Text = " + errorStatusText);
                }
            } else {
                System.out.println("Error: Response PDU is null");
            }
        } else {
            System.out.println("Error: Agent Timeout... ");
        }
        snmp.close();

        if (result.contains("=")) {

            result = result.substring(result.indexOf("=") + 1, result.length()).trim();


        }
        // System.out.println(result);   // for testing only, to pirnt out
        return result;

    }


    public Boolean snmpSet(String target_oid, String target_value) throws IOException {
        Boolean result = false;
        // Create TransportMapping and Listen
        TransportMapping transport = new DefaultUdpTransportMapping();
        transport.listen();

        // Create Target Address object
        CommunityTarget comtarget = new CommunityTarget();
        comtarget.setCommunity(new OctetString(this.community));
        comtarget.setVersion(this.version);
        comtarget.setAddress(new UdpAddress(this.ipAddress + "/" + this.port));
        comtarget.setRetries(this.retires);
        comtarget.setTimeout(this.timeout);

        // Create the PDU object
        PDU pdu = new PDU();

        // Setting the Oid and Value for sysContact variable
        OID oid = new OID(target_oid);
        Variable var = new OctetString(target_value);
        VariableBinding varBind = new VariableBinding(oid, var);
        pdu.add(varBind);

        pdu.setType(PDU.SET);
        pdu.setRequestID(new Integer32(1));

        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(transport);
//
//        System.out.println("\nRequest:\n[ Note: Set Request is sent for sysContact oid in RFC 1213 MIB.");
//        System.out.println("Set operation will change the sysContact value to " + target_value);
//        System.out.println("Once this operation is completed, Querying for sysContact will get the value = " + target_value + " ]");
//
//        System.out.println("Request:\nSending Snmp Set Request to Agent...");
        ResponseEvent response = snmp.set(pdu, comtarget);

        // Process Agent Response
        if (response != null) {
            // System.out.println("\nResponse:\nGot Snmp Set Response from Agent");
            PDU responsePDU = response.getResponse();

            if (responsePDU != null) {
                int errorStatus = responsePDU.getErrorStatus();
                int errorIndex = responsePDU.getErrorIndex();
                String errorStatusText = responsePDU.getErrorStatusText();

                if (errorStatus == PDU.noError) {
                    System.out.println("Snmp Set Response = " + responsePDU.getVariableBindings());
                    result = true;
                } else {
                    System.out.println("Error: Request Failed");
                    System.out.println("Error Status = " + errorStatus);
                    System.out.println("Error Index = " + errorIndex);
                    System.out.println("Error Status Text = " + errorStatusText);
                    result = false;
                }
            } else {
                System.out.println("Error: Response PDU is null");
                result = false;
            }
        } else {
            System.out.println("Error: Agent Timeout... ");
            result = false;
        }
        snmp.close();
        return result;


    }


    public String[] snmpGetNext(String currentOid) throws Exception {
        // System.out.println("SNMP GET-NEXT Demo");
        String[] result = {"", ""};
        String temp = "___NONE___";   // set ___NONE___ as initial value to avoid the real empty response from devices
        // Create TransportMapping and Listen
        TransportMapping transport = new DefaultUdpTransportMapping();
        transport.listen();

        // Create Target Address object
        CommunityTarget comtarget = new CommunityTarget();
        comtarget.setCommunity(new OctetString(this.community));
        comtarget.setVersion(this.version);
        comtarget.setAddress(new UdpAddress(this.ipAddress + "/" + this.port));
        comtarget.setRetries(this.retires);
        comtarget.setTimeout(this.timeout);

        // Create the PDU object
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(currentOid))); //Querying GetNext of sysDescr will get the sysObjectID OID value
        pdu.setRequestID(new Integer32(1));
        pdu.setType(PDU.GETNEXT);

        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(transport);

        ResponseEvent response = snmp.getNext(pdu, comtarget);

        // Process Agent Response
        if (response != null) {
            //System.out.println("\nResponse:\nGot GetNext Response from Agent...");
            PDU responsePDU = response.getResponse();

            if (responsePDU != null) {
                int errorStatus = responsePDU.getErrorStatus();
                int errorIndex = responsePDU.getErrorIndex();
                String errorStatusText = responsePDU.getErrorStatusText();

                if (errorStatus == PDU.noError) {

                    temp = responsePDU.getVariableBindings().firstElement().toString();
                } else {
                    System.out.println("Error: Request Failed");
                    System.out.println("Error Status = " + errorStatus);
                    System.out.println("Error Index = " + errorIndex);
                    System.out.println("Error Status Text = " + errorStatusText);
                }
            } else {
                System.out.println("Error: GetNextResponse PDU is null");
            }
        } else {
            System.out.println("Error: Agent Timeout... ");
        }
        snmp.close();

        if (temp != "___NONE___" || temp.contains("=")) {
            result[0] = "." + temp.substring(0, temp.indexOf("=")).trim();   // [0] is the next OID
            result[1] = temp.substring(temp.indexOf("=") + 1, temp.length()).trim();   // [1] is the value of the next oid
        }

//        if (result[0].equalsIgnoreCase(currentOid)){
//            throw  new IllegalArgumentException("This OID has been the last one in the OID table");
//        }


        return result;

    }


    public HashMap<String, String> snmpWalk() throws Exception {
        // Force the 2 times retries and 5s timeout in case of poor network
        this.retires = 2;
        this.timeout = 5000;
        HashMap<String, String> walkResult = new HashMap<>();
        final String startingOid = ".1.3.6.1.2.1.1.1.0";
        walkResult.put(startingOid, snmpGet(startingOid));
        String currentOid = startingOid;
        while (true) {

            if (snmpGet(currentOid) == "___NONE___") {
                System.out.println(currentOid + " : break");
                break;
            }


            String nextOid = snmpGetNext(currentOid)[0];
            String nextValue = snmpGetNext(currentOid)[1];

            if (nextValue.equalsIgnoreCase("endOfMibView")) {
                break;
            }


            System.out.println(nextOid + " : " + nextValue);

            currentOid = nextOid;

            walkResult.put(nextOid, nextValue);

        }


        return walkResult;
    }


}

