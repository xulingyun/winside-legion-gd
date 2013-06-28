package hero;

import javax.microedition.lcdui.Image;

public class Animal implements HeroInterface {

	public String toString() {
		return "Animal [name=" + name + ", src=" + src + ", level=" + level
				+ ", x=" + x + ", y=" + y + ", offX=" + offX + ", offY=" + offY
				+ ", kind=" + kind + ", move=" + move + "]";
	}

	public String name;
	public Image src;
	public int level;
	public int x;
	public int y;
	public int offX = 0;
	public int offY = 0;
	int kind;
	boolean iceNoMove;

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getKind() {
		return kind;
	}

	public boolean move;

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public int getOffX() {
		return offX;
	}

	public void setOffX(int offX) {
		this.offX = offX;
	}

	public int getOffY() {
		return offY;
	}

	public void setOffY(int offY) {
		this.offY = offY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getSrc() {
		return src;
	}

	public void setSrc(Image src) {
		this.src = src;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public Animal() {
	}

	public Animal(String name, Image src, int level, int x, int y, int offX,
			int offY) {
		this.name = name;
		this.src = src;
		this.level = level;
		this.x = x;
		this.y = y;
		this.offX = offX;
		this.offY = offY;
		iceNoMove = false;
		move = false;
	}

	public boolean isIceNoMove() {
		return iceNoMove;
	}

	public void setIceNoMove(boolean iceNoMove) {
		this.iceNoMove = iceNoMove;
	}

}
