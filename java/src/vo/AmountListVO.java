package vo;

public class AmountListVO {
	private String m_name;
	private int totalAmount;
	
	public AmountListVO() {
		super();
	}

	public AmountListVO(String m_name, int totalAmount) {
		super();
		this.m_name = m_name;
		this.totalAmount = totalAmount;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return m_name + " : " + totalAmount;
	}
}
