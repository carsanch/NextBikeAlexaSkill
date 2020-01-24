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

import com.carlossamartin.alexa.nextbike.model.google.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeocodingResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("results")
    private List<Address> addresses;

    public GeocodingResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setPlaces(List<Address> addresses) {
        this.addresses = addresses;
    }
}
