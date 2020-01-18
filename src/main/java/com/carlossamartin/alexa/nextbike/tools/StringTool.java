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
package com.carlossamartin.alexa.nextbike.tools;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.CosineSimilarity;

public class StringTool {

	final private static String textDelimiter = " ";
	final private static String[] PATH_LIST = { "Calle", "Camino", "Carretera", "Glorieta", "Pasaje", "Paseo", "Plaza",
			"Rambla", "Ronda", "Sector", "Travesía", "Urbanización", "Avenida", "Barrio", "Calleja", "Cuesta",
			"Carrera", "Edificio", "Parque", "Plazuela", "Plazoleta", "Jardines", "Via", "Polígono", "C.C.",
			"Centro comercial", "Aparcamiento" };

	public static Double cosineSimilarity(String a, String b) {
		CosineSimilarity documentsSimilarity = new CosineSimilarity();

		Map<CharSequence, Integer> vectorA = Arrays.stream(a.split(textDelimiter))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));
		Map<CharSequence, Integer> vectorB = Arrays.stream(b.split(textDelimiter))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

		Double docABCosSimilarity = documentsSimilarity.cosineSimilarity(vectorA, vectorB);
		return docABCosSimilarity;
	}

	public static String removeKindPath(String a) {
		String lower = a.toLowerCase();
		for (int i = 0; i < PATH_LIST.length; i++) {
			if (lower.contains(PATH_LIST[i].toLowerCase())) {
				lower = lower.toLowerCase().replaceAll(PATH_LIST[i].toLowerCase(), "");
			}
		}
		return lower;
	}
}
