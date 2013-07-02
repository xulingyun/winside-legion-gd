package hero;

import javax.microedition.lcdui.Image;

import test.Resource;

public class IceDrow {

	int x = 500;
	int y = 0;
	Soldier s;
	int index;
	public static final Image img1 = Resource.loadImage(Resource.soldier121);
	public static final Image img2 = Resource.loadImage(Resource.soldier122);
	public static final int lastTime = 10;
	long startTime;

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public IceDrow(Soldier s) {
		this.s = s;
		index = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Soldier getS() {
		return s;
	}

	public void setS(Soldier s) {
		this.s = s;
	}

}
