package de.ifgi.envision.eps.processingagent.schoharie;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;
import de.ifgi.envision.eps.event.WaterMLObservationEvent;
import de.ifgi.envision.eps.subscriber.ActionToMinorThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.ActionToNormalThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.MajorToModerateThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.MinorToActionThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.MinorToModerateThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.ModerateToMajorThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.ModerateToMinorThresholdExceededSubscriber;
import de.ifgi.envision.eps.subscriber.NormalToActionThresholdExceededSubscriber;

public final class PrattsvilleEPA {
	private static org.apache.log4j.Logger log = Logger.getLogger(PrattsvilleEPA.class);
	
	public static final void initiate() {
		// get current context classloader                                                                                                                                  
		ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
		// then alter the class-loader (but which one ? the one used to load this class itself) with:
		Thread.currentThread().setContextClassLoader(PrattsvilleEPA.class.getClassLoader());
		
		registerStatementUnderToActionStage();
		registerStatementActionToMinorFlood();
		registerStatementMinorToModerateFlood();
		registerStatementModerateToMajorFlood();
		registerStatementMajorToModerateFlood();
		registerStatementModerateToMinorFlood();
		registerStatementMinorToActionStage();
		registerStatementActionToUnderActionStage();
		log.info("PrattsvilleEPA initiated");
		
		// restore the class loader to its original value:
		Thread.currentThread().setContextClassLoader(contextClassloader);
	}
	
	private static void registerStatementUnderToActionStage() {
		String pattern = "SELECT a, b " +
				"FROM pattern [every (a=WaterMLObservationEvent" +
				"(a.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_ACTION_STAGE)) +
				") -> b=WaterMLObservationEvent" + 
				"(b.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_ACTION_STAGE)) +
				"))] " +
				"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
				"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new NormalToActionThresholdExceededSubscriber());
	}
	
	
	private static void registerStatementActionToMinorFlood() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MINOR_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MINOR_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new ActionToMinorThresholdExceededSubscriber());
	}
	
	private static void registerStatementMinorToModerateFlood() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MODERATE_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MODERATE_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new MinorToModerateThresholdExceededSubscriber());
	}
	
	private static void registerStatementModerateToMajorFlood() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MAJOR_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MAJOR_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new ModerateToMajorThresholdExceededSubscriber());
	}

	private static void registerStatementMajorToModerateFlood() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MAJOR_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MAJOR_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new MajorToModerateThresholdExceededSubscriber());
	}
	
	private static void registerStatementModerateToMinorFlood() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MODERATE_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MODERATE_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new ModerateToMinorThresholdExceededSubscriber());
	}
	
	private static void registerStatementMinorToActionStage() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MINOR_FLOOD_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_MINOR_FLOOD_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new MinorToActionThresholdExceededSubscriber());
	}
	
	private static void registerStatementActionToUnderActionStage() {
		String pattern = "SELECT a, b " +
		"FROM pattern [every (a=WaterMLObservationEvent" + 
		"(a.value > ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_ACTION_STAGE)) +
		") -> b=WaterMLObservationEvent" +
		"(b.value < ".concat(String.valueOf(FloodThresholds.PRATTSVILLE_ACTION_STAGE)) +
		"))] " +
		"WHERE (a.observer.id = '".concat(Integer.valueOf(FloodThresholds.PRATTSVILLE_SITE_CODE).toString()) + 
		"') and (a.observer.id = b.observer.id)";
		EventProcessingServiceProvider.getEPServiceProvider().getEPAdministrator().createEPL(pattern).setSubscriber(new ActionToNormalThresholdExceededSubscriber());
	}
}
