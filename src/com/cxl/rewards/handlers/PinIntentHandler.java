package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

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
       
      /* Request request = input.getRequestEnvelope().getRequest();
       IntentRequest intentRequest = (IntentRequest) request;
       Intent intent = intentRequest.getIntent();
       Map<String, Slot> slots = intent.getSlots();
       
       Slot pinSlot = slots.get(PIN_SLOT);
       String pinValue = pinSlot.getValue();
*/
       String speechText = "Thank your Rob Moon...your now ready to use the new and fantastic loyalty rewards Alexa skill by connections..."
          		+ "please tell Alexa what you would like to do next... you can say things like what is my point balance or list my redemptions... say exit to quit";

       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards", speechText)
                .withReprompt(speechText)
                .build();
    }

}
