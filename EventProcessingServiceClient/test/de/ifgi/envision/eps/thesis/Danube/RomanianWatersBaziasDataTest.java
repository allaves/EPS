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

public class RomanianWatersBaziasDataTest {

	private static Logger log = Logger.getLogger(RomanianWatersBaziasDataTest.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOS/sos";
	
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String PROCEDURE_0002 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002";
	
	private static String BEGIN_DATE = "2006-01-01T18:00:00.000";
	private static String END_DATE = "2007-01-01T18:00:00.000";
	
	private static String PATTERN_EXCEEDING_ATTENTION_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 70.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17)) ]";
	private static String PATTERN_DECEEDING_ATTENTION_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 70.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 70.17)) ]";
	private static String PATTERN_EXCEEDING_FLOOD_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.07) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.07)) ]";
	private static String PATTERN_DECEEDING_FLOOD_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.07) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.07)) ]";
	private static String PATTERN_EXCEEDING_DANGER_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.17)) ]";
	private static String PATTERN_DECEEDING_DANGER_THRESHOLD = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 71.17) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 71.17)) ]";
	
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
	
	
	// Flood stage situations
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
			
			// 2. Register statements
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

			
			// 3.1 Schedule waterlevel service request
			RegisterServiceForHistoricalData registerService = new RegisterServiceForHistoricalData();
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
