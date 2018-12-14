package com.cxl.rewards.interceptor;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.ResponseInterceptor;
import com.amazon.ask.model.Response;

/**
 * 
 * @author rmoon
 *
 */
public class PersistenceSavingResponseInterceptor implements ResponseInterceptor 
{

    @Override
    public void process(HandlerInput input, Optional<Response> output) 
    {
        input.getAttributesManager().savePersistentAttributes();
    }

}
