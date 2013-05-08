package de.ifgi.envision.eps.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import de.ifgi.envision.eps.exception.MalformedRuleException;

public class RuleMLParser {
	
	private static Logger log = Logger.getLogger(RuleMLParser.class);
	
	private Gson gson;
	private String jsonStm;
	private EnStreaMRule rule;
	
	public RuleMLParser() {
		gson = new Gson(); 
	}

	/*
	 * Translates a statement encoded in RuleML to JSON
	 */
	public Pair<String, String> ruleMLToEPL(String ruleMLStm) throws MalformedRuleException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Pair<String,String> result = null;
		try {
			builder = dbf.newDocumentBuilder();
			Document dom = builder.parse(new ByteArrayInputStream(ruleMLStm.getBytes()));
			result = parseDocument(dom);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Assumptions (if):
	 * - The rule contains ONE and only sensor (ssn:SensingDevice)
	 * - The rule contains ONE value (ssn:hasValue) and one arithmetic operator (e.g. _:greaterThan)
	 * - The rule may contain one or more intervals (...)
	 * 
	 * Assumptions (then):
	 * - The rule contains ONE and only event type (rdf:type)
	 */
	private Pair<String, String> parseDocument(Document dom) throws MalformedRuleException {
		rule = new EnStreaMRule();
		
		HashMap<String, Integer> variablePosition = new HashMap<String, Integer>();
		
		String[] operators = new String[11];
		String[] values = new String[11];
		
		for (int i = 0; i <= 10; i++) {
			operators[i] = "";
			values[i] = "";
		}
		
		String observedProperty = null;
		String sensorId = null;
		String var = null;
		String operator = null;
		String aggregateType = null;
		String aggregateTimeSpan = null;
		String eventType = null;
		
		boolean isObservedPropertyNode = false;
		boolean isSensorIdNode = false;
		boolean isEventTypeNode = false;
		boolean isAggregateTypeNode = false;
		boolean isAggregateTimeSpanNode = false;
		
		int varCont = -1;
		int valueIterator = -1;
		
		Pair<String, String> result = new Pair<String, String>();
		rule.set$from("SensorAggregate");
		
		// List of Atom nodes
		NodeList atomNodes = dom.getElementsByTagName("Atom");
		for (int i = 0; i < atomNodes.getLength(); i++) {
			boolean isValueNode = false;
			boolean isOperatorNode = false;
			// Traverse Atom nodes 
			Node atom = atomNodes.item(i);		
			NodeList atomNodeChilds = atom.getChildNodes();
			for (int j = 0; j < atomNodeChilds.getLength(); j++) {
				varCont = 0;
				// Traverse Atom childs
				Node atomChild = atomNodeChilds.item(j);
				if (atomChild.getNodeName().equalsIgnoreCase("Op")) {
					// Traverse Op childs
					NodeList opNodeChilds = atomChild.getChildNodes();
					for (int k = 0; k < opNodeChilds.getLength(); k++) {
						Node opChild = opNodeChilds.item(k);
						if (opChild.getNodeName().equalsIgnoreCase("Rel")) {
							String relAttr = opChild.getAttributes().getNamedItem("iri").getTextContent();
							// OBSERVED PROPERTY
							if (relAttr.equalsIgnoreCase("ssn:observes")) {
								// Observed property node
								isObservedPropertyNode = true;
							}
							// SENSOR
							else if (relAttr.equalsIgnoreCase("ssn:SensingDevice")) {
								// Sensor Id node
								isSensorIdNode = true;
							}
							// VALUE
							else if (relAttr.equalsIgnoreCase("ssn:hasValue")) {
								// Value node
								valueIterator = 0;
								isValueNode = true;
							}
							// INTERVALS
							else if (relAttr.equalsIgnoreCase("_:value_1I_ago")) {
								valueIterator = 1;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_2I_ago")) {
								valueIterator = 2;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_3I_ago")) {
								valueIterator = 3;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_4I_ago")) {
								valueIterator = 4;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_5I_ago")) {
								valueIterator = 5;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_6I_ago")) {
								valueIterator = 6;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_7I_ago")) {
								valueIterator = 7;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_8I_ago")) {
								valueIterator = 8;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_9I_ago")) {
								valueIterator = 9;
								isValueNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:value_10I_ago")) {
								valueIterator = 10;
								isValueNode = true;
							}
							// OPERATORS
							else if (relAttr.equalsIgnoreCase("_:greaterThan")) {
								// Operator node
								operator = "$gt";
								isOperatorNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:different")) {
								// Operator node
								operator = "$ne";
								isOperatorNode = true;
							}
							else if (relAttr.equalsIgnoreCase("_:lessThan")) {
								// Operator node
								operator = "$lt";
								isOperatorNode = true;
							}
							// AGGREGATE TYPE
							else if (relAttr.equalsIgnoreCase("rdf:label")) {
								// Type node
								isAggregateTypeNode = true;
							}
							// AGGREGATE TIMESPAN
							else if (relAttr.equalsIgnoreCase("ssn:observationSamplingTime")) {
								// TimeSpan node
								isAggregateTimeSpanNode = true;
							}
							else if (relAttr.equalsIgnoreCase("rdf:type")) {
								// Event type
								isEventTypeNode = true;
							}
							
						}
						
					}
				}
				else if (atomChild.getNodeName().equalsIgnoreCase("Var")) {
					varCont++;
					var = atomChild.getTextContent();
					if (isOperatorNode) {
						// Adds the operator to the corresponding position in the array
						operators[valueIterator] = operator;
						variablePosition.put(var, valueIterator);
					}
					else if (isValueNode && varCont == 2) {
						variablePosition.put(var, valueIterator);
					}
				}
				else if (atomChild.getNodeName().equalsIgnoreCase("Ind")) {
					if (isObservedPropertyNode) {
						// Add observed property
						observedProperty = atomChild.getTextContent();
						rule.setPhenomenon(observedProperty);
						isObservedPropertyNode = false;
					}
					else if (isSensorIdNode) {
						// Add sensor ID
						sensorId = atomChild.getTextContent();
						rule.setSensor(sensorId);
						isSensorIdNode = false;
					}
					else if (isValueNode) {
						values[valueIterator] = atomChild.getTextContent();
						operators[valueIterator] = "$eq";
					}
					else if (isOperatorNode) {
						values[variablePosition.get(var)] = atomChild.getTextContent();
					}
					else if (isAggregateTypeNode) {
						aggregateType = atomChild.getTextContent();
						rule.setType(aggregateType);
						isAggregateTypeNode = false;
					}
					else if (isAggregateTimeSpanNode) {
						aggregateTimeSpan = atomChild.getTextContent();
						rule.setTimeSpan(aggregateTimeSpan);
						isAggregateTimeSpanNode = false;
					}
					else if (isEventTypeNode) {
						// Add event type to the result
						eventType = atomChild.getTextContent();
						result.setEventType(eventType);
						isEventTypeNode = false;
					}
				}
				
			}
		}
	
		// Set Values and intervals
		Value v = new Value(operators[0], values[0]);
		rule.setValue(v);
		
		if (!operators[1].isEmpty()) {
			v = new Value(operators[1], values[1]);
			rule.setValue_1I_ago(v);
		}
		if (!operators[2].isEmpty()) {
			v = new Value(operators[2], values[2]);
			rule.setValue_2I_ago(v);
		}
		if (!operators[3].isEmpty()) {
			v = new Value(operators[3], values[3]);
			rule.setValue_3I_ago(v);
		}
		if (!operators[4].isEmpty()) {
			v = new Value(operators[4], values[4]);
			rule.setValue_4I_ago(v);
		}
		if (!operators[5].isEmpty()) {
			v = new Value(operators[5], values[5]);
			rule.setValue_5I_ago(v);
		}
		if (!operators[6].isEmpty()) {
			v = new Value(operators[6], values[6]);
			rule.setValue_6I_ago(v);
		}
		if (!operators[7].isEmpty()) {
			v = new Value(operators[7], values[7]);
			rule.setValue_7I_ago(v);
		}
		if (!operators[8].isEmpty()) {
			v = new Value(operators[8], values[8]);
			rule.setValue_8I_ago(v);
		}
		if (!operators[9].isEmpty()) {
			v = new Value(operators[9], values[9]);
			rule.setValue_9I_ago(v);
		}
		if (!operators[10].isEmpty()) {
			v = new Value(operators[10], values[10]);
			rule.setValue_10I_ago(v);
		}
		
		// Finish JSON statement
		jsonStm = gson.toJson(rule);
		// Add the statements to the result pair
		result.setJsonStm(jsonStm);				
		
		return result;
	}

	
	

}
