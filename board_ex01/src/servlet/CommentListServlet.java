package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.CommentDAO;
import vo.CommentVO;

@WebServlet("/clist")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String b_no_ = request.getParameter("b_no");
		int b_no = Integer.parseInt(b_no_);
		
		CommentDAO cdao = new CommentDAO();
		ArrayList<CommentVO> clist = cdao.selectAll(b_no);
		JSONArray jarray = new JSONArray(clist);
		
		String sendj= jarray.toString();
		out.print(sendj);
	}
}
