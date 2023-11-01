package com.mySpringWeb.utils;

import com.mySpringWeb.domain.place.PlaceVO;
import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.domain.webhook.HookLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class PlaceUtil {
    private final HookUtil hookUtil = new HookUtil();
    private final MappingUtil mappingUtil = new MappingUtil();
    public JSONArray getAllPlaceToJSON(String keyword, String code, String x, String y) {
        return mappingUtil.placeToObject(getAllPlace(keyword, code, x, y));
    }

    public List<PlaceVO> getAllPlace(String keyword, String code, String x, String y) {
        return getPlaceData(x, y, code, keyword, 1000);
    }

    public List<PlaceVO> getPlaceData(String x, String y, String category_group_code, String query, int radius) {
        List<PlaceVO> places = new ArrayList<>();

        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();

        String restapiKey = envUtil.getValueByKey("KAKAO_RESTKEY");
        headers.put("Authorization", "KakaoAK " + restapiKey);
        int size = 10;

        for (int page = 1; page <= 2; page++) {
            Map<String, Object> params = new HashMap<>();
            params.put("category_group_code", category_group_code);
            params.put("x", x);
            params.put("y", y);
            params.put("radius", radius);
            params.put("page", page);
            params.put("size", size);
            params.put("query", query);

            JSONObject result = requestUtil.requestData(RequestType.KAKAO_PLACE_KEYWORD_SEARCH, "GET", headers, params);

            String status = (String) result.get("result_status");

            if (Objects.equals(status, "success")) {
                JSONObject data = (JSONObject) result.get("result_data");
                JSONArray items = (JSONArray) data.get("documents");

                for (Object item : items) {
                    PlaceVO place = createPlace((JSONObject) item);
                    if (!places.contains(place)) places.add(place);
                }
            } else hookUtil.send_Embed_Hook(
                    HookLevel.WARN,
                    "플레이스 목록 조회 실패",
                    String.format(
                            "Function: %s > %s\n%s",
                            getClass().getName(),
                            "getPlaceData",
                            String.format(
                                    "category_group_cde: %s\nx: %s\ny: %s\nradius: %s\npage: %s\nsize: %s\nquery: %s",
                                    category_group_code, x, y, radius, page, size, query
                            )
                    )
            );
        }
        return places;
    }

    public JSONObject getMyloc(String ip) {
        JSONObject loc = new JSONObject();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> params = new HashMap<>();

        params.put("fields", 16576);

        JSONObject result = requestUtil.requestData(RequestType.IP_LOCATION, "GET", null, params, ip);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");

            loc.put("x", data.get("lat").toString());
            loc.put("y", data.get("lon").toString());
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "IP 좌표 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getMyloc",
                String.format(
                    "ip: %s",
                    ip
                )
            )
        );

        return loc;
    }

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
}
