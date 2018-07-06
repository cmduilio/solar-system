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
	}
	
	//any angle % 90 reduce it to first quadrant.
	public static void checkWeather() {
		Weather weather2 = Weather.NORMAL;
		weather2 = checkForDrought();
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
	
	public static Weather checkForRainy() {
		Weather weather = Weather.NORMAL;
		return weather;
	}
	
}
