package com.cxl.rewards.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author rmoon
 *
 */
public class LoyaltyRewardsUtil 
{
	//
	private LoyaltyRewardsUtil() {}
	
	/**
	 * Build out the return String.
	 * @param msg
	 * @param values
	 * @return String
	 */
	public static String buildMessage(String msg, String[] values)
	{
		String newMessage = MessageFormat.format(msg, values);	
		return newMessage;
	}
	
	public static String getTodaysDate() 
	{
	       SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	   	   Date date = new Date();
	   	   String currentDate = dateFormat.format(date);
	   	   return currentDate;
	}

}
