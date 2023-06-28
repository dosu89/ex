package dto;

public class FixedCostDTO {
	private String f_name;
	private int f_cost;
	private int f_date;
	
	public FixedCostDTO() {};
	
	public FixedCostDTO(String f_name, int f_cost, int f_date) {
		super();
		this.f_name = f_name;
		this.f_cost = f_cost;
		this.f_date = f_date;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public int getF_cost() {
		return f_cost;
	}

	public void setF_cost(int f_cost) {
		this.f_cost = f_cost;
	}

	public int getF_date() {
		return f_date;
	}

	public void setF_date(int f_date) {
		this.f_date = f_date;
	}
}
