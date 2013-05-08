package de.ifgi.envision.eps.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import de.ifgi.envision.eps.event.SOSObservationEvent;
import de.ifgi.envision.eps.event.WaterMLObservationEvent;

public class WaterMLObservationListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean newEvent = newEvents[0];
		System.out.println("Event received!");
		if (newEvent.getEventType().getUnderlyingType().equals(WaterMLObservationEvent.class)) {
			WaterMLObservationEvent wmlObservation = (WaterMLObservationEvent) newEvent.getUnderlying();
			System.out.printf("Observed Property: %s\n", wmlObservation.getObservedProperty());
			System.out.printf("Value: %s %s\n", wmlObservation.getValue(), wmlObservation.getUnitOfMeasure());
		}
	}

}
