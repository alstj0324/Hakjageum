package com.mySpringWeb.controller;

import com.mySpringWeb.domain.BasketVO;
import com.mySpringWeb.domain.UserVO;
import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.utils.BlogUtil;
import com.mySpringWeb.utils.BookUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class BookController {
	@Autowired
	private BasketService basketService;
	
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
            Model model
    ) throws UnsupportedEncodingException {
        BookUtil bookUtil = new BookUtil();
        bookUtil.getBookList(category, model, 1, 1);

        BlogUtil blogUtil = new BlogUtil();
        blogUtil.getBlogList(blog, model, 1, 10);

        return "bookReview";
    }
    
//    도서목록 불러오기
    @RequestMapping(value="bookBasket.do")
    public String bookBasket(
    		@RequestParam(defaultValue="")
    		String user_id, 
    		Model model
    )throws UnsupportedEncodingException{
    	System.out.println(user_id);
    	BasketVO vo = new BasketVO();
    	vo.setUser_id(user_id);
    	System.out.println("vo : "+vo);
    	List<BasketVO> basketList = basketService.getBasketList(vo);
    	System.out.println(basketList);
    	model.addAttribute("basketList", basketList);
    	
    	return null;
    }
}
