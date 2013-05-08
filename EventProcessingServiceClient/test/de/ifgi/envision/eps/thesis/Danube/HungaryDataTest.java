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

public class HungaryDataTest {

	private static Logger log = Logger.getLogger(RomanianWatersBaziasDataTest.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOS/sos";
	
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String PROCEDURE_0046 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046";
	private static String PROCEDURE_0049 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049";
	private static String PROCEDURE_0050 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050";
	
	private static String BEGIN_DATE = "2012-06-01T18:00:00.000";
	private static String END_DATE = "2013-02-27T18:00:00.000";
	
	// ESTERGOM - 0046
	private static String PATTERN_ENTERING_ALERT_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 500) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 500)) ]";
	private static String PATTERN_LEAVING_ALERT_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 500) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 500)) ]";
	
	private static String PATTERN_ENTERING_FLOOD_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 600) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 600)) ]";
	private static String PATTERN_LEAVING_FLOOD_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 600) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 600)) ]";
	
	private static String PATTERN_ENTERING_DANGER_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 650) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 650)) ]";
	private static String PATTERN_LEAVING_DANGER_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 650) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 650)) ]";
	
	private static String PATTERN_ENTERING_LNV_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 771) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 771)) ]";
	private static String PATTERN_LEAVING_LNV_STAGE_ESTERGOM_0046 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 771) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0046', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 771)) ]";
	
	
	// BAJA - 0049
	private static String PATTERN_ENTERING_ALERT_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 700) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 700)) ]";
	private static String PATTERN_LEAVING_ALERT_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 700) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 700)) ]";
	
	private static String PATTERN_ENTERING_FLOOD_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 800) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 700)) ]";
	private static String PATTERN_LEAVING_FLOOD_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 800) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 700)) ]";
	
	private static String PATTERN_ENTERING_DANGER_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 900) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 900)) ]";
	private static String PATTERN_LEAVING_DANGER_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 900) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 900)) ]";
	
	private static String PATTERN_ENTERING_LNV_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 976) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 976)) ]";
	private static String PATTERN_LEAVING_LNV_STAGE_BAJA_0049 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 976) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0049', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 976)) ]";
		
	
	// MOHACS - 0050
	private static String PATTERN_ENTERING_ALERT_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 700) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 700)) ]";
	private static String PATTERN_LEAVING_ALERT_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 700) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 700)) ]";
	
	private static String PATTERN_ENTERING_FLOOD_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 850) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 850)) ]";
	private static String PATTERN_LEAVING_FLOOD_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 850) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 850)) ]";
	
	private static String PATTERN_ENTERING_DANGER_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 950) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 950)) ]";
	private static String PATTERN_LEAVING_DANGER_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 950) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 950)) ]";
	
	private static String PATTERN_ENTERING_LNV_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 984) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 984)) ]";
	private static String PATTERN_LEAVING_LNV_STAGE_MOHACS_0050 = "SELECT obs1, obs2 FROM pattern[every (obs1=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 984) -> obs2=ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0050', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 984)) ]";
		
	
	
	// Aggregation Event Patterns
	private static String PATTERN_ALERT_PERIOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringAlertRegion') -> obs2=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingAlertRegion'))]";
	private static String PATTERN_FLOOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringFloodRegion') -> obs2=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingFloodRegion'))]";
	private static String PATTERN_DANGEROUS_FLOOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringDangerRegion') -> obs2=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingDangerRegion'))]";
	private static String PATTERN_EXTREME_FLOOD_EVENT = "SELECT obs1, obs2 FROM pattern[every (obs1=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringLNVRegion') -> obs2=EventAbstraction(eventType = 'http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingLNVRegion'))]";
	
	
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
			
			// Event types
			String enteringLNVRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringLNVRegion";
			String leavingLNVRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingLNVRegion";
			
			String enteringDangerRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringDangerRegion";
			String leavingDangerRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingDangerRegion";
			
			String enteringFloodRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringFloodRegion";
			String leavingFloodRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingFloodRegion";
			
			String enteringAlertRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#EnteringAlertRegion";
			String leavingAlertRegionEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#LeavingAlertRegion";
			
			String floodAlertEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#FloodAlert";
			String floodEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#Flood"; 
			
			String dangerousFloodEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#DangerousFlood";
			String extremeFloodEventType = "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#ExtremeFlood"; 
			
			
			// Registration ESTERGOM - 0046
			RegisterStatement registerStatement = new RegisterStatement();
			registerStatement.setStm(PATTERN_ENTERING_ALERT_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(enteringAlertRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the alert stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement entering the alert stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_ALERT_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(leavingAlertRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the alert stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement leaving the alert stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_FLOOD_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(enteringFloodRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the flood stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement entering the flood stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_FLOOD_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(leavingFloodRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the flood stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement leaving the flood stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_DANGER_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(enteringDangerRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering danger stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement entering danger stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_DANGER_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(leavingDangerRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving danger stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement leaving danger stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_LNV_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(enteringLNVRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering LNV stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement entering LNV stage not registered - ESTERGOM_0046.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_LNV_STAGE_ESTERGOM_0046);
			registerStatement.setEventType(leavingLNVRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving LNV stage registered - ESTERGOM_0046.");
			}
			else {
				log.info("Statement leaving LNV stage not registered - ESTERGOM_0046.");
			}
			
			
			
			// Registration BAJA - 0049
			registerStatement.setStm(PATTERN_ENTERING_ALERT_STAGE_BAJA_0049);
			registerStatement.setEventType(enteringAlertRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the alert stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement entering the alert stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_ALERT_STAGE_BAJA_0049);
			registerStatement.setEventType(leavingAlertRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the alert stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement leaving the alert stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_FLOOD_STAGE_BAJA_0049);
			registerStatement.setEventType(enteringFloodRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the flood stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement entering the flood stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_FLOOD_STAGE_BAJA_0049);
			registerStatement.setEventType(leavingFloodRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the flood stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement leaving the flood stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_DANGER_STAGE_BAJA_0049);
			registerStatement.setEventType(enteringDangerRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering danger stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement entering danger stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_DANGER_STAGE_BAJA_0049);
			registerStatement.setEventType(leavingDangerRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving danger stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement leaving danger stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_LNV_STAGE_BAJA_0049);
			registerStatement.setEventType(enteringLNVRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering LNV stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement entering LNV stage not registered - BAJA_0049.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_LNV_STAGE_BAJA_0049);
			registerStatement.setEventType(leavingLNVRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving LNV stage registered - BAJA_0049.");
			}
			else {
				log.info("Statement leaving LNV stage not registered - BAJA_0049.");
			}
			
			
			// Registration MOHACS - 0050
			registerStatement.setStm(PATTERN_ENTERING_ALERT_STAGE_MOHACS_0050);
			registerStatement.setEventType(enteringAlertRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the alert stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement entering the alert stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_ALERT_STAGE_MOHACS_0050);
			registerStatement.setEventType(leavingAlertRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the alert stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement leaving the alert stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_FLOOD_STAGE_MOHACS_0050);
			registerStatement.setEventType(enteringFloodRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering the flood stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement entering the flood stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_FLOOD_STAGE_MOHACS_0050);
			registerStatement.setEventType(leavingFloodRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving the flood stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement leaving the flood stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_DANGER_STAGE_MOHACS_0050);
			registerStatement.setEventType(enteringDangerRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering danger stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement entering danger stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_DANGER_STAGE_MOHACS_0050);
			registerStatement.setEventType(leavingDangerRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving danger stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement leaving danger stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_ENTERING_LNV_STAGE_MOHACS_0050);
			registerStatement.setEventType(enteringLNVRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement entering LNV stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement entering LNV stage not registered - MOHACS_0050.");
			}
			
			registerStatement.setStm(PATTERN_LEAVING_LNV_STAGE_MOHACS_0050);
			registerStatement.setEventType(leavingLNVRegionEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement leaving LNV stage registered - MOHACS_0050.");
			}
			else {
				log.info("Statement leaving LNV stage not registered - MOHACS_0050.");
			}
			
			
			
			// Aggregation events
			registerStatement.setStm(PATTERN_ALERT_PERIOD_EVENT);
			registerStatement.setEventType(floodAlertEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for alert stage registered.");
			}
			else {
				log.info("Statement for alert stage not registered.");
			}
			
			registerStatement.setStm(PATTERN_FLOOD_EVENT);
			registerStatement.setEventType(floodEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for flood registered.");
			}
			else {
				log.info("Statement for flood not registered.");
			}
			
			registerStatement.setStm(PATTERN_DANGEROUS_FLOOD_EVENT);
			registerStatement.setEventType(dangerousFloodEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for dangerous flood registered.");
			}
			else {
				log.info("Statement for dangerous flood not registered.");
			}
			
			registerStatement.setStm(PATTERN_EXTREME_FLOOD_EVENT);
			registerStatement.setEventType(extremeFloodEventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				log.info("Statement for extreme flood registered.");
			}
			else {
				log.info("Statement for extreme flood not registered.");
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
