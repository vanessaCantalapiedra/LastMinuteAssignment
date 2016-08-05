package lastminute.FlightsSearch.data;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import lastminute.FlightsSearch.AppModel;

public class SearchParamsTest {

	private SearchParams currentSearch ;
	private AppModel appModel;
	DecimalFormat myFormatter = new DecimalFormat("##.##");
	
	@Before
	public void setUp() throws Exception {
		appModel = new AppModel();
	    assertTrue(appModel.fillData("flights.txt"));
	    
		currentSearch = new SearchParams("MADRID", "BARCELONA", 1, 0, 0, 10);
		currentSearch.reloadAvailableflights(appModel.getAvailableFlights("MAD", "BCN"));
		
		
	}

	@Test
	public void testGetSearchHeader() {
		
		currentSearch.setOriginCity("MADRID");
		currentSearch.setDestinyCity("BARCELONA");
		currentSearch.setAdutls(1);
		currentSearch.setChildren(0);
		currentSearch.setInfants(0);
		currentSearch.setDaysBetween(10);
		
		String header = currentSearch.getSearchHeader();
		StringBuilder expectedStrbuild = new StringBuilder();
		expectedStrbuild.append("1 adult, 10 days to the departure date, flying MAD -> BCN");
		expectedStrbuild.append(System.lineSeparator());
		expectedStrbuild.append("Flights:");
		
		assertEquals(expectedStrbuild.toString(), header);
		
		currentSearch.setAdutls(0);
		currentSearch.setChildren(0);
		currentSearch.setInfants(0);
		
		expectedStrbuild = new StringBuilder();
		expectedStrbuild.append("No passengers selected, 10 days to the departure date, flying MAD -> BCN");
		expectedStrbuild.append(System.lineSeparator());
		expectedStrbuild.append("Flights:");
		
		header = currentSearch.getSearchHeader();
		assertEquals(expectedStrbuild.toString(), header);
		
		currentSearch.setChildren(1);
		currentSearch.setInfants(1);
		
		expectedStrbuild = new StringBuilder();
		expectedStrbuild.append("1 children, 1 infants, 10 days to the departure date, flying MAD -> BCN");
		expectedStrbuild.append(System.lineSeparator());
		expectedStrbuild.append("Flights:");
		
		
		header = currentSearch.getSearchHeader();
		assertEquals(expectedStrbuild.toString(), header);
		
	}

	@Test
	public void testCalculateTotalPrice() {
		currentSearch.setAdutls(1);
		currentSearch.setChildren(0);
		currentSearch.setInfants(0);
		currentSearch.setDaysBetween(30);
		float totalPrice = currentSearch.calculateTotalPrice(197, "TK");
		float expectedPrice = 157.6f;
		
		assertEquals((Float)expectedPrice, (Float)totalPrice);
		
		currentSearch.setAdutls(2);
		currentSearch.setChildren(1);
		currentSearch.setInfants(1);
		currentSearch.setDaysBetween(15);
		totalPrice = currentSearch.calculateTotalPrice(250, "TK");
		expectedPrice = 806f;
		
		assertEquals((Float)expectedPrice, (Float)totalPrice);
		
		
		currentSearch.setAdutls(1);
		currentSearch.setChildren(2);
		currentSearch.setInfants(0);
		currentSearch.setDaysBetween(2);
		totalPrice = currentSearch.calculateTotalPrice(259, "IB");
		expectedPrice = 909.09f;
		
		assertEquals((Float)expectedPrice, (Float)totalPrice);
		
	}
	
	

	@Test
	public void testPrintPricingFlights() {
		
		currentSearch = new SearchParams("BARCELONA", "MADRID",1, 2, 0, 2);
		currentSearch.reloadAvailableflights(appModel.getAvailableFlights("BCN", "MAD"));
		
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

}
