package hero;

import javax.microedition.lcdui.Image;

import cn.ohyeah.stb.util.RandomValue;

public class Monster implements HeroInterface {

	public int skillWaitTime;
	public int hurtValue;
	public int kind;
	public Image src;
	public String name;
	public int level;
	int x;
	int y;
	int bloods;
	int boold;
	int state;
	public long startTime;
	public long endTime;
	long waitTime;
	int _x;
	int animIndex;
	boolean newMonster;
	int animDeath = 0;

	public int getAnimDeath() {
		return animDeath;
	}

	public void setAnimDeath(int animDeath) {
		this.animDeath = animDeath;
	}

	public boolean isNewMonster() {
		return newMonster;
	}

	public void setNewMonster(boolean newMonster) {
		this.newMonster = newMonster;
	}

	public int getAnimIndex_attack() {
		return animIndex_attack;
	}

	public void setAnimIndex_attack(int animIndex_attack) {
		this.animIndex_attack = animIndex_attack;
	}

	int animIndex_attack;
	int progressbar;
	long startBlood;
	boolean showBlood = false;

	public Monster(int skillWaitTime, int hurtValue, int kind, Image src,
			String name, int level, int x, int y, int boold, int state,
			int progressbar, int boolds) {
		this.skillWaitTime = skillWaitTime;
		this.hurtValue = hurtValue;
		this.kind = kind;
		this.src = src;
		this.name = name;
		this.level = level;
		this.x = x;
		this.y = y;
		this.boold = boold;
		this.bloods = boolds;
		this.state = state;
		this.progressbar = progressbar;
		_x = x;
		newMonster = true;
		animIndex = 0;
		animIndex_attack = 0;
		setWaitTime();
		setStartTime(System.currentTimeMillis());
	}

	public boolean isShowBlood() {
		return showBlood;
	}

	public void setShowBlood(boolean showBlood) {
		this.showBlood = showBlood;
	}

	public long getStartBlood() {
		return startBlood;
	}

	public void setStartBlood(long startBlood) {
		this.startBlood = startBlood;
	}

	public int getProgressbar() {
		return progressbar;
	}

	public void setProgressbar(int progressbar) {
		this.progressbar = progressbar;
	}

	public int getAnimIndex() {
		return animIndex;
	}

	public void setAnimIndex(int animIndex) {
		this.animIndex = animIndex;
	}

	public boolean isAlreadyCollision() {
		return alreadyCollision;
	}

	public void setAlreadyCollision(boolean alreadyCollision) {
		this.alreadyCollision = alreadyCollision;
	}

	boolean alreadyCollision = false;

	public void setWaitTime() {
		this.waitTime = RandomValue.getRandInt(1, 5) * 1000;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		endTime = System.currentTimeMillis();
		return endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getSkillWaitTime() {
		return skillWaitTime;
	}

	public void setSkillWaitTime(int skillWaitTime) {
		this.skillWaitTime = skillWaitTime;
	}

	public int getHurtValue() {
		return hurtValue;
	}

	public void setHurtValue(int hurtValue) {
		this.hurtValue = hurtValue;
	}

	public int getBoold() {
		return boold;
	}

	public void setBoold(int boold) {
		this.boold = boold;
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

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getKind() {
		return kind;
	}

	public Monster() {
	}

	public int getBloods() {
		return bloods;
	}

	public void setBloods(int bloods) {
		this.bloods = bloods;
	}

	public int get_x() {
		return _x;
	}

	public void set_x(int _x) {
		this._x = _x;
	}

	public static boolean equals(Monster m1, Monster m2) {
		if (m1.getX() == m2.getX() && m1.getY() == m2.getY())
			return true;
		else
			return false;
	}

}
