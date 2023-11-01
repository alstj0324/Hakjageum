package com.mySpringWeb.utils;

import com.mySpringWeb.domain.bookrecommend.BookVO;
import com.mySpringWeb.domain.place.PlaceVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class MappingUtil {

    public JSONArray bookToObject(List<BookVO> placelist) {
        JSONArray jArray = new JSONArray();

        for (BookVO item : placelist) {
            JSONObject jObject = new JSONObject();

            jObject.put("title", item.getTitle().split("\\(")[0]);
            jObject.put("author", item.getAuthor().replaceAll("\\^", ", "));
            jObject.put("link", item.getLink());
            jObject.put("image", item.getImage());
            jObject.put("pubdate", item.getPubdate());
            jObject.put("isbn", item.getIsbn());
            jObject.put("description", item.getDescription());
            jObject.put("discount", item.getDiscount());
            jObject.put("publisher", item.getPublisher());

            jArray.add(jObject);
        }

        return jArray;
    }

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

