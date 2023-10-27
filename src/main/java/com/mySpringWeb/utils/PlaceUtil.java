package com.mySpringWeb.utils;

import com.mySpringWeb.domain.CafeVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlaceUtil {

    public JSONArray getCafeListToJSON(String x, String y) {
        return cafeToObject(getCafeList(x, y));
    }

    public List<CafeVO> getAllCafe(String x, String y, int radius) {
        List<CafeVO> cafelist = new ArrayList<>();
        String restapiKey = "78ebcb4c00100253dfd2e6916a21dff5";

        String apiHost = "https://dapi.kakao.com/v2/local/search/category";

        for (int page = 1; page <= 45; page++) {
            String apiURL = String.format(
                    "%s?category_group_code=CE7&x=%s&y=%s&radius=%s&page=%d&size=45",
                    apiHost, x, y, radius, page
            );

            System.out.println(apiURL);

            try {
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", "KakaoAK " + restapiKey);

                int responseCode = con.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder res = new StringBuilder();

                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    res.append(line);
                }
                br.close();

                if (responseCode == 200) {
                    JSONParser parsing = new JSONParser();
                    Object obj = parsing.parse(res.toString());
                    JSONObject jsonObj = (JSONObject)obj;
                    JSONArray documents = (JSONArray) jsonObj.get("documents");

                    for (Object item : documents) {
                        CafeVO cafeVO = createCafe((JSONObject) item);
                        if (!cafelist.contains(cafeVO)) cafelist.add(cafeVO);
                    }

                    if(documents.size() != 45) break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return cafelist;
    }

    public List<CafeVO> getCafeList(String x, String y) {
        List<CafeVO> cafelist = new ArrayList<>();
        String restapiKey = "78ebcb4c00100253dfd2e6916a21dff5";

        String apiHost = "https://dapi.kakao.com/v2/local/search/category.json";
        String apiURL = String.format(
                "%s?category_group_code=CE7&x=%s&y=%s&radius=20000",
                apiHost, x, y
        );

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "KakaoAK " + restapiKey);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) System.out.println("주변 카페 검색 API 정상");
            else System.out.println("주변 카페 검색 API 에러");


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder res = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();
            if (responseCode == 200) {
                JSONParser parsing = new JSONParser();
                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject)obj;
                JSONArray documents = (JSONArray) jsonObj.get("documents");

                for (Object item : documents) {
                    CafeVO cafeVO = createCafe((JSONObject) item);
                    cafelist.add(cafeVO);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return cafelist;
    }

    // VO 생성이 필요하다면 진행
    private CafeVO createCafe(JSONObject jsonObject) {
        CafeVO cafe = new CafeVO();

        cafe.setAddress((String) jsonObject.get("address_name"));
        cafe.setRoad_address((String) jsonObject.get("road_address_name"));
        cafe.setPhone((String) jsonObject.get("phone"));
        cafe.setName((String) jsonObject.get("place_name"));
        cafe.setUrl((String) jsonObject.get("place_url"));
        cafe.setX((String) jsonObject.get("y"));
        cafe.setY((String) jsonObject.get("x"));

        return cafe;
    }

    public JSONArray cafeToObject(List<CafeVO> cafelist) {
        JSONArray jArray = new JSONArray();

        for (CafeVO item : cafelist) {
            JSONObject jObject = new JSONObject();

            jObject.put("name", item.getName());
            jObject.put("address", item.getAddress());
            jObject.put("road_address", item.getRoad_address());
            jObject.put("phone", item.getPhone());
            jObject.put("url", item.getUrl());
            jObject.put("x", item.getX());
            jObject.put("y", item.getY());

            jArray.add(jObject);
        }

        return jArray;
    }

//    public JSONArray cafeToObject(List<CafeVO> cafelist) {
//        JSONArray jArray = new JSONArray();
//
//        for (CafeVO item : cafelist) {
//            JSONObject jObject = new JSONObject();
//
//            jObject.put("name", item.getName());
//            jObject.put("address", item.getAddress());
//            jObject.put("detail_address", item.getDetail_address());
//            jObject.put("latitude", item.getLatitude());
//            jObject.put("longitude", item.getLongitude());
//
//            jArray.add(jObject);
//        }
//
//        return jArray;
//    }
//
//    public JSONArray libraryToObject(List<LibraryVO> librarylist) {
//        JSONArray jArray = new JSONArray();
//
//        for (LibraryVO item : librarylist) {
//            JSONObject jObject = new JSONObject();
//
//            jObject.put("name", item.getName());
//            jObject.put("address", item.getAddress());
//            jObject.put("detail_address", item.getDetail_address());
//            jObject.put("operating_time", item.getOperating_time());
//            jObject.put("closed", item.getClosed());
//            jObject.put("latitude", item.getLatitude());
//            jObject.put("longitude", item.getLongitude());
//
//            jArray.add(jObject);
//        }
//        return jArray;
//    }
}
