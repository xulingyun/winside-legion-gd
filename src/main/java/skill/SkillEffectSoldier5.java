package skill;

import test.CreatArray;
import test.GameEngine;
import hero.Soldier;

public class SkillEffectSoldier5 {
	CreatArray ca;
	Soldier[][][] soldierKindArray;
	Soldier[][] ss = null;

	public SkillEffectSoldier5(Soldier[][][] soldierKindArray, CreatArray ca) {
		this.soldierKindArray = soldierKindArray;
		this.ca = ca;
	}

	public synchronized Soldier[][] activity() {
		GameEngine.count = 0;
		int count = 0;
		int sum = 0;
		int[] length = new int[9];
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 9; i++) {
				if (soldierKindArray[j][i][0] != null) {
					count++;
					sum += count;
					for (int k = 0; k < 15; k++) {
						if (soldierKindArray[j][i][k] == null) {
							length[count - 1] = k;
							break;
						}
					}
				}
			}
		}
		ss = new Soldier[count][];
		for (int k = 0; k < count; k++) {
			ss[k] = new Soldier[length[count - 1]];
		}
		count = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 9; i++) {
				if (soldierKindArray[j][i][0] != null) {
					for (int k = 0; k < 15; k++) {
						if (soldierKindArray[j][i][k] != null) {
							if (k == ss[count].length) {
								System.out.println("????????????????????????????????help");
								count++;
								break;
							}
							ss[count][k] = soldierKindArray[j][i][k];
						} else {
							count++;
							break;
						}
					}
				}
			}
		}
		return ss;
	}

	public Soldier[][] getSs() {
		return ss;
	}
}
