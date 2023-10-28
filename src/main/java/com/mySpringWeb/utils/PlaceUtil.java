package com.mySpringWeb.utils;

import com.mySpringWeb.domain.PlaceVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PlaceUtil {

    public JSONArray getAllPlaceToJSON(String keyword, String code, String x, String y) {
        return placeToObject(getAllPlace(keyword, code, x, y));
    }

    public List<PlaceVO> getAllPlace(String keyword, String code, String x, String y) {
        return getPlaceData(x, y, code, keyword, 1000);
    }

    public List<PlaceVO> getPlaceData(String x, String y, String category_group_code, String query, int radius) {
        List<PlaceVO> placelist = new ArrayList<>();
        String restapiKey = "78ebcb4c00100253dfd2e6916a21dff5";
        String apiHost = "https://dapi.kakao.com/v2/local/search/keyword.json";

        for (int page = 1; page <= 2; page++) {
            try {
                String keyword = URLEncoder.encode(query, "UTF-8");

                String apiURL = String.format(
                        "%s?category_group_code=%s&x=%s&y=%s&radius=%d&page=%d&size=10&query=%s",
                        apiHost, category_group_code, x, y, radius, page, keyword
                );

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
                        PlaceVO placeVO = createPlace((JSONObject) item);
                        if (!placelist.contains(placeVO)) placelist.add(placeVO);
                    }

                    if(documents.size() != 10) break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return placelist;
    }

    public JSONObject getMyloc(String ip) {
        JSONObject result = new JSONObject();
        String apiHost = "http://ip-api.com";

        String apiURL = String.format(
                "%s/json/%s",
                apiHost, ip
        );

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

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
                JSONObject jsonObj = (JSONObject) obj;

                result.put("x", jsonObj.get("lat").toString());
                result.put("y", jsonObj.get("lon").toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    // VO 생성이 필요하다면 진행
    private PlaceVO createPlace(JSONObject jsonObject) {
        PlaceVO place = new PlaceVO();

        place.setAddress((String) jsonObject.get("address_name"));
        place.setRoad_address((String) jsonObject.get("road_address_name"));
        place.setPhone((String) jsonObject.get("phone"));
        place.setName((String) jsonObject.get("place_name"));
        place.setUrl((String) jsonObject.get("place_url"));
        place.setX((String) jsonObject.get("y"));
        place.setY((String) jsonObject.get("x"));

        return place;
    }

    // VO를 JSON Object로 변환
    public JSONArray placeToObject(List<PlaceVO> placelist) {
        JSONArray jArray = new JSONArray();

        for (PlaceVO item : placelist) {
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
}
