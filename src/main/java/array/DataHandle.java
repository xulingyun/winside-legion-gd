package array;

import java.util.Enumeration;
import java.util.Vector;

import hero.Soldier;

public class DataHandle {
	Soldier[][][] soldierKindArray = new Soldier[4][9][15];
	int[][] location = new int[4][9];

	int s0length;
	int l = 0;
	Soldier[] s0;

	public Soldier[][][] getSoldierKindArray() {
		return soldierKindArray;
	}

	public DataHandle(Vector v1, Vector v2, Vector v3, Vector v4) {
		Soldier[][] soldier = new Soldier[4][15];
		int[] length = new int[4];
		Enumeration e1 = v1.elements();
		length[0] = v1.size();
		int i1 = 0;
		while (e1.hasMoreElements()) {
			soldier[0][i1] = (Soldier) e1.nextElement();
			i1++;
		}
		e1 = v2.elements();
		length[1] = v2.size();
		i1 = 0;
		while (e1.hasMoreElements()) {
			soldier[1][i1] = (Soldier) e1.nextElement();
			i1++;
		}
		e1 = v3.elements();
		length[2] = v3.size();
		i1 = 0;
		while (e1.hasMoreElements()) {
			soldier[2][i1] = (Soldier) e1.nextElement();
			i1++;
		}
		e1 = v4.elements();
		length[3] = v4.size();
		i1 = 0;
		while (e1.hasMoreElements()) {
			soldier[3][i1] = (Soldier) e1.nextElement();
			i1++;
		}
		handle1(soldier[0], length[0], 0);
		handle1(soldier[1], length[1], 1);
		handle1(soldier[2], length[2], 2);
		handle1(soldier[3], length[3], 3);
	}

	public void handle1(Soldier[] soldier, int length, int p) {
		l = 0;
		int length2 = 0;
		s0length = length;
		s0 = new Soldier[s0length];
		System.arraycopy(soldier, 0, s0, 0, s0length);
		while (s0length > 0) {
			handle2(soldier, length, p, length2);
			length2++;
			System.arraycopy(s0, 0, soldier, 0, s0length);
		}
	}

	public void handle2(Soldier[] soldier, int length, int p, int length2) {
		if (s0length > 0) {
			if (s0length <= 5) {
				for (int i = 0; i < s0length; i++) {
					soldierKindArray[p][length2][i] = soldier[i];
					soldier[i].setState(1);
				}
				l = s0length;
				s0length = 0;
			} else if (s0length > 5) {
				soldierKindArray[p][length2][0] = soldier[0];
				soldier[0].setState(1);
				soldierKindArray[p][length2][1] = soldier[1];
				soldier[1].setState(1);
				soldierKindArray[p][length2][2] = soldier[2];
				soldier[2].setState(1);
				s0[0] = null;
				s0[1] = null;
				s0[2] = null;
				l = 3;
				for (int j = 0; j < l; j++) {
					for (int k = 0; k < s0length; k++) {
						for (int h = 0; h < l; h++) {
							if (soldierKindArray[p][length2][h] == soldier[k]) {
								break;
							} else if (h == l - 1) {
								boolean b = compare(
										soldierKindArray[p][length2][j],
										soldier[k], soldier, s0length);
								if (b) {
									soldierKindArray[p][length2][l] = soldier[k];
									soldier[k].setState(1);
									s0[k] = null;
									l++;
								}
							}
						}
					}
				}
				for (int d = 0, a = 3; a < s0length; a++) {
					if (s0[a] != null) {
						s0[d] = s0[a];
						d++;
					}
				}
				s0length -= l;
			}
			location[p][length2] = l;
		}
	}

	public int[][] getLocation() {
		return location;
	}

	public boolean compare(Soldier s1, Soldier s2, Soldier[] s, int length11) {
		boolean b = false;
		Soldier s3 = null;
		if (s1.getX() == s2.getX()
				&& (s1.getY() == s2.getY() + 1 || s1.getY() == s2.getY() - 1)) {
			for (int i = 0; i < length11;) {
				s3 = s[i];
				if (s3 == s1 || s3 == s2) {
					i++;
				} else if (s3.getY() == s1.getY() + 1
						|| s3.getY() == s2.getY() + 1
						|| s3.getY() == s1.getY() - 1
						|| s3.getY() == s2.getY() - 1) {
					b = true;
					return b;
				} else {
					i++;
				}
			}
		} else if (s1.getY() == s2.getY()
				&& (s1.getX() == s2.getX() + 1 || s1.getX() == s2.getX() - 1)) {
			for (int i = 0; i < length11;) {
				s3 = s[i];
				if (s3 == s1 || s3 == s2) {
					i++;
				} else if (s3.getX() == s1.getX() + 1
						|| s3.getX() == s2.getX() + 1
						|| s3.getX() == s1.getX() - 1
						|| s3.getX() == s2.getX() - 1) {
					b = true;
					return b;
				} else {
					i++;
				}
			}
		}
		return b;
	}

}
