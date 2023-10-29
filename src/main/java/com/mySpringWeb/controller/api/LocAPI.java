package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.HookUtil;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/loc")
public class LocAPI {

    @GetMapping("/get1")
    public ResponseEntity<Boolean> get1() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/get/{ip}")
    public ResponseEntity<JSONObject> getIp(
        @PathVariable("ip")
        String ip
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONObject loc = placeUtil.getMyloc(ip);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("Location 정보 조회", "IP: " + ip + "\nLOC: " + loc.toJSONString());
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, (List<EmbedVO>) embedVO);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(loc);
    }
}
