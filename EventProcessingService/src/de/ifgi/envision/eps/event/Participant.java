package de.ifgi.envision.eps.event;

import com.vividsolutions.jts.geom.Geometry;

public interface Participant {

	public String getId();
	
	public Geometry getSpatialLocation();
	
	public String getName();
}
