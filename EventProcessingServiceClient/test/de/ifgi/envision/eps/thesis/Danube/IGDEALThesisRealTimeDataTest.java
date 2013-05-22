package de.ifgi.envision.eps.thesis.Danube;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.util.IOHelper;

import de.ifgi.envision.eps.core.EPSManagerClientStart;
import de.ifgi.envision.eps.core.EPSManagerStub;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterStatement;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterService;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterServiceForHistoricalData;
import de.ifgi.envision.eps.core.EPSManagerStub.Start;
import de.ifgi.envision.eps.core.EPSManagerStub.Stop;

public class IGDEALThesisRealTimeDataTest {

	private static Logger log = Logger.getLogger(IGDEALThesisRealTimeDataTest.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOSpublic/sos";
	
	private static String OFFERING_WATERFLOW = "WATER_FLOW";
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERFLOW = "urn:ogc:def:phenomenon:OGC:1.0.30:waterflow";
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String PROCEDURE_0003 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003";
	
	private static String FOI_0003 = "<sa:SamplingPoint gml:id=\"foi_0003\" xsi:schemaLocation=\"http://www.opengis.net/sampling/1.0 http://schemas.opengis.net/sampling/1.0.0/sampling.xsd\" " +
			"xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:gml=\"http://www.opengis.net/gml\" " +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">" +
			"<gml:description>Water measurements / computations at Pancevo</gml:description>" +
			"<gml:name>Pancevo</gml:name>" +
				"<sa:sampledFeature xlink:href=\"urn:ogc:def:nil:OGC:unknown\"/>" +
				"<sa:position>" +
					"<gml:Point gml:id=\"point_sf_1\">" +
						"<gml:pos srsName=\"urn:ogc:def:crs:EPSG::4326\">44.79830197 20.63680407</gml:pos>" +
					"</gml:Point>" +
				"</sa:position>" +
			"</sa:SamplingPoint>";

	
	/*
	 * Iron Gates scenario
	 */
	// High and low water level patterns for Pancevo
	private static String PATTERN_BELOW_3000 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value < 3000) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 65.5)) ] WHERE obs2.time.between(obs1.time, obs1.time.plus(10 seconds))";
	private static String PATTERN_BETWEEN_3000_3500 = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value between 3000 and 3500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.81)] WHERE obs2.time.between(obs1.time, obs1.time.plus(10 seconds))";
	private static String PATTERN_ABOVE_3500_HIGH = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value > 3500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.0)] WHERE obs2.time.between(obs1.time, obs1.time.plus(10 seconds))";
	private static String PATTERN_ABOVE_3500_LOW = "SELECT obs1, obs2 FROM pattern[every obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value > 3500) -> every obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 66.5)] WHERE obs2.time.between(obs1.time, obs1.time.plus(10 seconds))";
	
	// Event types
	private static String HIGH_WATER_LEVEL_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/IronGates/IronGatesEventTypes.rdf#HighWaterLevel";
	private static String LOW_WATER_LEVEL_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/IronGates/IronGatesEventTypes.rdf#LowWaterLevel";
		
	/*
	 * Serbian Waters scenario
	 */
	// Event types - thresholds
	private static String EXCEEDING_ATTENTION_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#ExceedingAttentionThreshold";
	private static String DECEEDING_ATTENTION_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#DeceedingAttentionThreshold";
	private static String EXCEEDING_FLOOD_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#ExceedingFloodThreshold";
	private static String DECEEDING_FLOOD_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#DeceedingFlooodThreshold";
					
	// Event types - flood stages
	private static String ATTENTION_STAGE_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#AttentionStage";
	private static String FLOOD_STAGE_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#FloodStage"; 
		
	// Event patterns - thresholds
	private static String PATTERN_EXCEEDING_ATTENTION_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 70.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17)) ]";
	private static String PATTERN_DECEEDING_ATTENTION_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 70.17)) ]";
	private static String PATTERN_EXCEEDING_FLOOD_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.07) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.07)) ]";
	private static String PATTERN_DECEEDING_FLOOD_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.07) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0003', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.07)) ]";
	
	// Event patterns - Flood stage situations
	private static String PATTERN_FLOOD_STAGE_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + EXCEEDING_FLOOD_THRESHOLD_EVENT_TYPE + "') -> obs2=EventAbstraction(eventType = '" + DECEEDING_FLOOD_THRESHOLD_EVENT_TYPE + "'))]";
	private static String PATTERN_ATTENTION_STAGE_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + EXCEEDING_ATTENTION_THRESHOLD_EVENT_TYPE + "') -> obs2=EventAbstraction(eventType = '" + DECEEDING_ATTENTION_THRESHOLD_EVENT_TYPE + "'))]";
	
	
	
	/*
	 * Client to demo the EPSManager
	 * 1. Start the EPS
	 * 2. Register statements
	 * 3. Insert simulated sensor observations
	 * 3. Register SOS requests for real-time data
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
			
			// 2.1 Register statements for Serbian Waters
			// Statement definition: Simulated high water level statements for Pancevo	
			RegisterStatement registerStatement = new RegisterStatement();
			registerStatement.setStm(PATTERN_EXCEEDING_ATTENTION_THRESHOLD);
			registerStatement.setEventType(EXCEEDING_ATTENTION_THRESHOLD_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the attention stage registered.");
			}
			else {
				log.info("Statement entering the attention stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_ATTENTION_THRESHOLD);
			registerStatement.setEventType(DECEEDING_ATTENTION_THRESHOLD_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the attention stage registered.");
			}
			else {
				log.info("Statement leaving the attention stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_ATTENTION_STAGE_EVENT);
			registerStatement.setEventType(ATTENTION_STAGE_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for attention stage registered.");
			}
			else {
				log.info("Statement for attention stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_EXCEEDING_FLOOD_THRESHOLD);
			registerStatement.setEventType(EXCEEDING_FLOOD_THRESHOLD_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the flood stage registered.");
			}
			else {
				log.info("Statement entering the flood stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_FLOOD_THRESHOLD);
			registerStatement.setEventType(DECEEDING_FLOOD_THRESHOLD_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the flood stage registered.");
			}
			else {
				log.info("Statement leaving the flood stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_FLOOD_STAGE_EVENT);
			registerStatement.setEventType(FLOOD_STAGE_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for flood registered.");
			}
			else {
				log.info("Statement for flood not registered.");
			}
			
			
			registerStatement.setStm(PATTERN_BELOW_3000);
			registerStatement.setEventType(LOW_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BELOW_3000 registered.");
			}
			else {
				log.info("Statement BELOW_3000 not registered.");
			}
			
			
			registerStatement.setStm(PATTERN_BETWEEN_3000_3500);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement BETWEEN_3000_3500 registered.");
			}
			else {
				log.info("Statement BETWEEN_3000_3500 not registered.");
			}
			
			registerStatement.setStm(PATTERN_ABOVE_3500_HIGH);
			registerStatement.setEventType(HIGH_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement ABOVE_3500_HIGH registered.");
			}
			else {
				log.info("Statement ABOVE_3500_HIGH registered.");
			}
			
			registerStatement.setStm(PATTERN_ABOVE_3500_LOW);
			registerStatement.setEventType(LOW_WATER_LEVEL_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement ABOVE_3500_LOW registered.");
			}
			else {
				log.info("Statement ABOVE_3500_LOW registered.");
			}
			
			
			// 3.1 Insert waterflow observations
			// Simulation of a waterflow increase - every 10 seconds, 40 m3/s more during 3 minutes, starting from 2900 m3/s
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date dateTimestamp = new Date();
			Calendar calCounter = Calendar.getInstance();
			calCounter.setTime(dateTimestamp);
			Calendar calLimit = Calendar.getInstance();
			calLimit.setTime(dateTimestamp);
			calLimit.add(Calendar.MINUTE, 3);
			String timestamp = formatter.format(dateTimestamp);
			String value = "2900";
			while (calCounter.getTime().before(calLimit.getTime())) {
				insertObservations(OBS_PROP_WATERFLOW, FOI_0003, timestamp, PROCEDURE_0003, value, "m3/s");
				calCounter.add(Calendar.SECOND, 10);
				timestamp = formatter.format(calCounter.getTime());
				value = String.valueOf((Double.valueOf(value) + 40));
			}
			// Simulation of a static waterflow - every 10 seconds, the same value (maximum of previous period was 3620 m3/s) during 3 minutes
			calLimit.add(Calendar.MINUTE, 3);
			while (calCounter.getTime().before(calLimit.getTime())) {
				insertObservations(OBS_PROP_WATERFLOW, FOI_0003, timestamp, PROCEDURE_0003, value, "m3/s");
				calCounter.add(Calendar.SECOND, 10);
				timestamp = formatter.format(calCounter.getTime());
			}
			
			
			// 3.2 Insert waterlevel observations
			// Simulation of a water level increase - every 10 seconds, 0,5 cm more during 3 minutes, starting from 65,9 cm
			dateTimestamp = new Date();
			calCounter.setTime(dateTimestamp);
			calLimit.setTime(dateTimestamp);
			calLimit.add(Calendar.MINUTE, 3);
			timestamp = formatter.format(dateTimestamp);
			value = "65.9";
			while (calCounter.getTime().before(calLimit.getTime())) {
				insertObservations(OBS_PROP_WATERLEVEL, FOI_0003, timestamp, PROCEDURE_0003, value, "cm");
				calCounter.add(Calendar.SECOND, 10);
				timestamp = formatter.format(calCounter.getTime());
				value = String.valueOf((Double.valueOf(value) + 0.5));
			}
			// Simulation of a water level decrease - every 10 seconds, 0,5 mdMA less during 3 minutes, starting from 74,9 mdMA
			calLimit.add(Calendar.MINUTE, 3);
			while (calCounter.getTime().before(calLimit.getTime())) {
				insertObservations(OBS_PROP_WATERLEVEL, FOI_0003, timestamp, PROCEDURE_0003, value, "cm");
				calCounter.add(Calendar.SECOND, 10);
				timestamp = formatter.format(calCounter.getTime());
				value = String.valueOf((Double.valueOf(value) - 0.5));
			}
			
			Date currentDate = new Date();
			log.info("***REAL-TIME EXPERIMENT DATA RETRIEVAL TIMESTAMP: " + currentDate.toString() + " ***");
			
			// 4.1 Schedule waterflow service request
			RegisterService registerService = new RegisterService();
			registerService.setServiceURL(SERVICE_URL);
			registerService.setObservedProperty(OBS_PROP_WATERFLOW);
			registerService.setOffering(OFFERING_WATERFLOW);
			registerService.setTimeUnit("SECONDS");
			registerService.setNumberOfTimeUnits(10);
			stub.registerService(registerService);
			
			Thread.sleep(500);
			
			// 4.2 Schedule waterlevel service request
			registerService.setServiceURL(SERVICE_URL);
			registerService.setObservedProperty(OBS_PROP_WATERLEVEL);
			registerService.setOffering(OFFERING_GAUGE_HEIGHT);
			registerService.setTimeUnit("SECONDS");
			registerService.setNumberOfTimeUnits(10);
			stub.registerService(registerService);
			
			Thread.sleep(400000);
			
			// 4. Stop the service
			Stop stop = new Stop();
			stub.stop(stop);
			
			
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		
		
	/*
	 * Method to insert simulated observations into the SOS
	 */
	private static void insertObservations(String observedProperty, String foi,
			String timestamp, String procedure, String value, String uom) {
		String request = 
				"<InsertObservation xmlns=\"http://www.opengis.net/sos/1.0\" " +
				"xmlns:ows=\"http://www.opengis.net/ows/1.1\" " +
				"xmlns:ogc=\"http://www.opengis.net/ogc\" " +
				"xmlns:om=\"http://www.opengis.net/om/1.0\" " +
				"xmlns:sos=\"http://www.opengis.net/sos/1.0\" " +
				"xmlns:sa=\"http://www.opengis.net/sampling/1.0\" " +
				"xmlns:gml=\"http://www.opengis.net/gml\" " +
				"xmlns:swe=\"http://www.opengis.net/swe/1.0.1\" " +
				"xmlns:xlink=\"http://www.w3.org/1999/xlink\" " +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
				"xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 " +
				"http://schemas.opengis.net/sos/1.0.0/sosInsert.xsd " +
				"http://www.opengis.net/sampling/1.0 " +
				"http://schemas.opengis.net/sampling/1.0.0/sampling.xsd " +
				"http://www.opengis.net/om/1.0 " +
				"http://schemas.opengis.net/om/1.0.0/extensions/observationSpecialization_override.xsd\" " +
				"service=\"SOS\" version=\"1.0.0\">" +
					"<AssignedSensorId>" + procedure + "</AssignedSensorId>" +
					"<om:Measurement>" +
						"<om:samplingTime><gml:TimeInstant><gml:timePosition>" + timestamp + "</gml:timePosition></gml:TimeInstant></om:samplingTime>" +
						"<om:procedure xlink:href=\" " + procedure + "\"/>" +
						"<om:observedProperty xlink:href=\"" + observedProperty + "\"/>" +
						"<om:featureOfInterest>" + foi + "</om:featureOfInterest>" +
						"<om:result uom=\"" + uom + "\">" + value + "</om:result>" +
					"</om:Measurement>" +
				"</InsertObservation>";
		InputStream is = null;
		try {
			is = IOHelper.sendPostMessage(SERVICE_URL, request);
			log.info("Request sent: ".concat(request));
			// Get response
			new OperationResult(is, null, request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
