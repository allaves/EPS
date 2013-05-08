package de.ifgi.envision.eps.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.n52.oxf.serviceAdapters.OperationResult;
import org.n52.oxf.util.IOHelper;
import org.n52.oxf.xmlbeans.parser.XMLHandlingException;
import org.openrdf.model.BNode;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.RDFWriterFactory;
import org.openrdf.rio.Rio;
import org.openrdf.rio.n3.N3Writer;
import org.openrdf.sail.memory.MemoryStore;

import com.vividsolutions.jts.geom.Geometry;

import de.ifgi.envision.eps.parser.OMParser;


public class MessageBuilder {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(MessageBuilder.class);
	
	// Event Processing Agent - Service URL
	private static String EPS_URL = "http://giv-wfs.uni-muenster.de/axis2/services/EPSManager";
	
	// namespaces
	private static String NS_BASE = "http://purl.org/ifgi/eabs-instance#";
	private static String NS_DC = "http://purl.org/dc/elements/1.1/#";
	private static String NS_EABS = "http://wsmls.googlecode.com/svn/trunk/global/Event-abstraction/0.2/EventAbstraction.rdf#";
	private static String NS_SSN = "http://purl.oclc.org/NET/ssnx/ssn#"; 
	private static String NS_DUL = "http://www.loa.istc.cnr.it/ontologies/DUL.owl#";
	private static String NS_GEO = "http://www.opengis.net/ont/geosparql#";
	private static String NS_RDFS = "http://www.w3.org/2000/01/rdf-schema#";
	private static String NS_SF = "http://www.opengis.net/ont/sf#";
	private static String NS_XMLSchema = "http://www.w3.org/TR/xmlschema-2/#";
	private static String NS_RDF = "http://www.w3.org/TR/rdf-concepts/#";
	
	
	private Repository repo;
	private ValueFactory vf;
	
	private List<Statement> stack = new ArrayList<Statement>(); 
	private URI sourceCollection;
	private URI eventProcessingAgent;
	
	
	
	public MessageBuilder() throws RepositoryException {
		repo = new SailRepository(new MemoryStore());
		repo.initialize();
		vf = repo.getValueFactory();
	}
	
	

	/*
	 * This version of the method includes the event pattern that triggered the detection. 
	 * Therefore, it will be only callable from listeners that implement the interface StatementAwareUpdateListener.
	 */
	public String createRDFMessage(Geometry spatialLocation, Interval temporalLocation, ArrayList<String> observedPropertyList, 
			String eventType, String eventPattern, ArrayList<String> sourceUrlList, ArrayList<String> sourceRequestList, ArrayList<String> sensorIdList,
			Double aggregateValue) {
		/*
		 *  Importing ontologies
		 */
		BNode owlOntology = vf.createBNode();
		stackStatement(owlOntology, RDF.TYPE, OWL.ONTOLOGY);
		stackStatement(owlOntology, OWL.IMPORTS, vf.createURI(NS_DUL));
		stackStatement(owlOntology, OWL.IMPORTS, vf.createURI(NS_EABS));
		stackStatement(owlOntology, OWL.IMPORTS, vf.createURI(NS_SSN));
		stackStatement(owlOntology, OWL.IMPORTS, vf.createURI(NS_GEO));
		stackStatement(owlOntology, OWL.IMPORTS, vf.createURI(NS_SF));
		
		/*
		 * Instances
		 */
		// eabs:EventAbstraction instance
		URI eventAbstraction = vf.createURI(NS_BASE, "eventAbstraction_" + RandomStringUtils.random(6, true, false));
		stackStatement(eventAbstraction, RDF.TYPE, vf.createURI(NS_EABS, "EventAbstraction")); 
			
		// dul:EventType instance
		URI eventAbstractionType = vf.createURI(NS_BASE, "eventType_" + RandomStringUtils.random(6, true, false));
		String urlEventType = eventType.split("--")[0];
		stackStatement(eventAbstractionType, RDF.TYPE, vf.createURI(urlEventType));
		
		// eabs:EventAbstractionRule instance + literal
		URI eventAbstractionRule = vf.createURI(NS_BASE, "eventAbstractionRule_"+RandomStringUtils.random(6, true, false));
		stackStatement(eventAbstractionRule, RDF.TYPE, vf.createURI(NS_EABS, "EventAbstractionRule"));
		stackStatement(eventAbstractionRule, vf.createURI(NS_DUL, "hasDataValue"), vf.createLiteral(eventPattern));
		
		// eabs:EventDetectionProcedure instance + literal
		URI eventDetectionProcedure = vf.createURI(NS_BASE, "eventDetectionProcedure_"+RandomStringUtils.random(6, true, false));
		stackStatement(eventDetectionProcedure, RDF.TYPE, vf.createURI(NS_EABS, "EventDetectionProcedure"));
		stackStatement(eventDetectionProcedure, vf.createURI(NS_RDFS, "label"), vf.createLiteral("http://dbpedia.org/page/Complex_event_processing"));
		
		// eabs:EventProcessingAgent instance + literal
		// TODO the URL should be assigned on runtime
		eventProcessingAgent = vf.createURI(NS_BASE, "eventProcessingAgent_"+RandomStringUtils.random(6, true, false));
		stackStatement(eventProcessingAgent, RDF.TYPE, vf.createURI(NS_EABS, "EventProcessingAgent"));
		stackStatement(eventProcessingAgent, vf.createURI(NS_DC, "identifier"), vf.createLiteral(EPS_URL));
		
		// eabs:EventProcessingAgentOutput instance
		URI eventProcessingAgentOutput = vf.createURI(NS_BASE, "eventProcessingAgentOutput_"+RandomStringUtils.random(6, true, false));
		stackStatement(eventProcessingAgentOutput, RDF.TYPE, vf.createURI(NS_EABS, "EventProcessingAgentOutput"));
		
		// eabs:EventAbstractionValue instance
		URI eventAbstractionValue = vf.createURI(NS_BASE, "eventAbstractionValue_"+RandomStringUtils.random(6, true, false));
		stackStatement(eventAbstractionValue, RDF.TYPE, vf.createURI(NS_EABS, "EventAbstractionValue"));
		
		// geo:Geometry instance + literal
		/*
		 * For geo:wktLiterals, the beginning URI identifies the spatial reference system for the geometry. The OGC maintains a set of CRS URIs under the
		 * http://www.opengis.net/def/crs/ namespace. This leading spatial reference system URI is optional. In the absence of a leading spatial reference 
		 * system URI, the following spatial reference system URI will be assumed: <http://www.opengis.net/def/crs/OGC/1.3/CRS84>. 
		 * This URI denotes WGS 84 longitude-latitude.
		 */
		URI geometryInstance = vf.createURI(NS_BASE, "geometryInstance_"+RandomStringUtils.random(6, true, false));
		stackStatement(geometryInstance, RDF.TYPE, vf.createURI(NS_GEO, "Geometry"));
		//String location = "SRID=" + spatialLocation.getSRID() + ";" + spatialLocation.toString();
		stackStatement(geometryInstance, vf.createURI(NS_GEO, "asWKT"), vf.createLiteral(spatialLocation.toString(), new URIImpl(NS_SF + "wktLiteral")));

		// dul:SpaceRegion instance + literal
		URI spaceRegion = vf.createURI(NS_BASE, "spaceRegion_" + RandomStringUtils.random(6, true, false));
		stackStatement(spaceRegion, RDF.TYPE, vf.createURI(NS_DUL, "SpaceRegion"));
		stackStatement(spaceRegion, vf.createURI(NS_DUL, "hasRegionDataValue"), vf.createLiteral(spatialLocation.toString(), new URIImpl(NS_SF + "wktLiteral")));
		
		// dul:TimeInterval instance + literals {hasBeginning, hasEnd}
		URI timeInterval = vf.createURI(NS_BASE, "timeInterval_" + RandomStringUtils.random(6, true, false));
		stackStatement(timeInterval, RDF.TYPE, vf.createURI(NS_DUL, "TimeInterval"));
		stackStatement(timeInterval, vf.createURI(NS_DUL, "hasBeginning"), vf.createLiteral(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(temporalLocation.getStart().toDate()), new URIImpl(NS_XMLSchema + "dateTime")));
		stackStatement(timeInterval, vf.createURI(NS_DUL, "hasEnd"), vf.createLiteral(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(temporalLocation.getEnd().toDate()), new URIImpl(NS_XMLSchema + "dateTime")));
		
		// dul:SpatioTemporalRegion instance
		URI spatioTemporalRegion = vf.createURI(NS_BASE, "spatioTemporalRegion_" + RandomStringUtils.random(6, true, false));
		stackStatement(spatioTemporalRegion, RDF.TYPE, vf.createURI(NS_DUL, "SpatioTemporalRegion"));
		
		
		/**
		 *  If we assume that the Sensor Data Services are semantically annotated, we could directly use the observed property of the
		 *  observations analyzed to create the triple, but this is not often the case.
		 *  
		 */
		URI observedPropertyInstance = null;
		HashSet<String> observedPropertySet = new HashSet<String>();
		if (observedPropertyList != null) {
			for (String observedProperty : observedPropertyList) {
				if (!observedPropertySet.contains(observedProperty)) {
					observedPropertySet.add(observedProperty);
					observedPropertyInstance = vf.createURI(NS_BASE, "property_" + RandomStringUtils.random(6, true, false));
					stackStatement(observedPropertyInstance, RDF.TYPE, vf.createURI(NS_SSN, "Property"));
					stackStatement(observedPropertyInstance, vf.createURI(NS_DC, "identifier"), vf.createLiteral(observedProperty));
					stackStatement(eventAbstraction, vf.createURI(NS_SSN, "observedProperty"), observedPropertyInstance);
				}
			}
		}
		
		/*
		 *  dul:Collection
		 *  A collection of dul:InformationObjects (observationCollections)
		 */
		// If observedPropertyList and sourceRequestList are null, we have an EventAbstraction -> see EventAbstractionPatternStatementAwareListener.java
		if (observedPropertyList == null && sourceRequestList == null) {
			for (String collection : sourceUrlList) {
				stackStatement(eventAbstraction, vf.createURI(NS_EABS, "isDerivedFrom"), vf.createURI(collection));
			}
		}
		else {
			sourceCollection = vf.createURI(NS_BASE, "collection_" + RandomStringUtils.random(6, true, false));
			stackStatement(sourceCollection, RDF.TYPE, vf.createURI(NS_DUL, "Collection"));
			String url = null;
			URI informationObject = null;
			String request = null;
			HashSet<String> sourceSet = new HashSet<String>();			
			
			for (int i = 0; i < sourceUrlList.size(); i++) {
				url = sourceUrlList.get(i);
				if (sourceRequestList != null) {
					request = sourceRequestList.get(i);
					if (!sourceSet.contains(url + request)) {
						sourceSet.add(url + request);
						informationObject = vf.createURI(NS_BASE, "informationObject_" + RandomStringUtils.random(6, true, false));
						stackStatement(informationObject, RDF.TYPE, vf.createURI(NS_DUL, "InformationObject"));
						stackStatement(informationObject, vf.createURI(NS_DC, "source"), vf.createLiteral(url));
						// Populate the information object with the content of an ObservationCollection
						populateInformationObject(informationObject, url, request, temporalLocation, sensorIdList.get(i));
						stackStatement(sourceCollection, vf.createURI(NS_DUL, "hasMember"), informationObject);
					}
				}
				
			}
			stackStatement(eventAbstraction, vf.createURI(NS_EABS, "isDerivedFrom"), sourceCollection);
		}
		
		
		/*
		 * Properties
		 */
		// dul:SpatioTemporalRegion dul:hasConstituent dul:TimeInterval
		stackStatement(spatioTemporalRegion, vf.createURI(NS_DUL, "hasConstituent"), timeInterval);
		
		// dul:SpatioTemporalRegion dul:hasConstituent dul:SpaceRegion
		stackStatement(spatioTemporalRegion, vf.createURI(NS_DUL, "hasConstituent"), spaceRegion);
		
		// eabs:EventProcessingAgent ssn:implements eabs:EventDetectionProcedure
		stackStatement(eventProcessingAgent, vf.createURI(NS_SSN, "implements"), eventDetectionProcedure);
		
		// eabs:EventProcessingAgentOutput ssn:isProducedBy eabs:EventProcessingAgent;
		//		ssn:hasValue eabs:EventAbstractionValue
		stackStatement(eventProcessingAgentOutput, vf.createURI(NS_SSN, "isProducedBy"), eventProcessingAgent);
		stackStatement(eventProcessingAgentOutput, vf.createURI(NS_SSN, "hasValue"), eventAbstractionValue);
		
		// eabs:EventAbstractionValue eabs:hasEventType dul:EventType
		stackStatement(eventAbstractionValue, vf.createURI(NS_EABS, "hasEventType"), eventAbstractionType);
		
		// dul:eventType dul:isDefinedIn eabs:EventAbstractionRule
		stackStatement(eventAbstractionType, vf.createURI(NS_DUL, "isDefinedIn"), eventAbstractionRule);
		
		// eabs:EventAbstraction geo:hasGeometry geo:Geometry;
		//		dul:isObservableAt dul:TimeInterval;
		//		eabs:processingMethodUsed eabs:EventDetectionProcedure;
		//		eabs:processedBy eabs:EventProcessingAgent;
		//		eabs:hasAbstractionResult eabs:EventProcessingAgentOutput;
		//		eabs:isClassifiedBy dul:eventType;
		//		dul:satisfies eabs:EventAbstractionRule;
		//		eabs:inferredSpatioTemporalRegion dul:SpatioTemporalRegion;
		//		eabs:isDerivedFrom dul:Collection .
		stackStatement(eventAbstraction, vf.createURI(NS_GEO, "hasGeometry"), geometryInstance);
		stackStatement(eventAbstraction, vf.createURI(NS_DUL, "isObservableAt"), timeInterval);
		stackStatement(eventAbstraction, vf.createURI(NS_EABS, "processingMethodUsed"), eventDetectionProcedure);
		stackStatement(eventAbstraction, vf.createURI(NS_EABS, "processedBy"), eventProcessingAgent);
		stackStatement(eventAbstraction, vf.createURI(NS_EABS, "hasAbstractionResult"), eventProcessingAgentOutput);
		stackStatement(eventAbstraction, vf.createURI(NS_EABS, "isClassifiedBy"), eventAbstractionType);
		stackStatement(eventAbstraction, vf.createURI(NS_DUL, "satisfies"), eventAbstractionRule);
		stackStatement(eventAbstraction, vf.createURI(NS_EABS, "inferredSpatioTemporalRegion"), spatioTemporalRegion);
		
		
		
		RepositoryConnection connection = null;
		StringWriter sw = null;
		N3Writer n3Writer = null;
		//RDFWriter rdfWriter = null;
		String str = null;
		try {
			connection = repo.getConnection();
			connection.add(stack);
			sw = new StringWriter();
			n3Writer = new N3Writer(sw); 
			handleNamespaces(n3Writer);
			//rdfWriter = Rio.createWriter(RDFFormat.RDFXML, sw);
			connection.export(n3Writer);
			str = sw.toString();
			sw.close();
			connection.close();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RDFHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	public URI getSourceCollection() {
		return sourceCollection;
	}
	
	public URI getEventProcessingAgent() {
		return eventProcessingAgent;
	}
	
	
	private void populateInformationObject(URI informationObject, String url, String request, Interval temporalLocation, String sensorId) {
		String str = null;
		String eventTime = null;
		if (request.contains("<GetObservation")) {
			// POST request for an SOS service
			String begin = null;
			String end = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			if (temporalLocation.getStart().equals(temporalLocation.getEnd())) {
				// Punctual event
				Calendar calendar = Calendar.getInstance();
				Date endDate = temporalLocation.getEnd().toDate();	
				calendar.setTime(endDate);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				endDate = calendar.getTime();
				end = formatter.format(endDate);
				calendar.add(Calendar.DAY_OF_MONTH, -2);
				Date beginDate = calendar.getTime();
				begin = formatter.format(beginDate);
			}
			else {
				// Durative event
				begin = formatter.format(temporalLocation.getStart().toDate());
				end = formatter.format(temporalLocation.getEnd().toDate());
			}
			begin = begin.substring(0, begin.length()-2);
			begin = begin.concat(":00");
			end = end.substring(0, end.length()-2);
			end = end.concat(":00");
			eventTime = "<eventTime>" +
					"<ogc:TM_During>" +
						"<ogc:PropertyName>om:samplingTime</ogc:PropertyName>" +
						"<gml:TimePeriod>" +
							"<gml:beginPosition>" + begin + "</gml:beginPosition>" +
							"<gml:endPosition>" + end + "</gml:endPosition>" +
						"</gml:TimePeriod>" +
					 "</ogc:TM_During>" +
					 "</eventTime>" +
					 "<procedure>" + sensorId + "</procedure>";
			
			String newRequest = request.replaceAll("<eventTime>.+</eventTime>", eventTime);
			OperationResult opRes = getObservations(url, newRequest);
			try {
				str = new String(opRes.getIncomingResult(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			// WaterServiceUSGS requests consists in GET request embedded in the URL
			// TODO
		}
		stackStatement(informationObject, vf.createURI(NS_DUL, "hasDataValue"), vf.createLiteral(str, new URIImpl(NS_RDF + "XMLLiteral")));
	}


	private OperationResult getObservations(String url, String request) {
		InputStream is = null;
		OperationResult or = null;
		try {
			is = IOHelper.sendPostMessage(url, request);
			log.info("Request sent: ".concat(request));
			// Get response
			or = new OperationResult(is, null, request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return or;
	}

	private void handleNamespaces(N3Writer n3Writer) {
		try {
			n3Writer.handleNamespace("dul", NS_DUL);
			n3Writer.handleNamespace("eabs", NS_EABS);
			n3Writer.handleNamespace("ssn", NS_SSN);
			n3Writer.handleNamespace("geo", NS_GEO);
			n3Writer.handleNamespace("rdfs", NS_RDFS);
			n3Writer.handleNamespace("sf", NS_SF);
			n3Writer.handleNamespace("xmlSchema", NS_XMLSchema);
			n3Writer.handleNamespace("dc", NS_DC);
			n3Writer.handleNamespace("rdf", NS_RDF);
		} catch (RDFHandlerException e) {
			e.printStackTrace();
		}
	}

	
	private void stackStatement(Resource subject, URI predicate, Value object) {
		stack.add(vf.createStatement(subject, predicate, object));  
	}

}
