package de.ifgi.envision.eps.push;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;


import org.joda.time.Interval;
import org.junit.Test;
import org.openrdf.repository.RepositoryException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class TestMessageBuilder {

	
	@Test
	public void testCreateRDFMessage() {
		try {
			MessageBuilder mb = new MessageBuilder();
            String sensorId = "urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002";
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
    					"<ogc:TM_During>" +
    						"<ogc:PropertyName>om:samplingTime</ogc:PropertyName>" +
    						"<gml:TimePeriod>" +
    							"<gml:beginPosition>2006-04-05T17:44:15+00:00</gml:beginPosition>" +
    							"<gml:endPosition>2006-05-15T17:44:15+00:00</gml:endPosition>" +
    						"</gml:TimePeriod>" +
    					"</ogc:TM_During>" +
    				"</eventTime>" +
    				 "<observedProperty>urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel</observedProperty>" +
    				 "<responseFormat>text/xml;subtype=&quot;om/1.0.0&quot;</responseFormat>" +
    				 "</GetObservation>";
           
            String eventPattern = "SELECT * " +
            		"FROM ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', " +
            		"observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 7.7)";
            
//            String eventPattern = "SELECT * " +
//            		"FROM pattern[every (waterflow_obs=SOSObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0006', " +
//            		"observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterflow', value < 3000) ->" +
//            		" waterlevel_obs=SOSObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0001'," +
//            		" observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value > 69.81)) ] " +
//            		"WHERE waterlevel_obs.time.between(waterflow_obs.time, waterflow_obs.time.plus(23 hours 59 min))";
          
            Coordinate coordinate = new Coordinate(-74.43293, 42.31481);
            Point p = new GeometryFactory().createPoint(coordinate);
            p.setSRID(4326);
            Date begin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse("2006-05-01T20:00:00+0000");
            Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse("2006-05-14T20:00:00+0000");
            Interval temporalLocation = new Interval(begin.getTime(), end.getTime());
            
            ArrayList<String> observedPropertyList = new ArrayList<String>();
            observedPropertyList.add("urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel");
            observedPropertyList.add("urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel");
            ArrayList<String> sourceUrlList = new ArrayList<String>();
            sourceUrlList.add("http://194.102.135.23/EnvisionSOS/sos");
            sourceUrlList.add("http://194.102.135.23/EnvisionSOS/sos");
            ArrayList<String> sourceRequestList = new ArrayList<String>();
            sourceRequestList.add(source);
            sourceRequestList.add(source);
            ArrayList<String> sensorIdList = new ArrayList<String>();
            sensorIdList.add(sensorId);
            sensorIdList.add(sensorId);
            
			String msg = mb.createRDFMessage(p, temporalLocation, observedPropertyList, "http://wsmls.googlecode.com/svn/trunk/local/water/0.5/flood.rdf#HighWaterLevel", 
					eventPattern, sourceUrlList, sourceRequestList, sensorIdList, null);
			System.out.println(msg);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

}
