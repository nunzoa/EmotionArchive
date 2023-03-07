package net.softsociety.exam.service;

import java.util.ArrayList;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

public interface BoardService {
	//판매글 저장
	public int write(Board board);
	//판매글 읽기
	public Board read(int pnum);
	//판매글 목록
	public ArrayList<Board> list();
	//상품 검색
	public ArrayList<Board> search(String category, String searchWord);
	//판매글 삭제
	public int delete(Board board);
	//구매 처리
	public int buy(Board board);
	//리플 저장
	public int writeReply(Reply reply);
	//리플 목록
	public ArrayList<Reply> listReply(int pnum);

}
