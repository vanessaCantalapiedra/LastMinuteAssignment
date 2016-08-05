package lastminute.FlightsSearch.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Class to get the discounts info and pricing for an specific search
 */
public final class Pricing {
	
	static final int PASS_TYPE_ADULT = 0;
	static final int PASS_TYPE_CHILD = 1;
	static final int PASS_TYPE_INFANT = 2;
	

	static final float TIME_DISCOUNT_MORE_30 = 0.80f;
	static final float TIME_DISCOUNT_3_BETWEEN_15  =1.20f;
	static final float TIME_DISCOUNT_LESS_3 =1.50f;
	static final float NO_DISCOUNT = 1.0f;
	static final float CHILD_DISCOUNT = 0.67f;
	
	//printable strings
	static final String DISCOUNT_MORE_30="80%"; 
	static final String DISCOUNT_3_BETWEEN_15="120%"; 
	static final String DISCOUNT_LESS_3="150%"; 
	
     
	
	private int daysBetween ;
	private float  applicableTimeDiscount;
	private String currentDiscountString = "";
	private NumberFormat numberForm ;
    
	
	 public static final HashMap<String, Float> IATA_AIRLINE_DISC;
	    static
	    {
	    	IATA_AIRLINE_DISC = new HashMap<String, Float>();
	    	IATA_AIRLINE_DISC.put("IB", 10.0f);
	    	IATA_AIRLINE_DISC.put("BA", 15.0f);
	    	IATA_AIRLINE_DISC.put("LH", 7.0f);
	    	IATA_AIRLINE_DISC.put("FR", 20.0f);
	    	IATA_AIRLINE_DISC.put("VY", 10.0f);
	    	IATA_AIRLINE_DISC.put("TK", 5.0f);
	    	IATA_AIRLINE_DISC.put("U2", 19.90f);
	    }

	    Pricing (int daysBetween)
	    {
	    	this.daysBetween = daysBetween;
	    	numberForm = new DecimalFormat("#.##");
	    	calculateTimeDiscount();
	    }
	    
	   
	    public void updateTimeDiscount (int daysBetween)
	    {
	    	this.daysBetween = daysBetween;
	    	calculateTimeDiscount();
	    }
	
	/**
     * Select the specific discount to be applied
     * 
     */
	private void calculateTimeDiscount ()
	{
			 
		 if (daysBetween < 3 )
		 {
			this.applicableTimeDiscount =  TIME_DISCOUNT_LESS_3;
			currentDiscountString = this.DISCOUNT_LESS_3;
		 }
		 else if (daysBetween>=3 && daysBetween <= 15)
		 {
			 this.applicableTimeDiscount = TIME_DISCOUNT_3_BETWEEN_15;
			 currentDiscountString = this.DISCOUNT_3_BETWEEN_15;
		 }
		 else if (daysBetween>= 30)
		 {
			 this.applicableTimeDiscount = TIME_DISCOUNT_MORE_30;
			 currentDiscountString=this.DISCOUNT_MORE_30;
		 }
		 else
		 {
			 this.applicableTimeDiscount = NO_DISCOUNT; //not discount
			 currentDiscountString="";
		 }
	}
	
	/**
     * checks if there is any discount depending on the date  of the search in the current search.
     * If the selected date is close to the search date the discount is in fact a "penalization", but it is 
     * considered also a discount.
     */
	public boolean isDiscount ()
	{
		boolean result = true;
		if (this.applicableTimeDiscount == NO_DISCOUNT )
		{
			result = false;
		}
		
		return result;
	}
	
	/**
     * Prints the detailed pricing for an adult flight
     * @param basePrice baseprice of the flight
     * @return detailed pricing
     */
	public String getPriceAdultString (float basePrice)
	{
		String result;
		
		if (!currentDiscountString.isEmpty())
		{
			result = "(" + currentDiscountString + " of " + numberForm.format(basePrice) + ")";
		}
		else
		{
			result = numberForm.format(basePrice);
		}
		
		return result;
	}
	
	/**
     * Prints the detailed pricing for a child flight
     * @param basePrice baseprice of the flight
     * @return detailed pricing
     */
	public String getPriceChildString(float basePrice)
	{
		StringBuilder result = new StringBuilder("67% of ");
		

		if (!currentDiscountString.isEmpty())
		{
			result.append("(");
			result.append(currentDiscountString);
			result.append(" of ");
			result.append(numberForm.format(basePrice));
			result.append(")");
		
		}
		else
		{
			result.append(numberForm.format(basePrice)); 
		}
		
		return result.toString();
	}
	
	/**
     * Calculates the price for an adult
     * @param basePrice baseprice of the flight
     * @return the total price + discounts
     */
	public float getPriceAdult(float basePrice)
	{ 
		
		return basePrice*this.applicableTimeDiscount;
	}
	
	/**
     * Calculates the price for a child
     * @param basePrice baseprice of the flight
     * @return the total price + discounts
     */
	public float getPriceChild(float basePrice)
	{
		float basePriceAdult = getPriceAdult(basePrice);
		return  basePriceAdult*CHILD_DISCOUNT;
	}
	
	/**
     * Calculates the price for an infant
     * @param airline iata code of the airline to look for a infant discount
     * @return the total price + discounts
     */
	public float getPriceInfant(String airline)
	{
		Float fixedPrice = IATA_AIRLINE_DISC.get(airline);
		
		if (fixedPrice == null)
		{
			fixedPrice = 0.0f;

		}		
		return fixedPrice.floatValue();
	}
	
}
