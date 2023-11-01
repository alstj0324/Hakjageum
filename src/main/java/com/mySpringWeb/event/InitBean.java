package com.mySpringWeb.event;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.HookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitBean {
    private final HookUtil hookUtil = new HookUtil();

    @PostConstruct
    public void init() {
        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "서버 실행",
            "✅ 정상적으로 실행되었습니다."
        );
    }
}
