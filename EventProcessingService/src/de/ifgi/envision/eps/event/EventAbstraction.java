package de.ifgi.envision.eps.event;

import java.net.URI;
import java.util.ArrayList;

import org.joda.time.Interval;

import com.vividsolutions.jts.geom.Geometry;

public class EventAbstraction extends Event {
	
	private Geometry spatialLocation;
	private ArrayList<String> source;
	private String observer;
	
	
	public EventAbstraction(String eventType, Geometry spatialLocation, Interval temporalLocation, ArrayList<String> source, String observer) {
		super();
		this.eventType = eventType;
		this.temporalLocation = temporalLocation;
		this.spatialLocation = spatialLocation;
		this.source = source;
		this.observer = observer;
	}

	public Geometry getSpatialLocation() {
		return spatialLocation;
	}

	public void setSpatialLocation(Geometry spatialLocation) {
		this.spatialLocation = spatialLocation;
	}

	public ArrayList<String> getSource() {
		return source;
	}

	public void setSource(ArrayList<String> source) {
		this.source = source;
	}

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}
	
	
	
	

}
