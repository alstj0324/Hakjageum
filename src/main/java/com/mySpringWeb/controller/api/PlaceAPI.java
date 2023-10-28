package com.mySpringWeb.controller.api;

import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/place")
public class PlaceAPI {

    @GetMapping("/cafe/{x}/{y}")
    public ResponseEntity<JSONArray> getCafeList(
        @PathVariable("x") String x,
        @PathVariable("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON("카페", "CE7", x, y);
        return ResponseEntity.ok(arr);
    }

    @GetMapping("/library/{x}/{y}")
    public ResponseEntity<JSONArray> getLibraryList(
            @PathVariable("x") String x,
            @PathVariable("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON("도서관", "", x, y);
        return ResponseEntity.ok(arr);
    }

    @GetMapping("/search/{keyword}/{x}/{y}")
    public ResponseEntity<JSONArray> getSearchList(
            @PathVariable("keyword") String keyword,
            @PathVariable("x") String x,
            @PathVariable("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON(keyword, "", x, y);
        return ResponseEntity.ok(arr);
    }
}
