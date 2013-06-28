package test;

import hero.Hero;
import hero.IceDrow;
import hero.Monster;
import hero.MoveElementEffect;
import hero.Point;
import hero.Soldier;
import skill.Critical;
import test.PropManager;
import upgrade.Upgrade;
import map.Arrow;
import map.Bullet;
import map.Map;
import map.PromoteBlood;
import map.StrikeHero;

import I.Myself;
import cn.ohyeah.itvgame.model.GameRecord;
import cn.ohyeah.stb.game.GameCanvasEngine;
import cn.ohyeah.stb.game.Recharge;
import cn.ohyeah.stb.game.SGraphics;
import cn.ohyeah.stb.game.ServiceWrapper;
import cn.ohyeah.stb.key.KeyCode;
import cn.ohyeah.stb.key.KeyState;
import cn.ohyeah.stb.res.UIResource;
import cn.ohyeah.stb.ui.DrawUtil;
import cn.ohyeah.stb.ui.PopupConfirm;
import cn.ohyeah.stb.ui.PopupText;
import cn.ohyeah.stb.ui.TextView;
import cn.ohyeah.stb.util.BytesUtil;
import cn.ohyeah.stb.util.Collision;
import cn.ohyeah.stb.util.RandomValue;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

import crud.CRUD;

public class GameEngine extends GameCanvasEngine {
	public static boolean state;
	long[] recordValue = new long[58];
	CreatArray ca;
	int index;
	int indexSelect;
	public static int add = -1;
	public static int count;
	int arrowIndex;
	String page;
	int mainIndex;
	int gameIndex;
	int gameButtonSelect;
	int panle_x = 190;
	int panle_y = 130;
	long startAddTime = 0;
	int mapIndexOfIndex;
	int mainPage3Index;
	int mainPage4Index;
	int selectHeroIndex;
	boolean startAddState = false;
	private int[][][][][] mapArray;
	int[][] location = new int[4][9];
	public static Image[] img = new Image[140];
	Soldier[][] idPic = new Soldier[7][4];
	Monster[][] monsterPic = new Monster[7][4];
	int[] mainPageIndex = new int[5];
	Vector monsterVector = new Vector();
	Vector heroSkillmonster = new Vector();
	Vector buttleArray = new Vector();
	Vector arrowArray = new Vector();
	Vector magicArray = new Vector();
	Vector slickballArray = new Vector();
	Vector firelockArray = new Vector();
	Vector criticalArray = new Vector();
	Vector criticalArray1 = new Vector();
	int[] randomArray = new int[12];
	Point[] p = new Point[12];
	Hero hero;
	Map map = new Map();
	MoveElementEffect mee = new MoveElementEffect();
	int selectSoldierLocation;
	int selectSoldierindex;
	Vector deathArray = new Vector();
	CRUD crud;
	public PropManager pm;
	int winNum;
	int tempWinNum;
	int weiyiCount;
	boolean startAward;
	boolean isAlertAward;
	int circleNum;
	int sumCircleNum;
	int stopCount;
	int moveSmall_Index;
	int moveSmall_x;
	int moveSmall_y;
	int win1;
	int canGoodsKind;
	Point skill1Hero;

	public PlayerProp[] props;
	Myself my;
	GameRecord record;
	int temp_img_distance1 = 18;
	int temp_img_distance2 = temp_img_distance1 + 54;

	protected GameEngine(MIDlet midlet) {
		super(midlet);
		setRelease(false);
		pm = new PropManager(this);
		gameIndex = 0;
		gameButtonSelect = 0;
		mapIndexOfIndex = 0;
		mainPageIndex[0] = 0;
		page = Resource.PAGE_INDEX[0];
		mainIndex = -1;
		keyState = new KeyState();
		arrowIndex = 0;
		selectSoldierLocation = 0;
		selectSoldierindex = 0;
		mainPage3Index = -1;
		mainPage4Index = -1;
		selectHeroIndex = 0;
		shopPage = 0;
		shopSum = 11;
		img[0] = Resource.loadImage(Resource.soldier0);
		img[1] = Resource.loadImage(Resource.soldier1);
		img[2] = Resource.loadImage(Resource.soldier2);
		img[3] = Resource.loadImage(Resource.soldier3);
		img[4] = Resource.loadImage(Resource.soldier4);
		img[5] = Resource.loadImage(Resource.soldier5);
		img[6] = Resource.loadImage(Resource.soldier6);
		img[7] = Resource.loadImage(Resource.soldier7);
		img[8] = Resource.loadImage(Resource.soldier8);
		img[9] = Resource.loadImage(Resource.arrows);
		img[10] = Resource.loadImage(Resource.back0);
		img[11] = Resource.loadImage(Resource.hero);
		img[12] = Resource.loadImage(Resource.selectbox1);
		img[13] = Resource.loadImage(Resource.selectbox2);
		img[14] = Resource.loadImage(Resource.select2);
		img[15] = Resource.loadImage(Resource.star);
		img[16] = Resource.loadImage(Resource.number);
		img[17] = Resource.loadImage(Resource.money);
		loadMainImg();
	}

	public void getData() {
		ServiceWrapper sw = getServiceWrapper();
		record = sw.readRecord(100);
		pm = new PropManager(this);
		pm.queryProps();
		byte[] b = new byte[10000];
		if (record == null) {
			record = new GameRecord();
			record.setRecordId(100);
			record.setRemark("这是记录");
			int j = 0;
			int i = 0;
			for (j = 0; j < Resource.myProperty.length; j++) {
				BytesUtil.writeLong(b, j * 8, Resource.myProperty[j]);
			}
			i = (j + 1) * 8;
			for (j = 0; j < Resource.heroAndSoldierLevel.length; j++) {
				b[i++] = Resource.heroAndSoldierLevel[j];
			}
			for (j = 0; j < Resource.guanqiaLevel.length; j++) {
				b[i++] = Resource.guanqiaLevel[j];
			}
			for (j = 0; j < Resource.goodsNums.length; j++) {
				BytesUtil.writeInt(b, i + 4 * j, Resource.goodsNums[j]);
			}
			record.setData(b);
			sw.saveRecord(record);
		} else {
			b = record.getData();
			int i = 0;
			int j = 0;
			for (j = 0; j < Resource.myProperty.length; j++) {
				Resource.myProperty[j] = BytesUtil.readLong(b, j * 8);
			}
			i = (j + 1) * 8;
			for (j = 0; j < Resource.heroAndSoldierLevel.length; j++) {
				Resource.heroAndSoldierLevel[j] = b[i++];
			}
			for (j = 0; j < Resource.guanqiaLevel.length; j++) {
				Resource.guanqiaLevel[j] = b[i++];
			}
			for (j = 0; j < Resource.goodsNums.length; j++) {
				Resource.goodsNums[j] = BytesUtil.readInt(b, i + j * 4);
				if (Resource.goodsNums[j] > 0) {
					canGoodsKind++;
				}
			}
		}
		my = new Myself(Resource.myProperty[0], Resource.myProperty[1],
				Resource.myProperty[2]);
	}

	public void loadGameImg() {
		img[temp_img_distance1 - 6 + 41] = Resource.loadImage(Resource.queding);
		img[temp_img_distance1 - 6 + 15] = Resource.loadImage(Resource.button1);
		img[temp_img_distance1 - 6 + 39] = Resource.loadImage(Resource.panel1);
		img[temp_img_distance2] = Resource.loadImage(Resource.bg0);
		img[temp_img_distance2 + 1] = Resource.loadImage(Resource.bg1);
		img[temp_img_distance2 + 2] = Resource.loadImage(Resource.bg2);
		img[temp_img_distance2 + 3] = Resource.loadImage(Resource.bg3);
		img[temp_img_distance2 + 4] = Resource.loadImage(Resource.bg4);
		img[temp_img_distance2 + 5] = Resource.loadImage(Resource.bg5);
		img[temp_img_distance2 + 7] = Resource.loadImage(Resource.monster1);
		img[temp_img_distance2 + 8] = Resource.loadImage(Resource.monster2);
		img[temp_img_distance2 + 9] = Resource.loadImage(Resource.monster3);
		img[temp_img_distance2 + 10] = Resource.loadImage(Resource.monster4);
		img[temp_img_distance2 + 11] = Resource.loadImage(Resource.monster5);
		img[temp_img_distance2 + 12] = Resource.loadImage(Resource.monster6);
		img[temp_img_distance2 + 13] = Resource.loadImage(Resource.monster7);
		img[temp_img_distance2 + 14] = Resource.loadImage(Resource.monster8);
		img[temp_img_distance2 + 15] = Resource.loadImage(Resource.monster9);
		img[temp_img_distance2 + 16] = Resource.loadImage(Resource.monster10);
		img[temp_img_distance2 + 17] = Resource.loadImage(Resource.monster11);
		img[temp_img_distance2 + 18] = Resource.loadImage(Resource.monster12);
		img[temp_img_distance2 + 19] = Resource.loadImage(Resource.monster13);
		img[temp_img_distance2 + 20] = Resource.loadImage(Resource.boss1);
		img[temp_img_distance2 + 21] = Resource.loadImage(Resource.boss2);
		img[temp_img_distance2 + 22] = Resource.loadImage(Resource.boss3);
		img[temp_img_distance2 + 23] = Resource.loadImage(Resource.boss4);
		img[temp_img_distance2 + 24] = Resource.loadImage(Resource.boss5);
		img[temp_img_distance2 + 25] = Resource.loadImage(Resource.boss6);
		img[temp_img_distance2 + 26] = Resource.loadImage(Resource.boss7);
		img[temp_img_distance2 + 27] = Resource.loadImage(Resource.boss8);
		img[temp_img_distance2 + 28] = Resource.loadImage(Resource.bloodmagic);
		img[temp_img_distance2 + 29] = Resource.loadImage(Resource.bmbar);
		img[temp_img_distance2 + 30] = Resource.loadImage(Resource.powerbar);
		img[temp_img_distance2 + 31] = Resource.loadImage(Resource.powerbarbg);
		img[temp_img_distance2 + 32] = Resource.loadImage(Resource.powerbg1);
		img[temp_img_distance2 + 33] = Resource.loadImage(Resource.powerbg2);
		img[temp_img_distance2 + 34] = Resource.loadImage(Resource.herobg);
		img[temp_img_distance2 + 35] = Resource.loadImage(Resource.toolnumkey);
		img[temp_img_distance2 + 36] = Resource.loadImage(Resource.zhandouleft);
		img[temp_img_distance2 + 37] = Resource
				.loadImage(Resource.zhandoumiddle);
		img[temp_img_distance2 + 38] = Resource
				.loadImage(Resource.zhandouright);
		img[temp_img_distance2 + 39] = Resource.loadImage(Resource.monsterdie);
		img[temp_img_distance2 + 40] = Resource.loadImage(Resource.attackhero);
		img[temp_img_distance2 + 41] = Resource.loadImage(Resource.boss1attack);
		img[temp_img_distance2 + 42] = Resource.loadImage(Resource.boss2attack);
		img[temp_img_distance2 + 43] = Resource
				.loadImage(Resource.boss3attack1);
		img[temp_img_distance2 + 44] = Resource
				.loadImage(Resource.boss3attack2);
		img[temp_img_distance2 + 45] = Resource.loadImage(Resource.boss4attack);
		img[temp_img_distance2 + 46] = Resource.loadImage(Resource.boss5attack);
		img[temp_img_distance2 + 47] = Resource.loadImage(Resource.boss6m);
		img[temp_img_distance2 + 48] = Resource
				.loadImage(Resource.boss6mattack);
		img[temp_img_distance2 + 49] = Resource
				.loadImage(Resource.boss78attack);
		img[temp_img_distance2 + 50] = Resource.loadImage(Resource.soldier21);
		img[temp_img_distance2 + 51] = Resource.loadImage(Resource.soldier22);
		img[temp_img_distance2 + 52] = Resource.loadImage(Resource.soldier31);
		img[temp_img_distance2 + 53] = Resource.loadImage(Resource.soldier32);
		img[temp_img_distance2 + 54] = Resource.loadImage(Resource.soldier81);
		img[temp_img_distance2 + 55] = Resource.loadImage(Resource.soldier91);
		img[temp_img_distance2 + 56] = Resource.loadImage(Resource.bullet1);
		img[temp_img_distance2 + 57] = Resource.loadImage(Resource.bullet2);
		img[temp_img_distance2 + 58] = Resource.loadImage(Resource.powerbg3);
		img[temp_img_distance2 + 59] = Resource.loadImage(Resource.bosspower1);
		img[temp_img_distance2 + 60] = Resource.loadImage(Resource.bosspower2);
		img[temp_img_distance2 + 61] = Resource.loadImage(Resource.bossblood1);
		img[temp_img_distance2 + 62] = Resource.loadImage(Resource.bossblood2);
		img[temp_img_distance2 + 63] = Resource.loadImage(Resource.hero1skill);
		img[temp_img_distance2 + 64] = Resource
				.loadImage(Resource.attacknumber);
		img[temp_img_distance2 + 65] = Resource.loadImage(Resource.baojibg);
	}

	public void loadMainImg() {
		img[temp_img_distance1 + 0] = Resource.loadImage(Resource.awardbutton);
		img[temp_img_distance1 + 1] = Resource.loadImage(Resource.bg6);
		img[temp_img_distance1 + 2] = Resource.loadImage(Resource.bg10);
		img[temp_img_distance1 + 3] = Resource.loadImage(Resource.bg11);
		img[temp_img_distance1 - 6 + 10] = Resource.loadImage(Resource.bgleft);
		img[temp_img_distance1 - 6 + 11] = Resource
				.loadImage(Resource.bgmiddle);
		img[temp_img_distance1 - 6 + 12] = Resource.loadImage(Resource.bgright);
		img[temp_img_distance1 - 6 + 13] = Resource
				.loadImage(Resource.biankuang);
		img[temp_img_distance1 - 6 + 14] = Resource
				.loadImage(Resource.bingzhonginfo);
		img[temp_img_distance1 - 6 + 15] = Resource.loadImage(Resource.button1);
		img[temp_img_distance1 - 6 + 16] = Resource.loadImage(Resource.button2);
		img[temp_img_distance1 - 6 + 17] = Resource
				.loadImage(Resource.buttonbg);
		img[temp_img_distance1 - 6 + 18] = Resource
				.loadImage(Resource.buttontext1);
		img[temp_img_distance1 - 6 + 19] = Resource
				.loadImage(Resource.buttontext2);
		img[temp_img_distance1 - 6 + 20] = Resource
				.loadImage(Resource.buyandshengji);
		img[temp_img_distance1 - 6 + 21] = Resource.loadImage(Resource.caifu);
		img[temp_img_distance1 - 6 + 22] = Resource.loadImage(Resource.fire);
		img[temp_img_distance1 - 6 + 23] = Resource
				.loadImage(Resource.grasslands);
		img[temp_img_distance1 - 6 + 24] = Resource.loadImage(Resource.guanqia);
		img[temp_img_distance1 - 6 + 25] = Resource
				.loadImage(Resource.guanqiapj);
		img[temp_img_distance1 - 6 + 26] = Resource
				.loadImage(Resource.heroinfoall);
		img[temp_img_distance1 - 6 + 27] = Resource
				.loadImage(Resource.guanqiatext);
		img[temp_img_distance1 - 6 + 28] = Resource.loadImage(Resource.herobig);
		img[temp_img_distance1 - 6 + 29] = Resource
				.loadImage(Resource.heroinfo);
		img[temp_img_distance1 - 6 + 30] = Resource.loadImage(Resource.ice);
		img[temp_img_distance1 - 6 + 31] = Resource
				.loadImage(Resource.leftright);
		img[temp_img_distance1 - 6 + 32] = Resource.loadImage(Resource.login);
		img[temp_img_distance1 - 6 + 33] = Resource
				.loadImage(Resource.logintext);
		img[temp_img_distance1 - 6 + 34] = Resource
				.loadImage(Resource.myherotext);
		img[temp_img_distance1 - 6 + 35] = Resource
				.loadImage(Resource.mysoldertext);
		img[temp_img_distance1 - 6 + 36] = Resource
				.loadImage(Resource.newcomer);
		img[temp_img_distance1 - 6 + 37] = Resource.loadImage(Resource.notgo);
		img[temp_img_distance1 - 6 + 38] = Resource
				.loadImage(Resource.numberbig);
		img[temp_img_distance1 - 6 + 39] = Resource.loadImage(Resource.panel1);
		img[temp_img_distance1 - 6 + 40] = Resource.loadImage(Resource.pit);
		img[temp_img_distance1 - 6 + 41] = Resource.loadImage(Resource.queding);
		img[temp_img_distance1 - 6 + 42] = Resource
				.loadImage(Resource.selectforce);
		img[temp_img_distance1 - 6 + 43] = Resource.loadImage(Resource.shopbag);
		img[temp_img_distance1 - 6 + 44] = Resource.loadImage(Resource.shopbar);
		img[temp_img_distance1 - 6 + 45] = Resource
				.loadImage(Resource.shopleft);
		img[temp_img_distance1 - 6 + 46] = Resource
				.loadImage(Resource.shopmiddle);
		img[temp_img_distance1 - 6 + 47] = Resource
				.loadImage(Resource.shopright);
		img[temp_img_distance1 - 6 + 48] = Resource
				.loadImage(Resource.shopselect);
		img[temp_img_distance1 - 6 + 49] = Resource
				.loadImage(Resource.shoptext);
		img[temp_img_distance1 - 6 + 50] = Resource
				.loadImage(Resource.shopthingnum);
		img[temp_img_distance1 - 6 + 51] = Resource
				.loadImage(Resource.shoptool);
		img[temp_img_distance1 - 6 + 52] = Resource.loadImage(Resource.shopx1);
		img[temp_img_distance1 - 6 + 53] = Resource.loadImage(Resource.shopx2);
		img[temp_img_distance1 - 6 + 54] = Resource
				.loadImage(Resource.somewherename);
		img[temp_img_distance1 - 6 + 55] = Resource
				.loadImage(Resource.titlebingzhong);
		img[temp_img_distance1 - 6 + 56] = Resource
				.loadImage(Resource.titlehero);
		img[temp_img_distance1 - 6 + 57] = Resource
				.loadImage(Resource.udanddown);
		img[temp_img_distance1 - 6 + 58] = Resource.loadImage(Resource.wujin);
		img[temp_img_distance1 - 6 + 59] = Resource
				.loadImage(Resource.awardtext);
	}

	public void clearMainImg() {
		for (int i = 18; i < temp_img_distance2; i++)
			img[i] = null;
	}

	public void clearGameImg() {
		for (int i = temp_img_distance1; i < img.length; i++)
			img[i] = null;
	}

	private void handleMainKey3(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			if (mainPage3Index == 1)
				selectHeroIndex = mainPageIndex[3];
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			if (mainPage3Index == -1) {
				if (mainPageIndex[3] > 0)
					mainPageIndex[3]--;
				else
					mainPageIndex[3] = 5;
			}
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			if (mainPage3Index == -1) {
				if (mainPageIndex[3] < 5)
					mainPageIndex[3]++;
				else
					mainPageIndex[3] = 0;
			}
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (mainPage3Index == 1) {
				mainPage3Index = 0;
			} else
				mainPage3Index = -1;
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (mainPage3Index == -1) {
				mainPage3Index = 0;
			} else if (mainPage3Index == 0) {
				mainPage3Index = 1;
			}
		} else if (key.containsAndRemove(KeyCode.NUM0)) {
			mainIndex = 2;
		}
	}

	private void handleMainKey4(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			if (mainPage4Index == 1) {
				PopupText pt = UIResource.getInstance().buildDefaultPopupText();
				int num = changeSoldier();
				if (num == 0)
					pt.setText("卸载了该兵种！");
				else if (num == 1)
					pt.setText("添加了该兵种！");
				else if (num == 2)
					pt.setText("兵种已经满了！");
				pt.popup();
			}
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			mainPage4Index = -1;
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			mainPage4Index = 0;
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (mainPage4Index == -1) {
				if (selectSoldierLocation > 0) {
					selectSoldierLocation--;
				}
				if (selectSoldierindex > 0) {
					selectSoldierindex--;
				}
			} else if (mainPage4Index == 1) {
				mainPage4Index = 0;
			}
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (mainPage4Index == -1) {
				if (selectSoldierLocation < 3) {
					selectSoldierLocation++;
				}
				if (selectSoldierindex < 8) {
					selectSoldierindex++;
				}
			} else if (mainPage4Index == 0) {
				mainPage4Index = 1;
			}
		} else if (key.containsAndRemove(KeyCode.NUM0)) {
			for (int i = 0; i < 4; i++) {
				if (CreatArray.selcetSoldier[i] == -1) {
					PopupText pt = UIResource.getInstance()
							.buildDefaultPopupText();
					pt.setText("兵种不足四个！");
					pt.popup();
					break;
				} else if (i == 3) {
					mainIndex = 2;
					mainPageIndex[4] = 0;
					selectSoldierLocation = 0;
					selectSoldierindex = 0;
				}

			}
		}
	}

	private void handleMainKey2(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			if (mainPageIndex[2] == 0 || mainPageIndex[2] == 1) {
				clearMainImg();
				loadGameImg();
				Map.toaString();
				mapArray = Map.getMap();
				monsterVector.removeAllElements();
				getMonster();
				page = Resource.PAGE_INDEX[2];
				index = 0;
				count = 0;
				indexSelect = -1;
				moveSmall_Index = 4;
				moveSmall_x = 0;
				moveSmall_y = 0;
				hero = new Hero("xulingyun", mainPageIndex[3] + 1, 1,
						Upgrade.upgradeHeroDefence(1, mainPageIndex[3]), 22,
						452, Upgrade.upgradeHeroBlood(1, mainPageIndex[3]), 0,
						0, Upgrade.upgradeHeroBlood(1, mainPageIndex[3]));
				ca = new CreatArray(img, hero);
				idPic = ca.randomArray();
				ca.setIdPic(idPic);
				new AutomaticRemove().start();
				hero.setSrc(img[11]);
				win1 = 0;
				// }else if (mainPageIndex[2] == 1) {

			} else if (mainPageIndex[2] == 2) {
				mainIndex = 3;
			} else if (mainPageIndex[2] == 3) {
				mainIndex = 4;
			}
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			if (mainPageIndex[2] == 0 || mainPageIndex[2] == 2) {
				mainPageIndex[2]++;
			} else if (mainPageIndex[2] == 1 || mainPageIndex[2] == 3) {
				mainPageIndex[2]--;
			}
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			if (mainPageIndex[2] == 0 || mainPageIndex[2] == 2) {
				mainPageIndex[2]++;
			} else if (mainPageIndex[2] == 1 || mainPageIndex[2] == 3) {
				mainPageIndex[2]--;
			}
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (mainPageIndex[2] == 0 || mainPageIndex[2] == 1) {
				mainPageIndex[2] += 2;
			} else if (mainPageIndex[2] == 2 || mainPageIndex[2] == 3) {
				mainPageIndex[2] -= 2;
			}
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (mainPageIndex[2] == 0 || mainPageIndex[2] == 1) {
				mainPageIndex[2] += 2;
			} else if (mainPageIndex[2] == 2 || mainPageIndex[2] == 3) {
				mainPageIndex[2] -= 2;
			}
		} else if (key.containsAndRemove(KeyCode.NUM0)) {
			mainIndex = 1;
			mainPageIndex[2] = 0;
		}
	}

	private void handleMainKey1(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			if (Resource.guanqiaLevel[mainPageIndex[0] * 10 + mainPageIndex[1]] >= 0
					|| isDebugMode())
				mainIndex = 2;
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			if (mainPageIndex[1] > 0)
				mainPageIndex[1]--;
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			if (mainPageIndex[1] < 9)
				mainPageIndex[1]++;
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (mainPageIndex[1] < 5)
				mainPageIndex[1] += 5;
			else
				mainPageIndex[1] -= 5;
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (mainPageIndex[1] < 5)
				mainPageIndex[1] += 5;
			else
				mainPageIndex[1] -= 5;
		} else if (key.containsAndRemove(KeyCode.NUM0)) {
			mainIndex = 0;
			mainPageIndex[1] = 0;
		}
	}

	public void handleMainKey0(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			if (mainPageIndex[0] < 5) {
				mainPageIndex[1] = 0;
				mainIndex = 1;
			} else if (mainPageIndex[0] == 5) {
				int temp_x = 25;
				int temp_y = 100;
				for (int r = 0; r < 12; r++) {
					if (r >= 0 && r < 4) {
						p[r] = new Point(temp_x + 35 + (r % 4) * 80,
								temp_y + 50, r);
					} else if (r >= 4 && r < 7) {
						p[r] = new Point(temp_x + 275, temp_y + 50
								+ ((r % 4) + 1) * 80, r);
					} else if (r >= 7 && r < 10) {
						p[r] = new Point(temp_x + 275 - (r - 6) * 80,
								temp_y + 290, r);
					} else if (r >= 10 && r < 12) {
						p[r] = new Point(temp_x + 35, temp_y + 290 - (r - 9)
								* 80, r);
					}
				}
				isAlertAward = false;
				tempWinNum = 0;
				sumCircleNum = 0;
				stopCount = -1;
				circleNum = 0;
				startAward = false;
				weiyiCount = 0;
				winNum = 0;
				mainIndex = 6;
			}
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			if (mainPageIndex[0] >= 1 && mainPageIndex[0] < 3) {
				mainPageIndex[0]--;
				arrowIndex = 0;
			} else if (mainPageIndex[0] >= 3 && mainPageIndex[0] < 5) {
				mainPageIndex[0]++;
				arrowIndex = 0;
			}
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			if (mainPageIndex[0] >= 0 && mainPageIndex[0] < 2) {
				mainPageIndex[0]++;
				arrowIndex = 0;
			} else if (mainPageIndex[0] > 3 && mainPageIndex[0] <= 5) {
				mainPageIndex[0]--;
				arrowIndex = 0;
			}
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (mainPageIndex[0] == 5) {
				mainPageIndex[0] = 0;
				arrowIndex = 0;
			} else if (mainPageIndex[0] == 4) {
				mainPageIndex[0] = 1;
				arrowIndex = 0;
			} else if (mainPageIndex[0] == 3) {
				mainPageIndex[0] = 2;
				arrowIndex = 0;
			} else if (mainPageIndex[0] == 2) {
				mainPageIndex[0] = 6;
				arrowIndex = 0;
			}
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (mainPageIndex[0] == 0) {
				mainPageIndex[0] = 5;
				arrowIndex = 0;
			} else if (mainPageIndex[0] == 1) {
				mainPageIndex[0] = 4;
				arrowIndex = 0;
			} else if (mainPageIndex[0] == 2) {
				mainPageIndex[0] = 3;
				arrowIndex = 0;
			} else if (mainPageIndex[0] == 6) {
				mainPageIndex[0] = 2;
				arrowIndex = 0;
			}
		} else if (key.containsAndRemove(KeyCode.NUM3)) {
			mainIndex = 7;
		} else if (key.containsAndRemove(KeyCode.NUM4)) {
			mainIndex = 8;
		}
	}

	public void getMonster() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				if (mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] != 0) {
					monsterPic[i][j] = new Monster(
							Resource.MonsterSpeed[mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] - 1],
							Resource.MonsterAttack[mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] - 1],
							mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j],
							img[Resource.MonsterImgIndex[mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] - 1]],
							"Monster",
							Resource.MonsterLevel[mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] - 1],
							i,
							j,
							Resource.MonsterBlood[mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] - 1],
							1,
							0,
							Resource.MonsterBlood[mapArray[mainPageIndex[0]][mainPageIndex[1]][mapIndexOfIndex][i][j] - 1]);
					monsterVector.addElement(monsterPic[i][j]);
				}
			}
		}
		mapIndexOfIndex++;
	}

	public void drawStar(int location) {
		g.drawRegion(img[temp_img_distance1 - 6 + 38], 0, 19 * location, 17,
				19, 0, panle_x + 23 + location % 5 * 50, panle_y + 25
						+ (int) Math.floor(location / 5) * 85, 20);
		if (Resource.guanqiaLevel[mainPageIndex[0] * 10 + location] == -1) {
			g.drawRegion(img[temp_img_distance1 - 6 + 24], 141, 0, 47, 60, 0,
					panle_x + 10 + location % 5 * 50,
					panle_y + 45 + (int) Math.floor(location / 5) * 85, 20);
		} else {
			if (location == 4) {
				g.drawRegion(img[temp_img_distance1 - 6 + 24], 47, 0, 47, 60,
						0, panle_x + 10 + location % 5 * 50, panle_y + 45
								+ (int) Math.floor(location / 5) * 85, 20);
			} else if (location == 9) {
				g.drawRegion(img[temp_img_distance1 - 6 + 24], 94, 0, 47, 60,
						0, panle_x + 10 + location % 5 * 50, panle_y + 45
								+ (int) Math.floor(location / 5) * 85, 20);
			} else {
				g.drawRegion(img[temp_img_distance1 - 6 + 24], 0, 0, 47, 60, 0,
						panle_x + 10 + location % 5 * 50, panle_y + 45
								+ (int) Math.floor(location / 5) * 85, 20);
			}
			for (int yy = 0; yy < Resource.guanqiaLevel[mainPageIndex[0] * 10
					+ location]; yy++) {
				g.drawImage(img[15],
						panle_x + 14 + location % 5 * 50 + 14 * yy, panle_y
								+ 50 + (int) Math.floor(location / 5) * 85, 20);
			}
		}
	}

	public void drawMain() {
		int leftpointx = 20;
		int toppointy = 110;
		arrowIndex++;
		if (arrowIndex == 10000)
			arrowIndex = 0;
		if (mainIndex == -1) {
			g.drawImage(img[temp_img_distance1 - 6 + 32], 0, 0, 20);
			if (arrowIndex % 12 >= 0 && arrowIndex % 12 < 6) {
				g.drawImage(img[temp_img_distance1 - 6 + 33], 200, 480, 20);
			}
		} else if (mainIndex == 0) {
			g.drawImage(img[temp_img_distance1 - 6 + 8], 0, 0, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 36], 10, 180, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 20, 230, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 0, 85, 18, 0, 28,
					235, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 23], 210, 140, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 235, 230, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 18, 85, 18, 0,
					243, 235, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 40], 450, 160, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 465, 230, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 36, 85, 18, 0,
					473, 235, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 30], 510, 330, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 525, 395, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 54, 85, 18, 0,
					533, 400, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 22], 290, 320, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 305, 385, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 72, 85, 18, 0,
					313, 390, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 58], 500, 0, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 510, 125, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 90, 85, 18, 0,
					518, 130, 20);

			g.drawImage(img[temp_img_distance1 - 6 + 21], 0, 350, 20);
			g.drawImage(img[temp_img_distance1 - 6 + 54], 10, 415, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 108, 85, 18, 0,
					18, 420, 20);

			if (mainPageIndex[0] == 0) {
				arrowAnim(9, 20, 190, 0, 0, 0);
			} else if (mainPageIndex[0] == 1) {
				arrowAnim(9, 220, 140, 0, 0, 0);
			} else if (mainPageIndex[0] == 2) {
				arrowAnim(9, 460, 150, 0, 0, 0);
			} else if (mainPageIndex[0] == 3) {
				arrowAnim(9, 520, 320, 0, 0, 0);
			} else if (mainPageIndex[0] == 4) {
				arrowAnim(9, 320, 330, 0, 0, 0);
			} else if (mainPageIndex[0] == 5) {
				arrowAnim(9, 10, 350, 0, 0, 0);
			} else if (mainPageIndex[0] == 6) {
				arrowAnim(9, 520, 85, 0, 0, 0);
			}
			roleInformation();
		} else {
			g.drawImage(img[temp_img_distance1 - 6 + 9], 0, 0, 20);
			if (mainIndex == 1) {
				g.drawImage(img[temp_img_distance1 - 6 + 39], panle_x, panle_y,
						20);
				g.drawImage(img[temp_img_distance1 - 6 + 54], panle_x + 80,
						panle_y - 10, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 27], 0,
						18 * mainPageIndex[0], 85, 18, 0, panle_x + 88,
						panle_y - 5, 20);
				for (int k = 0; k < 10; k++) {
					drawStar(k);
				}
				arrowAnim(9, panle_x, panle_y, 3,
						20 + mainPageIndex[1] % 5 * 50,
						60 + (int) Math.floor(mainPageIndex[1] / 5) * 85);
				g.drawImage(img[10], panle_x + 180, panle_y + 190, 20);
				roleInformation();
			} else if (mainIndex == 2) {
				g.drawImage(img[temp_img_distance1 - 6 + 39], panle_x, panle_y,
						20);
				g.drawImage(img[temp_img_distance1 - 6 + 54], panle_x + 10,
						panle_y - 5, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 27], 0,
						18 * (mainPageIndex[0]), 85, 18, 0, panle_x + 20,
						panle_y, 20);

				g.setColor(159, 125, 64);
				g.drawLine(panle_x + 10, panle_y + 22, 460, 152);
				g.drawRect(panle_x + 12, panle_y + 25, 110, 18);
				g.drawImage(img[temp_img_distance1 - 6 + 25], panle_x + 14,
						panle_y + 29, 20);
				for (int yy = 0; yy < Resource.guanqiaLevel[mainPageIndex[0]
						* 10 + mainPageIndex[1]]; yy++) {
					g.drawImage(img[15], panle_x + 75 + 14 * yy, panle_y + 29,
							20);
				}

				g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0, 47, 29, 0,
						panle_x + 140, panle_y + 23, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0, 47, 29, 0,
						panle_x + 210, panle_y + 23, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 18], 0, 20, 40, 20,
						0, panle_x + 142, panle_y + 25, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 18], 0, 0, 40, 20, 0,
						panle_x + 212, panle_y + 25, 20);
				g.drawLine(panle_x + 10, panle_y + 52, 460, 182);
				g.drawImage(img[temp_img_distance1 - 6 + 34], panle_x + 30,
						panle_y + 56, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 35], panle_x + 160,
						panle_y + 56, 20);
				g.drawImage(img[14], panle_x + 30, panle_y + 80, 20);
				g.drawRegion(img[11], 59 * selectHeroIndex, 0, 59, 59, 0,
						panle_x + 30, panle_y + 80, 20);
				for (int f = 0; f < 4; f++) {
					g.drawImage(img[14], panle_x + 130 + f % 2 * 65, panle_y
							+ 70 + f / 2 * 65, 20);
					g.drawRegion(img[CreatArray.selcetSoldier[f]], 0, 0, 62,
							62, 0, panle_x + 130 + f % 2 * 65, panle_y + 70 + f
									/ 2 * 65, 20);
				}
				g.drawImage(img[10], panle_x + 20, panle_y + 170, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 16], 0, 0, 95, 29, 0,
						panle_x + 40, panle_y + 205, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 16], 0, 0, 95, 29, 0,
						panle_x + 160, panle_y + 205, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 19], 0, 0, 80, 20, 0,
						panle_x + 47, panle_y + 210, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 19], 0, 20, 80, 20,
						0, panle_x + 167, panle_y + 210, 20);
				roleInformation();
				if (mainPageIndex[2] == 0)
					arrowAnim(9, panle_x, panle_y, 3, 149, 20);
				else if (mainPageIndex[2] == 1)
					arrowAnim(9, panle_x, panle_y, 3, 219, 20);
				else if (mainPageIndex[2] == 2)
					arrowAnim(9, panle_x, panle_y, 3, 54, 200);
				else if (mainPageIndex[2] == 3)
					arrowAnim(9, panle_x, panle_y, 3, 177, 200);
			} else if (mainIndex == 3) {
				g.drawImage(img[temp_img_distance1 - 6 + 39], panle_x, panle_y,
						20);
				roleInformation();
				g.drawImage(img[temp_img_distance1 - 6 + 10], leftpointx,
						toppointy, 20);
				for (int s = 0; s < 18; s++) {
					g.drawImage(img[temp_img_distance1 - 6 + 11], leftpointx
							+ 46 + s * 30, toppointy, 20);
				}
				g.drawImage(img[temp_img_distance1 - 6 + 12], leftpointx + 556,
						toppointy, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 13], 0, 0, 82, 84, 0,
						leftpointx - 5, toppointy - 5, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 13], 82, 0, 81, 84,
						0, leftpointx + 526, toppointy - 5, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 56], leftpointx + 216,
						toppointy - 54, 20);
				for (int s = 0; s < 6; s++) {
					g.drawImage(img[14], leftpointx + 43 + 90 * s,
							toppointy + 40, 20);
					g.drawRegion(img[11], s * 62, 0, 62, 62, 0, leftpointx + 43
							+ 90 * s + 1, toppointy + 40 + 1, 20);
					if (mainPageIndex[3] == s) {
						g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0,
								47, 29, 0, leftpointx + 43 + 90 * s + 3,
								toppointy + 101, 20);
						g.drawRegion(img[temp_img_distance1 - 6 + 20], 0, 0,
								40, 20, 0, leftpointx + 43 + 90 * s + 5,
								toppointy + 106, 20);
						g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0,
								47, 29, 0, leftpointx + 43 + 90 * s + 3,
								toppointy + 130, 20);
						g.drawRegion(img[temp_img_distance1 - 6 + 18], 0, 20,
								40, 20, 0, leftpointx + 43 + 90 * s + 5,
								toppointy + 135, 20);
					}
				}
				g.drawRegion(img[temp_img_distance1 - 6 + 28],
						mainPageIndex[3] * 142, 0, 142, 161, 0,
						leftpointx + 70, toppointy + 190, 20);
				if (mainPageIndex[3] < 6 && mainPage3Index == -1) {
					if (arrowIndex % 16 >= 0 && arrowIndex % 16 < 4)
						g.drawRegion(img[9], 0, 0, 50, 44, 3, leftpointx + 62
								+ 90 * mainPageIndex[3], toppointy + 60, 20);
					else if (arrowIndex % 16 >= 4 && arrowIndex % 16 < 8)
						g.drawRegion(img[9], 50, 0, 50, 44, 3, leftpointx + 62
								+ 90 * mainPageIndex[3], toppointy + 60, 20);
					else if (arrowIndex % 16 >= 8 && arrowIndex % 16 < 12)
						g.drawRegion(img[9], 100, 0, 50, 44, 3, leftpointx + 62
								+ 90 * mainPageIndex[3], toppointy + 60, 20);
					else if (arrowIndex % 16 >= 12 && arrowIndex % 16 < 16)
						g.drawRegion(img[9], 150, 0, 50, 44, 3, leftpointx + 62
								+ 90 * mainPageIndex[3], toppointy + 60, 20);
				} else {
					if (mainPage3Index == 0) {
						arrowAnim(9, leftpointx, toppointy, 3, 80, 300);
					} else if (mainPage3Index == 1) {
						arrowAnim(9, leftpointx, toppointy, 3, 80, 330);
					}
				}
				g.drawImage(img[temp_img_distance1 - 6 + 39], leftpointx + 290,
						toppointy + 160, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 26], leftpointx + 300,
						toppointy + 170, 20);

				int valueClour = 0x000000;
				setColour(valueClour);
				g.drawString(1 + "", leftpointx + 448, toppointy + 183, 20);
				g.drawString(Upgrade.upgradeHeroDefence(1, mainPageIndex[3])
						+ "", leftpointx + 448, toppointy + 214, 20);
				g.drawString(
						Upgrade.upgradeHeroBlood(1, mainPageIndex[3]) + "",
						leftpointx + 448, toppointy + 245, 20);
				g.drawString(Resource.heroSkillInfo[mainPageIndex[3]],
						leftpointx + 448, toppointy + 276, 20);
				g.drawImage(img[10], panle_x + 300, panle_y + 360, 20);
			} else if (mainIndex == 4) {
				roleInformation();
				g.drawImage(img[temp_img_distance1 - 6 + 10], leftpointx,
						toppointy, 20);
				for (int s = 0; s < 18; s++) {
					g.drawImage(img[temp_img_distance1 - 6 + 11], leftpointx
							+ 46 + s * 30, toppointy, 20);
				}
				g.drawImage(img[temp_img_distance1 - 6 + 12], leftpointx + 556,
						toppointy, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 13], 0, 0, 82, 84, 0,
						leftpointx - 5, toppointy - 5, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 13], 82, 0, 81, 84,
						0, leftpointx + 526, toppointy - 5, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 55], leftpointx + 216,
						toppointy - 54, 20);
				g.drawRegion(img[temp_img_distance1 - 6 + 57], 0, 0, 54, 23, 0,
						leftpointx + 40, toppointy + 33, 20);
				for (int s = 0; s < 4; s++) {
					g.drawImage(img[14], leftpointx + 40, toppointy + 66 + 70
							* s, 20);
					if (s == selectSoldierLocation)
						g.drawImage(img[13], leftpointx + 39, toppointy + 66
								+ 70 * selectSoldierLocation, 20);
					g.drawRegion(img[selectSoldierindex + s
							- selectSoldierLocation], 0, 0, 62, 62, 0,
							leftpointx + 40, toppointy + 66 + 70 * s, 20);
					g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0, 47,
							29, 0, leftpointx + 110, toppointy + 66 + 70 * s,
							20);
					g.drawRegion(img[temp_img_distance1 - 6 + 20], 0, 00, 40,
							20, 0, leftpointx + 113, toppointy + 71 + 70 * s,
							20);
					g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0, 47,
							29, 0, leftpointx + 110, toppointy + 96 + 70 * s,
							20);
					g.drawRegion(img[temp_img_distance1 - 6 + 18], 0, 20, 40,
							20, 0, leftpointx + 113, toppointy + 101 + 70 * s,
							20);
				}
				if (mainPage4Index != -1) {
					if (mainPage4Index == 0) {
						arrowAnim(9, leftpointx, toppointy, 3, 120,
								65 + 70 * selectSoldierLocation);
					} else if (mainPage4Index == 1) {
						arrowAnim(9, leftpointx, toppointy, 3, 120,
								95 + 70 * selectSoldierLocation);
					}
				}
				g.drawRegion(img[temp_img_distance1 - 6 + 57], 0, 24, 54, 23,
						0, leftpointx + 40, toppointy + 346, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 39], leftpointx + 290,
						toppointy + 35, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 14], leftpointx + 300,
						toppointy + 45, 20);
				int valueClour = 0x000000;
				setColour(valueClour);
				g.drawString(1 + "", leftpointx + 445, toppointy + 45, 20);
				g.drawString(
						Upgrade.upgradeSoldierAttack(0, selectSoldierindex)
								+ "", leftpointx + 445, toppointy + 77, 20);
				g.drawString(
						Upgrade.upgradeSoldierAttack(0, selectSoldierindex)
								+ "", leftpointx + 445, toppointy + 110, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 42], leftpointx + 396,
						toppointy + 265, 20);
				for (int s = 0; s < 4; s++) {
					g.drawImage(img[14], leftpointx + 300 + 70 * s,
							toppointy + 300, 20);
					if (CreatArray.selcetSoldier[s] != -1)
						g.drawRegion(img[CreatArray.selcetSoldier[s]], 0, 0,
								62, 62, 0, leftpointx + 300 + 70 * s,
								toppointy + 300, 20);
				}
				g.drawImage(img[10], panle_x + 300, panle_y + 340, 20);
			} else if (mainIndex == 7) {
				drawShop();
			} else if (mainIndex == 6) {
				drawAward();
			} else if (mainIndex == 8) {
				drawAttainment();
			}
		}
	}

	private void roleInformation() {
		g.drawImage(img[temp_img_distance1 - 6 + 29], 0, 0, 20);
		int color = 0xffff00;
		setColour(color);
		TextView.showSingleLineText(g, my.getLevel() + "", 100, 25);
		TextView.showSingleLineText(g,
				my.getExp() + "/" + Upgrade.upgradeLeader(my.getLevel() + 1),
				150, 25);
		TextView.showSingleLineText(g, my.getMoney() + "", 350, 25);
	}

	private void drawAttainment() {

	}

	public int probability() {
		int i = RandomValue.getRandInt(0, 100);
		int winNums;
		if (i >= 0 && i < 10) {
			winNums = 0;
		} else if (i >= 10 && i < 15) {
			winNums = 1;
		} else if (i >= 15 && i < 30) {
			winNums = 2;
		} else if (i >= 30 && i < 35) {
			winNums = 3;
		} else if (i >= 35 && i < 45) {
			winNums = 4;
		} else if (i >= 45 && i < 50) {
			winNums = 5;
		} else if (i >= 50 && i < 65) {
			winNums = 6;
		} else if (i >= 65 && i < 70) {
			winNums = 7;
		} else if (i >= 70 && i < 80) {
			winNums = 8;
		} else if (i >= 80 && i < 85) {
			winNums = 9;
		} else if (i >= 85 && i < 95) {
			winNums = 10;
		} else {
			winNums = 11;
		}
		return winNums;
	}

	private void handleRewardKey(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			if (startAward)
				return;
			else {
				if (isAlertAward) {
					isAlertAward = false;
					awardCount = 0;
				} else {
					startAward = true;
					winNum = probability();
					int temp = winNum - tempWinNum;
					if (temp < 0) {
						temp = 12 - (tempWinNum - winNum);
					}
					sumCircleNum = 24 + temp;
				}
			}
		} else if (key.containsAndRemove(KeyCode.NUM0)) {
			if (startAward)
				return;
			if (!isAlertAward) {
				mainIndex = 0;
			}
		}
	}

	public void drawAward() {
		int temp_x = 25;
		int temp_y = 100;
		int i = 0;
		g.drawImage(img[temp_img_distance1 - 6 + 7], 0, 0, 20);
		g.drawImage(img[temp_img_distance1 - 6 + 10], temp_x, temp_y, 20);
		for (i = 0; i < 9; i++) {
			g.drawImage(img[temp_img_distance1 - 6 + 11], temp_x + 46 + i * 30,
					temp_y, 20);
		}
		g.drawImage(img[temp_img_distance1 - 6 + 12], temp_x + 316, temp_y, 20);
		for (i = 0; i < 12; i++) {
			g.drawImage(img[14], p[i].getX(), p[i].getY(), 20);
			if (i == 0) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, p[i].getX() + 6,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 18,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 30,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 42,
						p[i].getY() + 10, 20);
				g.drawImage(img[17], p[i].getX() + 12, p[i].getY() + 30, 20);// 1000
			} else if (i == 1) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 4 * 62, 0, 62,
						62, 0, p[i].getX(), p[i].getY(), 20);// 双倍金币
			} else if (i == 2) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, p[i].getX() + 0,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 12,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 24,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 36,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 48,
						p[i].getY() + 10, 20);
				g.drawImage(img[17], p[i].getX() + 12, p[i].getY() + 30, 20);// 10000
			} else if (i == 3) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 3 * 62, 0, 62,
						62, 0, p[i].getX(), p[i].getY(), 20);// 双倍经验
			} else if (i == 4) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, p[i].getX() + 6,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 18,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 30,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 42,
						p[i].getY() + 10, 20);// 1000
				g.drawImage(img[17], p[i].getX() + 12, p[i].getY() + 30, 20);
			} else if (i == 5) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 9 * 62, 0, 62,
						62, 0, p[i].getX(), p[i].getY(), 20);// 生命谐音
			} else if (i == 6) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, p[i].getX() + 0,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 12,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 24,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 36,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 48,
						p[i].getY() + 10, 20);
				g.drawImage(img[17], p[i].getX() + 12, p[i].getY() + 30, 20);// 10000
			} else if (i == 7) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 6 * 62, 0, 62,
						62, 0, p[i].getX(), p[i].getY(), 20);// 保护卡
			} else if (i == 8) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, p[i].getX() + 6,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 18,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 30,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 42,
						p[i].getY() + 10, 20);// 1000
				g.drawImage(img[17], p[i].getX() + 12, p[i].getY() + 30, 20);
			} else if (i == 9) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 7 * 62, 0, 62,
						62, 0, p[i].getX(), p[i].getY(), 20);// 军团之力
			} else if (i == 10) {
				g.drawRegion(img[16], 24, 0, 12, 16, 0, p[i].getX() + 0,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 12,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 24,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 36,
						p[i].getY() + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, p[i].getX() + 48,
						p[i].getY() + 10, 20);
				g.drawImage(img[17], p[i].getX() + 12, p[i].getY() + 30, 20);// 20000
			} else if (i == 11) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 8 * 62, 0, 62,
						62, 0, p[i].getX(), p[i].getY(), 20);// 刚一副护
			}
		}
		if (startAward) {
			if (tempWinNum >= 0 && tempWinNum < 3) {
				weiyiCount++;
				DrawUtil.drawRect(g, p[tempWinNum].getX() + weiyiCount * 80,
						p[tempWinNum].getY(), 61, 61, 3, 0xffff00);
				if (p[tempWinNum].getX() + weiyiCount * 80 == p[tempWinNum + 1]
						.getX()) {
					weiyiCount--;
					stopTime();
				}
			} else if (tempWinNum >= 6 && tempWinNum < 9) {
				weiyiCount--;
				DrawUtil.drawRect(g, p[tempWinNum].getX() + weiyiCount * 80,
						p[tempWinNum].getY(), 61, 61, 3, 0xffff00);
				if (p[tempWinNum].getX() + weiyiCount * 80 == p[tempWinNum + 1]
						.getX()) {
					weiyiCount++;
					stopTime();
				}
			} else if (tempWinNum >= 3 && tempWinNum < 6) {
				weiyiCount++;
				DrawUtil.drawRect(g, p[tempWinNum].getX(), p[tempWinNum].getY()
						+ weiyiCount * 80, 61, 61, 3, 0xffff00);
				if (p[tempWinNum].getY() + weiyiCount * 80 == p[tempWinNum + 1]
						.getY()) {
					weiyiCount--;
					stopTime();
				}
			} else if (tempWinNum >= 9 && tempWinNum < 12) {
				weiyiCount--;
				DrawUtil.drawRect(g, p[tempWinNum].getX(), p[tempWinNum].getY()
						+ weiyiCount * 80, 61, 61, 3, 0xffff00);
				if (tempWinNum != 11
						&& p[tempWinNum].getY() + weiyiCount * 80 == p[tempWinNum + 1]
								.getY()) {
					weiyiCount++;
					stopTime();
				} else if (p[11].getY() + weiyiCount * 80 == p[0].getY()) {
					weiyiCount++;
					stopTime();
				}
			}
		} else {
			DrawUtil.drawRect(g, p[winNum].getX(), p[winNum].getY(), 61, 61, 3,
					0xffff00);
		}
		g.drawImage(img[temp_img_distance1], p[1].getX() + 15,
				p[11].getY() + 15, 20);
		g.drawRegion(img[temp_img_distance1 - 6 + 59], 0, 0, 81, 19, 0,
				p[1].getX() + 30, p[11].getY() + 35, 20);
		if (awardCount == 1) {
			g.drawRegion(img[temp_img_distance1 - 6 + 59], 0, 19, 81, 19, 0,
					p[1].getX() + 30, p[11].getY() + 75, 20);
		} else {
			g.drawRegion(img[temp_img_distance1 - 6 + 59], 0, 38, 81, 19, 0,
					p[1].getX() + 30, p[11].getY() + 75, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 59], 0, 57, 81, 19, 0,
					p[1].getX() + 30, p[11].getY() + 95, 20);
		}
		setColour(0x000000);
		TextView.showSingleLineText(g, "今日免费抽奖次数：", p[1].getX() + 25,
				p[7].getY() + 70);
		g.drawImage(img[temp_img_distance1 - 6 + 53], temp_x + 380, temp_y, 20);
		g.drawRegion(img[temp_img_distance1 - 6 + 27], 0, 108, 85, 18, 0,
				temp_x + 430, temp_y + 20, 20);
		TextView.showMultiLineText(g, "    玩家每天有1次免费寻宝的机会。", 5, temp_x + 400,
				temp_y + 50, 180, 30);
		TextView.showMultiLineText(g, "    得到的道具会自动添加到商城个人储物箱中。祝大家好运。", 5,
				temp_x + 400, temp_y + 100, 180, 30);
		if (isAlertAward) {
			g.drawImage(img[temp_img_distance1 - 6 + 39], 180, 150, 20);
			TextView.showSingleLineText(g, "获得：", 240, 260);
			if (winNum == 0) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, 280 + 6, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 18, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 30, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 42, 240 + 10, 20);
				g.drawImage(img[17], 280 + 12, 240 + 30, 20);// 1000
			} else if (winNum == 1) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 4 * 62, 0, 62,
						62, 0, 280, 240, 20);// 双倍金币
			} else if (winNum == 2) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, 280 + 0, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 12, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 24, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 36, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 48, 240 + 10, 20);
				g.drawImage(img[17], 280 + 12, 240 + 30, 20);// 10000
			} else if (winNum == 3) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 3 * 62, 0, 62,
						62, 0, 280, 240, 20);// 双倍经验
			} else if (winNum == 4) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, 280 + 6, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 18, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 30, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 42, 240 + 10, 20);// 1000
				g.drawImage(img[17], 280 + 12, 240 + 30, 20);
			} else if (winNum == 5) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 9 * 62, 0, 62,
						62, 0, 280, 240, 20);// 生命谐音
			} else if (winNum == 6) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, 280 + 0, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 12, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 24, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 36, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 48, 240 + 10, 20);
				g.drawImage(img[17], 280 + 12, 240 + 30, 20);// 10000
			} else if (winNum == 7) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 6 * 62, 0, 62,
						62, 0, 280, 240, 20);// 保护卡
			} else if (i == 8) {
				g.drawRegion(img[16], 12, 0, 12, 16, 0, 280 + 6, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 18, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 30, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 42, 240 + 10, 20);// 1000
				g.drawImage(img[17], 280 + 12, 240 + 30, 20);
			} else if (winNum == 9) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 7 * 62, 0, 62,
						62, 0, 280, 240, 20);// 军团之力
			} else if (winNum == 10) {
				g.drawRegion(img[16], 24, 0, 12, 16, 0, 280 + 0, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 12, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 24, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 36, 240 + 10, 20);
				g.drawRegion(img[16], 0, 0, 12, 16, 0, 280 + 48, 240 + 10, 20);
				g.drawImage(img[17], 280 + 12, 240 + 30, 20);// 20000
			} else if (winNum == 11) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 8 * 62, 0, 62,
						62, 0, 280, 240, 20);// 刚一副护
			}
		}
		g.drawImage(img[10], 500, 480, 20);
	}

	int awardCount = 1;

	private void stopTime() {
		if (circleNum <= sumCircleNum - 7) {
			circleNum++;
			weiyiCount = 0;
			stopCount = -1;
			if (tempWinNum == 11)
				tempWinNum = 0;
			else
				tempWinNum += 1;
		} else if (stopCount == -1) {
			stopCount = RandomValue.getRandInt(0, 2);
		} else if (stopCount == 2) {
			if (tempWinNum == 11)
				tempWinNum = 0;
			else
				tempWinNum += 1;
			circleNum++;
			weiyiCount = 0;
			stopCount = -1;
		} else
			stopCount++;
		if (sumCircleNum == circleNum) {
			isAlertAward = true;
			startAward = false;
			tempWinNum = winNum % 12;
			circleNum = 0;
		}
	}

	int selectShopBigIndex = 0;
	int selectShopSmallIndex = -1;

	public void drawShop() {
		int temp_x = 10;
		int temp_y = 150;
		int i = 0;
		roleInformation();
		g.drawImage(img[temp_img_distance1 - 6 + 45], temp_x, temp_y, 20);
		for (i = 0; i < 35; i++) {
			g.drawImage(img[temp_img_distance1 - 6 + 46], temp_x + 25 + i * 10,
					temp_y, 20);
		}
		g.drawImage(img[temp_img_distance1 - 6 + 47], temp_x + 365, temp_y, 20);
		for (i = 0; i < 2; i++) {
			if (selectShopBigIndex == i) {
				g.drawRegion(img[temp_img_distance1 - 6 + 48], 0, 0, 96, 50, 0,
						temp_x + 96 * i, temp_y - 30, 20);
			} else {
				g.drawRegion(img[temp_img_distance1 - 6 + 48], 97, 0, 96, 50,
						0, temp_x + 96 * i, temp_y - 30, 20);
			}
			g.drawRegion(img[temp_img_distance1 - 6 + 49], 0, 20 * i, 80, 20,
					0, temp_x + 15 + 96 * i, temp_y - 20, 20);
		}

		if (shopPage * 6 - 6 >= 0) {
			g.drawRegion(img[temp_img_distance1 - 6 + 31], 0, 0, 23, 54, 0,
					temp_x + 5, temp_y + 117, 20);
		}
		if (shopSum - (shopPage + 1) * 6 > 0)
			g.drawRegion(img[temp_img_distance1 - 6 + 31], 24, 0, 23, 54, 0,
					temp_x + 365, temp_y + 117, 20);

		int temp = (shopSum - shopPage * 6) > 6 ? 6 : (shopSum - shopPage * 6);
		for (i = 0; i < temp; i++) {
			g.drawImage(img[temp_img_distance1 - 6 + 44], temp_x + 33 + (i % 2)
					* 165, temp_y + 40 + i / 2 * 65, 20);
			g.drawRegion(img[temp_img_distance1 - 6 + 51],
					(shopPage * 6 + i) * 62, 0, 62, 62, 0, temp_x + 33
							+ (i % 2) * 165, temp_y + 40 + i / 2 * 65, 20);
		}
		if (selectShopSmallIndex == -1) {
			arrowAnim(9, temp_x, temp_y, 3, 25 + 96 * selectShopBigIndex, -20);
		} else {
			if (selectShopSmallIndex < 6)
				arrowAnim(9, temp_x, temp_y, 3,
						155 + selectShopSmallIndex % 2 * 165,
						75 + 62 * (selectShopSmallIndex / 2));
			else {
				arrowAnim(9, temp_x, temp_y, 3,
						155 + (selectShopSmallIndex - 6) % 2 * 165,
						75 + 62 * ((selectShopSmallIndex - 6) / 2));
			}
		}
		if (selectShopSmallIndex != -1) {
			g.drawImage(img[temp_img_distance1 - 6 + 39], temp_x + 390, temp_y,
					20);
			TextView.showSingleLineText(g,
					Resource.shopInfo[(selectShopSmallIndex) * 2],
					temp_x + 490, temp_y + 10);
			TextView.showSingleLineText(g,
					Resource.shopInfo[(selectShopSmallIndex) * 2 + 1],
					temp_x + 430, temp_y + 40);
		}
		for (int kl = 0; kl < 5; kl++) {
			g.drawImage(img[temp_img_distance1 - 6 + 52], kl * 144, 420, 20);
		}
		g.drawImage(img[temp_img_distance1 - 6 + 43], 0, 420, 20);
		if (canGoodsKind > 5) {

		}
		int canGoodsIndex = 0;
		for (int kj = 0; kj < Resource.goodsNums.length; kj++) {
			if (Resource.goodsNums[kj] > 0) {
				g.drawRegion(img[temp_img_distance1 - 6 + 51], 62 * kj, 0, 62,
						62, 0, 170 + canGoodsIndex * 80, 430, 20);
				g.drawImage(img[temp_img_distance1 - 6 + 50],
						170 + canGoodsIndex * 80 + 40, 430, 20);
				canGoodsIndex++;
			}
		}
	}

	int money = 0;
	int shopPage;
	int shopSum;

	private void handleMainKey7(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			PopupConfirm pc = UIResource.getInstance()
					.buildDefaultPopupConfirm();
			pc.setText("是否购买道具");
			if (pc.popup() == 0) {
				boolean flag = pm.buyProp(138, 1, g);
				if(flag){
					PlayerProp props = pm.getPropById(135 + selectShopSmallIndex);
					props.setNums(props.getNums() + 1);
					Resource.goodsNums[selectShopSmallIndex]++;
				}
			}
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			if (selectShopSmallIndex == -1) {
				if (selectShopBigIndex == 1) {
					selectShopBigIndex = 0;
					shopSum = 11;
					shopPage = 0;
				}
			} else {
				if (selectShopSmallIndex == 1 || selectShopSmallIndex == 3
						|| selectShopSmallIndex == 5
						|| selectShopSmallIndex == 7
						|| selectShopSmallIndex == 9
						|| selectShopSmallIndex == 11) {
					selectShopSmallIndex--;
					if (selectShopSmallIndex > shopSum - 1) {
						selectShopSmallIndex++;
					}
				} else if (selectShopSmallIndex == 6
						|| selectShopSmallIndex == 8
						|| selectShopSmallIndex == 10) {
					shopPage -= 1;
					selectShopSmallIndex -= 5;
					if (selectShopSmallIndex > shopSum - 1) {
						selectShopSmallIndex += 5;
						shopPage += 1;
					}
				}
			}
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			if (selectShopSmallIndex == -1) {
				if (selectShopBigIndex == 0) {
					selectShopBigIndex = 1;
					shopSum = 3;
					shopPage = 0;
				}
			} else {
				if (selectShopSmallIndex == 0 || selectShopSmallIndex == 2
						|| selectShopSmallIndex == 4
						|| selectShopSmallIndex == 6
						|| selectShopSmallIndex == 8
						|| selectShopSmallIndex == 10) {
					selectShopSmallIndex++;
					if (selectShopSmallIndex > shopSum - 1) {
						selectShopSmallIndex--;
					}
				} else if (selectShopSmallIndex == 1
						|| selectShopSmallIndex == 3
						|| selectShopSmallIndex == 5) {
					shopPage += 1;
					selectShopSmallIndex += 5;
					if (selectShopSmallIndex > shopSum - 1) {
						selectShopSmallIndex -= 5;
						shopPage -= 1;
					}
				}
			}
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (selectShopSmallIndex != -1) {
				if (selectShopSmallIndex != 0 && selectShopSmallIndex != 1
						&& selectShopSmallIndex != 6
						&& selectShopSmallIndex != 7) {
					selectShopSmallIndex -= 2;
					if (selectShopSmallIndex > shopSum - 1) {
						selectShopSmallIndex += 2;
					}
				} else {
					selectShopSmallIndex = -1;
				}
			}
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (selectShopSmallIndex == -1) {
				selectShopSmallIndex = 0;
			} else {
				if (selectShopSmallIndex != 4 && selectShopSmallIndex != 5
						&& selectShopSmallIndex != 10
						&& selectShopSmallIndex != 11)
					selectShopSmallIndex += 2;
				if (selectShopSmallIndex > shopSum - 1) {
					selectShopSmallIndex -= 2;
				}
			}
		} else if (key.containsAndRemove(KeyCode.NUM0)) {
			mainIndex = 0;
			shopPage = 0;
		}
	}

	private void arrowAnim(int temp, int leftpointx, int toppointy, int angle,
			int num1, int num2) {
		if (arrowIndex % 16 >= 0 && arrowIndex % 16 < 4)
			g.drawRegion(img[temp], 0, 0, 50, 44, angle, leftpointx + num1,
					toppointy + num2, 20);
		else if (arrowIndex % 16 >= 4 && arrowIndex % 16 < 8)
			g.drawRegion(img[temp], 50, 0, 50, 44, angle, leftpointx + num1,
					toppointy + num2, 20);
		else if (arrowIndex % 16 >= 8 && arrowIndex % 16 < 12)
			g.drawRegion(img[temp], 100, 0, 50, 44, angle, leftpointx + num1,
					toppointy + num2, 20);
		else if (arrowIndex % 16 >= 12 && arrowIndex % 16 < 16)
			g.drawRegion(img[temp], 150, 0, 50, 44, angle, leftpointx + num1,
					toppointy + num2, 20);
	}

	public void drawWin() {
		gameIndex = 2;
		g.drawImage(img[temp_img_distance1 - 6 + 39], panle_x, panle_y, 20);
		int valueClour = 0x000000;
		setColour(valueClour);
		TextView.showSingleLineText(g, "战斗胜利", panle_x + 100, panle_y + 20);
		DrawUtil.drawRect(g, panle_x + 50, panle_y + 50, 130, 23, 2, 0);
		int tempStar = 0;
		if (hero.getBoold() >= hero.getBoolds() * 0.8)
			tempStar = 3;
		else if (hero.getBoold() >= hero.getBoolds() * 0.2
				&& hero.getBoold() < hero.getBoolds() * 0.8)
			tempStar = 2;
		else
			tempStar = 1;
		for (int i = 0; i < tempStar; i++) {
			g.drawImage(img[15], panle_x + 80 + i * 40, panle_y + 54, 20);
		}
		TextView.showSingleLineText(g, "获得的经验：" + winExp, panle_x + 80,
				panle_y + 120);
		TextView.showSingleLineText(g, "获得的金币：" + winMoney, panle_x + 80,
				panle_y + 140);

		if (Resource.guanqiaLevel[mainPageIndex[0] + mainPageIndex[1]] < tempStar)
			Resource.guanqiaLevel[mainPageIndex[0] + mainPageIndex[1]] = (byte) tempStar;
		if (Resource.guanqiaLevel[mainPageIndex[0] + mainPageIndex[1] + 1] == -1)
			Resource.guanqiaLevel[mainPageIndex[0] + mainPageIndex[1] + 1] = 0;
		g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0, 47, 29, 0,
				panle_x + 117, panle_y + 175, 20);
		g.drawRegion(img[temp_img_distance1 - 6 + 41], 0, 0, 40, 20, 0,
				panle_x + 120, panle_y + 180, 20);
	}

	public void drawFail() {
		gameIndex = 1;
		g.drawImage(img[temp_img_distance1 - 6 + 39], panle_x, panle_y, 20);
		int valueClour = 0x000000;
		setColour(valueClour);
		TextView.showSingleLineText(g, "战斗失败", panle_x + 100, panle_y + 20);
		TextView.showSingleLineText(g, "快去升级士兵和英雄来提示自己的实力吧。", panle_x + 80,
				panle_y + 140);
		g.drawRegion(img[temp_img_distance1 - 6 + 15], 0, 0, 47, 29, 0,
				panle_x + 117, panle_y + 175, 20);
		g.drawRegion(img[temp_img_distance1 - 6 + 41], 0, 0, 40, 20, 0,
				panle_x + 120, panle_y + 180, 20);
	}

	boolean bossStart = false;
	int bossMoveindex = 0;

	public void drawGame() {
		if (System.currentTimeMillis() - hero.getNegativeTime() > 10000) {
			hero.setDefenceCoefficient(1.0f);
		}
		if (moveSmall_Index < 4) {
			if (moveSmall_Index == 0) {
				moveSmall_x = 4;
				moveSmall_y = -4;
			} else if (moveSmall_Index == 1) {
				moveSmall_x = -4;
				moveSmall_y = 4;
			} else if (moveSmall_Index == 2) {
				moveSmall_x = 4;
				moveSmall_y = 4;
			} else if (moveSmall_Index == 3) {
				moveSmall_x = -4;
				moveSmall_y = -4;
			}
		} else if (moveSmall_Index == 4) {
			moveSmall_x = 0;
			moveSmall_y = 0;
		}
		g.drawImage(img[temp_img_distance2 + mainPageIndex[0]], moveSmall_x,
				moveSmall_y, 20);
		drawMonster();
		drawSoldier();
		drawMoveSoldier();
		if (bossStart) {
			for (int kg = 0; kg < monsterVector.size(); kg++) {
				if (((Monster) monsterVector.elementAt(kg)).getKind() == 14)
					drawBoss1Bullet();
				else if (((Monster) monsterVector.elementAt(kg)).getKind() == 15)
					drawBoss2Bullet();
				else if (((Monster) monsterVector.elementAt(kg)).getKind() == 16)
					drawBoss3Bullet();
				else if (((Monster) monsterVector.elementAt(kg)).getKind() == 17)
					drawBoss4Bullet();
				else if (((Monster) monsterVector.elementAt(kg)).getKind() == 18)
					drawBoss5Bullet();
				else if (((Monster) monsterVector.elementAt(kg)).getKind() == 19)
					drawBoss6Bullet();
			}
		}
		for (int i = 0; i < v1a.size();) {
			if (v1a.elementAt(i) != null) {
				monsterHurtAnim1((Monster) v1a.elementAt(i), i, v1a);
			}
		}
		for (int i = 0; i < v2a.size();) {
			if (v2a.elementAt(i) != null) {
				monsterHurtAnim1((Monster) v2a.elementAt(i), i, v2a);
			}
		}
		for (int i = 0; i < v3a.size();) {
			if (v3a.elementAt(i) != null) {
				monsterHurtAnim3((Monster) v3a.elementAt(i), i);
			}
		}

		for (int i = 0; i < v4a.size();) {
			if (v4a.elementAt(i) != null) {
				monsterHurtAnim1((Monster) v4a.elementAt(i), i, v4a);
			}
		}

		for (int i = 0; i < v5a.size();) {
			if (v5a.elementAt(i) != null) {
				monsterHurtAnim5((Monster) v5a.elementAt(i), i);
			}
		}

		for (int i = 0; i < v6a.size();) {
			if (v6a.elementAt(i) != null) {
				monsterHurtAnim1((Monster) v6a.elementAt(i), i, v6a);
			}
		}
		for (int i = 0; i < v7a.size();) {
			if (v7a.elementAt(i) != null) {
				monsterHurtAnim7((Monster) v7a.elementAt(i), i);
			}
		}
		if (v8a.size() != 0) {
			for (int i = 0; i < v8a.size();) {
				if (v8a.elementAt(i) != null) {
					monsterHurtAnim8((Monster) v8a.elementAt(i), i);
				}
			}
		}
		for (int i = 0; i < v9a.size();) {
			if (v9a.elementAt(i) != null) {
				monsterHurtAnim9((Monster) v9a.elementAt(i), i);
			}
		}
		for (int i = 0; i < deathArray.size();) {
			if (deathArray.elementAt(i) != null) {
				drawDeath((Monster) deathArray.elementAt(i), i);
			}
		}

		for (int i = 0; i < criticalArray.size();) {
			if (criticalArray.elementAt(i) != null) {
				i = showHurtAnim((Critical) criticalArray.elementAt(i), i,
						criticalArray, 0);
			}
		}
		for (int i = 0; i < criticalArray1.size();) {
			if (criticalArray1.elementAt(i) != null) {
				i = showHurtAnim((Critical) criticalArray1.elementAt(i), i,
						criticalArray1, 1);
			}
		}
		for (int kk = 0; kk < IceDrowVector.size();) {
			kk = drawIceDrow((IceDrow) IceDrowVector.elementAt(kk), kk);
		}
		for (int kk = 0; kk < stickVector.size();) {
			kk = drawStick((Bullet) stickVector.elementAt(kk), kk);

		}
		drawMonsterBullet1();
		drawSoldierBullet1();
		drawSoldierBullet2();
		drawSoldierBullet3();
		drawSoldierBullet4();
		if (moveSmall_Index < 4) {
			drawSwordScar();
			StrikeHero.getInstance().setIndexAnim(
					StrikeHero.getInstance().getIndexAnim() + 1);
			moveSmall_Index++;
		}
		if (hero.getMagic() == 100) {
			if (monsterVector.size() != 0) {
				int temp = RandomValue.getRandInt(0, monsterVector.size());
				heroSkillmonster.addElement(monsterVector.elementAt(temp));
				for (int i = 0; i < monsterVector.size(); i++) {
					if (((Monster) monsterVector.elementAt(i)).getY() != ((Monster) monsterVector
							.elementAt(temp)).getY()
							&& ((Monster) monsterVector.elementAt(i)).getX() == ((Monster) monsterVector
									.elementAt(temp)).getX()) {
						Monster m = new Monster();
						m = (Monster) monsterVector.elementAt(i);
						heroSkillmonster.addElement(m);
					}
				}
				skill1Hero = new Point(325 + moveSmall_x,
						11
								+ (((Monster) (heroSkillmonster.elementAt(0)))
										.getX() - 1) * 60 + moveSmall_y + 20, 0);
				hero.setMagic(0, 0);
			}
		}
		drawHeroSkill(heroSkillmonster);
		drawHeroInfo1();
		judgeRemove();
		if (monsterVector.size() == 0) {
			if (mapIndexOfIndex < 3) {
				getMonster();
			} else {
				if (win1 == 0) {
					winExp = 800;
					winMoney = 1000;
					Resource.myProperty[1] += winExp;
					Resource.myProperty[2] += winMoney;
					if (Resource.myProperty[1] >= Upgrade
							.upgradeLeader(Resource.myProperty[0])) {
						Resource.myProperty[1] -= Upgrade
								.upgradeLeader(Resource.myProperty[0]);
						Resource.myProperty[0]++;
					}
					my.setLevel(Resource.myProperty[0]);
					my.setExp(Resource.myProperty[1]);
					my.setMoney(Resource.myProperty[2]);
					win1 = 1;
					drawWin();
				} else {
					drawWin();
				}
			}
		}
		if (hero.getBoold() <= 0) {
			drawFail();
		}
		arrowIndex++;
		if (arrowIndex == 10000)
			arrowIndex = 0;
	}

	int winExp;
	int winMoney;

	public int drawStick(Bullet b, int kk) {
		g.drawImage(b.getSrc(), b.getX(), b.getY(), 20);
		if (b.getX() < 0) {
			monster10AttackAndAbsorbOfHero();
			stickVector.removeElement(b);
		} else {
			b.setX(b.getX() - 50);
			kk++;
		}
		return kk;
	}

	private void setColour(int valueClour) {
		g.setColor(valueClour);
		Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
				Font.SIZE_LARGE);
		g.setFont(font);
	}

	private void drawHeroSkill(Vector heroSkillmonster2) {
		if (skill1Hero != null && hero.getKind() == 1) {
			drawSkill1(heroSkillmonster);
		} // else if (hero.getKind() == 2) {
			// drawSkill1(heroSkillmonster);
			// } else if (hero.getKind() == 3) {
			// drawSkill1(heroSkillmonster);
			// } else if (hero.getKind() == 4) {
			// drawSkill1(heroSkillmonster);
			// } else if (hero.getKind() == 5) {
			// drawSkill1(heroSkillmonster);
			// } else if (hero.getKind() == 6) {
			// drawSkill1(heroSkillmonster);
			// }
	}

	public void drawDeath(Monster m, int l) {
		int j = m.getAnimIndex();
		if (j % 6 >= 0 && j % 6 < 2) {
			g.drawRegion(img[temp_img_distance2 + 39], 0, 0, 62, 52, 0,
					385 + m.getY() * 60, 11 + m.getX() * 60, 20);
		} else if (j % 6 >= 2 && j % 6 < 4) {
			g.drawRegion(img[temp_img_distance2 + 39], 62, 0, 62, 52, 0,
					385 + m.getY() * 60, 11 + m.getX() * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 39], 124, 0, 62, 52, 0,
					385 + m.getY() * 60, 11 + m.getX() * 60, 20);
		}
		if (j % 12 == 11) {
			monsterVector.removeElement(m);
			deathArray.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	public void drawMonster() {
		for (int h = 0; h < monsterVector.size();) {
			Monster m = (Monster) monsterVector.elementAt(h);
			if (m.getBoold() != 0) {
				if (m.getKind() >= 1 && m.getKind() <= 3 || m.getKind() == 5
						|| m.getKind() == 6)
					h = monster12356Attack(h, m);
				else if (m.getKind() == 4)
					h = monster4Attack(h, m);
				else if (m.getKind() == 7)
					h = monster7Attack(h, m);
				else if (m.getKind() >= 8 && m.getKind() <= 13)
					h = monster8_13Attack(h, m);
				else if (m.getKind() == 14)
					h = boss1Attack(h, m);
				else if (m.getKind() == 15)
					h = boss2Attack(h, m);
				else if (m.getKind() == 16)
					h = boss3Attack(h, m);
				else if (m.getKind() == 17)
					h = boss4Attack(h, m);
				else if (m.getKind() == 18)
					h = boss5Attack(h, m);
				else if (m.getKind() == 19)
					h = boss6Attack(h, m);
				else if (m.getKind() == 19)
					h = boss6Attack(h, m);
				else if (m.getKind() == 19)
					h = boss6Attack(h, m);
				else if (m.getKind() == 22)
					h = monster22Attack(h, m);
			} else {
				h++;
			}
		}
	}

	public int showHurtAnim(Critical c, int l, Vector v, int baoji) {
		long end = System.currentTimeMillis();
		int temp = c.getNum();
		if (baoji == 0) {
			g.drawImage(img[temp_img_distance2 + 65], c.getX() - 10,
					c.getY() - 30, 20);
		}
		if (temp < 1000 && temp >= 100) {
			g.drawRegion(img[temp_img_distance2 + 64],
					20 * (int) Math.floor(temp / 100), 0, 20, 25, 0,
					c.getX() + 10, c.getY() - 20, 20);
			g.drawRegion(img[temp_img_distance2 + 64],
					20 * (int) Math.floor(temp % 100 / 10), 0, 20, 25, 0,
					c.getX() + 30, c.getY() - 20, 20);
			g.drawRegion(img[temp_img_distance2 + 64],
					20 * (int) Math.floor(temp % 10), 0, 20, 25, 0,
					c.getX() + 50, c.getY() - 20, 20);
		} else if (temp > 10) {
			g.drawRegion(img[temp_img_distance2 + 64],
					20 * (int) Math.floor(temp / 10), 0, 20, 25, 0,
					c.getX() + 20, c.getY() - 20, 20);
			g.drawRegion(img[temp_img_distance2 + 64],
					20 * (int) Math.floor(temp % 10), 0, 20, 25, 0,
					c.getX() + 40, c.getY() - 20, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 64],
					20 * (int) Math.floor(temp % 10), 0, 20, 25, 0,
					c.getX() + 20, c.getY() - 20, 20);
		}
		if (end - c.getTime() >= Critical.showtime) {
			v.removeElement(c);
		} else {
			l++;
		}
		return l;
	}

	public void drawSkill1(Vector ms) {
		int j = hero.getSkillIndex();
		skill1Hero.setX(skill1Hero.getX() + 30);
		for (int i = 0; i < ms.size(); i++) {
			Monster m = ((Monster) ms.elementAt(i));
			if (385 + m.getY() * 60 + moveSmall_x <= skill1Hero.getX()
					|| 385 + m.getY() * 60 + moveSmall_x >= skill1Hero.getX() + 40) {
				m.setShowBlood(true);
				m.setStartBlood(System.currentTimeMillis());
			}
			if (j % 6 >= 0 && j % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 63], 0, 0, 64, 103, 0,
						skill1Hero.getX(), skill1Hero.getY(), 20);
			} else if (j % 6 >= 2 && j % 6 < 4) {
				g.drawRegion(img[temp_img_distance2 + 63], 64, 0, 64, 103, 0,
						skill1Hero.getX(), skill1Hero.getY(), 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 63], 128, 0, 64, 103, 0,
						skill1Hero.getX(), skill1Hero.getY(), 20);
			}
		}
		hero.setSkillIndex(hero.getSkillIndex() + 1);
		if (skill1Hero.getX() >= 660) {
			hero.setSkillIndex(0);
			heroSkillmonster.removeAllElements();
			skill1Hero = null;
		}
	}

	public void monsterHurtAnim1(Monster m, int l, Vector v) {
		int j = m.getAnimIndex();
		int i = 0;
		if (m.getKind() > 14)
			i++;
		if (j % 6 >= 0 && j % 6 < 2) {
			g.drawRegion(img[temp_img_distance2 + 51], 0, 0, 34, 33, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 6 >= 2 && j % 6 < 4) {
			g.drawRegion(img[temp_img_distance2 + 51], 34, 0, 34, 33, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 51], 68, 0, 34, 33, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		}
		if (j % 10 == 9) {
			v.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	public void monsterHurtAnim3(Monster m, int l) {
		int j = m.getAnimIndex();
		int i = 0;
		if (m.getKind() > 14)
			i++;
		if (j % 5 >= 0 && j % 5 < 1) {
			g.drawRegion(img[temp_img_distance2 + 53], 0, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 1 && j % 5 < 2) {
			g.drawRegion(img[temp_img_distance2 + 53], 42, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 2 && j % 5 < 3) {
			g.drawRegion(img[temp_img_distance2 + 53], 84, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 3 && j % 10 < 4) {
			g.drawRegion(img[temp_img_distance2 + 53], 126, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 53], 168, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		}
		if (j % 10 == 9) {
			v3a.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	public void monsterHurtAnim5(Monster m, int l) {
		int j = m.getAnimIndex();
		int i = 0;
		if (m.getKind() > 14)
			i++;
		if (j % 5 >= 0 && j % 5 < 1) {
			g.drawRegion(img[temp_img_distance2 + 53], 0, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 1 && j % 5 < 2) {
			g.drawRegion(img[temp_img_distance2 + 53], 42, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 2 && j % 5 < 3) {
			g.drawRegion(img[temp_img_distance2 + 53], 84, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 3 && j % 10 < 4) {
			g.drawRegion(img[temp_img_distance2 + 53], 126, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 53], 168, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		}
		if (j % 10 == 9) {
			v5a.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	public void monsterHurtAnim7(Monster m, int l) {
		int j = m.getAnimIndex();
		int i = 0;
		if (m.getKind() > 14)
			i++;
		if (j % 5 >= 0 && j % 5 < 1) {
			g.drawRegion(img[temp_img_distance2 + 53], 0, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 1 && j % 5 < 2) {
			g.drawRegion(img[temp_img_distance2 + 53], 42, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 2 && j % 5 < 3) {
			g.drawRegion(img[temp_img_distance2 + 53], 84, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 3 && j % 10 < 4) {
			g.drawRegion(img[temp_img_distance2 + 53], 126, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 53], 168, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		}
		if (j % 10 == 9) {
			v7a.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	public void monsterHurtAnim8(Monster m, int l) {
		int j = m.getAnimIndex();
		int i = 0;
		if (m.getKind() > 14)
			i++;
		if (j % 5 >= 0 && j % 5 < 1) {
			g.drawRegion(img[temp_img_distance2 + 54], 0, 0, 60, 60, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 1 && j % 5 < 2) {
			g.drawRegion(img[temp_img_distance2 + 54], 60, 0, 60, 60, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 2 && j % 5 < 3) {
			g.drawRegion(img[temp_img_distance2 + 54], 120, 0, 60, 60, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 3 && j % 10 < 4) {
			g.drawRegion(img[temp_img_distance2 + 54], 180, 0, 60, 60, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 54], 240, 0, 60, 60, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		}
		if (j % 10 == 9) {
			v8a.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	public void monsterHurtAnim9(Monster m, int l) {
		int j = m.getAnimIndex();
		int i = 0;
		if (m.getKind() > 14)
			i++;
		if (j % 5 >= 0 && j % 5 < 1) {
			g.drawRegion(img[temp_img_distance2 + 53], 0, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 1 && j % 5 < 2) {
			g.drawRegion(img[temp_img_distance2 + 53], 42, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 2 && j % 5 < 3) {
			g.drawRegion(img[temp_img_distance2 + 53], 84, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else if (j % 5 >= 3 && j % 10 < 4) {
			g.drawRegion(img[temp_img_distance2 + 53], 126, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		} else {
			g.drawRegion(img[temp_img_distance2 + 53], 168, 0, 42, 42, 0,
					385 + m.getY() * 60, 11 + (m.getX() + i) * 60, 20);
		}
		if (j % 10 == 9) {
			v9a.removeElement(m);
		} else {
			l++;
		}
		m.setAnimIndex(++j);
	}

	private void drawBloodAndMagicB(Monster m) {
		g.drawImage(img[temp_img_distance2 + 61], 385 + m.getY() * 60,
				11 + m.getX() * 60, 20);
		int bloodLength = (int) (m.getBoold() / Math.ceil(m.getBloods() / 150));
		g.drawRegion(img[temp_img_distance2 + 62], 0, 0, bloodLength, 7, 0,
				385 + m.getY() * 60 + 1, 11 + m.getX() * 60 + 1, 20);
		g.drawImage(img[temp_img_distance2 + 59], 385 + m.getY() * 60,
				21 + m.getX() * 60, 20);
	}

	private int boss1Attack(int h, Monster m) {
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 4) {
				g.drawRegion(m.getSrc(), 0, 0, 220, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else {
				g.drawRegion(m.getSrc(), 220, 0, 220, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			}
			drawBloodAndMagicB(m);
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 0, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 0, 0, 220, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 0, 0, 220, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 0, 0, 220, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			}
			drawBloodAndMagicB(m);
			int powerLength = (int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 100);
			if (powerLength > 0) {
				g.drawRegion(img[temp_img_distance2 + 60], 0, 0, powerLength,
						4, 0, 385 + m.getY() * 60, 21 + m.getX() * 60 + 1, 20);
			}
		} else {
			drawBloodAndMagicB(m);
			bossStart = true;
			bossMoveindex = 0;
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int boss2Attack(int h, Monster m) {
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			int moveX = 0;
			if (bossStart) {
				if (bossMoveindex < 3)
					moveX = -bossMoveindex * 60;
				else if (bossMoveindex >= 3 && bossMoveindex < 6)
					moveX = -(6 - bossMoveindex) * 60;
			}
			if (arrowIndex % 10 >= 0 && arrowIndex % 10 < 2) {
				g.drawRegion(m.getSrc(), 0, 0, 125, 180, 0, 385 + m.getY() * 60
						+ moveX, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 10 >= 2 && arrowIndex % 10 < 4) {
				g.drawRegion(m.getSrc(), 125, 0, 125, 180, 0, 385 + m.getY()
						* 60 + moveX, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 10 >= 4 && arrowIndex % 10 < 6) {
				g.drawRegion(m.getSrc(), 250, 0, 125, 180, 0, 385 + m.getY()
						* 60 + moveX, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 10 >= 6 && arrowIndex % 10 < 8) {
				g.drawRegion(m.getSrc(), 375, 0, 125, 180, 0, 385 + m.getY()
						* 60 + moveX, 11 + m.getX() * 60, 20);
			} else {
				g.drawRegion(m.getSrc(), 500, 0, 125, 180, 0, 385 + m.getY()
						* 60 + moveX, 11 + m.getX() * 60, 20);
			}
			drawBloodAndMagicB(m);
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 5 >= 0 && arrowIndex % 5 < 1) {
				g.drawRegion(img[temp_img_distance2 + 58], 0, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 0, 0, 125, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 5 >= 1 && arrowIndex % 5 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 125, 0, 125, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 5 >= 2 && arrowIndex % 5 < 3) {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 250, 0, 125, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 5 >= 3 && arrowIndex % 5 < 4) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 375, 0, 125, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
				g.drawRegion(m.getSrc(), 500, 0, 125, 180, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60, 20);
			}
			drawBloodAndMagicB(m);
			int powerLength = (int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 100);
			if (powerLength > 0) {
				g.drawRegion(img[temp_img_distance2 + 60], 0, 0, powerLength,
						4, 0, 385 + m.getY() * 60, 21 + m.getX() * 60 + 1, 20);
			}
		} else {
			drawBloodAndMagicB(m);
			bossStart = true;
			bossMoveindex = 0;
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int boss3Attack(int h, Monster m) {
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 2) {
				g.drawImage(m.getSrc(), 385 + m.getY() * 60 + moveSmall_x, 11
						+ m.getX() * 60 + 5 + moveSmall_y, 20);
			} else if (arrowIndex % 8 >= 2 && arrowIndex % 8 < 4) {
				g.drawImage(m.getSrc(), 385 + m.getY() * 60 + moveSmall_x, 11
						+ m.getX() * 60 - 5 + moveSmall_y, 20);
			} else if (arrowIndex % 8 >= 4 && arrowIndex % 8 < 6) {
				g.drawImage(m.getSrc(), 385 + m.getY() * 60 + 5 + moveSmall_x,
						11 + m.getX() * 60 + moveSmall_y, 20);
			} else if (arrowIndex % 8 >= 6 && arrowIndex % 8 < 8) {
				g.drawImage(m.getSrc(), 385 + m.getY() * 60 - 5 + moveSmall_x,
						11 + m.getX() * 60 + moveSmall_y, 20);
			}
			drawBloodAndMagicB(m);
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 0, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			}
			g.drawImage(m.getSrc(), 385 + m.getY() * 60 + moveSmall_x,
					11 + m.getX() * 60 + moveSmall_y, 20);
			drawBloodAndMagicB(m);
			int powerLength = (int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 100);
			if (powerLength > 0) {
				g.drawRegion(img[temp_img_distance2 + 60], 0, 0, powerLength,
						4, 0, 385 + m.getY() * 60, 21 + m.getX() * 60 + 1, 20);
			}
		} else {
			drawBloodAndMagicB(m);
			bossStart = true;
			bossMoveindex = 0;
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int boss4Attack(int h, Monster m) {
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (bossStart) {
				if (bossMoveindex < 6) {
					g.drawRegion(m.getSrc(), 160 * bossMoveindex, 0, 160, 175,
							0, 385 + m.getY() * 60, 11 + m.getX() * 60, 20);
					bossMoveindex++;
				} else {
					if (arrowIndex % 4 >= 0 && arrowIndex % 4 < 2) {
						g.drawRegion(m.getSrc(), 0, 0, 160, 175, 0,
								385 + m.getY() * 60, 11 + m.getX() * 60, 20);
					} else {
						g.drawRegion(m.getSrc(), 160, 0, 160, 175, 0,
								385 + m.getY() * 60, 11 + m.getX() * 60, 20);
					}
				}
			} else {
				if (arrowIndex % 4 >= 0 && arrowIndex % 4 < 2) {
					g.drawRegion(m.getSrc(), 0, 0, 160, 175, 0,
							385 + m.getY() * 60, 11 + m.getX() * 60, 20);
				} else {
					g.drawRegion(m.getSrc(), 160, 0, 160, 175, 0,
							385 + m.getY() * 60, 11 + m.getX() * 60, 20);
				}
			}
			drawBloodAndMagicB(m);
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 0, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			}
			g.drawRegion(m.getSrc(), 0, 0, 160, 175, 0, 385 + m.getY() * 60,
					11 + m.getX() * 60, 20);
			drawBloodAndMagicB(m);
			int powerLength = (int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 100);
			if (powerLength > 0) {
				g.drawRegion(img[temp_img_distance2 + 60], 0, 0, powerLength,
						4, 0, 385 + m.getY() * 60, 21 + m.getX() * 60 + 1, 20);
			}
		} else {
			drawBloodAndMagicB(m);
			bossStart = true;
			bossMoveindex = 0;
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int boss5Attack(int h, Monster m) {
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (bossStart) {
				if (bossMoveindex < 6) {
					g.drawRegion(m.getSrc(),
							103 * (int) Math.floor(bossMoveindex / 2), 0, 103,
							148, 0, 385 + m.getY() * 60, 11 + m.getX() * 60, 20);
					bossMoveindex++;
				} else {
					if (arrowIndex % 4 >= 0 && arrowIndex % 4 < 2) {
						g.drawRegion(m.getSrc(), 0, 0, 103, 148, 0,
								385 + m.getY() * 60, 11 + m.getX() * 60, 20);
					} else {
						g.drawRegion(m.getSrc(), 103, 0, 103, 148, 0,
								385 + m.getY() * 60, 11 + m.getX() * 60, 20);
					}
				}
			} else {
				if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 2) {
					g.drawRegion(m.getSrc(), 0, 0, 103, 148, 0,
							385 + m.getY() * 60 + 8, 11 + m.getX() * 60, 20);
				} else if (arrowIndex % 8 >= 2 && arrowIndex % 8 < 4) {
					g.drawRegion(m.getSrc(), 0, 0, 103, 148, 0,
							385 + m.getY() * 60 - 8, 11 + m.getX() * 60, 20);
				} else if (arrowIndex % 8 >= 4 && arrowIndex % 4 < 6) {
					g.drawRegion(m.getSrc(), 0, 0, 103, 148, 0,
							385 + m.getY() * 60, 11 + m.getX() * 60 + 8, 20);
				} else {
					g.drawRegion(m.getSrc(), 103, 0, 103, 148, 0,
							385 + m.getY() * 60, 11 + m.getX() * 60 - 8, 20);
				}
			}
			drawBloodAndMagicB(m);
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 0, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			}
			g.drawRegion(m.getSrc(), 0, 0, 103, 148, 0, 385 + m.getY() * 60,
					11 + m.getX() * 60, 20);
			drawBloodAndMagicB(m);
			int powerLength = (int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 100);
			if (powerLength > 0) {
				g.drawRegion(img[temp_img_distance2 + 60], 0, 0, powerLength,
						4, 0, 385 + m.getY() * 60, 21 + m.getX() * 60 + 1, 20);
			}
		} else {
			drawBloodAndMagicB(m);
			bossStart = true;
			bossMoveindex = 0;
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int boss6Attack(int h, Monster m) {
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(m.getSrc(), 0, 0, 132, 178, 0,
						385 + m.getY() * 60 + 8, 11 + m.getX() * 60, 20);
			} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
				g.drawRegion(m.getSrc(), 132, 0, 132, 178, 0,
						385 + m.getY() * 60 - 8, 11 + m.getX() * 60, 20);
			} else {
				g.drawRegion(m.getSrc(), 264, 0, 132, 178, 0,
						385 + m.getY() * 60, 11 + m.getX() * 60 - 8, 20);
			}
			drawBloodAndMagicB(m);
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(img[temp_img_distance2 + 58], 0, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
				g.drawRegion(img[temp_img_distance2 + 58], 183, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 58], 366, 0, 183, 29, 0,
						370 + m.getY() * 60, 11 + m.getX() * 60 + 160, 20);
			}
			g.drawRegion(m.getSrc(), 0, 0, 132, 178, 0, 385 + m.getY() * 60,
					11 + m.getX() * 60, 20);
			drawBloodAndMagicB(m);
			int powerLength = (int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 100);
			if (powerLength > 0) {
				g.drawRegion(img[temp_img_distance2 + 60], 0, 0, powerLength,
						4, 0, 385 + m.getY() * 60, 21 + m.getX() * 60 + 1, 20);
			}
		} else {
			drawBloodAndMagicB(m);
			bossStart = true;
			bossMoveindex = 0;
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	public void drawBoss1Bullet() {
		bossMoveindex++;
		for (int j = 0; j < 8; j++) {
			g.drawImage(img[temp_img_distance2 + 41], 520 - 60 * bossMoveindex,
					50 * j, 20);
		}
		if (520 - 60 * bossMoveindex < 10) {
			bossStart = false;
			bossMoveindex = 0;
			moveSmall_Index = 0;
			if (StrikeHero.getInstance().getIndexAnim() == 4) {
				StrikeHero.getInstance().setIndexAnim(0);
			}
		}
	}

	public void drawBoss2Bullet() {
		bossMoveindex++;
		if (bossMoveindex >= 5) {
			for (int j = 0; j < 4; j++) {
				if (arrowIndex % 12 >= 0 && arrowIndex % 12 < 4) {
					g.drawRegion(img[temp_img_distance2 + 42], 0, 0, 90, 106,
							0, 520 - 60 * bossMoveindex - 70, 10 + 110 * j, 20);
				} else if (arrowIndex % 12 >= 4 && arrowIndex % 12 < 8) {
					g.drawRegion(img[temp_img_distance2 + 42], 90, 0, 90, 106,
							0, 520 - 60 * bossMoveindex - 70, 10 + 110 * j, 20);
				} else {
					g.drawRegion(img[temp_img_distance2 + 42], 180, 0, 90, 106,
							0, 520 - 60 * bossMoveindex - 70, 10 + 110 * j, 20);
				}
			}
			if (520 - 60 * bossMoveindex - 70 < 10) {
				bossStart = false;
				bossMoveindex = 0;
				moveSmall_Index = 0;
				if (StrikeHero.getInstance().getIndexAnim() == 4) {
					StrikeHero.getInstance().setIndexAnim(0);
				}
			}
		}
	}

	public void drawBoss3Bullet() {
		bossMoveindex++;
		int stoneLength = Resource.stoneXY.length;
		if (bossMoveindex <= 8) {
			for (int j = 0; j < stoneLength / 3; j++) {
				Resource.stoneXY[j * 3 + 2] += 50;
				g.drawImage(img[temp_img_distance2 + 43],
						Resource.stoneXY[j * 3], Resource.stoneXY[j * 3 + 1]
								+ Resource.stoneXY[j * 3 + 2], 20);
			}
		} else if (bossMoveindex > 8 && bossMoveindex <= 13) {
			for (int j = 0; j < stoneLength / 3; j++) {
				g.drawRegion(img[temp_img_distance2 + 44],
						(bossMoveindex - 9) * 128, 0, 128, 100, 0,
						Resource.stoneXY[j * 3], Resource.stoneXY[j * 3 + 1]
								+ Resource.stoneXY[j * 3 + 2], 20);
				if (bossMoveindex == 13) {
					Resource.stoneXY[j * 3 + 2] = 0;
					if (j == stoneLength / 3 - 1) {
						bossStart = false;
						bossMoveindex = 0;
						moveSmall_Index = 0;
						if (StrikeHero.getInstance().getIndexAnim() == 4) {
							StrikeHero.getInstance().setIndexAnim(0);
						}
					}
				}
			}
		}
	}

	public void drawBoss4Bullet() {
		if (bossMoveindex >= 6)
			bossMoveindex++;
		for (int j = 0; j < 3; j++) {
			g.drawRegion(img[temp_img_distance2 + 45],
					(3 - ((int) Math.floor(bossMoveindex / 3))) * 112, 0, 112,
					120, 0, 320 - 80 * (int) Math.floor(bossMoveindex / 3),
					20 + 120 * j, 20);
		}
		if (bossMoveindex == 11) {
			bossStart = false;
			bossMoveindex = 0;
			moveSmall_Index = 0;
			if (StrikeHero.getInstance().getIndexAnim() == 4) {
				StrikeHero.getInstance().setIndexAnim(0);
			}
		}
	}

	public void drawBoss5Bullet() {
		if (bossMoveindex >= 6)
			bossMoveindex++;
		for (int j = 0; j < 3; j++) {
			g.drawRegion(img[temp_img_distance2 + 46],
					((int) Math.floor(bossMoveindex / 3)) * 48, 0, 48, 90, 0,
					320 - 80 * (int) Math.floor(bossMoveindex / 3),
					20 + 120 * j, 20);
		}
		if (bossMoveindex == 11) {
			bossStart = false;
			bossMoveindex = 0;
			moveSmall_Index = 0;
			if (StrikeHero.getInstance().getIndexAnim() == 4) {
				StrikeHero.getInstance().setIndexAnim(0);
			}
		}
	}

	public void drawBoss6Bullet() {
		for (int h = 0; h < monsterVector.size(); h++) {
			Monster m = (Monster) monsterVector.elementAt(h);
			if ((m.getX() == 2 || m.getX() == 3) && m.getY() == 0) {
				monsterVector.removeElement(m);
			}
		}
		monsterVector.addElement(new Monster(Resource.MonsterSpeed[21],
				Resource.MonsterAttack[21], 22,
				img[Resource.MonsterImgIndex[21]], "zhaohuan",
				Resource.MonsterLevel[21], 2, 0, Resource.MonsterBlood[21], 1,
				0, Resource.MonsterBlood[21]));
		monsterVector.addElement(new Monster(Resource.MonsterSpeed[21],
				Resource.MonsterAttack[21], 22,
				img[Resource.MonsterImgIndex[21]], "zhaohuan",
				Resource.MonsterLevel[21], 3, 0, Resource.MonsterBlood[21], 1,
				0, Resource.MonsterBlood[21]));
		bossStart = false;
		bossMoveindex = 0;
	}

	private void drawBloodAndMagicM(Monster m, int bm) {
		if (bm == 1) {
			g.drawImage(img[temp_img_distance2 + 31], 385 + m.getY() * 60
					+ moveSmall_x, 4 + m.getX() * 60 + moveSmall_y, 20);
			int tempBlood = (int) (m.getBoold() * 1.0 / m.getBloods() * 50);
			if (System.currentTimeMillis() - m.getStartBlood() <= 3000) {
				for (int w = 0; w < tempBlood; w++) {
					g.drawRegion(img[temp_img_distance2 + 30], 1, 0, 1, 4, 0,
							385 + m.getY() * 61 + w + moveSmall_x, 5 + m.getX()
									* 60 + moveSmall_y, 0);
				}
			} else {
				m.setShowBlood(false);
			}
		} else if (bm == 2) {
			m.setProgressbar((int) ((m.getEndTime() - m.getStartTime() - m
					.getWaitTime()) / 200));
			g.drawImage(img[temp_img_distance2 + 31], 385 + m.getY() * 60
					+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			for (int w = 0; w < m.getProgressbar(); w++) {
				g.drawRegion(img[temp_img_distance2 + 30], 0, 0, 1, 4, 0, 385
						+ m.getY() * 61 + w + moveSmall_x, 12 + m.getX() * 60
						+ moveSmall_y, 0);
			}
		}
	}

	private int monster12356Attack(int h, Monster m) {
		if (m.isShowBlood()) {
			drawBloodAndMagicM(m, 1);
			// g.drawRegion(src, x_src, y_src, width, height, 0, x_dest, y_dest,
			// 20);
		}
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 4) {
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			} else {
				g.drawRegion(m.getSrc(), 60, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			}
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			drawBloodAndMagicM(m, 2);
			if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 4) {
				g.drawRegion(img[temp_img_distance2 + 32], 0, 0, 60, 32, 0, 385 // sdasd
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
				g.drawRegion(img[temp_img_distance2 + 33], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 32], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
				g.drawRegion(img[temp_img_distance2 + 33], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
			}
		} else {
			g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
					+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			m.setProgressbar(0);
			buttleArray.addElement(new Bullet(385 + m.getY() * 60, 11 + m
					.getX() * 60, img[temp_img_distance2 + 56], m));
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int monster4Attack(int h, Monster m) {
		if (m.isShowBlood()) {
			drawBloodAndMagicM(m, 1);
		}
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 16 >= 0 && arrowIndex % 16 < 4) {
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + 5 + moveSmall_y, 20);
			} else if (arrowIndex % 16 >= 4 && arrowIndex % 16 < 8) {
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 - 5 + moveSmall_y, 20);
			} else if (arrowIndex % 16 >= 8 && arrowIndex % 16 < 12) {
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ 5 + moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			} else if (arrowIndex % 16 >= 12 && arrowIndex % 16 < 16) {
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						- 5 + moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			}
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			drawBloodAndMagicM(m, 2);
			if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 4) {
				g.drawRegion(img[temp_img_distance2 + 32], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
				g.drawRegion(img[temp_img_distance2 + 33], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 32], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
				g.drawRegion(img[temp_img_distance2 + 33], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
			}
		} else {
			g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
					+ moveSmall_x, 11 + m.getX() * 60 + 5 + moveSmall_y, 20);
			m.setProgressbar(0);
			buttleArray.addElement(new Bullet(385 + m.getY() * 60, 11 + m
					.getX() * 60, img[temp_img_distance2 + 56], m));
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int monster7Attack(int h, Monster m) {
		if (m.isShowBlood()) {
			drawBloodAndMagicM(m, 1);
		}
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 12 >= 0 && arrowIndex % 12 < 4) {
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			} else if (arrowIndex % 12 >= 4 && arrowIndex % 12 < 8) {
				g.drawRegion(m.getSrc(), 60, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			} else {
				g.drawRegion(m.getSrc(), 120, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			}
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			drawBloodAndMagicM(m, 2);
			if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 4) {
				g.drawRegion(img[temp_img_distance2 + 32], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
				g.drawRegion(img[temp_img_distance2 + 33], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 32], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
				g.drawRegion(img[temp_img_distance2 + 33], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
			}
		} else {
			g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY() * 60
					+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			m.setProgressbar(0);
			buttleArray.addElement(new Bullet(385 + m.getY() * 60, 11 + m
					.getX() * 60, img[temp_img_distance2 + 56], m));
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	private int monster8_13Attack(int h, Monster m) {
		if (m.isShowBlood()) {
			drawBloodAndMagicM(m, 1);
		}
		int www = m.getSrc().getWidth();
		int hhh = m.getSrc().getHeight();
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 3) {
				g.drawRegion(m.getSrc(), 0, 0, www / 4, hhh, 0, 385 + m.getY()
						* 60 + moveSmall_x, 11 + m.getX() * 60 + moveSmall_y,
						20);
			} else {
				g.drawRegion(m.getSrc(), www / 4, 0, www / 4, hhh, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ moveSmall_y, 20);
			}
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			drawBloodAndMagicM(m, 2);
			if (arrowIndex % 2 >= 0 && arrowIndex % 2 < 1) {
				g.drawRegion(img[temp_img_distance2 + 32], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, www / 4, hhh, 0, 385 + m.getY()
						* 60 + moveSmall_x, 11 + m.getX() * 60 + moveSmall_y,
						20);
				g.drawRegion(img[temp_img_distance2 + 33], 0, 0, 60, 32, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60 + 30
						+ moveSmall_y, 20);
			} else {
				g.drawRegion(img[temp_img_distance2 + 32], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
				g.drawRegion(m.getSrc(), 0, 0, www / 4, hhh, 0, 385 + m.getY()
						* 60 + moveSmall_x, 11 + m.getX() * 60 + moveSmall_y,
						20);
				g.drawRegion(img[temp_img_distance2 + 33], 60, 0, 60, 32, 0,
						385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
								+ 30 + moveSmall_y, 20);
			}
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				+ m.getSkillWaitTime() * 1000
				&& m.getAnimIndex_attack() < 2) {
			if (m.getAnimIndex_attack() == 0) {
				g.drawRegion(m.getSrc(), www / 4 * 2, 0, www / 4, hhh, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
						+ moveSmall_y, 20);
			} else if (m.getAnimIndex_attack() == 1) {
				g.drawRegion(m.getSrc(), www / 4 * 3, 0, www / 4, hhh, 0, 385
						+ m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
						+ moveSmall_y, 20);
			}
			m.setAnimIndex_attack(m.getAnimIndex_attack() + 1);
		} else {
			g.drawRegion(m.getSrc(), 0, 0, www / 4, hhh, 0, 385 + m.getY() * 60
					+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			m.setProgressbar(0);
			m.setStartTime(m.getEndTime());
			m.setAnimIndex_attack(0);
			monster8_13Anim(m);
		}
		h++;
		return h;
	}

	private int monster22Attack(int h, Monster m) {
		if (m.isShowBlood()) {
			drawBloodAndMagicM(m, 1);
		}
		if (m.getEndTime() - m.getStartTime() < m.getWaitTime()) {
			if (m.isNewMonster()) {
				if (m.getAnimIndex() % 6 >= 0 && m.getAnimIndex() % 6 < 2) {
					g.drawRegion(m.getSrc(), 0, 0, 60, 60, 0, 385 + m.getY()
							* 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, 20);
					m.setAnimIndex(m.getAnimIndex() + 1);
				} else if (m.getAnimIndex() % 6 >= 2
						&& m.getAnimIndex() % 6 < 4) {
					g.drawRegion(m.getSrc(), 60, 0, 60, 60, 0, 385 + m.getY()
							* 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, 20);
					m.setAnimIndex(m.getAnimIndex() + 1);
				} else {
					g.drawRegion(m.getSrc(), 120, 0, 60, 60, 0, 385 + m.getY()
							* 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, 20);
					m.setAnimIndex(m.getAnimIndex() + 1);
					m.setNewMonster(false);
				}
			} else {
				if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
					g.drawRegion(m.getSrc(), 120, 0, 60, 60, 0, 385 + m.getY()
							* 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, 20);
				} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
					g.drawRegion(m.getSrc(), 180, 0, 60, 60, 0, 385 + m.getY()
							* 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, 20);
				} else {
					g.drawRegion(m.getSrc(), 240, 0, 60, 60, 0, 385 + m.getY()
							* 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, 20);
				}
			}
		} else if (m.getEndTime() - m.getStartTime() >= m.getWaitTime()
				&& m.getEndTime() - m.getStartTime() < m.getWaitTime()
						+ m.getSkillWaitTime() * 1000) {
			if (arrowIndex % 6 >= 0 && arrowIndex % 6 < 2) {
				g.drawRegion(m.getSrc(), 120, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			} else if (arrowIndex % 6 >= 2 && arrowIndex % 6 < 4) {
				g.drawRegion(m.getSrc(), 180, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			} else {
				g.drawRegion(m.getSrc(), 240, 0, 60, 60, 0, 385 + m.getY() * 60
						+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			}
		} else {
			g.drawRegion(m.getSrc(), 120, 0, 60, 60, 0, 385 + m.getY() * 60
					+ moveSmall_x, 11 + m.getX() * 60 + moveSmall_y, 20);
			buttleArray.addElement(new Bullet(385 + m.getY() * 60, 11 + m
					.getX() * 60, img[temp_img_distance2 + 56], m));
			m.setStartTime(m.getEndTime());
		}
		h++;
		return h;
	}

	Vector stickVector = new Vector();

	private void monster8_13Anim(Monster m) {
		if (m.getKind() == 8) {
			buttleArray.addElement(new Bullet(385 + m.getY() * 60, 11 + m
					.getX() * 60, img[temp_img_distance2 + 56], m));
		} else if (m.getKind() == 9) {
			addMonster9Hp();
		} else if (m.getKind() == 10) {
			stickVector.addElement(new Bullet(
					385 + m.getY() * 60 + moveSmall_x, 11 + m.getX() * 60
							+ moveSmall_y, img[temp_img_distance2 + 57], m));
		} else if (m.getKind() == 11) {
			monster11MakeSoldierNoMove();
		} else if (m.getKind() == 12) {
			monster12MakeHeroDefenceDown();
		} else if (m.getKind() == 13) {
			monster13MakeMyselfTimeDecrease();
		}
	}

	public void drawSoldier() {
		if (indexSelect == -1) {
			g.drawImage(img[12], index % 4 * 60 + 17 + moveSmall_x,
					((int) Math.floor(index / 4)) * 60 + 11 + moveSmall_y, 0);
		} else if (indexSelect != -1) {
			g.drawImage(img[13], index % 4 * 60 + 17 + moveSmall_x,
					((int) Math.floor(index / 4)) * 60 + 11 + moveSmall_y, 0);
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				if (idPic[i][j] != null) {
					g.drawRegion(img[idPic[i][j].getAuthenticKind()], 0, 0, 62,
							62, 0, j * 60 + 17 + idPic[i][j].getOffX()
									+ moveSmall_x,
							i * 60 + idPic[i][j].getOffY() + 11 + moveSmall_y,
							0);
				}
			}
		}
	}

	public void judgeRemove() {
		if (add == 0) {
			if (!startAddState) {
				startAddTime = System.currentTimeMillis();
				startAddState = true;
			} else if (System.currentTimeMillis() - startAddTime > 100
					&& startAddState) {
				boolean bb = mee.start(idPic);
				if (!bb && MoveElementEffect.index == 0) {
					add = -1;
					startAddState = false;
					new AutomaticRemove().start();
				}

			}
		}
	}

	Vector v9a = new Vector();

	/***
	 * 9.火枪手攻击判断
	 * 
	 * @param soldiers
	 */
	public void hackbuteerAttack(Soldier[] soldiers) {
		int[] idRow = new int[7];
		Monster[] idCol = new Monster[7];
		int idcolMonster = 3;

		for (int i = 0; i < 7; i++) {
			idRow[i] = 0;
		}
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] != null) {
				idRow[soldiers[i].getX()]++;
			}
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				idcolMonster = ((Monster) monsterVector.elementAt(i)).getY() < idcolMonster ? ((Monster) monsterVector
						.elementAt(i)).getY() : idcolMonster;
		}
		for (int i = 0; i < 7; i++) {
			idCol[i] = null;
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				if (mapIndexOfIndex == 3 && idcolMonster == 1
						&& ((Monster) monsterVector.elementAt(i)).getX() == 2) {
					idCol[2] = (Monster) monsterVector.elementAt(i);
					idCol[3] = (Monster) monsterVector.elementAt(i);
					idCol[4] = (Monster) monsterVector.elementAt(i);
				} else if (idcolMonster == ((Monster) monsterVector
						.elementAt(i)).getY()) {
					idCol[((Monster) monsterVector.elementAt(i)).getX()] = (Monster) monsterVector
							.elementAt(i);
				}
		}
		for (int i = 0; i < 7; i++) {
			if (idRow[i] != 0) {
				for (int j = 0; j < 7; j++) {
					if (i - j >= 0 && idCol[i - j] != null
							&& idCol[i - j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i - j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && ((i - j) == 2
													|| (i - j) == 3 || (i - j) == 4)))) {
										firelockArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														- j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					} else if (i + j <= 6 && idCol[i + j] != null
							&& idCol[i + j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i + j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && (i
													+ j == 2
													|| i + j == 3 || i + j == 4)))) {
										firelockArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														+ j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	Vector v8a = new Vector();

	/***
	 * 8.刺客攻击判断
	 * 
	 * @param soldiers
	 */
	public void assassinAttack(Soldier[] soldiers) {
		for (int i = 0; i < soldiers.length; i++) {
			for (int j = 0; j < monsterVector.size(); j++) {
				if (soldiers[i] != null) {
					if (((Monster) monsterVector.elementAt(j)).getBoold() != 0
							&& collision(soldiers[i],
									(Monster) monsterVector.elementAt(j))
							&& !soldiers[i].isFightWait()) {
						soldiers[i].setFightWait(true);
						int x = ((Monster) monsterVector.elementAt(j)).getX();
						int y = ((Monster) monsterVector.elementAt(j)).getY();
						int count = 0;
						for (int m = 0; m < soldiers.length; m++) {
							if (soldiers[m] != null) {
								if (x == soldiers[m].getX()
										|| (mapIndexOfIndex == 3 && y == 1 && (x == soldiers[m]
												.getX()
												|| x == soldiers[m].getX() - 1 || x == soldiers[m]
												.getX() - 2))) {
									count++;
									Monster m2 = (Monster) monsterVector
											.elementAt(j);
									int shanghaiNum = shanghai(
											soldiers[m].getLevel(),
											soldiers[m].getKind(),
											soldiers.length);
									m2.setBoold(m2.getBoold() - shanghaiNum);
									Critical c = new Critical(385 + m2.getY()
											* 60 + moveSmall_x, 11 + m2.getX()
											* 60 + moveSmall_y, shanghaiNum);
									criticalArray1.addElement(c);
									soldiers[m] = null;
								}
							}
						}
						Monster m8 = new Monster();
						m8 = (Monster) monsterVector.elementAt(j);
						v8a.addElement(m8);
						if (((Monster) monsterVector.elementAt(j)).getBoold() <= 0) {
							((Monster) monsterVector.elementAt(j)).setBoold(0);
							deathArray.addElement((Monster) monsterVector
									.elementAt(j));
						}
					}
				}
			}
		}
		int isEnptyNum = 0;
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] == null) {
				isEnptyNum++;
			}
		}
		if (isEnptyNum == soldiers.length) {
			soldiers = null;
		}
	}

	Vector v7a = new Vector();

	/***
	 * 7.魔法攻击判断
	 * 
	 * @param soldiers
	 */
	public void magicAttack(Soldier[] soldiers) {
		int[] idRow = new int[7];
		Monster[] idCol = new Monster[7];
		int idcolMonster = 3;

		for (int i = 0; i < 7; i++) {
			idRow[i] = 0;
		}
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] != null) {
				idRow[soldiers[i].getX()]++;
			}
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				idcolMonster = ((Monster) monsterVector.elementAt(i)).getY() < idcolMonster ? ((Monster) monsterVector
						.elementAt(i)).getY() : idcolMonster;
		}
		for (int i = 0; i < 7; i++) {
			idCol[i] = null;
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				if (mapIndexOfIndex == 3 && idcolMonster == 1
						&& ((Monster) monsterVector.elementAt(i)).getX() == 2) {
					idCol[2] = (Monster) monsterVector.elementAt(i);
					idCol[3] = (Monster) monsterVector.elementAt(i);
					idCol[4] = (Monster) monsterVector.elementAt(i);
				} else if (idcolMonster == ((Monster) monsterVector
						.elementAt(i)).getY()) {
					idCol[((Monster) monsterVector.elementAt(i)).getX()] = (Monster) monsterVector
							.elementAt(i);
				}
		}
		for (int i = 0; i < 7; i++) {
			if (idRow[i] != 0) {
				for (int j = 0; j < 7; j++) {
					if (i - j >= 0 && idCol[i - j] != null
							&& idCol[i - j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i - j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && ((i - j) == 2
													|| (i - j) == 3 || (i - j) == 4)))) {
										magicArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														- j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					} else if (i + j <= 6 && idCol[i + j] != null
							&& idCol[i + j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i + j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && (i
													+ j == 2
													|| i + j == 3 || i + j == 4)))) {
										magicArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														+ j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	Vector v6a = new Vector();

	/***
	 * 6.狂战士攻击判断
	 * 
	 * @param soldiers
	 * 
	 */
	public void crazyInfantryAttack(Soldier[] soldiers) {
		for (int i = 0; i < soldiers.length; i++) {
			for (int j = 0; j < monsterVector.size(); j++) {
				if (soldiers[i] != null) {
					int level = soldiers[i].getLevel();
					int kind = soldiers[i].getKind();
					int length_s = soldiers.length;
					if (((Monster) monsterVector.elementAt(j)).getBoold() != 0
							&& collision(soldiers[i],
									(Monster) monsterVector.elementAt(j))
							&& !soldiers[i].isFightWait()) {
						soldiers[i].setFightWait(true);
						int x = ((Monster) monsterVector.elementAt(j)).getX();
						int y = ((Monster) monsterVector.elementAt(j)).getY();
						int count = 0;
						for (int m = 0; m < soldiers.length; m++) {
							if (soldiers[m] != null) {
								if (x == soldiers[m].getX()
										|| (mapIndexOfIndex == 3 && y == 1 && (x == soldiers[m]
												.getX()
												|| x == soldiers[m].getX() - 1 || x == soldiers[m]
												.getX() - 2))) {
									count++;
									Monster m2 = (Monster) monsterVector
											.elementAt(j);
									int shanghaiNum = shanghai(level, kind,
											length_s);
									m2.setShowBlood(true);
									m2.setStartBlood(System.currentTimeMillis());
									m2.setBoold(m2.getBoold() - shanghaiNum);
									Critical c = new Critical(385 + m2.getY()
											* 60 + moveSmall_x, 11 + m2.getX()
											* 60 + moveSmall_y, shanghaiNum);
									criticalArray1.addElement(c);
									soldiers[m] = null;
								}
							}
						}
						Monster m61 = new Monster();
						m61 = (Monster) monsterVector.elementAt(j);
						v6a.addElement(m61);
						if (((Monster) monsterVector.elementAt(j)).getBoold() <= 0) {
							((Monster) monsterVector.elementAt(j)).setBoold(0);
							deathArray.addElement((Monster) monsterVector
									.elementAt(j));
						}
						for (int g = 0; g < monsterVector.size(); g++) {
							if (((Monster) monsterVector.elementAt(g))
									.getBoold() != 0
									&& (Monster) monsterVector.elementAt(j) != (Monster) monsterVector
											.elementAt(g)
									&& ((Monster) monsterVector.elementAt(j))
											.getY() == ((Monster) monsterVector
											.elementAt(g)).getY()
									&& (((Monster) monsterVector.elementAt(j))
											.getX() == ((Monster) monsterVector
											.elementAt(g)).getX() + 1 || ((Monster) monsterVector
											.elementAt(j)).getX() == ((Monster) monsterVector
											.elementAt(g)).getX() - 1)) {

								Monster m62 = new Monster();
								m62 = (Monster) monsterVector.elementAt(g);
								int shanghaiNum = shanghai(level, kind,
										length_s);
								m62.setShowBlood(true);
								m62.setStartBlood(System.currentTimeMillis());
								m62.setBoold(m62.getBoold() - shanghaiNum);
								Critical c = new Critical(385 + m62.getY() * 60
										+ moveSmall_x, 11 + m62.getX() * 60
										+ moveSmall_y, shanghaiNum);
								criticalArray1.addElement(c);
								v6a.addElement(m62);
								if (((Monster) monsterVector.elementAt(g))
										.getBoold() <= 0) {
									((Monster) monsterVector.elementAt(g))
											.setBoold(0);
									deathArray
											.addElement((Monster) monsterVector
													.elementAt(g));
								}
							}
						}
					}
				}
			}
		}
		int isEnptyNum = 0;
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] == null) {
				isEnptyNum++;
			}
		}
		if (isEnptyNum == soldiers.length) {
			soldiers = null;
		}
	}

	Vector v5a = new Vector();

	/***
	 * 5.牧师攻击判断
	 * 
	 * @param soldiers
	 */
	public void pastorAttack(Soldier[] soldiers) {
		int[] idRow = new int[7];
		Monster[] idCol = new Monster[7];
		int idcolMonster = 3;

		for (int i = 0; i < 7; i++) {
			idRow[i] = 0;
		}
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] != null) {
				idRow[soldiers[i].getX()]++;
			}
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				idcolMonster = ((Monster) monsterVector.elementAt(i)).getY() < idcolMonster ? ((Monster) monsterVector
						.elementAt(i)).getY() : idcolMonster;
		}
		for (int i = 0; i < 7; i++) {
			idCol[i] = null;
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				if (mapIndexOfIndex == 3 && idcolMonster == 1
						&& ((Monster) monsterVector.elementAt(i)).getX() == 2) {
					idCol[2] = (Monster) monsterVector.elementAt(i);
					idCol[3] = (Monster) monsterVector.elementAt(i);
					idCol[4] = (Monster) monsterVector.elementAt(i);
				} else if (idcolMonster == ((Monster) monsterVector
						.elementAt(i)).getY()) {
					idCol[((Monster) monsterVector.elementAt(i)).getX()] = (Monster) monsterVector
							.elementAt(i);
				}
		}
		for (int i = 0; i < 7; i++) {
			if (idRow[i] != 0) {
				for (int j = 0; j < 7; j++) {
					if (i - j >= 0 && idCol[i - j] != null
							&& idCol[i - j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i - j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && ((i - j) == 2
													|| (i - j) == 3 || (i - j) == 4)))) {
										slickballArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														- j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					} else if (i + j <= 6 && idCol[i + j] != null
							&& idCol[i + j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i + j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && (i
													+ j == 2
													|| i + j == 3 || i + j == 4)))) {
										slickballArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														+ j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	Vector v4a = new Vector();

	/***
	 * 4.锤兵攻击判断
	 * 
	 * @param soldiers
	 */
	public void hammerAttack(Soldier[] soldiers) {
		for (int i = 0; i < soldiers.length; i++) {
			for (int j = 0; j < monsterVector.size(); j++) {
				if (soldiers[i] != null) {
					if (((Monster) monsterVector.elementAt(j)).getBoold() != 0
							&& collision(soldiers[i],
									(Monster) monsterVector.elementAt(j))
							&& !soldiers[i].isFightWait()) {
						soldiers[i].setFightWait(true);
						int x = ((Monster) monsterVector.elementAt(j)).getX();
						int y = ((Monster) monsterVector.elementAt(j)).getY();
						int count = 0;
						for (int m = 0; m < soldiers.length; m++) {
							if (soldiers[m] != null) {
								if (x == soldiers[m].getX()
										|| (mapIndexOfIndex == 3 && y == 1 && (x == soldiers[m]
												.getX()
												|| x == soldiers[m].getX() - 1 || x == soldiers[m]
												.getX() - 2))) {
									count++;
									Monster m2 = (Monster) monsterVector
											.elementAt(j);
									int shanghaiNum = shanghai(
											soldiers[m].getLevel(),
											soldiers[m].getKind(),
											soldiers.length);
									m2.setShowBlood(true);
									m2.setStartBlood(System.currentTimeMillis());
									m2.setBoold(m2.getBoold() - shanghaiNum);
									Critical c = new Critical(385 + m2.getY()
											* 60 + moveSmall_x, 11 + m2.getX()
											* 60 + moveSmall_y, shanghaiNum);
									criticalArray1.addElement(c);
									soldiers[m] = null;
								}
							}
						}
						Monster m4 = new Monster();
						m4 = (Monster) monsterVector.elementAt(j);
						v4a.addElement(m4);
						((Monster) monsterVector.elementAt(j))
								.setStartTime(System.currentTimeMillis());
						if (((Monster) monsterVector.elementAt(j)).getBoold() <= 0) {
							((Monster) monsterVector.elementAt(j)).setBoold(0);
							deathArray.addElement((Monster) monsterVector
									.elementAt(j));
						}
					}
				}
			}
		}
		int isEnptyNum = 0;
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] == null) {
				isEnptyNum++;
			}
		}
		if (isEnptyNum == soldiers.length) {
			soldiers = null;
		}
	}

	Vector v3a = new Vector();

	/***
	 * 3.猎人攻击判断
	 * 
	 * @param soldiers
	 */
	public void hunterAttack(Soldier[] soldiers) {
		int[] idRow = new int[7];
		Monster[] idCol = new Monster[7];
		int idcolMonster = 3;

		for (int i = 0; i < 7; i++) {
			idRow[i] = 0;
		}
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] != null) {
				idRow[soldiers[i].getX()]++;
			}
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				idcolMonster = ((Monster) monsterVector.elementAt(i)).getY() < idcolMonster ? ((Monster) monsterVector
						.elementAt(i)).getY() : idcolMonster;
		}
		for (int i = 0; i < 7; i++) {
			idCol[i] = null;
		}
		for (int i = 0; i < monsterVector.size(); i++) {
			if (((Monster) monsterVector.elementAt(i)).getBoold() != 0)
				if (mapIndexOfIndex == 3 && idcolMonster == 1
						&& ((Monster) monsterVector.elementAt(i)).getX() == 2) {
					idCol[2] = (Monster) monsterVector.elementAt(i);
					idCol[3] = (Monster) monsterVector.elementAt(i);
					idCol[4] = (Monster) monsterVector.elementAt(i);
				} else if (idcolMonster == ((Monster) monsterVector
						.elementAt(i)).getY()) {
					idCol[((Monster) monsterVector.elementAt(i)).getX()] = (Monster) monsterVector
							.elementAt(i);
				}
		}
		for (int i = 0; i < 7; i++) {
			if (idRow[i] != 0) {
				for (int j = 0; j < 7; j++) {
					if (i - j >= 0 && idCol[i - j] != null
							&& idCol[i - j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i - j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && ((i - j) == 2
													|| (i - j) == 3 || (i - j) == 4)))) {
										arrowArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														- j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					} else if (i + j <= 6 && idCol[i + j] != null
							&& idCol[i + j].getBoold() != 0) {
						for (int h = 0; h < soldiers.length; h++) {
							if (soldiers[h] != null && soldiers[h].getX() == i) {
								for (int f = 0; f < 7; f++) {
									if ((idCol[f] != null && idCol[f]
											.getBoold() != 0)
											&& (idCol[f].getX() == i + j || (mapIndexOfIndex == 1
													&& idcolMonster == 1
													&& idCol[f].getX() == 2 && (i
													+ j == 2
													|| i + j == 3 || i + j == 4)))) {
										arrowArray.addElement(new Arrow(
												soldiers[h].getX() * 60 + 17,
												soldiers[h].getY() * 60 + 11,
												img[temp_img_distance2 + 52], i
														+ j, idcolMonster,
												idCol[f], soldiers[h]
														.getLevel(),
												soldiers.length));
									}
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	Vector v2a = new Vector();

	/***
	 * 2.骑兵攻击判断
	 * 
	 * @param soldiers
	 */
	public void sowarAttack(Soldier[] soldiers) {
		for (int i = 0; i < soldiers.length; i++) {
			for (int j = 0; j < monsterVector.size(); j++) {
				if (soldiers[i] != null) {
					if (((Monster) monsterVector.elementAt(j)).getBoold() != 0
							&& collision(soldiers[i],
									(Monster) monsterVector.elementAt(j))
							&& !soldiers[i].isCollision()) {
						int x = ((Monster) monsterVector.elementAt(j)).getX();
						int y = ((Monster) monsterVector.elementAt(j)).getY();
						int count = 0;
						for (int m = 0; m < soldiers.length; m++) {
							if (soldiers[m] != null) {
								if (mapIndexOfIndex == 3
										&& y == 1
										&& (x == soldiers[m].getX()
												|| x == soldiers[m].getX() - 1 || x == soldiers[m]
												.getX() - 2)) {
									count++;
									soldiers[m].setCollision(true);
								} else if (x == soldiers[m].getX()) {
									count++;
									soldiers[m].setCollision(true);
								}
							}
						}
						for (int f = 0; f < monsterVector.size(); f++) {
							Monster m2 = new Monster();
							m2 = (Monster) monsterVector.elementAt(f);
							if (m2.getBoold() != 0 && x == m2.getX()) {
								v2a.addElement(m2);
								int shanghaiNum = shanghai(
										soldiers[i].getLevel(),
										soldiers[i].getKind(), soldiers.length);
								m2.setShowBlood(true);
								m2.setStartBlood(System.currentTimeMillis());
								m2.setBoold(m2.getBoold() - shanghaiNum);
								Critical c = new Critical(385 + m2.getY() * 60
										+ moveSmall_x, 11 + m2.getX() * 60
										+ moveSmall_y, shanghaiNum);
								criticalArray1.addElement(c);
								if (m2.getBoold() <= 0) {
									m2.setBoold(0);
									deathArray.addElement(m2);
								}
							}
						}
					}
				}
			}
		}
	}

	Vector v1a = new Vector();

	/***
	 * 1.士兵攻击判断
	 * 
	 * @param soldiers
	 */
	public void infantryAttack(Soldier[] soldiers) {
		for (int i = 0; i < soldiers.length; i++) {
			for (int j = 0; j < monsterVector.size(); j++) {
				if (soldiers[i] != null) {
					if (((Monster) monsterVector.elementAt(j)).getBoold() != 0
							&& collision(soldiers[i],
									(Monster) monsterVector.elementAt(j))
							&& !soldiers[i].isFightWait()) {
						soldiers[i].setFightWait(true);
						int x = ((Monster) monsterVector.elementAt(j)).getX();
						int y = ((Monster) monsterVector.elementAt(j)).getY();
						int count = 0;
						for (int m = 0; m < soldiers.length; m++) {
							if (soldiers[m] != null) {
								if (x == soldiers[m].getX()
										|| (mapIndexOfIndex == 3 && y == 1 && (x == soldiers[m]
												.getX()
												|| x == soldiers[m].getX() - 1 || x == soldiers[m]
												.getX() - 2))) {
									count++;
									Monster mns = (Monster) monsterVector
											.elementAt(j);
									int shanghaiNum = shanghai(
											soldiers[m].getLevel(),
											soldiers[m].getKind(),
											soldiers.length);
									mns.setShowBlood(true);
									mns.setStartBlood(System
											.currentTimeMillis());
									mns.setBoold(mns.getBoold()
											- shanghai(soldiers[m].getLevel(),
													soldiers[m].getKind(),
													soldiers.length));
									// soldiers[m] = null;
									Critical c = new Critical(385 + mns.getY()
											* 60 + moveSmall_x, 11 + mns.getX()
											* 60 + moveSmall_y, shanghaiNum);
									criticalArray1.addElement(c);
								}
							}
						}
						Monster m1 = new Monster();
						m1 = (Monster) monsterVector.elementAt(j);
						v1a.addElement(m1);
						if (((Monster) monsterVector.elementAt(j)).getBoold() <= 0) {
							((Monster) monsterVector.elementAt(j)).setBoold(0);
							deathArray.addElement((Monster) monsterVector
									.elementAt(j));
						}
					}
				}
			}
		}
		int isEnptyNum = 0;
		for (int i = 0; i < soldiers.length; i++) {
			if (soldiers[i] == null) {
				isEnptyNum++;
			}
		}
		if (isEnptyNum == soldiers.length) {
			soldiers = null;
		}
	}

	public int shanghai(int level, int kind, int count) {
		int atk = Upgrade.upgradeSoldierAttack(level, kind);
		if (count == 3) {
			return atk;
		} else if (count == 4) {
			return (int) ((int) atk * 1.25);
		} else if (count == 5) {
			return (int) ((int) atk * 1.5);
		} else {
			return (int) ((int) atk * 2);
		}
	}

	public void drawMoveSoldier() {
		if (ca.getSs() != null) {
			Soldier[][][] ss = ca.getSs();
			int length0 = ss.length;
			for (int f = 0; f < length0; f++) {
				if (ss[f] == null)
					break;
				int length1 = ss[f].length;
				for (int i = 0; i < length1; i++) {
					if (ss[f][i] == null)
						break;
					for (int h = 0; h < ss[f][i].length; h++) {
						if (ss[f][i][h] != null) {
							for (int ff = 0; ff < ss[f][i].length; ff++) {
								if (ss[f][i][ff] != null) {
									for (int kk = 0; kk < IceDrowVector.size();) {
										if (Soldier.equal(ss[f][i][ff],
												((IceDrow) IceDrowVector
														.elementAt(kk)).getS())) {
											IceDrowVector.removeElementAt(kk);
										} else {
											kk++;
										}
									}
								}
							}
							if (ss[f][i][h].getAuthenticKind() == 0) {
								infantryAttack(ss[f][i]);
								soldier1Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 1) {
								sowarAttack(ss[f][i]);
								soldier2Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 2) {
								hunterAttack(ss[f][i]);
								soldier3Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 3) {
								hammerAttack(ss[f][i]);
								soldier1Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 4) {
								pastorAttack(ss[f][i]);
								soldier3Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 5) {
								crazyInfantryAttack(ss[f][i]);
								soldier1Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 6) {
								magicAttack(ss[f][i]);
								soldier3Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 7) {// 刺客（还不能运行）
								assassinAttack(ss[f][i]);
								soldier1Anim(ss, f, i);
							} else if (ss[f][i][h].getAuthenticKind() == 8) {
								if (ss[f][i][0].getAnimIndex() == 0) {
									hackbuteerAttack(ss[f][i]);
								}
								soldier9Anim(ss, f, i);
							}
							break;
						}
					}
				}
			}
		}
	}

	private void soldier9Anim(Soldier[][][] ss, int f, int i) {
		int length2 = ss[f][i].length;
		for (int j = 0; j < length2; j++) {
			if (ss[f][i][j] != null && ss[f][i][j].getAnimIndex() == 4) {
				ss[f][i][j] = null;
				continue;
			}
			g.drawRegion(img[(ss[f][i][j].getAuthenticKind())], 0, 0, 62, 62,
					0, ss[f][i][j].getY() * 60 + 17 + ss[f][i][j].getOffX(),
					ss[f][i][j].getX() * 60 + 11 + ss[f][i][j].getOffY(), 20);
			g.drawRegion(img[temp_img_distance2 + 55],
					ss[f][i][j].getAnimIndex() * 25, 0, 25, 28, 0,
					ss[f][i][j].getY() * 60 + 57 + ss[f][i][j].getOffX(),
					ss[f][i][j].getX() * 60 + 31 + ss[f][i][j].getOffY(), 20);
			ss[f][i][j].setAnimIndex(ss[f][i][j].getAnimIndex() + 1);
		}
	}

	private void soldier3Anim(Soldier[][][] ss, int f, int i) {
		ss[f][i] = null;
	}

	private void soldier2Anim(Soldier[][][] ss, int f, int i) {
		int length2 = ss[f][i].length;
		for (int j = 0; j < length2; j++) {
			if (ss[f][i][j] == null)
				continue;
			ss[f][i][j].setOffX((ss[f][i][j].getOffX() + 40));
			g.drawRegion(img[(ss[f][i][j].getAuthenticKind())], 0, 0, 62, 62,
					0, ss[f][i][j].getY() * 60 + 17 + ss[f][i][j].getOffX(),
					ss[f][i][j].getX() * 60 + 11 + ss[f][i][j].getOffY(), 20);
			if (ss[f][i][j].getState() == 1) {
				if (arrowIndex % 8 >= 0 && arrowIndex % 8 < 2) {
					g.drawRegion(
							img[temp_img_distance2 + 50],
							0,
							0,
							53,
							53,
							0,
							ss[f][i][j].getY() * 60 + 38
									+ ss[f][i][j].getOffX(), ss[f][i][j].getX()
									* 60 + ss[f][i][j].getOffY() + 20, 0);
				} else if (arrowIndex % 8 >= 2 && arrowIndex % 8 < 4) {
					g.drawRegion(
							img[temp_img_distance2 + 50],
							53,
							0,
							53,
							53,
							0,
							ss[f][i][j].getY() * 60 + 38
									+ ss[f][i][j].getOffX(), ss[f][i][j].getX()
									* 60 + ss[f][i][j].getOffY() + 20, 0);
				} else if (arrowIndex % 8 >= 4 && arrowIndex % 8 < 6) {
					g.drawRegion(
							img[temp_img_distance2 + 50],
							106,
							0,
							53,
							53,
							0,
							ss[f][i][j].getY() * 60 + 38
									+ ss[f][i][j].getOffX(), ss[f][i][j].getX()
									* 60 + ss[f][i][j].getOffY() + 20, 0);
				} else {
					g.drawRegion(
							img[temp_img_distance2 + 50],
							159,
							0,
							53,
							53,
							0,
							ss[f][i][j].getY() * 60 + 38
									+ ss[f][i][j].getOffX(), ss[f][i][j].getX()
									* 60 + ss[f][i][j].getOffY() + 20, 0);
				}
			}
			if (j + 1 == length2
					&& ss[f][i][j].getY() * 60 + 17 + ss[f][i][j].getOffX() > 800) {
				ss[f][i] = null;
			}
		}
	}

	private void soldier1Anim(Soldier[][][] ss, int f, int i) {
		int length2 = ss[f][i].length;
		for (int j = 0; j < length2; j++) {
			if (ss[f][i][j] != null) {
				// if (ss[f][i][j].isFightWait()) {
				// ss[f][i][j].setFightWaitIndex(ss[f][i][j]
				// .getFightWaitIndex() + 1);
				// g.drawRegion(img[(ss[f][i][j].getAuthenticKind())], 0, 0,
				// 62, 62, 0, ss[f][i][j].getY() * 60 + 17
				// + ss[f][i][j].getOffX(), ss[f][i][j].getX()
				// * 60 + 11 + ss[f][i][j].getOffY(), 20);
				// if (j + 1 == length2
				// && ss[f][i][j].getY() * 60 + 17
				// + ss[f][i][j].getOffX() > 800) {
				// ss[f][i] = null;
				// }
				// if (ss[f][i][j].getFightWaitIndex() == 5) {
				// ss[f][i][j] = null;
				// }
				// } else {
				ss[f][i][j].setOffX((ss[f][i][j].getOffX() + 40));
				g.drawRegion(img[(ss[f][i][j].getAuthenticKind())], 0, 0, 62,
						62, 0,
						ss[f][i][j].getY() * 60 + 17 + ss[f][i][j].getOffX(),
						ss[f][i][j].getX() * 60 + 11 + ss[f][i][j].getOffY(),
						20);
				if (j + 1 == length2
						&& ss[f][i][j].getY() * 60 + 17 + ss[f][i][j].getOffX() > 800) {
					ss[f][i] = null;
				}
				// }
			} else if (ss[f][i][j] == null) {
				continue;
			}
		}
	}

	public void drawHeroInfo1() {
		int temp_x = 5;
		int temp_y = 435;
		g.drawImage(img[temp_img_distance2 + 36], temp_x, temp_y, 20);
		for (int i = 0; i < 58; i++) {
			g.drawImage(img[temp_img_distance2 + 37], temp_x + 25 + i * 10,
					temp_y, 20);
		}
		g.drawImage(img[temp_img_distance2 + 38], temp_x + 605, temp_y, 20);
		g.drawImage(img[temp_img_distance2 + 34], temp_x + 7, temp_y + 9, 20);
		g.drawImage(img[temp_img_distance2 + 29], Hero.booldX - 1,
				Hero.booldY - 1, 20);
		g.drawImage(img[temp_img_distance2 + 29], Hero.magicX - 1,
				Hero.magicY - 1, 20);
		for (int i = 0; i < 3; i++) {
			g.drawImage(img[temp_img_distance2 + 34], temp_x + 399 + 78 * i,
					Hero.booldY - 5, 20);
			g.drawImage(img[temp_img_distance2 + 35], temp_x + 391 + 78 * i,
					Hero.booldY + 53, 20);
		}

		g.drawRegion(hero.getSrc(), selectHeroIndex * 59, 0, 59, 59, 0,
				hero.getX() + 1, hero.getY() + 1, 20);// 画英雄图片
		int unit = hero.getBoolds() / 100;
		if (hero.getBoold() >= unit) {// 画英雄血条
			g.drawRegion(img[temp_img_distance2 + 28], 0, 0, 2, 21, 0,
					Hero.booldX, Hero.booldY, 0);
			if (hero.getBoold() == hero.getBoolds()) {
				for (int i = 0; i < (hero.getBoolds() - 2 * unit) / unit; i++)
					g.drawRegion(img[temp_img_distance2 + 28], 2, 0, 2, 21, 0,
							Hero.booldX + 2 * (i + 1), Hero.booldY, 0);
				g.drawRegion(img[temp_img_distance2 + 28], 4, 0, 2, 21, 0,
						Hero.booldX + (hero.getBoold() / unit - 1) * 2,
						Hero.booldY, 0);
			}
			if (hero.getBoold() < hero.getBoolds() - unit) {
				for (int i = 0; i < (hero.getBoold() - unit) / unit; i++)
					g.drawRegion(img[temp_img_distance2 + 28], 2, 0, 2, 21, 0,
							Hero.booldX + 2 * (i + 1), Hero.booldY, 0);
			}
			TextView.showSingleLineText(g,
					hero.getBoold() + "/" + hero.getBoolds(), Hero.booldX + 80,
					Hero.booldY);
		}
		unit = 100 / 100;
		if (hero.getMagic() >= unit) {// 画英雄蓝条
			g.drawRegion(img[temp_img_distance2 + 28], 0, 21, 2, 21, 0,
					Hero.magicX, Hero.magicY, 0);
			if (hero.getMagic() == 2000) {
				for (int i = 0; i < (hero.getMagic() - 2 * unit) / unit; i++)
					g.drawRegion(img[temp_img_distance2 + 28], 2, 21, 2, 21, 0,
							Hero.magicX + 2 * (i + 1), Hero.magicY, 0);
				g.drawRegion(img[temp_img_distance2 + 28], 4, 21, 2, 21, 0,
						Hero.magicX + (hero.getMagic() / unit - 1) * 2,
						Hero.magicY, 0);
			}
			if (hero.getMagic() < 2000) {
				for (int i = 0; i < (hero.getMagic() - unit) / unit; i++)
					g.drawRegion(img[temp_img_distance2 + 28], 2, 21, 2, 21, 0,
							Hero.magicX + 2 * (i + 1), Hero.magicY, 0);
			}
		}
		TextView.showSingleLineText(g, hero.getMagic() + "/" + 100,
				Hero.magicX + 80, Hero.magicY);
	}

	public boolean judgeCritical() {
		boolean b = false;
		int i = RandomValue.getRandInt(0, 10);
		if (i < 8) {
			b = true;
		}
		return b;
	}

	public void drawSoldierBullet4() {
		if (firelockArray != null) {
			Enumeration e = firelockArray.elements();
			while (e.hasMoreElements()) {
				Arrow a = (Arrow) e.nextElement();
				int _x = a.getX() - (a.getEnd_x() * 60 + 17);
				int _y = a.getY() - (a.getEnd_y() * 60 + 385);
				double xielv;
				if (_x == 0) {
					xielv = 0;
				} else {
					xielv = (_x * 1.0) / (_y * 1.0);
				}
				if (xielv == 0) {
					a.setX(a.getX());// 向下跑
				} else {
					a.setX(a.getX() + (int) (xielv * 60));// 向下跑
				}
				a.setY((a.getY() + 60));// 向右跑
				if (xielv <= -1.0) {
					g.drawRegion(a.getSrc(), 0, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv > -1.0 && xielv < 0.0) {
					g.drawRegion(a.getSrc(), 54, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv == 0.0) {
					g.drawRegion(a.getSrc(), 108, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv > 0.0 && xielv < 1.0) {
					g.drawRegion(a.getSrc(), 162, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv >= 1.0) {
					g.drawRegion(a.getSrc(), 216, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				}
				if (Collision
						.checkSquareCollision(a.getY(), a.getX(), 32, 20,
								a.getEnd_y() * 60 + 385,
								a.getEnd_x() * 60 + 17, 60, 60)) {
					Monster m9 = new Monster();
					m9 = a.getM();
					v9a.addElement(m9);
					a.getM().setShowBlood(true);
					a.getM().setStartBlood(System.currentTimeMillis());
					int tempBlood = shanghai(a.getLevel(), 9, 5);
					if (judgeCritical()) {
						a.getM().setBoold(a.getM().getBoold() - tempBlood);
						Critical c = new Critical(a.getY(), a.getX(),
								tempBlood * 2);
						criticalArray.addElement(c);
					} else {
						a.getM().setBoold(a.getM().getBoold() - tempBlood);
					}
					if (a.getM().getBoold() <= 0) {
						a.getM().setBoold(0);
						deathArray.addElement(a.getM());
					}
					firelockArray.removeElement(a);
				}
				if (a.getY() > 600) {
					firelockArray.removeElement(a);
				}
			}
		}
	}

	public void drawSoldierBullet3() {
		if (magicArray != null) {
			Enumeration e = magicArray.elements();
			while (e.hasMoreElements()) {
				Arrow a = (Arrow) e.nextElement();
				int _x = a.getX() - (a.getEnd_x() * 60 + 17);
				int _y = a.getY() - (a.getEnd_y() * 60 + 385);
				double xielv;
				if (_x == 0) {
					xielv = 0;
				} else {
					xielv = (_x * 1.0) / (_y * 1.0);
				}
				if (xielv == 0) {
					a.setX(a.getX());// 向下跑
				} else {
					a.setX(a.getX() + (int) (xielv * 60));// 向下跑
				}
				a.setY((a.getY() + 60));// 向右跑
				if (xielv <= -1.0) {
					g.drawRegion(a.getSrc(), 0, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv > -1.0 && xielv < 0.0) {
					g.drawRegion(a.getSrc(), 54, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv == 0.0) {
					g.drawRegion(a.getSrc(), 108, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv > 0.0 && xielv < 1.0) {
					g.drawRegion(a.getSrc(), 162, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv >= 1.0) {
					g.drawRegion(a.getSrc(), 216, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				}
				if (Collision
						.checkSquareCollision(a.getY(), a.getX(), 32, 20,
								a.getEnd_y() * 60 + 385,
								a.getEnd_x() * 60 + 17, 60, 60)) {
					Monster m7 = new Monster();
					m7 = a.getM();
					v7a.addElement(m7);
					a.getM().setShowBlood(true);
					a.getM().setStartBlood(System.currentTimeMillis());
					int tempBlood = shanghai(a.getLevel(), 7, 5);
					Critical c = new Critical(a.getY(), a.getX(), tempBlood);
					criticalArray1.addElement(c);
					a.getM().setBoold(a.getM().getBoold() - tempBlood);
					if (a.getM().getBoold() <= 0) {
						a.getM().setBoold(0);
						deathArray.addElement(a.getM());
					}
					magicArray.removeElement(a);
				}
				if (a.getY() > 600) {
					magicArray.removeElement(a);
				}
			}
		}
	}

	public void drawSoldierBullet2() {
		if (slickballArray != null) {
			Enumeration e = slickballArray.elements();
			while (e.hasMoreElements()) {
				Arrow a = (Arrow) e.nextElement();
				int _x = a.getX() - (a.getEnd_x() * 60 + 17);
				int _y = a.getY() - (a.getEnd_y() * 60 + 385);
				double xielv;
				if (_x == 0) {
					xielv = 0;
				} else {
					xielv = (_x * 1.0) / (_y * 1.0);
				}
				if (xielv == 0) {
					a.setX(a.getX());// 向下跑
				} else {
					a.setX(a.getX() + (int) (xielv * 60));// 向下跑
				}
				a.setY((a.getY() + 60));// 向右跑
				g.drawRegion(a.getSrc(), 0, 0, 32, 20, 0, a.getY(), a.getX(),
						20);
				if (Collision
						.checkSquareCollision(a.getY(), a.getX(), 32, 20,
								a.getEnd_y() * 60 + 385,
								a.getEnd_x() * 60 + 17, 60, 60)) {
					Monster m5 = new Monster();
					m5 = a.getM();
					v5a.addElement(m5);
					a.getM().setShowBlood(true);
					a.getM().setStartBlood(System.currentTimeMillis());
					int tempBlood = shanghai(a.getLevel(), 5, 5);
					Critical c = new Critical(a.getY(), a.getX(), tempBlood);
					criticalArray1.addElement(c);
					a.getM().setBoold(a.getM().getBoold() - tempBlood);
					hero.setBoold(hero.getBoold()
							+ (int) (tempBlood * 6.0 / 100));
					if (hero.getBoold() >= hero.getBoolds()) {
						hero.setBoold(hero.getBoolds());
					}
					if (a.getM().getBoold() <= 0) {
						a.getM().setBoold(0);
						deathArray.addElement(a.getM());
					}
					slickballArray.removeElement(a);
				}
				if (a.getY() > 600) {
					slickballArray.removeElement(a);
				}
			}
		}
	}

	public void drawSoldierBullet1() {
		if (arrowArray != null) {
			Enumeration e = arrowArray.elements();
			while (e.hasMoreElements()) {
				Arrow a = (Arrow) e.nextElement();
				int _x = a.getX() - (a.getEnd_x() * 60 + 17);
				int _y = a.getY() - (a.getEnd_y() * 60 + 385);
				double xielv;
				if (_x == 0) {
					xielv = 0;
				} else {
					xielv = (_x * 1.0) / (_y * 1.0);
				}
				if (xielv == 0) {
					a.setX(a.getX());// 向下跑
				} else {
					a.setX(a.getX() + (int) (xielv * 60));// 向下跑
				}
				a.setY((a.getY() + 60));// 向右跑
				if (xielv <= -1.0) {
					g.drawRegion(a.getSrc(), 0, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv > -1.0 && xielv < 0.0) {
					g.drawRegion(a.getSrc(), 54, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv == 0.0) {
					g.drawRegion(a.getSrc(), 108, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv > 0.0 && xielv < 1.0) {
					g.drawRegion(a.getSrc(), 162, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				} else if (xielv >= 1.0) {
					g.drawRegion(a.getSrc(), 216, 0, 54, 72, 0, a.getY(),
							a.getX(), 20);
				}
				if (Collision
						.checkSquareCollision(a.getY(), a.getX(), 32, 20,
								a.getEnd_y() * 60 + 385,
								a.getEnd_x() * 60 + 17, 60, 60)) {
					Monster m3 = new Monster();
					m3 = a.getM();
					v3a.addElement(m3);
					a.getM().setShowBlood(true);
					a.getM().setStartBlood(System.currentTimeMillis());
					int tempBlood = shanghai(a.getLevel(), 3, 5);
					Critical c = new Critical(a.getY(), a.getX(), tempBlood);
					criticalArray1.addElement(c);
					a.getM().setBoold(a.getM().getBoold() - tempBlood);
					if (a.getM().getBoold() <= 0) {
						a.getM().setBoold(0);
						deathArray.addElement(a.getM());
					}
					arrowArray.removeElement(a);
				}
				if (a.getY() > 600) {
					arrowArray.removeElement(a);
				}
			}
		}
	}

	public void drawMonsterBullet1() {
		if (buttleArray != null) {
			Enumeration e = buttleArray.elements();
			while (e.hasMoreElements()) {
				Bullet b = (Bullet) e.nextElement();
				b.setX((b.getX() - 30));
				g.drawRegion(b.getSrc(), 0, 0, 32, 20, 0, b.getX(), b.getY(),
						20);
				if (b.getX() < 0) {
					int ddx = b.getM().getHurtValue() <= (int) (Upgrade
							.upgradeHeroDefence(hero.getLevel(), hero.getKind()) * hero
							.getDefenceCoefficient()) ? 0 : b.getM()
							.getHurtValue()
							- (int) (Upgrade.upgradeHeroDefence(
									hero.getLevel(), hero.getKind()) * hero
									.getDefenceCoefficient());
					hero.setBoold(hero.getBoold() - ddx);
					buttleArray.removeElement(b);
					moveSmall_Index = 0;
					if (StrikeHero.getInstance().getIndexAnim() == 4) {
						StrikeHero.getInstance().setIndexAnim(0);
					}
				}
			}
		}
	}

	public void drawSwordScar() {
		if (StrikeHero.getInstance().getIndexAnim() == 0)
			g.drawRegion(img[temp_img_distance2 + 40], 0, 0, 97, 176, 0, 300,
					100, 20);
		else if (StrikeHero.getInstance().getIndexAnim() == 1)
			g.drawRegion(img[temp_img_distance2 + 40], 97, 0, 97, 176, 0, 300,
					100, 20);
		else if (StrikeHero.getInstance().getIndexAnim() == 2)
			g.drawRegion(img[temp_img_distance2 + 40], 194, 0, 97, 176, 0, 300,
					100, 20);
		else if (StrikeHero.getInstance().getIndexAnim() == 3)
			g.drawRegion(img[temp_img_distance2 + 40], 291, 0, 97, 176, 0, 300,
					100, 20);
	}

	public int changeSoldier() {
		int changgeSucceed = 0;
		int i;
		IT: for (i = 0; i < 4; i++) {
			if (CreatArray.selcetSoldier[i] == selectSoldierindex) {
				CreatArray.selcetSoldier[i] = -1;
				changgeSucceed = 0;
				break IT;
			} else if (i == 3) {
				for (int j = 0; j < 4; j++) {
					if (CreatArray.selcetSoldier[j] == -1) {
						CreatArray.selcetSoldier[j] = selectSoldierindex;
						changgeSucceed = 1;
						break IT;
					} else if (j == 3) {
						changgeSucceed = 2;
						break IT;
					}
				}
			}
		}
		return changgeSucceed;
	}

	// public void changeSoldier2() {
	// it: for (int i = 0; i < 4; i++) {
	// if (CreatArray.selcetSoldier[i] == selectSoldierindex) {
	// CreatArray.selcetSoldier[i] = -1;
	// break it;
	// } else if (i == 3) {
	// for (int j = 0; j < 4; j++) {
	// if (CreatArray.selcetSoldier[j] == -1) {
	// CreatArray.selcetSoldier[j] = selectSoldierindex;
	// break it;
	// }
	// }
	// }
	// }
	// }

	public void addMonster9Hp() {
		Enumeration e = monsterVector.elements();
		while (e.hasMoreElements()) {
			Monster m = (Monster) e.nextElement();
			if (m.getBoold() != 0) {
				m.setBoold(m.getBoold() + 100);
				if (m.getBoold() > m.getBloods()) {
					m.setBoold(m.getBloods());
				}
				m.setShowBlood(true);
				m.setStartBlood(System.currentTimeMillis());
			}
		}
	}

	public void monster10AttackAndAbsorbOfHero() {
		hero.setBoold(hero.getBoold() - 50);
		hero.setMagic(hero.getMagic() - 10, 0);
		if (hero.getBoold() < 0) {
			hero.setBoold(0);
		}
		if (hero.getMagic() < 0) {
			hero.setMagic(0, 0);
		}
	}

	public void monster11MakeSoldierNoMove() {
		boolean b = true;
		int x = 0;
		int y = 0;
		while (b) {
			x = RandomValue.getRandInt(2, 6);
			y = RandomValue.getRandInt(0, 3);
			if (idPic[x][y] != null && idPic[x][y].getOffX() == 0) {
				idPic[x][y].setIceNoMove(true);
				idPic[x][y].setStartTime();
				b = false;
			}
		}
		IceDrow id = new IceDrow(idPic[x][y]);
		id.setStartTime();
		IceDrowVector.addElement(id);
	}

	Vector IceDrowVector = new Vector();

	public int drawIceDrow(IceDrow id, int kk) {
		if (!Collision.checkCircularCollision(id.getX(), id.getY(),
				IceDrow.img1.getWidth() / 4, IceDrow.img1.getHeight(), id
						.getS().getY() * 60 + 17, id.getS().getX() * 60 + 11,
				id.getS().getSrc().getWidth() / 3, id.getS().getSrc()
						.getHeight())
				&& id.getIndex() == 0) {
			float xielv = 0.0f;
			int _x = id.getX() - (id.getS().getY() * 60 + 17);
			int _y = id.getY() - (id.getS().getX() * 60 + 11);
			if (_x == 0) {
				xielv = 0.0f;
				id.setY(id.getY() + 50);
			} else if (_y == 0) {
				id.setX(id.getX() + 50);
				xielv = 0.0f;
			} else {
				xielv = (float) ((_x * 1.0) / (_y * 1.0));
				double yy = Math.sqrt((50 * 50) / (xielv * xielv + 1));
				double xx = yy * xielv;
				id.setX(id.getX() + (int) xx);
				id.setY(id.getY() + (int) yy);
			}
			g.drawRegion(IceDrow.img1, 0, 0, IceDrow.img1.getWidth() / 4,
					IceDrow.img1.getHeight(), 0, id.getX(), id.getY(), 20);
			kk++;
		} else if (Collision.checkCircularCollision(id.getX(), id.getY(),
				IceDrow.img1.getWidth() / 4, IceDrow.img1.getHeight(), id
						.getS().getY() * 60 + 17, id.getS().getX() * 60 + 11,
				id.getS().getSrc().getWidth() / 3, id.getS().getSrc()
						.getHeight())
				&& id.getIndex() < 4) {
			g.drawRegion(IceDrow.img1,
					IceDrow.img1.getWidth() / 4 * id.getIndex(), 0,
					IceDrow.img1.getWidth() / 4, IceDrow.img1.getHeight(), 0,
					id.getS().getY() * 60 + 19 + moveSmall_x, id.getS().getX()
							* 60 + 35 + moveSmall_y, 20);
			id.setIndex(id.getIndex() + 1);
			kk++;
		} else {
			if (System.currentTimeMillis() - id.getStartTime() < IceDrow.lastTime * 1000) {
				g.drawImage(IceDrow.img2, id.getS().getY() * 60 + 19
						+ moveSmall_x,
						id.getS().getX() * 60 + 35 + moveSmall_y, 20);
				kk++;
			} else {
				id.getS().setIceNoMove(false);
				ca.moveArray();
				IceDrowVector.removeElement(id);
			}
		}
		return kk;
	}

	public void monster12MakeHeroDefenceDown() {
		hero.setDefenceCoefficient(0.5f);
		hero.setNegativeTime();
	}

	public void monster13MakeMyselfTimeDecrease() {
		Enumeration e = monsterVector.elements();
		while (e.hasMoreElements()) {
			Monster m = (Monster) e.nextElement();
			if (m.getBoold() != 0) {
				m.setStartTime(m.getStartTime() - 5000);
			}
		}
	}

	public void loop() {
		handleKey(keyState);
		draw();
		if (isDebugMode()) {
			addDebugUserMessage("debug模式1键加攻击力，2打boss，3结束本关");
		}
	}

	public void draw() {
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (page.equals(Resource.PAGE_INDEX[0])
				|| page.equals(Resource.PAGE_INDEX[1])) {
			drawMain();
		} else if (page.equals(Resource.PAGE_INDEX[2])) {
			drawGame();
		}
	}

	public void recursion(Soldier[] s1, int index, Soldier[] s2, int count,
			int length) {
		if (s1[index].getX() + 1 == s2[count + 1].getX()) {
			for (int k = 0; k < length; k++) {
				if (Soldier.equal(s1[k], s2[count + 1])) { // s1[k].equal(s2[count
															// + 1])
					break;
				} else if (k == length - 1) {
					s1[++index] = s2[count + 1];
					length++;
					break;
				}
			}
		} else if (s1[index].getX() == s2[count + 1].getX()
				&& s1[index].getY() + 1 == s2[count + 1].getY()) {
			for (int k = 0; k < length; k++) {
				if (Soldier.equal(s1[k], s2[count + 1])) {
					break;
				} else if (k == length - 1) {
					s1[++index] = s2[count + 1];
					length++;
					break;
				}
			}
		} else if (s1[index].getX() == s2[count + 1].getX()
				&& s1[index].getY() - 1 == s2[count + 1].getY()) {
			for (int k = 0; k < length; k++) {
				if (Soldier.equal(s1[k], s2[count + 1])) {
					break;
				} else if (k == length - 1) {
					s1[++index] = s2[count + 1];
					length++;
					break;
				}
			}
		} else {

		}
	}

	public void exchange(Soldier idPic1, Soldier idPic2) {
		int x1 = idPic1.getX();
		int y1 = idPic1.getY();
		Image img1 = idPic1.getSrc();
		int x2 = idPic2.getX();
		int y2 = idPic2.getY();
		Image img2 = idPic2.getSrc();
		idPic2.setX(x1);
		idPic2.setY(y1);
		idPic2.setSrc(img1);
		idPic[x1][y1] = idPic2;
		idPic1.setX(x2);
		idPic1.setY(y2);
		idPic1.setSrc(img2);
		idPic[x2][y2] = idPic1;
	}

	public void handleKey(KeyState key) {
		if (page == Resource.PAGE_INDEX[0]) {
			if (key.containsAndRemove(KeyCode.OK)) {
				getData();
				page = Resource.PAGE_INDEX[1];
				mainIndex = 0;
			}
		} else if (page == Resource.PAGE_INDEX[1]) {
			if (mainIndex == 0)
				handleMainKey0(key);
			else if (mainIndex == 1)
				handleMainKey1(key);
			else if (mainIndex == 2)
				handleMainKey2(key);
			else if (mainIndex == 3)
				handleMainKey3(key);
			else if (mainIndex == 4)
				handleMainKey4(key);
			else if (mainIndex == 6)
				handleRewardKey(key);
			else if (mainIndex == 7)
				handleMainKey7(key);
		} else if (page == Resource.PAGE_INDEX[2]) {
			if (gameIndex == 0)
				handleGameKey(key);
			else if (gameIndex == 1)
				handeGameOver2(key);
			else if (gameIndex == 2)
				handeGameOver2(key);
		}
	}

	// private void handeGameOver1(KeyState key) {
	// if (key.containsAndRemove(KeyCode.OK)) {
	// if (gameButtonSelect == 0) {
	//
	// } else if (gameButtonSelect == 1) {
	//
	// }
	// } else if (key.containsAndRemove(KeyCode.LEFT)) {
	// if (gameButtonSelect == 0)
	// gameButtonSelect = 1;
	// else
	// gameButtonSelect = 0;
	// } else if (key.containsAndRemove(KeyCode.RIGHT)) {
	// if (gameButtonSelect == 0)
	// gameButtonSelect = 1;
	// else
	// gameButtonSelect = 0;
	// }
	// }

	private void handeGameOver2(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK)) {
			mainPageIndex[1] = 0;
			mainIndex = 1;
			gameIndex = 0;
			page = Resource.PAGE_INDEX[1];
			mapIndexOfIndex = 0;
			clearGameImg();
			loadMainImg();
		}
	}

	private void handleGameKey(KeyState key) {
		if (key.containsAndRemove(KeyCode.OK) && state) {
			if (indexSelect == -1) {
				indexSelect = index;
			} else if (indexSelect != -1) {
				indexSelect = -1;
			}
		} else if (key.containsAndRemove(KeyCode.LEFT)) {
			if (indexSelect == -1) {
				if (index % 4 == 0)
					index += 3;
				else
					index--;
			} else if (indexSelect != -1) {
				if (index % 4 == 0) {
					return;
				} else {
					int x = (int) Math.floor(index / 4);
					int y = (int) Math.floor(index % 4);
					if (idPic[x][y].isIceNoMove()
							|| idPic[x][y - 1].isIceNoMove()) {
						indexSelect = -1;
						return;
					}
					idPic[x][y].setMove(true);
					indexSelect = -1;
					AnimationThread at = new AnimationThread(KeyCode.LEFT,
							idPic[x][y], idPic[x][y - 1]);
					at.start();
				}
			}
		} else if (key.containsAndRemove(KeyCode.RIGHT)) {
			if (indexSelect == -1) {
				if (index % 4 == 3)
					index -= 3;
				else
					index++;
			} else if (indexSelect != -1) {
				if (index % 4 == 3) {
					return;
				} else {
					int x = (int) Math.floor(index / 4);
					int y = (int) Math.floor(index % 4);
					if (idPic[x][y].isIceNoMove()
							|| idPic[x][y + 1].isIceNoMove()) {
						indexSelect = -1;
						return;
					}
					idPic[x][y].setMove(true);
					indexSelect = -1;
					AnimationThread at = new AnimationThread(KeyCode.RIGHT,
							idPic[x][y], idPic[x][y + 1]);
					at.start();
				}
			}
		} else if (key.containsAndRemove(KeyCode.UP)) {
			if (indexSelect == -1) {
				if (index < 4)
					index += 24;
				else
					index -= 4;
			} else if (indexSelect != -1) {
				if (index < 4) {
					return;
				} else {
					int x = (int) Math.floor(index / 4);
					int y = (int) Math.floor(index % 4);
					if (idPic[x][y].isIceNoMove()
							|| idPic[x - 1][y].isIceNoMove()) {
						indexSelect = -1;
						return;
					}
					idPic[x][y].setMove(true);
					indexSelect = -1;
					AnimationThread at = new AnimationThread(KeyCode.UP,
							idPic[x][y], idPic[x - 1][y]);
					at.start();
				}
			}
		} else if (key.containsAndRemove(KeyCode.DOWN)) {
			if (indexSelect == -1) {
				if (index > 23)
					index -= 24;
				else
					index += 4;
			} else if (indexSelect != -1) {
				if (index > 23) {
					return;
				} else {
					int x = (int) Math.floor(index / 4);
					int y = (int) Math.floor(index % 4);
					if (idPic[x][y].isIceNoMove()
							|| idPic[x + 1][y].isIceNoMove()) {
						indexSelect = -1;
						return;
					}
					idPic[x][y].setMove(true);
					indexSelect = -1;
					AnimationThread at = new AnimationThread(KeyCode.DOWN,
							idPic[x][y], idPic[x + 1][y]);
					at.start();
				}
			}
		} else if (key.containsAndRemove(KeyCode.NUM1)) {
			if (isDebugMode()) {
				levelDebug++;
				for(int i=0;i<9;i++){
					Upgrade.upgradeSoldierAttack(levelDebug, i);
				}
			}
		}
	}

	int levelDebug =1;
	public boolean collision(Soldier s, Monster m) {
		if (mapIndexOfIndex == 3
				&& m.getY() == 1
				&& (s.getX() == m.getX() || s.getX() == m.getX() + 1 || s
						.getX() == m.getX() + 2)) {
			if (s.getOffX() + s.getY() * 60 + 17 >= 385 + m.getY() * 60) {
				return true;
			}
		} else if (s.getX() == m.getX()) {
			if (s.getOffX() + s.getY() * 60 + 77 >= 385 + m.getY() * 60) {
				return true;
			}
		}
		return false;
	}

	class AnimationThread extends Thread {

		int exchange = 1;
		int keyCode;
		Soldier idPic1;
		Soldier idPic2;
		int offX1 = 0;
		int offX2 = 0;

		public AnimationThread(int keyCode, Soldier idPic1, Soldier idPic2) {
			this.keyCode = keyCode;
			this.idPic1 = idPic1;
			this.idPic2 = idPic2;
		}

		public void run() {
			while (idPic1.isMove()) {
				if (keyCode == KeyCode.LEFT) {
					if (offX1 == -55 && exchange == 1) {
						moveXElement1();
					} else if (exchange == 1) {
						moveSetXOff1();
					} else if (offX1 == 55 && exchange == 2) {
						moveXElement2();
					} else if (exchange == 2) {
						moveSetXOff2();
					}
					moveSleep();
				} else if (keyCode == KeyCode.RIGHT) {
					if (offX1 == 55 && exchange == 1) {
						moveXElement1();
					} else if (exchange == 1) {
						moveSetXOff2();
					} else if (offX1 == -55 && exchange == 2) {
						moveXElement2();
					} else if (exchange == 2) {
						moveSetXOff1();
					}
					moveSleep();
				} else if (keyCode == KeyCode.UP) {
					if (offX1 == -55 && exchange == 1) {
						moveYElement1();
					} else if (exchange == 1) {
						moveSetYOff1();
					} else if (offX1 == 55 && exchange == 2) {
						moveYElement2();
					} else if (exchange == 2) {
						moveSetYOff2();
					}
					moveSleep();
				} else if (keyCode == KeyCode.DOWN) {
					if (offX1 == 55) {
						moveYElement1();
					} else if (exchange == 1) {
						moveSetYOff2();
					} else if (offX1 == -55 && exchange == 2) {
						moveYElement2();
					} else if (exchange == 2) {
						moveSetYOff1();
					}
					moveSleep();
				}
			}
		}

		private void moveSetYOff2() {
			offX1 += 11;
			offX2 -= 11;
			idPic1.setOffY(offX1);
			idPic2.setOffY(offX2);
		}

		private void moveSetYOff1() {
			offX1 -= 11;
			offX2 += 11;
			idPic1.setOffY(offX1);
			idPic2.setOffY(offX2);
		}

		private void moveYElement2() {
			idPic1.setMove(false);
			idPic1.setOffY(0);
			idPic2.setOffY(0);
			exchange(idPic1, idPic2);
		}

		private void moveSleep() {
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void moveXElement2() {
			idPic1.setMove(false);
			idPic1.setOffX(0);
			idPic2.setOffX(0);
			exchange(idPic1, idPic2);
		}

		private void moveSetXOff2() {
			offX1 += 11;
			offX2 -= 11;
			idPic1.setOffX(offX1);
			idPic2.setOffX(offX2);
		}

		private void moveSetXOff1() {
			offX1 -= 11;
			offX2 += 11;
			idPic1.setOffX(offX1);
			idPic2.setOffX(offX2);
		}

		private void moveYElement1() {
			idPic1.setMove(false);
			offX1 = 0;
			offX2 = 0;
			idPic1.setOffY(offX1);
			idPic2.setOffY(offX2);
			exchange(idPic1, idPic2);
			if (!ca.sss()) {
				idPic1.setMove(true);
				exchange = 2;
			}
		}

		private void moveXElement1() {
			idPic1.setMove(false);
			offX1 = 0;
			offX2 = 0;
			idPic1.setOffX(offX1);
			idPic2.setOffX(offX2);
			exchange(idPic1, idPic2);
			if (!ca.sss()) {
				idPic1.setMove(true);
				exchange = 2;
			}
		}
	}

	class AutomaticRemove extends Thread {

		public void run() {
			ca.sss();
		}

	}

}
