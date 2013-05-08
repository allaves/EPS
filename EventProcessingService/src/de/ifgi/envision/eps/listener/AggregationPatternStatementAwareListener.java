package de.ifgi.envision.eps.listener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.openrdf.repository.RepositoryException;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import de.ifgi.envision.eps.event.AggregationEvent;
import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.push.EventPusher;
import de.ifgi.envision.eps.push.MessageBuilder;

public class AggregationPatternStatementAwareListener implements
		StatementAwareUpdateListener {
	
	private static Logger log = Logger.getLogger(AggregationPatternStatementAwareListener.class);
	
	private MessageBuilder builder;

	/*
	 * Aggregate patterns template: this pattern is used to detect a set of observations ordered in time, as the ones described in AggregateStatementAwareListener, with the difference that each observation is the result of an aggregation process.
	 * Template:
	 * 
	 * INSERT INTO AggregationEvent 
	 * SELECT temporalLocation, observedProperty, spatialLocation, observer, source, unitOfMeasure, url, [min, max, avg, sum, count, stddev](value) as aggregateValue, '[min, max, avg, sum, count, stddev]' as aggregationType, '[TIME_VALUE]' as aggregationTimeSpan
	 * FROM ObservationEvent(observedProperty = '…', observer.id = '…').win:time_batch([TIME_VALUE])
	 * 
	 * SELECT obs1, obs2
	 * FROM pattern[ every(obs1=AggregationEvent(observedProperty = '…', observer.id = '…', aggregateValue [<, >, =, !=] [FLOAT_VALUE])
	 * 	-> obs2=AggregationEvent(observedProperty = '…', observer.id = '…', aggregateValue [<, >, =, !=] [FLOAT_VALUE]))]
	 *  
	 * @see com.espertech.esper.client.StatementAwareUpdateListener#update(com.espertech.esper.client.EventBean[], com.espertech.esper.client.EventBean[], com.espertech.esper.client.EPStatement, com.espertech.esper.client.EPServiceProvider)
	 */
	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents,
			EPStatement statement, EPServiceProvider epServiceProvider) {
		
		ArrayList<AggregationEvent> eventList = new ArrayList<AggregationEvent>();
		EventBean newEvent = newEvents[0];
		String stm = statement.getText().toLowerCase();
		ArrayList<String> observedPropertyList = new ArrayList<String>();
		LinkedHashMap<String, String> observationCollectionMap = new LinkedHashMap<String, String>();
		
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
		ArrayList<String> sourceUrlList = new ArrayList<String>();
		ArrayList<String> sourceRequestList = new ArrayList<String>();
		
		for (int i = 0; i < viewsArray.length; i++) {
			obsAlias = viewsArray[i];
			obsAlias = obsAlias.replace(" ", "");
			AggregationEvent obs = (AggregationEvent) newEvent.get(obsAlias);
			eventList.add(obs);
			// obs-i geometry
			geometries[i] = obs.getSpatialLocation();
			// obs-i observedProperty
			observedProperty = obs.getObservedProperty();
			// obs-i source
			source = obs.getSource();
			// obs-i url
			url = obs.getUrl();
			if (!observedPropertyList.contains(observedProperty)) {
				observedPropertyList.add(observedProperty);
			}
			// obs-i source
			sourceRequestList.add(obs.getSource());
			// obs-i url
			sourceUrlList.add(obs.getUrl());
			// obs-i sensor id
			sensorIdList.add(obs.getObserver().getId());
			
			System.out.printf("EVENT at %s!!\n", obs.getSpatialLocation());
			//System.out.printf("Sensor %s: %s\n", obsAlias, obs.getObserver().getId());
			System.out.printf("Timestamp %s: %s\n", obsAlias, obs.getTemporalLocation().toString());
			System.out.printf("Aggregate value %s: %.2f %s\n", obsAlias, obs.getAggregateValue(), obs.getUnitOfMeasure());
			System.out.printf("Observed property %s: %s\n", obsAlias, obs.getObservedProperty());
		}
		
		AggregationEvent lastEvent = eventList.get(eventList.size()-1);
		// Spatial location: the envelope of a collection of the different geometries of each event (that will be, normally, points)
		Geometry spatialLocation = new GeometryFactory().createGeometryCollection(geometries).getEnvelope();
		// Temporal location: begin of the last event's interval, end to the last event's interval
		Interval temporalLocation = new Interval(lastEvent.getTemporalLocation().getStartMillis(), lastEvent.getTemporalLocation().getEndMillis());
		
		try {
			builder = new MessageBuilder();
			// Values would be also be interesting to add to the EventObservation instance
			// Pattern listeners can also contain aggregate values
			// The message should allow adding multiple observed properties, e.g. water level, water flow
			//String message = builder.createRDFMessage(spatialLocation, temporalLocation, observedPropertyList, statement.getName(), statement.getText(), observationCollectionMap, sensorIdList, null);
			String message = builder.createRDFMessage(spatialLocation, temporalLocation, observedPropertyList, statement.getName(), statement.getText(), sourceUrlList, sourceRequestList, sensorIdList, null);
			EventPusher.getEventPusher().push(message);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
