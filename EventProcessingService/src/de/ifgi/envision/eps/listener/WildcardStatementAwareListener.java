package de.ifgi.envision.eps.listener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.openrdf.repository.RepositoryException;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.push.EventPusher;
import de.ifgi.envision.eps.push.MessageBuilder;

/*
 * Wildcard template: addressed to simple observation events with the attribute filters for observed property, sensor identifier, and value or the measurement.
 * Template:
 * 
 * SELECT *
 * FROM ObservationEvent(observedProperty = ‘…’, observer.id = ‘…’, value [<, >, =, !=] [FLOAT_VALUE])
 * 
 */
public class WildcardStatementAwareListener implements StatementAwareUpdateListener {
	
	private static Logger log = Logger.getLogger(WildcardStatementAwareListener.class);
	
	private MessageBuilder builder;

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents,
			EPStatement statement, EPServiceProvider epServiceProvider) {
		
		ObservationEvent event = (ObservationEvent) newEvents[0].getUnderlying();
		
		System.out.printf("EVENT at %s!!\n", event.getSpatialLocation());
		System.out.printf("Sensor: %s\n", event.getObserver().getId());
		System.out.printf("Timestamp: %s\n", event.getTemporalLocation().toString());
		System.out.printf("Observed property: %s\n", event.getObservedProperty());
		System.out.printf("Value: %.2f %s\n", event.getValue(), event.getUnitOfMeasure());
		
		ArrayList<String> observedPropertyList = new ArrayList<String>();
		observedPropertyList.add(event.getObservedProperty());
		ArrayList<String> sourceUrlList = new ArrayList<String>();
		sourceUrlList.add(event.getUrl());
		ArrayList<String> sourceRequestList = new ArrayList<String>();
		sourceRequestList.add(event.getSource());
		ArrayList<String> sensorIdList = new ArrayList<String>();
		sensorIdList.add(event.getObserver().getId());
		
		
		try {
			builder = new MessageBuilder();
			// Value would be also be interesting to add to the EventObservation instance
			String message = builder.createRDFMessage(event.getSpatialLocation(), event.getTemporalLocation(), observedPropertyList, statement.getName(), statement.getText(), sourceUrlList, sourceRequestList, sensorIdList, null);
			EventPusher.getEventPusher().push(message);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
