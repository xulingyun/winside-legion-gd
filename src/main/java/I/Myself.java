package I;

public class Myself {
	long level;
	long exp;
	long money;

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public Myself(long level, long exp, long money) {
		this.level = level;
		this.exp = exp;
		this.money = money;
	}
}
