package de.ifgi.envision.eps.listener;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.openrdf.repository.RepositoryException;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.SafeIterator;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.vividsolutions.jts.geom.Geometry;

import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.push.EventPusher;
import de.ifgi.envision.eps.push.MessageBuilder;

public class EventObservationStatementAwareListener implements StatementAwareUpdateListener {
	
	private static Logger log = Logger.getLogger(EventObservationStatementAwareListener.class);
	
	private MessageBuilder builder;
	
	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents,
			EPStatement statement, EPServiceProvider epServiceProvider) {
		
		EventBean newEvent = newEvents[0];
		
		ObservationEvent obs1 = (ObservationEvent) newEvent.get("obs1");
		ObservationEvent obs2 = (ObservationEvent) newEvent.get("obs2");
			
		System.out.printf("EVENT at %s!!\n", obs2.getSpatialLocation());
		System.out.printf("Sensor obs 1: %s\n", obs1.getObserver().getId());
		System.out.printf("Timestamp obs 1: %s\n", obs1.getTemporalLocation().toString());
		System.out.printf("Value obs 1: %s %s\n", obs1.getValue(), obs1.getUnitOfMeasure());
		
		System.out.printf("Sensor obs 2: %s\n", obs2.getObserver().getId());
		System.out.printf("Timestamp observation 2: %s\n", obs2.getTemporalLocation().toString());
		System.out.printf("Value obs 2: %s %s\n\n", obs2.getValue(), obs2.getUnitOfMeasure());
		System.out.printf("Source: %s\n\n", obs2.getSource());
		System.out.printf("EPL statement: %s\n\n", statement.getText());
		
		String[] observedPropertyArray = new String[]{obs1.getObservedProperty(), obs2.getObservedProperty()};
		
		try {
			builder = new MessageBuilder();
			String message = builder.createRDFMessage(obs2.getSpatialLocation(), obs2.getTemporalLocation(), observedPropertyArray, statement.getName(), statement.getText(), obs2.getSource(), obs2.getUrl());
			EventPusher.getEventPusher().push(message);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
