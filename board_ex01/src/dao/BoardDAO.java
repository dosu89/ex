package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BoardVO;

public class BoardDAO {
	
	public boolean insert(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("INSERT INTO tbl_board (b_title, b_writer, b_content) "
					+	"VALUE (?, ?, ?)");
			pstmt.setString(1, vo.getB_title());
			pstmt.setString(2, vo.getB_writer());
			pstmt.setString(3, vo.getB_content());
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
	
	public BoardVO selectOne(int b_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("SELECT * FROM tbl_board WHERE b_no = ?");
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String b_title = rs.getString("b_title");
				String b_writer = rs.getString("b_writer");
				String b_content = rs.getString("b_content");
				
				board = new BoardVO(b_no, b_title, b_writer, b_content);
			}
		} catch (Exception e) {
			System.out.println("개별 조회 실패 : " + e.getMessage());
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
		return board;
	}
	
	public ArrayList<BoardVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> bList = new ArrayList<>();
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM tbl_board");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int b_no = rs.getInt("b_no");
				String b_title = rs.getString("b_title");
				String b_writer = rs.getString("b_writer");
				String b_content = rs.getString("b_content");
				
				BoardVO board = new BoardVO(b_no, b_title, b_writer, b_content);
				bList.add(board);
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
		return bList;
	}
	
	public boolean update(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("UPDATE tbl_board SET b_title=?, b_writer=?, b_content=? WHERE b_no = ?");
			pstmt.setString(1, vo.getB_title());
			pstmt.setString(2, vo.getB_writer());
			pstmt.setString(3, vo.getB_content());
			pstmt.setInt(4, vo.getB_no());
			
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
	
	public boolean delete(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = DButil.DBcon.getConnection();
			pstmt = con.prepareStatement
					("DELETE FROM tbl_board WHERE b_title =?");
			pstmt.setString(1, name);
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

}
