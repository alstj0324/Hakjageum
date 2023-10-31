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

    @PreDestroy
    public void cleanUp() {
        try {
            DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
            List<EmbedVO> embedList = new ArrayList<>();
            HookUtil hookUtil = new HookUtil();
            EmbedVO embedVO = hookUtil.Info_Embed("서버 종료", "✅ 정상적으로 종료되었습니다.");
            embedList.add(embedVO);
            HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
            discordWebhookService.sendWebhook(hookVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
