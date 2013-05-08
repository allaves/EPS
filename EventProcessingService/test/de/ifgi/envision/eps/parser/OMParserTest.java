package de.ifgi.envision.eps.parser;

import static org.junit.Assert.*;

import org.junit.Test;
import org.n52.oxf.OXFException;
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.xmlbeans.parser.XMLHandlingException;

import de.ifgi.envision.eps.service.SOS;

public class OMParserTest {
	
	private SOS service = null;
	private OMParser parser = null;
	
	private static String SERVICE_URL = "http://194.102.135.23/EnvisionSOSpublic/sos"; 
	private static String OFFERING = "GAUGE_HEIGHT";
	private static String OBSERVED_PROPERTY = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
	private static String GET_OBSERVATIONS_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<GetObservation xmlns=\"http://www.opengis.net/sos/1.0\" " +
			"xmlns:ows=\"http://www.opengis.net/ows/1.1\" " +
			"xmlns:gml=\"http://www.opengis.net/gml\" " +
			"xmlns:ogc=\"http://www.opengis.net/ogc\" " +
			"xmlns:om=\"http://www.opengis.net/om/1.0\" " +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
			"xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 http://schemas.opengis.net/sos/1.0.0/sosGetObservation.xsd\" " +
			"service=\"SOS\" version=\"1.0.0\" srsName=\"urn:ogc:def:crs:EPSG::4326\">" +
			"<offering>" + OFFERING + "</offering>" +
			"<eventTime>" +
				"<ogc:TM_During>" +
					"<ogc:PropertyName>om:samplingTime</ogc:PropertyName>" +
					"<gml:TimePeriod>" +
						"<gml:beginPosition>2012-11-05T12:00:00.000Z</gml:beginPosition>" +
						"<gml:endPosition>2012-11-08T14:00:00.000Z</gml:endPosition>" +
					"</gml:TimePeriod>" +
				"</ogc:TM_During>" +
			"</eventTime>" +
			 "<observedProperty>" + OBSERVED_PROPERTY + "</observedProperty>" +
			 "<responseFormat>text/xml;subtype=&quot;om/1.0.0&quot;</responseFormat>" +
			 "</GetObservation>";
	
	public OMParserTest() {
		service = new SOS(SERVICE_URL, OFFERING, OBSERVED_PROPERTY);
		parser = new OMParser();
	}

	@Test
	public void test() {
		OperationResult or = service.getObservations(SERVICE_URL, GET_OBSERVATIONS_REQUEST);
		try {
			parser.unmarshalFeatures(or);
		} catch (OXFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLHandlingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
