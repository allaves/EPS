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

import de.ifgi.envision.eps.core.EPSManagerStub.RegisterJSONStatement;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterRuleMLStatement;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterService;
import de.ifgi.envision.eps.core.EPSManagerStub.RegisterStatement;
import de.ifgi.envision.eps.core.EPSManagerStub.Start;
import de.ifgi.envision.eps.core.EPSManagerStub.Stop;

public class EPSManagerClientJSONWildcardTest {
	
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
//			
//			// 1. Start the service
			Start start = new Start();
			stub.start(start);
			
			// 2. Register statements
			// Statement definition: ...
			// 2.1 First, we create a new event type called AggregateEvent-...
			// 2.2 We create a new named window called AggregateWindow-...
			// 2.3 We register a statement that aggregates the observations and insert them into the named window.
			// 2.4 We register a second statement which contains a pattern to check the order of the aggregated values.
			String stm1 = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"Type\":\"MAX\", \"TimeSpan\":\"D\", \"Value\":{\"$gt\":\"7.7\"}}";
			String ruleMLStm_type1 = "<RuleML xmlns=\"http://ruleml.org/spec\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" " +
					"xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:ssn=\"http://purl.org/ifgi/ssn#\" xmlns:dul=\"http://purl.org/ifgi/dul#\" " +
					"xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\" xmlns:time=\"http://www.w3.org/2006/time/\">" +
					"	<Assert mapClosure=\"universal\">" +
					"		<Implies>" +
					"			<if>" +
					"				<And>" +
					"					<Atom>" +
					"						<Op>" +
					"							<Rel iri=\"ssn:SensingDevice\"/>" +
					"						</Op>" +
					"						<Var>a</Var>" +
					"						<Ind>urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054</Ind>" +
					"					</Atom>" +
					"					<Atom>" +
					"						<Op>" +
					"							<Rel iri=\"ssn:observes\"/>" +
					"						</Op>" +
					"						<Var>a</Var>" +
					"						<Ind>urn:ogc:def:phenomenon:OGC:1.0.30:waterflow</Ind>" +
					"					</Atom>" +
					"					<Atom>" +
					"						<Op>" +
					"							<Rel iri=\"ssn:hasValue\"/>" +
					"						</Op>" +
					"						<Var>a</Var>" +
					"						<Var>b</Var>" +
					"					</Atom>" +
					"					<Atom>" +
					"						<Op>" +
					"							<Rel iri=\"_:greaterThan\"/>" +
					"						</Op>" +
					"						<Var>b</Var>" +
					"						<Ind>7.0</Ind>" +
					"					</Atom>" +
					"				</And>" +
					"			</if>" +
					"			<then>" +
					"				<Atom>" +
					"					<Op>" +
					"						<Rel iri=\"rdf:type\"/>" +
					"					</Op>" +
					"					<Var>evt</Var>" +
					"					<Ind>http://purl.org/ifgi/water/flood#WaterLevelChange</Ind>" +
					"				</Atom>" +
					"			</then>" +
					"		</Implies>" +
					"	</Assert>" +
					"</RuleML>";
			
			String ruleMLStm_type2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><RuleML xmlns=\"http://ruleml.org/spec\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:ssn=\"http://purl.org/ifgi/ssn#\" xmlns:dul=\"http://purl.org/ifgi/dul#\" xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\" xmlns:time=\"http://www.w3.org/2006/time/\"><Assert mapClosure=\"universal\"><Implies><if><And><Atom><Op><Rel iri=\"ssn:hasValue\"/></Op><Var>a</Var><Ind>0.0</Ind></Atom><Atom><Op><Rel iri=\"_:value_1I_ago\"/></Op><Var>a</Var><Var>b</Var></Atom><Atom><Op><Rel iri=\"_:different\"/></Op><Var>b</Var><Ind>0.0</Ind></Atom><Atom><Op><Rel iri=\"_:value_2I_ago\"/></Op><Var>a</Var><Var>c</Var></Atom><Atom><Op><Rel iri=\"_:lessThan\"/></Op><Var>c</Var><Ind>0.0</Ind></Atom><Atom><Op><Rel iri=\"_:value_3I_ago\"/></Op><Var>a</Var><Ind>2.0</Ind></Atom><Atom><Op><Rel iri=\"ssn:SensingDevice\"/></Op><Var>a</Var><Ind>21</Ind></Atom><Atom><Op><Rel iri=\"ssn:observes\"/></Op><Var>a</Var><Ind>urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel</Ind></Atom></And></if><then><Atom><Op><Rel iri=\"rdf:type\"/></Op><Var>evt</Var><Ind>http://purl.org/ifgi/water/flood#WaterLevelChange</Ind></Atom></then></Implies></Assert></RuleML>";
			
			String ruleMLStm_type3 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><RuleML xmlns=\"http://ruleml.org/spec\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:ssn=\"http://purl.org/ifgi/ssn#\" xmlns:dul=\"http://purl.org/ifgi/dul#\" xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\" xmlns:time=\"http://www.w3.org/2006/time/\"><Assert mapClosure=\"universal\"><Implies><if><And><Atom><Op><Rel iri=\"ssn:hasValue\"/></Op><Var>a</Var><Ind>0.0</Ind></Atom><Atom><Op><Rel iri=\"ssn:observes\"/></Op><Var>a</Var><Ind>urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel</Ind></Atom><Atom><Op><Rel iri=\"rdf:label\"/></Op><Var>a</Var><Ind>SUM</Ind></Atom><Atom><Op><Rel iri=\"ssn:observationSamplingTime\"/></Op><Var>a</Var><Ind>D</Ind></Atom><Atom><Op><Rel iri=\"ssn:SensingDevice\"/></Op><Var>a</Var><Ind>8</Ind></Atom></And></if><then><Atom><Op><Rel iri=\"rdf:type\"/></Op><Var>evt</Var><Ind>http://purl.org/ifgi/water/flood#WaterLevelChange</Ind></Atom></then></Implies></Assert></RuleML>";
			
			String ruleMLStm_type4 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><RuleML xmlns=\"http://ruleml.org/spec\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:ssn=\"http://purl.org/ifgi/ssn#\" xmlns:dul=\"http://purl.org/ifgi/dul#\" xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\" xmlns:time=\"http://www.w3.org/2006/time/\"><Assert mapClosure=\"universal\"><Implies><if><And><Atom><Op><Rel iri=\"ssn:hasValue\"/></Op><Var>a</Var><Ind>0.0</Ind></Atom><Atom><Op><Rel iri=\"ssn:observes\"/></Op><Var>a</Var><Ind>urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel</Ind></Atom><Atom><Op><Rel iri=\"rdf:label\"/></Op><Var>a</Var><Ind>SUM</Ind></Atom><Atom><Op><Rel iri=\"ssn:observationSamplingTime\"/></Op><Var>a</Var><Ind>D</Ind></Atom><Atom><Op><Rel iri=\"ssn:SensingDevice\"/></Op><Var>a</Var><Ind>8</Ind></Atom><Atom><Op><Rel iri=\"_:value_1I_ago\"/></Op><Var>a</Var><Var>b</Var></Atom><Atom><Op><Rel iri=\"_:lessThan\"/></Op><Var>b</Var><Ind>3.0</Ind></Atom><Atom><Op><Rel iri=\"_:value_2I_ago\"/></Op><Var>a</Var><Var>c</Var></Atom><Atom><Op><Rel iri=\"_:greaterThan\"/></Op><Var>c</Var><Ind>288.0</Ind></Atom></And></if><then><Atom><Op><Rel iri=\"rdf:type\"/></Op><Var>evt</Var><Ind>http://purl.org/ifgi/water/flood#WaterLevelChange</Ind></Atom></then></Implies></Assert></RuleML>";
			
			//String stm1 = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"Value\":{\"$gt\":\"69.0\"}}";
			//String stm1 = "SELECT obs1, obs2, obs3 FROM pattern[ every(obs1=ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', value >70.0) -> obs2=ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', value >72.0) -> obs3=ObservationEvent(observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054', value >74.0))]";
//			
			// Event type
			String eventType = "http://purl.org/ifgi/water/flood#HighWaterLevel";
			
//			RegisterRuleMLStatement registerRuleMLStatement = new RegisterRuleMLStatement();
//			registerRuleMLStatement.setRuleMLStm(ruleMLStm_type2);
//			
//			if (stub.registerRuleMLStatement(registerRuleMLStatement).get_return()) {
//				System.out.println("Statement 1 registered.");
//			}
//			else {
//				System.out.println("Statement 1 not registered.");
//			}
			
			RegisterJSONStatement registerJSONStatement = new RegisterJSONStatement();
			registerJSONStatement.setJsonStm(stm1);
			registerJSONStatement.setEventType(eventType);
			
			if (stub.registerJSONStatement(registerJSONStatement).get_return()) {
				System.out.println("Statement 1 registered.");
			}
			else {
				System.out.println("Statement 1 not registered.");
			}
//			
//			RegisterStatement registerStatement = new RegisterStatement();
//			registerStatement.setStm(stm1);
//			registerStatement.setEventType(eventType);
//			if (stub.registerStatement(registerStatement).get_return()) {
//				System.out.println("Statement 1 registered.");
//			}
//			else {
//				System.out.println("Statement 1 not registered.");
//			}
			
			
//			
//			registerStatement.setStm(stm3);
//			if (stub.registerStatement(registerStatement).get_return()) {
//				System.out.println("Statement 3 registered: IF the waterflow is between 2.000 and 2.500 m3/s AND the water level exceeds 41,37 mdMA THEN a HighWaterLevel event is created for Drobeta Turnu Severin-Kladovo section (sensor-0015)");
//			}
//			else {
//				System.out.println("Statement 2 not registered");
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
			String value = "65";
			while (calCounter.getTime().before(calLimit.getTime())) {
				insertObservations(OBS_PROP_WATERFLOW, FOI_0054, timestamp, PROCEDURE_0054, value, "m3/s");
				calCounter.add(Calendar.SECOND, 10);
				timestamp = formatter.format(calCounter.getTime());
				//value = String.valueOf((Double.valueOf(value) + 40));
				value = String.valueOf((Double.valueOf(value) + 2));
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
