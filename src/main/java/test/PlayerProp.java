package test;

/**
 * ����
 * 
 * @author Administrator
 * 
 */
public class PlayerProp {

	/* ID */
	private int id;

	/* �������� */
	private String name;

	/* ����ID */
	private int propId;

	/* �۷ѵ� */
	private int feeCode;

	/* �������� */
	private int nums;

	/* ���� */
	private String desc;

	/* ���߼۸� */
	private int price;

	public int getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(int feeCode) {
		this.feeCode = feeCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setNumsAdd() {
		nums = nums + 1;
	}
	public void setNumsReduce() {
		nums = nums - 1;
	}
}
