package map;

import javax.microedition.lcdui.Image;

import test.Resource;
import cn.ohyeah.stb.game.SGraphics;

public class Wave {

	private static boolean show = false;
	private static long time;

	public static void draw(SGraphics g, int index) {
		Image wave = Resource.loadImage(Resource.wave);
		int h = wave.getHeight(), w = wave.getWidth();
		int mapx = 320 - wave.getWidth() / 2 / 4;
		int mapy = 100;
		g.drawRegion(wave, index * w / 4, 0, w / 4, h, 0, mapx, mapy, 20);
		Resource.releaseImage(Resource.wave);
	}

	public static void setShow(boolean show) {
		Wave.show = show;
	}

	public static boolean isShow() {
		return show;
	}

	public static void setTime(long time) {
		Wave.time = time;
	}

	public static long getTime() {
		return time;
	}
}
