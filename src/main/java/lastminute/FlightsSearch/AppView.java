package lastminute.FlightsSearch;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.browser.Browser;

/**
 * Viewer Class
 */
public class AppView {

	//CONSTANTS
	private final static String [] CITIES =  {"AMSTERDAM","BARCELONA","CONPENHAGEN", "FRANKFURT","ISTANBUL","LONDON", "MADRID","PARIS","ROME"};
	protected Shell shlLastminuteSearchFlight;
	private AppController controller;

	
	// CONTROLLERS
	Combo comboOrigin = null;
	Combo comboDestination = null;
	DateTime departDate =null;
	Spinner adultsNb = null;
	Spinner childNb = null;
	Spinner infantsNb = null;
	List list;
	
	
	void setController (AppController controller)
	{
		this.controller = controller;
	}
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLastminuteSearchFlight.open();
		shlLastminuteSearchFlight.layout();
		while (!shlLastminuteSearchFlight.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public String getOriginCity ()
	{
		return comboOrigin.getText();
	}
	
	public String getDestinyCity ()
	{
		return comboDestination.getText();
	}
	
	public int getAdults ()
	{
		return Integer.parseInt(adultsNb.getText());
	}
	
	public int getChildren ()
	{
		return Integer.parseInt(childNb.getText());
	}
	
	public int getInfants ()
	{
		return Integer.parseInt(infantsNb.getText());
	}
	
	public void displayErrorMessage (String message)
	{
		 MessageBox messageBox = new MessageBox(shlLastminuteSearchFlight);
		 messageBox.setMessage(message);
		 messageBox.open();
    }
	
	public void cleanResultsList  ()
	{
		list.removeAll();
	}
	
	public Calendar getDate ()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, departDate.getDay());
		calendar.set(Calendar.MONTH, departDate.getMonth());
		calendar.set(Calendar.YEAR, departDate.getYear());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	
		return calendar ;
	}
	
	public void addFlightResult (String flightInfo)
	{
		list.add(flightInfo);
	}
	
	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlLastminuteSearchFlight = new Shell();
		shlLastminuteSearchFlight.setSize(535, 496);
		shlLastminuteSearchFlight.setText("LastMinute Search Flight");
		
		Composite searchcomposite = new Composite(shlLastminuteSearchFlight, SWT.NONE);
		searchcomposite.setBounds(10, 10, 497, 429);
		
		Group grpSearchParameters = new Group(searchcomposite, SWT.NONE);
		grpSearchParameters.setText("Search Parameters");
		grpSearchParameters.setBounds(10, 10, 477, 239);
		
		Label lblOriginAirport = new Label(grpSearchParameters, SWT.NONE);
		lblOriginAirport.setBounds(10, 38, 128, 20);
		lblOriginAirport.setText("Origin Airport:");
		
		comboOrigin = new Combo(grpSearchParameters,SWT.DROP_DOWN | SWT.READ_ONLY);
		comboOrigin.setBounds(182, 35, 285, 28);
		comboOrigin.setItems(CITIES);
		comboOrigin.select(0);
		
		Label lblDestinationAirport = new Label(grpSearchParameters, SWT.NONE);
		lblDestinationAirport.setBounds(10, 81, 144, 20);
		lblDestinationAirport.setText("Destination Airport:");
		
		comboDestination = new Combo(grpSearchParameters, SWT.DROP_DOWN | SWT.READ_ONLY);
		comboDestination.setBounds(182, 78, 285, 28);
		comboDestination.setItems(CITIES);
		comboDestination.select(0);
		
		 departDate = new DateTime(grpSearchParameters, SWT.BORDER | SWT.READ_ONLY);
		departDate.setBounds(182, 116, 116, 28);
		
		Label lblDepartureDate = new Label(grpSearchParameters, SWT.NONE);
		lblDepartureDate.setText("Departure date:");
		lblDepartureDate.setBounds(10, 124, 144, 20);
		
		Label lblAdults = new Label(grpSearchParameters, SWT.NONE );
		lblAdults.setText("Adults:");
		lblAdults.setBounds(10, 166, 57, 20);
		
		Label lblChilds = new Label(grpSearchParameters, SWT.NONE);
		lblChilds.setText("Child:");
		lblChilds.setBounds(178, 166, 45, 20);
		
		Label lblInfants = new Label(grpSearchParameters, SWT.NONE);
		lblInfants.setText("Infants:");
		lblInfants.setBounds(322, 166, 57, 20);
		
		adultsNb = new Spinner(grpSearchParameters, SWT.BORDER | SWT.READ_ONLY);
		adultsNb.setBounds(73, 163, 59, 26);
		
		childNb = new Spinner(grpSearchParameters, SWT.BORDER | SWT.READ_ONLY);
		childNb.setBounds(225, 163, 59, 26);
		
		infantsNb = new Spinner(grpSearchParameters, SWT.BORDER | SWT.READ_ONLY);
		infantsNb.setBounds(385, 163, 59, 26);
		
		Button btnSearch = new Button(grpSearchParameters, SWT.NONE);
		btnSearch.addSelectionListener(this.controller);
		btnSearch.setBounds(377, 199, 90, 30);
		btnSearch.setText("Search");
		
		
		Group gpResults = new Group(searchcomposite, SWT.NONE);
		gpResults.setText("Results");
		gpResults.setBounds(10, 255, 477, 164);
		
		list = new List(gpResults, SWT.BORDER);
		list.setBounds(10, 24, 457, 130);
	

	}
}
