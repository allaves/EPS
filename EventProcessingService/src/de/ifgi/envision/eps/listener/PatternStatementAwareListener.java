package de.ifgi.envision.eps.listener;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.openrdf.repository.RepositoryException;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.event.EventAbstraction;
import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.push.EventPusher;
import de.ifgi.envision.eps.push.MessageBuilder;
import de.ifgi.envision.eps.push.ParliamentKB;

public class PatternStatementAwareListener implements StatementAwareUpdateListener {
	
	private static Logger log = Logger.getLogger(PatternStatementAwareListener.class);
	
	private MessageBuilder builder;

	/*
	 * Simple pattern template: defines a set of observations ordered in time or arrival to the EPSIt is assumed that events are sent as they are sensed and that there are no relevant delays, so we expect that the order corresponds more or less to the observation timestamps., i.e. obs3 follows obs2, which follows obs1.
	 * @see com.espertech.esper.client.StatementAwareUpdateListener#update(com.espertech.esper.client.EventBean[], com.espertech.esper.client.EventBean[], com.espertech.esper.client.EPStatement, com.espertech.esper.client.EPServiceProvider)
	 */
	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents,
			EPStatement statement, EPServiceProvider epServiceProvider) {
		
		ArrayList<ObservationEvent> eventList = new ArrayList<ObservationEvent>();
		EventBean newEvent = newEvents[0];
		String stm = statement.getText().toLowerCase();
		ArrayList<String> observedPropertyList = new ArrayList<String>();
		ArrayList<String> sourceUrlList = new ArrayList<String>();
		ArrayList<String> sourceRequestList = new ArrayList<String>();
		
		// Split by FROM
		String selectClause = stm.split("from")[0];
		// Split by SELECT
		String viewsString = selectClause.split("select")[1];
		// Split by comma
		String[] viewsArray = viewsString.split(",");
		String obsAlias = null;
		String observedProperty = null;
		String source = null;
		String url = null;
		Geometry[] geometries = new Geometry[viewsArray.length];
		ArrayList<String> sensorIdList = new ArrayList<String>();
		
		for (int i = 0; i < viewsArray.length; i++) {
			obsAlias = viewsArray[i];
			obsAlias = obsAlias.replace(" ", "");
			ObservationEvent obs = (ObservationEvent) newEvent.get(obsAlias);
			eventList.add(obs);
			// obs-i geometry
			geometries[i] = obs.getSpatialLocation(); 
			// obs-i observedProperty
			observedProperty = obs.getObservedProperty();
			if (!observedPropertyList.contains(observedProperty)) {
				observedPropertyList.add(observedProperty);
			}
			// obs-i source
			sourceRequestList.add(obs.getSource());
			// obs-i url
			sourceUrlList.add(obs.getUrl());
			// obs-i sensor id
			sensorIdList.add(obs.getObserver().getId());
			
			System.out.printf("\n\n\nEVENT at %s!!\n", obs.getSpatialLocation());
			System.out.printf("Sensor %s: %s\n", obsAlias, obs.getObserver().getId());
			System.out.printf("Timestamp %s: %s\n", obsAlias, obs.getTemporalLocation().toString());
			System.out.printf("Value %s: %s %s\n", obsAlias, obs.getValue(), obs.getUnitOfMeasure());
			System.out.printf("Observed property %s: %s\n\n\n\n", obsAlias, observedProperty);
		}
		
		ObservationEvent lastEvent = eventList.get(eventList.size()-1);
		
		// Spatial location: the envelope of a collection of the different geometries of each event (that will be, normally, points)
		Geometry spatialLocation = new GeometryFactory().createGeometryCollection(geometries).getEnvelope();
		spatialLocation.setSRID(geometries[0].getSRID());
		// Temporal location: begin of the last event's interval, end to the last event's interval
		Interval temporalLocation = new Interval(lastEvent.getTemporalLocation().getStartMillis(), lastEvent.getTemporalLocation().getEndMillis());
		
		// Creating the event instance that will be sent to the Event Bus
		try {
			builder = new MessageBuilder();
			String message = builder.createRDFMessage(spatialLocation, temporalLocation, observedPropertyList, statement.getName(), statement.getText(), sourceUrlList, sourceRequestList, sensorIdList, null);
			// Sending triples to the Event Bus
			EventPusher.getEventPusher().push(message);
			// Inserting triples into Parliament
			if (ParliamentKB.getInstance().insertTriples(message, "N3")) {
				log.info("Triples inserted into Parliament!");
			}
			else {
				log.error("ERROR: Triples not inserted into Parliament.");
			}
			//log.info(message);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		// The source of an Event Abstraction points to the collection of sources that were used to derive it
		ArrayList<String> eventAbstractionSource = new ArrayList<String>();
		eventAbstractionSource.add(builder.getSourceCollection().toString());
		
		// Creating the EventAbstraction that will be sent to the CEP engine
		EventAbstraction eventAbstraction = new EventAbstraction(statement.getName(), spatialLocation, temporalLocation, eventAbstractionSource, builder.getEventProcessingAgent().toString());
		EventProcessingServiceProvider.getEPServiceProvider().getEPRuntime().sendEvent(eventAbstraction);
	}

}
