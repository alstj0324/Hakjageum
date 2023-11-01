package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.HookUtil;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/place")
public class PlaceAPI {
    private final HookUtil hookUtil = new HookUtil();

    @GetMapping("/cafe")
    public ResponseEntity<JSONArray> getCafeList(
        @RequestParam("x") String x,
        @RequestParam("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON("카페", "CE7", x, y);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "카페 Place 조회",
            String.format(
                "x: %s\ny: %s",
                x, y
            )
        );

        return ResponseEntity.ok(arr);
    }

    @GetMapping("/library")
    public ResponseEntity<JSONArray> getLibraryList(
            @RequestParam String x,
            @RequestParam String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON("도서관", "", x, y);

        hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "도서관 Place 조회",
                String.format(
                        "x: %s\ny: %s",
                        x, y
                )
        );

        return ResponseEntity.ok(arr);
    }

    @GetMapping("/search")
    public ResponseEntity<JSONArray> getSearchList(
            @RequestParam String keyword,
            @RequestParam String x,
            @RequestParam String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON(keyword, "", x, y);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "검색 Place 조회",
            String.format(
                "keyword: %s\nx: %s\ny: %s",
                keyword, x, y
            )
        );

        return ResponseEntity.ok(arr);
    }
}
