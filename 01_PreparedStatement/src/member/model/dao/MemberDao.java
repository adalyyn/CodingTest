package member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import member.model.vo.Member;

public class MemberDao {

	final String driverClass = "oracle.jdbc.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속하려고 하는 디비주소
	final String user = "student1";
	final String password = "student1";
	
	public int insertMember(Member member) {	//DML
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into member values (?, ?, ?, ?, ?, default, default)";
						
		try {
			//1. jdbc driver class 등록
			Class.forName(driverClass);
		//2. Connection 객체 생성 & autoCommit 설정
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		//3. PreparedStatement 객체 생성 & 미완성쿼리 값 대입
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getName());
		pstmt.setString(3, member.getGender());
		pstmt.setDate(4, member.getBirthday());
		pstmt.setString(5, member.getEmail());
		
		//4. 쿼리실행 & 정수형리턴값
		result = pstmt.executeUpdate();
		//5. 트랜잭션		
		conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//6. 자원반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member set name = ?, gender = ?, birthday = ?, email = ? where id = ?";
		int result = 0;
		
		try {
			//1. jdbc driver class연결
			Class.forName(driverClass);
		//2. connection 객체 생성 & autoCommit 설정
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		//3. PreparedStatement 객체 생성 & 미완성쿼리 값 대입 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getGender());
			pstmt.setDate(3, member.getBirthday());
			pstmt.setString(4, member.getEmail());		
			pstmt.setString(5, member.getId());		
		//4. 쿼리실행
			result = pstmt.executeUpdate();
		//5. 트랜젝션
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//6. 자원반납
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from member where id = ?";
		
		try {
			//1. jdbc driver class 등록
			Class.forName(driverClass);
		//2. connection 객체 생성 & autoCommit 설정
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		//3. PreparedStatement 객체 생성 & 미완성 쿼리 값 대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
		//4. 쿼리실행 & 결과값 처리
			result = pstmt.executeUpdate();
		//5. 트랜잭션
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//6. 자원반납
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
