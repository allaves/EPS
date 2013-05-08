package de.ifgi.envision.eps.core;

import java.util.Date;

import org.junit.Test;

import de.ifgi.envision.eps.service.SOS;


public class TestEPSManager {

	private EPSManager epsManager;
	
	private static final String EVENT_PATTERN = "SELECT waterflow_obs, waterlevel_obs " +
		"FROM pattern[every waterflow_obs=SOSObservationEvent(observer.id='urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0011', " +
		"observedProperty='urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value>2429) " +
		"-> every waterlevel_obs=SOSObservationEvent(observer.id = waterflow_obs.observer.id, observedProperty='urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value>439)] " +
		"WHERE waterlevel_obs.time.between(waterflow_obs.time, waterflow_obs.time.plus(23 hours 59 min))";
	
	private static final String SERVICE_URL = "http://194.102.135.23/EnvisionSOS/sos";
	private static final String SERVICE_VERSION = "1.0.0";
	
	public TestEPSManager() {
		epsManager = new EPSManager();
	}
	
//	@Test
//	public void testStart() {
//		epsManager.start();
//		try {
//			Thread.sleep(20000);
//			epsManager.stop();
//			Thread.sleep(10000);
//			epsManager.restart();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testRegisterStatement() {
		epsManager.start();
		epsManager.registerStatement(EVENT_PATTERN, "HighWaterLevel");
//		epsManager.registerService(SERVICE_URL, SERVICE_VERSION, "WATER_FLOW", 
//				"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow", null, null, 
//				new String[]{"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0011"}, 
//				null, "minutes", 5);
//		epsManager.registerService(SERVICE_URL, SERVICE_VERSION, "GAUGE_HEIGHT", 
//				"urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel", null, null, 
//				new String[]{"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0011"}, 
//				null, "minutes", 5);
		
	}
}
