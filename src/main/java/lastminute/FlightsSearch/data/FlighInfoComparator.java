package lastminute.FlightsSearch.data;

import java.util.Comparator;

/**
 * Comparator to order FlightInfo objects alphabetically by their destiny airport
 */
public class FlighInfoComparator implements Comparator<FlightInfo>{

	
	
	    public int compare(FlightInfo o1, FlightInfo o2) {
	        return o1.getDestAirport().compareTo(o2.getDestAirport());
	    }
	
}
