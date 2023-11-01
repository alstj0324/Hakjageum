package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.user.UserVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.service.UserService;
import com.mySpringWeb.utils.HookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/info")
public class UserAPI {
	
    @Autowired
    private UserService userService;

    private final HookUtil hookUtil = new HookUtil();

    @GetMapping("/get")
    public ResponseEntity<UserVO> getUserInfo(@RequestParam String userId) {
        UserVO uservo = userService.getUser(userId);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "User 정보 조회",
            String.format(
                "UserId: %s",
                userId
            )
        );

        return ResponseEntity.ok(uservo);
    }
}
