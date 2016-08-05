package lastminute.FlightsSearch.data;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FlightDataUtilsTest {

	@Test
	public void testDaysBetween() {
		
		DateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
	   try
	   {
		   
	   
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dateForm.parse("04/08/2016"));
		//c1.set(Calendar.SECOND, 0);
		//c1.set(Calendar.MILLISECOND, 0);
		//c1.set(Calendar.HOUR, 0);
		
		    
		Calendar c2 = Calendar.getInstance();
		c2.setTime(dateForm.parse("10/08/2016"));
		//c2.set(Calendar.SECOND, 0);
		//c2.set(Calendar.MILLISECOND, 0);
		//c2.set(Calendar.HOUR, 0);
		
		assertEquals(6, FlightDataUtils.daysBetween(c1, c2));
		c2.setTime(dateForm.parse("06/09/2016"));
		assertEquals(33, FlightDataUtils.daysBetween(c1, c2));
		c2.setTime(dateForm.parse ("04/08/2016"));
		assertEquals(0,FlightDataUtils.daysBetween(c1, c2));
		c2.setTime(dateForm.parse ("04/08/2017"));
		assertEquals(365,FlightDataUtils.daysBetween(c1, c2));
		c2.setTime(dateForm.parse ("03/08/2016"));
		assertEquals(0,FlightDataUtils.daysBetween(c1, c2));
	   }
	   catch(Exception e)
	   {
		   fail("Exception parsing dates");
	   }
	}

}
