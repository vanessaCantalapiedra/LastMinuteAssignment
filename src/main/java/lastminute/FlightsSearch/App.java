package lastminute.FlightsSearch;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import lastminute.FlightsSearch.data.FlightDataUtils;
import lastminute.FlightsSearch.logging.CustomLogFormatter;

/**
 * Main entry point of the application
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	try 
		{
			//initialize log system
			Logger myLogger = Logger.getLogger(FlightDataUtils.LOGGER_NAME);
			FileHandler logFile = new FileHandler(FlightDataUtils.LOG_FILE);					
		    logFile.setFormatter( new CustomLogFormatter());
		    
		    myLogger.setUseParentHandlers(false);			    		
		    myLogger.addHandler(logFile);
							
		} 
		catch (Exception e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(" ERROR>>> The log file system could not be initialized." );
					     	
		}										

    	
    	try {
		
    		//create our model view controller
    		AppModel      model      = new AppModel();
            AppView       view       = new AppView();
            AppController controller = new AppController(model, view);
            view.setController (controller);
            
			view.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        
        
    }
}
