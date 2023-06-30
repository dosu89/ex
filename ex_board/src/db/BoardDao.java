package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Board;

public class BoardDao {

	
	public ArrayList<Board> getListWithPaging(int p) {
		DBcon2 conn = new DBcon2();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> bList = new ArrayList<>();
		
		int pagePerBoardCnt = 10;
		int startIdx = (p-1)*10;
		
		String sql = "SELECT * FROM tbl_board3 LIMIT "+startIdx +", "+pagePerBoardCnt;
		
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setRegdate(rs.getTimestamp("regdate"));
				bList.add(b);
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
	
	public int getListCnt() {
		DBcon2 conn = new DBcon2();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "SELECT count(bno) FROM tbl_board3";
		
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next())
				result = rs.getInt(1);
			
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
		return result;
	}
}
