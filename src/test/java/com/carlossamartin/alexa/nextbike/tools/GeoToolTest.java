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
package com.carlossamartin.alexa.nextbike.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoToolTest {

	@Test
	public void testDistFrom() {
		double distance = GeoTool.distanceFromToInMeters(28.128562, -15.430456, 28.114576, -15.419346);
		System.out.println("distance: " + distance);
		assertTrue(distance > 1000);
		
		distance = GeoTool.distanceFromToInMeters(28.128562, -15.430456, 28.134756, -15.434571);
		System.out.println("distance: " + distance);
		assertTrue(distance < 1000);
	}

}
