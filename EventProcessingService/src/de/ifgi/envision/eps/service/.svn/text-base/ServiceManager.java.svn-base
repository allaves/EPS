package de.ifgi.envision.eps.service;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.n52.oxf.serviceAdapters.OperationResult;

import com.espertech.esper.client.EPServiceProviderManager;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;

/**
 * Singleton used to schedule getObservation requests
 * @author a_llav02
 *
 */
public class ServiceManager {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ServiceManager.class);
	
	private static ServiceManager instance;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);
	
	private HashMap<String, Object> registry;
	
	private ServiceManager() {
		registry = new HashMap<String, Object>();
	}
	
	public static ServiceManager getServiceManager() {
		if (instance == null) {
			// get current context classloader                                                                                                                                  
			ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
			// then alter the class-loader (but which one ? the one used to load this class itself) with:
			Thread.currentThread().setContextClassLoader(EventProcessingServiceProvider.class.getClassLoader());
			
			instance = new ServiceManager();	
			
			// restore the class loader to its original value:
			Thread.currentThread().setContextClassLoader(contextClassloader);
		}
		return instance;
	}
	
	public ScheduledExecutorService getScheduler() {
		return scheduler;
	}
	
	public boolean registerService(String key, Object service) {
		registry.put(key, service);
		log.info("Service registered! Key: ".concat(key));
		return true;
	}
	
	public void deregisterService(String key) {
		if (registry.containsKey(key)) {
			registry.remove(key);
			log.info("Service removed! Key: ".concat(key));
		}
		else {
			log.warn("The service registry does not contain a service with key ".concat(key));
		}
	}
	
	public Set<String> getServiceIds() {
		return registry.keySet();
	}
	
	public Object getService(String serviceId) {
		if (registry.containsKey(serviceId)) {
			return registry.get(serviceId);
		}
		log.warn("The registry does not contain any service with key ".concat(serviceId));
		return null;
	}
	
	public void clearRegistry() {
		log.info("***Clearing service registry***");
		registry.clear();
		log.info("Service registry is cleared.");
	}
	
	

}
