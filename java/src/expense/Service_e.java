package expense;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.ExpenseDAO;
import dao.FixedCostDAO;
import dao.MaterialDAO;
import dto.ExpenseDTO;
import dto.FixedCostDTO;
import dto.MaterialDTO;
import dto.StockDTO;
import stock.Service_st;



public class Service_e {

	public void insertExpense(String m_code, int e_cost, int e_amount) {
		ExpenseDTO e = new ExpenseDTO(m_code, e_cost, e_amount);
		e.setE_date(LocalDateTime.now());
		Service_st s_serv = new Service_st();
		Service_e e_serv = new Service_e();
		ExpenseDAO dao = new ExpenseDAO();
		StockDTO s = null;
		
		if (dao.insert(e))
			System.out.println("지출 입력 완료");
		
			//지출 입력시 재고 데이터 확인후 재고 입력
			if(e_serv.auto_io(e) != null)
				s= e_serv.auto_io(e);
				s_serv.insertStock(s.getM_code(), s.getS_amount());
				System.out.println("재고 자동 입력 완료");
	}
	
	public void updateExpense(ExpenseDTO e) {
		ExpenseDAO dao = new ExpenseDAO();
		Object dto = e;
		if (dao.update(dto))
			System.out.println("수정 완료");
	}
	
	// 모든 지출 리스트 받아와 출력
	public void selectAllExpense() {
		ExpenseDAO dao = new ExpenseDAO();
		List<Object> list = dao.getData();
		@SuppressWarnings("unchecked")
		List<ExpenseDTO> elist = (List<ExpenseDTO>)(Object)list;
		for (ExpenseDTO e : elist) {
			System.out.println(e);
		}
	}
	
	public void deleteExpense(ExpenseDTO e) {
		ExpenseDAO dao = new ExpenseDAO();
		if (dao.dalete(e))
			System.out.println("삭제 완료");
	}
	
	
	// 오늘 날짜와 고정비 지출 날짜 동일 시 자동 입력
	public boolean fixedCostInsert() {
		FixedCostDAO dao = new FixedCostDAO();
		boolean flag = false;
		LocalDateTime today = LocalDateTime.now();
		int day = today.getDayOfMonth();
		@SuppressWarnings("unchecked")
		List<FixedCostDTO> fList = (List<FixedCostDTO>)(Object)dao.getData();
		for (FixedCostDTO f : fList) {
			if(f.getF_date() == day) {
				ExpenseDTO dto = new ExpenseDTO(f.getF_name(), f.getF_cost(), 1);
				dto.setE_date(LocalDateTime.now());
				if (dao.insert(dto))
					System.out.println("고정비 등록 완료");
					flag = true;
			}
		}
		return flag;
	}
	
	// 오늘 날짜와 고정비 입력 날짜 체크 후 당일 고정비 목록 반환
	public ArrayList<String> fixedCostCheck() {
		FixedCostDAO dao = new FixedCostDAO();
		LocalDateTime today = LocalDateTime.now();
		int day = today.getDayOfMonth();
		@SuppressWarnings("unchecked")
		List<FixedCostDTO> fList = (List<FixedCostDTO>)(Object)dao.getData();
		ArrayList<String> f_names = new ArrayList<>();
		for (FixedCostDTO f : fList) {
			if(f.getF_date() == day) {
				f_names.add(f.getF_name());
			}
		}
		return f_names;
	}
	
	// 지출시 재고 영역 자동 입력을 위한 재고 객체 생성
	public StockDTO auto_io(ExpenseDTO e) {
		StockDTO s = null;
		MaterialDAO mdao = new MaterialDAO();
		@SuppressWarnings("unchecked")
		List<MaterialDTO> mlist = (List<MaterialDTO>)(Object)mdao.getData();
		for (MaterialDTO m : mlist) {
			if(m.getM_code().equals(e.getM_code())) {
				String m_code = e.getM_code();
				int s_amount = e.getE_amount();
				s = new StockDTO(m_code, s_amount);
			}
		}
		return s;
	}
	
	// 특정 날짜 사이의 지출 리스트 보여주기
	public void betweenDateList(String date1, String date2) {
		ExpenseDAO edao = new ExpenseDAO();
		List<ExpenseDTO> eList = edao.getDateData(date1, date2);
		for (ExpenseDTO e : eList) {
			System.out.println(e);
		}
	}
}
