package com.application.JPApractice2Ver.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.JPApractice2Ver.config.BoardMapper;
import com.application.JPApractice2Ver.data.Board;
import com.application.JPApractice2Ver.data.BoardDTO;
import com.application.JPApractice2Ver.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createBoard(BoardDTO boardDTO) {
		//패스워드 암호화
		String encodedPasswd = passwordEncoder.encode(boardDTO.getPasswd());
		boardDTO.setPasswd(encodedPasswd);
		boardDTO.setEnrollAt(new Date());
		
		boardRepository.save(BoardMapper.toEntity(boardDTO));
	}

	@Override
	public List<BoardDTO> getBoardLIst() {
		
		return BoardMapper.ToDTOList(boardRepository.findAll());
	}

	@Override
	public BoardDTO getBoardDetail(Long boardId) {
		//update board set readCnt=readCnt+1 where boardId=?
		Board board = boardRepository.findById(boardId).orElse(null);
		board.setReadCnt(board.getReadCnt()+1);
		boardRepository.save(board);
		
		
		return BoardMapper.toDTO(board);
	}

	@Override
	public boolean checkAuthorizedUser(BoardDTO boardDTO) {
		boolean isCheckMember = false;
		Board board = boardRepository.findById(boardDTO.getBoardId()).orElse(null);
		
		if(passwordEncoder.matches(boardDTO.getPasswd(),board.getPasswd())) {
			isCheckMember = true;
		}
		return isCheckMember;
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		
		 Board board= boardRepository.findById(boardDTO.getBoardId()).orElse(null);//id를 가지고온다
		 
		 //Subject, Content 수정해주기
		 board.setSubject(boardDTO.getSubject());
		 board.setContent(boardDTO.getContent());
		 
		 //업데이트하기
		 boardRepository.save(board);
	}

	@Override
	public void deleteBoard(Long boardId) {
		 boardRepository.deleteById(boardId);
	}
	
	

}
