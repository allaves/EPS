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

public class IGDEALThesisHistoricalDataTest {

	private static Logger log = Logger.getLogger(IGDEALThesisHistoricalDataTest.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOS/sos";
	
	private static String OFFERING_WATERFLOW = "WATER_FLOW";
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERFLOW = "urn:ogc:def:phenomenon:OGC:1.0.30:waterflow";
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String PROCEDURE_0002 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002";
	
	private static String BEGIN_DATE = "2006-01-01T18:00:00.000";
	private static String END_DATE = "2007-01-01T18:00:00.000";
	
	/*
	 * Iron Gates scenario
	 */
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
	 * Romanian Waters scenario
	 */
	// Event types - thresholds
	private static String EXCEEDING_ATTENTION_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#ExceedingAttentionThreshold";
	private static String DECEEDING_ATTENTION_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#DeceedingAttentionThreshold";
	private static String EXCEEDING_FLOOD_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#ExceedingFloodThreshold";
	private static String DECEEDING_FLOOD_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#DeceedingFlooodThreshold";
	private static String EXCEEDING_DANGER_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#ExceedingDangerThreshold";
	private static String DECEEDING_DANGER_THRESHOLD_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#DeceedingDangerThreshold";
					
	// Event types - flood stages
	private static String ATTENTION_STAGE_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#AttentionStage";
	private static String FLOOD_STAGE_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#FloodStage"; 
	private static String DANGER_STAGE_EVENT_TYPE = "http://wsmls.googlecode.com/svn/trunk/application/EventType/RomanianWaters/RomanianWatersEventTypes.rdf#DangerStage";
		
	// Event patterns - thresholds
	private static String PATTERN_EXCEEDING_ATTENTION_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 70.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17)) ]";
	private static String PATTERN_DECEEDING_ATTENTION_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 70.17)) ]";
	private static String PATTERN_EXCEEDING_FLOOD_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.07) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.07)) ]";
	private static String PATTERN_DECEEDING_FLOOD_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.07) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.07)) ]";
	private static String PATTERN_EXCEEDING_DANGER_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.17)) ]";
	private static String PATTERN_DECEEDING_DANGER_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.17)) ]";
	
	// Event patterns - Flood stage situations
	private static String PATTERN_DANGER_STAGE_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + EXCEEDING_DANGER_THRESHOLD_EVENT_TYPE + "') -> obs2=EventAbstraction(eventType = '" + DECEEDING_DANGER_THRESHOLD_EVENT_TYPE + "'))]";
	private static String PATTERN_FLOOD_STAGE_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + EXCEEDING_FLOOD_THRESHOLD_EVENT_TYPE + "') -> obs2=EventAbstraction(eventType = '" + DECEEDING_FLOOD_THRESHOLD_EVENT_TYPE + "'))]";
	private static String PATTERN_ATTENTION_STAGE_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + EXCEEDING_ATTENTION_THRESHOLD_EVENT_TYPE + "') -> obs2=EventAbstraction(eventType = '" + DECEEDING_ATTENTION_THRESHOLD_EVENT_TYPE + "'))]";
	
	
	
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
			
			// 2.1 Register statements for Romanian Waters
			// Statement definition: High water level statements for Nera Mouth based on tables provided by CS-Romania			
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
			
			registerStatement.setStm(PATTERN_EXCEEDING_DANGER_THRESHOLD);
			registerStatement.setEventType(EXCEEDING_DANGER_THRESHOLD_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the danger stage registered.");
			}
			else {
				log.info("Statement entering the danger stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_DANGER_THRESHOLD);
			registerStatement.setEventType(DECEEDING_DANGER_THRESHOLD_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the danger stage registered.");
			}
			else {
				log.info("Statement leaving the danger stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_DANGER_STAGE_EVENT);
			registerStatement.setEventType(DANGER_STAGE_EVENT_TYPE);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for danger registered.");
			}
			else {
				log.info("Statement for danger not registered.");
			}

			
			// 2.2 Register statements for Iron Gates
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
			
			// 3.2 Schedule waterlevel service request
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
