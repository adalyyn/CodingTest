package member.view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

public class MemberMenu {

	Scanner sc = new Scanner(System.in);
	
	//컨트롤러에게 요청하기위해 그 객체를 이 클래스에서 가지고 있어야 한다.
	MemberController memberController = new MemberController();
	
	public void mainMenu() {
		String menu = "\n"
						+ "+++++++++++회원 정보 관리+++++++++++\n"
						+ "1. 전체조회\n"
						+ "2. 아이디 조회\n"
						+ "3. 이름검색\n"
						+ "4. 회원가입\n"
						+ "5. 회원정보변경\n"
						+ "6. 회원탈퇴\n"
						+ "0. 프로그램 종료\n"
						+ "++++++++++++++++++++++++++++++\n"
						+ "선택 : ";
		while(true) {
			System.out.print(menu);	//메뉴 보여주기
			String choice = sc.next();
			
			//다른case에서도 쓸 수 있게 변수는 여기에 선언.
			Member member = null;
			int result = 0;
			String id = " ";
			
			
			switch(choice) {
			case "1" : break;
			case "2" : break;
			case "3" : break;
			case "4" : 
				//사용자가 4번누르면 member객체(vo)하나 만든 후 controller에게 요청 -> dao요청(인서트해줘) -> dml은 1을 리턴
				member = inputMember();	//메소드 통해서 객체 하나 만들기.
				result = memberController.insertMember(member);	//만든 객체를 컨드롤러에 보내기 -> DML이기 때문에 정수형을 리턴할 것임.
				System.out.println(result > 0 ? "회원가입 성공!" : "회원가입 실패!");
				break;
			case "5" : 
				//이름, 성별, 생일, 이메일을 한번에 변경할 것. id는 변경못함.
				//update member set name = ?, gender = ?, birthday = ?, email = ? where id = ?
				member = updateMember();
				result = memberController.updateMember(member);
				System.out.println(result > 0 ? "회원정보수정 성공!" : "회원정보 수정 실패!");
				break;
			case "6" : 
				//delete from member where = ?
				id = deleteMember();
				result = memberController.deleteMember(id);
				System.out.println(result > 0 ? "회원 탈퇴 성공!" : "회원 탈퇴 실패!");
				break;
			case "0" : return;
			default : System.out.println("잘못입력하셨습니다.");
			}
			
		}
		
	}
	
	
	private String deleteMember() {
		System.out.print("삭제할 아이디를 입력하세요 : ");
		return sc.next();
	}


	/**
	 * id를 입력받아 member 객체를 수정
	 */
	private Member updateMember() {
		System.out.println("=====회원정보수정=======");
		System.out.print("수정할 아이디를 입력하세요 : ");
		String id = sc.next();		
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("성별(M/F) : ");
		String gender = String.valueOf(sc.next().toUpperCase().charAt(0));
		System.out.print("생일(19921104) : ");
		String temp = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date birthday = null;
		try {
			birthday = new Date(sdf.parse(temp).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print("이메일 : ");
		String email = sc.next();
		return new Member(id, name, gender, birthday, email, 0, null);
	}



	/**
	 * 사용자 입력값을 받아 member객체를 반환
	 * @return
	 */
	private Member inputMember() {		
		
		System.out.println("> 회원정보를 입력하세요");
		System.out.print("아이디 : ");
		String id = sc.next();
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("성별(M,F) : ");
		String gender = String.valueOf(sc.next().toUpperCase().charAt(0));	//소문자로 써도 대문자로 바꿔줌. String으로 써도 한글자만 가져옴 "mm" -> "MM" -> "M"
																			//마지막으로 char타입을 String으로 형변환 (String.valueof)
		System.out.print("생일(19921104) : ");
		String temp = sc.next();
		Date birthday = null;
		//문자열 -> java.util.Date -> java.sql.date (SimpleDateFormat parse 이용해서 날짜타입으로 바꾸기)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			//temp를 원하는 형식으로 쪼갬 -> 리턴값은 java.util.date, 우리가 필요한건 sql.date임
			//sdf.parse(temp);
			//sdf.parse(temp).getTime();	//-> long타입
			//new Date(sdf.parse(temp).getTime()); //Date의 인자로 전달. sql.date를 import하기
			birthday = new Date(sdf.parse(temp).getTime()); //birthday 변수에 담기
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		System.out.print("이메일 : ");
		String email = sc.next();

		return new Member(id, name, gender, birthday, email, 0, null);
	}

}
