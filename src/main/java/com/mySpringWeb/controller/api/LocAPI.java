package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.utils.HookUtil;
import com.mySpringWeb.utils.PlaceUtil;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loc")
public class LocAPI {
    private final HookUtil hookUtil = new HookUtil();

    @GetMapping("/get")
    public ResponseEntity<JSONObject> getIp(
        @RequestParam
        String ip
    ) {
        PlaceUtil placeUtil = new PlaceUtil();
        JSONObject loc = placeUtil.getMyloc(ip);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "IP 조회",
            String.format(
                "ip: %s",
                ip
            )
        );

        return ResponseEntity.ok(loc);
    }
}
