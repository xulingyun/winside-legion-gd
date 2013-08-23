package upgrade;

import test.Resource;

public class Upgrade {

	public static int upgradeMonsterBoold(int initBoold, int guanqia, int baseNumBoold, boolean fangshi, int tong40) {
		int blood;
		if (!fangshi)
			blood = initBoold + guanqia * baseNumBoold;
		else {
			if (tong40 == 0)
				blood = initBoold + guanqia * baseNumBoold;
			else
				blood = initBoold * 2 + (guanqia + 40) * baseNumBoold;
		}
		return blood;
	}

	public static int upgradeMonsterAtk(int initAtk, int guanqia, int baseNumAtk, boolean fangshi, int tong40) {
		int Atk;
		if (!fangshi)
			Atk = initAtk + guanqia * baseNumAtk;
		else {
			if (tong40 == 0)
				Atk = initAtk + guanqia * baseNumAtk;
			else
				Atk = initAtk * 2 + (guanqia + 40) * baseNumAtk;
		}
		return Atk;
	}

	public static int upgradeSoldierMoney(int level, int kind) {
		return level * Resource.soldierValue[kind - 1][3];
	}

	public static int upgradeSoldierAttack(int level, int kind) {
		return Resource.soldierValue[kind - 1][0] + (level - 1) * Resource.soldierValue[kind - 1][2];
	}

	public static int upgradeHeroDefence(int level, int kind) {
		return Resource.heroValue[kind][1] + (level - 1) * Resource.heroValue[kind][4];
	}

	public static int upgradeHeroBlood(int level, int kind) {
		return Resource.heroValue[kind][0] + (level - 1) * Resource.heroValue[kind][3];
	}

	public static int upgradeHeroMoney(int level, int kind) {
		return Resource.heroValue[kind][2] + (level - 1) * Resource.heroValue[kind][5];
	}

	public static int upgradeHeroAttack(int level, int kind) {
		int temp;
		if (kind < 5) {
			temp = Resource.heroValue[kind][6] + (level - 1) * Resource.heroValue[kind][7];
		} else
			temp = Resource.heroValue[kind][8] + (level - 1) * Resource.heroValue[kind][9];
		return temp;
	}

	public static long upgradeLeaderExp(long level) {
		long exp = (long) (((level - 1) * (level - 1) + 60) / 5 * ((level - 1) * 2 + 60));
		return exp;
	}

	public static int upgradeLeaderNengliang(int level) {
		return 20 + (level - 1) * 1;
	}

	public static long guanqiaMoney(int guanqiaIndex) {
		return Resource.guanqiaMoneyAndJingyan[0] + Resource.guanqiaMoneyAndJingyan[1] * guanqiaIndex;
	}

	public static long guanqiaExp(int guanqiaIndex) {
		return Resource.guanqiaMoneyAndJingyan[2] + Resource.guanqiaMoneyAndJingyan[3] * guanqiaIndex;
	}

	public static long TguanqiaMoney(int guanqiaIndex, int tong40) {
		long money;
		if (tong40 == 0) {
			money = Resource.TongMoneyAndJingyan[0] + Resource.TongMoneyAndJingyan[1] * guanqiaIndex;
		} else {
			money = Resource.TongMoneyAndJingyan[0] * 2 + Resource.TongMoneyAndJingyan[1] * (guanqiaIndex + 40);
		}
		return money;
	}

	public static long TguanqiaExp(int guanqiaIndex, int tong40) {
		long Exp;
		if (tong40 == 0) {
			Exp = Resource.TongMoneyAndJingyan[2] + Resource.TongMoneyAndJingyan[3] * guanqiaIndex;
		} else {
			Exp = Resource.TongMoneyAndJingyan[2] * 2 + Resource.TongMoneyAndJingyan[3] * (guanqiaIndex + 40);
		}
		return Exp;
	}

}