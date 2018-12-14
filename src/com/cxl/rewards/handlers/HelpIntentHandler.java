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
public class HelpIntentHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
        String speechText = "The following commands are available in the latest version of this skill...you can say things like, "
        		+ "what is my point balance... or just simply balance.... you can ask alexa to get the status of your latest "
        		+ "redemption by saying redemption status..."
        		+ "you can also redeem for a gift card by saying redeem...when you are finished just say end to exit. "
        		+ "Thank you for using this latest additions to the connetions loyalty product lineup";
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards", speechText)
                .withReprompt(speechText)
                .build();
    }
}
