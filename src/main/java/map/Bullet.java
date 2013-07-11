package map;

import hero.Monster;

import javax.microedition.lcdui.Image;

public class Bullet {

	int x;
	int y;
	Image src;
	Monster m;
	int flag =0;
	int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

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
