package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DButil.DBcon;
import DButil.DBcrud;
import dto.MaterialDTO;

public class MaterialDAO implements DBcrud{

	@Override
	public boolean insert(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		MaterialDTO m = (MaterialDTO)dto;
		
		String query = "INSERT INTO material VALUE (?, ?, ?)";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getM_code());
			pstmt.setString(2, m.getM_name());
			pstmt.setString(3, m.getM_group());
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				flag = true;
			}
			
		} catch (Exception e) {
			System.out.println("입력 실패 : " + e.getMessage());
			
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

	@Override
	public List<Object> getData() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<>();
		
		String query = "SELECT * FROM material";
		
		try {
			con = DButil.DBcon.getConn();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if( rs != null ) {
					String m_code = rs.getString("m_code");
					String m_name = rs.getString("m_name");
					String m_group = rs.getString("m_group");
					MaterialDTO dto = new MaterialDTO(m_code, m_name, m_group);
					list.add(dto);
				}
			}
		} catch (Exception e) {
			System.out.println("데이터 반환 실패 : " + e.getMessage());
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
		return list;
	}

	@Override
	public boolean update(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MaterialDTO m = (MaterialDTO)dto;
		boolean flag = false;
		
		String query = "UPDATE material SET m_name = ?, m_group = ? WHERE m_code = ?";
		
		try {
			con = DButil.DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getM_name());
			pstmt.setString(2, m.getM_group());
			pstmt.setString(3, m.getM_code());
			int result = pstmt.executeUpdate();
			
			if ( result == 1)
				flag = true;
			
		} catch (Exception e) {
			System.out.println("업데이트 실패 : " + e.getMessage());
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

	@Override
	public boolean dalete(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MaterialDTO m = (MaterialDTO)dto;
		boolean flag = false;
		
		String query = "DELETE FROM material WHERE m_code = ?";
		
		try {
			con = DButil.DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getM_code());
			int result = pstmt.executeUpdate();
			
			if (result == 1) 
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
