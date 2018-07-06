package galaxy;

import java.util.ArrayList;
import java.util.List;

import galaxy.model.Civilization;
import galaxy.model.Weather;

public class SolarSystem {
	private static SolarSystem solarSystem = new SolarSystem();
	
	private static List<Civilization> civilizations = new ArrayList<>();
	private static Weather weather;
	
	
	private SolarSystem() {}
	
	public static SolarSystem getInstance() {
		return solarSystem;
	}
	
	protected static void init() {

    	Civilization vulcanos = new Civilization(1, 500, 0,"vulcanos");
    	Civilization ferengis = new Civilization(3, 2000, 0, "ferengis");
    	Civilization betasoides = new Civilization(-5, 1000, 0, "betasoides");
    	
    	civilizations.add(vulcanos);
    	civilizations.add(ferengis);
    	civilizations.add(betasoides);
	}
	
	protected static void nextDay() {
		for(Civilization civilization : civilizations) {
			civilization.nextDay();
		}
	}
	
	public static void showAllPositions() {
		for(Civilization civilization : civilizations) {
			System.out.println(civilization.getName() + ":" + civilization.getPosition());
		}
	}
	
	public static boolean cycleDone() {
		boolean result = true;
		for(Civilization civilization : civilizations) {
			result = result && civilization.getPosition() == 0;
		}
		return result;
	}
	
	public static void runForCycles(int x) {
    	int days = 0;
		for(int i = 0; i<x; i++) {
        	checkWeather();
//        	SaveWeather();
        	System.out.println("day: " + days +  " " + weather);
        	SolarSystem.nextDay();
        	days++;
	    	while(!SolarSystem.cycleDone()) {
	        	checkWeather();
//	        	SaveWeather();
	        	System.out.println("day: " + days +  " " + weather);
	        	SolarSystem.nextDay();
	    		days++;
	    	}

    	}
		
    	System.out.println(Weather.DROUGHT.getTimes());
    	System.out.println(Weather.NORMAL.getTimes());
    	System.out.println(Weather.CNPT.getTimes());
	}
	
	//any angle % 90 reduce it to first quadrant.
	public static void checkWeather() {
		Weather weather2 = Weather.NORMAL;
		weather2 = checkForDrought();
		if (weather2 == Weather.NORMAL) {
			weather2 = checkForCNPT();
		}		
		
		if (weather2 == Weather.NORMAL) {
			weather2 = checkForCNPT();
		}
		
		if(weather2 != weather) {
			weather2.addTimes();
		}
		weather = weather2;
	}
	
	public static Weather checkForDrought() {
		Weather weather = Weather.NORMAL;
		Civilization civ = civilizations.get(0);
		if((civ.getPosition() % 90) == (civilizations.get(1).getPosition() % 90)
				&& (civ.getPosition() % 90) == (civilizations.get(2).getPosition() % 90)) {
			weather = Weather.DROUGHT;
		}
		return weather;
	}

	//area of triangle is
	//1/2[x1(y2-y3)+x2(y3-y1)+x3(y1-y2)]
	//if area = 0, it means they are aligned
	public static Weather checkForCNPT() {
		Weather weather = Weather.NORMAL;
		double areaOfTriangle = areaOfPlanets();
		
		if(areaOfTriangle == 0) {
			weather = Weather.CNPT;
		}
		return weather;
	}
	
	public static double areaOfPlanets() {
		double x1 = civilizations.get(0).getXPosition();
		double x2 = civilizations.get(1).getXPosition();
		double x3 = civilizations.get(2).getXPosition();
		double y1 = civilizations.get(0).getYPosition();
		double y2 = civilizations.get(1).getYPosition();
		double y3 = civilizations.get(2).getYPosition();
		
		double areaOfTriangle = (x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)) / 2;
		
		return areaOfTriangle;
	}
	
}
