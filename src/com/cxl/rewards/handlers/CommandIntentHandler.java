package com.cxl.rewards.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CommandIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CommandIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Please say your command";
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Command", speechText)
                .build();
    }

}
