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

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class NextBikeRestClient {

    private static final String NEXT_BIKE_API_URL = "https://api.nextbike.net/maps/nextbike-official.json";

    public NextBikeDataResponse getNextBikeDataResponseByCity(String cityId)
    {
        String url = NEXT_BIKE_API_URL;

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(url));
        NextBikeClient proxy = target.proxy(NextBikeClient.class);

        Response response = proxy.getNextBikeDataByCity(cityId);


        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        NextBikeDataResponse nextBikeDataResponse = response.readEntity(NextBikeDataResponse.class);
        return nextBikeDataResponse;
    }
}
