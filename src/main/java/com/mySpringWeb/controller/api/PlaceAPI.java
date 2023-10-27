package com.mySpringWeb.controller.api;

import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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
        JSONArray arr = placeUtil.getAllCafeToJSON(x, y);
        return ResponseEntity.ok(arr);
    }

    @GetMapping("/library/{x}/{y}")
    public ResponseEntity<JSONArray> getLibraryList(
            @PathVariable("x") String x,
            @PathVariable("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllLibraryToJSON(x, y);
        return ResponseEntity.ok(arr);
    }
}
