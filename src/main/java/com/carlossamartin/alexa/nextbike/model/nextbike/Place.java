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
public class Place {
	
    @JsonProperty("uid")
    private String uid;

	@JsonProperty("lat")
    private Float lat;
	
    @JsonProperty("lng")
    private Float lng;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("number")
    private String number;
    
    @JsonProperty("bikes")
    private Integer bikes;
    
    @JsonProperty("booked_bikes")
    private Integer bookedBikes;
    
    @JsonProperty("bike_racks")
    private Integer bikeRacks;
    
    @JsonProperty("free_racks")
    private Integer freeRacks;
    
    @JsonProperty("maintenance")
    private Boolean maintenance;
    
    @JsonProperty("bike_list")
    private List<Bike> bikeList;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getBikes() {
		return bikes;
	}

	public void setBikes(Integer bikes) {
		this.bikes = bikes;
	}

	public Integer getBookedBikes() {
		return bookedBikes;
	}

	public void setBookedBikes(Integer bookedBikes) {
		this.bookedBikes = bookedBikes;
	}

	public Integer getBikeRacks() {
		return bikeRacks;
	}

	public void setBikeRacks(Integer bikeRacks) {
		this.bikeRacks = bikeRacks;
	}

	public Integer getFreeRacks() {
		return freeRacks;
	}

	public void setFreeRacks(Integer freeRacks) {
		this.freeRacks = freeRacks;
	}

	public Boolean getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Boolean maintenance) {
		this.maintenance = maintenance;
	}

	public List<Bike> getBikeList() {
		return bikeList;
	}

	public void setBikeList(List<Bike> bikeList) {
		this.bikeList = bikeList;
	}
    
    
}
