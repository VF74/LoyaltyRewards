package com.cxl.rewards;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.cxl.rewards.handlers.CancelandStopIntentHandler;
import com.cxl.rewards.handlers.CartIntentHandler;
import com.cxl.rewards.handlers.FallbackIntentHandler;
import com.cxl.rewards.handlers.FeaturedItemsIntentHandler;
import com.cxl.rewards.handlers.HelpIntentHandler;
import com.cxl.rewards.handlers.LaunchRequestHandler;
import com.cxl.rewards.handlers.LoyaltyRewardsExceptionHandler;
import com.cxl.rewards.handlers.PinIntentHandler;
import com.cxl.rewards.handlers.PointBalanceIntentHandler;
import com.cxl.rewards.handlers.RedemptionHistoryIntentHandler;
import com.cxl.rewards.handlers.SessionEndedRequestHandler;

/**
 * Entry Point for the Skill
 * @author moonr
 *
 */
public class LoyaltyRewardsStreamHandler extends SkillStreamHandler 
{

    @SuppressWarnings("unchecked")
	private static Skill getSkill() 
    {
        return Skills.standard()
                .addRequestHandlers(
                        new PinIntentHandler(),
                		new PointBalanceIntentHandler(),
                        new RedemptionHistoryIntentHandler(),
                        new FeaturedItemsIntentHandler(),
                        new CartIntentHandler(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler())
                 .addExceptionHandler(new LoyaltyRewardsExceptionHandler())
                .withSkillId("amzn1.ask.skill.538186b5-caa9-413c-9391-95bf89bb833a")
                .build();
        
        //.addResponseInterceptors(new PersistenceSavingResponseInterceptor())
    }

    public LoyaltyRewardsStreamHandler() {
        super(getSkill());
    }

}
