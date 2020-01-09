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
package com.carlossamartin.alexa.nextbike.restclient.lpa;

import com.carlossamartin.alexa.nextbike.model.nextbike.City;
import com.carlossamartin.alexa.nextbike.restclient.NextBikeDataResponse;
import com.carlossamartin.alexa.nextbike.restclient.NextBikeRestClient;

public class NextBikeLPARestClient {

	private static final String LPA_UID = "408";
	private static final String LPA_ALIAS = "laspalmas";

	public City getData() throws Exception
	{
        NextBikeRestClient client = new NextBikeRestClient();
        NextBikeDataResponse response = client.getNextBikeDataResponseByCity(LPA_UID);
        
        if(response.getCountries() == null || response.getCountries().size() < 1)
        {
        	throw new Exception();
        }
        
        if(response.getCountries().get(0).getCities() == null || response.getCountries().get(0).getCities().size() < 1 )
        {
        	throw new Exception();
        }
        
        City city = response.getCountries().get(0).getCities().get(0);
        if(!LPA_ALIAS.equals(city.getAlias()))
        {
        	throw new Exception();
        }   
        
		return city;
	}
}
