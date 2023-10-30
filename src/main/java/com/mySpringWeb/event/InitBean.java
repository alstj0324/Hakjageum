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
    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        try {
            DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
            List<EmbedVO> embedList = new ArrayList<>();
            HookUtil hookUtil = new HookUtil();
            EmbedVO embedVO = hookUtil.Info_Embed("서버 실행", "✅ 정상적으로 실행되었습니다.");
            embedList.add(embedVO);
            HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
            discordWebhookService.sendWebhook(hookVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
