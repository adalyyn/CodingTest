package member.run;

import member.view.MemberMenu;

public class MemberRun {

	public static void main(String[] args) {
		//memberMenu객체를 만들고 메인메뉴를 호출해준다.
		new MemberMenu().mainMenu();
		
		System.out.println("====이용해주셔서 감사합니다.====");

	}

}
