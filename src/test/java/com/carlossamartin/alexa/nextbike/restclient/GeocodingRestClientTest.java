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
package com.carlossamartin.alexa.nextbike.restclient;

import static org.junit.Assert.*;

import org.junit.Test;

import com.carlossamartin.alexa.nextbike.model.google.Location;

public class GeocodingRestClientTest {

	@Test
	public void testGetLocationByAddress() {
		GeocodingRestClient client = new GeocodingRestClient();
		Location out =client.getLocationByAddress("calle triana 1, Las Palmas de Gran Canaria");
		
		System.out.println("Lat: "+ out.getLat() + " Long: " + out.getLng());
		assertTrue(out != null);
		assertTrue(out.getLat().equals(28.102966F));
		assertTrue(out.getLng().equals(-15.41441F));
	}

}
