package com.cxl.rewards.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.cxl.rewards.db.DBUtil;
import com.cxl.rewards.model.AccountInfo;
import com.cxl.rewards.util.LoyaltyRewardsUtil;
import com.cxl.rewards.util.SpeechConstants;

/**
 * 
 * @author rmoon
 *
 */
public class PointBalanceIntentHandler implements RequestHandler 
{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PointBalanceIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
       String speechText = "Your current point balance is {0} points as of {1}, you have {2} points expiring by {3}..." 
                           + SpeechConstants.LASTEST_REDEMPTIONS;
       
       SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
   	   Date date = new Date();
   	   String currentDate = dateFormat.format(date); //2013/10/15 16:16:39
       
       try {
    	   AccountInfo acctInfo = DBUtil.getAccountInfo("");
    	   String currentPts = acctInfo.getAccountBalance();
    	   String ptsExpire = acctInfo.getPointsToExpire();
    	   String expireDate = acctInfo.getExpirationDate();
           String[] values = {currentPts, currentDate, ptsExpire, expireDate};
           
           speechText = LoyaltyRewardsUtil.buildMessage(speechText, values);
       } catch (Exception e) {
		e.printStackTrace();
       }
       
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LoyaltyRewards Point Balance", speechText)
                .withReprompt(speechText)
                .build();
    }

}
