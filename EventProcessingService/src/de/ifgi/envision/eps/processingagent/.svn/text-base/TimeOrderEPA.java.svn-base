package de.ifgi.envision.eps.processingagent;

import org.apache.log4j.Logger;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;

public final class TimeOrderEPA {
	private static org.apache.log4j.Logger log = Logger.getLogger(TimeOrderEPA.class);
	
	public static final void initiate() {
		// get current context classloader                                                                                                                                  
		ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
		// then alter the class-loader (but which one ? the one used to load this class itself) with:
		Thread.currentThread().setContextClassLoader(TimeOrderEPA.class.getClassLoader());
		
		registerOrderSOSObservationEvents();
	}

	private static void registerOrderSOSObservationEvents() {
		String pattern = "INSERT into OrderedSOSObservationEvents " +
				"SELECT * FROM SOSObservationEvent.ext:time_order(time, 1 day)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern);
	}
	

}
