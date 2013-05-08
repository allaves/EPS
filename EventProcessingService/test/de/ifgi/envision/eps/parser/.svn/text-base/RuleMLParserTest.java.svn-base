package de.ifgi.envision.eps.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

import de.ifgi.envision.eps.exception.MalformedRuleException;

public class RuleMLParserTest {

	@Test
	public void testRuleMLToEPL() {
		//String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006\", \"Phenomenon\":\"waterflow\", \"Type\":\"MAX\", \"TimeSpan\":\"3D\", \"Value\":{\"$lt\":\"69.1\"}}";
		//String jsonStm = "{\"phenomenon\":\"waterlevel\", \"type\":\"AVG\", \"timespan\":\"D\", \"value\":{\"gt\":\"69.1\"}}";
		String jsonStm = "{\"$from\":\"SensorAggregate\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"Value\":{\"$lt\":\"3043.3\"}, \"Value_1I_ago\":{\"$lt\":\"3291.0\"}}";
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
				"						<Ind>8</Ind>" +
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
		RuleMLParser parser = new RuleMLParser();
		
		try {
			Pair<String, String> result = parser.ruleMLToEPL(ruleMLStm_type4);
			System.out.println(result.getJsonStm());
			System.out.println(result.getEventType());
		} catch (MalformedRuleException e) {
			e.printStackTrace();
		}
	}

}
