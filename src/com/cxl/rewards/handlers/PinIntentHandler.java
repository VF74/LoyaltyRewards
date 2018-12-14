package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * 
 * @author rmoon
 *
 */
public class PinIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PinIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
       String speechText = "Thank your Rob Moon...your now ready to use the new and fantastic loyalty rewards Alexa skill by connections..."
       		+ "please tell Alexa what you would like to do next... you can say things like what is my point balance or list my redemptions..."
       		+ "say end to quit";
       
       //var cityName = this.event.request.intent.slots.City.value;
       /*Map attributes = input.getAttributesManager().getRequestAttributes();
       String pin = (String)attributes.get("pin");
       speechText = speechText + "...your pin is... " + pin;*/
       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards", speechText)
                .withReprompt(speechText)
                .build();
    }

}
