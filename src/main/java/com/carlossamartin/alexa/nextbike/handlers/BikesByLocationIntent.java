package com.carlossamartin.alexa.nextbike.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.PermissionStatus;
import com.amazon.ask.model.Permissions;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.geolocation.Coordinate;
import com.amazon.ask.model.interfaces.geolocation.GeolocationState;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
import com.carlossamartin.alexa.nextbike.model.nextbike.Place;
import com.carlossamartin.alexa.nextbike.services.NextBikeLPAService;

import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BikesByLocationIntent implements RequestHandler {

	static final Logger logger = LogManager.getLogger(BikesByLocationIntent.class);
	private NextBikeLPAService service = new NextBikeLPAService();

	private static final String GEO_PERMISSION = "alexa::devices:all:geolocation:read";

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("BikesByLocationIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		logger.debug("Start BikesByLocationIntent");
		ResponseBuilder response = input.getResponseBuilder();

		if (deviceSupportsGeolocation(input)) {
			return handleAtCurrentLocation(input, response);
		} else {
			return handleAskForLocation(response);
		}
	}

	private Optional<Response> handleAskForLocation(ResponseBuilder response) {
		return say("Su dispositivo no soporta localización.", response);
	}

	private Optional<Response> handleAtCurrentLocation(HandlerInput input, ResponseBuilder response) {
		if (hasGeolocationPermission(input)) {
			return say(getCurrentLocation(input).map(this::searchBikes).orElse("No pudimos obtener tu localización."),
					response);
		} else {
			askForPermissionIfDenied(response);
			return say("No tenemos permiso para obtener tu localización.", response);
		}
	}

	private Optional<Response> say(String speechText, ResponseBuilder response) {
		response.withSpeech(speechText).withSimpleCard("Sitycleta", speechText).withReprompt(speechText);
		return response.build();
	}

	private boolean deviceSupportsGeolocation(HandlerInput input) {
		return input.getRequestEnvelope().getContext().getSystem().getDevice().getSupportedInterfaces()
				.getGeolocation() != null;
	}

	private boolean hasGeolocationPermission(HandlerInput input) {
		Permissions permissions = input.getRequestEnvelope().getSession().getUser().getPermissions();
		return permissions != null && permissions.getScopes() != null
				&& permissions.getScopes().get(GEO_PERMISSION).getStatus() == PermissionStatus.GRANTED;
	}

	private void askForPermissionIfDenied(ResponseBuilder response) {
		response.withAskForPermissionsConsentCard(Arrays.asList(GEO_PERMISSION));
	}

	private Optional<Coordinate> getCurrentLocation(HandlerInput input) {
		GeolocationState geo = input.getRequestEnvelope().getContext().getGeolocation();
		return Optional.ofNullable(geo.getCoordinate());
	}

	private String searchBikes(Coordinate location) {

		logger.info("Device coordinates: " + location.getLatitudeInDegrees() + " " + location.getLongitudeInDegrees());
		Place place = service.getPlaceByLocation(location.getLatitudeInDegrees(), location.getLongitudeInDegrees());

		String nameStationPlace = place.getName().trim();
		Integer bikes = place.getBikes();

		StringBuilder stb = new StringBuilder();
		stb.append("Hay disponibles ").append(bikes).append(" bicicletas en la estación ").append(nameStationPlace)
				.append(".");

		return stb.toString();
	}

}
