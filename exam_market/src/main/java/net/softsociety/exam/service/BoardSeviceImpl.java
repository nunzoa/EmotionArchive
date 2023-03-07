package net.softsociety.exam.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;


@Transactional
@Service
public class BoardSeviceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

	@Override
	public int write(Board board) {
		int result = boardDAO.insertBoard(board);
		return result;
	}

	@Override
	public Board read(int boardnum) {
		Board board = boardDAO.selectBoard(boardnum);
		return board;
	}

	@Override
	public ArrayList<Board> list() {
		ArrayList<Board> boardlist = boardDAO.selectBoardList(); 
		return boardlist;
	}
	
	@Override
	public ArrayList<Board> search(String category, String searchWord) {
		
		HashMap<String, String> map =new HashMap<>();
		map.put("category", category);
		map.put("searchWord", searchWord);
		
		ArrayList<Board> boardlist = boardDAO.search(map); 
		
		return boardlist;
	}

	@Override
	public int delete(Board board) {
		int result = boardDAO.deleteBoard(board);
		return result;
	}
	
	@Override
	public int buy(Board board) {
		int result = boardDAO.buy(board);
		return 0;
	}

	@Override
	public int writeReply(Reply reply) {
		int result = boardDAO.insertReply(reply);
		return result;
	}

	@Override
	public ArrayList<Reply> listReply(int boardnum) {
		ArrayList<Reply> replylist = boardDAO.selectReplyList(boardnum);
		return replylist;
	}

}
