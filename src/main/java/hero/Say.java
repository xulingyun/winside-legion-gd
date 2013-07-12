package hero;

import javax.microedition.lcdui.Image;

import test.Resource;

public class Say extends Point {

	private String SayString = "";
	private int off_x;
	private int off_y;
	public final static long lastTime = 2000;
	public final static Image img = Resource.loadImage(Resource.saybox);

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	private long startTime;

	public Say(int x, int y, int value) {
		super(x, y, value);
		setStartTime(System.currentTimeMillis());

	}

	public String getSayString() {
		return SayString;
	}

	public void setSayString(String sayString) {
		SayString = sayString;
	}

	public int getOff_x() {
		return off_x;
	}

	public void setOff_x(int off_x) {
		this.off_x = off_x;
	}

	public int getOff_y() {
		return off_y;
	}

	public void setOff_y(int off_y) {
		this.off_y = off_y;
	}

}
