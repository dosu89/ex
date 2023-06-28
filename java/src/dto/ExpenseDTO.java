package dto;

import java.time.LocalDateTime;

public class ExpenseDTO {
	private int e_no;
	private String m_code;
	private int e_cost;
	private int e_amount;
	private LocalDateTime e_date;
	
	public ExpenseDTO() {}

	public ExpenseDTO(String m_code, int e_cost, int e_amount) {
		super();
		this.m_code = m_code;
		this.e_cost = e_cost;
		this.e_amount = e_amount;
	}

	public ExpenseDTO(int e_no, String m_code, int e_cost, int e_amount, LocalDateTime e_date) {
		super();
		this.e_no = e_no;
		this.m_code = m_code;
		this.e_cost = e_cost;
		this.e_amount = e_amount;
		this.e_date = e_date;
	}

	public int getE_no() {
		return e_no;
	}

	public void setE_no(int e_no) {
		this.e_no = e_no;
	}

	public String getM_code() {
		return m_code;
	}

	public void setM_code(String m_code) {
		this.m_code = m_code;
	}

	public int getE_cost() {
		return e_cost;
	}

	public void setE_cost(int e_cost) {
		this.e_cost = e_cost;
	}

	public int getE_amount() {
		return e_amount;
	}

	public void setE_amount(int e_amount) {
		this.e_amount = e_amount;
	}

	public LocalDateTime getE_date() {
		return e_date;
	}

	public void setE_date(LocalDateTime e_date) {
		this.e_date = e_date;
	}

	@Override
	public String toString() {
		return "ExpenseDTO [e_no=" + e_no + ", m_code=" + m_code + ", e_cost=" + e_cost + ", e_amount=" + e_amount
				+ ", e_date=" + e_date + "]";
	}
}
