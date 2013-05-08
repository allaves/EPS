package de.ifgi.envision.eps.event;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import de.ifgi.envision.eps.event.Participant;

public class Sensor implements Participant {
	private String id;
	private Point spatialLocation;
	private String srs;
	private String name;


	public Sensor(String id, Point spatialLocation, String srs, String name) {
		this.id = id;
		this.spatialLocation = spatialLocation;
		this.srs = srs;
		this.name = name;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Geometry getSpatialLocation() {
		return spatialLocation;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSrs() {
		return srs;
	}

	public void setSrs(String srs) {
		this.srs = srs;
	}

	public void setSpatialLocation(Point spatialLocation) {
		this.spatialLocation = spatialLocation;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
