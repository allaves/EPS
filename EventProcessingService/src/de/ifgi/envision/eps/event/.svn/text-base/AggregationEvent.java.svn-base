package de.ifgi.envision.eps.event;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;

import com.vividsolutions.jts.geom.Geometry;

public class AggregationEvent extends Event {
	
	private Sensor observer;
	
	//private Double value;
	
	private Double aggregateValue;
	
	private String observedProperty;
	
	/*
	 * We assume that the spatialLocation is a com.vividsolutions.jts.geom.MultiPoint
	 */
	private Geometry spatialLocation;
	
	private String source;
	
	private String unitOfMeasure;
	
	private String url;
	
	// New attributes
	private String aggregationType;
	
	private String aggregationTimeSpan;
	
	// Constructors
	
	public AggregationEvent() {
		super();
	}

	public AggregationEvent(Interval temporalLocation,
			List<Participant> participants, Geometry spatialLocation, Sensor observer, Double aggregateValue, String observedProperty, String unitOfMeasure, String source, String url) {
		super(temporalLocation, participants);
		this.observer = observer;
		participants.add(observer);
		this.aggregateValue = aggregateValue;
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

	public Double getAggregateValue() {
		return aggregateValue;
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
	
	
	public void setAggregateValue(Double aggregateValue) {
		this.aggregateValue = aggregateValue;
	}

	public void setObserver(Sensor observer) {
		this.observer = observer;
	}
//
//	public void setValue(Double value) {
//		this.value = value;
//	}

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

	public String getAggregationType() {
		return aggregationType;
	}

	public void setAggregationType(String aggregationType) {
		this.aggregationType = aggregationType;
	}

	public String getAggregationTimeSpan() {
		return aggregationTimeSpan;
	}

	public void setAggregationTimeSpan(String aggregationTimeSpan) {
		this.aggregationTimeSpan = aggregationTimeSpan;
	}
	

}
