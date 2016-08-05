package lastminute.FlightsSearch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import lastminute.FlightsSearch.data.FlightDataUtils;
import lastminute.FlightsSearch.data.FlightInfo;
import lastminute.FlightsSearch.data.SearchParams;

/**
 * Controller class
 */
public class AppController implements SelectionListener{
	
	AppModel myModel;
	AppView myView;
	static Logger myLogger = Logger.getLogger(FlightDataUtils.LOGGER_NAME);
	SearchParams currentSearch ;
	
	/**
	 * Sets the view and the controller and executes the initial load of data
	 * @param model class to manage the data
	 * @param view class to represent the data
	 */
	public AppController (AppModel model, AppView view)
	{
		myModel = model;
		myView = view;
		loadData();
	}
	
	public AppController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Executes the initial load of data from the txt file or any other kind of source
	 */
	public void loadData ()
	{
		if (!myModel.fillData(FlightDataUtils.DATA_PATH))
		{
			myView.displayErrorMessage("Error al cargar los datos");
			myLogger.severe("Error loading flight info.");
		}
		else
		{
			myLogger.info("Flight info loaded successfully.");
		}
	}
	
	/**
	 * Display the flights available for the executed search
	 */
	public void printResults ()
	{
		myView.cleanResultsList();
		
		String header = currentSearch.getSearchHeader();
		int flightsNb = currentSearch.getFlightsNumber();
		myView.addFlightResult(header);
		myLogger.info(header);
		System.out.println(header);
	    
		
		
		    
			for (int i=0; i<flightsNb;i++)
			{
				String flight  = currentSearch.printPricingFlights(i);
				myView.addFlightResult(flight);
				myLogger.info(flight);
			    System.out.println(flight);
				
			}
		
			if ( flightsNb == 0 )
		{
			myView.addFlightResult("No flights available");
			myLogger.info("No flights available");
		    System.out.println("No flights available");
		}
	
	}
	
	/**
	 * Executes the search based on the information given by the viewer
	 */
	public void executeSearch ()
	{
		 Calendar date = myView.getDate();
		 String originCity = myView.getOriginCity();
		 String destinyCity = myView.getDestinyCity();
		 int  adults = myView.getAdults();
		 int  children = myView.getChildren();
		 int  infants = myView.getInfants();
		 
		 //get date from today
		 Calendar today = Calendar.getInstance() ;
		 
		 today.set(Calendar.HOUR, 0);
		 today.set(Calendar.MINUTE, 0);
		 today.set(Calendar.MILLISECOND, 0);
			
		 StringBuilder errorMessage = new StringBuilder();
		 
		 
		 //check for parameters to be ok
		 if (originCity.equals(destinyCity))
		 {
			 errorMessage.append("Origin and destiny cities are the same. Please change this.");
			 errorMessage.append(System.getProperty("line.separator"));
		 }
		 
		 if (today.after(date))
		 {
			 errorMessage.append("Selected date is not ok. Please select a date equals or bigger than today.");
			 errorMessage.append(System.getProperty("line.separator"));
		 }
		 
		 if (adults==0 && children == 0 && infants == 0)
		 {
			 errorMessage.append("Please, select at least one passenger.");
			 errorMessage.append(System.getProperty("line.separator"));
		 }
	
		 if (errorMessage.length() != 0)
		 {
			 myView.displayErrorMessage(errorMessage.toString());
		 }
		 else
		 {//we can proceed to the search
			 //we should check if the parameters have been changed for example to know i we need to reload the list of flights or not
			currentSearch = new SearchParams(originCity, destinyCity, myView.getAdults(), myView.getChildren(),myView.getInfants(), FlightDataUtils.daysBetween(today, date));
			processSearch();
			printResults();
			 
		 } 		 
	}

	/**
	 * Reloads the set of flights of the search. So we need to do this only when the cities have been changed
	 */
	public void processSearch ()
	{
		currentSearch.reloadAvailableflights (myModel.getAvailableFlights(currentSearch.getOriginCity(), currentSearch.getDestinyCity()));
	}

	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		executeSearch();		
	}
	
	
	public void setSearch (SearchParams search)
	{
		this.currentSearch = search;
	}
	
	public void setAppModel(AppModel appModel)
	{
		this.myModel = appModel;
	}
}
;