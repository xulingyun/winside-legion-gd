package map;

import hero.Monster;

import javax.microedition.lcdui.Image;

public class Slickball extends Bullet {

	Monster m;
	int end_x;
	int end_y;

	public Slickball(int start_x, int start_y, Image src, int end_x, int end_y,
			Monster m) {
		super(start_x, start_y, src, null);
		this.end_x = end_x;
		this.end_y = end_y;
		this.m = m;
	}

	public Monster getM() {
		return m;
	}

	public void setM(Monster m) {
		this.m = m;
	}

	public int getEnd_x() {
		return end_x;
	}

	public void setEnd_x(int end_x) {
		this.end_x = end_x;
	}

	public int getEnd_y() {
		return end_y;
	}

	public void setEnd_y(int end_y) {
		this.end_y = end_y;
	}
}
