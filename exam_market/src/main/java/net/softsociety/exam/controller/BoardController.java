package net.softsociety.exam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;
import net.softsociety.exam.service.BoardService;

/**
 * 거래게시판 관련 콘트롤러
 */
@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	/**
	 * 게시판 글목록으로 이동
	 */
	@GetMapping("list")
	public String list(Model model) {
		
		ArrayList<Board> boardlist = service.list();
		model.addAttribute("boardlist", boardlist);
		
		return "list";
	}

	/**
	 * 상품 검색 페이지로 이동
	 */
	@GetMapping("search")
	public String search() {
		return "search";
	}

	/**
	 * 카테고리와 검색어를 전달받아 검색결과를 리턴
	 * @param category 상품 카테고리
	 * @param searchWord 검색어
	 */
	@ResponseBody
	@PostMapping("search")
	public ArrayList<Board> search(String category, String searchWord) {
		log.debug(category + searchWord);
		ArrayList<Board> boardlist = service.search(category, searchWord);
		return boardlist;
	}

	/**
	 * 글쓰기 폼으로 이동
	 */
	@GetMapping("write")
	public String write() {
		return "writeForm";
	}
	
	/**
	 * 글 저장 
	 * @param board 사용자가 폼에 입력한 게시글 정보
	 * @param user 인증정보
	 */
	@PostMapping("write")
	public String write(
			Board board
			, @AuthenticationPrincipal UserDetails user) {
		
		board.setMemberid(user.getUsername());
		
		int result = service.write(board);
		return "redirect:/board/list";
	}
	
	/**
	 * 글 읽기 
	 * @param boardnum 읽을 글번호
	 */
	@GetMapping("read")
	public String read(
			Model model
			, @RequestParam(name="boardnum", defaultValue = "0") int boardnum) { 

		Board board = service.read(boardnum);
		if (board == null) {
			return "redirect:/board/list";
		}
		
		ArrayList<Reply> replylist = service.listReply(boardnum);
		
		model.addAttribute("board", board);
		model.addAttribute("replylist", replylist);
		return "read";
	}	

	/**
	 * 글 삭제
	 * @param pnum 삭제할 글 번호
	 * @param user 인증정보
	 */
	@GetMapping ("delete")
	public String delete(int boardnum, @AuthenticationPrincipal UserDetails user) {
		Board board = new Board();
		board.setBoardnum(boardnum);
		board.setMemberid(user.getUsername());
		int result = service.delete(board);
		
		return "redirect:list";
	}

	/**
	 * 구매 처리
	 * @param pnum 판매글 번호
	 * @param user 인증정보
	 */
	@GetMapping ("buy")
	public String buy(int boardnum, @AuthenticationPrincipal UserDetails user) {
		Board board = new Board();
		board.setBoardnum(boardnum);
		board.setBuyerid(user.getUsername());
		int result = service.buy(board);
		
		return "redirect:list";
	}
	

	/**
	 * 리플 저장
	 * @param reply 저장할 리플 정보
	 */
	@PostMapping("writeReply")
	public String writeReply(
		Reply reply
		, @AuthenticationPrincipal UserDetails user) {
		
		reply.setMemberid(user.getUsername());
		int result = service.writeReply(reply);
		
		return "redirect:/board/read?boardnum=" + reply.getBoardnum();
	}
	
}
