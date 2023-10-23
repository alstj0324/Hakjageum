package com.mySpringWeb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mySpringWeb.domain.BlogVO;
import com.mySpringWeb.domain.BookVO;
import com.mySpringWeb.domain.CafeVO;
import com.mySpringWeb.domain.LibraryVO;
import com.mySpringWeb.service.CafeService;
import com.mySpringWeb.service.LibraryService;


@Controller
public class MainController {

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String main() {
		return "redirect:/main.jsp";
	}
}
