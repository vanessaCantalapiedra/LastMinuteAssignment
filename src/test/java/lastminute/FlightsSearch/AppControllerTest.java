package lastminute.FlightsSearch;

import static org.junit.Assert.*;

import org.junit.Test;

import lastminute.FlightsSearch.data.FlightDataUtils;

public class AppControllerTest {
	
	private AppModel myModel = new AppModel();
	private String filePath = "flights.txt";
	@Test
	public void testLoadData() {
		assertTrue(myModel.fillData(filePath));
	}

}
