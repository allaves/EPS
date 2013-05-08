package de.ifgi.envision.eps.listener;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Interval;
import org.junit.Test;
import org.openrdf.repository.RepositoryException;

import de.ifgi.envision.eps.exception.MalformedRuleException;
import de.ifgi.envision.eps.push.MessageBuilder;

public class AggregateStatementAwareListenerTest {

	@Test
	public void test() {
		MessageBuilder msgBuilder = null;
		AggregateStatementAwareListener listener = null;
		try {
			msgBuilder = new MessageBuilder();
			listener = new AggregateStatementAwareListener();
			
			String timeWindow = "5 hours";
			Date begin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z").parse("2006-04-05T17:44:15 +0000");
			Interval eventInterval = new Interval(begin.getTime(), begin.getTime());
			Interval temporalLocation = listener.setIntervalBeginningFromTimeWindow(timeWindow, eventInterval.getEnd().toDate());
			assertEquals(temporalLocation, new Interval(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z").parse("2006-04-05T12:44:15 +0000").getTime(), begin.getTime()));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
