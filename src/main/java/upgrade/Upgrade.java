package upgrade;

public class Upgrade {

	public static int upgradeSoldierMoney(int level, int kind) {
		if (kind == 1)
			return level * 1000;
		else if (kind == 2)
			return level * 3500;
		else if (kind == 3)
			return level * 3000;
		else if (kind == 4)
			return level * 2000;
		else if (kind == 5)
			return level * 2500;
		else if (kind == 6)
			return level * 4000;
		else if (kind == 7)
			return level * 5000;
		else if (kind == 8)
			return level * 5000;
		else
			return level * 5000;
	}

	public static int upgradeSoldierAttack(int level, int kind) {
		if (kind == 1)
			return 30 + (level - 1) * 3;
		else if (kind == 2)
			return 35 + (level - 1) * 3;
		else if (kind == 3)
			return 35 + (level - 1) * 2;
		else if (kind == 4)
			return 40 + (level - 1) * 2;
		else if (kind == 5)
			return 45 + (level - 1) * 3;
		else if (kind == 6)
			return 30 + (level - 1) * 3;
		else if (kind == 7)
			return 35 + (level - 1) * 2;
		else if (kind == 8)
			return 50 + (level - 1) * 3;
		else
			return 60 + (level - 1) * 3;
	}

	public static int upgradeHeroDefence(int level, int kind) {
		if (kind == 0)
			return 5 + (level - 1) * 5;
		else if (kind == 1)
			return 10 + (level - 1) * 5;
		else if (kind == 2)
			return 10 + (level - 1) * 5;
		else if (kind == 3)
			return 15 + (level - 1) * 5;
		else if (kind == 4)
			return 20 + (level - 1) * 5;
		else
			return 30 + (level - 1) * 5;
	}

	public static int upgradeHeroBlood(int level, int kind) {
		if (kind == 0)
			return 450 + (level - 1) * 50;
		else if (kind == 1)
			return 500 + (level - 1) * 60;
		else if (kind == 2)
			return 600 + (level - 1) * 70;
		else if (kind == 3)
			return 700 + (level - 1) * 80;
		else if (kind == 4)
			return 800 + (level - 1) * 100;
		else
			return 800 + (level - 1) * 120;
	}

	public static int upgradeHeroMoney(int level, int kind) {
		if (kind == 0)
			return 1000 + (level - 1) * 4000;
		else if (kind == 1)
			return 2000 + (level - 1) * 4000;
		else if (kind == 2)
			return 3000 + (level - 1) * 5000;
		else if (kind == 3)
			return 4000 + (level - 1) * 5000;
		else if (kind == 4)
			return 5000 * level;
		else
			return 5000 * level + 1000;
	}

	public static long upgradeLeader(long level) {
		long exp = (long)(((level-1)*(level-1)*(level-1)+60)/5*((level-1)*2+60));
		return exp;
	}

}
