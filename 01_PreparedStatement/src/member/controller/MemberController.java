package member.controller;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberController {

	//dao 호출위해 객체 생성
	private MemberDao memberDao = new MemberDao();
	
	//dao에게 일 시키기 : 토스하기, 동일한 이름의 메소드 만들기. 그래야 흐름파악이 쉬움.
	public int insertMember(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	}

	public int updateMember(Member member) {
		int result = memberDao.updateMember(member);
		return result;
	}

	public int deleteMember(String id) {		
		return memberDao.deleteMember(id);
	}

}
