#It is a SNMP/ICMP common API library in Java.


----------

##Brief summary 
####SNMP
	SNMPGET()
	SNMPSET()
	SNMPWALK()
	SNMPGETNEXT()

----------

####ICMP
	isAddressReachable()

----------

####Discover an IP range to get all reachable addresses
	obtainReachalbeAddresses()

#### Parameters
	- Timeout
	- Retries 
	- SNMP version (1, 2c, 3)
	- SNMP community string for reading and writing 
