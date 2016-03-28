package ToolKit;

import icmp.IcmpBasicOperation;

import java.util.ArrayList;

/**
 * Created by yuwang on 12/13/15.
 */
public class DiscoverWithinRange {
    private String addressStart;
    private String addressEnd;
    private int retries;
    private int timeout;

    private ArrayList<String> allAddresses;

    public DiscoverWithinRange(String addressStart, String addressEnd, int retries, int timeout) {

        if (retries <= 0) {
            throw new IllegalArgumentException("retries must be higher than zero");
        }

        if (timeout < 1000 || timeout > 10000) {
            throw new IllegalArgumentException("timeout should be between 1000 to 10000 ms");
        }

        this.timeout = timeout;
        this.addressStart = addressStart;
        this.addressEnd = addressEnd;
        this.retries = retries;
        allAddresses = parseAddressList();
    }

    public ArrayList<String> obtainReachables() {
        ArrayList<String> resultList = new ArrayList<>();
        for (String entry : this.allAddresses) {
            IcmpBasicOperation icmpBasicOperation = new IcmpBasicOperation(entry, this.timeout, this.retries);
            if (icmpBasicOperation.ifReachable()) {
                resultList.add(entry);
            }

        }

        return resultList;
    }

    public ArrayList<String> parseAddressList() {
        ArrayList<String> resultList = new ArrayList<>();
        if (this.addressStart.equalsIgnoreCase(this.addressEnd)) {
            resultList.add(this.addressEnd);
            return resultList;
        }

        String[] start = this.addressStart.split("\\.");
        String[] end = this.addressEnd.split("\\.");

        if (start.length != 4 || end.length != 4) {
            throw new IllegalArgumentException("IP address range is illegal");
        }

        for (int i = 0; i < 4; i++) {
            try {
                int temp = Integer.parseInt(start[i]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("There should not be any letter in IP address");
            }

            try {
                int temp = Integer.parseInt(end[i]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("There should not be any letter in IP address");
            }

        }

        if (Integer.parseInt(start[0]) <= 0 || Integer.parseInt(start[0]) > 255
                || Integer.parseInt(end[0]) <= 0 || Integer.parseInt(end[0]) > 255
                || Integer.parseInt(start[3]) < 0 || Integer.parseInt(start[3]) > 255
                || Integer.parseInt(end[3]) < 0 || Integer.parseInt(end[3]) > 255) {
            throw new IllegalArgumentException("The IP address is illegal");
        }

        if (start[0] == end[0] && start[1] == end[1] && start[2] == end[2]
                && Integer.parseInt(start[3]) > Integer.parseInt(end[3])) {

            throw new IllegalArgumentException("Starting IP cannot be after ending IP");
        }

        if (start[0].equals(end[0]) && start[1].equals(end[1]) && start[2].equals(end[2])
                && Integer.parseInt(start[3]) < Integer.parseInt(end[3])) {
            int diff = Integer.parseInt(end[3]) - Integer.parseInt(start[3]);
            int i = 0;
            while (i <= diff) {
                resultList.add(start[0] + "." + start[1] + "." + start[2] + "." + String.valueOf(Integer.parseInt(start[3]) + i));
                //System.out.println(start[0] + "." + start[1] + "." + start[2] + "." + String.valueOf(Integer.parseInt(start[3]) + i));
                i++;
            }

        }


        return resultList;
    }


}
