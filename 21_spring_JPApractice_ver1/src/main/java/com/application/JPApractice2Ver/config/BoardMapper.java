package com.application.JPApractice2Ver.config;

import java.util.List;
import java.util.stream.Collectors;

import com.application.JPApractice2Ver.data.Board;
import com.application.JPApractice2Ver.data.BoardDTO;

import jakarta.persistence.Entity;

public class BoardMapper {
	
	//dto->entity 인 객체로 변환시켜 db로 전달한다.
	public static Board toEntity(BoardDTO boardDTO) {
		
		Board entity = new Board();
		entity.setBoardId(boardDTO.getBoardId());
		entity.setPasswd(boardDTO.getPasswd());
		entity.setWriter(boardDTO.getWriter());
		entity.setSubject(boardDTO.getSubject());
		entity.setContent(boardDTO.getContent());
		entity.setReadCnt(boardDTO.getReadCnt());
		entity.setEnrollAt(boardDTO.getEnrollAt());
		return entity;
		
	}
	
	//entity ->dto로 변환시켜 controller로 전달
	public static BoardDTO toDTO(Board entity) {
		 BoardDTO dto = new BoardDTO();
		 dto.setBoardId(entity.getBoardId());
		 dto.setPasswd(entity.getPasswd());
		dto.setWriter(entity.getWriter());
		dto.setSubject(entity.getSubject());
		dto.setContent(entity.getContent());
		dto.setReadCnt(entity.getReadCnt());
		dto.setEnrollAt(entity.getEnrollAt());
		
		return dto;
	}
	
	//entity->dto의 배열 형태로 변환
	public static List<BoardDTO> ToDTOList(List<Board> boards){
		return boards.stream().map(BoardMapper::toDTO).collect(Collectors.toList());
	}
	
	

}
