package net.softsociety.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.softsociety.exam.dao.MemberDAO;
import net.softsociety.exam.domain.Member;

@Service
public class MemberSeviceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public int insertMember(Member member) {
		System.out.println(member);
		String encodedPassword = passwordEncoder.encode(member.getMemberpw());
		member.setMemberpw(encodedPassword);
		
		int result = memberDAO.insertMember(member);
		return result;
	}

}
