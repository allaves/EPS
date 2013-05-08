package de.ifgi.envision.eps.service;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.jfree.util.Log;
import org.junit.Test;
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.util.IOHelper;

import de.ifgi.envision.eps.producer.SOSEventProducer;

public class TestSOS {
	
	private SOS service;
	private SOSEventProducer eventProducer;
	
//	private static String SERVICE_URL = "http://194.102.135.23/EnvisionSOS/sos"; 
	private static String SERVICE_URL = "http://www.pegelonline.wsv.de/webservices/gis/sos";
	public TestSOS() {
		eventProducer = new SOSEventProducer(SERVICE_URL);
		String serviceVersion = "1.0.0";
//		String offering = "GAUGE_HEIGHT";
		String offering = "urn:ogc:offering:offering0";
//		String observedProperty = "urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel";
		String observedProperty = "Wasserstand";
		Date begin = null;
		Date end = null;
//		String[] procedure = new String[]{"urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002"};
		String[] procedure = new String[]{"Wasserstand - Kelheimwinzer (10054500)"};
		String foi = null;
		//service = new SOS(serviceURL, serviceVersion, offering, observedProperty, begin, end, procedure, foi);
		service = new SOS(SERVICE_URL, offering, observedProperty);
	}
	
//	@Test
//	public void testScheduleGetResponseBody() {
//		String timeUnit = "MINUTES";
//		Integer numberOfTimeUnits = 5;
//		ScheduledFuture<?> serviceHandler = service.scheduleGetResponseBody(timeUnit, numberOfTimeUnits);
//		assertNotNull(serviceHandler);
//	}
	
//	@Test
//	public void testGetObservations() {
//		OperationResult res= service.getObservations();
//		SOSEventProducer eventProducer = new SOSEventProducer();
//		eventProducer.parseXml(null, res);
//	}
	
//	@Test
//	public void testScheduleGetResponseBody() {
//		service.scheduleGetResponseBody(TimeUnit.MINUTES, 2);
//	}
	
	@Test
	public void testGetLatestGetObservations() {
		String url = null;
		service.scheduleGetResponseBody(TimeUnit.HOURS, 24);
		
		OperationResult or = service.getLatestObservations();
		
		eventProducer.parseXml(SERVICE_URL, or);
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//service.scheduleGetResponseBody(TimeUnit.MINUTES, 2);
			
	}

}
