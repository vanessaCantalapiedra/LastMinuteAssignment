package lastminute.FlightsSearch.data;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Stores constants and functionalities to be used across all the flightdata classess
 * 
 */
public class FlightDataUtils {

	
	 /**
     * Given a date , returns the number of days since today.
     * we assume that the selectedDate is always bigger thant today
     * @param selectedDate date to calculate from
     */
	 public static int daysBetween(Calendar startDate, Calendar endDate)
	 {  
		   Calendar startDateCopy = (Calendar) startDate.clone(); 
		   int  daysBetween = 0;  
		   while (startDateCopy.before(endDate)) {  
			   startDateCopy .add(Calendar.DAY_OF_MONTH, 1);  
		     daysBetween++;  
		   }  
		   return daysBetween;  
	 }  
	
	 public static final HashMap<String, String> IATA_CITY;
	    static
	    {
	    	IATA_CITY = new HashMap<String, String>();
	    	IATA_CITY.put("AMSTERDAM", "AMS");
	    	IATA_CITY.put("BARCELONA", "BCN");
	    	IATA_CITY.put("CONPENHAGEN", "CPH");
	    	IATA_CITY.put("FRANKFURT", "FRA");
	    	IATA_CITY.put("ISTANBUL", "IST");
	    	IATA_CITY.put("LONDON", "LHR");
	    	IATA_CITY.put("MADRID", "MAD");
	    	IATA_CITY.put("PARIS", "CDG");
	    	IATA_CITY.put("ROME", "FCO ");
	    }
	    
	    //LOGGING VALUES
	    public static final String LOGGER_NAME="LOGGER";
	    public static final String LOG_FILE = "lastminuteFlightSearch.log";
	    
	    //DATA VALUES
	    public static final String DATA_PATH = "flights.txt";
	    
}
