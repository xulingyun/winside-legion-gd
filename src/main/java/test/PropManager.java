package test;

import cn.ohyeah.stb.game.Recharge;
import cn.ohyeah.stb.game.SGraphics;
import cn.ohyeah.stb.game.ServiceWrapper;
import cn.ohyeah.stb.res.UIResource;
import cn.ohyeah.stb.ui.PopupConfirm;
import cn.ohyeah.stb.ui.PopupText;

public class PropManager {

	public PlayerProp[] props;
	private int[] propIds = { 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148,
			149 };
	private String[] propCode = { "HJ106", "HJ107", "HJ108", "HJ109", "HJ110", "HJ111", "HJ112",
			"HJ113", "HJ114", "HJ115", "HJ116", "HJ117", "HJ118", "HJ119", "HJ120" };
	private int[] price = { 30, 30, 10, 30, 30, 30, 10, 30, 30, 30, 30, 20, 10, 30, 30 };
	private String name[] = { "����ҩ��", "������", "����", "˫�����鿨", "˫����ҿ�", "����ʯ", "��������", "����֮��",
			"���Ի���", "����г��", "������", "�齱", "����ɨ��", "����", "�޾�֮��" };
	private String desc[] = { "�ָ�Ӣ��40%��HP����ս����ʹ�ã�", "ʹ������ʧ30%��HP����ս����ʹ�ã�", "ʿ���Ĺ�������������30�룩��ս����ʹ�ã�",
			"ս��ʤ����õľ��鷭����", "ս��ʤ����õĽ�ҷ�����", "ʹ�ú�����20��������", "ͨ����ս��ʧ�ܱ�����ʧ��", "��������ʿ��10%��������",
			"����Ӣ��20���������", "����Ӣ��20%������", "�һ�10000��ҡ�", "���³齱��", "�ӿ�ɨ�����ٶ�", "ս������", "���Լ���ȥ�޼�֮��" };
	GameEngine engine;

	public PropManager(GameEngine engine, PlayerProp[] props) {
		this.engine = engine;
		this.props = props;
	}

	/* ��ѯ��ҵ��� */
	public void queryProps() {
		initProps(props);
		// try {
		// for (int j = 0; j < props.length; j++) {
		// props[j].setNums(Resource.goodsNums[j]);
		// }
		// } catch (Exception e) {
		// initProps(props);
		// }
		printInfo();
	}

	public void printInfo() {
		for (int i = 0; i < props.length; i++) {
			System.out.println("����ID==" + props[i].getPropId());
			System.out.println("��������==" + props[i].getNums());
		}
	}

	public void initProps(PlayerProp[] props2) {
		// props = new PlayerProp[15];
		System.out.println("�����������ݲ���ʼ��������Ϣ,size:" + props.length);
		for (int i = 0; i < props.length; i++) {
			PlayerProp prop = new PlayerProp();
			prop.setPropId(propIds[i]);
			prop.setName(name[i]);
			prop.setPrice(price[i]);
			prop.setId(i);
			prop.setNums(0);
			prop.setDesc(desc[i]);
			prop.setFeeCode(0);
			// prop.setPropCode(propCode[i]);
			props[i] = prop;
		}

		for (int m = 0; m < props.length; m++) {
			System.out.println("propId:" + props[m].getPropId() + ", price:" + props[m].getPrice()
					+ ", name:" + props[m].getName());
		}
	}

	/* ���ݵ���ID��ѯ�õ������� */
	public PlayerProp getPropById(int propId) {
		int len = props.length;
		for (int i = len - 1; i >= 0; i--) {
			if (props[i].getPropId() == propId) {
				return props[i];
			}
		}
		return null;
	}

	public int getPropNumsById(int id) {
		int len = props.length;
		for (int i = len - 1; i >= 0; i--) {
			if (props[i].getPropId() == id) {
				return props[i].getNums();
			}
		}
		return 0;
	}

	public int getPriceById(int propId) {
		int len = props.length;
		for (int i = len - 1; i >= 0; i--) {
			if (props[i].getPropId() == propId) {
				return props[i].getPrice();
			}
		}
		return 0;
	}

	public String getNameById(int propId) {
		int len = props.length;
		for (int i = len - 1; i >= 0; i--) {
			if (props[i].getPropId() == propId) {
				return props[i].getName();
			}
		}
		return null;
	}

	public void addPropNum(int propId) {
		int len = props.length;
		for (int i = len - 1; i >= 0; i--) {
			if (props[i].getPropId() == propId) {
				props[i].setNums(props[i].getNums() + 1);
			}
		}
	}

	public void reducePropNum(int propId) {
		int len = props.length;
		for (int i = len - 1; i >= 0; i--) {
			if (props[i].getPropId() == propId) {
				props[i].setNums(props[i].getNums() - 1);
			}
		}
	}

	public boolean buyProp(int propId, int propCount, SGraphics g) {
		PlayerProp pp = getPropById(propId);
		System.out.println("pp=====>" + pp);
		if (engine.getEngineService().getBalance() < pp.getPrice()) {
			PopupConfirm pc = UIResource.getInstance().buildDefaultPopupConfirm();
			pc.setText(engine.getEngineService().getExpendAmountUnit() + "����,�Ƿ�ȥ��ֵ?");
			if (pc.popup() == 0) {
				Recharge recharge = new Recharge(engine);
				recharge.recharge();
				// if (g != null) {
				// engine.stateGame.show(g);
				// }
			}
			return false;
		} else {
			ServiceWrapper sw = engine.getServiceWrapper();
			// sw.purchaseProp(propId, propCount, "����"+propName);
			// sw.expend(pp.getPrice(), propId, "����"+pp.getName());
			sw.consume(1, pp.getPrice(), pp.getName());
			PopupText pt = UIResource.getInstance().buildDefaultPopupText();
			if (sw.isServiceSuccessful()) {
				pt.setText("����" + pp.getName() + "�ɹ�");
				addPropNum(propId);
			} else {
				pt.setText("����" + pp.getName() + "ʧ��, ԭ��: " + sw.getMessage());

			}
			pt.popup();
			return sw.isServiceSuccessful();
		}
	}

	/* ������Ϣ */
	public String setPropDatas() {
		String datas = "";
		for (int i = 0; i < props.length; i++) {
			datas += props[i].getNums();
			if (i != props.length - 1) {
				datas += "|";
			}
		}
		return datas;
	}
}
