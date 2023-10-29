package com.mySpringWeb.controller;

import com.mySpringWeb.utils.BlogUtil;
import com.mySpringWeb.utils.BookUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class BookController {
	
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
            Model model
    ) throws UnsupportedEncodingException {
        BookUtil bookUtil = new BookUtil();
        bookUtil.getBookList(category, model, 1, 1);

        BlogUtil blogUtil = new BlogUtil();
        blogUtil.getBlogList(blog, model, 1, 10);

        return "bookReview";
    }
}
