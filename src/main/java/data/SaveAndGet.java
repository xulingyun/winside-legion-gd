package data;

import java.util.Date;

import cn.ohyeah.stb.game.ServiceWrapper;
import cn.ohyeah.stb.util.ConvertUtil;
import cn.ohyeah.stb.util.DateUtil;
import test.GameEngine;
import test.PropManager;
import test.Resource;
import upgrade.Upgrade;

public class SaveAndGet {

	static GameEngine engine;
	static PropManager pm;
	public static int result;
	public static boolean isFirstGame = true;

	public SaveAndGet(GameEngine engine, PropManager pm) {
		SaveAndGet.engine = engine;
		SaveAndGet.pm = pm;
	}

	private static SaveAndGet gameRecord;
	long nowtime;

	public static SaveAndGet getInstance() {
		if (gameRecord == null) {
			gameRecord = new SaveAndGet(engine, pm);
		}
		return gameRecord;
	}

	private String[] joinData() {
		nowtime = System.currentTimeMillis();
		String[] splitString = new String[4];
		if (isFirstGame) {
			splitString[0] = 1 + ",";
		} else {
			splitString[0] = 0 + ",";
		}
		splitString[0] += Resource.tongtiantafloor + ",";
		splitString[0] += nowtime + ",";
		splitString[0] += engine.my.getLevel() + ",";
		splitString[0] += engine.my.getExp() + ",";
		splitString[0] += engine.my.getMoney() + ",";
		splitString[0] += engine.my.getNengliang() + ",";
		splitString[0] += engine.my.getCountAward() + ",";
		splitString[0] += engine.my.getCountTongtianta();
		int i;
		for (i = 0; i < Resource.heroAndSoldierLevel.length; i++) {
			if (i == 0)
				splitString[1] = Resource.heroAndSoldierLevel[i] + ",";
			else
				splitString[1] += Resource.heroAndSoldierLevel[i] + ",";
		}
		for (i = 0; i < Resource.savaSelcetSoldier.length; i++) {
			splitString[1] += Resource.savaSelcetSoldier[i] + ",";
		}
		for (i = 0; i < Resource.isBelongMyOfHero.length; i++) {
			splitString[1] += Resource.isBelongMyOfHero[i] + ",";
		}
		splitString[1] += Resource.saveSelectHero;

		for (i = 0; i < Resource.guanqiaLevel.length; i++) {
			if (i == 0)
				splitString[2] = Resource.guanqiaLevel[i] + ",";
			else
				splitString[2] += Resource.guanqiaLevel[i] + ",";
		}
		for (i = 0; i < engine.pm.props.length; i++) {
			if (i == 0)
				splitString[3] = engine.pm.props[i].getNums() + ",";
			else
				splitString[3] += engine.pm.props[i].getNums() + ",";
		}
		for (i = 0; i < Resource.isFinishAttainment.length; i++) {
			if (i == Resource.isFinishAttainment.length - 1)
				splitString[3] += Resource.isFinishAttainment[i];
			else
				splitString[3] += Resource.isFinishAttainment[i] + ",";
		}
		print();
		return splitString;
	}

	public int saveData() {
		ServiceWrapper sw = engine.getServiceWrapper();
		String[] ss = joinData();
		sw.saveRecord(1, ss[0]);
		sw.saveRecord(2, ss[1]);
		sw.saveRecord(3, ss[2]);
		sw.saveRecord(4, ss[3]);
		return sw.getResult();
	}

	public int readRecord() {
		try {
			ServiceWrapper sw = engine.getServiceWrapper();
			String[] data = new String[4];
			data[0] = sw.loadRecord(1).trim();
			data[1] = sw.loadRecord(2).trim();
			data[2] = sw.loadRecord(3).trim();
			data[3] = sw.loadRecord(4).trim();
			if (data[0].equals("") || data[1].equals("") || data[2].equals("")
					|| data[3].equals("")) {
				result = -1;
				return result;
			}
			splitData(data);
			result = sw.getResult();
			return result;
		} catch (Exception e) {
			// errorMessage += "\n读取游戏记录出错" + e.getMessage();
			result = -1;
			return result;
		}
	}

	private void splitData(String[] ss) {
		String[][] sss = new String[4][60];
		sss[0] = ConvertUtil.split(ss[0], ",");
		sss[1] = ConvertUtil.split(ss[1], ",");
		sss[2] = ConvertUtil.split(ss[2], ",");
		sss[3] = ConvertUtil.split(ss[3], ",");

		String gh = "";
		for (int jb = 0; jb < sss[0].length; jb++) {
			gh += sss[0][jb];
		}

		int i = 0;
		int j = 0;
		if (Byte.parseByte(sss[0][i++].trim()) == 0) {
			isFirstGame = false;
		} else {
			isFirstGame = true;
		}
		Resource.tongtiantafloor = Integer.parseInt(sss[0][i++].trim());
		Resource.resumeNengliangTime = Long.parseLong(sss[0][i++].trim());
		engine.my.setLevel(Integer.parseInt(sss[0][i++].trim()));
		engine.my.setExp(Long.parseLong(sss[0][i++].trim()));
		engine.my.setMoney(Long.parseLong(sss[0][i++].trim()));
		engine.my.setNengliang(Integer.parseInt(sss[0][i++].trim()));
		engine.my.setCountAward(Integer.parseInt(sss[0][i++].trim()));
		engine.my.setCountTongtianta(Integer.parseInt(sss[0][i].trim()));

		for (i = 0; i < Resource.heroAndSoldierLevel.length; i++) {
			Resource.heroAndSoldierLevel[i] = Byte.parseByte(sss[1][i].trim());
		}
		j += i;
		for (i = 0; i < Resource.savaSelcetSoldier.length; i++) {
			Resource.savaSelcetSoldier[i] = Byte.parseByte(sss[1][j + i].trim());
		}
		j += i;
		for (i = 0; i < Resource.isBelongMyOfHero.length; i++) {
			Resource.isBelongMyOfHero[i] = Byte.parseByte(sss[1][j + i].trim());
		}
		j += i;
		Resource.saveSelectHero = Byte.parseByte(sss[1][j].trim());

		i = 0;
		j = 0;
		for (i = 0; i < Resource.guanqiaLevel.length; i++) {
			Resource.guanqiaLevel[i] = Byte.parseByte(sss[2][i].trim());
		}
		i = 0;
		j = 0;
		System.out.println("道具数目1：");
		System.out.println("道具数目3：" + sss[3][0]);
		for (i = 0; i < engine.pm.props.length; i++) {
			System.out.println("道具数目2：" + engine.pm.props.length);
			engine.pm.props[i].setNums(Byte.parseByte(sss[3][i].trim()));
			System.out.println("道具数目：" + engine.pm.props[i].getNums());
		}
		j += i;
		for (i = 0; i < Resource.isFinishAttainment.length; i++) {
			Resource.isFinishAttainment[i] = Byte.parseByte(sss[3][j + i].trim());
		}
		long nowTime = System.currentTimeMillis();
		long timeSum = nowTime - Resource.resumeNengliangTime;
		engine.my.setNengliang(engine.my.getNengliang() + (int) (timeSum / 1000 / 60 / 30));
		if (engine.my.getNengliang() > Upgrade.upgradeLeaderNengliang(engine.my.getLevel())) {
			engine.my.setNengliang(Upgrade.upgradeLeaderNengliang(engine.my.getLevel()));
		}
		if (!DateUtil.isSameDay(new Date(Resource.resumeNengliangTime), new Date(nowTime))) {
			engine.my.setCountAward(1);
			engine.my.setCountTongtianta(1);
		}
		Resource.resumeNengliangTime = nowTime;
		print();
	}

	public void print() {
		System.out.println("engine.my.getLevel():" + engine.my.getLevel());
		System.out.println("engine.my.getExp():" + engine.my.getExp());
		System.out.println("engine.my.getMoney():" + engine.my.getMoney());
		System.out.println("engine.my.getNengliang():" + engine.my.getNengliang());
		for (int i = 0; i < Resource.heroAndSoldierLevel.length; i++) {
			System.out.println("Resource.heroAndSoldierLevel:" + Resource.heroAndSoldierLevel[i]);
		}
		for (int i = 0; i < Resource.guanqiaLevel.length; i++) {
			System.out.println("Resource.guanqiaLevel:" + Resource.guanqiaLevel[i]);
		}
		for (int i = 0; i < Resource.isBelongMyOfHero.length; i++) {
			System.out.println("Resource.isBelongMyOfHero:" + Resource.isBelongMyOfHero[i]);
		}
		for (int i = 0; i < engine.pm.props.length; i++) {
			System.out.println("engine.pm.props[" + i + "]:" + engine.pm.props[i].getNums());
		}
		for (int i = 0; i < Resource.isFinishAttainment.length; i++) {
			System.out.println("Resource.isFinishAttainment:" + Resource.isFinishAttainment[i]);
		}
		System.out.println("Resource.tongtiantafloor:" + Resource.tongtiantafloor);
		System.out.println("Resource.resumeNengliangTime:" + Resource.resumeNengliangTime);
		System.out.println("engine.my.tong:" + engine.my.getCountTongtianta());
		System.out.println("engine.my.Award:" + engine.my.getCountAward());
		System.out.println("isFirstGame:" + isFirstGame);
	}

}
