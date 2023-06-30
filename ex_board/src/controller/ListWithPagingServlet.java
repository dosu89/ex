package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BoardDao;
import vo.Board;

@WebServlet("/paging")
public class ListWithPagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param = request.getParameter("p");
		int p = Integer.parseInt(param);
		
		BoardDao dao = new BoardDao();
		
		ArrayList<Board> blist = dao.getListWithPaging(p);
		
		int cnt = dao.getListCnt();
		int lastPage = (int)Math.ceil(cnt/10.0);
		
		int startNum = p-((p-1)%5); 
				
		request.setAttribute("startNum", startNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", blist);
		request.getRequestDispatcher("listWithPaging.jsp").forward(request, response);
		
	}

}
