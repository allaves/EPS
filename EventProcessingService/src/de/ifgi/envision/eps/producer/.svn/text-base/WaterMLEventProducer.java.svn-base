package de.ifgi.envision.eps.producer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.Interval;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import de.ifgi.envision.eps.core.EPSManager;
import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.event.Event;
import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.event.Participant;
import de.ifgi.envision.eps.event.Sensor;
import de.ifgi.envision.eps.exception.SensorLocationException;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.GeogLocationType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.LatLonPointType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.QueryInfoType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.SiteInfoType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.SiteInfoType.SiteCode;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.TimeSeriesType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.UnitsType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.ValueSingleVariable;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.VariableInfoType;
import de.ifgi.envision.eps.model.org.cuahsi.waterml.VariableInfoType.VariableCode;

public class WaterMLEventProducer implements EventProducer {
	//private HashMap<String, Event> eventList;	// testing
	private String serviceUrl;
	
	public WaterMLEventProducer(String serviceUrl) {
		//eventList = new HashMap<String, Event>();
		this.serviceUrl = serviceUrl;
	}
	
	@Override
	public void send(Event event) {
		EventProcessingServiceProvider.getEPServiceProvider().getEPRuntime().sendEvent(event);
	}

	
	
	/**
	 * So far we do nothing with the schemaURL
	 * @param schemaURL
	 * @param observationCollection
	 */
	public void parseXml(URL schemaURL, File observationCollection) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(observationCollection);
			parseDocument(dom);
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
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.ifgi.envision.eps.manager.EventProducer#parseXml(java.net.URL, java.lang.String)
	 */
	@Override
	public void parseXml(URL schemaURL, String observationCollection) {
		// observationCollection String must be transformed into InputStream 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(new ByteArrayInputStream(observationCollection.getBytes()));
			parseDocument(dom);
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
		
	}
	
	/**
	 * <timeSeriesResponse>
	 *   </queryInfo>
	 *   </timeSeries>
	 *   </timeSeries>
	 *   ...
	 * </timeSeriesResponse>
	 * @param observationCollection
	 */
	public void parseDocument(Document dom) {	
		// root element
		Element timeSeriesResponse = dom.getDocumentElement();
		//Element firstChild = (Element) timeSeriesResponse.getFirstChild();
		// queryInfo
		//Element queryInfo = (Element) timeSeriesResponse.getElementsByTagName("queryInfo").item(0);
		//QueryInfoType queryInfoType = getQueryInfoType(queryInfo);
		// list of timeSeries
//		Node sibling = timeSeriesResponse.getFirstChild();
//		while (sibling != null) {
//			NodeList list = sibling.getChildNodes();
//			sibling = sibling.getNextSibling();
//		}
		NodeList timeSeriesList = timeSeriesResponse.getElementsByTagName("ns1:timeSeries");
		if (timeSeriesList != null && timeSeriesList.getLength() > 0) {
			for (int i = 0; i < timeSeriesList.getLength(); i++) {
				//if (timeSeriesList.item(i) != '\n')
				Element elem = (Element) timeSeriesList.item(i);
				try {
					getTimeSeriesType(elem);
				} catch (SensorLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//System.out.printf("%d events has been sent.\n", timeSeriesList.getLength());

	}

	private QueryInfoType getQueryInfoType(Element queryInfo) {
		// Not relevant info identified at queryInfo
		return null;
	}

	private TimeSeriesType getTimeSeriesType(Element timeSeries) throws SensorLocationException {
		TimeSeriesType timeSeriesType = new TimeSeriesType();
		// timeSeries @name
		timeSeriesType.setName(timeSeries.getAttribute("name"));
		// timeSeries > sourceInfo
		Element siteInfo = (Element) timeSeries.getElementsByTagName("ns1:sourceInfo").item(0);
		SiteInfoType siteInfoType = getSiteInfo(siteInfo);
		timeSeriesType.setSourceInfo(siteInfoType);
		// timeSeries > sourceInfo > site
		GeogLocationType geoLocation = siteInfoType.getGeoLocationGeogLocation();
		Sensor site = null;
		if (geoLocation instanceof LatLonPointType) {
			Coordinate c = new Coordinate(((LatLonPointType)geoLocation).getLatitude(), ((LatLonPointType)geoLocation).getLongitude());
			Point p = new GeometryFactory().createPoint(c);
			p.setSRID(Integer.valueOf(geoLocation.getSrs().split(":")[1]));
			site = new Sensor(String.valueOf(siteInfoType.getSiteCodeList().get(0).getSiteID()), p, geoLocation.getSrs(), siteInfoType.getSiteName());
		}
		else {
			throw new SensorLocationException();
		}
		// timeSeries > variable
		Element variable = (Element) timeSeries.getElementsByTagName("ns1:variable").item(0);
		VariableInfoType variableInfo = getVariableInfo(variable);
		timeSeriesType.setVariable(variableInfo);
		// Observed property - will be a URL pointing to a concept in our domain ontology in future versions
		String observedProperty = variableInfo.getVariableName();
		// Unit of measurement - will be a URL pointing to a concept in our application ontology in future versions
		String uom = variableInfo.getUnit().getUnitCode();
		// timeSeries > values
		Element values = (Element) timeSeries.getElementsByTagName("ns1:values").item(0);
		//timeSeriesType.setValueList(list)
		NodeList valueList = values.getElementsByTagName("ns1:value");
		if (valueList != null && valueList.getLength() > 0) {
			for (int i = 0; i < valueList.getLength(); i++) {
				Element v = (Element) valueList.item(i);
				ValueSingleVariable vsv = getValueSingleVariable(v);
				// timestamp
				Date timestamp = vsv.getDateTime();
				Interval temporalLocation = new Interval(timestamp.getTime(), timestamp.getTime());
				// Casting from string value to double value
				Double obsValue = Double.valueOf(v.getFirstChild().getNodeValue());
				// Create event
				Event event = new ObservationEvent(temporalLocation, new ArrayList<Participant>(), 
						site.getSpatialLocation(), site, obsValue, observedProperty, uom, serviceUrl, serviceUrl);
				// Send event
				send(event);
				//eventList.put(timeSeriesType.getName().concat("-event").concat(String.valueOf(eventList.size()+1)), event);
			}
		}
		return timeSeriesType;
	}

	private ValueSingleVariable getValueSingleVariable(Element value) {
		ValueSingleVariable vsv = new ValueSingleVariable(); 
		// Date sample -> 2011-10-14T10:00:00.000-05:00
		// We skip milliseconds and offset
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String dateTime = value.getAttribute("dateTime");
		try {
			vsv.setDateTime(formatter.parse(dateTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vsv;
	}

	private VariableInfoType getVariableInfo(Element variable) {
		VariableInfoType vit = new VariableInfoType();
		// variable > variableCode and @vocabulary
		Element variableCode = (Element) variable.getElementsByTagName("ns1:variableCode").item(0);
		List<VariableCode> codeList = new ArrayList<VariableCode>();
		VariableCode vc = new VariableCode();
		vc.setString(variableCode.getFirstChild().getNodeValue());
		vc.setVocabulary(variableCode.getAttribute("vocabulary"));
		codeList.add(vc);
		vit.setVariableCodeList(codeList);
		// variable > variableName
		Element variableName = (Element) variable.getElementsByTagName("ns1:variableName").item(0);
		vit.setVariableName(variableName.getFirstChild().getNodeValue());
		// variable > unit > unitCode
		Element unit = (Element) variable.getElementsByTagName("ns1:unit").item(0);
		Element unitCode = (Element) variable.getElementsByTagName("ns1:unitCode").item(0);
		UnitsType unitsType = new UnitsType();
		unitsType.setUnitCode(unitCode.getFirstChild().getNodeValue());
		vit.setUnit(unitsType);		
		return vit;
	}

	private SiteInfoType getSiteInfo(Element siteInfo) {
		SiteInfoType sit = new SiteInfoType();
		// timeSeries > sourceInfo > siteName
		String siteName = siteInfo.getElementsByTagName("ns1:siteName").item(0).getFirstChild().getNodeValue();
		sit.setSiteName(siteName);
		// timeSeries > sourceInfo > siteCode
		Integer siteId = Integer.valueOf((siteInfo.getElementsByTagName("ns1:siteCode").item(0).getFirstChild().getNodeValue()));
		List<SiteCode> siteCodeList = new ArrayList<SiteCode>();
		SiteCode siteCode = new SiteCode();
		siteCode.setSiteID(siteId);
		siteCodeList.add(siteCode);
		sit.setSiteCodeList(siteCodeList);
		// geoLocation > geogLocation
		Element geoLocation = (Element) siteInfo.getElementsByTagName("ns1:geoLocation").item(0);
		Element geogLocation = (Element) geoLocation.getElementsByTagName("ns1:geogLocation").item(0);
		GeogLocationType geogLocationType = getGeogLocationType(geogLocation);
		sit.setGeoLocationGeogLocation(geogLocationType);
		return sit;
	}

	/**
	 * In this version we only consider LatLonPointType which extends GeogLocationType.
	 * @param geogLocation
	 * @return
	 */
	private GeogLocationType getGeogLocationType(Element geogLocation) {
		LatLonPointType latLonPoint = new LatLonPointType();
		latLonPoint.setSrs(geogLocation.getAttribute("srs"));
		Element lat = (Element) geogLocation.getElementsByTagName("ns1:latitude").item(0);
		latLonPoint.setLatitude(Double.valueOf(lat.getFirstChild().getNodeValue()));
		Element lon = (Element) geogLocation.getElementsByTagName("ns1:longitude").item(0);
		latLonPoint.setLongitude(Double.valueOf(lon.getFirstChild().getNodeValue()));
		return latLonPoint;
	}

//	public HashMap<String, Event> getEventList() {
//		return eventList;
//	}
	
	
}
