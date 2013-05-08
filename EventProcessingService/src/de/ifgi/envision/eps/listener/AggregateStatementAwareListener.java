package de.ifgi.envision.eps.listener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.openrdf.repository.RepositoryException;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.vividsolutions.jts.geom.Geometry;

import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.exception.MalformedRuleException;
import de.ifgi.envision.eps.push.EventPusher;
import de.ifgi.envision.eps.push.MessageBuilder;

/*
 * Simple aggregate template: aggregates the values of simple observation events with the indicated observed property and sensor identifier.
 * Template:
 * 
 * SELECT *, [min, max, avg, sum, count, stddev](value) as aggregateValue, [TIME_VALUE] as aggregationTimeSpan
 * FROM ObservationEvent(observedProperty = '…', observer.id = '…').win:time_batch([TIME_VALUE])
 * HAVING [min, max, avg, sum, count, stddev](value) [<, >, =, !=] [FLOAT_VALUE]
 * 
 */
public class AggregateStatementAwareListener implements	StatementAwareUpdateListener {

	private static Logger log = Logger.getLogger(AggregateStatementAwareListener.class);
	
	private MessageBuilder builder;
	
	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents,
			EPStatement statement, EPServiceProvider epServiceProvider) {

		//ObservationEvent event = (ObservationEvent) newEvents[0].getUnderlying();
		EventBean event = newEvents[0];
		// Spatial location
		Geometry spatialLocation = (Geometry) event.get("spatialLocation");
		// Temporal location		
		String timeWindow = (String) event.get("aggregationTimeSpan");
		Interval eventInterval = (Interval) event.get("temporalLocation");
		Interval temporalLocation = null;
		try {
			temporalLocation = setIntervalBeginningFromTimeWindow(timeWindow, eventInterval.getEnd().toDate());
		} catch (MalformedRuleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Observed property
		ArrayList<String> observedPropertyList = new ArrayList<String>();
		observedPropertyList.add((String) event.get("observedProperty"));
		// Observation collection
		String source = (String) event.get("source");
		String url = (String) event.get("url");
		ArrayList<String> sourceUrlList = new ArrayList<String>();
		sourceUrlList.add(url);
		ArrayList<String> sourceRequestList = new ArrayList<String>();
		sourceRequestList.add(source);
		// Sensor ids
		String uom = (String) event.get("unitOfMeasure");
		String sensorId = (String) event.get("observer.id");
		ArrayList<String> sensorIdList = new ArrayList<String>();
		sensorIdList.add(sensorId);
		// Aggregate value
		Double aggregateValue = (Double) event.get("aggregateValue");
		
		System.out.printf("EVENT at %s!!\n", spatialLocation.toText());
		System.out.printf("Timestamp: %s\n", temporalLocation.getEnd().toString());
		System.out.printf("AggregateValue: %.2f %s\n", aggregateValue, uom);
		
		try {
			builder = new MessageBuilder();
			String message = builder.createRDFMessage(spatialLocation, temporalLocation, observedPropertyList, statement.getName(), statement.getText(), sourceUrlList, sourceRequestList, sensorIdList, aggregateValue);
			EventPusher.getEventPusher().push(message);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns an interval with (beginning date = endDate - timeWindow, endDate)
	 */
	public Interval setIntervalBeginningFromTimeWindow(String timeWindow, Date endDate) throws MalformedRuleException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		String[] timeWindowArray = timeWindow.split(" ");
		if (timeWindowArray.length != 2) {
			throw new MalformedRuleException("The value for the time window is malformed. Check the documentation for more information: http://esper.codehaus.org/esper-4.4.0/doc/reference/en/html_single/index.html");
		}
		else {
			int numberOfUnits = Integer.parseInt(timeWindowArray[0]);
			int timeUnit = getTimeUnit(timeWindowArray[1]);
			calendar.add(timeUnit, -numberOfUnits);
			Interval temporalLocation = new Interval(calendar.getTimeInMillis(), endDate.getTime());
			return temporalLocation;
		}
	}
	
	private int getTimeUnit(String timeUnit) throws MalformedRuleException {
		int calendarUnit = -1;
		if ((timeUnit.compareToIgnoreCase("seconds") == 0) || (timeUnit.compareToIgnoreCase("second") == 0)) {
			calendarUnit = Calendar.SECOND;			
		}
		else if ((timeUnit.compareToIgnoreCase("minutes") == 0) || (timeUnit.compareToIgnoreCase("second") == 0)) {
			calendarUnit = Calendar.MINUTE;
		}
		else if ((timeUnit.compareToIgnoreCase("hours") == 0) || (timeUnit.compareToIgnoreCase("hour") == 0)) {
			calendarUnit = Calendar.HOUR_OF_DAY;
		}
		else if ((timeUnit.compareToIgnoreCase("days") == 0) || (timeUnit.compareToIgnoreCase("day") == 0)) {
			calendarUnit = Calendar.DAY_OF_YEAR;
		}
		else {
			throw new MalformedRuleException("The value for the time window is malformed. Check the documentation for more information: http://esper.codehaus.org/esper-4.4.0/doc/reference/en/html_single/index.html");
		}
		return calendarUnit;
	}

}
