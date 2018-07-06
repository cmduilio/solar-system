package galaxy.model;

public class Civilization extends Planet{
	private String name;
	public Civilization(int speed, int radius, int position, String name) {
		super(speed, radius, position);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//Adding 360 makes all degrees positive
	//this also makes me fast-foreward any amount of days
	public void moveDays(int days) {
		if(days > 0) {
			int newPosition = (360 + this.getPosition() + days*this.getSpeed()) % 360;
			this.setPosition(newPosition);
		}
	}
	
	public void nextDay() {
		moveDays(1);
	}
}
