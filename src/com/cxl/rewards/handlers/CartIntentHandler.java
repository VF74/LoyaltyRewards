package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.cxl.rewards.util.LoyaltyRewardsUtil;
import com.cxl.rewards.util.SpeechConstants;

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
       String speechText = "Our top offers are...";
       
       //String[] values = {"100", "25" };
       //speechText = LoyaltyRewardsUtil.buildMessage(speechText, values);
       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards Point Balance", speechText)
                .withReprompt(speechText)
                .build();
    }

}
