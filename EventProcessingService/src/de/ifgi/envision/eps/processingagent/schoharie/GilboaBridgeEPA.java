package de.ifgi.envision.eps.processingagent.schoharie;

import org.apache.log4j.Logger;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.event.WaterMLObservationEvent;
import de.ifgi.envision.eps.subscriber.ActionToMinorThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.ActionToNormalThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.MinorToActionThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.NormalToActionThresholdExceededSubscriber;

/**
 * Revise this EPA!
 * It doesn't detect all the events it should detect.
 * @author a_llav02
 *
 */
public final class GilboaBridgeEPA {
	private static org.apache.log4j.Logger log = Logger.getLogger(GilboaBridgeEPA.class);

	public static final void initiate() {
		// get current context classloader                                                                                                                                  
		ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
		// then alter the class-loader (but which one ? the one used to load this class itself) with:
		Thread.currentThread().setContextClassLoader(PrattsvilleEPA.class.getClassLoader());
		
		registerStatementUnderToActionStage();
		registerStatementActionToMinorFlood();
		registerStatementMinorToActionStage();
		registerStatementActionToUnderActionStage();
		log.info("GilboaBridgeEPA initiated");
		
		// restore the class loader to its original value:
		Thread.currentThread().setContextClassLoader(contextClassloader);
	}
	
	private static void registerStatementUnderToActionStage() {
		String pattern = "SELECT a, b " +
				"FROM pattern [every (a=WaterMLObservationEvent" + 
				"(a.value < ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_ACTION_STAGE)) +
				") -> b=WaterMLObservationEvent" +
				"(b.value > ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_ACTION_STAGE)) +
				"))] " +
				"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.GILBOA_BRIDGE_SITE_CODE).toString()) + 
				"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new NormalToActionThresholdExceededSubscriber());
	}
	
	private static void registerStatementActionToMinorFlood() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value < ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_MINOR_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value > ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_MINOR_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.GILBOA_BRIDGE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new ActionToMinorThresholdExceededSubscriber());
	}
	
	
	private static void registerStatementMinorToActionStage() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value > ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_MINOR_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value < ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_MINOR_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.GILBOA_BRIDGE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new MinorToActionThresholdExceededSubscriber());
	}
	
	private static void registerStatementActionToUnderActionStage() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value > ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_ACTION_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value < ".concat(String.valueOf(FloodThresholds.GILBOA_BRIDGE_ACTION_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.GILBOA_BRIDGE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new ActionToNormalThresholdExceededSubscriber());
	}
}
