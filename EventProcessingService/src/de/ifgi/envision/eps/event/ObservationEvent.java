package de.ifgi.envision.eps.event;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;

import com.vividsolutions.jts.geom.Geometry;

public class ObservationEvent extends Event {
	
	private Sensor observer;
	
	private Double value;
	
	private String observedProperty;
	
	private Geometry spatialLocation;
	
	private String source;
	
	private String unitOfMeasure;
	
	private String url;
	
	// Constructors
	
	public ObservationEvent() {
		super();
	}

	public ObservationEvent(Interval temporalLocation,
			List<Participant> participants, Geometry spatialLocation, Sensor observer, Double value, String observedProperty, String unitOfMeasure, String source, String url) {
		super(temporalLocation, participants);
		this.observer = observer;
		participants.add(observer);
		this.value = value;
		this.observedProperty = observedProperty;
		this.spatialLocation = spatialLocation;
		this.source = source;
		this.unitOfMeasure = unitOfMeasure;
		this.url = url;
	}
	
	// Methods

	public Sensor getObserver() {
		return observer;
	}

	public Double getValue() {
		return value;
	}
	
	public String getObservedProperty() {
		return observedProperty;
	}
	
	public Geometry getSpatialLocation() {
		return spatialLocation;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	
	public String getUrl() {
		return url;
	}

	public void setObserver(Sensor observer) {
		this.observer = observer;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setObservedProperty(String observedProperty) {
		this.observedProperty = observedProperty;
	}

	public void setSpatialLocation(Geometry spatialLocation) {
		this.spatialLocation = spatialLocation;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
