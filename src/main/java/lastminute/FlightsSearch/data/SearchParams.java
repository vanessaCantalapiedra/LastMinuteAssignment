package lastminute.FlightsSearch.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Class to store all the parameters of an specific search. So it allows to check easily if some parameters
 * have been changed. 
 */

public final class SearchParams {
	
	String originCity;
	String destinyCity;
	ArrayList<FlightInfo> flights;
	
	public SearchParams(String origin, String destiny, int adults, int children, int infants, int daysBetween)
	{
		currentPricing =  new Pricing(daysBetween);
		flights = new ArrayList<FlightInfo>();
		
		setAdutls(adults);
		setChildren(children);
		setInfants(infants);
		setDaysBetween(daysBetween);
		setDestinyCity(destiny);
		setOriginCity(origin);
		 
	}
	
	
	public int getFlightsNumber()
	{
		return flights.size();
	}
	
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {	
		this.originCity = FlightDataUtils.IATA_CITY.get(originCity);
		if (this.originCity==null)
		{
		  this.originCity="";     
		}
	}
	public String getDestinyCity() {
		return destinyCity;
	}
	public void setDestinyCity(String destinyCity) {
		this.destinyCity = FlightDataUtils.IATA_CITY.get(destinyCity);
		if (this.destinyCity==null)
		{
		  this.destinyCity="";     
		}
	}
	public int getAdults() {
		return adults;
	}
	public void setAdutls(int adutls) {
		this.adults = adutls;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public int getInfants() {
		return infants;
	}
	public void setInfants(int infants) {
		this.infants = infants;
	}
	int   daysBetween;
	public int getDaysBetween() {
		return daysBetween;
	}
	public void setDaysBetween(int daysBetween) {
		this.daysBetween = daysBetween;
		//automatically updates the price
		currentPricing.updateTimeDiscount(daysBetween);
	}
	
	private int    adults;
	private int    children;
	private int    infants;
	private float 	price;
	
	Pricing currentPricing;
	
	/**
	 * updates only the list with the available flights
	 * @param availableFlights list with the new list of available flights
	 */
	public  void reloadAvailableflights ( ArrayList<FlightInfo> availableFlights)
	{
		this.flights = availableFlights; 
	}

	/**
	 * Creates the text to write at the begining of the search
	 * @return the header of the search with form to be printed
	 */
	public String getSearchHeader()
	{
		String adultsStr = "";
		String childrenStr ="";
		String infantsStr = "";
		StringBuilder strFinal = new StringBuilder();
		
		if (this.flights.size() > 0)
		{
			if (adults>0)
			{
				strFinal.append( adults + " adult, ");
			}
			
			if (children>0)
			{
				strFinal.append(children + " children, ");
			}
			
			if (infants>0)
			{
				strFinal.append(infants + " infants, ");
			}
			
			if (strFinal.length() == 0)
			{
				strFinal.append("No passengers selected, ");
			}
			
				strFinal.append(daysBetween +" days to the departure date, flying ");
			
				
			
		}
		
		if (originCity.isEmpty())
		{
			strFinal.append("ORIGIN CITY NOT FOUND");
		}
		else
		{
			strFinal.append(originCity);
		}
		strFinal.append(" -> ");
		if (destinyCity.isEmpty())
		{
			strFinal.append("DESTINY CITY NOT FOUND");
		}
		else
		{
			strFinal.append(destinyCity);
		}
		
		
		if (this.flights.size() > 0)
		{
			strFinal.append(System.lineSeparator());
			strFinal.append("Flights:");
		}
		
		
		return strFinal.toString();
	}
	
	/**
	 * Checks wether the pricing should be print with detailed information
	 * @return true or false depending of the number of passengers and type
	 */
	private boolean isDetailedInfo ()
	{
		boolean result = false;
		boolean bAdults = (adults!=1);
		boolean bChildren = (children!=1);
		boolean bInfants = (infants !=1);
			
		if ((currentPricing.isDiscount() && (bAdults ^ bChildren ^ bInfants) ) || adults >1 || children>1 || infants> 1 || (bAdults ^ bChildren ^ bInfants) )
		{
			result = true;
		}
		
		return result;
	}
    
	/**
	 * Calculates the price of each available flight, including all passengers 
	 * @param basePrice the base price of the flight
	 * @param airline the used airline, need to apply discount on infants passengers
	 * @return the total price of all passengers
	 */
	public float calculateTotalPrice(float basePrice, String airline)
	{
		float totalPrice = 0.0f;
		totalPrice = adults*currentPricing.getPriceAdult(basePrice) + children * currentPricing.getPriceChild(basePrice) + infants* currentPricing.getPriceInfant(airline); 
		return totalPrice;
	}
	
	/**
	 * Creates a string with the content of the flight , including pricing  to be printed 
	 * @param the index of the selected flight to be printed
	 * @return the information of the flight ready to be printed
	 */
	public String printPricingFlights (int idx)
	{
		StringBuilder strFlight = new StringBuilder();
		FlightInfo tempflightInfo = this.flights.get(idx);
			//try 25E6
			strFlight.append("\u2022");
			strFlight.append(tempflightInfo.getFlightCode());
			float totalPrice = calculateTotalPrice(tempflightInfo.getPrice(), tempflightInfo.getAirlineIATA());
			
			float basePrice = tempflightInfo.getPrice();
			
			//decilam format to remove unnecesary 0.0
			NumberFormat numberForm = new DecimalFormat("#.##");
	       
			strFlight.append(", " + numberForm.format(totalPrice) + " \u20AC ");
			
			StringBuilder strPriceDetailed = new StringBuilder();
			int passengerTypes = 0;
			if (isDetailedInfo())
			{
				if (adults>1)
				{	
					strPriceDetailed.append(adults +  " * ");
					
				}
				if (adults>0)
				{
					strPriceDetailed.append(currentPricing.getPriceAdultString(basePrice));
					passengerTypes++;
				}
				
				if (children>=1 && strPriceDetailed.length() > 0)
				{
					strPriceDetailed.append("+");
				}
				
				
				if (children>1)
				{
					strPriceDetailed.append(children +  " * ");				
				}
				if (children>0)
				{
					strPriceDetailed.append(currentPricing.getPriceChildString(basePrice));
					passengerTypes++;
				}
				
				//eliminate 0 euros
				float infantPrice = currentPricing.getPriceInfant(tempflightInfo.getAirlineIATA());
				
				if (infantPrice >0)
				{
					if (infants>=1 && strPriceDetailed.length() >0)
					{
						strPriceDetailed.append("+");
					}
					
					if (infants>1)
					{
						
						strPriceDetailed.append(infants +  " * ");	
					}
					if (infants>0)
					{
						strPriceDetailed.append(numberForm.format(infantPrice));
						passengerTypes++;
					}	
					
			
				}
						
			}
		
			if (passengerTypes>1)
			{
				strFlight.append("(");
				strFlight.append(strPriceDetailed);
				strFlight.append(")");
			}
			else
			{
				strFlight.append(strPriceDetailed);
			}
			
		return strFlight.toString();
	}
	
	@Override
    public  String toString() {
        return adults + "adults, "+ children + " children," + infants + " infants, "+daysBetween +" days to the departure date, flying "+ originCity + " -> "+ destinyCity; 
    }
	
	
	

}
