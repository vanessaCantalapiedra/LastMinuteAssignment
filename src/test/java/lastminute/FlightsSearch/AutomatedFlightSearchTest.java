package lastminute.FlightsSearch;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import lastminute.FlightsSearch.data.FlightDataUtils;
import lastminute.FlightsSearch.data.SearchParams;

public class AutomatedFlightSearchTest {

	private AppController appController;	
	private AppModel appModel;
	private AppView appView;
	private SearchParams currentSearch;
	
	 DecimalFormat myFormatter = new DecimalFormat("##.##");
    
 
	 @Before 
	   public void setUp() {
		 appController = new AppController();
		 appModel = new AppModel();
		 appController.setAppModel(appModel);
	     assertTrue(appModel.fillData("flights.txt"));
	   }
	 
	 
	@Test
	public void test1() {
		
		System.out.println("-------------------------------------------------");
		
		currentSearch = new SearchParams("AMSTERDAM", "FRANKFURT",1, 0, 0, 30);
		appController.setSearch(currentSearch);
		appController.processSearch();
		StringBuilder strBuilderExpectedResult = new StringBuilder();
		strBuilderExpectedResult.append("1 adult, 30 days to the departure date, flying AMS -> FRA");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("Flights:");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•TK2372, "+ myFormatter.format(157.6)+" € ");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•TK2659, "+ myFormatter.format(198.4)+" € ");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•LH5909, "+ myFormatter.format(90.4)+" € ");
		String strResult = strBuilderExpectedResult.toString();
		
		StringBuilder strBuilderFlight = new StringBuilder();
		strBuilderFlight.append(currentSearch.getSearchHeader());
		
		int flightsNb = currentSearch.getFlightsNumber();
		
		for (int i=0; i<flightsNb;i++)
		{
			strBuilderFlight.append(System.lineSeparator());
			String flight  = currentSearch.printPricingFlights(i);
			strBuilderFlight.append(flight);
		}
		
		if ( flightsNb == 0 )
		{
			strBuilderFlight.append(System.lineSeparator());
			strBuilderFlight.append("No flights available");
		   
		}
		
		//System.out.println(strBuilderExpectedResult.toString());
		System.out.println(strBuilderFlight.toString());
		
	
		
		assertEquals(strBuilderExpectedResult.toString(),strBuilderFlight.toString());
	}
	
	
	@Test
	public void test2() {
		
		System.out.println("-------------------------------------------------");
		
		currentSearch = new SearchParams("LONDON", "ISTANBUL",2, 1, 1, 15);
		appController.setSearch(currentSearch);
		appController.processSearch();
		StringBuilder strBuilderExpectedResult = new StringBuilder();
		strBuilderExpectedResult.append("2 adult, 1 children, 1 infants, 15 days to the departure date, flying LHR -> IST");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("Flights:");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•TK8891, 806 € (2 * (120% of 250)+67% of (120% of 250)+5)");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•LH1085, "+ myFormatter.format(481.19)+" € (2 * (120% of 148)+67% of (120% of 148)+7)");
		String strResult = strBuilderExpectedResult.toString();
		
		StringBuilder strBuilderFlight = new StringBuilder();
		strBuilderFlight.append(currentSearch.getSearchHeader());
		
		int flightsNb = currentSearch.getFlightsNumber();
		
		for (int i=0; i<flightsNb;i++)
		{
			strBuilderFlight.append(System.lineSeparator());
			String flight  = currentSearch.printPricingFlights(i);
			strBuilderFlight.append(flight);
		}
		
		if ( flightsNb == 0 )
		{
			strBuilderFlight.append(System.lineSeparator());
			strBuilderFlight.append("No flights available");
		   
		}
		
		
		//System.out.println(strBuilderExpectedResult.toString());
		System.out.println(strBuilderFlight.toString());
		
	
		
		assertEquals(strBuilderExpectedResult.toString(),strBuilderFlight.toString());
	}
	
	
	@Test
	public void test3() {
		
		System.out.println("-------------------------------------------------");
		currentSearch = new SearchParams("BARCELONA", "MADRID",1, 2, 0, 2);
		appController.setSearch(currentSearch);
		appController.processSearch();
		StringBuilder strBuilderExpectedResult = new StringBuilder();
		strBuilderExpectedResult.append("1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("Flights:");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•IB2171, "+ myFormatter.format(909.09)+" € ((150% of 259)+2 * 67% of (150% of 259))");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("•LH5496, "+ myFormatter.format(1028.43)+" € ((150% of 293)+2 * 67% of (150% of 293))");
		String strResult = strBuilderExpectedResult.toString();
		
		StringBuilder strBuilderFlight = new StringBuilder();
		strBuilderFlight.append(currentSearch.getSearchHeader());
		
		
		int flightsNb = currentSearch.getFlightsNumber();
		
		for (int i=0; i<flightsNb;i++)
		{
			strBuilderFlight.append(System.lineSeparator());
			String flight  = currentSearch.printPricingFlights(i);
			strBuilderFlight.append(flight);
		}
		
		if ( flightsNb == 0 )
		{
			strBuilderFlight.append(System.lineSeparator());
			strBuilderFlight.append("No flights available");
		   
		}
		
		//System.out.println(strBuilderExpectedResult.toString());
		System.out.println(strBuilderFlight.toString());
		
	
		
		assertEquals(strBuilderExpectedResult.toString(),strBuilderFlight.toString());
	}
	
	@Test
	public void test4() {
		
		System.out.println("-------------------------------------------------");
		currentSearch = new SearchParams("PARIS", "FRANKFURT",1, 2, 0, 2);
		appController.setSearch(currentSearch);
		appController.processSearch();
		StringBuilder strBuilderExpectedResult = new StringBuilder();
		strBuilderExpectedResult.append("CDG -> FRA");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("No flights available");
		
		String strResult = strBuilderExpectedResult.toString();
		
		StringBuilder strBuilderFlight = new StringBuilder();
		strBuilderFlight.append(currentSearch.getSearchHeader());
		
		int flightsNb = currentSearch.getFlightsNumber();
		
		for (int i=0; i<flightsNb;i++)
		{
			strBuilderFlight.append(System.lineSeparator());
			String flight  = currentSearch.printPricingFlights(i);
			strBuilderFlight.append(flight);
		}
		
		if ( flightsNb == 0 )
		{
			strBuilderFlight.append(System.lineSeparator());
			strBuilderFlight.append("No flights available");
		   
		}
		
		//System.out.println(strBuilderExpectedResult.toString());
		System.out.println(strBuilderFlight.toString());
		
	
		
		assertEquals(strBuilderExpectedResult.toString(),strBuilderFlight.toString());
	}
	
	@Test
	public void test5() {
		System.out.println("-------------------------------------------------");
		currentSearch = new SearchParams("XXXX", "XXXX",1, 2, 0, 2);
		appController.setSearch(currentSearch);
		appController.processSearch();
		StringBuilder strBuilderExpectedResult = new StringBuilder();
		strBuilderExpectedResult.append("ORIGIN CITY NOT FOUND -> DESTINY CITY NOT FOUND");
		strBuilderExpectedResult.append(System.lineSeparator());
		strBuilderExpectedResult.append("No flights available");
		
		
		String strResult = strBuilderExpectedResult.toString();
		
		StringBuilder strBuilderFlight = new StringBuilder();
		strBuilderFlight.append(currentSearch.getSearchHeader());
		
		int flightsNb = currentSearch.getFlightsNumber();
		
		for (int i=0; i<flightsNb;i++)
		{
			strBuilderFlight.append(System.lineSeparator());
			String flight  = currentSearch.printPricingFlights(i);
			strBuilderFlight.append(flight);
		}
		
		if ( flightsNb == 0 )
		{
			strBuilderFlight.append(System.lineSeparator());
			strBuilderFlight.append("No flights available");
		   
		}
		
		//System.out.println(strBuilderExpectedResult.toString());
		System.out.println(strBuilderFlight.toString());
		
	
		
		assertEquals(strBuilderExpectedResult.toString(),strBuilderFlight.toString());
	}
	
	
}
