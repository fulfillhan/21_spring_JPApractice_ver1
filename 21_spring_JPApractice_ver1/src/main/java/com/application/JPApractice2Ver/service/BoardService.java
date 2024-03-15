package com.application.JPApractice2Ver.service;

import java.util.List;

import com.application.JPApractice2Ver.data.BoardDTO;

public interface BoardService {

	public void createBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardLIst();

	public BoardDTO getBoardDetail(Long boardId);

	public boolean checkAuthorizedUser(BoardDTO boardDTO);

	public void updateBoard(BoardDTO boardDTO);

	public void deleteBoard(Long boardId);

	
}
