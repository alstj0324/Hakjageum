package com.mySpringWeb.utils;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HookUtil {
    private final String hook_log = "https://discord.com/api/webhooks/1168194471901544568/E8ZipDSwFkfhadRxYde9K_cIO8V2cA1qadG1ywmoNXF5fhc24IB5afmX1aJylxmOps5V";

    public HookVO create_Hook(HookLevel level, String content) {
        HookVO hookVO = HookVO.builder()
                .url(hook_log)
                .content(content)
                .tts(false)
                .build();

        setProfile(hookVO, level);
        return hookVO;
    }

    public HookVO create_Hook(HookLevel level, List<EmbedVO> embedList) {
        HookVO hookVO = HookVO.builder()
        .url(hook_log)
        .embedList(embedList)
        .tts(false)
        .build();

        setProfile(hookVO, level);
        return hookVO;
    }

    public EmbedVO Info_Embed(String title, String content) {
        String datestr = dateToString(new Date());
        long time = splitSecond(new Date());
        return EmbedVO.builder()
        .title("[Info] " + title)
        .description(String.format("`발생시간: %s`(<t:%d:R>)\n```%s```", datestr, time, (content.length() > 1800 ? content.substring(0, 1800) + "..." : content)))
        .color(Color.decode("#99FFCC"))
        .build();
    }

    public EmbedVO Warn_Embed(String title, String content) {
        String datestr = dateToString(new Date());
        long time = splitSecond(new Date());
        return EmbedVO.builder()
        .title("[Warn] " + title)
        .description(String.format("`발생시간: %s`(<t:%d:R>)\n```%s```", datestr, time, (content.length() > 1800 ? content.substring(0, 1800) + "..." : content)))
        .color(Color.decode("#FF9900"))
        .build();
    }

    public EmbedVO Danger_Embed(String title, String content) {
        String datestr = dateToString(new Date());
        long time = splitSecond(new Date());
        return EmbedVO.builder()
        .title("[Danger] " + title)
        .description(String.format("`발생시간: %s`(<t:%d:R>)\n```%s```", datestr, time, (content.length() > 1800 ? content.substring(0, 1800) + "..." : content)))
        .color(Color.decode("#FF0000"))
        .build();
    }

    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    private long splitSecond(Date date) {
        return (long) (date.getTime() / 1000);
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
}
