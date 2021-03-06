package de.ifgi.envision.eps.producer;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.n52.oxf.OXFException;
import org.n52.oxf.feature.OXFFeature;
import org.n52.oxf.feature.OXFFeatureCollection;
import org.n52.oxf.feature.dataTypes.OXFMeasureType;
import org.n52.oxf.feature.dataTypes.OXFPhenomenonPropertyType;
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.xmlbeans.parser.XMLHandlingException;

import com.vividsolutions.jts.geom.Point;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.event.Event;
import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.event.Participant;
import de.ifgi.envision.eps.event.Sensor;
import de.ifgi.envision.eps.parser.OMParser;

public class SOSEventProducer implements EventProducer {
	private static Logger log = Logger.getLogger(SOSEventProducer.class);
	
	private HashMap<String, Date> dateOfLastObservations;
	
	private int observationNumber;
	private String serviceUrl;
	
	public SOSEventProducer(String serviceUrl) {
		//eventList = new HashMap<String, Event>();
		//epServiceProvider = EventProcessingServiceProvider.getEPServiceProvider();
		dateOfLastObservations = new HashMap<String, Date>();
		this.serviceUrl = serviceUrl;
	}
	
	public Date getOldestDateOfLastObservations() {
		Date oldestDate = new Date();
		log.info("\n\ndateOfLastObservations value collection:");
		log.info(dateOfLastObservations.values().toString());
		for (Date d : dateOfLastObservations.values()) {
			if (d.before(oldestDate)) {
				oldestDate = d;
			}
		}
		return oldestDate;
	}
	

	@Override
	public void send(Event event) {
		EventProcessingServiceProvider.getEPServiceProvider().getEPRuntime().sendEvent(event);
	}

	@Override
	public void parseXml(URL schemaURL, String observationCollection) {
		
	}

	public void parseXml(String source, OperationResult observationCollection) {
		OMParser parser = new OMParser();
		observationCollection.getIncomingResult();
		OXFFeatureCollection obsCollection = null;
		Iterator<OXFFeature> it = null;
		String procedure = null;
		String dateStr = null;
		Date timestamp = null;
		Interval temporalLocation = null;  		
		
		try {
			//log.info(observationCollection.toString());
			// get current context classloader
			ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
			// then alter the class-loader (but which one ? the one used to load this class itself) with:
			Thread.currentThread().setContextClassLoader(SOSEventProducer.class.getClassLoader());
			
			obsCollection = parser.unmarshalFeatures(observationCollection);
			
			// restore the class loader to its original value:
			Thread.currentThread().setContextClassLoader(contextClassloader);
			
			this.observationNumber = obsCollection.size();
			log.info(this.observationNumber);
			it = obsCollection.iterator();
		} catch (OXFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLHandlingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (it.hasNext()) {
			OXFFeature obs = it.next();
			// Procedure - Sensor id
			procedure = (String) obs.getAttribute("procedure");
			dateStr = obs.getAttribute("samplingTime").toString();
			// Time zone management
			if (dateStr.contains("+") || dateStr.contains("-")) {
				dateStr = dateStr.concat("00");
			}
			try {
				//temporalLocation = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateStr);
				timestamp = new SimpleDateFormat("dd.M.yyyy HH:mm:ss.SSS").parse(dateStr);
				temporalLocation = new Interval(timestamp.getTime(), timestamp.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// The hash map is initialized with the sensor ID and the date of the last observation set to "0"
			if (dateOfLastObservations.get(procedure) == null) {
				dateOfLastObservations.put(procedure, new Date(0));
			}
			// we will send a new event observation for a specific sensor only if the last observation value was measured before the current one
			if (dateOfLastObservations.get(procedure).before(temporalLocation.getEnd().toDate())) {
				dateOfLastObservations.put(procedure, temporalLocation.getEnd().toDate());
				// Check if we the geometry contains the SRID
				OXFFeature point = (OXFFeature) obs.getAttribute("featureOfInterest");
				
				Point spatialLocation = (Point) point.getAttribute("location");
				// We assume that the spatial location of the sensor corresponds to a point. ID and name missing!
				Sensor sensor = new Sensor(procedure, spatialLocation, Integer.toString(spatialLocation.getSRID()), procedure);
				// The sensor is added to the list of participants in the event
				List<Participant> participants = new ArrayList<Participant>();
				participants.add(sensor);
				// We assume that the result value is of type OXFMeasureType
				OXFMeasureType result = (OXFMeasureType) obs.getAttribute("result");
				Double value = result.getValue();
				// Observed property. Is "observedProperty" the name of the attribute?
				OXFPhenomenonPropertyType obsProp = (OXFPhenomenonPropertyType) obs.getAttribute("observedProperty");
				String observedProperty = obsProp.getURN();
				// Unit of measure - test
				String unitOfMeasure = result.getUomIdentifier();
				Event event = new ObservationEvent(temporalLocation, participants, spatialLocation, sensor, value, observedProperty, unitOfMeasure, source, serviceUrl);
				
				log.info("***Observation event sent***");
				log.info("Timestamp: ".concat(dateStr));
				log.info("Spatial location: ".concat(spatialLocation.toString()));
				log.info("Sensor: ".concat(sensor.getName()));
				log.info("Observed property: ".concat(observedProperty));
				log.info("Value: ".concat(value + unitOfMeasure));
				send(event);
				//log.info("SOSObservationEvent sent!");
			}
		}
		
	}
	
	
}
