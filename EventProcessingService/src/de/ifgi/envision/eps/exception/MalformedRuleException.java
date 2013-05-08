package de.ifgi.envision.eps.exception;

import org.apache.log4j.Logger;

public class MalformedRuleException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MalformedRuleException.class);

	public MalformedRuleException(String msg) {
		log.error(msg);
	}

}
