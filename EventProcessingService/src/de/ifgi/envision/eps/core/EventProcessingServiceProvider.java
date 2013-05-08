package de.ifgi.envision.eps.core;

import org.apache.log4j.Logger;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import de.ifgi.envision.eps.event.AggregationEvent;
import de.ifgi.envision.eps.event.EventAbstraction;
import de.ifgi.envision.eps.event.ObservationEvent;
import de.ifgi.envision.eps.exception.MalformedRuleException;
import de.ifgi.envision.eps.listener.AggregateStatementAwareListener;
import de.ifgi.envision.eps.listener.AggregationPatternStatementAwareListener;
import de.ifgi.envision.eps.listener.EventAbstractionPatternStatementAwareListener;
import de.ifgi.envision.eps.listener.PatternStatementAwareListener;
import de.ifgi.envision.eps.listener.WildcardStatementAwareListener;

public class EventProcessingServiceProvider {
	
	private static Logger log = Logger.getLogger(EventProcessingServiceProvider.class);
	
	// Singleton
	//private static final EPServiceProvider epServiceProvider = EPServiceProviderManager.getDefaultProvider();
	private static EPServiceProvider instance;
	
	private EventProcessingServiceProvider() {}
	
	public static EPServiceProvider getEPServiceProvider() {
		if (instance == null) {
			// get current context classloader                                                                                                                                  
			ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
			// then alter the class-loader (but which one ? the one used to load this class itself) with:
			Thread.currentThread().setContextClassLoader(EventProcessingServiceProvider.class.getClassLoader());
			
			instance = EPServiceProviderManager.getDefaultProvider(getConfiguration());		
			
			// restore the class loader to its original value:
			Thread.currentThread().setContextClassLoader(contextClassloader);
		}
		return instance;
	}
	
	
	public static Configuration getConfiguration() {
		Configuration conf = new Configuration();
		conf.addEventType("ObservationEvent", ObservationEvent.class.getName());
		conf.addEventType("AggregationEvent", AggregationEvent.class.getName());
		conf.addEventType("EventAbstraction", EventAbstraction.class.getName());
		return conf;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public static boolean registerStatement(String stm, String eventType) {
//		Class clazz = null;
//		Object subscriberConstructor = null;
//		Object listenerConstructor = null;
		
		ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
		// then alter the class-loader (but which one ? the one used to load this class itself) with:
		Thread.currentThread().setContextClassLoader(EPSManager.class.getClassLoader());
		
		// Create statement with name
		if (!stm.isEmpty()) {
			EPStatement epStm = EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(stm, eventType);

			// Select statement aware listener
			stm = stm.toLowerCase();
			if (!stm.startsWith("insert into")) {
				return addListener(epStm);
			}
			else {
				log.info("INSERT INTO statement registered.");
			}
			
			// restore the class loader to its original value:
			Thread.currentThread().setContextClassLoader(contextClassloader);
			log.info("Statement registered!");
			return true;
		}
		// restore the class loader to its original value:
		Thread.currentThread().setContextClassLoader(contextClassloader);
		return true;
	}
	
	public static boolean addListener(EPStatement epStm) {
		String stm = epStm.getText().toLowerCase();
		// Split by FROM
		String selectClause = stm.split("from")[0];
		// Split by SELECT
		String viewsString = selectClause.split("select")[1];
		// Split by comma
		String[] viewsArray = viewsString.split(",");
		
		if (viewsArray.length > 1) {
			if (selectClause.contains("select *,")) {
				// SELECT *, avg(value)...
				epStm.addListener(new AggregateStatementAwareListener());
				log.info("AggregateStatementAwareListener registered!");
				return true;
			}
			else if (stm.contains("aggregationevent")) {
				// SELECT obs1, obs2,... FROM pattern[every(obs1=AggregationEvent(...
				epStm.addListener(new AggregationPatternStatementAwareListener());
				log.info("AggregationPatternStatementAwareListener registered!");
				return true;
			}
			else if (stm.contains("eventabstraction")) {
				// SELECT obs1, obs2,... FROM pattern[every(obs1=EventAbstraction(...
				epStm.addListener(new EventAbstractionPatternStatementAwareListener());
				log.info("EventAbstractionPatternStatementAwareListener registered!");
				return true;
			}
			else {
				// SELECT obs1, obs2,... FROM pattern[every(obs1=ObservationEvent(...
				epStm.addListener(new PatternStatementAwareListener());
				log.info("PatternStatementAwareListener registered!");
				return true;
			}
		}
		else {
			if (stm.contains("aggregationevent")) {
				// SELECT * FROM AggregationEvent(...
				epStm.addListener(new AggregateStatementAwareListener());
				log.info("AggregateStatementAwareListener registered!");
				return true;
			}
			else if (stm.contains("select * from")) {
				// SELECT * FROM
				epStm.addListener(new WildcardStatementAwareListener());
				log.info("WildcardStatementAwareListener registered!");
				return true;
			}
		}
		return false;
	}
	

}
