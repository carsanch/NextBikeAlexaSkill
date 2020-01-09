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
package com.carlossamartin.alexa.nextbike.model.nextbike;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	
    @JsonProperty("uid")
    private String uid;

	@JsonProperty("lat")
    private Float lat;
	
    @JsonProperty("lng")
    private Float lng;
    
    @JsonProperty("alias")
    private String alias;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("num_places")
    private Integer numPlaces;
    
    @JsonProperty("set_point_bikes")
    private Integer setPointBikes;
    
    @JsonProperty("available_bikes")
    private Integer availableBikes;
    
    @JsonProperty("places")
    private List<Place> places;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumPlaces() {
		return numPlaces;
	}

	public void setNumPlaces(Integer numPlaces) {
		this.numPlaces = numPlaces;
	}

	public Integer getSetPointBikes() {
		return setPointBikes;
	}

	public void setSetPointBikes(Integer setPointBikes) {
		this.setPointBikes = setPointBikes;
	}

	public Integer getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(Integer availableBikes) {
		this.availableBikes = availableBikes;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}
    
}
