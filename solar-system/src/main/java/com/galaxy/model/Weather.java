package com.galaxy.model;

//enum with all types of weather and total of periods
public enum Weather {
    NORMAL("Normal", 0), 
    RAINY("Lluvia", 0), 
    DROUGHT("Sequia", 0), 
    OPT("Presion y Temperatura óptimos", 0);
    
    private String name;
	private int times;
    
	private Weather(String name, int times) {
		this.name = name;
		this.times = times;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTimes() {
		return times;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}
	
	public void addTimes() {
		this.times++;
	}
}
