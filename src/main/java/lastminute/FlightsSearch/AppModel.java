package lastminute.FlightsSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import lastminute.FlightsSearch.data.FlighInfoComparator;
import lastminute.FlightsSearch.data.FlightDataUtils;
import lastminute.FlightsSearch.data.FlightInfo;
import lastminute.FlightsSearch.data.SearchParams;

/**
 * Model class to manage the data
 */
public class AppModel {
	 
	//hashmap is one of the best option: we dont need sync, and we need unique key values
	//we are going to access to an specific city by name so we need a dictionary structure.
	//the problem, maybe is when it is get bigger
	// the arraylist is enough to hold the list with the flightinfo for a particular city, we don not 
	//need sync and the main operation is access, because the insertion and deletion is done before the search
	//in the data load and we are addint to the end . the sort function comes after. The default sort method id mergedsort
	//maybe we can do some testings doing quicksort
	HashMap <String, ArrayList<FlightInfo>> flightsData = new HashMap <String, ArrayList<FlightInfo>>();
	private static final Logger LOGGER = Logger.getLogger( FlightDataUtils.LOGGER_NAME );
    
	/**
	 * For each pair of origin and destiny cities , it generates a list with the available flights
	 * @param originCity city from
	 * @param destinicyty city to
	 * @return a list of FlightInfo objects with the information of the flights
	 * @see FlightInfo
	 */
	public ArrayList<FlightInfo> getAvailableFlights (String originCity, String destinyCity)
	{
		// get the list of flight for the origincity
		
		ArrayList<FlightInfo> flightsInfo = flightsData.get(originCity);
		ArrayList<FlightInfo> flightsResult = new ArrayList<FlightInfo>();
		
		if (flightsInfo != null)
		{
			//find all the flights 
			int idx = flightsBinarySearch(flightsInfo, destinyCity, 0 , flightsInfo.size()-1);
			
			
			
			//getElements on the left side
			boolean end = false;
			int tempIdx = idx;
			while (!end && tempIdx > 0)
			{
					tempIdx--;
					
					FlightInfo flightInfo = flightsInfo.get(tempIdx);
					if (flightInfo.getDestAirport().equals(destinyCity))
					{
						flightsResult.add(flightInfo);
					}
					else
					{
						end = true;
					}			
			}
			
			//add the element in the middle
			if (idx != -1)
			{
				flightsResult.add(flightsInfo.get(idx));
			}
			
			//do the same for the right side
			end = false;
			tempIdx = idx;
			while (!end && tempIdx < (flightsInfo.size()-1))
			{
					tempIdx++;
					
					FlightInfo flightInfo = flightsInfo.get(tempIdx);
					if (flightInfo.getDestAirport().equals(destinyCity))
					{
						flightsResult.add(flightInfo);
					}
					else
					{
						end = true;
					}			
			}
			
		
		}

		
		return flightsResult;
	}
	
	/**
	 * Fills with the data from a plain text source
	 * @param filePath path to the file with the information
	 * @return the success or failure of the data load
	 */
	public boolean fillData (String filePath)
	{
		boolean result = true;
		String originCity = new String();
		try {
			
			InputStream in = getClass().getClassLoader().getResourceAsStream(filePath); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
		   String line = "";
		  
			while((line = br.readLine()) != null) {  
			       String[] fields = line.split(",");
			        originCity = fields[0];
			        ArrayList<FlightInfo> infoCityList= flightsData.get(originCity);
			        if ( null == infoCityList)
			        {
			        	FlightInfo info = new FlightInfo(fields[1], fields[2], fields[3]);
			        	ArrayList<FlightInfo> infoList = new ArrayList<FlightInfo>();
			        	infoList.add(info);
			        	flightsData.put(originCity, infoList);
			        }
			        else
			        {
			            //insert in order
			        	FlightInfo info = new FlightInfo(fields[1], fields[2], fields[3]);
			        	infoCityList.add(info);
			        	
			        }	        
			      
			   }
			
			in.close();
			br.close();
			//order lists
			int cityNumbers = flightsData.size();
			//TODO maybe we cna change this for a quicksort
			FlighInfoComparator comparator = new FlighInfoComparator ();
			
			for (Map.Entry<String, ArrayList<FlightInfo>> entry : flightsData.entrySet()) {
			    
			    ArrayList<FlightInfo> tempList = entry.getValue();
			    Collections.sort(tempList, comparator);

			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.severe("Error getting flight data: " + e.getMessage());
			result =false;
		}
		
		
		return result;
	}
	
	public int getTotalCities()
	{
		return flightsData.size();
	}

	//binary search to find the first index of the destiny city
	private int flightsBinarySearch(ArrayList<FlightInfo> list, String destCity, int leftIdx, int rightIdx)
	{
		   
		   if (leftIdx > rightIdx)
               return -1;
		   
		   int middleIdx = (leftIdx + rightIdx)/2;
		   
		   
		   
		   if (list.get(middleIdx).getDestAirport().equals(destCity))
	            return middleIdx;
	      else if (list.get(middleIdx).getDestAirport().compareTo(destCity) > 0)
	            return flightsBinarySearch(list, destCity, leftIdx, middleIdx - 1);
	      else
	            return flightsBinarySearch(list, destCity, middleIdx + 1, rightIdx);      
		
		   
	}

}
