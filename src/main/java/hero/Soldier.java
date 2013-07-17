package hero;

import javax.microedition.lcdui.Image;

public class Soldier extends Animal {
	private Soldier soldier;
	private int kind;
	private int state;
	private boolean collision = false;
	private int authenticKind;
	private long startTime;
	private int animIndex;
	private boolean fightWait;
	private int fightWaitIndex;
	private boolean firstAction = true;
	private static float AtkCoefficient = 1.0f;

	public static float getAtkCoefficient() {
		return AtkCoefficient;
	}

	public static void setAtkCoefficient(float f) {
		AtkCoefficient = f;
	}

	public boolean isFirstAction() {
		return firstAction;
	}

	public void setFirstAction(boolean firstAction) {
		this.firstAction = firstAction;
	}

	public boolean isFightWait() {
		return fightWait;
	}

	public void setFightWait(boolean fightWait) {
		this.fightWait = fightWait;
	}

	public int getAnimIndex() {
		return animIndex;
	}

	public int getFightWaitIndex() {
		return fightWaitIndex;
	}

	public void setFightWaitIndex(int fightWaitIndex) {
		this.fightWaitIndex = fightWaitIndex;
	}

	public void setAnimIndex(int animIndex) {
		this.animIndex = animIndex;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime() {
		this.startTime = System.currentTimeMillis();
	}

	public int getAuthenticKind() {
		return authenticKind;
	}

	public void setAuthenticKind(int authenticKind) {
		this.authenticKind = authenticKind;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public Soldier(String name, Image src, int level, int x, int y, int kind,
			int state, int offX, int offY, int authenticKind, int animIndex) {
		super(name, src, level, x, y, offX, offY);
		this.kind = kind;
		this.state = state;
		this.authenticKind = authenticKind;
		this.animIndex = animIndex;
		fightWait = false;
		fightWaitIndex = 0;
	}

	public Soldier() {
	}

	public boolean equal(Soldier s2) {
		System.out.println(s2);
		if (soldier.getX() == s2.getX() && soldier.getY() == s2.getY())
			return true;
		else
			return false;
	}

	public static boolean equal(Soldier s1, Soldier s2) {
		if (s1.getX() == s2.getX() && s1.getY() == s2.getY())
			return true;
		else
			return false;
	}

}
