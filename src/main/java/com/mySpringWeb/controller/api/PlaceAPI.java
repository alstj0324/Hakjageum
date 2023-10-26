package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.CafeVO;
import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/place")
public class PlaceAPI {

    @Autowired
    private BasketService basketService;

    @GetMapping("/cafe/{x}/{y}")
    public ResponseEntity<JSONArray> getCafeList(
        @PathVariable("x") String x,
        @PathVariable("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        List<CafeVO> arr = placeUtil.getAllCafe(x, y, 20000);
        JSONArray jarr = placeUtil.cafeToObject(arr);
        return ResponseEntity.ok(jarr);
    }
}
