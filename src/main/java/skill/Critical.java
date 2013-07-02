package skill;

public class Critical {

	private int x;
	private int y;
	private int num;
	private long time;
	public static long showtime = 1000;
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

	public Critical(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.time = System.currentTimeMillis();
	}

}
