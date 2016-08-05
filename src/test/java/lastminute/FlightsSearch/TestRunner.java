package lastminute.FlightsSearch;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import lastminute.FlightsSearch.data.FlighInfoComparatorTest;
import lastminute.FlightsSearch.data.FlightDataUtilsTest;
import lastminute.FlightsSearch.data.PricingTest;
import lastminute.FlightsSearch.data.SearchParamsTest;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(
    		  AutomatedFlightSearchTest.class, 
    		  AppModelTest.class, 
    		  AppControllerTest.class,
    		  FlighInfoComparatorTest.class,
    		  FlightDataUtilsTest.class,
    		  PricingTest.class,
    		  SearchParamsTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}  	