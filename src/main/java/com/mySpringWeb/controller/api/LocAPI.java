package com.mySpringWeb.controller.api;

import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loc")
public class LocAPI {

    @GetMapping("/get/{ip}")
    public ResponseEntity<JSONObject> getIp(
        @PathVariable("ip")
        String ip
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONObject loc = placeUtil.getMyloc(ip);
        return ResponseEntity.ok(loc);
    }
}
