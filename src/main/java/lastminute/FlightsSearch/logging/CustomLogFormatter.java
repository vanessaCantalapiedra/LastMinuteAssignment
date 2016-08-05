package lastminute.FlightsSearch.logging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Gives custom format to the log files
 * 
 */
public class CustomLogFormatter extends Formatter{

	public String format(LogRecord record) 
	{
		
		// Create a StringBuffer to contain the formatted record
		// start with the date.
		StringBuffer sb = new StringBuffer();
		
		String level = record.getLevel().getName();
		
		if (level.equalsIgnoreCase("SEVERE"))
		{
			sb.append("***");
		}
		
		// Get the date from the LogRecord and add it to the buffer				
		Date date = new Date(record.getMillis());		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		String dateFormatted = dateFormat.format(date);
		
		
		sb.append(dateFormatted);
		sb.append("# ");
		
		// Get the level name and add it to the buffer
		sb.append(record.getLevel().getName());
		sb.append(" ->");
		 
		// Get the formatted message (includes localization 
		// and substitution of paramters) and add it to the buffer
		sb.append(formatMessage(record));
		sb.append(System.getProperty("line.separator"));
        
		return sb.toString();
	}
}
