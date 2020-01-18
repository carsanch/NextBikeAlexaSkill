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

import static org.junit.Assert.*;

import org.junit.Test;

import com.carlossamartin.alexa.nextbike.model.nextbike.Place;
import com.carlossamartin.alexa.nextbike.services.NextBikeLPAService;

public class NextBikeLPAServiceTest {

	@Test
	public void testGetPlaceByName() {
		NextBikeLPAService service = new NextBikeLPAService();
		Place place = service.getPlaceByName("La feria");
		System.out.println(place.getName());
		assertTrue(place.getName().trim().equals("Plaza de la feria"));
	}
	
	@Test
	public void testGetPlaceByLocation() {
		NextBikeLPAService service = new NextBikeLPAService();
		Double originLat = 28.146316;
		Double originLong = -15.429563;
		Place place = service.getPlaceByLocation(originLat, originLong);
		System.out.println(place.getName());
		assertTrue(place.getName().trim().equals("Torre Woermann"));
	}
}
