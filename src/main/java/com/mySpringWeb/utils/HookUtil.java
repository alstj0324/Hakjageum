package com.mySpringWeb.utils;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HookUtil {
    private final String hook_url;

    public HookUtil() {
        EnvUtil envUtil = new EnvUtil();
        String Developer = envUtil.getValueByKey("DEVELOPER");
        this.hook_url = envUtil.getValueByKey("WEBHOOK_" + Developer);
    }

    private void setProfile(HookVO hookVO, HookLevel level) {
        if (level == HookLevel.INFO) {
            hookVO.setUsername("학자금 로그봇");
            hookVO.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/8577/8577916.png");
        } else if (level == HookLevel.WARN) {
            hookVO.setUsername("학자금 경고봇");
            hookVO.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/6897/6897039.png");
        } else if (level == HookLevel.DANGER) {
            hookVO.setUsername("학자금 위험봇");
            hookVO.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/6785/6785368.png");
        }
    }

    public HookVO create_Hook(HookLevel level, String content) {
        HookVO hookVO = HookVO.builder()
        .url(hook_url)
        .content(content)
        .tts(false)
        .build();

        setProfile(hookVO, level);
        return hookVO;
    }

    public HookVO create_Hook(HookLevel level, List<EmbedVO> embedList) {
        HookVO hookVO = HookVO.builder()
        .url(hook_url)
        .embedList(embedList)
        .tts(false)
        .build();

        setProfile(hookVO, level);
        return hookVO;
    }

    public void send_Embed_Hook(HookLevel level, String title, String content) {
        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();

        content = (content.length() > 1800 ? content.substring(0, 1800) + "..." : content);
        String datestr = dateToString(new Date());
        String time_str = String.format("`발생시간: %s`(<t:%d:R>)\n", datestr, splitSecond(new Date()));

        EmbedVO vo = EmbedVO.builder()
        .title("[" + level.name() + "] " + title)
        .description(time_str + "```" + content + "```")
        .color(level.name().equals("INFO") ? Color.decode("#99FFCC") : level.name().equals("WARN") ? Color.decode("#FF9900") : Color.decode("#FF0000"))
        .build();

        List<EmbedVO> embedList = new ArrayList<>();
        embedList.add(vo);

        HookVO hookVO = create_Hook(level, embedList);
        discordWebhookService.sendWebhook(hookVO);
    }

    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    private long splitSecond(Date date) {
        return date.getTime() / 1000;
    }

}
