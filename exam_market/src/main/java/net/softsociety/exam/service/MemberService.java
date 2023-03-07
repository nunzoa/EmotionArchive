package net.softsociety.exam.service;

import net.softsociety.exam.domain.Member;

/** 
 * 회원정보 관련 서비스
 */
public interface MemberService {
	/**
	 * 회원가입
	 * @param member 저장할 회원 정보
	 * @return 저장된 행 개수
	 */
	public int insertMember(Member member);
	
}
