package com.mySpringWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mySpringWeb.domain.board.PostVO;
import com.mySpringWeb.service.PostService;

@Controller
public class BoardController {
	@Autowired
	private PostService postService;
	
	/*-----------------[이동매핑]-------------------*/
	@RequestMapping(value="listFreeBoard.do")
	public String freeBoardList(Model model) {
		System.out.println("글 목록 검색 처리");
    	return "boards/listFreeBoard";
	}
	@RequestMapping(value="listBookBoard.do")
	public String bookBoardList(Model model) {
		System.out.println("글 목록 검색 처리");
		String board_code = "BA2";
		List<PostVO> boardList = postService.getBoardList(board_code);
    	model.addAttribute("boardList", boardList);
    	return "boards/listBookBoard";
	}
	@RequestMapping(value="listHobbyBoard.do")
	public String hobbyBoardList() {    
    	return "boards/listHobbyBoard";
	}
	@RequestMapping(value="insertBookBoard.do")
	public String getBoardtest() {
		return "boards/insertBookBoard";
	}
	/*--------------------------------------------*/
	
	@RequestMapping(value="insertBookBoard.do", method=RequestMethod.POST)
	public String insertBookBoard(PostVO vo) {
		System.out.println("글 작성 처리");
		System.out.println("controller"+vo);
		postService.insertBoard(vo);
    	return "redirect:listBookBoard.do";
	}
	/*
	@RequestMapping(value="getBoardList.do")
	public String getBoardList(Model model) {     
		System.out.println("글 목록 검색 처리");
    	List<BoardVO> boardList = boardService.getBoardList();              
    	model.addAttribute("boardList", boardList);
  
    	return "getBoardList";
	}
	
	@RequestMapping(value="getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("글 상세조회 처리");
                                
        vo = boardService.getBoard(vo);                               
    	model.addAttribute("board", vo);

    	return "getBoard";
	}
	
	
	@RequestMapping(value="getBoardList.do")
	public String getBoardList(Model model) {     
		System.out.println("글 목록 검색 처리");
    	List<BoardVO> boardList = boardService.getBoardList();              
    	model.addAttribute("boardList", boardList);
  
    	return "getBoardList";
	}
	*/
	
}

