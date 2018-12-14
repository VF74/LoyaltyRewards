package com.cxl.rewards.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

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
       String speechText = "Your redemption history as of December 17 2018 is";
    
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards Redeption History", speechText)
                .withReprompt(speechText)
                .build();
    }

}
