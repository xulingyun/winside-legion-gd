package skill;

import hero.Monster;

public class Critical {

	private int x;
	private int y;
	private int num;
	private long time;
	public static long showtime = 1000;
	private int count = 0;
	private int index;
	private Monster m;

	public Monster getM() {
		return m;
	}

	public void setM(Monster m) {
		this.m = m;
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

	public static long getShowtime() {
		return showtime;
	}

	public static void setShowtime(long showtime) {
		Critical.showtime = showtime;
	}

	private int movePixel = 0;

	public int getMovePixel() {
		return movePixel;
	}

	public void setMovePixel(int movePixel) {
		this.movePixel = movePixel;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Critical(int x, int y, int num, Monster m) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.m = m;
		this.time = System.currentTimeMillis();
		index = 0;
	}

}
