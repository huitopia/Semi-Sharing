package com.sharing.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;



import com.sharing.main.vo.Contag;
import com.sharing.main.vo.Mainpage;
import com.sharing.main.vo.TagList;

public class MainDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  
  
  public int serchCount(String keyword) {
	
		
		String sqlCount = "SELECT COUNT(*) FROM M_POST WHERE " 	
					+  "P_TITLE LIKE '%' || ? || '%'";

		int count = 0;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1,  keyword);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println("count serch : " + count);
			}			
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
  
  public int tagCount(String tagName) {
		
		
		String sqlCount = "SELECT COUNT(*) "
				+ "FROM P_TAG "
				+ "WHERE tb_code = (SELECT TB_CODE "
				+ "FROM TAG_BD "
				+ "WHERE TB_NAME = ? ) ";

		int count = 0;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1,  tagName);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println("count serch : " + count);
			}			
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
  
 
	

	public ArrayList<Mainpage> searchList(String keyword, int startRow, int endRow) {
		
	
		String sqlSearchList = "SELECT * "
		  		+ "FROM (SELECT ROW_NUMBER () OVER (ORDER BY NVL(B.blcount,0) DESC) LROW, A.*, NVL(B.blcount,0) as lCount "
		  		+ "        FROM (SELECT P.P_NO as pno, P.P_TITLE as ptitle, P.P_DATE as pdate, P.P_TEXT as pcontent, P.P_PIC as ppic, U.U_PROPIC as upropic, U.U_NAME as uname, L.L_NAME as lname, CT.CT_NAME as ctname, C.C_NAME as cname  "
		  		+ "              FROM M_POST P, USER_INFO U, LANDMARK L, COUNTRY C, CITY CT "
		  		+ "              WHERE U.U_NUM = P.U_NUM AND P.C_CODE = C.C_CODE AND P.CT_CODE = CT.CT_CODE AND P.L_CODE = L.L_CODE AND P_TITLE LIKE ?) A "
		  		+ "              FULL OUTER JOIN  "
		  		+ "             (SELECT P_NO as bpno, NVL(COUNT(*), 0) as blcount "
		  		+ "              FROM LIKE_LIST L "
		  		+ "              GROUP BY P_NO)B "
		  		+ "        ON  A.pno = B.bpno) m "
		  		+ "WHERE m.lrow >= ? AND m.lrow <= ?";
		
		ArrayList<Mainpage> mainList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mainList = new ArrayList<Mainpage>();
				
				do {					
					Mainpage mp = new Mainpage();
					mp.setNo(rs.getInt("pno"));
					mp.setTitle(rs.getString("ptitle"));
					mp.setRdate(rs.getTimestamp("pdate"));
					mp.setContent(rs.getString("pcontent"));
					mp.setPicLoc(rs.getString("ppic"));
					mp.setuPropic(rs.getString("upropic"));
					mp.setUname(rs.getString("uname"));
					mp.setLm(rs.getString("lname"));
					mp.setCt(rs.getString("ctname"));
					mp.setCn(rs.getString("cname"));
					mp.setLike(rs.getInt("lcount"));
					
					if(mp.getUname() != null) mainList.add(mp);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mainList;
	}
	
	
	public ArrayList<Mainpage> tagList(String tagName, int startRow, int endRow) {
		
		
		String sqltSearchList = "SELECT * "
				+ "FROM (SELECT ROW_NUMBER () OVER (ORDER BY NVL(B.blcount,0) DESC) LROW, A.*, NVL(B.blcount,0) as lCount "
				+ "        FROM (SELECT P.P_NO as pno, P.P_TITLE as ptitle, P.P_DATE as pdate, P.P_TEXT as pcontent, "
				+ "                     P.P_PIC as ppic, U.U_PROPIC as upropic, U.U_NAME as uname, L.L_NAME as lname, CT.CT_NAME as ctname, C.C_NAME as cname, T.tname  "
				+ "              FROM M_POST P, USER_INFO U, LANDMARK L, COUNTRY C, CITY CT, (SELECT P.P_NO as tpno, B.TB_NAME as tname, b.tb_code as tcode"
				+ "                                                                            FROM P_TAG P, TAG_BD B "
				+ "                                                                            WHERE P.TB_CODE = B.TB_CODE) T "
				+ "              WHERE U.U_NUM = P.U_NUM AND P.C_CODE = C.C_CODE AND P.CT_CODE = CT.CT_CODE AND P.L_CODE = L.L_CODE AND p.p_no = T.tpno AND T.tname=?)  A "
				+ "              "
				+ "              FULL OUTER JOIN  "
				+ "              "
				+ "             (SELECT P_NO as bpno, NVL(COUNT(*), 0) as blcount "
				+ "              FROM LIKE_LIST L "
				+ "              GROUP BY P_NO)B "
				+ "              "
				+ ""
				+ "        ON  A.pno = B.bpno "
				+ "        WHERE NOT A.ptitle IS NULL "
				+ "        ) m "
				+ "WHERE m.lrow >= ? AND m.lrow <= ?";
		
		ArrayList<Mainpage> mainList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqltSearchList);			
			pstmt.setString(1, tagName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mainList = new ArrayList<Mainpage>();
				
				do {					
					Mainpage mp = new Mainpage();
					mp.setNo(rs.getInt("pno"));
					mp.setTitle(rs.getString("ptitle"));
					mp.setRdate(rs.getTimestamp("pdate"));
					mp.setContent(rs.getString("pcontent"));
					mp.setPicLoc(rs.getString("ppic"));
					mp.setuPropic(rs.getString("upropic"));
					mp.setUname(rs.getString("uname"));
					mp.setLm(rs.getString("lname"));
					mp.setCt(rs.getString("ctname"));
					mp.setCn(rs.getString("cname"));
					mp.setTname(rs.getString("tname"));
					mp.setLike(rs.getInt("lcount"));
					
					if(mp.getUname() != null) mainList.add(mp);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mainList;
	}
  
  
  
  public ArrayList<Mainpage> mainList(int startRow, int endRow){
	  
	  String sqlMainList = "SELECT * "
	  		+ "FROM (SELECT ROW_NUMBER () OVER (ORDER BY NVL(B.blcount,0) DESC) LROW, A.*, NVL(B.blcount,0) as lCount "
	  		+ "        FROM (SELECT P.P_NO as pno, P.P_TITLE as ptitle, P.P_DATE as pdate, P.P_TEXT as pcontent, P.P_PIC as ppic, U.U_PROPIC as upropic, U.U_NAME as uname, L.L_NAME as lname, CT.CT_NAME as ctname, C.C_NAME as cname  "
	  		+ "              FROM M_POST P, USER_INFO U, LANDMARK L, COUNTRY C, CITY CT "
	  		+ "              WHERE U.U_NUM = P.U_NUM AND P.C_CODE = C.C_CODE AND P.CT_CODE = CT.CT_CODE AND P.L_CODE = L.L_CODE) A "
	  		+ "              FULL OUTER JOIN  "
	  		+ "             (SELECT P_NO as bpno, NVL(COUNT(*), 0) as blcount "
	  		+ "              FROM LIKE_LIST L "
	  		+ "              GROUP BY P_NO)B "
	  		+ "        ON  A.pno = B.bpno) m "
	  		+ "WHERE m.lrow >= ? AND m.lrow <= ?";
	  
	  ArrayList<Mainpage> mainList = null;
	  
	  
	  try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sqlMainList);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			mainList = new ArrayList<Mainpage>();
			
			do {
				Mainpage mp = new Mainpage();
				
				// LROW PNO PTITLE PDATE 
				// PCONTENT PPIC UPROPIC UNAME LNAME CTNAME 
				// CNAME LCOUNT
				mp.setNo(rs.getInt("pno"));
				mp.setTitle(rs.getString("ptitle"));
				mp.setRdate(rs.getTimestamp("pdate"));
				mp.setContent(rs.getString("pcontent"));
				mp.setPicLoc(rs.getString("ppic"));
				mp.setuPropic(rs.getString("upropic"));
				mp.setUname(rs.getString("uname"));
				mp.setLm(rs.getString("lname"));
				mp.setCt(rs.getString("ctname"));
				mp.setCn(rs.getString("cname"));
				mp.setLike(rs.getInt("lcount"));
				
				mainList.add(mp);
				
				System.out.println(mp.getTitle());

			} while(rs.next());
			
		}
	} catch (SQLException e) {
		System.out.println("리스트 불러오기 문제");
		e.printStackTrace();
	}finally {			
		DBManager.close(conn, pstmt, rs);
	}
	  
	 
	  
	  return mainList;
  }
  
  
  public int getMainListCount() {
	  String sqlCount = "SELECT COUNT(*) FROM M_POST";
	  int count = 0;
	 
	  conn = DBManager.getConnection();
	  try {
		pstmt = conn.prepareStatement(sqlCount);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {			
		DBManager.close(conn, pstmt, rs);
	}
	  
	  System.out.println("count : " + count);
	return count;
	  
	  
  }
  
  
  public ArrayList<Contag> contagList(int pno){
	 String sqlContag ="SELECT P.P_NO as pno, TH.TH_NAME as hname, TB.TB_NAME as bname "
				 		+ "FROM P_TAG P, TAG_HD TH, TAG_BD TB "
				 		+ "WHERE p.p_no = ? AND P.TB_CODE = TB.TB_CODE AND TB.TH_CODE = TH.TH_CODE";
	 
	 ArrayList<Contag> contagList = null;

	 int t = 0;
	 
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlContag);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				contagList = new ArrayList<Contag>();
				
				do {
					Contag ct = new Contag();
					
					ct.setPno(rs.getInt("pno"));
					ct.setHname(rs.getString("hname"));
					ct.setBname(rs.getString("bname"));
					
					contagList.add(ct);
					
					
					t ++;
				}while(rs.next());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			DBManager.close(conn, pstmt, rs);
		}
	 
	  
	  
		  return contagList;
	  }
  
  
  	public int likeCount(int pno){
  		String sqlLike = "SELECT COUNT(*) FROM LIKE_LIST WHERE P_NO = ?";
  		int likeCount = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlLike);
			
			pstmt.setInt(1, pno);
			
			rs = pstmt.executeQuery();
			
			if(rs != null) likeCount = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("likecount : " + likeCount);
  		
  		
  		return likeCount;
  	}
  	
  	
  	
  	public ArrayList<TagList> allTagList(){
  		 String sqltag ="SELECT B.TB_CODE as tno, B.TB_NAME as bname, H.TH_NAME as hname "
  		 		+ "FROM TAG_BD B, TAG_HD H "
  		 		+ "WHERE B.TH_CODE = H.TH_CODE";
  		 
  		 ArrayList<TagList> allTagListS = null;
  		ArrayList<TagList> allTagList = null;
  		 TagList tl = new TagList();

  
  			try {
  				conn = DBManager.getConnection();
  				pstmt = conn.prepareStatement(sqltag);
  				rs = pstmt.executeQuery();
  				
  				if(rs.next()) {
  					
  					allTagListS = new ArrayList<TagList>();
  					allTagList = new ArrayList<TagList>();
  					
  					do {
  						
  						tl = new TagList();
  						
  						tl.setTno(rs.getInt("tno"));
  						tl.setBname(rs.getString("bname"));
  						tl.setHname(rs.getString("hname"));
  						
  						allTagList.add(tl);
  	  					
  						
  					
  					}while(rs.next());
  					

  					
  					

  					
  					
  				}
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}finally {			
  				DBManager.close(conn, pstmt, rs);
  			}
  			
  			
  			
  			return allTagList;
    }
  	
  	
}

	
