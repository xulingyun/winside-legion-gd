package hero;

public class Point {

	int x;
	int y;
	int value;

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Point(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}
