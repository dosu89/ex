package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import vo.CommentVO;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String c_content = request.getParameter("content");
		String c_writer = request.getParameter("writer");
		String b_no_ = request.getParameter("b_no");
		int b_no = Integer.parseInt(b_no_);
		
		CommentVO c = new CommentVO(b_no, c_writer,  c_content);
		CommentDAO dao = new CommentDAO();
		
		if(dao.insert(c))
			System.out.println("댓글 등록 완료");
	}
}
