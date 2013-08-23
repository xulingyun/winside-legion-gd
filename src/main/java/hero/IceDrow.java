package hero;

public class IceDrow {

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	int first;
	int x = 500;
	int y = -100;
	Soldier s;
	int index;
	public static final int lastTime = 10;
	long startTime;
	int _x;
	int _y;
	int xx;
	int yy;

	public int getXx() {
		return xx;
	}

	public void setXx(int xx) {
		this.xx = xx;
	}

	public int getYy() {
		return yy;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}

	public int get_x() {
		return _x;
	}

	public void set_x(int _x) {
		this._x = _x;
	}

	public int get_y() {
		return _y;
	}

	public void set_y(int _y) {
		this._y = _y;
	}

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
		first = 0;
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
