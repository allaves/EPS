package de.ifgi.envision.eps.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.n52.oxf.OXFException;
import org.n52.oxf.owsCommon.ExceptionReport;
import org.n52.oxf.owsCommon.capabilities.Operation;
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.serviceAdapters.ParameterContainer;
import org.n52.oxf.serviceAdapters.sos.SOSAdapter;
import org.n52.oxf.util.IOHelper;

import de.ifgi.envision.eps.producer.SOSEventProducer;

public class SOS {

	private static org.apache.log4j.Logger log = Logger.getLogger(SOS.class);
	
	//private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private String serviceId;
	private SOSEventProducer eventProducer;
	private String serviceURL;
	private String offering;
	private String observedProperty;
	private String request;
	private Date beginPosition;
	private Date endPosition;
	private Date dateCursor;
	private Calendar calendar;
	
	private SimpleDateFormat formatter;
	
	private TimeUnit timeUnit;
	private int numberOfTimeUnits;
	
	private ScheduledFuture<?> futureTask;
	
	
	
	public SOS(String serviceURL, String offering, String observedProperty) {
		this.serviceId = serviceURL.concat("/").concat(offering);
		this.eventProducer = new SOSEventProducer(serviceURL);
		this.serviceURL = serviceURL;
		this.offering = offering;
		this.observedProperty = observedProperty;
		// Date format like 2008-03-01T17:44:15+00:00
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		calendar = Calendar.getInstance();
	}
	
	public SOS(String serviceURL, String offering, String observedProperty, String begin, String end) {
		this.serviceId = serviceURL.concat("/").concat(offering);
		this.eventProducer = new SOSEventProducer(serviceURL);
		this.serviceURL = serviceURL;
		this.offering = offering;
		this.observedProperty = observedProperty;
		// Date format like 2008-03-01T17:44:15+00:00
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			beginPosition = formatter.parse(begin);
			endPosition = formatter.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar = Calendar.getInstance();
	}
	
	
	public Date getEndPosition() {
		return endPosition;
	}
	
	public void setEndPosition(Date endPosition) {
		this.endPosition = endPosition;
	}
	
	/*
	 * Requests the latest observations from the SOS
	 */
	public OperationResult getLatestObservations() {
		InputStream is = null;
		OperationResult or = null;
		try {
			// Set request using a time period with oldest timestamp as beginPosition and endPosition
			setGetObservationsRequest(timeUnit, numberOfTimeUnits);
			is = IOHelper.sendPostMessage(serviceURL, request);
			log.info("Request sent: ".concat(request));
			// Get response
			or = new OperationResult(is, null, request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return or;
	}
	
	
	public OperationResult getObservations(String serviceURL, String request) {
		InputStream is = null;
		OperationResult or = null;
		try {
			is = IOHelper.sendPostMessage(serviceURL, request);
			log.info("Request sent: ".concat(request));
			// Get response
			or = new OperationResult(is, null, request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return or;
	}

	
	public SOSEventProducer getEventProducer() {
		return eventProducer;
	}
	
	private int setTimeUnitAddition(TimeUnit timeUnit) {
		int calendarUnit = -1;
		switch (timeUnit) {
		case SECONDS:	
			calendarUnit = Calendar.SECOND;			
			break;
		case MINUTES:
			calendarUnit = Calendar.MINUTE;
			break;
		case HOURS:
			calendarUnit = Calendar.HOUR_OF_DAY;
			break;
		case DAYS:
			calendarUnit = Calendar.DAY_OF_YEAR;
			break;
		default:
			log.error("The time unit specified is not allowed!");
			break;
		}
		return calendarUnit;
	}
	
	
	private void setGetObservationsRequest(TimeUnit timeUnit, Integer numberOfTimeUnits) {
		int calendarUnit = setTimeUnitAddition(timeUnit);
		if (endPosition == null) {
			// service schedule for near real-time data
			// dateCursor initialized at current system time
			// TODO dateCursor initialized at sensor locale
			dateCursor = new Date();
			calendar.setTime(dateCursor);
			calendar.add(calendarUnit, -numberOfTimeUnits);
			beginPosition = calendar.getTime();
		}
		else if (beginPosition.before(endPosition)) {
			// service schedule for historical data
			calendar.setTime(beginPosition);
			calendar.add(calendarUnit, numberOfTimeUnits);
			dateCursor = calendar.getTime();
		}
		else if (futureTask != null) {
			// If beginPosition is "after" endPosition, the scheduling is cancelled and the service deregistered
			futureTask.cancel(true);
			ServiceManager.getServiceManager().deregisterService(serviceId);
		}
		
		request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<GetObservation xmlns=\"http://www.opengis.net/sos/1.0\" " +
				"xmlns:ows=\"http://www.opengis.net/ows/1.1\" " +
				"xmlns:gml=\"http://www.opengis.net/gml\" " +
				"xmlns:ogc=\"http://www.opengis.net/ogc\" " +
				"xmlns:om=\"http://www.opengis.net/om/1.0\" " +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
				"xsi:schemaLocation=\"http://www.opengis.net/sos/1.0 http://schemas.opengis.net/sos/1.0.0/sosGetObservation.xsd\" " +
				"service=\"SOS\" version=\"1.0.0\" srsName=\"urn:ogc:def:crs:EPSG::4326\">" +
				"<offering>" + offering + "</offering>" +
				"<eventTime>" +
					"<ogc:TM_During>" +
						"<ogc:PropertyName>om:samplingTime</ogc:PropertyName>" +
						"<gml:TimePeriod>" +
							"<gml:beginPosition>" + formatter.format(beginPosition) + "</gml:beginPosition>" +
							"<gml:endPosition>" + formatter.format(dateCursor) + "</gml:endPosition>" +
						"</gml:TimePeriod>" +
					"</ogc:TM_During>" +
				"</eventTime>" +
				 "<observedProperty>" + observedProperty + "</observedProperty>" +
				 "<responseFormat>text/xml;subtype=&quot;om/1.0.0&quot;</responseFormat>" +
				 "</GetObservation>";
		
		beginPosition = dateCursor;
	}
	
	
	/*
	 * Schedules a task periodically every [numberOfTimeUnits] [timeUnit]
	 */
	public ScheduledFuture<?> scheduleGetResponseBody(TimeUnit timeUnit, Integer numberOfTimeUnits) {
		this.timeUnit = timeUnit;
		this.numberOfTimeUnits = numberOfTimeUnits;
		final Callable<OperationResult> callableGetObservations = new Callable<OperationResult>() {
			public OperationResult call() {
				OperationResult op = getLatestObservations();
				return op;
			}
		};
		final Runnable getObservationsTask = new Runnable() {
			public void run() {
				OperationResult res = null;
				try {
					res = callableGetObservations.call();
					eventProducer.parseXml(request, res);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OutOfMemoryError e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		if (beginPosition == null) {
			// near real-time schedule
			futureTask = ServiceManager.getServiceManager().getScheduler().scheduleAtFixedRate(getObservationsTask, 0, numberOfTimeUnits, timeUnit);
		}
		else {
			// if the schedule is for historical data, each call is executed every 3 seconds
			futureTask = ServiceManager.getServiceManager().getScheduler().scheduleAtFixedRate(getObservationsTask, 0, 3, TimeUnit.SECONDS);
		}
		
		return futureTask;
	}
	
	public ScheduledFuture<?> getFutureTask() {
		return futureTask;
	}
}
