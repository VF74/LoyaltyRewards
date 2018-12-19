package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
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
	private static final String ITEM_SLOT = "item";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CartIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        
        Slot itemSlot = slots.get(ITEM_SLOT);
        String itemValue = itemSlot.getValue();    	
    	
        String speechText = "thank you, You asked to redeem for item number {0} ...Your redemption for {1} is now complete... A confirmation email will be sent to you shortly... "
           		+ "Your new point balance is {2} points ... what would you like to do next.";
        
    	try {
        	int itemIdSelected = Integer.parseInt(itemValue);
        	FeaturedItem itemSelected = null;
 		
     	   List<FeaturedItem> featuredItem = DBUtil.getFeaturedItems("");
     	   for(FeaturedItem item: featuredItem) {
     		   int id = item.getId();
     		   if(id == itemIdSelected) {
     			  itemSelected = item;
     			  break;
     		   }
     	   }    		
    		
			FeaturedItem item = DBUtil.redeemItem("", itemSelected);
			
	    	AccountInfo acctInfo = DBUtil.getAccountInfo("");
	    	String currentPts = acctInfo.getAccountBalance();
	        
	    	String[] values = {itemValue, itemSelected.getItemDescription(), currentPts};
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
