package com.galaxy.model;

import javax.persistence.*;

@Entity
@Table(name = "DAY")
public class Day {

	@Id
	@Column(name = "DAY")
	private int day;

	@Column(name = "WEATHER")
	private String weather;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}
}
