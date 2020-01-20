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

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NextBikeRestClient {

	private static final Logger logger = LogManager.getLogger(NextBikeRestClient.class);
	private static final String NEXT_BIKE_API_URL = "https://api.nextbike.net/maps/nextbike-official.json";

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public NextBikeDataResponse getNextBikeDataResponseByCity(String cityId) {

		NextBikeDataResponse nextBikeDataResponse = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(NEXT_BIKE_API_URL);
			builder.setParameter("city", cityId);

			httpclient = HttpClients.createDefault();

			HttpGet httpGet = new HttpGet(builder.build());
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");

			logger.debug("Resquest to: " + httpGet.getURI());
			response = httpclient.execute(httpGet);

			logger.debug("response: " + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			nextBikeDataResponse = objectMapper.readValue(response.getEntity().getContent(),
					NextBikeDataResponse.class);

		} catch (URISyntaxException e) {
			logger.error(e);
		} catch (ClientProtocolException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return nextBikeDataResponse;
	}
}
