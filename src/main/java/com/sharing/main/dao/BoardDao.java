package com.sharing.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sharing.main.vo.QNAPost;

public class BoardDao {
	

	private Connection conn;
	
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	public int getBoardCount(String type, String keyword) {
	System.out.println(type + " - " + keyword);
	
	String sqlCount = "SELECT COUNT(*) FROM QNA_POST WHERE "
	+ type + " LIKE '%' || ? || '%'";
	
	int count = 0;
	try{
	
	conn = DBManager.getConnection();
	
	pstmt = conn.prepareStatement(sqlCount);
	
	pstmt.setString(1, keyword);
	
	rs = pstmt.executeQuery();
	
	if(rs.next()) {
	count = rs.getInt(1);
	}
	} catch(Exception e) {
	e.printStackTrace();
	} finally {
	
	DBManager.close(conn, pstmt, rs);
	}

	return count;
	}
	
	public ArrayList<QNAPost> searchList(
	String type, String keyword, int startRow, int endRow) {
	/* 검색어가 포함된 게시 글 리스트를 추출하기 위한 쿼리
	* 테이블에서 현재 페이지에 해당하는 게시 글을 검색할 때 ROWNUM을 사용했다.
	* ROWNUM은 쿼리의 결과로 검색되는 행들의 순서 값을 가진 의사컬럼으로
	* 1부터 시작한다. 최신 게시 글을 먼저 보여주기 위해 ORDER BY DESC를
	* 지정하고 요청된 페이지에 보여줄 게시 글의 시작 행과 마지막 행을 지정한다.
	*
	* 아래의 쿼리로 질의하게 되면 게시 글 전체를 글 번호에 해당하는 no를 기준으로
	* 내림차순 정렬하여 검색하고 WHERE 절에 지정한 첫 번째 Placeholder(?)에
	* 해당하는 시작 행부터 두 번째 Placeholder(?)에 해당하는 마지막 행까지의
	* 게시 글을 추출할 수 있다.
	**/
	String sqlSearchList = "SELECT * FROM (SELECT ROWNUM num, Q_NO, Q_TITLE,"
	+ " Q_TEXT, Q_DATE, Q_PIC , U_NUM FROM"
	+ " (SELECT * FROM QNA_POST WHERE " + type + " LIKE ?"
	+ " ORDER BY no DESC)) WHERE num >= ? AND num <= ?";
	
	ArrayList<QNAPost> boardList = null;
	try{
	conn = DBManager.getConnection();
	pstmt = conn.prepareStatement(sqlSearchList);
	pstmt.setString(1, "%" + keyword + "%");
	pstmt.setInt(2, startRow);
	pstmt.setInt(3, endRow);
	rs = pstmt.executeQuery();
	
	if(rs.next()) {
		
	boardList = new ArrayList<QNAPost>();
	
	do {
	QNAPost board = new QNAPost();
	board.setqNo(rs.getInt("no"));
	board.setqTitle(rs.getString("title"));
	board.setqText(rs.getString("text"));
	board.setqDate(rs.getTimestamp("date"));
	board.setqPic(rs.getString("pic"));
	board.setuNum(rs.getInt("unum"));
	
	boardList.add(board);
	} while(rs.next());
	}
	} catch(Exception e) {
	e.printStackTrace();
	} finally {
	DBManager.close(conn, pstmt, rs);
	}
	return boardList;
	}
	/* 전체 게시 글 수를 계산하기 위해 호출되는 메서드 - paging 처리에 사용
	* DB 테이블에 등록된 모든 게시 글의 수를 반환하는 메서드
	**/
	public int getBoardCount() {
	String sqlCount = "SELECT COUNT(*) FROM QNA_POST";
	int count = 0;
	try{
	conn = DBManager.getConnection();
	pstmt = conn.prepareStatement(sqlCount);
	rs = pstmt.executeQuery();
	while(rs.next()) {
	count = rs.getInt(1);
	}
	} catch(Exception e) {
	e.printStackTrace();
	} finally {
	DBManager.close(conn, pstmt, rs);
	}
	return count;
	}
	
	
	public ArrayList<QNAPost> boardList(int startRow, int endRow) {
	
	String sqlBoardList = "SELECT * FROM (SELECT ROWNUM num,  Q_NO, Q_TITLE,"
			+ " Q_TEXT, Q_DATE, Q_PIC , U_NUM FROM"
			+ " (SELECT * FROM QNA_POST ORDER BY Q_NO DESC))"
			+ "  WHERE num >= ? AND num <= ?";
	ArrayList<QNAPost> boardList = null;
	try{
	conn = DBManager.getConnection();
	pstmt = conn.prepareStatement(sqlBoardList);
	pstmt.setInt(1, startRow);
	pstmt.setInt(2, endRow);
	rs = pstmt.executeQuery();
	if(rs.next()) {
	boardList = new ArrayList<QNAPost>();
	do {
	QNAPost board = new QNAPost();
	board.setqNo(rs.getInt("Q_NO"));
	board.setqTitle(rs.getString("Q_TITLE"));
	board.setqText(rs.getString("Q_TEXT"));
	board.setqDate(rs.getTimestamp("Q_DATE"));
	board.setqPic(rs.getString("Q_PIC"));
	board.setuNum(rs.getInt("U_NUM"));
	boardList.add(board);
	} while(rs.next());
	}
	} catch(Exception e) {
	e.printStackTrace();
	} finally {
	DBManager.close(conn, pstmt, rs);
	}
	return boardList;
	}
	
	
	public QNAPost getBoard(int no, boolean state) {
		String boardSql = "SELECT * FROM QNA_POST WHERE Q_NO=?";
	
		QNAPost board = null;
		try{
		
		conn = DBManager.getConnection();
		
		DBManager.setAutoCommit(conn, false);
		
		pstmt = conn.prepareStatement(boardSql);
	
		pstmt.setInt(1, no);
	
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
		    board = new QNAPost();
			board.setqNo(rs.getInt("Q_NO"));
			board.setqTitle(rs.getString("Q_TITLE"));
			board.setqText(rs.getString("Q_TEXT"));
			board.setqDate(rs.getTimestamp("Q_DATE"));
			board.setqPic(rs.getString("Q_PIC"));
			board.setuNum(rs.getInt("U_NUM"));}
		
		// 모든 작업이 완료되면 커밋하여 트랜잭션을 종료한다.
		DBManager.commit(conn);
		
		} catch(Exception e) {
		// DB 작업이 하나라도 에러가 발생하면 롤백하고 트랜잭션을 종료한다.
		DBManager.rollback(conn);
		System.out.println("BoardDao - getBoard(no, state)");
		e.printStackTrace();
		} finally {
		// 6. DBManager를 이용해 Connection을 DBCP에 반납한다.
		DBManager.close(conn, pstmt, rs);
		}
		// 요청한 하나의 게시 글을 반환 한다.
		return board;
		}
		/* 게시 글쓰기 요청시 호출되는 메서드
		* 게시 글을 작성하고 등록하기 버튼을 클릭하면 게시 글을 DB에 추가하는 메서드
		**/
		public void insertBoard(QNAPost board) {
		String sqlInsert = "INSERT INTO QNA_POST(Q_NO, Q_TITLE, Q_TEXT, Q_DATE,"
		+ " Q_PIC, U_NUM) "
		+ " VALUES(QNA_POST.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sqlInsert);
		pstmt.setInt(1, board.getqNo());
		pstmt.setString(2, board.getqTitle());
		pstmt.setString(3, board.getqText());
		pstmt.setTimestamp(4, board.getqDate());
		pstmt.setString(5, board.getqPic());
		pstmt.executeUpdate();
		} catch(Exception e) {
		e.printStackTrace();
		} finally {
		DBManager.close(conn, pstmt, rs);
		}
		}
		/* 게시 글 수정, 게시 글 삭제 시 비밀번호 입력을 체크하는 메서드
		**/
		public boolean isPassCheck(int no, String pass) {
		boolean isPass = false;
		String sqlPass = "SELECT pass FROM QNA_POST WHERE no=?";
		try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sqlPass);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
		isPass = rs.getString(1).equals(pass);
		}
		} catch(SQLException e) {
		e.printStackTrace();
		} finally {
		DBManager.close(conn, pstmt, rs);
		}
		return isPass;
		}
		/* 게시 글 수정 요청시 호출되는 메서드
		* 게시 글을 수정하고 수정하기 버튼을 클릭하면 게시 글을 DB에 수정하는 메서드
		**/
		public void updateBoard(QNAPost board) {
		String sqlUpdate = "UPDATE QNA_POST set Q_NO=?, Q_TITLE=?, Q_TEXT=?,"
		+ " Q_DATE=SYSDATE, Q_PIC=? WHERE no=?";
		try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sqlUpdate);
		pstmt.setInt(1, board.getqNo());
		pstmt.setString(2, board.getqTitle());
		pstmt.setString(3, board.getqText());
		pstmt.setTimestamp(4, board.getqDate());
		pstmt.setString(5, board.getqPic());
		pstmt.executeUpdate();
		} catch(Exception e) {
		e.printStackTrace();
		} finally {
		DBManager.close(conn, pstmt);
		}
		} // end updateBoard(Board board)
		/* 게시 글 삭제 요청 시 호출되는 메서드
		* no에 해당 하는 게시 글을 DB에서 삭제하는 메서드
		**/
		public void deleteBoard(int no) {
		String sqlDelete = "DELETE FROM QNA_POST WHERE no=?";
		try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sqlDelete);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		} catch(Exception e) {
		e.printStackTrace();
		} finally {
		DBManager.close(conn, pstmt);
		}
		} // end deleteBoard(int no);
		}


