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

public class SlovakiaDataTest {

	private static Logger log = Logger.getLogger(SlovakiaDataTest.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOS/sos";
	
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String PROCEDURE_0044 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044";
	private static String PROCEDURE_0045 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045";
	
	private static String BEGIN_DATE = "2012-06-01T18:00:00.000";
	private static String END_DATE = "2013-03-05T18:00:00.000";
	
	
	// BRATISLAVA - 0044
	private static String PATTERN_EXCEEDING_1ST_DEGREE_THRESHOLD_BRATISLAVA_0044 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 650) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 650)) ]";
	private static String PATTERN_DECEEDING_1ST_DEGREE_THRESHOLD_BRATISLAVA_0044 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 650) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 650)) ]";
	
	private static String PATTERN_EXCEEDING_2ND_DEGREE_THRESHOLD_BRATISLAVA_0044 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 750) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 750)) ]";
	private static String PATTERN_DECEEDING_2ND_DEGREE_THRESHOLD_BRATISLAVA_0044 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 750) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 750)) ]";
	
	private static String PATTERN_EXCEEDING_3RD_DEGREE_THRESHOLD_BRATISLAVA_0044 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 850) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 850)) ]";
	private static String PATTERN_DECEEDING_3RD_DEGREE_THRESHOLD_BRATISLAVA_0044 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 850) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0044', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 850)) ]";
	
	
	// KOMARNO - 0045
	private static String PATTERN_EXCEEDING_1ST_DEGREE_THRESHOLD_KOMARNO_0045 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 600) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 600)) ]";
	private static String PATTERN_DECEEDING_1ST_DEGREE_THRESHOLD_KOMARNO_0045 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 600) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 600)) ]";
	
	private static String PATTERN_EXCEEDING_2ND_DEGREE_THRESHOLD_KOMARNO_0045 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 640) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 640)) ]";
	private static String PATTERN_DECEEDING_2ND_DEGREE_THRESHOLD_KOMARNO_0045 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 640) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 640)) ]";
	
	private static String PATTERN_EXCEEDING_3RD_DEGREE_THRESHOLD_KOMARNO_0045 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 710) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 710)) ]";
	private static String PATTERN_DECEEDING_3RD_DEGREE_THRESHOLD_KOMARNO_0045 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 710) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0045', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 710)) ]";
	

	// Event types								
	private static String exceeding1stDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Exceeding1stDegreeThreshold";
	private static String deceeding1stDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Deceeding1stDegreeThreshold";
	
	private static String exceeding2ndDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Exceeding2ndDegreeThreshold";
	private static String deceeding2ndDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Deceeding2ndDegreeThreshold";
	
	private static String exceeding3rdDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Exceeding3rdDegreeThreshold";
	private static String deceeding3rdDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Deceeding3rdDegreeThreshold";
	
	private static String floodActivity1stDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#FloodActivity1stDegree";
	private static String floodActivity2ndDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#FloodActivity2ndDegree"; 
	private static String floodActivity3rdDegreeEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#FloodActivity3rdDegree";
	
	// Aggregation Event Patterns
	private static String PATTERN_1ST_DEGREE_FLOOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + exceeding1stDegreeEventType + "') -> obs2=EventAbstraction(eventType = '" + deceeding1stDegreeEventType + "'))]";
	private static String PATTERN_2ND_DEGREE_FLOOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + exceeding2ndDegreeEventType + "') -> obs2=EventAbstraction(eventType = '" + deceeding2ndDegreeEventType + "'))]";
	private static String PATTERN_3RD_DEGREE_FLOOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = '" + exceeding3rdDegreeEventType + "') -> obs2=EventAbstraction(eventType = '" + deceeding3rdDegreeEventType + "'))]";
	
	
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
			// Statement definition: http://www.hydroinfo.hu/hidinfo.html
			
			
			
			// Registration BRATISLAVA - 0044
			RegisterStatement registerStatement = new RegisterStatement();
			registerStatement.setStm(PATTERN_EXCEEDING_1ST_DEGREE_THRESHOLD_BRATISLAVA_0044);
			registerStatement.setEventType(exceeding1stDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the alert stage registered - BRATISLAVA_0044.");
			}
			else {
				log.info("Statement entering the alert stage not registered - BRATISLAVA_0044.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_1ST_DEGREE_THRESHOLD_BRATISLAVA_0044);
			registerStatement.setEventType(deceeding1stDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the alert stage registered - BRATISLAVA_0044.");
			}
			else {
				log.info("Statement leaving the alert stage not registered - BRATISLAVA_0044.");
			}
			
			registerStatement.setStm(PATTERN_EXCEEDING_2ND_DEGREE_THRESHOLD_BRATISLAVA_0044);
			registerStatement.setEventType(exceeding2ndDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the flood stage registered - BRATISLAVA_0044.");
			}
			else {
				log.info("Statement entering the flood stage not registered - BRATISLAVA_0044.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_2ND_DEGREE_THRESHOLD_BRATISLAVA_0044);
			registerStatement.setEventType(deceeding2ndDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the flood stage registered - BRATISLAVA_0044.");
			}
			else {
				log.info("Statement leaving the flood stage not registered - BRATISLAVA_0044.");
			}
			
			registerStatement.setStm(PATTERN_EXCEEDING_3RD_DEGREE_THRESHOLD_BRATISLAVA_0044);
			registerStatement.setEventType(exceeding3rdDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering danger stage registered - BRATISLAVA_0044.");
			}
			else {
				log.info("Statement entering danger stage not registered - BRATISLAVA_0044.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_3RD_DEGREE_THRESHOLD_BRATISLAVA_0044);
			registerStatement.setEventType(deceeding3rdDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving danger stage registered - BRATISLAVA_0044.");
			}
			else {
				log.info("Statement leaving danger stage not registered - BRATISLAVA_0044.");
			}
			
			
			
			// Registration KOMARNO - 0045
			registerStatement.setStm(PATTERN_EXCEEDING_1ST_DEGREE_THRESHOLD_KOMARNO_0045);
			registerStatement.setEventType(exceeding1stDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the alert stage registered - KOMARNO_0045.");
			}
			else {
				log.info("Statement entering the alert stage not registered - KOMARNO_0045.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_1ST_DEGREE_THRESHOLD_KOMARNO_0045);
			registerStatement.setEventType(deceeding1stDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the alert stage registered - KOMARNO_0045.");
			}
			else {
				log.info("Statement leaving the alert stage not registered - KOMARNO_0045.");
			}
			
			registerStatement.setStm(PATTERN_EXCEEDING_2ND_DEGREE_THRESHOLD_KOMARNO_0045);
			registerStatement.setEventType(exceeding2ndDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the flood stage registered - KOMARNO_0045.");
			}
			else {
				log.info("Statement entering the flood stage not registered - KOMARNO_0045.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_2ND_DEGREE_THRESHOLD_KOMARNO_0045);
			registerStatement.setEventType(deceeding2ndDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the flood stage registered - KOMARNO_0045.");
			}
			else {
				log.info("Statement leaving the flood stage not registered - KOMARNO_0045.");
			}
			
			registerStatement.setStm(PATTERN_EXCEEDING_3RD_DEGREE_THRESHOLD_KOMARNO_0045);
			registerStatement.setEventType(exceeding3rdDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering danger stage registered - KOMARNO_0045.");
			}
			else {
				log.info("Statement entering danger stage not registered - KOMARNO_0045.");
			}
			
			registerStatement.setStm(PATTERN_DECEEDING_3RD_DEGREE_THRESHOLD_KOMARNO_0045);
			registerStatement.setEventType(deceeding3rdDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving danger stage registered - KOMARNO_0045.");
			}
			else {
				log.info("Statement leaving danger stage not registered - KOMARNO_0045.");
			}
			
			
			// Aggregation events
			registerStatement.setStm(PATTERN_1ST_DEGREE_FLOOD_EVENT);
			registerStatement.setEventType(floodActivity1stDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for alert stage registered.");
			}
			else {
				log.info("Statement for alert stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_2ND_DEGREE_FLOOD_EVENT);
			registerStatement.setEventType(floodActivity2ndDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for flood registered.");
			}
			else {
				log.info("Statement for flood not registered.");
			}
			
			registerStatement.setStm(PATTERN_3RD_DEGREE_FLOOD_EVENT);
			registerStatement.setEventType(floodActivity3rdDegreeEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for dangerous flood registered.");
			}
			else {
				log.info("Statement for dangerous flood not registered.");
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
