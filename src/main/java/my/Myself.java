package my;

public class Myself {
	int level;
	long exp;
	long money;
	int nengliang;
	int countAward = 1;
	int countTongtianta = 1;

	public int getCountAward() {
		return countAward;
	}

	public void setCountAward(int countAward) {
		this.countAward = countAward;
	}

	public int getCountTongtianta() {
		return countTongtianta;
	}

	public void setCountTongtianta(int countTongtianta) {
		this.countTongtianta = countTongtianta;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public int getNengliang() {
		return nengliang;
	}

	public void setNengliang(int nengliang) {
		this.nengliang = nengliang;
	}

	public Myself() {
		this.level = 1;
		this.exp = 0;
		this.money = 0;
		this.nengliang = 20;
	}
}
