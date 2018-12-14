package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.cxl.rewards.db.DBUtil;
import com.cxl.rewards.model.AccountInfo;
import com.cxl.rewards.model.FeaturedItem;
import com.cxl.rewards.util.LoyaltyRewardsUtil;

/**
 * 
 * @author rmoon
 *
 */
public class CartIntentHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CartIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
        String speechText = "thank you, Your redemption for {0} is complete... A confirmation email will be sent to you shortly... "
           		+ "Your new point balance is {1} points";
        
    	try {
			FeaturedItem item = DBUtil.redeemItem("", "");
			
	    	AccountInfo acctInfo = DBUtil.getAccountInfo("");
	    	String currentPts = acctInfo.getAccountBalance();
	        
	    	String[] values = {item.getItemDescription(), currentPts};
	        speechText = LoyaltyRewardsUtil.buildMessage(speechText, values);
	           
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	

       
       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards Point Balance", speechText)
                .withReprompt(speechText)
                .build();
    }

}
