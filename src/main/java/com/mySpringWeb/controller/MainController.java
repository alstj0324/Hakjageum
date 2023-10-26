package com.mySpringWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mySpringWeb.service.LibraryService;


@Controller
public class MainController {
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String main() {
		return "redirect:/main.jsp";
	}
}
