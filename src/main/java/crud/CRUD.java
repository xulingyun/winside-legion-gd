package crud;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cn.ohyeah.itvgame.model.GameRecord;
import cn.ohyeah.stb.game.ServiceWrapper;
import test.GameEngine;
import test.Resource;

public class CRUD {

	GameEngine engine;
	public static boolean result;

	public CRUD(GameEngine engine) {
		this.engine = engine;
	}

	public void init() {
		byte record[];
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);
		ServiceWrapper sw = engine.getServiceWrapper();
		try {
			setRecordData(dout);
			record = bout.toByteArray();
			GameRecord gameRecord = new GameRecord();
			gameRecord.setData(record);
			gameRecord.setScores(1000);
			// gameRecord.setPlayDuration(i);
			gameRecord.setRemark("存档");
			gameRecord.setRecordId(10009);
			sw.saveRecord(gameRecord);
		} catch (Exception e) {
			System.out.println("保存游戏失败，原因：" + e.getMessage());
			// state = STATUS_MAIN_MENU;
		} finally {
			try {
				dout.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void setRecordData(DataOutputStream dout) {
		try {
			for (int i = 0; i < 67;) {
				int j;
				for (j = 0; j < Resource.myProperty.length; i++,j++) {
					dout.writeLong(Resource.myProperty[j]);
				}
				System.out.println(Resource.myProperty.length);
				for (j = 0; j < Resource.heroAndSoldierLevel.length; i++,j++) {
					dout.writeLong(Resource.heroAndSoldierLevel[j]);
				}
				System.out.println(Resource.heroAndSoldierLevel.length);
				for (j = 0; j < Resource.guanqiaLevel.length; i++,j++) {
					dout.writeLong(Resource.guanqiaLevel[j]);
				}
				System.out.println(Resource.guanqiaLevel.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean readRecord() {
		ServiceWrapper sw = engine.getServiceWrapper();
		GameRecord gameRecord = sw.readRecord(10009);
		if (!sw.isServiceSuccessful() || gameRecord == null) {
			return result = false;
		}
		if (gameRecord.getPlayDuration() == -1) {
			return result = false;
		}
		ByteArrayInputStream bin = new ByteArrayInputStream(
				gameRecord.getData());
		DataInputStream din = new DataInputStream(bin);
		try {
			// StateGame.scores = gameRecord.getScores();
			initRecordInfo(din);
			return result = true;
		} catch (Exception e) {
			System.out.println("读取游戏失败，原因：" + e.getMessage());
			// state = STATUS_MAIN_MENU;
			return result = false;
		} finally {
			try {
				din.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void initRecordInfo(DataInputStream din) throws IOException {
		String s = "";
		for (int i = 0; i < din.available(); i++) {
			s += "****" + din.readLong();
		}
		System.out.println(s);

		// printInfo();
	}

}
