package com.carlossamartin.alexa.nextbike.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.carlossamartin.alexa.nextbike.NextBikeStreamHandler;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LaunchRequestHandler implements RequestHandler {
	
	static final Logger logger = LogManager.getLogger(LaunchRequest.class);

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
		logger.debug("Start LaunchRequest");
		logger.debug("Request Envelope: " + input.getRequestEnvelopeJson());
		
        String speechText = "Bienvenido a la skill no oficial de Siticleta. ¿Qué quieres hacer?";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Sitycleta", speechText)
                .withReprompt(speechText)
                .build();
    }

}
