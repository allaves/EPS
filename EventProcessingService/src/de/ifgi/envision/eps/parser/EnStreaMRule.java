package de.ifgi.envision.eps.parser;

import java.util.LinkedList;

public class EnStreaMRule {
	
	private String $from;
	private String Phenomenon;
	private String Sensor;
	private String Type;
	private String TimeSpan;
	private Value Value;
	private Value Value_1I_ago;
	private Value Value_2I_ago;
	private Value Value_3I_ago;
	private Value Value_4I_ago;
	private Value Value_5I_ago;
	private Value Value_6I_ago;
	private Value Value_7I_ago;
	private Value Value_8I_ago;
	private Value Value_9I_ago;
	private Value Value_10I_ago;
	private String date;	// ignore this field
	private String year;	// ignore this field
	private String month;	// ignore this field
	private String dayofmonth;	// ignore this field
	private String dayofweek;	// ignore this field
	private String timeofday;	// ignore this field
	private String hour;	// ignore this field
	
	private LinkedList<Value> intervals;
	
	public EnStreaMRule() {	
	}
	
	private void initializeIntervalsList() {
		intervals = new LinkedList<Value>();
		// Intervals FIFO list initialization - addFirst() + removeFirst()
		
		if (Value_1I_ago != null) {
			intervals.addFirst(Value_1I_ago);
		}
		if (Value_2I_ago != null) {
			intervals.addFirst(Value_2I_ago);
		}
		if (Value_3I_ago != null) {
			intervals.addFirst(Value_3I_ago);
		}
		if (Value_4I_ago != null) {
			intervals.addFirst(Value_4I_ago);
		}
		if (Value_5I_ago != null) {
			intervals.addFirst(Value_5I_ago);
		}
		if (Value_6I_ago != null) {
			intervals.addFirst(Value_6I_ago);
		}
		if (Value_7I_ago != null) {
			intervals.addFirst(Value_7I_ago);
		}
		if (Value_8I_ago != null) {
			intervals.addFirst(Value_8I_ago);
		}
		if (Value_9I_ago != null) {
			intervals.addFirst(Value_9I_ago);
		}
		if (Value_10I_ago != null) {
			intervals.addFirst(Value_10I_ago);
		}
	}
	
	public LinkedList<Value> getIntervals() {
		initializeIntervalsList();
		return intervals;
	}
	
	public String get$from() {
		return $from;
	}

	public void set$from(String $from) {
		this.$from = $from;
	}

	public String getPhenomenon() {
		return Phenomenon;
	}

	public void setPhenomenon(String phenomenon) {
		Phenomenon = phenomenon;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getTimeSpan() {
		return TimeSpan;
	}

	public void setTimeSpan(String timeSpan) {
		TimeSpan = timeSpan;
	}
	
	public Value getValue() {
		return Value;
	}

	public void setValue(Value value) {
		Value = value;
	}
	
	public String getSensor() {
		return Sensor;
	}
	
	public void setSensor(String sensor) {
		Sensor = sensor;
	}

	public Value getValue_1I_ago() {
		return Value_1I_ago;
	}

	public void setValue_1I_ago(Value value_1i_ago) {
		Value_1I_ago = value_1i_ago;
	}

	public Value getValue_2I_ago() {
		return Value_2I_ago;
	}

	public void setValue_2I_ago(Value value_2i_ago) {
		Value_2I_ago = value_2i_ago;
	}

	public Value getValue_3I_ago() {
		return Value_3I_ago;
	}

	public void setValue_3I_ago(Value value_3i_ago) {
		Value_3I_ago = value_3i_ago;
	}

	public Value getValue_4I_ago() {
		return Value_4I_ago;
	}

	public void setValue_4I_ago(Value value_4i_ago) {
		Value_4I_ago = value_4i_ago;
	}

	public Value getValue_5I_ago() {
		return Value_5I_ago;
	}

	public void setValue_5I_ago(Value value_5i_ago) {
		Value_5I_ago = value_5i_ago;
	}

	public Value getValue_6I_ago() {
		return Value_6I_ago;
	}

	public void setValue_6I_ago(Value value_6i_ago) {
		Value_6I_ago = value_6i_ago;
	}

	public Value getValue_7I_ago() {
		return Value_7I_ago;
	}

	public void setValue_7I_ago(Value value_7i_ago) {
		Value_7I_ago = value_7i_ago;
	}

	public Value getValue_8I_ago() {
		return Value_8I_ago;
	}

	public void setValue_8I_ago(Value value_8i_ago) {
		Value_8I_ago = value_8i_ago;
	}

	public Value getValue_9I_ago() {
		return Value_9I_ago;
	}

	public void setValue_9I_ago(Value value_9i_ago) {
		Value_9I_ago = value_9i_ago;
	}

	public Value getValue_10I_ago() {
		return Value_10I_ago;
	}

	public void setValue_10I_ago(Value value_10i_ago) {
		Value_10I_ago = value_10i_ago;
	}
	

	
}
