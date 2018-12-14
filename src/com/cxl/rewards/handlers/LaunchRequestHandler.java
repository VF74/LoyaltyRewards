package com.cxl.rewards.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to My Company Rewards... please say your four digit pin you used to register your account";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Hello", speechText)
                .withReprompt(speechText)
                .build();
    }

}
