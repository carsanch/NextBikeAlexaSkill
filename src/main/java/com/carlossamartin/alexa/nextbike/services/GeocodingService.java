/*
 * NextBike Alexa Skill
 *
 * Copyright © 2020 Carlos Sanchez Martin (carlos.samartin@gmail.com)
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlossamartin.alexa.nextbike.model.google.Location;
import com.carlossamartin.alexa.nextbike.restclient.GeocodingRestClient;

public class GeocodingService {
	private static final Logger logger = LogManager.getLogger(GeocodingService.class);
	private GeocodingRestClient geoClient = new GeocodingRestClient();

	public Location getLocationByAddress(String address) {
		//TODO Make check logic.
		return geoClient.getLocationByAddress(address);
	}

}
