package hero;

import javax.microedition.lcdui.Image;

public interface HeroInterface {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Image getSrc();

	public abstract void setSrc(Image src);

	public abstract int getLevel();

	public abstract void setLevel(int level);

	public abstract int getX();

	public abstract void setX(int x);

	public abstract int getY();

	public abstract void setY(int y);

	public abstract void setKind(int kind);

	public abstract int getKind();

}