package net.softsociety.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Member;
import net.softsociety.exam.service.MemberService;

/**
 * 회원 정보 관련 콘트롤러
 */

@Slf4j
@RequestMapping("member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	/**
	 * 가입 폼으로 이동
	 */
	@GetMapping("join")
	public String join() {
		return "joinForm";
	}
	
	/**
	 * 가입 처리
	 * @param member 사용자가 폼에 입력한 정보
	 */
	@PostMapping("join")
	public String join(Member member) {
		log.debug("가입정보 : {}", member);
		int result = service.insertMember(member);
		
		return "redirect:/";
	}
	
    /**
     * 로그인 화면으로 이동
     */
    @GetMapping("/login")
    public String login() {

        return "loginForm";
    }
	
}
