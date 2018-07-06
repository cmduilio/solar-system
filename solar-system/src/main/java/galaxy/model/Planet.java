package galaxy.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Planet {
	
	//degree's per day
	private int speed;
	private int radius;
	
	//in degrees
	private int position;
	
	public Planet(int speed, int radius, int position) {
		this.speed = speed;
		this.radius = radius;
		this.position = position;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public double getXPosition() {
		BigDecimal bd = new BigDecimal(Math.cos(position));
	    bd = bd.setScale(3, RoundingMode.HALF_UP);
	    return radius * bd.doubleValue();
	}

	public double getYPosition() {
		BigDecimal bd = new BigDecimal(Math.sin(position));
	    bd = bd.setScale(3, RoundingMode.HALF_UP);
	    return radius * bd.doubleValue();
	}
	
	public static boolean areAllAligned(List<Planet> planets) {
		boolean result = false;
		if(planets != null && !planets.isEmpty()) {
			Planet planet = planets.get(0);
			for(Planet plan : planets) {
				
			}
		}
		return result;
	}
	
}
