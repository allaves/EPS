package de.ifgi.envision.eps.thesis.Danube;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.log4j.Logger;

import de.ifgi.envision.eps.core.EPSManagerStub;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterServiceForHistoricalData;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterStatement;
import de.ifgi.envision.eps.core.EPSManagerStub.Start;

public class IronGatesBaziasDataTest {

	private static Logger log = Logger.getLogger(IronGatesBaziasDataTest.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOS/sos";
	
	private static String OFFERING_WATERFLOW = "WATER_FLOW";
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERFLOW = "urn:ogc:def:phenomenon:OGC:1.0.30:waterflow";
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String FOI_0002 = "<gml:FeatureCollection xsi:schemaLocation=\" http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">" +
			"<gml:featureMember>" +
			"    <sa:SamplingPoint gml:id=\"foi_0002\" xsi:schemaLocation=\" http://www.opengis.net/sampling/1.0 http://schemas.opengis.net/sampling/1.0.0/sampling.xsd\">" +
			"      <gml:description>Water measurements / computations at Bazias</gml:description>" +
			"      <gml:name>Bazias</gml:name>" +
			"      <sa:sampledFeature xlink:href=\"urn:ogc:def:nil:OGC:unknown\"/>" +
			"      <sa:position>" +
			"        <gml:Point>" +
			"          <gml:pos srsName=\"urn:ogc:def:crs:EPSG::4326\">44.80861612 21.39046182</gml:pos>" +
			"        </gml:Point>" +
			"      </sa:position>" +
			"    </sa:SamplingPoint>" +
			"  </gml:featureMember>" +
			"</gml:FeatureCollection>";
	
	private static String PROCEDURE_0002 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002";
	
	private static String BEGIN_DATE = "2006-01-01T18:00:00.000";
	private static String END_DATE = "2007-01-01T18:00:00.000";
	
	// High water level patterns for Bazias
	//private static String PATTERN_BELOW_3000 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value < 3000) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.81)) ] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_3000_3500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 3000 and 3500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.81)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_3500_4000 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 3500 and 4000) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.87)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_4000_4500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 4000 and 4500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.92)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_4500_5000 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 4500 and 5000) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.97)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_5000_5500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 5000 and 5500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.02)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_5500_6000 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 5500 and 6000) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.10)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_6000_6500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 6000 and 6500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_6500_7000 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 6500 and 7000) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.25)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_7000_7500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 7000 and 7500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.31)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_7500_8000 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 7500 and 8000) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.38)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	private static String PATTERN_BETWEEN_8000_11500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 8000 and 11500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.4)] WHERE obs2.time.between(obs1.time, obs1.time.plus(23 hours 59 min))";
	
	
	// High water level patterns for Iron Gates I
	private static String PATTERN_HIGH_WATER_LEVEL_IRON_GATES_I = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 69.59) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.59)]";
	private static String PATTERN_LOW_WATER_LEVEL_IRON_GATES_I = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 63) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 63)]";
	
	// High water level patterns for Iron Gates II
	private static String PATTERN_HIGH_WATER_LEVEL_IRON_GATES_II = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0034', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 41.00) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0034', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 41.00)]";
	private static String PATTERN_LOW_WATER_LEVEL_IRON_GATES_II = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0034', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 39.4) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0034', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 39.4)]";
		
	
	// Event types
	private static String HIGH_WATER_LEVEL_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/IronGates/IronGatesEventTypes.rdf#HighWaterLevel";
	private static String LOW_WATER_LEVEL_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/IronGates/IronGatesEventTypes.rdf#LowWaterLevel";
	
	
	/*
	 * Client to demo the EPSManager
	 * 1. Start the EPS
	 * 2. Register statements
	 * 3. Register SOS requests for historical data
	 */
	public static void main(String[] args) {
		try {
			// Initialize the stub
			EPSManagerStub stub = new EPSManagerStub();
			Options options = stub._getServiceClient().getOptions();
			options.setProperty(HTTPConstants.CHUNKED, false);
//			
//			// 1. Start the service
			Start start = new Start();
			stub.start(start);
			
			// 2. Register statements			
			
			RegisterStatement registerStatement = new RegisterStatement();
			registerStatement.setStm(PATTERN_HIGH_WATER_LEVEL_IRON_GATES_I);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement HIGH_WATER_LEVEL_IRON_GATES_I registered.");
			}
			else {
				log.info("Statement HIGH_WATER_LEVEL_IRON_GATES_I not registered.");
			}
			
			registerStatement.setStm(PATTERN_LOW_WATER_LEVEL_IRON_GATES_I);
			registerStatement.setEventType(LOW_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement LOW_WATER_LEVEL_IRON_GATES_I registered.");
			}
			else {
				log.info("Statement LOW_WATER_LEVEL_IRON_GATES_I not registered.");
			}
			
			registerStatement.setStm(PATTERN_HIGH_WATER_LEVEL_IRON_GATES_II);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement HIGH_WATER_LEVEL_IRON_GATES_II registered.");
			}
			else {
				log.info("Statement HIGH_WATER_LEVEL_IRON_GATES_II not registered.");
			}
			
			registerStatement.setStm(PATTERN_LOW_WATER_LEVEL_IRON_GATES_II);
			registerStatement.setEventType(LOW_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement LOW_WATER_LEVEL_IRON_GATES_II registered.");
			}
			else {
				log.info("Statement LOW_WATER_LEVEL_IRON_GATES_II not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_3000_3500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_3000_3500 registered.");
			}
			else {
				log.info("Statement BETWEEN_3000_3500 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_3500_4000);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_3500_4000 registered.");
			}
			else {
				log.info("Statement BETWEEN_3500_4000 registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_4000_4500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_4000_4500 registered.");
			}
			else {
				log.info("Statement BETWEEN_4000_4500 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_4500_5000);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_4500_5000 registered.");
			}
			else {
				log.info("Statement BETWEEN_4500_5000 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_5000_5500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_5000_5500 registered.");
			}
			else {
				log.info("Statement BETWEEN_5000_5500 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_5500_6000);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_5500_6000 registered.");
			}
			else {
				log.info("Statement BETWEEN_5500_6000 registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_6000_6500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_6000_6500 registered.");
			}
			else {
				log.info("Statement BETWEEN_6000_6500 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_6500_7000);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_6500_7000 registered.");
			}
			else {
				log.info("Statement BETWEEN_6500_7000 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_7000_7500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_7000_7500 registered.");
			}
			else {
				log.info("Statement BETWEEN_7000_7500 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_7500_8000);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_7500_8000 registered.");
			}
			else {
				log.info("Statement BETWEEN_7500_8000 not registered.");
			}
			
			registerStatement.setStm(PATTERN_BETWEEN_8000_11500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_8000_11500 registered.");
			}
			else {
				log.info("Statement BETWEEN_8000_11500 not registered.");
			}


			
			// 3.1 Schedule waterflow service request
			RegisterServiceForHistoricalData registerService = new RegisterServiceForHistoricalData();
			registerService.setServiceURL(SERVICE_URL);
			registerService.setObservedProperty(OBS_PROP_WATERFLOW);
			registerService.setOffering(OFFERING_WATERFLOW);
			registerService.setBegin(BEGIN_DATE);
			registerService.setEnd(END_DATE);
			registerService.setTimeUnit("DAYS");
			registerService.setNumberOfTimeUnits(1);
			stub.registerServiceForHistoricalData(registerService);
			
			// 3.1 Schedule waterlevel service request
			registerService = new RegisterServiceForHistoricalData();
			registerService.setServiceURL(SERVICE_URL);
			registerService.setObservedProperty(OBS_PROP_WATERLEVEL);
			registerService.setOffering(OFFERING_GAUGE_HEIGHT);
			registerService.setBegin(BEGIN_DATE);
			registerService.setEnd(END_DATE);
			registerService.setTimeUnit("DAYS");
			registerService.setNumberOfTimeUnits(1);
			stub.registerServiceForHistoricalData(registerService);
			
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
