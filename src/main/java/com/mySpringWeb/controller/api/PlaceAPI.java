package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.HookUtil;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("카페 Place 조회", "x: " + x + "\ny: " + y + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(arr);
    }

    @GetMapping("/library/{x}/{y}")
    public ResponseEntity<JSONArray> getLibraryList(
            @PathVariable("x") String x,
            @PathVariable("y") String y
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONArray arr = placeUtil.getAllPlaceToJSON("도서관", "", x, y);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("도서관 Place 조회", "x: " + x + "\ny: " + y + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

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

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("검색 Place 조회", "keyword: " + keyword + "\nx: " + x + "\ny: " + y + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(arr);
    }
}
