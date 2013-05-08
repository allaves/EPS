package de.ifgi.envision.eps.client;

import java.rmi.RemoteException;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;

public class TestEPSManagerClientRestart {

	public static void main(String[] args) {
		EPSManagerStub stub;
	    try {
	    	//stub = new EPSManagerStub("http://giv-wfs.uni-muenster.de/axis2/services/EPSManager");
	    	stub = new EPSManagerStub("http://giv-wfs.uni-muenster.de/axis2/services/EPSManager");
	    	// Configuration options
	    	Options ops = new Options();
	    	// Set timeout in the Client 
	        long timeout = 3 * 60 * 1000; // Two minutes
	        ops.setTimeOutInMilliSeconds(timeout);
	    	ops.setProperty(HTTPConstants.CHUNKED, Boolean.FALSE);
	    	stub._getServiceClient().setOptions(ops);
	    	stub._getServiceClient().setTargetEPR(new EndpointReference("http://giv-wfs.uni-muenster.de/axis2/services/EPSManager"));
			stub.restart();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
}
