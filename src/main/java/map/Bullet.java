package map;

import hero.Monster;

import javax.microedition.lcdui.Image;

public class Bullet {

	int x;
	int y;
	Image src;
	Monster m;

	public Monster getM() {
		return m;
	}

	public void setM(Monster m) {
		this.m = m;
	}

	public Image getSrc() {
		return src;
	}

	public void setSrc(Image src) {
		this.src = src;
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

	public Bullet() {
	}

	public Bullet(int x, int y, Image src, Monster m) {
		this.x = x;
		this.y = y;
		this.src = src;
		this.m = m;
	}

}
