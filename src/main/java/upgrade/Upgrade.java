package upgrade;

import test.Resource;

public class Upgrade {

	public static int upgradeSoldierMoney(int level, int kind) {
		return level * Resource.soldierValue[kind - 1][3];
	}

	public static int upgradeSoldierAttack(int level, int kind) {
		return Resource.soldierValue[kind - 1][0] + (level - 1)
				* Resource.soldierValue[kind - 1][2];
	}

	public static int upgradeHeroDefence(int level, int kind) {
		return Resource.heroValue[kind][1] + (level - 1)
				* Resource.heroValue[kind][4];
	}

	public static int upgradeHeroBlood(int level, int kind) {
		return Resource.heroValue[kind][0] + (level - 1)
				* Resource.heroValue[kind][3];
	}

	public static int upgradeHeroMoney(int level, int kind) {
		return Resource.heroValue[kind][2] + (level - 1)
				* Resource.heroValue[kind][5];
	}

	public static int upgradeHeroAttack(int level, int kind) {
		return Resource.heroValue[kind][6] + (level - 1)
				* Resource.heroValue[kind][7];
	}

	public static long upgradeLeaderExp(long level) {
		long exp = (long) (((level - 1) * (level - 1) + 60) / 5 * ((level - 1) * 2 + 60));
		return exp;
	}

	public static int upgradeLeaderNengliang(int level) {
		return 20 + (level - 1) * 1;
	}

}
