package com.mySpringWeb.controller;

import com.mySpringWeb.domain.BasketVO;
import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.utils.BlogUtil;
import com.mySpringWeb.utils.BookUtil;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class BookController {
	@Autowired
	private BasketService basketService;
	
	@RequestMapping(value="getBoardBook.do")
	public String getBoardBook() {
		return "getBoardBook";
	}
	@RequestMapping(value="insertBook.do")
	public String insertBook() {
		return "insertBook";
	}
	
    @RequestMapping(value="bookRecommend.do", method = RequestMethod.GET)
    public String bookRecommend(
            @RequestParam(defaultValue="자기계발")
            String category,
            Model model
    ) throws UnsupportedEncodingException {
        BookUtil bookUtil = new BookUtil();
        bookUtil.getBookList(category, model);

        return "bookRecommend";
    }

    @RequestMapping(value="bookReview.do")
    public String bookReview(
            @RequestParam(defaultValue="자기계발")
            String category,
            @RequestParam(defaultValue="자기계발")
            String blog,
            @RequestParam(value="id", defaultValue="")
            String id,
            Model model
    ) throws UnsupportedEncodingException {
        BookUtil bookUtil = new BookUtil();
        bookUtil.getBookList(category, model, 1, 1);
        BlogUtil blogUtil = new BlogUtil();
        blogUtil.getBlogList(blog, model, 1, 10);
        
        /*아이디를 받아와서 아이디랑 isbn으로 같은내용이 있다면 1을 줘서 다음페이지에서 삭제구현*/
        if(!id.equals("")) {
        	BasketVO vo = new BasketVO();
        	vo.setUser_id(id);
        	vo.setBook_unique_id(category);
        	String data=basketService.checkBasket(vo);
        	System.out.println("book list->book review" + data);
        	if(data.equals("True")){
        		String bookcheck = "0";
        		model.addAttribute("bookcheck",bookcheck);
        	}else if(data.equals("False")) {
        		String bookcheck = "1";
        		model.addAttribute("bookcheck",bookcheck);
        	}
        }
        return "bookReview";
    }
    
    /*basket관련*/
    @RequestMapping(value="addBasket.do", method=RequestMethod.GET)
    @ResponseBody
    public String addBasket(BasketVO vo) throws UnsupportedEncodingException{
    	System.out.println("넘어온 아이디: "+vo.getUser_id()+",넘어온isbn :"+vo.getBook_unique_id());
    	String data=basketService.checkBasket(vo);
    	System.out.println("도서체크 결과 값"+data);
    	if(data.equals("True")){
    		basketService.addBasket(vo);
    	}
    	return data;
    }
    
    @RequestMapping(value="deleteBasket.do", method=RequestMethod.GET)
    @ResponseBody
    public String deleteBasket(BasketVO vo) throws UnsupportedEncodingException{
    	System.out.println("넘어온 아이디: "+vo.getUser_id()+",넘어온isbn :"+vo.getBook_unique_id());
    	String data=basketService.checkBasket(vo);
    	System.out.println("도서체크 결과 값"+data);
    	if(data.equals("False")){
    		basketService.deleteBasket(vo);
    	}
    	return data;
    }
    
    @RequestMapping(value="getBasketList.do", method=RequestMethod.GET)
    @ResponseBody
    public JSONArray getBasketList(BasketVO vo) throws UnsupportedEncodingException{
    	JSONArray basketList = basketService.getBasketList(vo);
    	return basketList;
    }
    
    
}
