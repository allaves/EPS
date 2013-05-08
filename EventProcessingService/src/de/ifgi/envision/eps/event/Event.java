package de.ifgi.envision.eps.event;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;

public abstract class Event {
	/**
	 * The temporal location will be considered as a timestamp for the prototype
	 */
	//protected Date temporalLocation;
	protected Interval temporalLocation;
	
	protected long time;
	
	protected String eventType;
	
	protected List<Participant> participants;
	
	public Event() {}
	
	public Event(Interval temporalLocation, List<Participant> participants) {
		this.temporalLocation = temporalLocation;
		this.participants = participants;
		//this.time = temporalLocation.getTime();
		this.time = temporalLocation.getEndMillis();
	}
	
	public Interval getTemporalLocation() {
		return temporalLocation;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public List<Participant> getParticipants() {
		return participants;
	}
	
	public long getTime() {
		return time;
	}

	public void setTemporalLocation(Interval temporalLocation) {
		this.temporalLocation = temporalLocation;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

}
