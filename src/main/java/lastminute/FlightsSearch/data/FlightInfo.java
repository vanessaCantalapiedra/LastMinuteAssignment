package lastminute.FlightsSearch.data;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores information related to the different flights
 * 
 */
public final class FlightInfo {

	private String airlineIATA;
	private String destAirport;
	public String getDestAirport() {
		return destAirport;
	}

	public void setDestAirport(String destAirport) {
		this.destAirport = destAirport;
	}

	private String flightCode;
	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	private float  price;
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	private static final Logger LOGGER = Logger.getLogger( FlightDataUtils.LOGGER_NAME);
	
	public FlightInfo(String destAirport, String flightcode, String price)
	{
		
		this.destAirport = destAirport;
		this.flightCode = flightcode;
		this.setAirlineIATA(flightCode.substring(0, 2));
		
		try 
		{
		  this.price = Float.parseFloat(price);
		}
		catch (NumberFormatException ex)
		{
			 LOGGER.log( Level.SEVERE, ex.toString(), ex );
		}
	}
	
	
	@Override
    public  String toString() {
        return "FlightInfo [destAirpot=" + destAirport + ", flightCode="
                + flightCode + ", price=" + price + "]";
    }

	public String getAirlineIATA() {
		return airlineIATA;
	}

	public void setAirlineIATA(String airlineIATA) {
		this.airlineIATA = airlineIATA;
	}


	
}
