package com.mySpringWeb.controller.api;

import com.mySpringWeb.service.CafeService;
import com.mySpringWeb.service.LibraryService;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/place")
public class PlaceApi {
    @Autowired
    private CafeService cafeService;
    @Autowired
    private LibraryService libraryService;

    @GetMapping(value = "/cafe/{x}/{y}", produces = "application/json")
    public JSONArray getRadiusCafeList(@PathVariable("x") String x, @PathVariable("y") String y) {
        PlaceUtil placeUtil = new PlaceUtil();
        return placeUtil.getCafeListToJSON(x, y);
    }


//    @RequestMapping(value="placeRecommend.do")
//    public String placeRecommend(
//            @RequestParam(defaultValue="카페")
//            String placeCategory,
//            Model model
//    ) {
//        model.addAttribute("placeCategory", placeCategory);
//        System.out.println("카테고리 : "+placeCategory);
//
//        if(placeCategory.equals("카페")) {
//            System.out.println("카페목록 검색처리");
//            List<CafeVO> cafeList = cafeService.getCafeList();
//            JSONArray cafeArray = cafeToObject(cafeList);
//            System.out.println(cafeArray);
//            model.addAttribute("cafeArray", cafeArray);
//        }else if(placeCategory.equals("도서관")) {
//            System.out.println("도서관 목록 검색처리");
//            List<LibraryVO> libraryList = libraryService.getLibraryList();
//            JSONArray libraryArray = libraryToObject(libraryList);
//            System.out.println(libraryArray);
//            model.addAttribute("libraryArray", libraryArray);
//        } else System.out.println("placeRecommend.do 실패");
//
//        return "placeRecommend";
//    }
//
//
//    @RequestMapping(value="placeSearchFromDB.do")
//    @ResponseBody
//    public JSONArray placeSearchFromDB(String place) throws UnsupportedEncodingException {
//        System.out.println("검색정보값" + place);
//        place = URLEncoder.encode(place, "UTF-8");
//        System.out.println("블로그값2"+place);
//        JSONArray data = null;
//        // 네이버 검색 API 요청
//        String clientId = "XSL_8Ps7NFtNXXxfpzVY";
//        String clientSecret = "eLE8nAIerK";
//        String apiURL;
//        apiURL = "https://openapi.naver.com/v1/search/local.json?";
//        apiURL += "query=" + place;
//        apiURL += "&display=1&start=1&sort=sim";
//
//        try{
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("X-Naver-Client-Id", clientId);
//            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else {  // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//
//            String inputLine;
//            StringBuilder res = new StringBuilder();
//            while ((inputLine = br.readLine()) != null) {
//                res.append(inputLine);
//            }
//            br.close();
//            if(responseCode==200) {
//                JSONParser parsing = new JSONParser();
//                Object obj = parsing.parse(res.toString());
//                JSONObject jsonObj = (JSONObject)obj;
//                JSONArray items = (JSONArray)jsonObj.get("items");
//                data = items;
//            }
//        }catch(Exception e){System.out.println(e);}
//
//        System.out.println("placeSearch.do"+data);
//        return data;
//    }
//
//    @RequestMapping(value="placeSearch.do")
//    @ResponseBody
//    public JSONArray placeSearch(String place) throws UnsupportedEncodingException {
//        System.out.println("검색정보값" + place);
//        place = URLEncoder.encode(place, "UTF-8");
//        System.out.println("블로그값2"+place);
//        JSONArray data = null;
//        // 네이버 검색 API 요청
//        String clientId = "XSL_8Ps7NFtNXXxfpzVY";
//        String clientSecret = "eLE8nAIerK";
//        String apiURL;
//        apiURL = "https://openapi.naver.com/v1/search/local.json?";
//        apiURL += "query=" + place;
//        apiURL += "&display=5&start=1&sort=sim";
//
//        try{
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("X-Naver-Client-Id", clientId);
//            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else {  // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//
//            String inputLine;
//            StringBuilder res = new StringBuilder();
//            while ((inputLine = br.readLine()) != null) {
//                res.append(inputLine);
//            }
//            br.close();
//            if(responseCode==200) {
//                JSONParser parsing = new JSONParser();
//                Object obj = parsing.parse(res.toString());
//                JSONObject jsonObj = (JSONObject)obj;
//                JSONArray items = (JSONArray)jsonObj.get("items");
//                data = items;
//            }
//        }catch(Exception e){System.out.println(e);}
//
//        System.out.println("placeSearch.do"+data);
//        return data;
//    }
}
