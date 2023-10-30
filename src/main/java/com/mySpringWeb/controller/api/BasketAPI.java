package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.HookUtil;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/basket")
public class BasketAPI {
	
    @Autowired
    private BasketService basketService;

    /*
        GET /api/user/basket/get?userId={UserId}
     */
    @GetMapping("/get")
    public ResponseEntity<JSONArray> new_getBasketList(@RequestParam String userId) {
        JSONArray arr = basketService.getBasketList(userId);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("Basket 정보 조회", "UserId: " + userId + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(arr);
    }

    /*
        GET /api/user/basket/getbookinfo?userId={UserId}
     */
    @GetMapping("/getbookinfo")
    public ResponseEntity<JSONArray> new_getBasketListToBookInfo(@RequestParam String userId) {
        JSONArray arr = basketService.getBasketList(userId);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("Basket To Book 정보 조회", "UserId: " + userId + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(arr);
    }


    @Deprecated
    @GetMapping("/get/{userId}")
    public ResponseEntity<JSONArray> getBasketList(@PathVariable("userId") String userId) {
        JSONArray arr = basketService.getBasketList(userId);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("Basket 정보 조회", "UserId: " + userId + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(arr);
    }

    @Deprecated
    @GetMapping("/getvo/{userId}")
    public ResponseEntity<JSONArray> getBasketListToVo(@PathVariable("userId") String userId) {
        JSONArray arr = basketService.getBasketList(userId);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("BasketVO 정보 조회", "UserId: " + userId + "\nData:\n" + arr.toJSONString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);
        
        return ResponseEntity.ok(arr);
    }
}
