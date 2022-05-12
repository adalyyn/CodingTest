package jdbc.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcTest {

	final String driverClass = "oracle.jdbc.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속하려고 하는 디비주소
	final String user = "student1";
	final String password = "student1";
	
	public static void main(String[] args) {
		JdbcTest instance = new JdbcTest();
//		instance.test1();
		instance.test2("M", "leess");
	
	}

	/**
	 * DML
	 */
	private void test2(String gender, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;	//처리행의 수 
		
		//이순신의 gender를 M으로 세팅하기
		String sql = "update member set gender =? where id = ?";
		
		try {
			//1. jdbc driver class 등록
			Class.forName(driverClass);
			
			//2. Connection 객체 생성 & autoCommit을 false로 설정
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			//3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gender);	//''따옴표 씌워줌
			pstmt.setString(2, id);			
			//4. 쿼리 실행 & 정수형 리턴값 받기
			result = pstmt.executeUpdate();
			
			//5. 트랜젝션 처리
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
				System.out.println(result + "행이 수정되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}


	/**
	 * DQL
	 */
	private void test1() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member";
		
		try {
			//1. jdbc driver 객체 등록
			//jdbc에 있는 드라이버 클래스를 사용하려고 등록하는 것.
			//ojdbc가 프로젝트와 연결되어있지 않다면 ClassNotFoundException 뜰 것임.
			Class.forName(driverClass);
			System.out.println("> 드라이버 클래스 등록 완료!");
			
			//2. Conncetion 객체 생성
			conn = DriverManager.getConnection(url, user, password);	
			System.out.println("> DB연결 성공!");
			
			//3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);	//실행할 쿼리를 pstmt에 전달
			System.out.println("> PreparedStatement 객체 생성 성공!");
			
			//4. 쿼리 실행 & ResultSet 리턴받기
			rset = pstmt.executeQuery();
			System.out.println("> 실행 및 결과집합 수신 성공!");
			
			//5. ResultSet 처리
			while(rset.next()) {
				String id = rset.getString("id");
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				int point = rset.getInt("point");
				Timestamp regDate = rset.getTimestamp("reg_date");
				
				System.out.printf("%s\t %s\t %s\t %s\t %s\t %s\t %s %n", id, name, gender, birthday, email, point, regDate);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
				
		//6. 자원반납하기
		try {
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		System.out.println("> 자원반납 완료!");
		}
	}

}
