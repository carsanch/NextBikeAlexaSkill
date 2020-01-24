package com.carlossamartin.alexa.nextbike.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
import com.carlossamartin.alexa.nextbike.model.nextbike.Place;
import com.carlossamartin.alexa.nextbike.services.NextBikeLPAService;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BikesByStationNameIntent implements IntentRequestHandler {

	static final Logger logger = LogManager.getLogger(BikesByStationNameIntent.class);
	private NextBikeLPAService service = new NextBikeLPAService();

	@Override
	public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
		return input.matches(Predicates.intentName("BikesByStationNameIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		logger.debug("Start BikesByStationNameIntent");
		logger.debug("Request Envelope: " + input.getRequestEnvelopeJson());
		
		ResponseBuilder response = input.getResponseBuilder();
		
		Intent intent = intentRequest.getIntent();
		Slot stationSlot = intent.getSlots().get("station");
		String nameStation = stationSlot.getValue();
		
		Place place = service.getPlaceByName(nameStation);
		String nameStationPlace = place.getName().trim();
		Integer bikes = place.getBikes();

		StringBuilder stb = new StringBuilder();
		stb.append("Hay disponibles ").append(bikes).append(" bicicletas en la estación ").append(nameStationPlace)
				.append(".");

		String speechText = stb.toString();
		return say(speechText, response);
	}
	
	private Optional<Response> say(String speechText, ResponseBuilder response) {
		response.withSpeech(speechText).withSimpleCard("Sitycleta", speechText).withReprompt(speechText);
		return response.build();
	}

}
