package de.ifgi.envision.eps.push;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.joda.time.Interval;
import org.junit.Test;
import org.openrdf.repository.RepositoryException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class TestEventPusher {
	
	
	private String createRDFMessage() {
		String msg = null;
		try {
			MessageBuilder mb = new MessageBuilder();
			Coordinate coordinate = new Coordinate(42.31481, -74.43293);
            Point p = new GeometryFactory().createPoint(coordinate);
            p.setSRID(4326);
            Date date = new SimpleDateFormat("dd.M.yyyy HH:mm:ss").parse("05.10.2012 17:33:25");
            Interval temporalLocation = new Interval(date.getTime(), date.getTime());
            String source = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    				"<GetObservation xmlns=\"http://www.opengis.net/sos/1.0\" " +
    				"xmlns:ows=\"http://www.opengis.net/ows/1.1\" " +
    				"xmlns:gml=\"http://www.opengis.net/gml\" " +
    				"xmlns:ogc=\"http://www.opengis.net/ogc\" " +
    				"xmlns:om=\"http://www.opengis.net/om/1.0\" " +
    				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
    				"xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 http://schemas.opengis.net/sos/1.0.0/sosGetObservation.xsd\" " +
    				"service=\"SOS\" version=\"1.0.0\" srsName=\"urn:ogc:def:crs:EPSG::4326\">" +
    				"<offering>GAUGE_HEIGHT</offering>" +
    				"<eventTime>" +
    				    "<ogc:TM_Equals>" +
    				      "<ogc:PropertyName>om:samplingTime</ogc:PropertyName>" +
    				      "<gml:TimeInstant>" +
    				        "<gml:timePosition>latest</gml:timePosition>" +
    				      "</gml:TimeInstant>" +
    				    "</ogc:TM_Equals>" +
    				 "</eventTime>" +
    				 "<observedProperty>urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel</observedProperty>" +
    				 "<responseFormat>text/xml;subtype=&quot;om/1.0.0&quot;</responseFormat>" +
    				 "</GetObservation>";
            String eventPattern = "SELECT waterflow_obs, waterlevel_obs " +
            		"FROM pattern[every (waterflow_obs=SOSObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006', " +
            		"observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value < 3000) ->" +
            		" waterlevel_obs=SOSObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0001'," +
            		" observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.81)) ] " +
            		"WHERE waterlevel_obs.time.between(waterflow_obs.time, waterflow_obs.time.plus(23 hours 59 min))";
            ArrayList<String> observedPropertyList = new ArrayList<String>();
            observedPropertyList.add("urn:ogc:def:phenomenon:OGC:1.0.30:waterflow");
            observedPropertyList.add("urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel");
            LinkedHashMap<String, String> observationCollectionMap = new LinkedHashMap<String, String>();
            observationCollectionMap.put(source, "http://194.102.135.23/EnvisionSOS/sos");
            ArrayList<String> sensorIdList = new ArrayList<String>();
            sensorIdList.add("urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006");
            sensorIdList.add("urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0001");
            
			//msg = mb.createRDFMessage(p, temporalLocation, observedPropertyList, "http://purl.org/ifgi/water/flood#HighWaterLevel", eventPattern, observationCollectionMap, sensorIdList, null);
			//System.out.println(msg);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Test
	public void testPush() {
		String msg = createRDFMessage();
		EventPusher.getEventPusher().initConnection();
		EventPusher.getEventPusher().push(msg);
		EventPusher.getEventPusher().closeConnection();
	}

}
