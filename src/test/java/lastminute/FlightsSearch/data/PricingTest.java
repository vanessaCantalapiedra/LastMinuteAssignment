package lastminute.FlightsSearch.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PricingTest {

	Pricing currentPricing;
	
	@Before
	public void setUp() throws Exception {
		currentPricing = new Pricing(34);
	}

	@Test
	public void testIsDiscount() {
		assertTrue(currentPricing.isDiscount());
		currentPricing.updateTimeDiscount(18);
		assertFalse(currentPricing.isDiscount());
	}

	@Test
	public void testGetPriceAdultString() {
			
		String expectedString = "100";
		float basePrice = 100;
		currentPricing.updateTimeDiscount(18);
		String adultPrice = currentPricing.getPriceAdultString(basePrice);
		assertEquals(expectedString, adultPrice);
		
		currentPricing.updateTimeDiscount(30);
		expectedString = "(80% of 100)";
		adultPrice = currentPricing.getPriceAdultString(basePrice);
		assertEquals(expectedString, adultPrice);
		
		
		currentPricing.updateTimeDiscount(31);
		expectedString = "(80% of 100)";
		adultPrice = currentPricing.getPriceAdultString(basePrice);
		assertEquals(expectedString, adultPrice);
		
		
		currentPricing.updateTimeDiscount(15);
		expectedString = "(120% of 100)";
		adultPrice = currentPricing.getPriceAdultString(basePrice);
		assertEquals(expectedString, adultPrice);
		
		currentPricing.updateTimeDiscount(0);
		expectedString = "(150% of 100)";
		adultPrice = currentPricing.getPriceAdultString(basePrice);
		assertEquals(expectedString, adultPrice);
		
		currentPricing.updateTimeDiscount(3);
		expectedString = "(120% of 100)";
		adultPrice = currentPricing.getPriceAdultString(basePrice);
		assertEquals(expectedString, adultPrice);
		
	}

	@Test
	public void testGetPriceChildString() {
		String expectedString = "67% of 100";
		float basePrice = 100;
		currentPricing.updateTimeDiscount(18);
		String childPrice = currentPricing.getPriceChildString(basePrice);
		assertEquals(expectedString, childPrice);
		
		currentPricing.updateTimeDiscount(30);
		expectedString = "67% of (80% of 100)";
		childPrice = currentPricing.getPriceChildString(basePrice);
		assertEquals(expectedString, childPrice);
		
		currentPricing.updateTimeDiscount(31);
		expectedString = "67% of (80% of 100)";
		childPrice = currentPricing.getPriceChildString(basePrice);
		assertEquals(expectedString, childPrice);
		
		
		currentPricing.updateTimeDiscount(15);
		expectedString = "67% of (120% of 100)";
		childPrice = currentPricing.getPriceChildString(basePrice);
		assertEquals(expectedString, childPrice);
		
		currentPricing.updateTimeDiscount(0);
		expectedString = "67% of (150% of 100)";
		childPrice = currentPricing.getPriceChildString(basePrice);
		assertEquals(expectedString, childPrice);
		
		currentPricing.updateTimeDiscount(3);
		expectedString = "67% of (120% of 100)";
		childPrice = currentPricing.getPriceChildString(basePrice);
		assertEquals(expectedString, childPrice);
	}

	@Test
	public void testGetPriceAdult() {
		float basePrice = 150.0f; 
		currentPricing.updateTimeDiscount(18);
		
		float expectedPrice = basePrice;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceAdult(basePrice) );
		
		currentPricing.updateTimeDiscount(30);
		expectedPrice = basePrice * 0.80f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceAdult(basePrice) );
		
		currentPricing.updateTimeDiscount(31);
		expectedPrice = basePrice * 0.80f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceAdult(basePrice) );
		
		currentPricing.updateTimeDiscount(15);
		expectedPrice = basePrice*1.20f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceAdult(basePrice) );
		
		currentPricing.updateTimeDiscount(0);
		expectedPrice = basePrice * 1.50f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceAdult(basePrice) );
		
		currentPricing.updateTimeDiscount(3);
		expectedPrice = basePrice*1.20f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceAdult(basePrice) );
		
	}

	@Test
	public void testGetPriceChild() {
		
		float basePrice = 150.0f; 
		currentPricing.updateTimeDiscount(18);
		
		float expectedPrice = basePrice * 0.67f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceChild(basePrice) );
		
		currentPricing.updateTimeDiscount(30);
		expectedPrice = (basePrice * 0.80f)*0.67f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceChild(basePrice) );
		
		currentPricing.updateTimeDiscount(31);
		expectedPrice = (basePrice * 0.80f)*0.67f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceChild(basePrice) );
		
		currentPricing.updateTimeDiscount(15);
		expectedPrice = (basePrice*1.20f)*0.67f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceChild(basePrice) );
		
		currentPricing.updateTimeDiscount(0);
		expectedPrice = (basePrice * 1.50f)*0.67f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceChild(basePrice) );
		
		currentPricing.updateTimeDiscount(3);
		expectedPrice = (basePrice*1.20f)*0.67f;
		assertEquals((Float)expectedPrice, (Float)currentPricing.getPriceChild(basePrice) );
		
	}

	@Test
	public void testGetPriceInfant() {
		assertEquals((Float)10.0f, (Float)currentPricing.getPriceInfant("IB"));
		assertEquals((Float) 0.0f, (Float)currentPricing.getPriceInfant("XX"));
	}

}
