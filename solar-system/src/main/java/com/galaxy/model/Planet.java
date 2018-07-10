package com.galaxy.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

	//Degrees always 0 <= position < 360
	public void setPosition(int position) {
		this.position = position;
	}
	
	public double getXPosition() {
		BigDecimal cos = new BigDecimal(Math.cos(Math.toRadians(position)));
		cos = cos.setScale(2, RoundingMode.HALF_UP);
	    return radius * cos.doubleValue();
	}

	public double getYPosition() {
		BigDecimal sin = new BigDecimal(Math.sin(Math.toRadians(position)));
		sin = sin.setScale(2, RoundingMode.HALF_UP);
	    return radius * sin.doubleValue();
	}
}
