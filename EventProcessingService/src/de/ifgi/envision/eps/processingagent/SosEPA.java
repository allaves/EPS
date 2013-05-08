package de.ifgi.envision.eps.processingagent;

import org.apache.log4j.Logger;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.subscriber.SOSObservationEventSubscriber;

public class SosEPA {
	private static Logger log = Logger.getLogger(SosEPA.class);
	
	public static final void initiate() {
		// get current context classloader                                                                                                                                  
		ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
		// then alter the class-loader (but which one ? the one used to load this class itself) with:
		Thread.currentThread().setContextClassLoader(SosEPA.class.getClassLoader());
		
		registerStatementAllSOS();
		
		// restore the class loader to its original value:
		Thread.currentThread().setContextClassLoader(contextClassloader);
	}

	private static void registerStatementAllSOS() {
		String pattern = "SELECT * " +
						 "FROM OrderedSOSObservationEvents";
		//EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new SOSObservationEventSubscriber());
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern);
	}
}
