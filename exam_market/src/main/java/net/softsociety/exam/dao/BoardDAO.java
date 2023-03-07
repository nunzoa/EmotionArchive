package net.softsociety.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardDAO {
    //판매글 저장
	public int insertBoard(Board board);
	//판매글 읽기
	public Board selectBoard(int boardnum);
	//판매글 목록
	public ArrayList<Board> selectBoardList();
	//상품 검색
	public ArrayList<Board> search(HashMap<String, String> map);
	//판매글 삭제
	public int deleteBoard(Board board);
	//구매 처리
	public int buy(Board board);
	//리플 저장
	public int insertReply(Reply reply);
	//리플 목록
	public ArrayList<Reply> selectReplyList(int boardnum);
}
