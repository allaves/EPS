package de.ifgi.envision.eps.push;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import de.ifgi.envision.eps.core.EventProcessingServiceProvider;

public class EventPusher {
	
	private static EventPusher instance;
	
	Logger log = Logger.getLogger("Event Pusher"); 
	private static String SERVER = "dunedin.uni-muenster.de";
	//private static int PORT = 15672;
	//public static String EXCHANGE = "amq.fanout";
	private static String EXCHANGE = "events";
	
	//public static String QUEUE = "flood_events";
	private Channel channel; 
	private Connection connection;
	
		
	public static EventPusher getEventPusher() {
		if (instance == null) {
			// get current context classloader                                                                                                                                  
			ClassLoader contextClassloader = Thread.currentThread().getContextClassLoader();
			// then alter the class-loader (but which one ? the one used to load this class itself) with:
			Thread.currentThread().setContextClassLoader(EventPusher.class.getClassLoader());
			
			instance = new EventPusher();
			
			// restore the class loader to its original value:
			Thread.currentThread().setContextClassLoader(contextClassloader);
		}
		return instance;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	private EventPusher() {
		
	}

	
	public void initConnection() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(SERVER);
		try {
			connection = factory.newConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Publishes the message to an exchange that delivers it to all the binded queues
	 */
	public void push(String message) {
		try {
			channel = connection.createChannel();
			channel.basicPublish(EXCHANGE, "", null, message.getBytes());
			//System.out.println(" [x] Sent '" + message + "'");
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
