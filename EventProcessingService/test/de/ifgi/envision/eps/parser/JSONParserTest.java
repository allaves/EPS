package de.ifgi.envision.eps.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

import de.ifgi.envision.eps.exception.MalformedRuleException;

public class JSONParserTest {

	@Test
	public void testJSONToEPL() {
		//String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006\", \"Phenomenon\":\"waterflow\", \"Type\":\"MAX\", \"TimeSpan\":\"3D\", \"Value\":{\"$lt\":\"69.1\"}}";
		String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006\", \"Phenomenon\":\"waterflow\", \"Value\":{\"$lt\":\"69.1\"}}";
		//String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"Type\":\"MAX\", \"TimeSpan\":\"D\", \"Value\":{\"$gt\":\"7.7\"}}";
		//String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006\", \"Phenomenon\":\"waterflow\", \"Value\":{\"$lt\":\"3043.3\"}, \"Value_1I_ago\":{\"$lt\":\"3291.0\"}}";
		//String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"Value\":{\"$lt\":\"2981.3\"}, \"Value_1I_ago\":{\"$gt\":\"2939.0\"}, \"Value_2I_ago\":{\"$lt\":\"3000.0\"}}";
		//String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"TimeSpan\":\"D\", \"Type\":\"SUM\", \"Value\":\"72.0\", \"Value_1I_ago\":\"0.0\", \"Value_2I_ago\":\"666.0\"}";
		//String jsonStm = "{\"$from\":\"SensorAggregate\",\"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel\",\"Sensor\":\"21\",\"Value\":{\"$eq\":\"0.0\"},\"Value_1I_ago\":{\"$ne\":\"0.0\"},\"Value_2I_ago\":{\"$lt\":\"0.0\"}}";
		//String jsonStm = "{\"$from\":\"SensorAggregate\",\"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel\",\"Sensor\":\"8\",\"Type\":\"SUM\",\"TimeSpan\":\"D\",\"Value\":{\"$eq\":\"0.0\"},\"Value_1I_ago\":{\"$lt\":\"3.0\"},\"Value_2I_ago\":{\"$gt\":\"288.0\"}}";
		JSONParser parser = new JSONParser();
		try {
			for (String str : parser.JSONToEPL(jsonStm)) {
				System.out.println(str);
			}
		} catch (MalformedRuleException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void testCheckEqOperator() {
//		String jsonStm = "{\"$from\":\"SensorAggregate\", \"Sensor\":\"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0054\", \"Phenomenon\":\"urn:ogc:def:phenomenon:OGC:1.0.30:waterflow\", \"TimeSpan\":\"D\", \"Type\":\"SUM\", \"Value\":\"72.0\", \"Value_1I_ago\":{\"$gt\":\"0.0\"}, \"Value_2I_ago\":\"666.0\"}";
//		JSONParser parser = new JSONParser();
//		System.out.println(parser.checkEqOperator(jsonStm));
//	}

}
