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
package com.carlossamartin.alexa.nextbike.restclient;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.text.diff.EditScript;
import org.apache.commons.text.diff.StringsComparator;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.junit.Test;

public class NextBikeDataRestClientTest {

	@Test
	public void testGetDataLPA() {
		NextBikeRestClient client = new NextBikeRestClient();
		NextBikeDataResponse out = client.getNextBikeDataResponseByCity("408");
		assertTrue(out.getCountries().size() > 0);
	}

	@Test
	public void whenEditScript_thenCorrect() {
		StringsComparator cmp = new StringsComparator("ABCFGH", "BCDEFG");
		EditScript<Character> script = cmp.getScript();
		int mod = script.getModifications();

		assertEquals(4, mod);
	}

	@Test
	public void testCosine() {
		String textDelimiter = " ";
		//String documentA = "We have to choose some coffee. Which one is not important to me.";
		//String documentB = "We have to choose coffee by it's beans. Darker beans looks better to me.";
		String documentA = "Estación de San Telmo";
		String documentB = "San Telmo";
		CosineSimilarity documentsSimilarity = new CosineSimilarity();

		Map<CharSequence, Integer> vectorA = Arrays.stream(documentA.split(textDelimiter))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));
		Map<CharSequence, Integer> vectorB = Arrays.stream(documentB.split(textDelimiter))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

		Double docABCosSimilarity = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

		System.out.printf("%4.3fn", docABCosSimilarity);
	}

}
