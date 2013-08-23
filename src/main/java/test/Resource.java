package test;

import java.io.IOException;

import javax.microedition.lcdui.Image;

public class Resource {

	public final static int[] MonsterBlood = { 100, 20, 150, 25, 100, 20, 300, 25, 150, 18, 300,
			20, 200, 25, 400, 30, 150, 20, 150, 25, 150, 20, 150, 30, 150, 30, 1500, 0, 2000, 0,
			2500, 0, 3000, 0, 3500, 0, 4000, 0, 4500, 0, 5000, 0, 400, 25 };

	public final static int[] stoneXY = { 10, -100, 0, 90, -200, 0, 180, -340, 0, 120, -360, 0, 40,
			-400, 0 };

	public final static int[] stoneXY1 = { 270, -150, 0, 90, -250, 0, 200, -350, 0 };

	public final static int[] stoneXY2 = { 20, 60, 0, 150, 80, 0, 70, 150, 0 };
	public final static int[] MonsterAttack = { 15, 1, 10, 1, 25, 1, 20, 1, 35, 1, 30, 1, 50, 1,
			45, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 150, 0, 150, 0, 350, 0, 400, 0, 450, 0, 0, 0, 600,
			0, 700, 0, 60, 0 };
	public final static int[] MonsterSpeed = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20,
			15, 15, 15, 15, 10, 15, 15, 15, 10 };
	public final static int[] MonsterLevel = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1 };
	public final static int[] MonsterImgIndex = { 97, 98, 99, 100, 101, 102, 103, 104, 105, 106,
			107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 137 };

	public static byte[] heroAndSoldierLevel = { 1, 1, 1, 1, 0, 0, 0, 0, 0 };
	public static byte[] isBelongMyOfHero = { 1, 0, 0, 0, 0, 0 };

	public final static long[] heroMoney = { 1000, 10000, 25000, 50000, 60000, 90000 };

	public final static long[] soldierMoney = { 0, 0, 0, 0, 30000, 20000, 40000, 50000, 50000 };

	public static byte[] guanqiaLevel = { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	public final static int[][] heroValue = { { 400, 4, 1000, 100, 1, 4000, 100, 15 },
			{ 500, 5, 2000, 110, 1, 4000, 10, 3 }, { 600, 6, 3000, 120, 1, 5000, 120, 15 },
			{ 700, 7, 4000, 130, 1, 8000, 200, 30 }, { 800, 8, 5000, 140, 1, 5000, 200, 15 },
			{ 800, 9, 6000, 150, 1, 5000, 150, 20, 250, 25 } };
	public final static int[][] soldierValue = { { 50, 1000, 5, 1000, 0, 0 },
			{ 55, 3500, 5, 3500 }, { 45, 3000, 3, 3000, 5, 1 }, { 45, 2000, 5, 2000, 0, 0 },
			{ 55, 2500, 3, 2500 }, { 50, 4000, 5, 4000, 6, 1 }, { 55, 5000, 3, 5000 },
			{ 65, 5000, 5, 5000 }, { 70, 5000, 3, 5000, 20, 1 } };
	public static int[] goodsPrice = { 1000, 1000, 1000 };
	// public static byte[] goodsNums = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public final static int[] prop2Localtion = { 150, -150, 0, 0, 250, -150, 0, 0, 350, -50, 0, 0,
			300, 100, 0, 0, 200, 100, 0, 0, 200, 50, 0, 0, 250, -100, 0, 0 };
	public final static long[] guanqiaMoneyAndJingyan = { 400, 50, 300, 40 };
	public final static long[] TongMoneyAndJingyan = { 500, 100, 500, 40 };

	public final static String[] shopInfo = { "血瓶", "恢复英雄40%的血量。（战斗中使用）", "火球术",
			"使敌人损失30%的血量。（战斗中使用）", "狂暴术", "士兵的攻击力翻倍。（30秒）", "双倍经验卡", "战斗胜利获得的经验翻倍。", "双倍金币卡",
			"战斗胜利获得的金币翻倍。", "能量石", "使用后增加20点能量。", "保护符文", "通天塔战斗失败避免损失。", "军团之力", "增加所有士兵10%攻击力。",
			"刚性护符", "增加英雄20点防御力。", "生命谐音", "增加英雄20%生命。", "金币礼包", "兑换10000金币。" };

	public final static String[] heroSkillInfo = { "对一条线上的所有敌人造成伤害", "提升防御力", "对一条线上的敌人造成伤害，并昏迷3秒",
			"恢复英雄生命", "对全部的敌人造成伤害", "对全部的敌人造成伤害，并恢复英雄生命" };

	public final static String[] heroSkillName = { "严惩之风", "神盾加护", "凝魂波动", "圣愈术", "星陨连击", "炼狱雷袭" };

	public final static String[] heroName = { "灾祸之刃-纳亚", "迅捷射手-奥拉尔", "战争堡垒—钢德", "审判之剑-哥顿",
			"生命之光-莉莉丝", "龙法师-绯色" };

	public final static String[] soldierNameAndSomething = { "步兵", "近战", "最普通的兵种。", "骑兵", "近战",
			"攻击具有穿透效果。", "猎人", "远程", "攻击具有中毒效果。", "锤兵", "近战", "可以打断敌人施法。", "牧师", "远程", "攻击具有回血效果。",
			"狂战士", "近战", "攻击具有溅射效果", "魔法师", "远程", "召唤火球攻击敌人。", "刺客", "近战", "可以连续攻击两次。", "火枪手",
			"远程", "有一定机率产生爆击。" };

	public final static String[] soldierNameUp = { "步兵", "剑士", "近卫军", "骑兵", "骑士", "金甲骑卫", "猎人",
			"弓箭手", "游侠", "锤兵", "铁锤兵", "钢铁守卫", "牧师", "神官", "光明祭祀", "狂战士", "狂暴者", "神灵武士", "魔法师",
			"大魔法师", "魔导师", "刺客", "暗杀者", "禁军统领", "火枪手", "鹰眼", "狙击手" };

	public static String[] AttainmentName = { "牛刀小试", "出入草原", "草原征服者", "地底冒险", "地心游记", "冰天雪地",
			"风雪之王", "炽热战场", "地狱之主", "探险者", "再接再厉", "优胜者", "所向披靡", "初出茅庐", "小有所成", "势不可挡", "战争之王",
			"杀戮盛宴", "清道夫", "一骑当千", "虚空之王", "魔法之王", "灵魂医者", "步战勇士", "构装骑士", "丛林主宰", "风暴之锤", "生命之光",
			"野蛮人", "传奇法师", "刺客信条", "狙击专家" };
	public static String[] AttainmentCondition = { "完成新手指导。", "击败索米亚草原的boss1", "击败索米亚草原的boss2",
			"击败怨灵大陆的boss1", "击败怨灵大陆的boss2", "击败冰霜大陆的boss1", "击败冰霜大陆的boss2", "击败熔岩之岛的boss1",
			"击败熔岩之岛的boss2", "通天塔第10层取得胜利", "通天塔第20层取得胜利", "通天塔第30层取得胜利", "通天塔第40层取得胜利",
			"索米亚草原的所有关卡取得3星", "怨灵地窟的所有关卡取得3星", "冰霜大陆的所有关卡取得3星", "熔岩之岛的所有关卡取得3星", "英雄1升到20级",
			"英雄2升到20级", "英雄3升到20级", "英雄4升到20级", "英雄5升到20级", "英雄6升到20级", "步兵升到20级", "骑士升到20级",
			"猎人升到20级", "锤兵升到20级", "牧师升到20级", "狂战士升到20级", "魔法兵升到20级", "刺客升到20级", "火枪兵升到20级" };
	public static byte[] isFinishAttainment = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int[] AttainmentMoney = { 5000, 5000, 5000, 5000, 5000, 10000, 10000, 10000,
			10000, 5000, 5000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,
			10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000 };
	public static int tongtiantafloor = 0;
	public static byte saveSelectHero = 0;
	public static byte[] savaSelcetSoldier = { 0, 1, 2, 3 };
	public static long resumeNengliangTime;

	public static short NUMS = 0;
	public static final short soldier0 = NUMS++;// 0
	public static final short soldier1 = NUMS++;// 1
	public static final short soldier2 = NUMS++;// 2
	public static final short soldier3 = NUMS++;// 3
	public static final short soldier4 = NUMS++;// 4
	public static final short soldier5 = NUMS++;// 5
	public static final short soldier6 = NUMS++;// 6
	public static final short soldier7 = NUMS++;// 7
	public static final short soldier8 = NUMS++;// 8
	public static final short arrows = NUMS++;// 9
	public static final short back0 = NUMS++;// 10
	public static final short hero = NUMS++;// 11
	public static final short selectbox1 = NUMS++;// 12
	public static final short selectbox2 = NUMS++;// 13
	public static final short select2 = NUMS++;// 14
	public static final short star = NUMS++;// 15
	public static final short number = NUMS++;// 16
	public static final short money = NUMS++;// 17
	public static final short newbox = NUMS++;// 18
	public static final short newtext = NUMS++;// 19
	public static final short chengjiupanel = NUMS++;// 20
	public static final short awardbutton = NUMS++;// 21
	public static final short bg6 = NUMS++;// 22
	public static final short bg10 = NUMS++;// 23
	public static final short bg11 = NUMS++;// 24
	public static final short bgleft = NUMS++;// 25
	public static final short bgmiddle = NUMS++;// 26
	public static final short bgright = NUMS++;// 27
	public static final short biankuang = NUMS++;// 28
	public static final short bingzhonginfo = NUMS++;// 29
	public static final short button1 = NUMS++;// 30
	public static final short button2 = NUMS++;// 31
	public static final short buttonbg = NUMS++;// 32
	public static final short buttontext1 = NUMS++;// 33
	public static final short buttontext2 = NUMS++;// 34
	public static final short buyandshengji = NUMS++;// 35
	public static final short caifu = NUMS++;// 36
	public static final short fire = NUMS++;// 37
	public static final short grasslands = NUMS++;// 38
	public static final short guanqia = NUMS++;// 39
	public static final short guanqiapj = NUMS++;// 40
	public static final short heroinfoall = NUMS++;// 41
	public static final short guanqiatext = NUMS++;// 42
	public static final short herobig = NUMS++;// 43
	public static final short heroinfo = NUMS++;// 44
	public static final short ice = NUMS++;// 45
	public static final short leftright = NUMS++;// 46
	public static final short login = NUMS++;// 47
	public static final short logintext = NUMS++;// 48
	public static final short myherotext = NUMS++;// 49
	public static final short mysoldertext = NUMS++;// 50
	public static final short newcomer = NUMS++;// 51
	public static final short selecthero = NUMS++;// 52
	public static final short numberbig = NUMS++;// 53
	public static final short panel1 = NUMS++;// 54
	public static final short pit = NUMS++;// 55
	public static final short queding = NUMS++;// 56
	public static final short selectforce = NUMS++;// 57
	public static final short shopbag = NUMS++;// 58
	public static final short shopbar = NUMS++;// 59
	public static final short shopleft = NUMS++;// 60
	public static final short shopmiddle = NUMS++;// 61
	public static final short shopright = NUMS++;// 62
	public static final short shopselect = NUMS++;// 63
	public static final short shoptext = NUMS++;// 64
	public static final short shopthingnum = NUMS++;// 65
	public static final short shoptool = NUMS++;// 66
	public static final short shopx1 = NUMS++;// 67
	public static final short shopx2 = NUMS++;// 68
	public static final short somewherename = NUMS++;// 69
	public static final short titlebingzhong = NUMS++;// 70
	public static final short titlehero = NUMS++;// 71
	public static final short udanddown = NUMS++;// 72
	public static final short wujin = NUMS++;// 73
	public static final short awardtext = NUMS++;// 74
	public static final short jbar = NUMS++;// 75
	public static final short nbar = NUMS++;// 76
	public static final short help1 = NUMS++;// 77
	public static final short help2 = NUMS++;// 78
	public static final short use = NUMS++;// 79
	public static final short attainment_icon = NUMS++;// 80
	public static final short attainment_finish = NUMS++;// 81
	public static final short stopandgo = NUMS++;// 82
	public static final short fight = NUMS++;// 83
	public static final short lock = NUMS++;// 84
	public static final short gongxini = NUMS++;// 85
	public static final short daojishinum = NUMS++;// 86
	public static final short upgreat = NUMS++;// 87
	public static final short uparrow = NUMS++;// 88
	public static final short bg0 = NUMS++;// 89
	public static final short bg1 = NUMS++;// 90
	public static final short bg2 = NUMS++;// 91
	public static final short bg3 = NUMS++;// 92
	public static final short monster1 = NUMS++;// 93
	public static final short monster2 = NUMS++;// 94
	public static final short monster3 = NUMS++;// 95
	public static final short monster4 = NUMS++;// 96
	public static final short monster5 = NUMS++;// 97
	public static final short monster6 = NUMS++;// 98
	public static final short monster7 = NUMS++;// 99
	public static final short monster8 = NUMS++;// 100
	public static final short monster9 = NUMS++;// 101
	public static final short monster10 = NUMS++;// 102
	public static final short monster11 = NUMS++;// 103
	public static final short monster12 = NUMS++;// 104
	public static final short monster13 = NUMS++;// 105
	public static final short boss1 = NUMS++;// 106
	public static final short boss2 = NUMS++;// 107
	public static final short boss3 = NUMS++;// 108
	public static final short boss4 = NUMS++;// 109
	public static final short boss5 = NUMS++;// 110
	public static final short boss6 = NUMS++;// 111
	public static final short boss7 = NUMS++;// 112
	public static final short boss8 = NUMS++;// 113
	public static final short bloodmagic = NUMS++;// 114
	public static final short bmbar = NUMS++;// 115
	public static final short powerbar = NUMS++;// 116
	public static final short powerbarbg = NUMS++;// 117
	public static final short powerbg1 = NUMS++;// 118
	public static final short powerbg2 = NUMS++;// 119
	public static final short herobg = NUMS++;// 120
	public static final short toolnumkey = NUMS++;// 121
	public static final short zhandouleft = NUMS++;// 122
	public static final short zhandoumiddle = NUMS++;// 123
	public static final short zhandouright = NUMS++;// 124
	public static final short monsterdie = NUMS++;// 125
	public static final short attackhero = NUMS++;// 126
	public static final short boss1attack = NUMS++;// 127
	public static final short boss2attack = NUMS++;// 128
	public static final short boss3attack1 = NUMS++;// 129
	public static final short boss3attack2 = NUMS++;// 130
	public static final short boss4attack = NUMS++;// 131
	public static final short boss5attack = NUMS++;// 132
	public static final short boss6m = NUMS++;// 133
	public static final short boss6mattack = NUMS++;// 134
	public static final short boss78attack = NUMS++;// 135
	public static final short soldier21 = NUMS++;// 136
	public static final short soldier22 = NUMS++;// 137
	public static final short soldier31 = NUMS++;// 138
	public static final short soldier32 = NUMS++;// 139
	public static final short soldier81 = NUMS++;// 140
	public static final short soldier91 = NUMS++;// 141
	public static final short bullet1 = NUMS++;// 142
	public static final short bullet2 = NUMS++;// 143
	public static final short powerbg3 = NUMS++;// 144
	public static final short bosspower1 = NUMS++;// 145
	public static final short bosspower2 = NUMS++;// 146
	public static final short bossblood1 = NUMS++;// 147
	public static final short bossblood2 = NUMS++;// 148
	public static final short hero1skill = NUMS++;// 149
	public static final short attacknumber = NUMS++;// 150
	public static final short baojibg = NUMS++;// 151
	public static final short stopimg = NUMS++;// 152
	public static final short hero4skill = NUMS++;// 153
	public static final short boss87attack = NUMS++;// 154
	public static final short hero3skill = NUMS++;// 155
	public static final short hero5skill = NUMS++;// 156
	public static final short hero6skill = NUMS++;// 157
	public static final short soldier51 = NUMS++;// 158
	public static final short soldier52 = NUMS++;// 159
	public static final short soldier53 = NUMS++;// 160
	public static final short soldier92 = NUMS++;// 161
	public static final short soldier71 = NUMS++;// 162
	public static final short soldier33 = NUMS++;// 163
	public static final short hero2skill = NUMS++;// 164
	public static final short shenglishibai = NUMS++;// 165
	public static final short star1 = NUMS++;// 166
	public static final short buff = NUMS++;// 167
	public static final short herosay1 = NUMS++;// 168
	public static final short herosay2 = NUMS++;// 169
	public static final short shanmove = NUMS++;// 170
	public static final short zhongdu = NUMS++;// 171
	public static final short heroskillsrc = NUMS++;// 172
	public static final short soldier121 = NUMS++;// 173
	public static final short soldier122 = NUMS++;// 174
	public static final short xingyunchoujiang = NUMS++;// 175
	public static final short IMG_ID_RECHARGE_CZBJ = NUMS++;// 176
	public static final short IMG_ID_RECHARGE_CZBJ_2 = NUMS++;// 177
	public static final short IMG_ID_PROMPT_POPUP_BG = NUMS++;// 178
	public static final short IMG_ID_PROMPT_QUEREN_ON = NUMS++;// 179
	public static final short IMG_ID_PROMPT_QUEREN = NUMS++;// 180
	public static final short wave = NUMS++;// 181
	public static final short dongxi = NUMS++;// 181
	public static final short shoprecord = NUMS++;// 182

	public static final byte isfirst = 0;

	public static final String[] PAGE_INDEX = { "PASS", "HOME_PAGE0", "GAME_PAGE" };

	public static final Image[] images = new Image[NUMS];

	static String[] somePic = { "/common/1.png", "/common/2.png", "/common/3.png", "/common/4.png",
			"/common/5.png", "/common/6.png", "/common/7.png", "/common/8.png", "/common/9.png",
			"/common/arrows.png", "/common/back0.png", "/common/hero.png",
			"/common/selectbox1.png", "/common/selectbox2.png", "/common/select2.png",
			"/common/star.png", "/common/number.png", "/common/money.png", "/common/newbox.png",
			"/common/newtext.png", "/common/chengjiupanel.png",

			"/firstpage/awardbutton.png", "/firstpage/bg6.jpg", "/firstpage/bg10.jpg",
			"/firstpage/bg11.jpg", "/firstpage/bgleft.jpg", "/firstpage/bgmiddle.jpg",
			"/firstpage/bgright.jpg", "/firstpage/biankuang.png", "/firstpage/bingzhonginfo.png",
			"/firstpage/button1.png", "/firstpage/button2.png", "/firstpage/buttonbg.png",
			"/firstpage/buttontext1.png", "/firstpage/buttontext2.png",
			"/firstpage/buyandshengji.png", "/firstpage/caifu.png", "/firstpage/fire.png",
			"/firstpage/grasslands.png", "/firstpage/guanqia.png", "/firstpage/guanqiapj.png",
			"/firstpage/heroinfoall.png", "/firstpage/guanqiatext.png", "/firstpage/herobig.png",
			"/firstpage/heroinfo.png", "/firstpage/ice.png", "/firstpage/leftright.png",
			"/firstpage/login.jpg", "/firstpage/logintext.png", "/firstpage/myherotext.png",
			"/firstpage/mysoldertext.png", "/firstpage/newcomer.png", "/firstpage/selecthero.png",
			"/firstpage/numberbig.png", "/firstpage/panel1.png", "/firstpage/pit.png",
			"/firstpage/queding.png", "/firstpage/selectforce.png", "/firstpage/shopbag.png",
			"/firstpage/shopbar.jpg", "/firstpage/shopleft.jpg", "/firstpage/shopmiddle.jpg",
			"/firstpage/shopright.jpg", "/firstpage/shopselect.png", "/firstpage/shoptext.png",
			"/firstpage/shopthingnum.png", "/firstpage/shoptool.png", "/firstpage/shopx1.png",
			"/firstpage/shopx2.png", "/firstpage/somewherename.png",
			"/firstpage/titlebingzhong.png", "/firstpage/titlehero.png",
			"/firstpage/udanddown.png", "/firstpage/wujin.png", "/firstpage/awardtext.png",
			"/firstpage/jbar.png", "/firstpage/nbar.png", "/firstpage/help1.png",
			"/firstpage/help2.png", "/firstpage/use.png", "/firstpage/attainment_icon.png",
			"/firstpage/attainment_finish.png", "/firstpage/stopandgo.png", "/firstpage/fight.png",
			"/firstpage/lock.png", "/firstpage/gongxini.png", "/firstpage/daojishinum.png",
			"/firstpage/upgreat.png", "/firstpage/uparrow.png",

			"/secondpage/bg0.jpg", "/secondpage/bg1.jpg", "/secondpage/bg2.jpg",
			"/secondpage/bg3.jpg", "/secondpage/monster1.png", "/secondpage/monster2.png",
			"/secondpage/monster3.png", "/secondpage/monster4.png", "/secondpage/monster5.png",
			"/secondpage/monster6.png", "/secondpage/monster7.png", "/secondpage/monster8.png",
			"/secondpage/monster9.png", "/secondpage/monster10.png", "/secondpage/monster11.png",
			"/secondpage/monster12.png", "/secondpage/monster13.png", "/secondpage/boss1.png",
			"/secondpage/boss2.png", "/secondpage/boss3.png", "/secondpage/boss4.png",
			"/secondpage/boss5.png", "/secondpage/boss6.png", "/secondpage/boss7.png",
			"/secondpage/boss8.png", "/secondpage/bloodmagic.png", "/secondpage/bmbar.png",
			"/secondpage/powerbar.png", "/secondpage/powerbarbg.png", "/secondpage/powerbg1.png",
			"/secondpage/powerbg2.png", "/secondpage/herobg.png", "/secondpage/toolnumkey.png",
			"/secondpage/zhandouleft.jpg", "/secondpage/zhandoumiddle.jpg",
			"/secondpage/zhandouright.jpg", "/secondpage/monsterdie.png",
			"/secondpage/attackhero.png", "/secondpage/boss1attack.png",
			"/secondpage/boss2attack.png", "/secondpage/boss3attack1.png",
			"/secondpage/boss3attack2.png", "/secondpage/boss4attack.png",
			"/secondpage/boss5attack.png", "/secondpage/boss6m.png",
			"/secondpage/boss6mattack.png", "/secondpage/boss78attack.png",
			"/secondpage/soldier21.png", "/secondpage/soldier22.png", "/secondpage/soldier31.png",
			"/secondpage/soldier32.png", "/secondpage/soldier81.png", "/secondpage/soldier91.png",
			"/secondpage/bullet1.png", "/secondpage/bullet2.png", "/secondpage/powerbg3.png",
			"/secondpage/bosspower1.png", "/secondpage/bosspower2.png",
			"/secondpage/bossblood1.png", "/secondpage/bossblood2.jpg",
			"/secondpage/hero1skill.png", "/secondpage/attacknumber.png",
			"/secondpage/baojibg.png", "/secondpage/stopimg.png", "/secondpage/hero4skill.png",
			"/secondpage/boss87attack.png", "/secondpage/hero3skill.png",
			"/secondpage/hero5skill.png", "/secondpage/hero6skill.png",
			"/secondpage/soldier51.png", "/secondpage/soldier52.png", "/secondpage/soldier53.png",
			"/secondpage/soldier92.png", "/secondpage/soldier71.png", "/secondpage/soldier33.png",
			"/secondpage/hero2skill.png", "/secondpage/shenglishibai.png", "/secondpage/star1.png",
			"/secondpage/buff.png", "/secondpage/herosay1.png", "/secondpage/herosay2.png",
			"/secondpage/shanmove.png", "/secondpage/zhongdu.png",

			"/secondpage/heroskillsrc.png", "/secondpage/soldier121.png",
			"/secondpage/soldier122.png", "/secondpage/xingyunchoujiang.png", "/recharge/czbg.png",
			"/recharge/czbg_.png", "/prompt/popup_bg.png", "/prompt/queren_on.png",
			"/prompt/queren.png", "/secondpage/wave.png", "/secondpage/dongxi.png",
			"/firstpage/shoprecord.jpg"

	};

	public static Image loadImage(int id) {
		if (images[id] == null) {
			try {
				images[id] = Image.createImage(somePic[id]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return images[id];
	}

	public static void releaseImage(int resId) {
		images[resId] = null;
	}

	public static void loadLogo() {
		loadImage(login);
		loadImage(logintext);
	}

	public static void UnloadLogo() {
		releaseImage(login);
		releaseImage(logintext);
	}

	public static void loadMain() {
		loadImage(bg10);
		loadImage(newcomer);
		loadImage(grasslands);
		loadImage(pit);
		loadImage(ice);
		loadImage(fire);
		loadImage(wujin);
		loadImage(caifu);
		loadImage(somewherename);
		loadImage(guanqiatext);
		loadImage(help1);
		loadImage(arrows);
	}

	public static void UnloadMain() {
		releaseImage(bg10);
		releaseImage(newcomer);
		releaseImage(grasslands);
		releaseImage(pit);
		releaseImage(ice);
		releaseImage(fire);
		releaseImage(wujin);
		releaseImage(caifu);
		releaseImage(somewherename);
		releaseImage(guanqiatext);
		releaseImage(help1);
		releaseImage(arrows);
	}

	public static void loadMainInfo() {
		loadImage(heroinfo);
		loadImage(jbar);
		loadImage(nbar);
		loadImage(daojishinum);
	}

	public static void UnloadMainInfo() {
		releaseImage(heroinfo);
		releaseImage(jbar);
		releaseImage(nbar);
		releaseImage(daojishinum);
	}

	public static void loadHero() {
		loadImage(bgleft);
		loadImage(bgmiddle);
		loadImage(bgright);
		loadImage(titlehero);
		loadImage(hero);
		loadImage(selecthero);
		loadImage(herobig);
		loadImage(button1);
		loadImage(buyandshengji);
		loadImage(buttontext1);
		loadImage(buttonbg);
		loadImage(money);
		loadImage(panel1);
		loadImage(heroinfoall);
		loadImage(uparrow);
		loadImage(back0);
		loadImage(bg11);
		loadImage(biankuang);
		loadImage(select2);
		loadImage(number);
	}

	public static void UnloadHero() {
		releaseImage(bgleft);
		releaseImage(bgmiddle);
		releaseImage(bgright);
		releaseImage(titlehero);
		releaseImage(hero);
		releaseImage(selecthero);
		releaseImage(herobig);
		releaseImage(button1);
		releaseImage(buyandshengji);
		releaseImage(buttontext1);
		releaseImage(buttonbg);
		releaseImage(money);
		releaseImage(panel1);
		releaseImage(heroinfoall);
		releaseImage(uparrow);
		releaseImage(back0);
		releaseImage(bg11);
		releaseImage(biankuang);
		releaseImage(select2);
		releaseImage(number);
	}

	public static void loadSoldier() {
		loadImage(bgleft);
		loadImage(bgmiddle);
		loadImage(bgright);
		loadImage(titlebingzhong);
		loadImage(udanddown);
		loadImage(soldier0);
		loadImage(soldier1);
		loadImage(soldier2);
		loadImage(soldier3);
		loadImage(soldier4);
		loadImage(soldier5);
		loadImage(soldier6);
		loadImage(soldier7);
		loadImage(soldier8);
		loadImage(select2);
		loadImage(selectbox2);
		loadImage(button1);
		loadImage(buyandshengji);
		loadImage(buttontext1);
		loadImage(buttonbg);
		loadImage(money);
		loadImage(panel1);
		loadImage(bingzhonginfo);
		loadImage(uparrow);
		loadImage(selectforce);
		loadImage(back0);
		loadImage(upgreat);
		loadImage(bg11);
		loadImage(biankuang);
		loadImage(number);
	}

	public static void UnloadSoldier() {
		releaseImage(bgleft);
		releaseImage(bgmiddle);
		releaseImage(bgright);
		releaseImage(titlebingzhong);
		releaseImage(udanddown);
		releaseImage(soldier0);
		releaseImage(soldier1);
		releaseImage(soldier2);
		releaseImage(soldier3);
		releaseImage(soldier4);
		releaseImage(soldier5);
		releaseImage(soldier6);
		releaseImage(soldier7);
		releaseImage(soldier8);
		releaseImage(select2);
		releaseImage(selectbox2);
		releaseImage(button1);
		releaseImage(buyandshengji);
		releaseImage(buttontext1);
		releaseImage(buttonbg);
		releaseImage(money);
		releaseImage(panel1);
		releaseImage(bingzhonginfo);
		releaseImage(uparrow);
		releaseImage(selectforce);
		releaseImage(back0);
		releaseImage(upgreat);
		releaseImage(bg11);
		releaseImage(biankuang);
		releaseImage(number);
	}

	public static void loadShop() {
		loadImage(shopleft);
		loadImage(shopmiddle);
		loadImage(shopright);
		loadImage(shopselect);
		loadImage(shoptext);
		loadImage(shopbar);
		loadImage(shoptool);
		loadImage(awardtext);
		loadImage(leftright);
		loadImage(shopx2);
		loadImage(shopx1);
		loadImage(shopbag);
		loadImage(select2);
		loadImage(shopthingnum);
		loadImage(button1);
		loadImage(use);
		loadImage(arrows);
		loadImage(back0);
		loadImage(number);
		loadImage(bg11);
	}

	public static void UnloadShop() {
		releaseImage(shopleft);
		releaseImage(shopmiddle);
		releaseImage(shopright);
		releaseImage(shopselect);
		releaseImage(shoptext);
		releaseImage(shopbar);
		releaseImage(shoptool);
		releaseImage(awardtext);
		releaseImage(leftright);
		releaseImage(shopx2);
		releaseImage(shopx1);
		releaseImage(shopbag);
		releaseImage(select2);
		releaseImage(shopthingnum);
		releaseImage(button1);
		releaseImage(use);
		releaseImage(arrows);
		releaseImage(back0);
		releaseImage(number);
		releaseImage(bg11);
	}

	public static void loadAttainment() {
		loadImage(bgleft);
		loadImage(bgmiddle);
		loadImage(bgright);
		loadImage(attainment_icon);
		loadImage(attainment_finish);
		loadImage(leftright);
		loadImage(back0);
		loadImage(bg11);
		loadImage(biankuang);
	}

	public static void UnloadAttainment() {
		releaseImage(bgleft);
		releaseImage(bgmiddle);
		releaseImage(bgright);
		releaseImage(attainment_icon);
		releaseImage(attainment_finish);
		releaseImage(leftright);
		releaseImage(back0);
		releaseImage(bg11);
		releaseImage(biankuang);
	}

	public static void loadHelp() {
		loadImage(help2);
	}

	public static void UnloadHelp() {
		releaseImage(help2);
	}

	public static void loadAward() {
		loadImage(bgleft);
		loadImage(bgmiddle);
		loadImage(bgright);
		loadImage(select2);
		loadImage(shoptool);
		loadImage(awardbutton);
		loadImage(awardtext);
		loadImage(shopx2);
		loadImage(panel1);
		loadImage(gongxini);
		loadImage(herobg);
		loadImage(money);
		loadImage(button1);
		loadImage(queding);
		loadImage(arrows);
		loadImage(back0);
		loadImage(bg6);
		loadImage(number);
		loadImage(guanqiatext);
	}

	public static void UnloadAward() {
		releaseImage(bgleft);
		releaseImage(bgmiddle);
		releaseImage(bgright);
		releaseImage(select2);
		releaseImage(shoptool);
		releaseImage(awardbutton);
		releaseImage(awardtext);
		releaseImage(shopx2);
		releaseImage(panel1);
		releaseImage(gongxini);
		releaseImage(herobg);
		releaseImage(money);
		releaseImage(button1);
		releaseImage(queding);
		releaseImage(arrows);
		releaseImage(back0);
		releaseImage(bg6);
		releaseImage(number);
		releaseImage(guanqiatext);
	}

	public static void loadSelectGuanqia() {
		loadImage(bg11);
		loadImage(panel1);
		loadImage(somewherename);
		loadImage(guanqiatext);
		loadImage(numberbig);
		loadImage(guanqia);
		loadImage(star);
		loadImage(arrows);
		loadImage(back0);
	}

	public static void UnloadSelectGuanqia() {
		releaseImage(bg11);
		releaseImage(panel1);
		releaseImage(somewherename);
		releaseImage(guanqiatext);
		releaseImage(numberbig);
		releaseImage(guanqia);
		releaseImage(star);
		releaseImage(arrows);
		releaseImage(back0);
	}

	public static void loadSoonStart() {
		loadImage(bg11);
		loadImage(panel1);
		loadImage(somewherename);
		loadImage(guanqiatext);
		loadImage(guanqiapj);
		loadImage(button1);
		loadImage(buttontext1);
		loadImage(myherotext);
		loadImage(mysoldertext);
		loadImage(hero);
		loadImage(select2);
		loadImage(button2);
		loadImage(buttontext2);
		loadImage(star);
		loadImage(back0);
		loadImage(arrows);
	}

	public static void UnloadSoonStart() {
		releaseImage(bg11);
		releaseImage(panel1);
		releaseImage(somewherename);
		releaseImage(guanqiatext);
		releaseImage(guanqiapj);
		releaseImage(button1);
		releaseImage(buttontext1);
		releaseImage(myherotext);
		releaseImage(mysoldertext);
		releaseImage(hero);
		releaseImage(select2);
		releaseImage(button2);
		releaseImage(buttontext2);
		releaseImage(star);
		releaseImage(back0);
		releaseImage(arrows);

	}

	public static void loadSoonStartSoldier(byte[] selectSolder) {
		for (int i = 0; i < 4; i++) {
			if (selectSolder[i] == 0) {
				loadImage(soldier0);
			} else if (selectSolder[i] == 1) {
				loadImage(soldier1);
			} else if (selectSolder[i] == 2) {
				loadImage(soldier2);
			} else if (selectSolder[i] == 3) {
				loadImage(soldier3);
			} else if (selectSolder[i] == 4) {
				loadImage(soldier4);
			} else if (selectSolder[i] == 5) {
				loadImage(soldier5);
			} else if (selectSolder[i] == 6) {
				loadImage(soldier6);
			} else if (selectSolder[i] == 7) {
				loadImage(soldier7);
			} else if (selectSolder[i] == 8) {
				loadImage(soldier8);
			}
		}
	}

	public static void UnloadSoonStartSoldier() {
		releaseImage(soldier0);
		releaseImage(soldier1);
		releaseImage(soldier2);
		releaseImage(soldier3);
		releaseImage(soldier4);
		releaseImage(soldier5);
		releaseImage(soldier6);
		releaseImage(soldier7);
		releaseImage(soldier8);
	}

	public static void loadSaodang() {
		loadImage(dongxi);
		loadImage(panel1);
		loadImage(newbox);
		loadImage(newtext);
		loadImage(stopandgo);
		loadImage(queding);
	}

	public static void UnloadSaodang() {
		releaseImage(panel1);
		releaseImage(newbox);
		releaseImage(newtext);
		releaseImage(stopandgo);
		releaseImage(queding);
		releaseImage(dongxi);

	}

	public static void loadMoveAtt() {
		loadImage(chengjiupanel);
		loadImage(attainment_finish);
	}

	public static void UnloadMoveAtt() {
		releaseImage(chengjiupanel);
		releaseImage(attainment_finish);
	}

	public static void loadBg(int index) {
		if ((index - 1) % 4 == 0) {
			loadImage(bg0);
		} else if ((index - 1) % 4 == 1) {
			loadImage(bg1);
		} else if ((index - 1) % 4 == 2) {
			loadImage(bg2);
		} else if ((index - 1) % 4 == 3) {
			loadImage(bg3);
		}
	}

	public static void UnloadBg(int index) {
		if (index == 0) {
			releaseImage(bg0);
		} else if (index == 1) {
			releaseImage(bg1);
		} else if (index == 2) {
			releaseImage(bg2);
		} else if (index == 3) {
			releaseImage(bg3);
		}
	}

	public static void loadGameHero(int index) {
		loadImage(hero);
		if (index == 0) {
			loadImage(hero1skill);
		} else if (index == 1) {
			loadImage(hero2skill);
		} else if (index == 2) {
			loadImage(hero3skill);
		} else if (index == 3) {
			loadImage(hero4skill);
		} else if (index == 4) {
			loadImage(hero5skill);
		} else if (index == 5) {
			loadImage(hero6skill);
		}
		loadImage(herobg);
		loadImage(bloodmagic);
		loadImage(shanmove);
		loadImage(zhandouleft);
		loadImage(zhandoumiddle);
		loadImage(zhandouright);
		loadImage(heroskillsrc);
		loadImage(toolnumkey);
		loadImage(shoptool);
		loadImage(shopthingnum);
		loadImage(back0);
		loadImage(bmbar);
		loadImage(buff);
		loadImage(select2);
		loadImage(hero4skill);
		loadImage(soldier71);
	}

	public static void UnloadGameHero(int index) {
		releaseImage(hero);
		if (index == 0) {
			releaseImage(hero1skill);
		} else if (index == 1) {
			releaseImage(hero2skill);
		} else if (index == 2) {
			releaseImage(hero3skill);
		} else if (index == 3) {
			releaseImage(hero4skill);
		} else if (index == 4) {
			releaseImage(hero5skill);
		} else if (index == 5) {
			releaseImage(hero6skill);
		}
		releaseImage(herobg);
		releaseImage(bloodmagic);
		releaseImage(shanmove);
		releaseImage(zhandouleft);
		releaseImage(zhandoumiddle);
		releaseImage(zhandouright);
		releaseImage(heroskillsrc);
		releaseImage(toolnumkey);
		releaseImage(shoptool);
		releaseImage(shopthingnum);
		releaseImage(back0);
		releaseImage(bmbar);
		releaseImage(buff);
		releaseImage(select2);
		releaseImage(hero4skill);
		releaseImage(soldier71);
	}

	public static void loadGameSoldier(byte[] selectS) {
		for (int i = 0; i < selectS.length; i++) {
			if (selectS[i] == 0) {
				loadImage(soldier0);
			} else if (selectS[i] == 1) {
				loadImage(soldier1);
				loadImage(soldier21);
				loadImage(soldier22);
			} else if (selectS[i] == 2) {
				loadImage(soldier2);
				loadImage(soldier31);
				loadImage(soldier32);
				loadImage(soldier33);
				loadImage(zhongdu);
			} else if (selectS[i] == 3) {
				loadImage(soldier3);
			} else if (selectS[i] == 4) {
				loadImage(soldier4);
				loadImage(soldier51);
				loadImage(soldier52);
				loadImage(soldier53);
			} else if (selectS[i] == 5) {
				loadImage(soldier5);
			} else if (selectS[i] == 6) {
				loadImage(soldier6);
				loadImage(soldier71);
			} else if (selectS[i] == 7) {
				loadImage(soldier7);
				loadImage(soldier81);
			} else if (selectS[i] == 8) {
				loadImage(soldier8);
				loadImage(soldier91);
				loadImage(soldier92);
				loadImage(baojibg);
			}
		}
		loadImage(attacknumber);
		loadImage(selectbox1);
		loadImage(selectbox2);
	}

	public static void UnloadGameSoldier(byte[] selectS) {
		for (int i = 0; i < selectS.length; i++) {
			if (selectS[i] == 0) {
				releaseImage(soldier0);
			} else if (selectS[i] == 1) {
				releaseImage(soldier1);
				releaseImage(soldier21);
				releaseImage(soldier22);
			} else if (selectS[i] == 2) {
				releaseImage(soldier2);
				releaseImage(soldier31);
				releaseImage(soldier32);
				releaseImage(soldier33);
				releaseImage(zhongdu);
			} else if (selectS[i] == 3) {
				releaseImage(soldier3);
			} else if (selectS[i] == 4) {
				releaseImage(soldier4);
				releaseImage(soldier51);
				releaseImage(soldier52);
				releaseImage(soldier53);
			} else if (selectS[i] == 5) {
				releaseImage(soldier5);
			} else if (selectS[i] == 6) {
				releaseImage(soldier6);
				releaseImage(soldier71);
			} else if (selectS[i] == 7) {
				releaseImage(soldier7);
				releaseImage(soldier81);
			} else if (selectS[i] == 8) {
				releaseImage(soldier8);
				releaseImage(soldier91);
				releaseImage(soldier92);
				releaseImage(baojibg);
			}
		}
		releaseImage(attacknumber);
		releaseImage(selectbox1);
		releaseImage(selectbox2);
	}

	public static void loadMonster(int[] v) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] >= 1 && v[i] <= 13) {
				loadImage(powerbg1);
				loadImage(powerbg2);
				loadImage(powerbar);
				loadImage(powerbarbg);
				if (v[i] >= 1 && v[i] < 9) {
					loadImage(bullet1);
					if (v[i] == 1) {
						loadImage(monster1);
					} else if (v[i] == 2) {
						loadImage(monster2);
					} else if (v[i] == 3) {
						loadImage(monster3);
					} else if (v[i] == 4) {
						loadImage(monster4);
					} else if (v[i] == 5) {
						loadImage(monster5);
					} else if (v[i] == 6) {
						loadImage(monster6);
					} else if (v[i] == 7) {
						loadImage(monster7);
					} else if (v[i] == 8) {
						loadImage(monster8);
					}
				} else if (v[i] == 9) {
					loadImage(monster9);
				} else if (v[i] == 10) {
					loadImage(monster10);
					loadImage(bullet2);
				} else if (v[i] == 11) {
					loadImage(monster11);
					loadImage(soldier121);
					loadImage(soldier122);
				} else if (v[i] == 12) {
					loadImage(monster12);
				} else if (v[i] == 13) {
					loadImage(monster13);
				}
			} else if (v[i] >= 14 && v[i] <= 21) {
				loadImage(bossblood1);
				loadImage(bossblood2);
				loadImage(bosspower1);
				loadImage(bosspower2);
				loadImage(powerbg3);
				if (v[i] == 14) {
					loadImage(boss1);
					loadImage(boss1attack);
				} else if (v[i] == 15) {
					loadImage(boss2);
					loadImage(boss2attack);
				} else if (v[i] == 16) {
					loadImage(boss3);
					loadImage(boss3attack1);
					loadImage(boss3attack2);
				} else if (v[i] == 17) {
					loadImage(boss4);
					loadImage(boss4attack);
				} else if (v[i] == 18) {
					loadImage(boss5);
					loadImage(boss5attack);
					loadImage(soldier121);
					loadImage(soldier122);
				} else if (v[i] == 19) {
					loadImage(boss6);
					loadImage(boss6m);
					loadImage(boss6mattack);
				} else if (v[i] == 20) {
					loadImage(boss7);
					loadImage(boss78attack);
				} else if (v[i] == 21) {
					loadImage(boss8);
					loadImage(boss87attack);
				}
			}
		}
		loadImage(monsterdie);
	}

	public static void UnloadMonster(int[] v) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] >= 1 && v[i] <= 13) {
				releaseImage(powerbg1);
				releaseImage(powerbg2);
				releaseImage(powerbar);
				releaseImage(powerbarbg);
				if (v[i] >= 1 && v[i] < 9) {
					releaseImage(bullet1);
					if (v[i] == 1) {
						releaseImage(monster1);
					} else if (v[i] == 2) {
						releaseImage(monster2);
					} else if (v[i] == 3) {
						releaseImage(monster3);
					} else if (v[i] == 4) {
						releaseImage(monster4);
					} else if (v[i] == 5) {
						releaseImage(monster5);
					} else if (v[i] == 6) {
						releaseImage(monster6);
					} else if (v[i] == 7) {
						releaseImage(monster7);
					} else if (v[i] == 8) {
						releaseImage(monster8);
					}
				} else if (v[i] == 9) {
					releaseImage(monster9);
				} else if (v[i] == 10) {
					releaseImage(monster10);
					releaseImage(bullet2);
				} else if (v[i] == 11) {
					releaseImage(monster11);
					releaseImage(soldier121);
					releaseImage(soldier122);
				} else if (v[i] == 12) {
					releaseImage(monster12);
				} else if (v[i] == 13) {
					releaseImage(monster13);
				}
			} else if (v[i] >= 14 && v[i] <= 21) {
				releaseImage(bossblood1);
				releaseImage(bossblood2);
				releaseImage(bosspower1);
				releaseImage(bosspower2);
				releaseImage(powerbg3);
				if (v[i] == 14) {
					releaseImage(boss1);
					releaseImage(boss1attack);
				} else if (v[i] == 15) {
					releaseImage(boss2);
					releaseImage(boss2attack);
				} else if (v[i] == 16) {
					releaseImage(boss3);
					releaseImage(boss3attack1);
					releaseImage(boss3attack2);
				} else if (v[i] == 17) {
					releaseImage(boss4);
					releaseImage(boss4attack);
				} else if (v[i] == 18) {
					releaseImage(boss5);
					releaseImage(boss5attack);
					releaseImage(soldier121);
					releaseImage(soldier122);
				} else if (v[i] == 19) {
					releaseImage(boss6);
					releaseImage(boss6m);
					releaseImage(boss6mattack);
				} else if (v[i] == 20) {
					releaseImage(boss7);
					releaseImage(boss78attack);
				} else if (v[i] == 21) {
					releaseImage(boss8);
					releaseImage(boss87attack);
				}
			}
		}
		releaseImage(monsterdie);
	}

	public static void loadWinAndFail() {
		loadImage(panel1);
		loadImage(shenglishibai);
		loadImage(star1);
		loadImage(button1);
		loadImage(button2);
		loadImage(queding);
		loadImage(fight);
		loadImage(xingyunchoujiang);
	}

	public static void UnloadWinAndFail() {
		releaseImage(panel1);
		releaseImage(shenglishibai);
		releaseImage(star1);
		releaseImage(button1);
		releaseImage(button2);
		releaseImage(queding);
		releaseImage(fight);
		loadImage(xingyunchoujiang);
	}

	public static void loadStop() {
		loadImage(panel1);
		loadImage(shopx2);
		loadImage(stopimg);
		loadImage(button2);
	}

	public static void UnloadStop() {
		releaseImage(panel1);
		releaseImage(shopx2);
		releaseImage(stopimg);
		releaseImage(button2);
	}

	public static void loadTongtianta() {
		loadImage(somewherename);
		loadImage(guanqiatext);
		loadImage(panel1);
		loadImage(bg11);
		loadImage(button2);
		loadImage(fight);
		loadImage(back0);
	}

	public static void UnloadTongtianta() {
		releaseImage(somewherename);
		releaseImage(guanqiatext);
		releaseImage(panel1);
		releaseImage(bg11);
		releaseImage(button2);
		releaseImage(fight);
		releaseImage(back0);
	}

	public static void UnloadXiaofei() {
		releaseImage(shoprecord);
	}

	public static void loadXiaofei() {
		loadImage(shoprecord);
	}

}
