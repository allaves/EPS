package de.ifgi.envision.eps.parser;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import de.ifgi.envision.eps.exception.MalformedRuleException;

public class JSONParser {

	private static Logger log = Logger.getLogger(JSONParser.class);

	private String[] eplStatements;
	private String value;
	private Value opValue;
	private String operator;
	private String observedProperty;
	private String sensorId;
	
	public JSONParser() {
		eplStatements = new String[]{"", ""};
	}
	
	/*
	 * Translates a statement encoded in JSON to Event Pattern Language (EPL)
	 */
	public String[] JSONToEPL(String jsonStm) throws MalformedRuleException {
		jsonStm = checkEqOperator(jsonStm);
		
		Gson gson = new Gson(); 
		EnStreaMRule enStreamRule = gson.fromJson(jsonStm, EnStreaMRule.class);
		
		// EnStreaM variables to EPL variables
		// observed property
		observedProperty = enStreamRule.getPhenomenon();
		// win:time()
		String window = enStreamRule.getTimeSpan();
		String windowPeriod = null;
		if (window != null) {
			windowPeriod = populateWindowPeriod(window);
		}
		
		// interval_1I_ago, interval_2I_ago, ...
		LinkedList<Value> intervals = enStreamRule.getIntervals();
		// Aggregate operators: avg(), min(), max(), count(), std(), sum()
		String aggregator = enStreamRule.getType();
		String eplAggregator = null;
		// TODO: check all aggregators
		if (aggregator != null) {
			eplAggregator = populateAggregator(aggregator);
		}
		
		// The opValue value can contain an operator like <, >, ==, or !=
		opValue = enStreamRule.getValue();
		if (opValue == null) {
			throw new MalformedRuleException("No Value parameter found in the JSON rule.");
		}
		
		// it should be equivalent to procedure
		sensorId = enStreamRule.getSensor();
		
		eplStatements[1] = "SELECT ";
		
		// The presence of intervals requires to create a pattern
		// We assume that the presence of intervals will be consecutive, i.e. Value_1I_ago, Value_3I_ago would be wrong
		// We assume that if there are intervals, the Sensor parameter applies for all the intervals - EnStreaM only allows to define one sensor
		// We assume that the Sensor, Phenomenon and Value properties are mandatories 
		// We assume that Type and TimeSpan go always together, but are not mandatory
		if (intervals.size() > 0) {
			// We add the the current Value at the end of the queue
			intervals.addLast(opValue);
			int i = 1;
			for (; i < intervals.size(); i++) {
				eplStatements[1] = eplStatements[1].concat("obs").concat(Integer.toString(i)).concat(", ");
			}
			eplStatements[1] = eplStatements[1].concat("obs").concat(Integer.toString(i)).concat(" FROM pattern[ every(");
			
			if (window == null) {
				// Type and TimeSpan are no present in the rule
				// We iterate the intervals list from "past to present", i.e. older intervals go first in the pattern
				int observationId = 0;
				while (!intervals.isEmpty()) {
					observationId++;
					opValue = intervals.removeFirst();
					populateValueAndOperator();
					
					eplStatements[1] = eplStatements[1].concat("obs").concat(Integer.toString(observationId)).concat("=ObservationEvent(");
					//Phenomenon (observedProperty) is mandatory
					eplStatements[1] = eplStatements[1].concat("observedProperty = '").concat(observedProperty).concat("'");
					// The sensor id is mandatory
					eplStatements[1] = eplStatements[1].concat(", observer.id = '").concat(sensorId).concat("'");
					eplStatements[1] = eplStatements[1].concat(", value ").concat(operator).concat(value);
					if (intervals.size() >= 1) {
						eplStatements[1] = eplStatements[1].concat(") -> ");
					}
					else {
						eplStatements[1] = eplStatements[1].concat(")");
					}
				}
			}
			else {
				// Type and TimeSpan are present in the rule
				// First, we create the INSERT statement
				eplStatements[0] = "INSERT INTO AggregationEvent SELECT temporalLocation, observedProperty, spatialLocation, observer, source, unitOfMeasure, url, ".concat(eplAggregator) +
						"(value) as aggregateValue, '".concat(eplAggregator).concat("' as aggregationType, '").concat(windowPeriod).concat("' as aggregationTimeSpan FROM ObservationEvent(" +
						"observedProperty = '".concat(observedProperty)).concat("', observer.id = '").concat(sensorId).concat("').win:time_batch(").concat(windowPeriod).concat(") ");
				// We iterate the intervals list from "past to present", i.e. older intervals go first in the pattern
				int observationId = 0;
				while (!intervals.isEmpty()) {
					observationId++;
					opValue = intervals.removeFirst();
					// Deserialize operator and value
					populateValueAndOperator();
					
					eplStatements[1] = eplStatements[1].concat("obs").concat(Integer.toString(observationId)).concat("=AggregationEvent(");
					//Phenomenon (observedProperty) is mandatory
					eplStatements[1] = eplStatements[1].concat("observedProperty = '").concat(observedProperty).concat("'");
					// The sensor id is mandatory
					eplStatements[1] = eplStatements[1].concat(", observer.id = '").concat(sensorId).concat("'");
					eplStatements[1] = eplStatements[1].concat(", aggregateValue ").concat(operator).concat(value);
					if (intervals.size() >= 1) {
						eplStatements[1] = eplStatements[1].concat(") -> ");
					}
					else {
						eplStatements[1] = eplStatements[1].concat(")");
					}
				}
			}
			eplStatements[1] = eplStatements[1].concat(")]");
		}
		// No intervals
		else {
			populateValueAndOperator();
			if (window != null) {
				eplStatements[1] = eplStatements[1].concat("*, ").concat(eplAggregator).concat("(value) as aggregateValue FROM ");
			}
			else {
				eplStatements[1] = eplStatements[1].concat("* FROM ");
			}
			// No pattern and Phenomenon (observedProperty) and value are mandatory
			eplStatements[1] = eplStatements[1].concat("ObservationEvent(");
			// The observed property is present in the rule
			eplStatements[1] = eplStatements[1].concat("observedProperty = '").concat(observedProperty).concat("'");
			// The sensor id is present in the rule
			eplStatements[1] = eplStatements[1].concat(", observer.id = '").concat(sensorId).concat("'");
			if (window != null) { 
				// Tipe and TimeSpan are present in the rule
				eplStatements[1] = eplStatements[1].concat(").win:time_batch(").concat(windowPeriod).concat(") ");
				//eplStatements[1] = eplStatements[1].concat(", aggregateValue ").concat(operator).concat(value).concat(") ");
				eplStatements[1] = eplStatements[1].concat("HAVING ").concat(eplAggregator).concat("(value) ").concat(operator).concat(value);
			}
			else {
				eplStatements[1] = eplStatements[1].concat(", value ").concat(operator).concat(value).concat(") ");
			}
		}
		for (String str : eplStatements) {
			log.info(str);
		}
		return eplStatements;
	}

	
	/*
	 * Checks if there is any value or interval with no $eq operator and adds it to the JSON statement
	 */
	public String checkEqOperator(String jsonStm) {
		Pattern pattern = null;
		Matcher matcher = null;
		String replacement = null;
		String substr = null;
		
		// Pattern for values and intervals with no $eq operator
		pattern = Pattern.compile("\"Value(_(1)?\\dI_ago)?\":\"(\\d)+\\.(\\d)+\"");
		matcher = pattern.matcher(jsonStm);
		while (matcher.find()) {
			substr = matcher.group();
			System.out.println(matcher.group());
			replacement = substr.replaceAll(":", ":\\{\"\\$eq\":");
			replacement = replacement.concat("}");
			jsonStm = jsonStm.replace(substr, replacement);
		}
		return jsonStm;
	}

	private void populateValueAndOperator() throws MalformedRuleException {
		// Deserialize operator and value
		if ((value = opValue.get$gt()) != null) {
			operator = ">";
		}
		else if ((value = opValue.get$lt()) != null) {
			operator = "<";
		}
		else if ((value = opValue.get$ne()) != null) {
			operator = "!=";
		}
		else if ((value = opValue.get$eq()) != null){
			operator = "=";
		}
		else {
			throw new MalformedRuleException("Wrong value operator.");
		}
	}

	private String populateAggregator(String aggregator) throws MalformedRuleException {
		if (aggregator.equalsIgnoreCase("AVG") || aggregator.equalsIgnoreCase("MIN") || aggregator.equalsIgnoreCase("MAX") || aggregator.equalsIgnoreCase("SUM")) {
			return aggregator.toLowerCase();
		}
		else if (aggregator.equalsIgnoreCase("CNT")) {
			return "count";
		}
		else if (aggregator.equalsIgnoreCase("STD")) {
			return "stddev";
		}
		else {
			throw new MalformedRuleException("Wrong aggregation type!");
		}
	}

	private String populateWindowPeriod(String window) throws MalformedRuleException {
		if (window.equalsIgnoreCase("D")) {
			return "1 day";
		}
		else if (window.equalsIgnoreCase("3D")) {
			return "3 days";
		}
		else if (window.equalsIgnoreCase("W")) {
			return "1 week";
		}
		else if (window.equalsIgnoreCase("M")) {
			return "1 month";
		}
		else {
			throw new MalformedRuleException("Wrong TimeSpan!");
		}
	}
	
	
}
