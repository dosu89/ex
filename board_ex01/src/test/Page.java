package test;

import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardVO;

public class Page {

	public String showPage(int p) {
		int num2 = 3*p -1;
		int num1 = num2 - 2;
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> blist = dao.selectAll();
		
		String str = "";
		
		for (int i = num1; i <= num2; i++) {
			str += "<tr>"
					+"<td>" + blist.get(i).getB_no() + "</td>"
					+"<td>" + blist.get(i).getB_title() + "</td>"
					+"<td>" + blist.get(i).getB_writer() + "</td>"
					+"</tr>";
		}
		return str;
	}
}
