/*
 * NextBike Alexa Skill
 *
 * Copyright � 2020 Carlos Sanchez Martin (carlos.samartin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carlossamartin.alexa.nextbike.handlers;

import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.PermissionStatus;
import com.amazon.ask.model.Permissions;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.geolocation.Coordinate;
import com.amazon.ask.model.interfaces.geolocation.GeolocationState;
import com.amazon.ask.model.services.deviceAddress.Address;
import com.amazon.ask.model.services.deviceAddress.DeviceAddressServiceClient;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
import com.carlossamartin.alexa.nextbike.model.google.Location;
import com.carlossamartin.alexa.nextbike.model.nextbike.Place;
import com.carlossamartin.alexa.nextbike.services.GeocodingService;
import com.carlossamartin.alexa.nextbike.services.NextBikeLPAService;
import com.carlossamartin.alexa.nextbike.tools.GeoTool;

public class BikesByLocationIntent implements RequestHandler {

	static final Logger logger = LogManager.getLogger(BikesByLocationIntent.class);
	private NextBikeLPAService nextBikeLPAService = new NextBikeLPAService();
	private GeocodingService geocodingService = new GeocodingService();

	private static final String GEO_PERMISSION = "alexa::devices:all:geolocation:read";
	private static final double LIMIT_DISTANCE = 5000;

	public static final String ALL_ADDRESS_PERMISSION = "read::alexa:device:all:address";

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("BikesByLocationIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		logger.debug("Start BikesByLocationIntent");
		logger.debug("Request Envelope: " + input.getRequestEnvelopeJson());

		ResponseBuilder response = input.getResponseBuilder();

		if (deviceSupportsGeolocation(input)) {
			return handleAtCurrentLocation(input, response);
		} else if (hasServiceAddressPermission(input)) {
			return handleAtAddress(input, response);
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
		response.withSpeech(speechText).withSimpleCard("Sitycleta", speechText);
		return response.build();
	}

	private boolean deviceSupportsGeolocation(HandlerInput input) {
		return input.getRequestEnvelope().getContext().getSystem().getDevice().getSupportedInterfaces()
				.getGeolocation() != null;
	}

	private boolean hasServiceAddressPermission(HandlerInput input) {

		Permissions permissions = input.getRequestEnvelope().getSession().getUser().getPermissions();
		return permissions != null;
	}

	private Optional<Response> handleAtAddress(HandlerInput input, ResponseBuilder response) {
		String deviceId = input.getRequestEnvelope().getContext().getSystem().getDevice().getDeviceId();
		DeviceAddressServiceClient deviceAddressServiceClient = input.getServiceClientFactory()
				.getDeviceAddressService();
		Address address = deviceAddressServiceClient.getFullAddress(deviceId);
		if (address == null) {
			return say(
					"Parece que no tienes una dirección establecida. Puede configurar su dirección desde la aplicación Amazon Alexa.",
					response);
		} else {
			String addressMessage = address.getAddressLine1() + " " + address.getStateOrRegion() + " "
					+ address.getPostalCode();
			return say(getCurrentLocation(addressMessage).map(this::searchBikes)
					.orElse("No pudimos obtener tu localización."), response);
		}
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

	private Optional<Location> getCurrentLocation(String address) {
		Location location = geocodingService.getLocationByAddress(address);
		return Optional.ofNullable(location);
	}

	private String searchBikes(Coordinate coordinate) {
		return searchBikes(coordinate.getLatitudeInDegrees(), coordinate.getLongitudeInDegrees());
	}

	private String searchBikes(Location location) {
		return searchBikes(location.getLat(), location.getLng());
	}

	private String searchBikes(Double latitude, Double longitude) {

		logger.info("Device coordinates: " + latitude + " " + longitude);
		Place place = nextBikeLPAService.getPlaceByLocation(latitude, longitude);

		String nameStationPlace = place.getName().trim();
		Integer bikes = place.getBikes();

		double distance = GeoTool.distanceFromToInMeters(latitude, longitude, place.getLat(), place.getLng());

		StringBuilder stb = new StringBuilder();
		if (distance > LIMIT_DISTANCE) {
			stb.append("La estación más cercana está a más de ").append((int)LIMIT_DISTANCE).append(" metros.");
		} else {
			stb.append("Hay disponibles ").append(bikes).append(" bicicletas en la estación ").append(nameStationPlace)
					.append(".");
		}

		return stb.toString();
	}

}
