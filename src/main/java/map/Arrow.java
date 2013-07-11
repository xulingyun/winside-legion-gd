package map;

import hero.Monster;

import javax.microedition.lcdui.Image;

public class Arrow extends Bullet {
	Monster m;
	int end_x;
	int end_y;
	int level;
	int count;
	int index = 0;
	int _x_;
	int _y_;
	boolean isFirst = true;;


	public Arrow(int start_x, int start_y, Image src, int end_x, int end_y,
			Monster m, int level, int count) {
		super(start_x, start_y, src, null);
		this.end_x = end_x;
		this.level = level;
		this.end_y = end_y;
		this.m = m;
		this.count = count;
	}
	
	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public int get_x_() {
		return _x_;
	}

	public void set_x_(int _x_) {
		this._x_ = _x_;
	}

	public int get_y_() {
		return _y_;
	}

	public void set_y_(int _y_) {
		this._y_ = _y_;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
