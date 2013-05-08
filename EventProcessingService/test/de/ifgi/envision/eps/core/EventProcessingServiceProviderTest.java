package de.ifgi.envision.eps.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.espertech.esper.client.EPStatement;

public class EventProcessingServiceProviderTest {

	@Test
	public void addListenerTest() {
		//String stm = "SELECT obs1, obs2, obs3 FROM pattern[ every(obs1=ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', value >70.0) -> obs2=ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', value >72.0) -> obs3=ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', value >74.0))]";
		String[] statements = new String[]{"",
										"SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.1) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.1))]"};
		for (String stm : statements) {
			assertTrue(EventProcessingServiceProvider.registerStatement(stm, "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#HighWaterLevel"));
		}
	}

}
