package com.carlossamartin.alexa.nextbike.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.carlossamartin.alexa.nextbike.model.nextbike.Place;
import com.carlossamartin.alexa.nextbike.services.NextBikeLPAService;

import java.util.Optional;

public class BikesByStationNameIntent implements IntentRequestHandler {

	private NextBikeLPAService service = new NextBikeLPAService();

	@Override
	public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
		return input.matches(Predicates.intentName("BikesByStationNameIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {

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
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard(intent.getName(), speechText).build();
	}

}
