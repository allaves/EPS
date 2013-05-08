package de.ifgi.envision.eps.event;

import com.espertech.esper.client.EventType;

public class EventPattern {
	private String eventPatternId;
	private String statement;
	private EventType eventType;
	private String dataCollectionId; // Will be replaced by some kind of data collection
	
	public EventPattern(String statement, EventType eventType) {
		super();
		this.statement = statement;
		this.eventType = eventType;
	}

	public String getEventPatternId() {
		return eventPatternId;
	}

	public void setEventPatternId(String eventPatternId) {
		this.eventPatternId = eventPatternId;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getDataCollectionId() {
		return dataCollectionId;
	}

	public void setDataCollectionId(String dataCollectionId) {
		this.dataCollectionId = dataCollectionId;
	}
	
	
}
