package de.ifgi.envision.eps.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import de.ifgi.envision.eps.producer.WaterMLEventProducer;

public class WaterServiceUSGS {
	private static org.apache.log4j.Logger log = Logger.getLogger(WaterServiceUSGS.class);
	
//	private final static String URL = "http://waterservices.usgs.gov/nwis/iv?";
//	private final static String CHARSET = "utf-8";
	
	//private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	private WaterMLEventProducer eventProducer;
	private HttpClient httpClient;
	private HttpGet httpGet;
	private ResponseHandler<String> responseHandler;
	private String responseBody;
	private String serviceUrl;
	
	//private final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
	
	public WaterServiceUSGS(String completeUrl) {
		eventProducer = new WaterMLEventProducer(completeUrl);
		httpClient = new DefaultHttpClient();
		httpGet = new HttpGet(completeUrl);
		responseHandler = new BasicResponseHandler();
		//responseBody = executeRequest();
		responseBody = getResponseBody();
		serviceUrl = completeUrl;
	}
	
	
	private String executeRequest() {
		String str = null;
		try {
			str = httpClient.execute(httpGet, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public String getResponseBody() {
		java.net.URL url = null;
		StringWriter writer = null;
		String response = null;
		try {
			//url = new java.net.URL("http://irtg.ifgi.de/wp-content/uploads/2012/02/streamHeightSchoharie_sept5to9th.txt");
			url = new java.net.URL("http://irtg.ifgi.de/wp-content/uploads/2012/03/breakabeenDataSet_sept5to9th.txt");
			//url = new URL("http://irtg.ifgi.de/wp-content/uploads/2012/03/breakabeenDataSet_sept5to9th_oneFloodEvent.txt");
			url.openConnection();
			InputStream is = url.openStream();	
			writer = new StringWriter();
			IOUtils.copy(is, writer, "UTF-8");
			response = writer.toString();
			is.close();
			writer.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return response;
	}
	
	/**
	 * Currently, we fake the sensor data request because the data available for this service is up to 120 days
	 * and we are interested on the water levels after the Tropical Storm Irene, which passed by NY state at the beginning of September 2011.
	 * @return
	 */
//	public File getResponse() {
//		File response = null;
//		try {
//			response = File.createTempFile("temp", "file");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		BufferedWriter writer;
//		try {
//			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(response), CHARSET));
//			writer.write(getResponseBody());
//			writer.flush();
//			writer.close();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return response;
//	}
	
	public ScheduledFuture<?> scheduleGetResponseBody() {
		final Callable<String> getObservations = new Callable<String>() {
			public String call() {
				//log.info("getObservations() callable execution!");
				//return getResponseBody();
				return responseBody;
			}
		};
		final Runnable getObservationsTask = new Runnable() {
			public void run() {
				String str = null;
				try {
					str = getObservations.call();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				eventProducer.parseXml(null, str);
			}
		};
		final ScheduledFuture<?> serviceHandler = scheduler.scheduleAtFixedRate(getObservationsTask, 1, 2, TimeUnit.MINUTES);
		//final ScheduledFuture<?> serviceHandler = scheduler.schedule(getObservationsTask, 2, TimeUnit.MINUTES);
		return serviceHandler;
	}

}
