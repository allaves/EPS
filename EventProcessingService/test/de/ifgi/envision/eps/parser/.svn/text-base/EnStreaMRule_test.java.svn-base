package de.ifgi.envision.eps.parser;

import java.util.LinkedList;

public class EnStreaMRule_test {
	
	private String $from;
	private String Phenomenon;
	private String Type;
	private String TimeSpan;
	private Value Value;
	private String Value_1I_ago;
	private String Value_2I_ago;
	private String Value_3I_ago;
	private String Value_4I_ago;
	private String Value_5I_ago;
	private String Value_6I_ago;
	private String Value_7I_ago;
	private String Value_8I_ago;
	private String Value_9I_ago;
	private String Value_10I_ago;
	private String date;	// ignore this field
	private String year;	// ignore this field
	private String month;	// ignore this field
	private String dayofmonth;	// ignore this field
	private String dayofweek;	// ignore this field
	private String timeofday;	// ignore this field
	private String hour;	// ignore this field
	
	private LinkedList<String> intervals;
	
	public EnStreaMRule_test() {
		initializeIntervalsList();
	}
	
	private void initializeIntervalsList() {
		// Intervals FIFO list initialization - addFirst() + removeFirst()
		intervals = new LinkedList<String>();
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
	
	public LinkedList<String> getIntervals() {
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
	

	public class Value {
		
		private String $gt;
		private String $lt;
		private String $ne;
		
		public String get$gt() {
			return $gt;
		}

		public void set$gt(String $gt) {
			this.$gt = $gt;
		}
		
		public String get$lt() {
			return $lt;
		}

		public void set$lt(String $lt) {
			this.$lt = $lt;
		}
		
		public String get$ne() {
			return $ne;
		}

		public void set$ne(String $ne) {
			this.$ne = $ne;
		}
		
	}
}
