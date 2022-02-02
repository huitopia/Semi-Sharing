package com.sharing.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.sharing.main.vo.Profile;

public class ProfileDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	


	public Profile getProfile(String uId) {
		
		String profilesql = "SELECT * FROM USER_INFO WHERE U_ID = ?";
		Profile profile = null;
		System.out.println("getProfile() - uId : " + uId);
	
		try {
			conn= DBManager.getConnection();			
			pstmt = conn.prepareStatement(profilesql);
			pstmt.setString(1, uId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				profile = new Profile();
				profile.setuNum(rs.getInt("U_NUM"));
				profile.setuId(rs.getString("U_ID"));
				profile.setuPropic(rs.getString("U_PROPIC"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return profile; 
	}
	
	public void setProfile(String uId, String uPropic) {
		
		String profileSql = "UPDATE USER_INFO SET U_PROPIC=? WHERE U_ID=?";
		System.out.println("setProfile() - uId : " + uId);
		
		try {
			conn= DBManager.getConnection();			
			pstmt = conn.prepareStatement(profileSql);
			pstmt.setString(1, uPropic);
			pstmt.setString(2, uId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
		}finally {
			DBManager.close(conn, pstmt,rs);
		}	
		
	}
	

}
