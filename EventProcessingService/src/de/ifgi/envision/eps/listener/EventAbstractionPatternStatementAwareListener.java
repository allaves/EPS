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
import com.espertech.esper.client.UpdateListener;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;

import de.ifgi.envision.eps.event.EventAbstraction;
import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.push.EventPusher;
import de.ifgi.envision.eps.push.MessageBuilder;
import de.ifgi.envision.eps.push.ParliamentKB;

public class EventAbstractionPatternStatementAwareListener implements StatementAwareUpdateListener {

	private static Logger log = Logger.getLogger(EventAbstractionPatternStatementAwareListener.class);

	private MessageBuilder builder;
	
	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement statement, EPServiceProvider epServiceProvider) {

		log.info("\n\n\n\n\n\n\nEVENT ABSTRACTION PATTERN MATCHED!!\n\n\n\n\n\n");
		
		ArrayList<EventAbstraction> eventList = new ArrayList<EventAbstraction>();
		EventBean newEvent = newEvents[0];
		String stm = statement.getText().toLowerCase();
		
		// Split by FROM
		String selectClause = stm.split("from")[0];
		// Split by SELECT
		String viewsString = selectClause.split("select")[1];
		// Split by comma
		String[] viewsArray = viewsString.split(",");
		String obsAlias = null;
		String eventType = null;
		ArrayList<String> sourceList = new ArrayList<String>();
		Geometry[] geometries = new Geometry[viewsArray.length];
		ArrayList<String> observerList = new ArrayList<String>();
		String observer = null;
		boolean equalGeometry = true;
		
		for (int i = 0; i < viewsArray.length; i++) {
			obsAlias = viewsArray[i];
			obsAlias = obsAlias.replace(" ", "");
			EventAbstraction obs = (EventAbstraction) newEvent.get(obsAlias);
			eventList.add(obs);
			// obs-i event type
			eventType = obs.getEventType();
			// obs-i geometry
			Geometry geom = obs.getSpatialLocation();
			
			for (Geometry aux : geometries) {
				if (aux != null && !geom.equals(aux)) {
					equalGeometry = false;
				}
			}
			if (equalGeometry) {
				geometries[i] = geom;
			}
			else {
				break;
			}
			// obs-i source
			sourceList.addAll(obs.getSource());	
			// obs-i observer id
			observer = obs.getObserver();
			if (!observerList.contains(observer)) {
				observerList.add(observer);
			}
			
			System.out.printf("\n\n\nEVENT at %s!!\n", obs.getSpatialLocation());
			System.out.printf("Timestamp %s: %s\n", obsAlias, obs.getTemporalLocation().toString());
			System.out.printf("Observer: %s\n" ,obs.getObserver());
			System.out.printf("Event type: %s\n\n\n", obs.getEventType());
		}
		
		if (equalGeometry) {
			EventAbstraction lastEvent = eventList.get(eventList.size()-1);
			// Spatial location: the bounding box of the different geometries of each event (that will be, normally, points)
			Geometry spatialLocation = new GeometryFactory().createGeometryCollection(geometries).getEnvelope();
			spatialLocation.setSRID(geometries[0].getSRID());
			// Temporal location: begin of the last event's interval, end to the last event's interval
			Interval temporalLocation = new Interval(eventList.get(0).getTemporalLocation().getStartMillis(), lastEvent.getTemporalLocation().getEndMillis());
			
			
			try {
				builder = new MessageBuilder();
				String message = builder.createRDFMessage(spatialLocation, temporalLocation, null, statement.getName(), statement.getText(), sourceList, null, observerList, null);
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
		}
	}

}
