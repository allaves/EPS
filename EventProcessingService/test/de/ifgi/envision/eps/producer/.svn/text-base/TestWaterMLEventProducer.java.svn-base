package de.ifgi.envision.eps.producer;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.soda.EPStatementObjectModel;
import com.espertech.esper.client.soda.Expressions;
import com.espertech.esper.client.soda.FilterStream;
import com.espertech.esper.client.soda.FromClause;
import com.espertech.esper.client.soda.SelectClause;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.event.Event;
import de.ifgi.envision.eps.event.Participant;
import de.ifgi.envision.eps.event.Sensor;
import de.ifgi.envision.eps.event.WaterMLObservationEvent;
import de.ifgi.envision.eps.subscriber.NormalToActionThresholdExceededSubscriber;

public class TestWaterMLEventProducer {
	
	private final static String SAMPLE_ONE_OBS =  "data/waterML_sample_oneObservation.xml";
	private final static String SAMPLE_TWO_SITES = "data/waterML_sample_2sites-2variables-lastObs.xml";
	private final static String SAMPLE_TWO_OBS = "data/iv.xml";
	
	
	private WaterMLEventProducer eventProducer;
	private HashMap<String, Event> eventList;
	
	public TestWaterMLEventProducer() {
		// Get instance of EPServiceProvider
		EPServiceProvider epServiceProvider = EventProcessingServiceProvider.getEPServiceProvider();
		// Set configuration
		epServiceProvider.getEPAdministrator().getConfiguration().addEventType(WaterMLObservationEvent.class);
		// Create an statement: SELECT * FROM WaterMLObservationEvent
		EPStatementObjectModel model = new EPStatementObjectModel();
		model.setSelectClause(SelectClause.createWildcard());
		model.setFromClause(FromClause.create(FilterStream.create(WaterMLObservationEvent.class.getName())));
		model.setWhereClause(Expressions.ge("value", 5));
		//EPStatement stmt = manager.getEPAdministrator().create(model);
		//manager.registerStatement("select * from ".concat(WaterMLObservationEvent.class.getName()).concat(" where value > 5 output first every 4 events"));
		//stmt.setSubscriber(new WaterMLObservationEventSubscriber());
		
		// To sort events by timestamp we use Event.ext:time_order(arrival_time, seconds_interval_waiting)
		// Maybe we should insert ordered events into another stream and then do the pattern detection!
		// Create pattern: SELECT * FROM [every (a = WaterMLObservationEvent(value < 2) -> b = WaterMLObservationEvent(value > 2))]
		String pattern = "SELECT b.value, b.spatialLocation, b.temporalLocation, b.observer.name FROM pattern [every (a=".concat(WaterMLObservationEvent.class.getName())
			+ "(a.value > 2) -> b=".concat(WaterMLObservationEvent.class.getName()) +
					"(b.value < 2))]";
		epServiceProvider.getEPAdministrator().createEPL(pattern).setSubscriber(new NormalToActionThresholdExceededSubscriber());
		
		
		eventProducer = new WaterMLEventProducer();
		eventList = new HashMap<String, Event>();
		//populateEventListOneEvent();
//		insertEvent("2011-10-18T17:45:00.000-05:00", new Coordinate(38.94977778, -77.12763889), "01646500", 
//				"EPSG:4326", new Double(13200), "Streamflow, ft&#179;/s", "cfs", "USGS:01646500:00060:00011");
		insertEvent("2011-10-18T17:45:00.000-05:00", new Coordinate(38.94977778, -77.12763889), "01646500", 
				"EPSG:4326", new Double(13200), "Streamflow, ft&#179;/s", "cfs", "USGS:01646500:00060:00011", "POTOMAC RIVER NEAR WASH, DC LITTLE FALLS PUMP STA");
		insertEvent("2011-10-18T17:45:00.000-05:00", new Coordinate(38.94977778, -77.12763889), "01646500", 
				"EPSG:4326", new Double(4.28), "Gage height, ft", "ft", "USGS:01646500:00065:00011", "POTOMAC RIVER NEAR WASH, DC LITTLE FALLS PUMP STA");
		insertEvent("2011-10-18T18:45:00.000-04:00", new Coordinate(37.27150554, -81.3048227), "03177710", 
				"EPSG:4326", new Double(25), "Streamflow, ft&#179;/s", "cfs", "USGS:03177710:00060:00011", "POTOMAC RIVER NEAR WASH, DC LITTLE FALLS PUMP STA");
		insertEvent("2011-10-18T18:45:00.000-04:00", new Coordinate(37.27150554, -81.3048227), "03177710", 
				"EPSG:4326", new Double(1.21), "Gage height, ft", "ft", "USGS:03177710:00065:00011", "POTOMAC RIVER NEAR WASH, DC LITTLE FALLS PUMP STA");
	}
	
	private void insertEvent(String temporalLocation, Coordinate c, String siteCode, 
			String srs, Double value, String observedProperty, String uom, String timeSeriesId, String name) {
		// temporal location
		// Date sample -> 2011-10-14T10:00:00.000-05:00
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date timestamp = null;
		try {
			timestamp = formatter.parse(temporalLocation);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// spatial location
		GeometryFactory geoFactory = new GeometryFactory();
		Point point = geoFactory.createPoint(c);
		// observer (sensor or site)
		Sensor sensor = new Sensor(Integer.valueOf(siteCode).toString(), point, srs, name);
		// object participants
		List<Participant> participants = new ArrayList<Participant>();
		// create event
		WaterMLObservationEvent event = new WaterMLObservationEvent(timestamp, participants, point, sensor, value, observedProperty, uom);
		// add event to event list
		eventList.put(timeSeriesId.concat("-event").concat(String.valueOf(eventList.size()+1)), event);
	}

	private void populateEventListOneEvent() {
		// Event 1
		// temporal location
		// Date sample -> 2011-10-14T10:00:00.000-05:00
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date temporalLocation = null;
		try {
			temporalLocation = formatter.parse("2011-10-18T17:45:00.000-05:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// spatial location
		Coordinate c = new Coordinate(38.94977778, -77.12763889);
		GeometryFactory geoFactory = new GeometryFactory();
		Point point = geoFactory.createPoint(c);
		// observer (sensor or site)
		Sensor sensor = new Sensor(Integer.valueOf("01646500").toString(), point, "EPSG:4326", "POTOMAC RIVER NEAR WASH, DC LITTLE FALLS PUMP STA");
		// object participants
		List<Participant> participants = new ArrayList<Participant>();
		// observed value
		Double value = new Double(13200);
		// observed property
		String observedProperty = "Streamflow, ft&#179;/s";
		// Unit of measurement
		String uom = "cfs";
		// create event
		WaterMLObservationEvent event1 = new WaterMLObservationEvent(temporalLocation, participants, point, sensor, value, observedProperty, uom);
		// add event to event list
		eventList.put("USGS:01646500:00060:00011-event0", event1);
	}

	@Test
	public void testParseXmlURLFile() {
		File f = new File(this.SAMPLE_TWO_SITES);
		eventProducer.parseXml(null, f);
		// number of events parsed
		assertEquals(eventList.size(), eventProducer.getEventList().size());
		System.out.printf("Number of events: %d\n", eventList.size());
		// get key set
		Set<String> keySet = eventList.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			// get events
			WaterMLObservationEvent expected = (WaterMLObservationEvent) eventList.get(key);
			WaterMLObservationEvent actual = (WaterMLObservationEvent) eventProducer.getEventList().get(key);
			// event type
			assertEquals(expected.getEventType(), actual.getEventType());
			System.out.printf("Event type: %s\n", actual.getEventType());
			// observed property
			assertEquals(expected.getObservedProperty(), actual.getObservedProperty());
			System.out.printf("Observed property: %s\n", actual.getObservedProperty());
			// spatial location
			assertEquals(expected.getSpatialLocation().toString(), actual.getSpatialLocation().toString());
			System.out.printf("Spatial location: %s\n", actual.getSpatialLocation().toString());
			// temporal location
			assertEquals(expected.getTemporalLocation().toString(), actual.getTemporalLocation().toString());
			System.out.printf("Temporal location: %s\n", actual.getTemporalLocation().toString());
			// observer
			assertEquals(expected.getObserver().getId(), actual.getObserver().getId());
			System.out.printf("Observer: %s\n", actual.getObserver().getId());
			// participants
			assertEquals(expected.getParticipants().size(), actual.getParticipants().size());
			assertEquals(expected.getParticipants().get(0).getId(), actual.getParticipants().get(0).getId());
			assertEquals(expected.getParticipants().get(0).getSpatialLocation().toString(), actual.getParticipants().get(0).getSpatialLocation().toString());
			System.out.printf("Participants spatial location: %s\n", actual.getParticipants().get(0).getSpatialLocation().toString());
			// value
			assertEquals(expected.getValue(), actual.getValue());
			System.out.printf("Value: %s\n", actual.getValue());
			// unit of measurement
			assertEquals(expected.getUnitOfMeasure(), actual.getUnitOfMeasure());
			System.out.printf("Unit of measurement: %s\n", actual.getUnitOfMeasure());
		}
	}
	
	
	
//	@Test
//	public void testParseXmlURLString() {
//		fail("Not yet implemented");
//	}
//
//	
//	@Test
//	public void testParseFile() {
//		fail("Not yet implemented");
//	}
	
	
	
}
