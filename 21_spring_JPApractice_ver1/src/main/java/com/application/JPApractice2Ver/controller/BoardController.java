package com.application.JPApractice2Ver.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.JPApractice2Ver.data.BoardDTO;
import com.application.JPApractice2Ver.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/createBoard")
	public String createBoard() {
		return "board/createBoard";
	}
	
	@PostMapping("createBoard")
	@ResponseBody
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		boardService.createBoard(boardDTO);
		
		String jsScript = """
				<script>
			 	   alert('작성 되었습니다.');
			 	   location.href = '/board/boardList';
		   	    </script>""";
		
		return jsScript;
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardLIst());
		return "board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail (Model model, @RequestParam("boardId") Long boardId) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/boardDetail";
	}
	
	@GetMapping("/authentication")
	public String authentication(Model model, @RequestParam("boardId") Long boardId, @RequestParam("menu") String menu) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		model.addAttribute("menu", menu);
		return "board/authentication";
	}
	
	@PostMapping("/authentication")
	@ResponseBody
	public String authentication(@ModelAttribute BoardDTO boardDTO,@RequestParam("menu") String menu) {
		
		
		String jString = "";
		if(boardService.checkAuthorizedUser(boardDTO)) {
			if(menu.equals("update")) {
				jString = "<script>";
				jString += "alert('인증되었습니다!');";
				jString += "location.href='/board/updateBoard?boardId=" + boardDTO.getBoardId() + "';";
				jString += "</script>";
				
			}
			else if (menu.equals("delete")) {
				jString = "<script>";
				jString += "alert('인증되었습니다!');";
				jString += "location.href='/board/deleteBoard?boardId=" + boardDTO.getBoardId() + "';";
				jString += "</script>";
			}
		}
		else {
			jString = """
					<script>
				 	   alert('패스워드를 확인하세요.');
				 	   history.go(-1);
			   	    </script>
					""";
		}
		return jString;
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(Model model,@RequestParam("boardId")Long boardId) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/udpateBoard";
	}
	
	@PostMapping("/updateBoard")
	@ResponseBody
	public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
		boardService.updateBoard(boardDTO);
		
		String jsScript = """
				<script>
			 	   alert('수정 되었습니다.');
			 	   location.href = 'boardList';
		   	    </script>""";
		
		return jsScript;	   
	}
	
	@GetMapping("/deleteMember")
	public String deleteMember(Model model, @RequestParam("boardId")Long boardId) {
		model.addAttribute("boardId", boardId);
		return "board/deleteMember";
	}
	
	@PostMapping("/deleteMember")
	@ResponseBody
	public String deleteMember(@RequestParam("boardId")Long boardId) {
		boardService.deleteBoard(boardId);
		
		String jsScript = """
				<script>
			 	   alert('삭제 되었습니다.');
			 	   location.href = '/board/boardList';
		   	    </script>""";
	
		return jsScript;
		
	}
	

}
