package de.ifgi.envision.eps.parser;

public class Value {
	
	private String $gt;
	private String $lt;
	private String $ne;
	private String $eq;
	
	public Value(String operator, String value) {
		if (operator.equalsIgnoreCase("$gt")) {
			$gt = value;
		}
		else if (operator.equalsIgnoreCase("$lt")) {
			$lt = value;
		}
		else if (operator.equalsIgnoreCase("$ne")) {
			$ne = value;
		}
		else {
			$eq = value;
		}
	}
	
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

	public String get$eq() {
		return $eq;
	}

	public void set$eq(String $eq) {
		this.$eq = $eq;
	}
	
}