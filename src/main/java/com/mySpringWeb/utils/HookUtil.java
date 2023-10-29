package com.mySpringWeb.utils;

import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.domain.webhook.embed.Footer;

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
        return EmbedVO.builder()
        .title("[Info] " + title)
        .description("```" + content + "```")
        .color(Color.BLUE)
        .footer(new Footer(String.format("`발생시간: %s(<t:%d:R>)`", NowDateFormated(), new Date().getTime()), ""))
        .build();
    }

    public EmbedVO Warn_Embed(String title, String content) {
        return EmbedVO.builder()
        .title("[Warn] " + title)
        .description("```" + content + "```")
        .color(Color.ORANGE)
        .footer(new Footer(String.format("`발생시간: %s(<t:%d:R>)`", NowDateFormated(), new Date().getTime()), ""))
        .build();
    }

    public EmbedVO Danger_Embed(String title, String content) {
        return EmbedVO.builder()
        .title("[Danger] " + title)
        .description("```" + content + "```")
        .color(Color.RED)
        .footer(new Footer(String.format("`발생시간: %s(<t:%d:R>)`", NowDateFormated(), new Date().getTime()), ""))
        .build();
    }

    private String NowDateFormated() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private void setProfile(HookVO hookVO, HookLevel level) {
        if (level == HookLevel.INFO) {
            hookVO.setUsername("학자금 로그봇");
            hookVO.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/665/665049.png");
        } else if (level == HookLevel.WARN) {
            hookVO.setUsername("학자금 경고봇");
            hookVO.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/6897/6897039.png");
        } else if (level == HookLevel.DANGER) {
            hookVO.setUsername("학자금 위험봇");
            hookVO.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/6897/6897039.png");
        }
    }
}
