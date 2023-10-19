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
	@Autowired
	private CafeService cafeService;
	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping(value="/WEB-INF/views/main.jsp")
	public String main() {
		return "main";
	}
	@RequestMapping(value="bookRecommend.do", method = RequestMethod.GET)
	public String bookRecommend(@RequestParam(defaultValue="자기계발")String category ,Model model) throws UnsupportedEncodingException {
		category = URLEncoder.encode(category, "UTF-8");
		
		// 네이버 검색 API 요청
		String clientId = "XSL_8Ps7NFtNXXxfpzVY"; 		
		String clientSecret = "eLE8nAIerK";
		String apiURL;
			apiURL = "https://openapi.naver.com/v1/search/book.json?";
			apiURL += "query=" + category;
			apiURL += "&display=100&start=100&sort=sim";
			
			try{
		    	URL url = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        con.setRequestMethod("GET");
		        con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		        int responseCode = con.getResponseCode();
		        BufferedReader br;
		        if(responseCode==200) { // 정상 호출
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          } else {  // 에러 발생
		            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		          }
		        
		        String inputLine;
		        StringBuilder res = new StringBuilder();
		        while ((inputLine = br.readLine()) != null) {
		          res.append(inputLine);
		        }
		        br.close();
		        if(responseCode==200) {
		      	  JSONParser parsing = new JSONParser();
		          Object obj = parsing.parse(res.toString());
		       	  JSONObject jsonObj = (JSONObject)obj;
		       	  JSONArray item = (JSONArray)jsonObj.get("items");
		       	  List<BookVO> books= null;
		       	  books = new ArrayList<BookVO>();
		       	  
			       	for (int i = 0; i < item.size(); i ++) {
			       		BookVO vo = new BookVO(); 
			       	    JSONObject tmp = (JSONObject)item.get(i);
			       	    String title = (String)tmp.get("title");
			       	    String image = (String)tmp.get("image");
			       	    String description = (String)tmp.get("description");
			       	    description = description.replaceAll("\"", "\'");
			       	    String author = (String)tmp.get("author");
			       	    String link = (String)tmp.get("link");
			       	    String pubdate = (String)tmp.get("pubdate");
			       	    String isbn = (String)tmp.get("isbn");
			       	    vo.setTitle(title);
			       	    vo.setImage(image);
			       	    vo.setDescription(description);
			       	    vo.setAuthor(author);
			       	    vo.setLink(link);
			       	    vo.setPubdate(pubdate);
			       	    vo.setIsbn(isbn);
			       	    if (vo != null) books.add(vo);
			       	}
			       	
			       	model.addAttribute("books", books);
			       	
		        }
		    }catch(Exception e){System.out.println(e);}
			
			
		return "bookRecommend";
	}
	@RequestMapping(value="bookReview.do")
	public String bookReview(@RequestParam(defaultValue="자기계발")String category,@RequestParam(defaultValue="자기계발")String blog ,Model model) throws UnsupportedEncodingException {
		category = URLEncoder.encode(category, "UTF-8");
		blog += " 리뷰";
		System.out.println("블로그값1"+blog);
		blog = URLEncoder.encode(blog, "UTF-8");
		System.out.println("블로그값2"+blog);
		
		// 네이버 검색 API 요청
		String clientId = "XSL_8Ps7NFtNXXxfpzVY"; 		
		String clientSecret = "eLE8nAIerK";
		String apiURL;
			apiURL = "https://openapi.naver.com/v1/search/book.json?";
			apiURL += "query=" + category;
			apiURL += "&display=1&start=1&sort=sim";
			
			try{
		    	URL url = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        con.setRequestMethod("GET");
		        con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		        int responseCode = con.getResponseCode();
		        BufferedReader br;
		        if(responseCode==200) { // 정상 호출
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          } else {  // 에러 발생
		            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		          }
		        
		        String inputLine;
		        StringBuilder res = new StringBuilder();
		        while ((inputLine = br.readLine()) != null) {
		          res.append(inputLine);
		        }
		        br.close();
		        if(responseCode==200) {
		      	  JSONParser parsing = new JSONParser();
		          Object obj = parsing.parse(res.toString());
		       	  JSONObject jsonObj = (JSONObject)obj;
		       	  JSONArray item = (JSONArray)jsonObj.get("items");
		       	  BookVO vo = new BookVO();
		       	  
		       	  
			       	for (int i = 0; i < 1; i ++) { 
			       	    JSONObject tmp = (JSONObject)item.get(i);
			       	    String title = (String)tmp.get("title");
			       	    String image = (String)tmp.get("image");
			       	    String description = (String)tmp.get("description");
			       	    description = description.replaceAll("\"", "\'");
			       	    String author = (String)tmp.get("author");
			       	    String link = (String)tmp.get("link");
			       	    String pubdate = (String)tmp.get("pubdate");
			       	    vo.setTitle(title);
			       	    vo.setImage(image);
			       	    vo.setDescription(description);
			       	    vo.setAuthor(author);
			       	    vo.setLink(link);
			       	    vo.setPubdate(pubdate);
			       	}
			       	model.addAttribute("books", vo);
		        }
		    }catch(Exception e){System.out.println(e);}
			
			/*----------------------------------------------------------------------------------------------------*/
			String apiURL2;
			apiURL2 = "https://openapi.naver.com/v1/search/blog.json?";
			apiURL2 += "query=" + blog;
			apiURL2 += "&display=10&start=1&sort=sim";
			
			try{
		    	URL url2 = new URL(apiURL2);
		        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		        con2.setRequestMethod("GET");
		        con2.setRequestProperty("X-Naver-Client-Id", clientId);
	            con2.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		        int responseCode2 = con2.getResponseCode();
		        BufferedReader br2;
		        if(responseCode2==200) { // 정상 호출
		            br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
		          } else {  // 에러 발생
		            br2 = new BufferedReader(new InputStreamReader(con2.getErrorStream()));
		          }
		        
		        String inputLine2;
		        StringBuilder res2 = new StringBuilder();
		        while ((inputLine2 = br2.readLine()) != null) {
		          res2.append(inputLine2);
		        }
		        br2.close();
		        if(responseCode2==200) {
		      	  JSONParser parsing2 = new JSONParser();
		          Object obj2 = parsing2.parse(res2.toString());
		       	  JSONObject jsonObj2 = (JSONObject)obj2;
		       	  System.out.println("블로그 결과 : "+jsonObj2);
		       	  JSONArray item = (JSONArray)jsonObj2.get("items"); 
		       	  List<BlogVO> blogs= null;
		       	  blogs = new ArrayList<BlogVO>();
		       	  
			       	for (int i = 0; i < item.size(); i ++) {
			       		BlogVO vo = new BlogVO(); 
			       	    JSONObject tmp = (JSONObject)item.get(i);
			       	    String title = (String)tmp.get("title");
			       	    String description = (String)tmp.get("description");
			       	    description = description.replaceAll("\"", "\'");
			       	    String link = (String)tmp.get("link");
			       	    vo.setTitle(title);
			       	    vo.setDescription(description); 
			       	    vo.setLink(link);
			       	    if (vo != null) blogs.add(vo);
			       	}
			       	System.out.println(blogs);
			       	model.addAttribute("blogs", blogs);
		        }
		    }catch(Exception e){System.out.println(e);}
			
		return "bookReview";
	}
	
	@RequestMapping(value="placeRecommend.do")
	public String placeRecommend(@RequestParam(defaultValue="카페")String placeCategory, Model model) {
		model.addAttribute("placeCategory", placeCategory);
		System.out.println("카테고리 : "+placeCategory);
		
		if(placeCategory.equals("카페")) {
			System.out.println("카페목록 검색처리");
			List<CafeVO> cafeList = cafeService.getCafeList();
			net.sf.json.JSONArray cafeArray = net.sf.json.JSONArray.fromObject(cafeList);
			System.out.println(cafeArray);
			model.addAttribute("cafeArray", cafeArray);
		}else if(placeCategory.equals("도서관")) {
			System.out.println("도서관 목록 검색처리");
			List<LibraryVO> libraryList = libraryService.getLibraryList();
			net.sf.json.JSONArray libraryArray = net.sf.json.JSONArray.fromObject(libraryList);
			System.out.println(libraryArray);
			model.addAttribute("libraryArray", libraryArray);
		}else System.out.println("placeRecommend.do 실패");
		
		
		
		return "placeRecommend";
	}
	
	
	@RequestMapping(value="placeSearchFromDB.do")
	@ResponseBody
	public JSONArray placeSearchFromDB(String place) throws UnsupportedEncodingException {
		System.out.println("검색정보값" + place);
		place = URLEncoder.encode(place, "UTF-8");
		System.out.println("블로그값2"+place);
		JSONArray data = null;
		// 네이버 검색 API 요청
		String clientId = "XSL_8Ps7NFtNXXxfpzVY"; 		
		String clientSecret = "eLE8nAIerK";
		String apiURL;
			apiURL = "https://openapi.naver.com/v1/search/local.json?";
			apiURL += "query=" + place;
			apiURL += "&display=1&start=1&sort=sim";
			
			try{
		    	URL url = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        con.setRequestMethod("GET");
		        con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		        int responseCode = con.getResponseCode();
		        BufferedReader br;
		        if(responseCode==200) { // 정상 호출
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          } else {  // 에러 발생
		            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		          }
		        
		        String inputLine;
		        StringBuilder res = new StringBuilder();
		        while ((inputLine = br.readLine()) != null) {
		          res.append(inputLine);
		        }
		        br.close();
		        if(responseCode==200) {
		      	  JSONParser parsing = new JSONParser();
		          Object obj = parsing.parse(res.toString());
		       	  JSONObject jsonObj = (JSONObject)obj;
		       	  JSONArray items = (JSONArray)jsonObj.get("items"); 
			      data = items; 
		        }
		    }catch(Exception e){System.out.println(e);}
		
		System.out.println("placeSearch.do"+data);
		return data;
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="placeSearch.do")
	@ResponseBody
	public JSONArray placeSearch(String place) throws UnsupportedEncodingException {
		System.out.println("검색정보값" + place);
		place = URLEncoder.encode(place, "UTF-8");
		System.out.println("블로그값2"+place);
		JSONArray data = null;
		// 네이버 검색 API 요청
		String clientId = "XSL_8Ps7NFtNXXxfpzVY"; 		
		String clientSecret = "eLE8nAIerK";
		String apiURL;
			apiURL = "https://openapi.naver.com/v1/search/local.json?";
			apiURL += "query=" + place;
			apiURL += "&display=5&start=1&sort=sim";
			
			try{
		    	URL url = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        con.setRequestMethod("GET");
		        con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		        int responseCode = con.getResponseCode();
		        BufferedReader br;
		        if(responseCode==200) { // 정상 호출
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          } else {  // 에러 발생
		            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		          }
		        
		        String inputLine;
		        StringBuilder res = new StringBuilder();
		        while ((inputLine = br.readLine()) != null) {
		          res.append(inputLine);
		        }
		        br.close();
		        if(responseCode==200) {
		      	  JSONParser parsing = new JSONParser();
		          Object obj = parsing.parse(res.toString());
		       	  JSONObject jsonObj = (JSONObject)obj;
		       	  JSONArray items = (JSONArray)jsonObj.get("items"); 
			      data = items; 
		        }
		    }catch(Exception e){System.out.println(e);}
		
		System.out.println("placeSearch.do"+data);
		return data;
	}
	
}
