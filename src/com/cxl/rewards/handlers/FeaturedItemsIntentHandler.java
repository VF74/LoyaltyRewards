package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.cxl.rewards.db.DBUtil;
import com.cxl.rewards.model.FeaturedItem;
import com.cxl.rewards.util.LoyaltyRewardsUtil;

/**
 * 
 * @author rmoon
 *
 */
public class FeaturedItemsIntentHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("FeaturedItemsIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
    	StringBuilder sb = new StringBuilder();
    	String currentDate = LoyaltyRewardsUtil.getTodaysDate(); 
    	String speechText = "Your featured items as of " + currentDate + " are... ";
    	sb.append(speechText);
       
       try {
    	   int count = 1;
    	   List<FeaturedItem> featuredItem = DBUtil.getFeaturedItems("");
    	   for(FeaturedItem item: featuredItem) {
    		   sb.append(" item " + count++ + " ..." + item.getItemDescription() + "... point cost " + item.getItemPointValue() + " ..." );
    	   }
    	   sb.append("... would you like to redeem for an item... just say which item number");
		} catch (Exception e) {
			e.printStackTrace();
		}
    
       return input.getResponseBuilder()
                .withSpeech(sb.toString())
                .withSimpleCard("LoyaltyRewards Redeption History", speechText)
                .withReprompt("")
                .build();
    }

}
