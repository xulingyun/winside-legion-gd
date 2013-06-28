package map;

public class StrikeHero {

	private static final StrikeHero sh = new StrikeHero();
	private int indexAnim = 4;

	private StrikeHero() {
	}

	public int getIndexAnim() {
		return indexAnim;
	}

	public void setIndexAnim(int indexAnim) {
		this.indexAnim = indexAnim;
	}

	public static StrikeHero getInstance() {
		return sh;
	}

	public boolean isChange() {
		if (indexAnim == 4) {
			return true;
		} else
			return false;
	}

}
