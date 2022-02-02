 package com.sharing.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import com.sharing.main.vo.MPost;
import com.sharing.main.vo.Profile;
import com.sharing.main.vo.UserInfo;

public class WriteDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, '또 가고 싶다', '2021/08/08','여행병이 또 도지고 있는 중~~','test8.jpg', 603, 101, 2003 ,10306, 1112);
	/*
	 CREATE TABLE M_POST(
    P_NO NUMBER PRIMARY KEY,
    P_TITLE VARCHAR2(100) NOT NULL,
    P_DATE DATE NOT NULL,
    P_TEXT VARCHAR2(200) NOT NULL,
    P_PIC VARCHAR2(200) NOT NULL,
    CT_CODE CONSTRAINT POST_CT_CODE_FK REFERENCES CITY(CT_CODE),
    C_CODE CONSTRAINT POST_C_CODE_FK REFERENCES COUNTRY(C_CODE),
    L_CODE CONSTRAINT POST_L_CODE_FK REFERENCES LANDMARK(L_CODE),
    U_NUM CONSTRAINT POST_U_NUM_FK REFERENCES USER_INFO(U_NUM) NOT NULL,
    M_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL
	); 
	 */
	
	public void addPost(MPost mPost) {
		String joinSql = "INSERT INTO M_POST VALUES(MPO_SQ.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, ? ,?, 0)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(joinSql);
			
			pstmt.setString(1, mPost.getpTitle());
			pstmt.setString(2, mPost.getpText());
			pstmt.setString(3, mPost.getpPic());
			pstmt.setInt(4, mPost.getCtCode());
			pstmt.setInt(5, mPost.getcCode());
			pstmt.setInt(6, mPost.getlCode());
			pstmt.setInt(7, mPost.getuNum());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	

}
