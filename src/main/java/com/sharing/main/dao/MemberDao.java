package com.sharing.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sharing.main.vo.UserInfo;

public class MemberDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//로그인 처리하는 메소드
	public int checkMember(String uId, String uPw) {
		String loginSql = "SELECT U_PW FROM USER_INFO WHERE U_ID = ?";
		
		int result = -1;
		String password = "";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(loginSql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				password = rs.getString("U_PW");
			} else {
				return result;
			}
			
			if(password.equals(uPw)) {
				result = 1;
			} else {
				result = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	// 회원 가입 처리 메소드
	public void joinMember(UserInfo userinfo) {
		String joinSql = "INSERT INTO USER_INFO(U_NUM, U_ID, U_PW, U_NAME, U_BT, U_JDATE, U_MAIL, U_PHONE) VALUES(USER_SQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(joinSql);
			
			pstmt.setString(1, userinfo.getuId());
			pstmt.setString(2, userinfo.getuPw());
			pstmt.setString(3, userinfo.getuName());
			pstmt.setTimestamp(4, userinfo.getuBt());
			pstmt.setString(5, userinfo.getuMail());
			pstmt.setInt(6, userinfo.getuPhone());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 중복 회원 체크 메소드
	public boolean overlapIdCheck(String uId) {
		String overlapSql = "SELECT U_ID FROM USER_INFO Where U_ID = ?";
		boolean result = false;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(overlapSql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			System.out.println("overlapIdCheck(String uId)");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	// 회원정보 수정 폼에 출력할 회원의 정보를 반환하는 메서드
	public UserInfo getUserInfo(String uId) {
		String selectUserInfo = "SELECT * FROM USER_INFO WHERE U_ID = ?";
		UserInfo userinfo = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectUserInfo);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userinfo = new UserInfo();
				userinfo.setuNum(rs.getInt("U_Num"));
				userinfo.setuId(rs.getString("U_ID"));
				userinfo.setuPw(rs.getString("U_PW"));
				userinfo.setuName(rs.getString("U_NAME"));
				userinfo.setuBt(rs.getTimestamp("U_BT"));
				userinfo.setuPhone(rs.getInt("U_PHONE"));
				userinfo.setuMail(rs.getString("u_Mail"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt,rs);
		}
		return userinfo;
	}
	

	
	// 회원 정보 수정을 처리하는 메소드
	public void updateUserInfo(UserInfo userinfo) {
		String joinSql = "UPDATE USER_INFO SET U_ID=?, U_PW=?, U_NAME=?, U_BT=?, U_JDATE=SYSDATE, U_GENDER=?, U_PHONE=?, U_MAIL=?, U_M_CK=? WHERE U_NUM=?";
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(joinSql);
			
			pstmt.setString(1, userinfo.getuId());
			pstmt.setString(2, userinfo.getuPw());
			pstmt.setString(3, userinfo.getuName());
			pstmt.setTimestamp(4, userinfo.getuBt());
			pstmt.setTimestamp(5, userinfo.getuJDate());
			pstmt.setInt(7, userinfo.getuPhone());
			pstmt.setString(8, userinfo.getuMail());
			pstmt.setInt(10, userinfo.getuNum());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
