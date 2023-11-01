package com.mySpringWeb.event;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.HookUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class DestroyBean {
    private final HookUtil hookUtil = new HookUtil();
    @PreDestroy
    public void cleanUp() {
        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "서버 종료",
            "✅ 정상적으로 종료되었습니다."
        );
    }
}
