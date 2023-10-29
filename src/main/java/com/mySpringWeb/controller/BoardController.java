package com.mySpringWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mySpringWeb.domain.BoardVO;
import com.mySpringWeb.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
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
	
	/*---------[임시이동매핑 추가]----------*/
	@RequestMapping(value="freeBoardList.do")
	public String freeBoardList() {    
    	return "freeBoardList";
	}
	@RequestMapping(value="bookBoardList.do")
	public String bookBoardList() {    
    	return "bookBoardList";
	}
	@RequestMapping(value="hobbyBoardList.do")
	public String hobbyBoardList() {    
    	return "hobbyBoardList";
	}
	@RequestMapping(value="getBoardtest.do")
	public String getBoardtest() {
		return "getBoard";
	}
	/*------------------------------------*/
	
	@RequestMapping(value="insertBoard.do", method=RequestMethod.GET)
	public String insertBoard() {
		return "insertBoard";
	}

	@RequestMapping(value="insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo) {
		System.out.println("글 작성 처리");
		boardService.insertBoard(vo);                                      //boardDAO인스턴스에 insertBoard()메소드에 board를 매개변수로 사용
	
    	return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="updateBoard.do")
	public String updateBoard(BoardVO vo) {
		System.out.println("글 수정 처리");
		boardService.updateBoard(vo);                                      //boardDAO인스턴스의 updateBoard에 board를 넘김 
    
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("글 삭제 처리");
		boardService.deleteBoard(vo);
    	return "redirect:getBoardList.do";
	}
}

