package de.ifgi.envision.eps.push;

import java.io.IOException;
import org.apache.log4j.Logger;
import com.bbn.parliament.jena.joseki.client.RemoteModel;

/*
 * Singleton
 * Represents a Parliament Knowledge Base
 */
public class ParliamentKB {
	
	private static Logger log = Logger.getLogger(ParliamentKB.class); 
	
	private static String PARLIAMENT_BASE = "http://giv-llaves.uni-muenster.de:8081/parliament/sparql";
	private static String PARLIAMENT_BULK = "http://giv-llaves.uni-muenster.de:8081/parliament/bulk";
	
	private static ParliamentKB instance;
	private RemoteModel model;
	
	public static ParliamentKB getInstance() {
		if (instance == null) {
			// get current context classloader                                                                                                                                  
			//ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
			// then alter the class-loader (but which one ? the one used to load this class itself) with:
			//Thread.currentThread().setContextClassLoader(ParliamentKB.class.getClassLoader());
			
			instance = new ParliamentKB();
			
			// restore the class loader to its original value:
			//Thread.currentThread().setContextClassLoader(contextClassloader);
		}
		return instance;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	private ParliamentKB() {
		model = new RemoteModel(PARLIAMENT_BASE, PARLIAMENT_BULK);
	}
	
	/*
	 * Inserts triples into the model by reading from a serialized input source
	 */
	public boolean insertTriples(String triples, String format) {
		try {
			model.insertStatements(triples, format, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	

}
