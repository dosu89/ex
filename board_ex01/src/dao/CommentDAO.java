package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CommentVO;

public class CommentDAO {
	
	public boolean insert(CommentVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("INSERT INTO tbl_comment (b_no, c_writer, c_content) "
					+	"VALUE (?, ?, ?)");
			pstmt.setInt(1, vo.getB_no());
			pstmt.setString(2, vo.getC_writer());
			pstmt.setString(3, vo.getC_content());
			int result = pstmt.executeUpdate();
			
			if (result ==1) 
				flag = true;
		} catch (Exception e) {
			System.out.println("입력 실패 : " + e.getMessage());
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return flag;
	}
	
	public CommentVO selectOne(int c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentVO comment = null;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM tbl_comment WHERE c_no = ?");
			pstmt.setInt(1, c_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int b_no = rs.getInt("b_no");
				String c_writer = rs.getString("c_writer");
				String c_content = rs.getString("c_content");
				
				comment = new CommentVO(c_no, b_no, c_writer, c_content);
			}
		} catch (Exception e) {
			System.out.println("전체 조회 실패 : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return comment;
	}
	
	public ArrayList<CommentVO> selectAll(int b_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommentVO> cList = new ArrayList<>();
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM tbl_comment WHERE b_no = ?");
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int c_no = rs.getInt("c_no");
				String c_writer = rs.getString("c_writer");
				String c_content = rs.getString("c_content");
				
				CommentVO comment = new CommentVO(c_no, b_no, c_writer, c_content);
				cList.add(comment);
			}
		} catch (Exception e) {
			System.out.println("전체 조회 실패 : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return cList;
	}
	
	public boolean update(CommentVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("UPDATE tbl_comment SET c_writer=?, c_content=? WHERE c_no = ?");
			pstmt.setString(1, vo.getC_writer());
			pstmt.setString(2, vo.getC_content());
			pstmt.setInt(3, vo.getC_no());
			
			int result = pstmt.executeUpdate();
			
			if (result ==1) 
				flag = true;
		} catch (Exception e) {
			System.out.println("수정 실패 : " + e.getMessage());
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return flag;
	}
	
	public boolean delete(int c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("DELETE FROM tbl_comment WHERE c_no =?");
			pstmt.setInt(1, c_no);
			int result = pstmt.executeUpdate();
			
			if(result ==1)
				flag = true;
		} catch (Exception e) {
			System.out.println("삭제 실패 : " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return flag;
	}
	
	public int cntComment(int b_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement("SELECT COUNT(c_no) FROM tbl_comment WHERE b_no = ?");
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				cnt = rs.getInt("COUNT(c_no)");
			}
		} catch (Exception e) {
			System.out.println("전체 조회 실패 : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return cnt;
	}
}
