package de.ifgi.envision.eps.push;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.ifgi.envision.eps.listener.PatternStatementAwareListener;

public class ParliamentKBTest {

	private static Logger log = Logger.getLogger(ParliamentKBTest.class);
	
	@Test
	public void testInsertTriples() {
		String triples = "@prefix dul: <http://www.loa.istc.cnr.it/ontologies/DUL.owl#> ." +
				"@prefix eabs: <http://purl.org/ifgi/event-abstraction#> ." +
				"@prefix ssn: <http://purl.oclc.org/NET/ssnx/ssn#> ." +
				"@prefix geo: <http://www.opengis.net/ont/geosparql#> ." +
				"@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ." +
				"@prefix sf: <http://www.opengis.net/ont/sf#> ." +
				"@prefix xmlSchema: <http://www.w3.org/TR/xmlschema-2/#> ." +
				"@prefix dc: <http://purl.org/dc/elements/1.1/#> ." +
				"@prefix rdf: <http://www.w3.org/TR/rdf-concepts/#> ." +
				"_:node17hrv2jpex1 a <http://www.w3.org/2002/07/owl#Ontology> ;" +
				"	<http://www.w3.org/2002/07/owl#imports> <http://www.loa.istc.cnr.it/ontologies/DUL.owl#> , <http://purl.org/ifgi/event-abstraction#> , <http://purl.oclc.org/NET/ssnx/ssn#> , <http://www.opengis.net/ont/geosparql#> ." +
				"<http://purl.org/ifgi/eabs-instance#eventAbstraction_GKXzrI> a eabs:EventAbstraction ." +
				"<http://purl.org/ifgi/eabs-instance#eventType_deBTaw> a <http://purl.org/ifgi/water/flood#HighWaterLevel> ." +
				"<http://purl.org/ifgi/eabs-instance#eventAbstractionRule_BrUDOO> a eabs:EventAbstractionRule ;" +
				"	dul:hasDataValue \"SELECT * FROM ObservationEvent(observer.id = 'urn:ogc:object:feature:Sensor:CSRO:csro-sensor-0002', observedProperty = 'urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel', value < 7.7)\" ." +
						"<http://purl.org/ifgi/eabs-instance#eventDetectionProcedure_pNTvia> a eabs:EventDetectionProcedure ;" +
						"	rdfs:label \"http://dbpedia.org/page/Complex_event_processing\" ." +
						"	<http://purl.org/ifgi/eabs-instance#eventProcessingAgent_NOxwBy> a eabs:EventProcessingAgent ;" +
						"	dc:identifier \"http://giv-wfs.uni-muenster.de/axis2/services/EPSManager\" ." +
						"" +
						"<http://purl.org/ifgi/eabs-instance#eventProcessingAgentOutput_ACohYO> a eabs:EventProcessingAgentOutput ." +
						"<http://purl.org/ifgi/eabs-instance#eventAbstractionValue_ULvDek> a eabs:EventAbstractionValue ." +
						"<http://purl.org/ifgi/eabs-instance#geometryInstance_FYRmzd> a geo:Geometry ;" +
						"	geo:asWKT \"SRID=4326;POINT (-74.43293 42.31481)\"^^sf:wktLiteral ." +
						"<http://purl.org/ifgi/eabs-instance#spaceRegion_ntCZLH> a dul:SpaceRegion ;" +
						"	dul:hasRegionDataValue \"SRID=4326;POINT (-74.43293 42.31481)\"^^sf:wktLiteral ." +
						"<http://purl.org/ifgi/eabs-instance#timeInterval_FwGJAA> a dul:TimeInterval ;" +
						"	dul:hasBeginning \"2006-05-14 22:00:00 +0200\"^^xmlSchema:dateTime ;" +
						" dul:hasEnd \"2006-05-14 22:00:00 +0200\"^^xmlSchema:dateTime ." +
						"<http://purl.org/ifgi/eabs-instance#spatioTemporalRegion_NzRTbR> a dul:SpatioTemporalRegion ." +
						"<http://purl.org/ifgi/eabs-instance#observedProperty_pVBUDE> a ssn:ObservedProperty ;" +
						"	dc:identifier \"urn:ogc:def:phenomenon:OGC:1.0.30:waterlevel\" ." +
						"" +
						"<http://purl.org/ifgi/eabs-instance#eventAbstraction_GKXzrI> ssn:observedProperty <http://purl.org/ifgi/eabs-instance#observedProperty_pVBUDE> ." +
						"" +
						"<http://purl.org/ifgi/eabs-instance#collection_bhydqq> a dul:Collection ." +
						"<http://purl.org/ifgi/eabs-instance#collection_bhydqq> dul:hasMember <http://purl.org/ifgi/eabs-instance#informationObject_IQKWgn> ." +
						"" +
						"<http://purl.org/ifgi/eabs-instance#spatioTemporalRegion_NzRTbR> dul:hasConstituent <http://purl.org/ifgi/eabs-instance#timeInterval_FwGJAA> , <http://purl.org/ifgi/eabs-instance#spaceRegion_ntCZLH> ." +
						"" +
						"<http://purl.org/ifgi/eabs-instance#eventProcessingAgent_NOxwBy> ssn:implements <http://purl.org/ifgi/eabs-instance#eventDetectionProcedure_pNTvia> ." +
						"<http://purl.org/ifgi/eabs-instance#eventProcessingAgentOutput_ACohYO> ssn:isProducedBy <http://purl.org/ifgi/eabs-instance#eventProcessingAgent_NOxwBy> ;" +
						"	ssn:hasValue <http://purl.org/ifgi/eabs-instance#eventAbstractionValue_ULvDek> ." +
						"<http://purl.org/ifgi/eabs-instance#eventAbstractionValue_ULvDek> eabs:hasEventType <http://purl.org/ifgi/eabs-instance#eventType_deBTaw> ." +
						"<http://purl.org/ifgi/eabs-instance#eventType_deBTaw> dul:isDefinedIn <http://purl.org/ifgi/eabs-instance#eventAbstractionRule_BrUDOO> ." +
						"<http://purl.org/ifgi/eabs-instance#eventAbstraction_GKXzrI> geo:hasGeometry <http://purl.org/ifgi/eabs-instance#geometryInstance_FYRmzd> ;" +
						"	dul:isObservableAt <http://purl.org/ifgi/eabs-instance#timeInterval_FwGJAA> ;" +
						"	eabs:processingMethodUsed <http://purl.org/ifgi/eabs-instance#eventDetectionProcedure_pNTvia> ;" +
						"	eabs:processedBy <http://purl.org/ifgi/eabs-instance#eventProcessingAgent_NOxwBy> ;" +
						"	eabs:hasAbstractionResult <http://purl.org/ifgi/eabs-instance#eventProcessingAgentOutput_ACohYO> ;" +
						"	eabs:isClassifiedBy <http://purl.org/ifgi/eabs-instance#eventType_deBTaw> ;" +
						"	dul:satisfies <http://purl.org/ifgi/eabs-instance#eventAbstractionRule_BrUDOO> ;" +
						"	eabs:inferredSpatioTemporalRegion <http://purl.org/ifgi/eabs-instance#spatioTemporalRegion_NzRTbR> ;" +
						"	eabs:isDerivedFrom <http://purl.org/ifgi/eabs-instance#collection_bhydqq> .";
		if (ParliamentKB.getInstance().insertTriples(triples, "N3")) {
			log.info("Triples inserted into Parliament!");
		}
		else {
			log.error("ERROR: Triples not inserted into Parliament.");
		}
	}

}
