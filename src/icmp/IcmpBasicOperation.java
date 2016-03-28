package icmp;


import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;

import java.util.ArrayList;

/**
 * Created by yuwang on 12/13/15.
 * Main class for checking if a device is reachable via ICMP ping
 */
public class IcmpBasicOperation {
    private int timeout;
    private int retries;
    private String ipAddress;

    public IcmpBasicOperation(String ipAddress, int timeout, int retries) {
        if (retries <= 0) {
            throw new IllegalArgumentException("retries must be higher than zero");
        }

        if (timeout < 1000 || timeout > 10000) {
            throw new IllegalArgumentException("timeout should be between 1000 to 10000 ms");
        }

        this.ipAddress = ipAddress;
        this.timeout = timeout;
        this.retries = retries;
    }


    public Boolean ifReachable() {
        Boolean result = true;

        ArrayList<Boolean> booleen = new ArrayList<>();
        try {

            // request
            final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();

            request.setHost(this.ipAddress);

            // repeat 4 times by default
            for (int count = 0; count < this.retries; count++) {

                // delegate
                final IcmpPingResponse response = IcmpPingUtil.executePingRequest(request);

                // log
                final String formattedResponse = IcmpPingUtil.formatResponse(response);
                System.out.println(String.valueOf(count) + " " + formattedResponse);

                if (formattedResponse.contains("Error: Timeout reached after")) booleen.add(false);
                if (!formattedResponse.contains("Error: Timeout reached after")) booleen.add(true);


                Thread.sleep(this.timeout);
            }
        } catch (final Throwable t) {

            // log
            t.printStackTrace();
        }

        return booleen.contains(true) ? true : false;


    }
}


