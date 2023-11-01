package com.mySpringWeb.controller;

import com.mySpringWeb.service.BasketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	@Autowired
	private BasketService basketService;

    @RequestMapping(value="bookRecommend.do", method = RequestMethod.GET)
    public String bookRecommend() {
        return "bookRecommend";
    }

    @RequestMapping(value="bookReview.do", method = RequestMethod.GET)
    public String bookReview() {
        return "bookReview";
    }
}
