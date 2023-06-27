package test;

import java.util.ArrayList;

import dao.CommentDAO;
import vo.CommentVO;

public class Test {

	public static void main(String[] args) {
		CommentDAO cdao = new CommentDAO();
		ArrayList<CommentVO> clist = cdao.selectAll(5);
		System.out.println(clist);
	}

}
