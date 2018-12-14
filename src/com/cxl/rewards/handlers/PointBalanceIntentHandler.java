package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.cxl.rewards.util.LoyaltyRewardsUtil;

/**
 * 
 * @author rmoon
 *
 */
public class PointBalanceIntentHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PointBalanceIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
       String speechText = "Your current point balance is {0} points as of December 17 2018, you have {1} expiring by January 1, 2019";
       
       String[] values = {
    		   "John Doe", "invoice #123", "2009-06-30"
    		 };
       
       LoyaltyRewardsUtil.buildMessage(speechText, values);
       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards Point Balance", speechText)
                .withReprompt("...What can I help you with next")
                .build();
    }

}
