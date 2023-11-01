package com.mySpringWeb.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mySpringWeb.domain.bookrecommend.BookVO;
import com.mySpringWeb.domain.board.CommentVO;
import com.mySpringWeb.domain.board.Pagination;
import com.mySpringWeb.domain.board.PostVO;
import com.mySpringWeb.service.CommentService;
import com.mySpringWeb.service.PostService;
import com.mySpringWeb.utils.BookUtil;

@Controller
public class BoardController {
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	
	/*-----------------[게시판 이동]-------------------*/
	@RequestMapping(value="listFreeBoard.do")
	public String freeBoardList(Model model) {
		System.out.println("글 목록 검색 처리");
    	return "boards/listFreeBoard";
	}
	@RequestMapping(value="listBookBoard.do")
	public String bookBoardList(@ModelAttribute("searchVO") PostVO searchVO, Model model) throws UnsupportedEncodingException {
		String board_code = "BA2";
		Pagination pagination = new Pagination();
		pagination.setCurrentPageNo(searchVO.getPageIndex());
		pagination.setRecordCountPerPage(searchVO.getPageUnit());
		pagination.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(pagination.getFirstRecordIndex());
	    searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
	    System.out.println("1번 라인"+ searchVO);
	    List<PostVO> boardList = postService.getList(searchVO, board_code);
	    System.out.println("2번 라인"+boardList);
	    int totalCount = postService.getPageCount(board_code);
	    System.out.println("3번 라인"+totalCount);
		
	    pagination.setTotalRecordCount(totalCount);
	    
	    searchVO.setEndDate(pagination.getLastPageNoOnPageList());
	    searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
	    searchVO.setPrev(pagination.getXprev());
	    searchVO.setNext(pagination.getXnext());
	    System.out.println("boardList"+boardList);
	    System.out.println("totalCount"+totalCount);
	    System.out.println("totalPageCnt"+(int)Math.ceil(totalCount / (double)searchVO.getPageUnit()));
	    System.out.println("pagination" + pagination);
	    
	    model.addAttribute("boardList",boardList);
	    model.addAttribute("totalCount",totalCount);
	    model.addAttribute("totalPageCnt",(int)Math.ceil(totalCount / (double)searchVO.getPageUnit()));
	    model.addAttribute("pagination",pagination);
	    searchVO.setQustr();
	    
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
	/*--------------[게시판 글쓰기]-------------------------*/
	
	@RequestMapping(value="insertBookBoard.do", method=RequestMethod.POST)
	public String insertBookBoard(PostVO vo) {
		System.out.println("글 작성 처리");
		System.out.println("controller"+vo);
		postService.insertBoard(vo);
    	return "redirect:listBookBoard.do";
	}
	/*-------------------[게시판 업데이트]----------------------------*/
	@RequestMapping(value="updateBookBoard.do")
	public String updateBookBoard(String id, Model model) {   
		PostVO vo = postService.getBoard(id);
		BookUtil bookUtil = new BookUtil();
		List<BookVO> bookList = bookUtil.getBookInfo(vo.getBook_id()); //isbn으로 책정보 조회
        BookVO book = bookList.get(0);//vo 단일형태로 변경(의도)
        String image = book.getImage(); 
        String title = book.getTitle().split("\\(")[0];
        String author = book.getAuthor().replaceAll("\\^",", ");
    	model.addAttribute("post", vo);
    	model.addAttribute("image",image);
    	model.addAttribute("title",title);
    	model.addAttribute("author",author);
    	return "boards/updateBookBoard";
	}
	
	@RequestMapping(value="updateBookBoard.do", method=RequestMethod.POST)
	public String updateBookBoard(PostVO vo) {
		System.out.println("BookBoard update처리");
		postService.updateBoard(vo);
		return "redirect:listBookBoard.do";
	}
	
	/*-------------------[게시판 삭제]-----------------------*/
	@RequestMapping(value="deleteBoard.do")
	public String deleteBoard(@RequestParam String id,@RequestParam String board_code) {
		System.out.println("글 삭제 처리");
		postService.deleteBoard(id);
		if(board_code.equals("BA0")) {
			return "redirect:listFreeBoard.do";
		}else if(board_code.equals("BA1")) {
			return "redirect:listHobbyBoard.do";
		}else return "redirect:listBookBoard.do"; 
	}
	
	/*----------------[게시판 글 보기]---------------------------*/
	@RequestMapping(value="getBookBoard.do")
	public String getBoard(@RequestParam String id, Model model) {
		System.out.println("Book Board 상세조회 처리" + id);
        PostVO vo = postService.getBoard(id);//게시글 정보 조회   
        postService.addCount(id);//조회수 증가
        BookUtil bookUtil = new BookUtil();
        List<BookVO> bookList = bookUtil.getBookInfo(vo.getBook_id()); //isbn으로 책정보 조회
        BookVO book = bookList.get(0);//vo 단일형태로 변경(의도)
        String image = book.getImage(); 
        String title = book.getTitle().split("\\(")[0];
        String author = book.getAuthor().replaceAll("\\^",", ");
        
        List<CommentVO> comment = commentService.getCommentList(id);
        System.out.println(comment);
    	model.addAttribute("post", vo);
    	model.addAttribute("image",image);
    	model.addAttribute("title",title);
    	model.addAttribute("author",author);
    	model.addAttribute("comment",comment);
    	
    	return "boards/getBookBoard";
	}
	/*----------------------[댓글기능]----------------------------------*/
	@RequestMapping(value="insertComment.do", method=RequestMethod.POST)
	public String insertComment(CommentVO vo) {
		System.out.println("댓글 작성 처리");
		System.out.println(vo);
		commentService.insertComment(vo);
		
    	return "redirect:getBookBoard.do?id="+vo.getPost_id();
	}
	
	
	
	
	/*-----------------------------[추가]--------------------------------------*/
	
}

