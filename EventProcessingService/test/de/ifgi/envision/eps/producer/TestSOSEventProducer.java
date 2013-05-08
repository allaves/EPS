package de.ifgi.envision.eps.producer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.espertech.esper.client.EPServiceProvider;

import de.ifgi.envision.eps.core.EventProcessingServiceProvider;


public class TestSOSEventProducer {
	
	private final static String SAMPLE_OBS =  "data/...";
	
	private SOSEventProducer eventProducer;
	
//	public TestSOSEventProducer() {
//		// Get instance of EPServiceProvider
//		EPServiceProvider epServiceProvider = EventProcessingServiceProvider.getEPServiceProvider();
//		// Set configuration
//		epServiceProvider.getEPAdministrator().getConfiguration().addEventType(WaterMLObservationEvent.class);
//	}
	
	@Test
	public void testGetOldestDateOfLastObservations() {
		SOSEventProducer producer = new SOSEventProducer("http://194.102.135.23/EnvisionSOS/sos");
		HashMap<String, Date> map = new HashMap<String, Date>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		try {
			map.put("1", formatter.parse("2012-10-18T05:00:00.000+02:00"));
			map.put("2", formatter.parse("2012-10-22T05:00:00.000+02:00"));
			map.put("3", formatter.parse("2012-10-25T05:00:00.000+02:00"));
			
			Date oldestDate = new Date();
			for (Date d : map.values()) {
				if (d.before(oldestDate)) {
					oldestDate = d;
				}
			}
			Assert.assertEquals(formatter.parse("2012-10-18T05:00:00.000+02:00"), oldestDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
