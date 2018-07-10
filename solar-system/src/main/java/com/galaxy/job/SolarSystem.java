package com.galaxy.job;

import java.util.ArrayList;
import java.util.List;
import com.galaxy.model.Civilization;
import com.galaxy.model.Day;
import com.galaxy.model.Weather;
import com.galaxy.service.DayService;
import com.galaxy.utils.Point;
import com.galaxy.utils.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SolarSystem {
	private List<Civilization> civilizations = new ArrayList<>();
	private Weather weather;
	private double maxPerimeter = 0;
	private int maxRainyDay = -1;
	private int day = 1;
	private static final Logger logger = LoggerFactory.getLogger(SolarSystem.class);

	@Autowired
	DayService dayService;

	public SolarSystem() {
		Civilization vulcanos = new Civilization(-1, 500, 0,"vulcanos");
		Civilization ferengis = new Civilization(-3, 2000, 0, "ferengis");
		Civilization betasoides = new Civilization(5, 1000, 0, "betasoides");

		civilizations.add(vulcanos);
		civilizations.add(ferengis);
		civilizations.add(betasoides);
	}

	@PostConstruct
	public void runForTenCycles(){
		runForCycles(10);
		logger.info("Períodos de sequia: " + Weather.DROUGHT.getTimes());
		logger.info("Períodos de lluvia: " + Weather.RAINY.getTimes() + " con pico máximo el día: " + maxRainyDay);
		logger.info("Periodos de condiciones optimas: " + Weather.OPT.getTimes());
	}

	private void nextDay() {
		for(Civilization civilization : civilizations) {
			civilization.nextDay();
		}
	}

	private boolean cycleDone() {
		boolean result = true;
		for(Civilization civilization : civilizations) {
			result = result && civilization.getPosition() == 0;
		}
		return result;
	}

	//Runs for X amount of cycles
	//each day it checks weather and saves it
	//then moves to the next day
	public void runForCycles(int x) {
    	for(int i = 0; i<x; i++) {
        	checkWeather();
        	saveWeather();
        	nextDay();
        	day++;
	    	while(!cycleDone()) {
	        	checkWeather();
	        	saveWeather();
	        	nextDay();
	    		day++;
	    	}
    	}
	}


	private void checkWeather() {
		Weather weather2 = Weather.NORMAL;

		Triangle triangle = new Triangle(new Point(civilizations.get(0).getXPosition(), civilizations.get(0).getYPosition()),
				new Point(civilizations.get(1).getXPosition(), civilizations.get(1).getYPosition()),
				new Point(civilizations.get(2).getXPosition(), civilizations.get(2).getYPosition()));

		weather2 = checkForDrought();

		if (weather2 == Weather.NORMAL) {
			weather2 = checkForOTP(triangle);
		}
		if (weather2 == Weather.NORMAL) {
			weather2 = checkForRainy(triangle);
		}

		if(weather2 != weather) {
			weather2.addTimes();
		}
		weather = weather2;
	}

	//any angle % 90 reduces it to the first quadrant.
	//if in the first quadrant all the angles are equals means they are alligned with center.
	private Weather checkForDrought() {
		Weather weather = Weather.NORMAL;
		Civilization civ = civilizations.get(0);
		if((civ.getPosition() % 90) == (civilizations.get(1).getPosition() % 90)
				&& (civ.getPosition() % 90) == (civilizations.get(2).getPosition() % 90)) {
			weather = Weather.DROUGHT;
		}
		return weather;
	}


	//if area = 0, it means they are all aligned
	//when we use checkForSTP we know they are not aligned with sun
	//so they are all aligned but not with the sun, so it's OTP
	//OTP = Optimal Temperature and Pressure
	private Weather checkForOTP(Triangle triangle) {
		Weather weather = Weather.NORMAL;
		double areaOfTriangle = triangle.getArea();

		if(areaOfTriangle == 0) {
			weather = Weather.OPT;
		}
		return weather;
	}

	//if area is > 0 it means there is a triangle.
	//if sun is inside triangle it's rainy season.
	private Weather checkForRainy(Triangle triangle) {
		Weather weather = Weather.NORMAL;
		double areaOfTriangle = triangle.getArea();

		if(areaOfTriangle > 0) {
			Point sunPoint = new Point(0,0);
			if(triangle.isPointInside(sunPoint)){
				weather = Weather.RAINY;
				double rainyPerimeter = triangle.getPerimeter();
				if(rainyPerimeter > maxPerimeter){
					maxPerimeter = rainyPerimeter;
					maxRainyDay = day;
				}
			}
		}
		return weather;
	}

	private void saveWeather(){
		Day day = new Day();
		day.setDay(this.day);

		day.setWeather(this.weather.getName());

		dayService.add(day);
	}
}
