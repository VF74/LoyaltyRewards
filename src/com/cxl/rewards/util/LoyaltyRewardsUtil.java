package com.cxl.rewards.util;

import java.text.MessageFormat;

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

}
