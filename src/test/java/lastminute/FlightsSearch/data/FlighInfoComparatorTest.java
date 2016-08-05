package lastminute.FlightsSearch.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlighInfoComparatorTest {

	
	 private final FlighInfoComparator comparator = new FlighInfoComparator();
	
	 @Test
	    public void testEqual() {
	        FlightInfo info1 = new FlightInfo("Londres", "xxx", "45");
	        FlightInfo info2 = new FlightInfo("Londres","yyy","33");
	        int result = comparator.compare(info1, info2);
	        assertTrue("expected to be equal", result == 0);
	    }

	    @Test
	    public void testGreaterThan() {
	    	FlightInfo info1 = new FlightInfo("Zurich", "xxx", "45");
	        FlightInfo info2 = new FlightInfo("Londres","yyy","33");
	        int result = comparator.compare(info1, info2);
	        assertTrue("expected to be greater than", result >= 1);
	    }

	    @Test
	    public void testLessThan() {
	    	FlightInfo info1 = new FlightInfo("Londres", "xxx", "45");
	        FlightInfo info2 = new FlightInfo("Zurich","yyy","33");
	        int result = comparator.compare(info1, info2);
	        assertTrue("expected to be less than", result <= -1);
	    }

}
