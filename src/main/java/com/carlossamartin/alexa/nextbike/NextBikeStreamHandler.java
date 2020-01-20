package com.carlossamartin.alexa.nextbike;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.carlossamartin.alexa.nextbike.handlers.BikesByStationNameIntent;
import com.carlossamartin.alexa.nextbike.handlers.CancelandStopIntentHandler;
import com.carlossamartin.alexa.nextbike.handlers.HelpIntentHandler;
import com.carlossamartin.alexa.nextbike.handlers.LaunchRequestHandler;
import com.carlossamartin.alexa.nextbike.handlers.SessionEndedRequestHandler;

public class NextBikeStreamHandler extends SkillStreamHandler {

	static final Logger logger = LogManager.getLogger(NextBikeStreamHandler.class);

	@SuppressWarnings("unchecked")
	private static Skill getSkill() {
		return Skills.standard().addRequestHandlers(new CancelandStopIntentHandler(), new BikesByStationNameIntent(),
				new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler()).build();
	}

	public NextBikeStreamHandler() {
		super(getSkill());
		logger.info("Start NextBikeStreamHandler");
	}

}