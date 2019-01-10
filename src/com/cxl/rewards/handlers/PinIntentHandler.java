package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazonaws.util.StringUtils;

/**
 * 
 * @author rmoon
 *
 */
public class PinIntentHandler implements RequestHandler 
{

	private static final String PIN_SLOT = "pin";
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PinIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
    	 String speechText = "";
    	 
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
       
        Slot pinSlot = slots.get(PIN_SLOT);
        String pinValue = pinSlot.getValue();
       
        if(StringUtils.isNullOrEmpty(pinValue)) {
        	speechText = "Please say your pin";
        } else {
        	if("1234".equalsIgnoreCase(pinValue)) {
                speechText = "Thank your Rob Moon...your now ready to use the new and fantastic loyalty rewards Alexa skill by connections..."
                  		+ "please tell Alexa what you would like to do next... you can say things like what is my point balance or list my redemptions... say exit to quit";
        		
        	} else {
                speechText = "The pin you said is not correct, please try again or say exit to quit";
        	}
        }

       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards", speechText)
                .withReprompt(speechText)
                .build();
    }

}
