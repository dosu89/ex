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
import org.json.JSONObject;

import dao.BoardDAO;
import dao.CommentDAO;
import vo.BoardVO;
import vo.CommentVO;

@WebServlet("/")
public class MainServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		PrintWriter out = response.getWriter();
		
		if(com.equals("/main") || com.equals("/")) {
			response.sendRedirect("main.jsp");

		} else if (com.equals("/blist")) {
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> blist = dao.selectAll();
			
			request.setAttribute("blist", blist);
			request.getRequestDispatcher("blist.jsp").forward(request, response);

		} else if (com.startsWith("/select")) {
			String b_noStr = request.getParameter("b_no");
			int b_no = Integer.parseInt(b_noStr);
			BoardDAO dao = new BoardDAO();
			BoardVO b = dao.selectOne(b_no);
			request.setAttribute("board", b);
			request.getRequestDispatcher("select.jsp").forward(request, response);

		} else if (com.startsWith("/regi")) {
			String b_title = request.getParameter("b_title");
			String b_writer = request.getParameter("b_writer");
			String b_content = request.getParameter("b_content");
			
			BoardVO vo = new BoardVO(b_title, b_writer, b_content);
			BoardDAO dao = new BoardDAO();
			if (dao.insert(vo)) 
				System.out.println("등록 완료");
			
		} else if (com.startsWith("/comreg")) {
			String c_content = request.getParameter("content");
			String c_writer = request.getParameter("writer");
			String b_no_ = request.getParameter("b_no");
			int b_no = Integer.parseInt(b_no_);
			
			CommentVO c = new CommentVO(b_no, c_writer,  c_content);
			CommentDAO dao = new CommentDAO();
			
			if(dao.insert(c)) 
				out.print("댓글 등록 완료");
			
		} else if (com.startsWith("/clist2")) {
			String b_no_ = request.getParameter("b_no");
			int b_no = Integer.parseInt(b_no_);
			
			CommentDAO cdao = new CommentDAO();
			ArrayList<CommentVO> clist = cdao.selectAll(b_no);
			JSONArray jarray = new JSONArray(clist);
			
			String sendj= jarray.toString();
			out.print(sendj);
			
		} else if (com.startsWith("/upCheck")) {
			String c_no_ = request.getParameter("c_no");
			int c_no = Integer.parseInt(c_no_);
			CommentDAO dao = new CommentDAO();
			
			System.out.println(c_no);
			CommentVO c = dao.selectOne(c_no);
			JSONObject j = new JSONObject(c);
			out.print(j);
		} else if (com.startsWith("update1")) {
			
		}
	}
}
