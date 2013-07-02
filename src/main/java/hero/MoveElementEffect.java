package hero;

import test.GameEngine;

public class MoveElementEffect {

	public static boolean state = false;
	public static int index=-1;

	public boolean start(Soldier[][] idPic) {
		state = true;
		jumpTag: for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				if (idPic[i][j]!=null&&idPic[i][j].getOffX() < 0) {
					state = true;
					index=1;
					break jumpTag;
				} else if (idPic[6][3].getOffX() == 0) {
					state = false;
					index = 0;
				}
			}
		}
		if (state) {
			for (int i = 0; i < 7; i++) {
				for (int j = 3; j >= 0; j--) {
					if (idPic[i][j]!=null&&idPic[i][j].getOffX() < 0) {
						if(!GameEngine.stopState)
						idPic[i][j].setOffX(idPic[i][j].getOffX() + 20);
					}
				}
			}
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return state;
	}

}
