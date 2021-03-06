package de.ifgi.envision.eps.core;

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
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.util.IOHelper;

import de.ifgi.envision.eps.core.EPSManagerStub.RegisterService;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterStatement;
import de.ifgi.envision.eps.core.EPSManagerStub.Start;
import de.ifgi.envision.eps.core.EPSManagerStub.Stop;

public class EPSManagerClientJSONPatternAggregateTest {
	
	private static Logger log = Logger.getLogger(EPSManagerClientStart.class); 
	
	private static String SERVICE_URL = "http://envision.c-s.ro/EnvisionSOSpublic/sos";
	
	private static String OFFERING_WATERFLOW = "WATER_FLOW";
	private static String OFFERING_GAUGE_HEIGHT = "GAUGE_HEIGHT";
	
	private static String OBS_PROP_WATERFLOW = "urn:ogc:def:phenomenon:OGC:1.0.30:waterflow";
	private static String OBS_PROP_WATERLEVEL = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	
	private static String FOI_0054 = "<sa:SamplingPoint xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:gml=\"http://www.opengis.net/gml\" " +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" gml:id=\"foi_0054\" " +
			"xsi:schemaLocation=\"http://www.opengis.net/sampling/1.0 http://schemas.opengis.net/sampling/1.0.0/sampling.xsd\">" +
			"<gml:description>Water measurements / computations at NoviSad</gml:description>" +
			"<gml:name>NoviSad</gml:name>" +
			"<sa:sampledFeature xlink:href=\"urn:ogc:def:nil:OGC:unknown\"/>" +
			"<sa:position><gml:Point gml:id=\"point_sf_1\"><gml:pos srsName=\"urn:ogc:def:crs:EPSG::4326\">45.243228 19.859104</gml:pos></gml:Point></sa:position>" +
			"</sa:SamplingPoint>";
	
	private static String FOI_0056 = "<sa:SamplingPoint xmlns:sa=\"http://www.opengis.net/sampling/1.0\" xmlns:gml=\"http://www.opengis.net/gml\" " +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" gml:id=\"foi_0056\" " +
			"xsi:schemaLocation=\"http://www.opengis.net/sampling/1.0 http://schemas.opengis.net/sampling/1.0.0/sampling.xsd\">" +
			"<gml:description>Water measurements / computations at Zemun</gml:description>" +
			"<gml:name>Zemun</gml:name>" +
			"<sa:sampledFeature xlink:href=\"urn:ogc:def:nil:OGC:unknown\"/>" +
			"<sa:position><gml:Point gml:id=\"point_sf_1\"><gml:pos srsName=\"urn:ogc:def:crs:EPSG::4326\">44.854165 20.401554</gml:pos></gml:Point></sa:position>" +
			"</sa:SamplingPoint>";
	
	private static String PROCEDURE_0054 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054";
	private static String PROCEDURE_0056 = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0056";
	
	/*
	 * Client to demo the EPSManager
	 * 1. Start the EPS
	 * 2. Register statements
	 * 3. Register SOS requests
	 * 4. Stop the EPS
	 */
	public static void main(String[] args) {
		try {
			// Initialize the stub
			EPSManagerStub stub = new EPSManagerStub();
			Options options = stub._getServiceClient().getOptions();
			options.setProperty(HTTPConstants.CHUNKED, false);
			
			// 1. Start the service
			Start start = new Start();
			stub.start(start);
			
			// 2. Register statements
			// {\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"Value\":{\"$lt\":\"2981.3\"}, \"Value_1I_ago\":{\"$gt\":\"2939.0\"}, \"Value_2I_ago\":{\"$lt\":\"3000.0\"}}
			// Statement definition: ...
			// 2.1 First, we create a new event type called AggregateEvent-...
			// 2.2 We create a new named window called AggregateWindow-...
			// 2.3 We register a statement that aggregates the observations and insert them into the named window.
			// 2.4 We register a second statement which contains a pattern to check the order of the aggregated values.
			String stm1 = "INSERT INTO AggregationEvent SELECT temporalLocation, observedProperty, spatialLocation, observer, source, unitOfMeasure, url, sum(value) as aggregateValue, 'sum' as aggregationType, '30 seconds' as aggregationTimeSpan FROM ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054').win:time_batch(30 seconds)";
			String stm2 = "SELECT obs1, obs2 FROM pattern[ every(obs1=AggregationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', aggregateValue = 6.0) -> obs2=AggregationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', aggregateValue > 12.0))]";
			
			//String stm3 = "SELECT * FROM AggregationEvent"; 
					
			// Event type
			String eventType = "http://purl.org/ifgi/water/flood#HighWaterLevel";
			
			RegisterStatement registerStatement = new RegisterStatement();
			registerStatement.setStm(stm1);
			registerStatement.setEventType(eventType);
			if (stub.registerStatement(registerStatement).get_return()) {
				System.out.println("Statement 1 registered.");
			}
			else {
				System.out.println("Statement 1 not registered.");
			}
			
			registerStatement.setStm(stm2);
			if (stub.registerStatement(registerStatement).get_return()) {
				System.out.println("Statement 2 registered.");
			}
			else {
				System.out.println("Statement 2 not registered.");
			}
//			
//			registerStatement.setStm(stm3);
//			if (stub.registerStatement(registerStatement).get_return()) {
//				System.out.println("Statement 3 registered.");
//			}
//			else {
//				System.out.println("Statement 3 not registered.");
//			}
			
			// 3.1 Insert waterflow observations
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			Date dateTimestamp = new Date();
			Calendar calCounter = Calendar.getInstance();
			calCounter.setTime(dateTimestamp);
			Calendar calLimit = Calendar.getInstance();
			calLimit.setTime(dateTimestamp);
			calLimit.add(Calendar.MINUTE, 3);
			String timestamp = formatter.format(dateTimestamp);
			//String value = "2900";
			String value = "1";
			while (calCounter.getTime().before(calLimit.getTime())) {
				insertObservations(OBS_PROP_WATERFLOW, FOI_0054, timestamp, PROCEDURE_0054, value, "m3/s");
				calCounter.add(Calendar.SECOND, 10);
				timestamp = formatter.format(calCounter.getTime());
				//value = String.valueOf((Double.valueOf(value) + 40));
				value = String.valueOf((Double.valueOf(value) + 1));
			}
			
			
			// 3.2 Insert waterlevel observations
//			dateTimestamp = new Date();
//			calCounter.setTime(dateTimestamp);
//			calLimit.setTime(dateTimestamp);
//			calLimit.add(Calendar.MINUTE, 3);
//			timestamp = formatter.format(dateTimestamp);
//			value = "65.9";
//			while (calCounter.getTime().before(calLimit.getTime())) {
//				insertObservations(OBS_PROP_WATERLEVEL, FOI_0056, timestamp, PROCEDURE_0056, value, "cm");
//				calCounter.add(Calendar.SECOND, 10);
//				timestamp = formatter.format(calCounter.getTime());
//				value = String.valueOf((Double.valueOf(value) + 2.0));
//			}
			
			// 3.3 Schedule waterflow service request
			RegisterService registerService = new RegisterService();
			registerService.setServiceURL(SERVICE_URL);
			registerService.setOffering(OFFERING_WATERFLOW);
			registerService.setObservedProperty(OBS_PROP_WATERFLOW);
			registerService.setTimeUnit("seconds");
			registerService.setNumberOfTimeUnits(10);
			stub.registerService(registerService);
			
			// 3.3 Schedule waterlevel service request
//			registerService.setOffering(OFFERING_GAUGE_HEIGHT);
//			registerService.setObservedProperty(OBS_PROP_WATERLEVEL);
//			stub.registerService(registerService);
			
			Thread.sleep(200000);
			
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
