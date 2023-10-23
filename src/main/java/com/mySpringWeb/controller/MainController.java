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
	
	@RequestMapping(value="placeRecommend.do")
	public String placeRecommend(@RequestParam(defaultValue="카페")String placeCategory, Model model) {
		model.addAttribute("placeCategory", placeCategory);
		System.out.println("카테고리 : "+placeCategory);
		
		if(placeCategory.equals("카페")) {
			System.out.println("카페목록 검색처리");
			List<CafeVO> cafeList = cafeService.getCafeList();
			JSONArray cafeArray = cafeToObject(cafeList);
			System.out.println(cafeArray);
			model.addAttribute("cafeArray", cafeArray);
		}else if(placeCategory.equals("도서관")) {
			System.out.println("도서관 목록 검색처리");
			List<LibraryVO> libraryList = libraryService.getLibraryList();
			JSONArray libraryArray = libraryToObject(libraryList);
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

	private JSONArray cafeToObject(List<CafeVO> cafelist) {
		JSONArray jArray = new JSONArray();

		for (CafeVO item : cafelist) {
			JSONObject jObject = new JSONObject();

			jObject.put("name", item.getName());
			jObject.put("address", item.getAddress());
			jObject.put("detail_address", item.getDetail_address());
			jObject.put("latitude", item.getLatitude());
			jObject.put("longitude", item.getLongitude());

			jArray.add(jObject);
		}

		return jArray;
	}

	private JSONArray libraryToObject(List<LibraryVO> librarylist) {
		JSONArray jArray = new JSONArray();

		for (LibraryVO item : librarylist) {
			JSONObject jObject = new JSONObject();

			jObject.put("name", item.getName());
			jObject.put("address", item.getAddress());
			jObject.put("detail_address", item.getDetail_address());
			jObject.put("operating_time", item.getOperating_time());
			jObject.put("closed", item.getClosed());
			jObject.put("latitude", item.getLatitude());
			jObject.put("longitude", item.getLongitude());

			jArray.add(jObject);
		}
		return jArray;
	}
}
