package de.ifgi.envision.eps.processingagent;

import org.apache.log4j.Logger;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.subscriber.HighWaterLevelEventSubscriber;

public final class SmederevoEPA {
	private static Logger log = Logger.getLogger(SmederevoEPA.class);

	public static final void initiate() {
		// get current context classloader                                                                                                                                  
		ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
		// then alter the class-loader (but which one ? the one used to load this class itself) with:
		Thread.currentThread().setContextClassLoader(SmederevoEPA.class.getClassLoader());
		
		registerStatementHighWaterLevel();
	}

	private static void registerStatementHighWaterLevel() {
		// This pattern detects all the cases for which the 
		String pattern = "SELECT waterflow_obs, waterlevel_obs " +
				"FROM pattern[every waterflow_obs=SOSObservationEvent(" +
					"observer.id='urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0011', " +
					"observedProperty='urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', " +
					"value>2429) " +
					"-> every waterlevel_obs=SOSObservationEvent(" +
					"observer.id = waterflow_obs.observer.id, " +
					"observedProperty='urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', " +
					"value>439)]" +
				"WHERE waterlevel_obs.time.between(waterflow_obs.time, waterflow_obs.time.plus(23 hours 59 min))";
		
//		String pattern = "SELECT * " +
//				"FROM OrderedSOSObservationEvents(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0011') " +
//				"WHERE (observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow') " +
//				"AND (value > 2429)";
		
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new HighWaterLevelEventSubscriber());
	}
}
