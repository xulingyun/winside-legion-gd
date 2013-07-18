package test;

import hero.Hero;
import hero.Soldier;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import cn.ohyeah.stb.util.RandomValue;
import skill.SkillEffectSoldier5;
import array.DataHandle;

public class CreatArray {

	Thread th1;
	int state;
	Image[] img = new Image[9];
	Vector v1 = new Vector();
	Vector v2 = new Vector();
	Vector v3 = new Vector();
	Vector v4 = new Vector();

	Vector v5 = new Vector();
	Vector v6 = new Vector();
	Vector v7 = new Vector();
	Vector v8 = new Vector();
	static int[] selcetSoldier = { 0, 1, 2, 3 };

	int row = 7;
	int col = 4;
	Soldier[][][] soldierKindArray = new Soldier[4][9][15];
	Soldier[][] idPic = new Soldier[7][4];
	boolean canMove;
	public static int sum;
	Soldier[][][] ss = null;
	int ssIndex;
	Hero hero;

	public Soldier[][][] getSs() {
		return ss;
	}

	public CreatArray(Image[] img, Hero hero) {
		for (int i = 0; i < 9; i++) {
			this.img[i] = img[i];
		}
		this.hero = hero;
		ssIndex = 0;
		ss = new Soldier[5][][];
		ss[0] = null;
		ss[1] = null;
		ss[2] = null;
		ss[3] = null;
		ss[4] = null;
	}

	public boolean sss() {
		sum = 0;
		vectorArray();
		des(v1, "v1");
		des(v2, "v2");
		des(v3, "v3");
		des(v4, "v4");
		if (v1.isEmpty() && v2.isEmpty() && v3.isEmpty() && v4.isEmpty()) {
			canMove = false;
			GameEngine.state = true;
			return canMove;
		} else {
			GameEngine.state = false;
			if ((v1.size() >= 5 || v2.size() >= 5 || v3.size() >= 5 || v4
					.size() >= 5) && (!GameEngine.isNowSoldierSay)) {
				GameEngine.isNowSoldierSay = true;
				GameEngine.randomSoldierSayLocaltion = RandomValue.getRandInt(
						0, 5);
				GameEngine.randomSoldierSayIndex = 0;
			}
			canMove = true;
		}
		DataHandle dh = new DataHandle(v1, v2, v3, v4);
		hero.setMagic(hero.getMagic(), v1.size());
		hero.setMagic(hero.getMagic(), v2.size());
		hero.setMagic(hero.getMagic(), v3.size());
		hero.setMagic(hero.getMagic(), v4.size());
		soldierKindArray = dh.getSoldierKindArray();
		SkillEffectSoldier5 sef = new SkillEffectSoldier5(soldierKindArray,
				this);
		ss[ssIndex] = sef.activity();
		ssIndex++;
		if (ssIndex == 5) {
			ssIndex = 0;
		}
		moveArray();
		clear();
		return canMove;
	}

	public void des(Vector v, String ss) {
		String s = ss + "***";
		for (int i = 0; i < v.size(); i++) {
			s += "(" + ((Soldier) v.elementAt(i)).getX()
					+ ((Soldier) v.elementAt(i)).getY() + ")";
		}
		System.out.println(s);
	}

	public void setIdPic(Soldier[][] idPic) {
		this.idPic = idPic;
	}

	public Soldier[][] randomArray() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				int imgIndex = RandomValue.getRandInt(1, 5);
				idPic[i][j] = new Soldier(
						"Soldier",
						img[imgIndex - 1],
						Resource.heroAndSoldierLevel[selcetSoldier[imgIndex - 1]],
						i, j, imgIndex, 0, 0, 0, selcetSoldier[imgIndex - 1], 0);
			}
		}
		pppp();
		return idPic;
	}

	public Soldier[][] getIdPic() {
		return idPic;
	}

	public void vectorArray() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (idPic[i][j] != null) {
					if (idPic[i][j].getKind() == 1) {
						addvector(i, j, v5);
					} else if (idPic[i][j].getKind() == 2) {
						addvector(i, j, v6);
					} else if (idPic[i][j].getKind() == 3) {
						addvector(i, j, v7);
					} else if (idPic[i][j].getKind() == 4) {
						addvector(i, j, v8);
					}
				}
			}
		}
		Soldier s = new Soldier();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (idPic[i][j] != null) {
					if (v5.contains(idPic[i][j])) {
						s = idPic[i][j];
						v1.addElement(s);
						idPic[i][j] = null;
					} else if (v6.contains(idPic[i][j])) {
						s = idPic[i][j];
						v2.addElement(s);
						idPic[i][j] = null;
					} else if (v7.contains(idPic[i][j])) {
						s = idPic[i][j];
						v3.addElement(s);
						idPic[i][j] = null;
					} else if (v8.contains(idPic[i][j])) {
						s = idPic[i][j];
						v4.addElement(s);
						idPic[i][j] = null;
					}
				}
			}
		}
	}

	private void addvector(int i, int j, Vector v) {
		int k = j + 1;
		if (k < col) {
			for (; k < col; k++) {
				if (idPic[i][j] != null && idPic[i][k] != null) {
					if (idPic[i][j].getKind() != idPic[i][k].getKind()) {
						break;
					}
				} else {
					break;
				}
			}
			if (k - j >= 3) {
				for (int m = j; m < k; m++) {
					if (!v.contains(idPic[i][m])) {
						idPic[i][m].setState(1);
						v.addElement(idPic[i][m]);
					}
				}
			}
		}
		k = i + 1;
		if (k < row) {
			for (; k < row; k++) {
				if (idPic[i][j] != null && idPic[k][j] != null) {
					if (idPic[i][j].getKind() != idPic[k][j].getKind()) {
						break;
					}
				} else {
					break;
				}
			}
			if (k - i >= 3) {
				for (int m = i; m < k; m++) {
					if (!v.contains(idPic[m][j])) {
						idPic[m][j].setState(1);
						v.addElement(idPic[m][j]);
					}
				}
			}
		}
	}

	public void setElementIsEmpty(Soldier soldier) {
		this.idPic[soldier.getX()][soldier.getY()] = null;
	}

	public void moveArray() {
		for (int i = 0; i < 7; i++) {
			for (int j = 3; j > 0; j--) {
				if (idPic[i][j] == null) {
					for (int k = j - 1; k >= 0;) {
						if (idPic[i][k] != null) {
							if (idPic[i][k].isIceNoMove()) {
								break;
							}
							idPic[i][j] = idPic[i][k];
							idPic[i][j].setY(j);
							idPic[i][j].setOffX((k - j) * 60);
							idPic[i][k] = null;
							break;
						} else {
							k--;
						}
					}
				}
			}
		}
		addArrayElement();
	}

	public void addArrayElement() {
		for (int i = 0; i < 7; i++) {
			int h = 1;
			id: for (int j = 3; j >= 0; j--) {
				if (idPic[i][j] == null) {
					for (int g = j - 1; g >= 0; g--) {
						if (idPic[i][g] != null && idPic[i][g].isIceNoMove()) {
							break id;
						}
					}
					int imgIndex = RandomValue.getRandInt(1, 5);
					idPic[i][j] = new Soldier(
							"Soldier",
							img[imgIndex - 1],
							Resource.heroAndSoldierLevel[selcetSoldier[imgIndex - 1]],
							i, j, imgIndex, 0, 0, 0,
							selcetSoldier[imgIndex - 1], 0);
					idPic[i][j].setOffX(-(j + h) * 60);
					h++;
				}
			}
			CreatArray.sum = 0;
		}
		pppp();
		GameEngine.add = 0;
	}

	public void clear() {
		v1.removeAllElements();
		v2.removeAllElements();
		v3.removeAllElements();
		v4.removeAllElements();
		v5.removeAllElements();
		v6.removeAllElements();
		v7.removeAllElements();
		v8.removeAllElements();
		soldierKindArray = null;
	}

	public void pppp() {
		System.out
				.println("*****************************************************");
		String s = "";
		for (int i = 0; i < 7; i++) {
			s = "[";
			for (int k = 0; k < 4; k++) {
				if (idPic[i][k] == null)
					s += "-1,";
				else
					s += idPic[i][k].getKind() + ",";
			}
			s += "]";
			System.out.println(s);
		}
	}
}
