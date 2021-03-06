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
package com.carlossamartin.alexa.nextbike.services;

import java.awt.geom.Point2D;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlossamartin.alexa.nextbike.model.nextbike.City;
import com.carlossamartin.alexa.nextbike.model.nextbike.Place;
import com.carlossamartin.alexa.nextbike.restclient.lpa.NextBikeLPARestClient;
import com.carlossamartin.alexa.nextbike.tools.StringTool;

public class NextBikeLPAService {
	
    private static final Logger logger = LogManager.getLogger(NextBikeLPAService.class);
	NextBikeLPARestClient client = new NextBikeLPARestClient();

	public Place getPlaceByName(String name) {
		logger.debug("getPlaceByName: " + name );
		Place place = null;
		Double factor = 0.0;
		try {
			City city = client.getCityData();
			for (Place placeiter : city.getPlaces()) {
				Double factoriter = StringTool.cosineSimilarity(StringTool.removeKindPath(name),
						StringTool.removeKindPath(placeiter.getName().trim()));
				if (place == null || factoriter > factor) {
					factor = factoriter;
					place = placeiter;
				}
			}
		} catch (Exception e) {
	        logger.error(e);
		}
        logger.info("Factor: " + factor);
        logger.info("Place: " + place.getName());
		return place;
	}

	public Place getPlaceByLocation(Double originLat, Double originLng) {
		Place place = null;
		Double distance = Double.MAX_VALUE;

		try {
			City city = client.getCityData();
			for (Place placeiter : city.getPlaces()) {
				Double distanceiter = getDistance(originLng, originLat, placeiter.getLng(), placeiter.getLat());
				if (place == null || distanceiter < distance) {
					distance = distanceiter;
					place = placeiter;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		logger.info("Distance from origin: " + distance);
        logger.info("Place: " + place.getName());
		return place;
	}

	private Double getDistance(Double x1, Double y1, Double x2, Double y2) {
		return Point2D.distance(x1, y1, x2, y2);
	}

}
