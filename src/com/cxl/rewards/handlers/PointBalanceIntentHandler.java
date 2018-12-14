package com.cxl.rewards.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class PointBalanceIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PointBalanceIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
       String speechText = "Your current point balance is 150 points as of December 17 2018";
       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards Point Balance", speechText)
                .withReprompt("What can I help you with next")
                .build();
    }

}
