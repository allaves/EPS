package de.ifgi.envision.eps.push;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import org.junit.Test;

import de.ifgi.envision.eps.service.WaterServiceUSGS;



public class TestWaterServiceUSGS {
	
	private WaterServiceUSGS service;
	private String URL = "http://waterservices.usgs.gov/nwis/iv?sites=01350355,01350100,01350101,01350000,01351500&startDT=2012-02-01&endDT=2012-02-02&parameterCd=00065";
	private String CHARSET = "UTF-8";
	
	public TestWaterServiceUSGS() {
		service = new WaterServiceUSGS(URL);
	}
	
//	@Test
//	public void testGetResponse() {
//		File f = service.getResponse();
//		Scanner scanner = null;
//		StringBuilder text = new StringBuilder();
//		try {
//		    String nl = System.getProperty("line.separator");
//		    scanner = new Scanner(new FileInputStream(f), CHARSET);
//		    while (scanner.hasNextLine()){
//		    	text.append(scanner.nextLine() + nl);
//		    }
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			scanner.close();
//	    }
//		System.out.println(text);
//	}
	
//	@Test
//	public void testGetResponseBody() {
//		String responseBody = service.getResponseBody();
//		System.out.println(responseBody);
//	}
	
	@Test
	public void testScheduleGetResponseBody() {
		service.scheduleGetResponseBody();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
