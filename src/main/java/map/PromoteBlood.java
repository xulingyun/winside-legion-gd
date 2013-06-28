package map;

import cn.ohyeah.stb.game.SGraphics;

public class PromoteBlood {
	int x;
	int y;
	int firstIndex;
	int sumIndex = 10;
	SGraphics g;

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

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getSumIndex() {
		return sumIndex;
	}

	public PromoteBlood(int x, int y, int firstIndex,SGraphics g) {
		this.x = x;
		this.y = y;
		this.firstIndex = firstIndex;
		this.g = g;
	}
	
	public void drawAnim(){
//		g.
		
	}

}
