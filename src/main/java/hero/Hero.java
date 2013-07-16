package hero;

import javax.microedition.lcdui.Image;

import test.Resource;

public class Hero implements HeroInterface {

	public final static int booldX = 178;
	public final static int booldY = 473;
	public final static int magicX = 178;
	public final static int magicY = 498;

	String name;
	Image src = Resource.loadImage(Resource.hero);
	Image heroskillSrc = Resource.loadImage(Resource.heroskillsrc);
	int level;
	int x;
	int y;
	int boold;
	int boolds;
	int skillIndex = 0;
	long skillStartTime = 0;
	int money;
	int energy;
	int masonry;
	int kind;
	float defenceCoefficient = 1.0f;
	float AtkCoefficient = 1.0f;
	float booldCoefficient = 1.0f;
	long negativeTime = 0l;
	public final static long hurtTime = 10000;
	long hurtStartTime;
	boolean lastHurt = false;

	public Image getHeroskillSrc() {
		return heroskillSrc;
	}

	public boolean isLastHurt() {
		return lastHurt;
	}

	public void setLastHurt(boolean lastHurt) {
		this.lastHurt = lastHurt;
	}

	public long getHurtStartTime() {
		return hurtStartTime;
	}

	public void setHurtStartTime(long hurtStartTime) {
		this.hurtStartTime = hurtStartTime;
	}

	public long getNegativeTime() {
		return negativeTime;
	}

	public void setNegativeTime(long negativeTime) {
		this.negativeTime = negativeTime;
	}

	public float getAtkCoefficient() {
		return AtkCoefficient;
	}

	public void setAtkCoefficient(float atkCoefficient) {
		AtkCoefficient = atkCoefficient;
	}

	public float getBooldCoefficient() {
		return booldCoefficient;
	}

	public void setBooldCoefficient(float booldCoefficient) {
		this.booldCoefficient = booldCoefficient;
	}

	public float getDefenceCoefficient() {
		return defenceCoefficient;
	}

	public void setDefenceCoefficient(float defenceCoefficient) {
		this.defenceCoefficient = defenceCoefficient;
	}

	public long getSkillStartTime() {
		return skillStartTime;
	}

	public void setSkillStartTime(long skillStartTime) {
		this.skillStartTime = skillStartTime;
	}

	public int getSkillIndex() {
		return skillIndex;
	}

	public void setSkillIndex(int skillIndex) {
		this.skillIndex = skillIndex;
	}

	public int getBoolds() {
		return boolds;
	}

	public void setBoolds(int boolds) {
		this.boolds = boolds;
	}

	int magic;
	long exp;
	public int armor;

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getKind() {
		return kind;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getMasonry() {
		return masonry;
	}

	public void setMasonry(int masonry) {
		this.masonry = masonry;
	}

	public int getBoold() {
		return boold;
	}

	public void setBoold(int boold) {
		this.boold = boold;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic, int count) {
		this.magic = magic;
		if (count == 3)
			this.magic += 10;
		else if (count == 4)
			this.magic += 15;
		else if (count >= 5)
			this.magic += 20;
		if (this.magic > 100) {
			this.magic = 100;
		}
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

	public Hero(String name, int kind, int level, int armor, int x, int y,
			int boold, int magic, long exp, int boolds) {
		this.name = name;
		this.level = level;
		this.x = x;
		this.y = y;
		this.kind = kind;
		this.boold = boold;
		this.magic = magic;
		this.exp = exp;
		this.boolds = boolds;
		this.armor = armor;
	}

}
