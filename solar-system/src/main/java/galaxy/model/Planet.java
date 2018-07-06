package galaxy.model;

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
		return 2D;
	}

	public double getYPosition() {
		return 2D;
	}
}
