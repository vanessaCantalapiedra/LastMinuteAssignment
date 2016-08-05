package lastminute.FlightsSearch;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import lastminute.FlightsSearch.data.FlightInfo;

public class AppModelTest {

	private AppModel myAppModel = new AppModel();
	private String filePath = "flights.txt";
	
	@Before
	public void setUp() throws Exception {
		assertTrue(myAppModel.fillData(filePath));
	}

	@Test
	public void testGetAvailableFlights() {
		ArrayList<FlightInfo> list = myAppModel.getAvailableFlights("CPH", "FCO");
		
		
		FlightInfo [] resultExpected = new FlightInfo[2];
		resultExpected[0] = new FlightInfo("FCO","TK4667","137");
		
		
		
		boolean ok = false;
		if (	resultExpected[0].getDestAirport().equals(list.get(0).getDestAirport()) &&
			resultExpected[0].getFlightCode().equals(list.get(0).getFlightCode()) &&
			resultExpected[0].getPrice()== list.get(0).getPrice() )
		
		{
			ok = true;
		}	
		
		assertTrue(ok);
	}

	@Test
	public void testFillData() {
		File file = new File(this.getClass().getClassLoader().getResource("flights.txt").getPath());
		assertTrue(file.exists());
	}

}
