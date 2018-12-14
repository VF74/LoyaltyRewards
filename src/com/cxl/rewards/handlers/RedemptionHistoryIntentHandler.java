package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.cxl.rewards.db.DBUtil;
import com.cxl.rewards.model.RedemptionItem;

/**
 * 
 * @author rmoon
 *
 */
public class RedemptionHistoryIntentHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RedemptionHistoryIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
    	StringBuilder sb = new StringBuilder();
    	String speechText = "Your redemption history as of December 17 2018 is...";
    	sb.append(speechText);
       
       try {
    	   List<RedemptionItem> redemptionList = DBUtil.getRedemptionHistory("");
    	   for(RedemptionItem item: redemptionList) {
    		   sb.append(item.getItemDescription() );
    		   sb.append("...redeemed on "+item.getRedemptionDate() );
    	   }
    	   sb.append("... would you like to hear the feature items next... just say list featured items");
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
